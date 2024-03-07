import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Consulting[] consultings;
    static int[] dp;

    static class Consulting {
        int endDate;
        int price;

        public Consulting(int endDate, int price){
            this.endDate = endDate;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        consultings = new Consulting[N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int period = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            consultings[i] = new Consulting(i+period,price);
        }

        dp = new int[N+2];
        int max = 0;
        for(int i=1;i<=N;i++){
            Consulting consulting = consultings[i];
            max = Math.max(max,dp[i]);

            if(consulting.endDate > N+1){
                continue;
            }

            dp[consulting.endDate] = Math.max(dp[consulting.endDate], max+consulting.price);
        }

        for(int i=1;i<N+2;i++){
            max = Math.max(max,dp[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }
}
