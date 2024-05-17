package hex.model.game.theoric.evaluator;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.Game;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Evaluator {

    public static Double evaluate(Board board) {
        if (board == null) {
            throw new NullPointerException("Null game");
        }

        Game game = new Game(board);
        boolean adversaryWin = game.existLine(0);
        boolean computerWin = game.existLine(1);

        if (adversaryWin) {
            return 800.0;
        } else if (computerWin) {
            return -800.0;
        }

        board.refreshAllVisit();
        int numberPathAbleForAdversary = getTokenConnected(board, 0);
        int numberPathAbleForComputer = getTokenConnected(board, 1);

        System.out.println("Number of paths for adversary: " + numberPathAbleForAdversary);
        System.out.println("Number of paths for computer: " + numberPathAbleForComputer);

        int centralControl = getCentralControl(board, 1) - getCentralControl(board, 0);
        System.out.println("Central control differential: " + centralControl);

        double shortestPathDiff = getShortestPathDiff(board);
        System.out.println("Shortest path differential: " + shortestPathDiff);

        int borderControl = getBorderControl(board, 1) - getBorderControl(board, 0);
        System.out.println("Border control differential: " + borderControl);

        int blockingPotential = getBlockingPotential(board, 1) - getBlockingPotential(board, 0);
        System.out.println("Blocking potential differential: " + blockingPotential);

        int freedomOfMovement = getFreedomOfMovement(board, 1) - getFreedomOfMovement(board, 0);
        System.out.println("Freedom of movement differential: " + freedomOfMovement);

        return (double) (numberPathAbleForComputer - numberPathAbleForAdversary) + centralControl + shortestPathDiff + borderControl + blockingPotential + freedomOfMovement;
    }

    public static int getTokenConnected(Board board, int player) {
        int tokenConnected = 0;
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                Cell cell = board.getGrid()[i][j];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == player && !cell.isVisited()) {
                    int connectedFromThisCell = browseFromCell(cell);
                    tokenConnected += connectedFromThisCell;
                    System.out.println("Connected tokens from cell (" + i + ", " + j + "): " + connectedFromThisCell);
                    board.refreshAllVisit();
                }
            }
        }
        return tokenConnected;
    }

    public static int browseFromCell(Cell cell) {
        int connected = 1;
        cell.setVisit(true);
        for (Direction direction : cell.getDirections().keySet()) {
            Cell cellInDirection = cell.getDirections().get(direction);
            if (cellInDirection != null && !cellInDirection.isVisited() && cellInDirection.getState() == State.PLAYER && cellInDirection.getPlayer() == cell.getPlayer()) {
                connected += browseFromCell(cellInDirection);
            }
        }
        return connected;
    }

    public static int getCentralControl(Board board, int player) {
        int centralControl = 0;
        int mid = board.getGrid().length / 2;

        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                Cell cell = board.getGrid()[i][j];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == player) {
                    int distanceToCenter = Math.abs(mid - i) + Math.abs(mid - j);
                    centralControl += (mid - distanceToCenter);
                }
            }
        }
        return centralControl;
    }

    public static double getShortestPathDiff(Board board) {
        int shortestPathForAdversary = getShortestPath(board, 0);
        int shortestPathForComputer = getShortestPath(board, 1);

        return shortestPathForAdversary - shortestPathForComputer;
    }

    public static int getShortestPath(Board board, int player) {
        class Node {
            final Cell cell;
            final int cost;
            final int heuristic;

            Node(Cell cell, int cost, int heuristic) {
                this.cell = cell;
                this.cost = cost;
                this.heuristic = heuristic;
            }
        }

        Comparator<Node> comparator = Comparator.comparingInt(a -> a.cost + a.heuristic);
        PriorityQueue<Node> openSet = new PriorityQueue<>(comparator);
        Set<Cell> closedSet = new HashSet<>();

        int gridSize = board.getGrid().length;
        Cell startCell = null;
        Cell endCell = null;

        if (player == 0) {
            for (int i = 0; i < gridSize; i++) {
                if (board.getGrid()[i][0].getPlayer() != null && board.getGrid()[i][0].getPlayer().getPosition() == player) {
                    startCell = board.getGrid()[i][0];
                    break;
                }
            }
            for (int i = 0; i < gridSize; i++) {
                if (board.getGrid()[i][gridSize - 1].getPlayer() != null && board.getGrid()[i][gridSize - 1].getPlayer().getPosition() == player) {
                    endCell = board.getGrid()[i][gridSize - 1];
                    break;
                }
            }
        } else {
            for (int j = 0; j < gridSize; j++) {
                if (board.getGrid()[0][j].getPlayer() != null && board.getGrid()[0][j].getPlayer().getPosition() == player) {
                    startCell = board.getGrid()[0][j];
                    break;
                }
            }
            for (int j = 0; j < gridSize; j++) {
                if (board.getGrid()[gridSize - 1][j].getPlayer() != null && board.getGrid()[gridSize - 1][j].getPlayer().getPosition() == player) {
                    endCell = board.getGrid()[gridSize - 1][j];
                    break;
                }
            }
        }

        if (startCell == null || endCell == null) {
            return Integer.MAX_VALUE;
        }

        openSet.add(new Node(startCell, 0, heuristic(startCell, endCell)));

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            Cell currentCell = currentNode.cell;

            if (currentCell.equals(endCell)) {
                return currentNode.cost;
            }

            closedSet.add(currentCell);

            for (Direction direction : currentCell.getDirections().keySet()) {
                Cell neighbor = currentCell.getDirections().get(direction);
                if (neighbor != null && !closedSet.contains(neighbor) && neighbor.getPlayer() != null && neighbor.getPlayer().getPosition() == player) {
                    int newCost = currentNode.cost + 1;
                    int heuristic = heuristic(neighbor, endCell);
                    openSet.add(new Node(neighbor, newCost, heuristic));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static int heuristic(Cell a, Cell b) {
        return Math.abs(a.getAbscissa() - b.getAbscissa()) + Math.abs(a.getOrdinate() - b.getOrdinate());
    }

    public static int getBorderControl(Board board, int player) {
        int borderControl = 0;
        int gridSize = board.getGrid().length;

        for (int i = 0; i < gridSize; i++) {
            if (player == 0) {
                if (board.getGrid()[i][0].getPlayer() != null && board.getGrid()[i][0].getPlayer().getPosition() == player) {
                    borderControl++;
                }
                if (board.getGrid()[i][gridSize - 1].getPlayer() != null && board.getGrid()[i][gridSize - 1].getPlayer().getPosition() == player) {
                    borderControl++;
                }
            } else {
                if (board.getGrid()[0][i].getPlayer() != null && board.getGrid()[0][i].getPlayer().getPosition() == player) {
                    borderControl++;
                }
                if (board.getGrid()[gridSize - 1][i].getPlayer() != null && board.getGrid()[gridSize - 1][i].getPlayer().getPosition() == player) {
                    borderControl++;
                }
            }
        }
        return borderControl;
    }

    public static int getBlockingPotential(Board board, int player) {
        int blockingPotential = 0;
        int opponent = 1 - player;
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                Cell cell = board.getGrid()[i][j];
                if (cell.getState() == State.EMPTY) {
                    int opponentConnections = 0;
                    for (Direction direction : cell.getDirections().keySet()) {
                        Cell neighbor = cell.getDirections().get(direction);
                        if (neighbor != null && neighbor.getPlayer() != null && neighbor.getPlayer().getPosition() == opponent) {
                            opponentConnections++;
                        }
                    }
                    blockingPotential += opponentConnections;
                }
            }
        }
        return blockingPotential;
    }

    public static int getFreedomOfMovement(Board board, int player) {
        int freedomOfMovement = 0;
        for (int i = 0; i < board.getGrid().length; i++) {
            for (int j = 0; j < board.getGrid()[i].length; j++) {
                Cell cell = board.getGrid()[i][j];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == player) {
                    for (Direction direction : cell.getDirections().keySet()) {
                        Cell neighbor = cell.getDirections().get(direction);
                        if (neighbor != null && neighbor.getState() == State.EMPTY) {
                            freedomOfMovement++;
                        }
                    }
                }
            }
        }
        return freedomOfMovement;
    }
}