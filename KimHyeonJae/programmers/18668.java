import java.util.*;

class Solution {
    private int MAX = 30000;
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int maxAlp = 0, maxCop = 0;
        for (int[] problem: problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }
        alp = Math.min(maxAlp, alp); 
        cop = Math.min(maxCop, cop);
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i < maxAlp + 1; i++) {
            Arrays.fill(dp[i], MAX);
        }
        Arrays.sort(problems, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        dp[alp][cop] = 0;
        
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                int oneAlp = Math.min(a + 1, maxAlp);
                dp[oneAlp][c] = Math.min(dp[oneAlp][c], dp[a][c] + 1);
                int oneCop = Math.min(c + 1, maxCop);
                dp[a][oneCop] = Math.min(dp[a][oneCop], dp[a][c] + 1);
                for (int[] problem: problems) {
                    if (a >= problem[0] && c >= problem[1]) {
                        int totalAlp = Math.min(a + problem[2], maxAlp);
                        int totalCop = Math.min(c + problem[3], maxCop);
                        dp[totalAlp][totalCop] = Math.min(dp[totalAlp][totalCop], dp[a][c] + problem[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}