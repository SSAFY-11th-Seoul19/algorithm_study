import java.util.*;


class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer> stack = new Stack<>();
        for (int x: moves) {
            int mx = x - 1;
            for (int height = 0; height < n; height++) {
                if (board[height][mx] == 0) {
                    continue;
                }
                if (!stack.isEmpty() && stack.peek() == board[height][mx]) {
                    answer += 2;
                    stack.pop();
                    board[height][mx] = 0;
                    break;
                }
                stack.push(board[height][mx]);
                board[height][mx] = 0;    
                break;
            }
        }
        return answer;
    }
}