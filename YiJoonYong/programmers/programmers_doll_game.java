import java.util.*;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for(int col : moves) {
            col--;
            for(int row=0; row<board.length; row++) {
                if (board[row][col] == 0)
                    continue;

                int target = board[row][col];
                board[row][col] = 0;

                if (stack.isEmpty() || (stack.peek() != target)){
                    stack.push(target);
                    break;
                }

                stack.pop();
                answer+=2;
                break;
            }
        }
        return answer;
    }
}
