class Solution {

    static int[] discount = {10, 20, 30, 40};    // 10, 20, 30, 40
    static int[] selected;
    static int n, m, maxPrice, maxUser;
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;

        selected = new int[m];

        dfs(users, emoticons, 0, 0);

        int[] answer = {maxUser, maxPrice};
        return answer;
    }

    public void dfs(int[][] users, int[] emoticons, int cnt, int idx) {
        if (cnt == m) {
            int moneySum = 0;
            int plusUserSum = 0;

            for (int[] userInfo : users) {  // 유저별 반복
                int moneyPerUser = 0;
                int rateThreshold = userInfo[0];
                int moneyThreshold = userInfo[1];

                for (int i = 0; i < m; i++) {   // 이모티콘 반복
                    if (rateThreshold <= selected[i]) {
                        int discountPrice = getDiscountPrice(emoticons[i], selected[i]);
                        moneyPerUser += discountPrice;
                    }
                }

                if (moneyPerUser >= moneyThreshold) {
                    plusUserSum++;
                    continue;
                }

                moneySum+=moneyPerUser;
            }

            if (maxUser < plusUserSum) {
                maxUser = plusUserSum;
                maxPrice = moneySum;
                return;
            }
            if (maxUser == plusUserSum) {
                maxPrice = Math.max(maxPrice, moneySum);
            }
            return;
        }

        for (int i = idx; i < discount.length; i++) {

            selected[cnt] = discount[i];
            dfs(users, emoticons, cnt+1, idx);
        }
    }

    public int getDiscountPrice(int emojiPrice, int discount) {
        return (int) (emojiPrice * ((100-discount)*0.01));
    }
}