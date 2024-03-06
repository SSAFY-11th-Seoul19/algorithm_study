import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int lc = 0, rc = 1, minLen = N+1;
        while (lc < rc && rc <= N) {
            if (arr[rc] - arr[lc] < S) {
                rc++;
                continue;
            }

            minLen = Math.min(minLen, rc - lc++);
        }

        System.out.println(minLen <= N ? minLen : 0);
    }
}

