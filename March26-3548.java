//https://leetcode.com/problems/equal-sum-grid-partition-ii/?envType=daily-question&envId=2026-03-26

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid) {
            for (int v : row) total += v;
        }

        // Try horizontal cuts
        if (checkHorizontal(grid, m, n, total)) return true;

        // Try vertical cuts
        return checkVertical(grid, m, n, total);
    }

    private boolean checkHorizontal(int[][] grid, int m, int n, long total) {
        long sum = 0;

        // frequency of remaining (bottom part)
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                freq.put(grid[i][j], freq.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                sum += val;

                freq.put(val, freq.get(val) - 1);
                if (freq.get(val) == 0) freq.remove(val);
            }

            long other = total - sum;
            if (sum == other) return true;

            long diff = Math.abs(sum - other);
            if (diff > 1e5) continue;

            if (sum > other) {
                if (canRemove(grid, 0, i, 0, n - 1, diff)) return true;
            } else {
                if (canRemove(grid, i + 1, m - 1, 0, n - 1, diff)) return true;
            }
        }
        return false;
    }

    private boolean checkVertical(int[][] grid, int m, int n, long total) {
        long sum = 0;

        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>();
        for (int[] row : grid) {
            for (int v : row) {
                freq.put(v, freq.getOrDefault(v, 0) + 1);
            }
        }

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int val = grid[i][j];
                sum += val;

                freq.put(val, freq.get(val) - 1);
                if (freq.get(val) == 0) freq.remove(val);
            }

            long other = total - sum;
            if (sum == other) return true;

            long diff = Math.abs(sum - other);
            if (diff > 1e5) continue;

            if (sum > other) {
                if (canRemove(grid, 0, m - 1, 0, j, diff)) return true;
            } else {
                if (canRemove(grid, 0, m - 1, j + 1, n - 1, diff)) return true;
            }
        }
        return false;
    }

    // Check if we can remove ONE cell = diff while keeping connectivity
    private boolean canRemove(int[][] grid,
                              int r1, int r2,
                              int c1, int c2,
                              long diff) {

        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;

        // 2D block → always safe
        if (rows > 1 && cols > 1) {
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    if (grid[i][j] == diff) return true;
                }
            }
            return false;
        }

        // single row → only edges
        if (rows == 1) {
            return grid[r1][c1] == diff || grid[r1][c2] == diff;
        }

        // single column → only edges
        if (cols == 1) {
            return grid[r1][c1] == diff || grid[r2][c1] == diff;
        }

        return false;
    }
}