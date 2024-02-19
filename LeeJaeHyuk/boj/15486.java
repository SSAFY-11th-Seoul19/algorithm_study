import java.io.*;
import java.util.*;

class 15486 {

    static class Consulting {
        int term;
        int price;

        Consulting(int term, int price) {
            this.term = term;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        Consulting[] consultingList = new Consulting[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int term = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            consultingList[i] = new Consulting(term, price);
        }

        for(int i=N-1; i>=0; i--) {
            Consulting c = consultingList[i];

            if(i + c.term - 1 > N-1) {
                dp[i] = dp[i+1];
            }
            else { // i + c.term <= N
                dp[i] = Math.max(dp[i+1], c.price + dp[i + c.term]);
            }
        }

        System.out.println(dp[0]);
    }
}