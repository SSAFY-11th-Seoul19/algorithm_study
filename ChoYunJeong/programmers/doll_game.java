//k=0에서 moves의 길이와 같아질 때까지 while문을 돌며,
//i를 0부터 n까지 for문을 돌며 board[i][moves[k]-1]번째 값이 0인지 확인
//0이라면 넘기고, 0이 아니라면 0으로 바꿔주고, 인형 스택에 집어넣는다.
//스택에 넣기 전 이전값과 검사하여 같다면 pop, answer +=2 or push

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int k = 0;
        Stack<Integer> dolls = new Stack<>();
        for(k++ < moves.length){
            for(int j =0; j<board.length; j++) {
                int pick = board[j][moves[i]-1];
                if(pick != 0) {
                    board[j][moves[i]-1] = 0;
                    if(!dolls.empty() && dolls.peek() == pick) {
                        dolls.pop();
                        answer += 2;
                    } else {
                        dolls.push(pick);
                    }
                    break;
                } else {
                    continue;
                }
            }
        }
        return answer;
    }
}
