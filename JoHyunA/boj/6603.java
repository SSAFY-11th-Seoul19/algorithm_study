import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int LOTTO_CNT = 6;
    static int k;
    static int[] nums;
    static int[] lottos = new int[LOTTO_CNT];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String input = br.readLine();

            if(input.equals("0")){
                break;
            }

            StringTokenizer st = new StringTokenizer(input);
            k = Integer.parseInt(st.nextToken());
            nums = new int[k];
            for(int i=0;i<k;i++){
                nums[i] = Integer.parseInt(st.nextToken());
            }
            makeLotto(0,0);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void makeLotto(int cnt, int idx){
        if(cnt == LOTTO_CNT){
            for(int lotto:lottos){
                sb.append(lotto).append(" ");
            }
            sb.append("\n");
            return;
        }

        if(idx==k){
            return;
        }

        lottos[cnt] = nums[idx];
        makeLotto(cnt+1, idx+1);
        makeLotto(cnt,idx+1);
    }
}
