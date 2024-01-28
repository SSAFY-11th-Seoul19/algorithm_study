import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int maxIdx = board.length - 1;

        Stack<Integer> s = new Stack<>();

        for (int m : moves) {
            int move = m - 1;
            int depth = 0;
            
            if (board[maxIdx][move] == 0) continue;
            while (board[depth][move] == 0) depth++;
            
            int doll = board[depth][move];
            board[depth][move] = 0;
            
            if (!s.empty() && s.peek() == doll) {
                s.pop();
                answer += 2;
            }
            else {
                s.push(doll);
            }
        }

        return answer;
    }
}
