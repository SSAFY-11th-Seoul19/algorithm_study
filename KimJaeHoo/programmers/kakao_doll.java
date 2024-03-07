import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N =  board.length;
        
        int tops[] = new int[N];
        for(int i = 0 ; i < N ; i ++){
            tops[i] = N;
            for(int j = 0 ; j < N ; j ++){
                if(board[j][i] > 0){
                    tops[i] =  j;
                    j = N;
                }
            }
        }
        
        Stack<Integer> dolls = new Stack<>();
        for(int move : moves){
            move -= 1;
            if(tops[move] >= N) continue;
            
            int doll = board[tops[move]][move];
            
            board[tops[move]++][move] = 0;
            if(!dolls.isEmpty() && (doll == dolls.peek())){
                dolls.pop();
                answer += 2;
            }
            else 
                dolls.push(doll);    
        }
        
        
        
        return answer;
    }
}
