//https://leetcode.com/problems/lexicographically-smallest-generated-string/?envType=daily-question&envId=2026-03-31
class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        char[] arr = new char[n + m - 1];

        // Step 1: Apply all 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (arr[i + j] == '\0') {
                        arr[i + j] = str2.charAt(j);
                    } else if (arr[i + j] != str2.charAt(j)) {
                        return "";
                    }
                }
            }
        }

        // Step 2: Fill remaining positions greedily
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '\0') {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;

                    if (isValidAt(arr, str1, str2, i)) {
                        break;
                    }
                }
            }
        }

        // Step 3: Final global validation (safety check)
        String result = new String(arr);

        for (int i = 0; i < n; i++) {
            boolean match = true;

            for (int j = 0; j < m; j++) {
                if (result.charAt(i + j) != str2.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }

        return result;
    }

    // 🔑 Local validation to avoid forming forbidden 'F' matches
    private boolean isValidAt(char[] arr, String str1, String str2, int pos) {
        int n = str1.length();
        int m = str2.length();

        // Only check windows affected by this position
        for (int i = Math.max(0, pos - m + 1); i <= Math.min(pos, n - 1); i++) {
            boolean match = true;

            for (int j = 0; j < m; j++) {
                if (arr[i + j] == '\0' || arr[i + j] != str2.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (str1.charAt(i) == 'F' && match) return false;
        }

        return true;
    }
}