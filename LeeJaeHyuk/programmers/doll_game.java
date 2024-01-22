import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] board, int[] moves) {

        int N = board.length;
        Stack<Integer>[] stacks = new Stack[N];
        for(int i=0; i<N; i++) {
            stacks[i] = new Stack<>();
        }

        for(int row=N-1; row>=0; row--) {
            for(int col=0; col<N; col++) {
                if(board[row][col] > 0) {
                    stacks[col].add(board[row][col]);
                }
            }
        }

        int count = 0;
        Stack<Integer> answer = new Stack<>();
        for(int i=0; i<moves.length; i++) {
            int move = moves[i] - 1;

            if(stacks[move].isEmpty()) continue;

            int getOne = stacks[move].pop();

            if(!answer.isEmpty() && answer.peek() == getOne) {
                answer.pop();
                count += 2;
            }
            else {
                answer.add(getOne);
            }
        }


        return count;
    }
}