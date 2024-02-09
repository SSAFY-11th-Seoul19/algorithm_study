import java.util.*;

class Solution {
    // 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
    // 2. 이모티콘 판매액을 최대한 늘리는 것
    
    // n 명의 사용자 m 개의 이모티콘 판매 
    // 할인율은 10% 20% 30% 40%
    
    // 자신의 기준에 따라 일정 비율 이상 이모티콘 모두구매
    // 기준 총 구매 비용 합 넘어가면 이모티콘 플러스 서비스 가입 
    private int[] discounts = new int[]{10, 20, 30, 40};
    private List<int[]> products = new ArrayList<>();
    
    // n 명 구매 기준 users
    // 이모티콘 m 개의 정가 
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {0, 0};
        products(0, new int[emoticons.length]);
        for (int[] product: products) {
            // product = [할인율] * users.length
            int totalE = 0;
            int totalAmount = 0;
            // users [최소할인율, 플러스 가입 기준 가격]
            for (int[] user: users) {
                // 전체 구매 금액
                int userAmount = 0;
                for (int rateIdx = 0; rateIdx < product.length; rateIdx++) {
                    int rate = product[rateIdx];
                    if (user[0] <= rate) {
                        userAmount += (emoticons[rateIdx] * (100 - rate)) / 100;
                    }
                }
                if (user[1] <= userAmount) {
                    totalE += 1; 
                    continue;
                }
                totalAmount += userAmount;
            }
            if (totalE > answer[0]) {
                answer[0] = totalE;
                answer[1] = totalAmount;
                continue;
            }
            if (totalE == answer[0] && totalAmount > answer[1]) {
                answer[0] = totalE;
                answer[1] = totalAmount;
            }
        }
        return answer;
    }
    
    
    // 할인율의 순열을 구하는 함수
    private void products(int cur, int[] result) {
        if (cur == result.length) {
            products.add(Arrays.copyOf(result, result.length));
            return;
        }
        for (int i = 0; i < discounts.length; i++) {
            result[cur] = discounts[i];
            products(cur + 1, result);
        }
    }
}