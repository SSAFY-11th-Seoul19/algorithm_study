import java.util.*;

public class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int num = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            switch (c) {
                case '*':
                    int num1 = stack.peek();
                    stack.pop();
                    if (!stack.empty()) {
                        int num2 = stack.peek();
                        stack.pop();
                        stack.push(num2 * 2);
                    }
                    stack.push(num1 * 2);
                    break;

                case '#':
                    int num3 = stack.peek();
                    stack.pop();
                    stack.push(num3 * -1);
                    break;

                case 'S':
                    stack.push(num);
                    break;

                case 'D':
                    stack.push(num * num);
                    break;

                case 'T':
                    stack.push(num * num * num);
                    break;

                default:
                    if (c == '1' && dartResult.charAt(i+1) == '0') {
                        num = 10;
                        i++;
                    }
                    else {
                        num = c - '0';
                    }
            }
        }

        while (!stack.empty()) {
            answer += stack.peek();
            stack.pop();
        }

        return answer;
    }
}
