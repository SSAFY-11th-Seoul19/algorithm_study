import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static int[] nums;
    static int ans;

    public static void main(String[] argrs) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int lp = 0;
        int rp = 0;
        int sum = nums[0];
        ans = Integer.MAX_VALUE;
        while(lp<N ){
            if(sum<S){
                if(++rp == N){
                    break;
                }
                sum += nums[rp];
                continue;
            }
            ans = Math.min(ans, rp-lp+1);
            if(lp == rp){
                sum -= nums[lp++];
                if(++rp == N){
                    break;
                }
                sum += nums[rp];
                continue;
            }

            sum -= nums[lp++];
        }

        bw.write((ans==Integer.MAX_VALUE)?"0":Integer.toString(ans));
        bw.flush();

        br.close();
        bw.flush();
    }
}
