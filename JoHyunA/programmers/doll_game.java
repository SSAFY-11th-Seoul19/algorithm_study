import java.util.*;

class Solution {
    public int N;
    public Deque<Integer> dq = new ArrayDeque<>();
    public int answer = 0;
    
    public int solution(int[][] board, int[] moves) {
        N = board.length;
        for(int moveIdx:moves){
            int topNumber = findTopNumber(board, moveIdx-1);
            if(topNumber == -1){
                continue;
            }
        
            putNumber(topNumber);   
            int dqSize = dq.size();
            for(int i=0;i<dqSize;i++){
                int num = dq.pollLast();
                dq.addFirst(num);
            }
        }
        
        return answer;
    }
    
    public int findTopNumber(int[][] board, int columnIdx){
        for(int i=0;i<N;i++){
            int number = board[i][columnIdx];
            
            if(number != 0){
                board[i][columnIdx] = 0;
                return number;
            }
        }
        return -1;
    }
    
    public void putNumber(int number){
        if(dq.isEmpty()){
            dq.add(number);
            return;
        }
        
        int originTop = dq.pollLast();
        if(originTop == number){
            answer += 2;
        }
        else{
            dq.addLast(originTop);
            dq.addLast(number);
        }
    }
}