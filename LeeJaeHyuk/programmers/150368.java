import java.util.*;

class User {
    int percent; // 해당 비율 이상이면 이모티콘을 구매한다
    int price; // 이 가격 이상 돈을쓰게된다면 -> 이모티콘플러스에 가입한다.

    User(int percent, int price) {
        this.percent = percent;
        this.price = price;
    }
}

class Result implements Comparable<Result> {
    int registerNum;
    int sale;

    Result(int registerNum, int sale) {
        this.registerNum = registerNum;
        this.sale = sale;
    }

    @Override
    public int compareTo(Result r) {
        if(this.registerNum == r.registerNum) {
            return r.sale - this.sale;
        }
        return r.registerNum - this.registerNum;
    }
}

class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        // 이모티콘 할인률은 10, 20, 30, 40

        // User는 100명, 이모티콘은 7개
        // 4 4 4 4 4 4 4 -> 이모티콘 가격 정책은 4의 7승 = 2의 14승 = 1024 * 16 = 16000개

        int[] pricePercent = new int[emoticons.length];
        User[] userList = new User[users.length];
        for(int i=0; i<users.length; i++) {
            userList[i] = new User(users[i][0], users[i][1]);
        }

        PriorityQueue<Result> pq = new PriorityQueue<>();

        dfs(0, userList, pricePercent, emoticons, pq);

        Result r = pq.poll();
        answer[0] = r.registerNum;
        answer[1] = r.sale;
        return answer;
    }

    public static void dfs(int index, User[] userList, int[] pricePercent, int[] emoticons, PriorityQueue<Result> pq) {
        if(index == pricePercent.length) {
            // 모든 유저들 대상으로 이모티콘 구매 여부 파악
            boolean[] userEmoticonPlus = new boolean[userList.length];
            int[] userPurchaseResult = new int[userList.length];

            for(int i=0; i<userList.length; i++) {
                User user = userList[i];
                for(int j=0; j<pricePercent.length; j++) {
                    if(user.percent <= pricePercent[j]) {
                        userPurchaseResult[i] += ((100-pricePercent[j]) * emoticons[j] / 100);
                    }
                }

                if(user.price <= userPurchaseResult[i]) {
                    userEmoticonPlus[i] = true;
                }
            }

            Result r = new Result(0,0);
            for(int i=0; i<userList.length; i++) {
                if(userEmoticonPlus[i]) r.registerNum += 1;
                else r.sale += userPurchaseResult[i];
            }

            pq.add(r);

            return;
        }

        pricePercent[index] = 10;
        dfs(index+1, userList, pricePercent, emoticons, pq);
        pricePercent[index] = 20;
        dfs(index+1, userList, pricePercent, emoticons, pq);
        pricePercent[index] = 30;
        dfs(index+1, userList, pricePercent, emoticons, pq);
        pricePercent[index] = 40;
        dfs(index+1, userList, pricePercent, emoticons, pq);

    }
}