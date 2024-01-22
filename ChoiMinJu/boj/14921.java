import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int lp = 0;
        int rp = N-1;

        int min = Integer.MAX_VALUE;
        while (true) {
            if (lp == rp) break;
            int sum = arr[lp] + arr[rp];
            if (Math.abs(sum) <= Math.abs(min)) min = sum;
            if (sum < 0) lp++; // 합을 더 크게 만들기
            else if (sum > 0) rp--; // 합을 더 작게 만들기
            else break;
        }
        System.out.println(min);

    }
}
