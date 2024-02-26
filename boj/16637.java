import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static String line;
    static int[] numbers;
    static char[] ops;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        numbers = new int[n / 2 + 1];
        ops = new char[n / 2];
        line = br.readLine();

        int numIdx = 0;
        int opIdx = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numbers[numIdx++] = line.charAt(i) - '0';
                continue;
            }
            ops[opIdx++] = line.charAt(i);
        }
        dfs(1, 0, numbers[0]);

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calc(int n1, int n2, char op) {
        if (op == '+') {
            return n1 + n2;
        }
        if (op == '*') {
            return n1 * n2;
        }
        return n1 - n2;
    }

    private static void dfs(int numIdx, int opIdx, int result) {
        if (numIdx >= numbers.length) {
            answer = Math.max(answer, result);
            return;
        }
        // 괄호 없는 경우
        dfs(numIdx + 1, opIdx + 1, calc(result, numbers[numIdx], ops[opIdx]));
        // 현재 거에 괄호 있는 경우
        if (numIdx + 1 < numbers.length) {
            dfs(numIdx + 2, opIdx + 2,
                    calc(result, calc(numbers[numIdx], numbers[numIdx + 1], ops[opIdx + 1]), ops[opIdx]));
        }
    }
}
