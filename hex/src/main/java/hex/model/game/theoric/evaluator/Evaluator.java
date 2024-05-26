package hex.model.game.theoric.evaluator;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.Direction;
import hex.model.board.cell.State;
import hex.model.game.Game;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Evaluator {

    private static final double weightMinDistance = -2.0;
    private static final double weightControlCenter = -10.0;
    private static final double weightLongestChain = 10.0;
    private static final double weightVerticalLines = -100.0;
    private static final double weightBlockHorizontalLines = -200.0;

    public static double evaluate(Board board, int player) {
        int opponent = (player == 1) ? 0 : 1;

        board.refreshAllVisit();
        Game g = new Game(board);
        if (g.existLine(player)) {
            return -2000000.0;
        }

        return calculateGainForPlayer(board, player) - calculateGainForPlayer(board, opponent);
    }

    private static double calculateGainForPlayer(Board board, int player) {
        double playerScore = 0;

        double playerMinDistance = calculateMinimumDistance(board, player);
        if (playerMinDistance != 0) {
            playerScore += Evaluator.weightMinDistance * (double) 1000 / playerMinDistance;
        }

        playerScore += Evaluator.weightControlCenter * controlCenter(board, player);
        playerScore += Evaluator.weightLongestChain * longestChain(board, player);
        playerScore += Evaluator.weightVerticalLines * evaluateVerticalLines(board, player);
        playerScore += Evaluator.weightBlockHorizontalLines * blockHorizontalLines(board, player != 0 ? 1 : 0);
        return playerScore;
    }

    private static int calculateMinimumDistance(Board board, int player) {
        int size = board.getGrid().length;
        int[][] distance = new int[size][size];
        boolean[][] visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distance[i][j] = 10000;
                visited[i][j] = false;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        if (player == 1) {
            for (int i = 0; i < size; i++) {
                Cell c = board.getGrid()[i][0];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                    queue.add(new Point(i, 0));
                    distance[i][0] = 0;
                }
            }
        } else {
            for (int j = 0; j < size; j++) {
                Cell c = board.getGrid()[0][j];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                    queue.add(new Point(0, j));
                    distance[0][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            Point coordinateCell = queue.poll();
            int x = coordinateCell.x, y = coordinateCell.y;
            visited[x][y] = true;
            for (Direction d : Direction.values()) {
                Point p = d.getNewCoordinates(x, y);
                int nx = p.x, ny = p.y;
                if (nx >= 0 && ny >= 0 && nx < size && ny < size && !visited[nx][ny]) {
                    int newDist = distance[x][y] + 1;
                    Cell c = board.getGrid()[nx][ny];
                    if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                        newDist = distance[x][y];
                    }
                    if (newDist < distance[nx][ny]) {
                        distance[nx][ny] = newDist;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }
        int minDistance = 10000;
        if (player == 1) {
            for (int i = 0; i < size; i++) {
                if (distance[i][size - 1] < minDistance) {
                    minDistance = distance[i][size - 1];
                }
            }
        } else {
            for (int j = 0; j < size; j++) {
                if (distance[size - 1][j] < minDistance) {
                    minDistance = distance[size - 1][j];
                }
            }
        }
        return minDistance == 10000 ? size * size : minDistance;
    }

    private static int controlCenter(Board board, int player) {
        int size = board.getGrid().length;
        int centerValue = 0;
        int centerStart = size / 3;
        int centerEnd = size - centerStart;
        for (int i = centerStart; i < centerEnd; i++) {
            for (int j = centerStart; j < centerEnd; j++) {
                Cell c = board.getGrid()[i][j];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                    centerValue += 1;
                }
            }
        }
        return centerValue;
    }

    private static int longestChain(Board board, int player) {
        int size = board.getGrid().length;
        boolean[][] visited = new boolean[size][size];
        int maxChainLength = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell c = board.getGrid()[i][j];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player && !visited[i][j]) {
                    int chainLength = dfs(board, player, visited, i, j);
                    if (chainLength > maxChainLength) {
                        maxChainLength = chainLength;
                    }
                }
            }
        }

        return maxChainLength;
    }

    private static int dfs(Board board, int player, boolean[][] visited, int x, int y) {
        int length = 1;
        visited[x][y] = true;
        for (Direction d : Direction.values()) {
            Point p = d.getNewCoordinates(x, y);
            if (board.coordinateIsValid(p.x, p.y)) {
                Cell c = board.getGrid()[p.x][p.y];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player && !visited[p.x][p.y]) {
                    length += dfs(board, player, visited, p.x, p.y);
                }
            }
        }
        return length;
    }

    private static int evaluateVerticalLines(Board board, int player) {
        return Evaluator.getScoreAboutLine(board, player, false);
    }

    private static int blockHorizontalLines(Board board, int opponent) {
        return Evaluator.getScoreAboutLine(board, opponent, true);
    }

    private static int getScoreAboutLine(Board board, int player, boolean displayRows) {
        int size = board.getGrid().length;
        int score = 0;
        for (int k = 0; k < size; k++) {
            int count = 0;
            for (int l = 0; l < size; l++) {
                Cell cell = displayRows ? board.getGrid()[k][l] : board.getGrid()[l][k];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == player) {
                    count++;
                } else {
                    score += (int) Math.pow(count, 2);
                    count = 0;
                }
            }
            score += (int) Math.pow(count, 2);
        }
        return score;
    }
}