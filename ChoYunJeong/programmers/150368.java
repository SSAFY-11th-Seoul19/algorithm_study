// 모든 조합 확인
// 총 판매액과 서비스 가입자를 각각 변수로 두어 반복문 내에서 비교 => max 값 갱신
// max값을 갱신했을 때의 i,j 값(각 이모티콘의 할인률)

static int[] sale = {10, 20, 30, 40};
static int maxMoney;
static int maxUsers;

class Solution { 
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        int size = users.length;
        userInfo = new int[size][2];
       
        
        
        
        return answer;
    }
    
    static void dfs(int count) { // 0 start
        int[][] userInfo;
        
        if(count == emoticons.length) { // 이모티콘을 모두 체크
            for(int user=0; user<users.length; user++) {
                if(userInfo[user][0] >= users[user][1]) { // 총 지출이 기준을 넘으면
                    userInfo[user][0] = 0; // 다 팔아버려
                    userInfo[user][1] = 1; // 결제해버려
                }
            }
            for(int user=0; user<users.length; user++) { //멤버쉽 가입자 체크
                if(userInfo[user][1] == 1) { // 결제해버린 놈
                    maxUsers++; // 증가하든지..
                }
            }
            return;
        }
        
        for(int i=0; i<4; i++) {
            for(int user=0; user<users.length; user++) { // 모든 유저 검사
                if (users[num][0] <= sale[i]) { // 세일에 현혹되면
                    userInfo[num][0] += emoticons[count] / sale[i]; // 사버리기
                }
            }
        }
        dfs(count+1);
    }
}
