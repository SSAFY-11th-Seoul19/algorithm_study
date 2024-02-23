package ChoiMinJu.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

// boj.16637.괄호추가하기
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> nums;
    static ArrayList<Character> ops;
    static int max;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        nums = new ArrayList<>();
        ops = new ArrayList<>();
        for (char c : arr) {
            if (c == '+' || c == '-' || c == '*') ops.add(c);
            else nums.add(c - '0');
        }

        max = Integer.MIN_VALUE;
        dfs(nums.get(0), 0);
        System.out.println(max);
    }

    static void dfs(int result, int idx) { // idx 번째 연산(ops)을 선택하거나 vs 선택하지 않거나
        if (idx == ops.size()) {
            max = Math.max(max, result);
            return;
        }

        // idx 번째 연산을 선택하지 않거나
        dfs(calc(ops.get(idx), result, nums.get(idx+1)), idx+1); // 이 경우는 바로 다음 연산자(idx+1) 고려 가능

        // idx 번째 연산을 선택하거나 -> 이 경우는 바로 다음 연산자를 고려할 수 없다 -> 다음 계산 후 다다음 계산
        if (idx+2 > ops.size()) return; // 다음을 계산할 수 없으면 선택 불가
        int res1 = calc(ops.get(idx+1), nums.get(idx+1), nums.get(idx+2)); // 다음(idx+1) 계산
        dfs(calc(ops.get(idx), result, res1),idx+2); // 다다음(idx+2) 계산
    }

    // 수식을 계산하는 함수
    static int calc (char op, int a, int b){
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> -1;
        };
    }
}

