import java.util.*;
import java.io.*;

public class 12865 {
    static class Stuff implements Comparable<Stuff> {
        int weight;
        int value;

        Stuff(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Stuff s) {
            return this.weight - s.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];
        Stuff[] stuffs = new Stuff[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            stuffs[i] = new Stuff(weight, value);
        }

        for(int i=1; i<=N; i++) {
            Stuff stuff = stuffs[i];
            for(int j=1; j<=K; j++) {
                if(stuff.weight <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stuff.weight] + stuff.value);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}

