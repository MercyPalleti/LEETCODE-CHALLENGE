//https://leetcode.com/problems/find-the-string-with-lcp/description/?envType=daily-question&envId=2026-03-28

import java.util.*;
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Basic validation
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        // Step 2: DSU union
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union(i, j, parent);
                }
            }
        }

        // Step 3: Assign characters
        char[] res = new char[n];
        Arrays.fill(res, '#');

        char ch = 'a';

        Map<Integer, Character> rootChar = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i, parent);

            if (!rootChar.containsKey(root)) {
                if (ch > 'z') return ""; // more than 26 groups
                rootChar.put(root, ch++);
            }

            res[i] = rootChar.get(root);
        }

        String word = new String(res);

        // Step 4: Validate LCP matrix
        if (!validate(word, lcp)) return "";

        return word;
    }

    // DSU find with path compression
    private int find(int x, int[] parent) {
        if (parent[x] == -1) return x;
        return parent[x] = find(parent[x], parent);
    }

    // DSU union
    private void union(int x, int y, int[] parent) {
        int px = find(x, parent);
        int py = find(y, parent);
        if (px != py) parent[py] = px;
    }

    // Validate LCP matrix
    private boolean validate(String word, int[][] lcp) {
        int n = word.length();

        int[][] dp = new int[n + 1][n + 1];

        // bottom-up LCP computation
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) return false;
            }
        }

        return true;
    }
}