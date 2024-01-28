import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] liquids;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        while(start<end){
            int sum = liquids[start]+liquids[end];
            if(Math.abs(sum) < Math.abs(min)){
                min = sum;
            }

            if(sum == 0){
                break;
            }
            else if(sum>0){
                end--;
            }
            else{
                start++;
            }
        }

        bw.write(Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }
}
