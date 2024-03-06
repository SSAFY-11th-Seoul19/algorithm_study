import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Integer> numStack = new ArrayDeque<>();
    static Deque<Character> operStack = new ArrayDeque<>();
    static int N, maxVal = Integer.MIN_VALUE;
    static String exp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        exp = br.readLine();
        recur(0);

        System.out.println(maxVal);
    }

    public static void recur(int cnt) {
        if (cnt == N) {
            Deque<Integer> numStackCopy = new ArrayDeque<>(numStack);
            Deque<Character> operStackCopy = new ArrayDeque<>(operStack);

            // 다 꺼내서 계산
            int frontNum = numStack.poll();
            while (!operStack.isEmpty()) {
                int backNum = numStack.poll();
                char oper = operStack.poll();
                frontNum = calc(oper, frontNum, backNum);
            }
            maxVal = Math.max(maxVal, frontNum);

            numStack = numStackCopy;
            operStack = operStackCopy;

            return;
        }

        char c = exp.charAt(cnt);
        if (c == '+' || c == '-' || c == '*') {
            operStack.offer(c);
            recur(cnt+1);
            operStack.pollLast();
        } else {
            // 그냥 스택에 추가
            numStack.offer(c - '0');
            recur(cnt + 1);
            numStack.pollLast();

            // 계산하고 추가
            if (cnt + 3 <= N) {
                int n1 = exp.charAt(cnt) - '0';
                int n2 = exp.charAt(cnt+2) - '0';
                numStack.offer(calc(exp.charAt(cnt+1), n1, n2));
                recur(cnt + 3);
                numStack.pollLast();
            }
        }
    }

    public static int calc(char oper, int n1, int n2) {
        if (oper == '+') {
            return n1+n2;
        }
        if (oper == '-') {
            return n1-n2;
        }
        //if (oper == '*')
        return n1*n2;
    }
}
