package hex.model.game.theoric.evaluator;

import hex.model.board.Board;
import hex.model.board.cell.Cell;
import hex.model.board.cell.State;
import hex.model.game.Game;

import java.util.LinkedList;
import java.util.Queue;

public class Evaluator {

    public static double evaluate(Board board, int player) {
        int opponent = (player == 1) ? 0 : 1;

        board.refreshAllVisit();
        Game g = new Game(board);
        if (g.existLine(player)) {
            return -2000000.0;
        }

        double playerScore = 0;
        double opponentScore = 0;

        // Define weights for each criterion
        double weightMinDistance = -2.0;
        double weightControlCenter = -10.0;
        double weightLongestChain = 10.0;
        double weightVerticalLines = -100.0; // Augmenter le poids pour rechercher et éviter les lignes verticales ennemies
        double weightBlockHorizontalLines = -50.0;

        // Prevent division by zero
        double playerMinDistance = calculateMinimumDistance(board, player);
        double opponentMinDistance = calculateMinimumDistance(board, opponent);
        if (playerMinDistance != 0) {
            playerScore += weightMinDistance * (double) 1000 / playerMinDistance;
        }
        if (opponentMinDistance != 0) {
            opponentScore += weightMinDistance * (double) 1000 / opponentMinDistance;
        }

        playerScore += weightControlCenter * controlCenter(board, player);
        opponentScore += weightControlCenter * controlCenter(board, opponent);

        playerScore += weightLongestChain * longestChain(board, player);
        opponentScore += weightLongestChain * longestChain(board, opponent);

        playerScore += weightVerticalLines * evaluateVerticalLines(board, player);
        opponentScore += weightVerticalLines * evaluateVerticalLines(board, opponent);

        playerScore += weightBlockHorizontalLines * blockHorizontalLines(board, opponent);
        opponentScore += weightBlockHorizontalLines * blockHorizontalLines(board, player);

        return playerScore - opponentScore;
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

        Queue<int[]> queue = new LinkedList<>();
        if (player == 1) {
            for (int i = 0; i < size; i++) {
                Cell c = board.getGrid()[i][0];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                    queue.add(new int[]{i, 0});
                    distance[i][0] = 0;
                }
            }
        } else {
            for (int j = 0; j < size; j++) {
                Cell c = board.getGrid()[0][j];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                    queue.add(new int[]{0, j});
                    distance[0][j] = 0;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            visited[x][y] = true;

            for (int[] d : directions) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && ny >= 0 && nx < size && ny < size && !visited[nx][ny]) {
                    int newDist = distance[x][y] + 1;
                    Cell c = board.getGrid()[nx][ny];
                    if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player) {
                        newDist = distance[x][y];
                    }
                    if (newDist < distance[nx][ny]) {
                        distance[nx][ny] = newDist;
                        queue.add(new int[]{nx, ny});
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
        int size = board.getGrid().length;
        int length = 1;
        visited[x][y] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}};
        for (int[] d : directions) {
            int nx = x + d[0], ny = y + d[1];
            if (nx >= 0 && ny >= 0 && nx < size && ny < size) {
                Cell c = board.getGrid()[nx][ny];
                if (c.getState() == State.PLAYER && c.getPlayer().getPosition() == player && !visited[nx][ny]) {
                    length += dfs(board, player, visited, nx, ny);
                }
            }
        }

        return length;
    }

    private static int evaluateVerticalLines(Board board, int player) {
        int size = board.getGrid().length;
        int verticalScore = 0;

        for (int col = 0; col < size; col++) {
            int consecutiveCount = 0;
            for (int row = 0; row < size; row++) {
                Cell cell = board.getGrid()[row][col];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == player) {
                    consecutiveCount++;
                } else {
                    verticalScore += (int) Math.pow(consecutiveCount, 2);  // Plus de points pour des chaînes plus longues
                    consecutiveCount = 0;
                }
            }
            verticalScore += (int) Math.pow(consecutiveCount, 2);  // Dernière colonne évaluée
        }

        return verticalScore;
    }

    private static int blockHorizontalLines(Board board, int opponent) {
        int size = board.getGrid().length;
        int blockScore = 0;

        for (int row = 0; row < size; row++) {
            int consecutiveCount = 0;
            for (int col = 0; col < size; col++) {
                Cell cell = board.getGrid()[row][col];
                if (cell.getState() == State.PLAYER && cell.getPlayer().getPosition() == opponent) {
                    consecutiveCount++;
                } else {
                    blockScore += (int) Math.pow(consecutiveCount, 2);  // Plus de points pour des chaînes plus longues
                    consecutiveCount = 0;
                }
            }
            blockScore += (int) Math.pow(consecutiveCount, 2);  // Dernière ligne évaluée
        }

        return blockScore;
    }
}