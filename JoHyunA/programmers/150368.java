class Solution {
    static int[] sales = {10,20,30,40};
    static int[] answer = new int[2];
    static int[] appliedSales;
    static int userCnt, emoticonCnt;

    public int[] solution(int[][] users, int[] emoticons) {
        userCnt = users.length;
        emoticonCnt = emoticons.length;
        appliedSales = new int[emoticonCnt];
        applySales(0, users, emoticons);

        return answer;
    }

    public static void applySales(int cnt, int[][] users, int[] emoticons){
        if(cnt == emoticonCnt){
            getResult(users, emoticons);
            return;
        }

        for(int i=0;i<4;i++){
            appliedSales[cnt] = sales[i];
            applySales(cnt+1, users, emoticons);
        }
    }

    public static void getResult(int[][] users, int[] emoticons){
        int subscriber = 0;
        int profit = 0;

        for(int[] user:users){
            int totalPrice = 0;

            for(int i=0;i<emoticonCnt;i++){
                if(user[0] > appliedSales[i]){
                    continue;
                }
                totalPrice += emoticons[i]/100*(100-appliedSales[i]);
            }

            if(totalPrice >= user[1]){
                subscriber++;
                continue;
            }
            profit += totalPrice;
        }

        if(subscriber>answer[0]){
            answer[0] = subscriber;
            answer[1] = profit;
            return;
        }

        if(subscriber == answer[0]){
            answer[1] = Math.max(answer[1],profit);
        }
    }
}