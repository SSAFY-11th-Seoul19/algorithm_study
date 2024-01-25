import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        dfs(N, 0);
        System.out.println(min);
    }

    public static void dfs(int n, int cnt) {
        if (cnt > min) return;
        if (n == K && min > cnt) {
            min = cnt;
        }
        dfs(n+1, cnt+1);
        dfs(n-1, cnt+1);
        dfs(n*2, cnt+1);
    }
}
