import java.util.Stack;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        int cnt = 0;
        Stack<Integer> stk = new Stack<>();
        for (int e : moves) {
            for (int i=0; i<N; i++) {
                if (board[i][e-1] != 0) {
                    stk.push(board[i][e-1]);
                    if (stk.size() >= 2) {
                        int topElement = stk.pop();
                        if (topElement == stk.peek()) {
                            stk.pop();
                            cnt += 2;
                        } else {
                            stk.push(topElement); // 뽑은거 다시 넣기
                        }
                    }
                    board[i][e-1] = 0;
                    break;
                }
            }
        }      
        return cnt;
    }
}
