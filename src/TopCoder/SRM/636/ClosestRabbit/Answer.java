package topcoder.srm636.ClosestRabbit;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    public static void main(String[] args) throws Exception {
        int test = -1;

        // 0
        String[] grid = new String[] { ".#.#." };
        int r = 2;
        double expected = 1.0;
        test = test(test, grid, r, expected);

        // 1
        grid = new String[] { "..###.", ".###.#" };
        r = 4;
        expected = 1.6;
        test = test(test, grid, r, expected);

        // 2
        grid = new String[] { "..###.", ".###.#" };
        r = 5;
        expected = 2.0;
        test = test(test, grid, r, expected);

        // 3
        grid = new String[] { ".....", "#...." };

        r = 4;
        expected = 1.253968253968254;
        test = test(test, grid, r, expected);

        // 4
        grid = new String[] { ".#####.#####..#....#", // 8
                "#......#....#.##..##",// 13
                ".####..#####..#.##.#",// 7
                ".....#.#...#..#....#",// 15
                "#####..#....#.#....#" };// 11

        r = 19;
        expected = 5.77311527122319;
        test = test(test, grid, r, expected);
    }

    private static int test(int test, String[] grid, int rabbitCount,
            double expected) throws Exception {
        System.out.println("Test" + (++test));
        double avg = getExpected(grid, rabbitCount);
        for (String line : grid) {
            System.out.println(line);
        }
        System.out.println(rabbitCount);
        System.out.println("Expected:" + expected);
        System.out.println("Actual:" + avg);
        if (expected == avg) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static double getExpected(String[] board, int r) {
        int h = board.length, w = board[0].length();
        // find the empty cells, keep their indexes in the correct order.
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i].charAt(j) == '.') {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        int n = x.size();
        int[][] dist = new int[n][n]; // the square of the pair-wise distances
        // It is b
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dx = x.get(i) - x.get(j);
                int dy = y.get(i) - y.get(j);
                dist[i][j] = dx * dx + dy * dy;
            }
        }
        // Pascal's triangle to calculate the binomial coeficient.
        double[][] C = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
            }
        }
        double res = 0;
        // For each pair of cells (i,j):
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int b = 0;
                // Count bad cells (shouldn't be included if we want i<->j to be
                // a double edge.:
                for (int k = 0; k < n; k++) {
                    if ((k == i) || (k == j)) {
                        continue;
                    }
                    boolean ok = true;
                    if ((dist[i][k] < dist[i][j])
                            || (dist[i][k] == dist[i][j] && k < j)) {
                        ok = false;
                    }
                    if ((dist[j][k] < dist[j][i])
                            || (dist[j][k] == dist[j][i] && k < i)) {
                        ok = false;
                    }
                    if (!ok) {
                        b++;
                    }
                }
                res += C[n - 2 - b][r - 2] / C[n][r];
            }
        }

        return res;
    }

}
