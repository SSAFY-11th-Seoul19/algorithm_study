import java.util.*;
import java.io.*;

public class 16637 {

    static int N;
    static char[] inputs;
    static int[] nums;
    static char[] signs;
    static boolean[] check;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inputs = br.readLine().toCharArray();

        nums = new int[N/2+1];
        signs = new char[N/2];

        int index = 0;
        for(int i=0; i<inputs.length; i++) {
            nums[index] = inputs[i] - '0';
            i++;
            if(i >= inputs.length) break;
            signs[index] = inputs[i];
            index+=1;
        }

        check = new boolean[signs.length];

        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int depth) {
        if(depth >= signs.length) {
            calculate();
            return;
        }

        // *** Example *** //
        //   1 + 2 - 3 * 6 - 5 + 9
        // ( 1 + 2 ) - 3 * 6 - 5 + 9
        // 부호를 연속으로 묶을 수 없으므로, 
        // 부호를 하나 괄호로 묶어서 우선순위를 높였다면,
        // 하나 건너뛰고 그 다음 부호부터 다시 괄호 묶을지 말지~~

        // 체크 -> 해당 인덱스의 부호는 괄호가 묶인 부호
        check[depth] = true;
        dfs(depth+2);
        check[depth] = false;
        // 체크안함
        dfs(depth+1);
    }

    public static void calculate() {
        int[] copy = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            copy[i] = nums[i];
        }

        for(int i=0; i<check.length; i++) {
            if(check[i]) { // 해당 인덱스의 부호는 괄호가 묶인 부호 -> 먼저 계산
                int num1 = copy[i];
                int num2 = copy[i+1];

                switch(signs[i]) {
                    case '+':
                        copy[i+1] = 0;
                        copy[i] = num1 + num2;
                        break;
                    case '-':
                        copy[i+1] = 0;
                        copy[i] = num1 - num2;
                        break;
                    case '*':
                        copy[i+1] = 1;
                        copy[i] = num1 * num2;
                        break;
                }
            }
        }

        // 앞에서부터 순차적으로 계산
        int start = copy[0];
        for(int i=1; i<copy.length; i++) {
            char c = signs[i-1];

            switch (c) {
                case '+':
                    start += copy[i];
                    break;
                case '-':
                    start -= copy[i];
                    break;
                case '*':
                    start *= copy[i];
                    break;
            }
        }
        answer = Math.max(answer, start);
    }
}

