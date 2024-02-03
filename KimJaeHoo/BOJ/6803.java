import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static int[] arr = new int[12];
    static int[] temp = new int[12];
    static StringBuilder ans = new StringBuilder("");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른입력

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0)
                break;

            for(int k = 0; k < K; k++){
                arr[k] = Integer.parseInt(st.nextToken());
            }
            SEARCH :
            for(int i = 1; i < (1 << K); i++) {
                int count = 0;
                for (int j = 0; j < K; j++) {
                    if ((i & (1 << j)) == 0) continue;
                    temp[count++] = arr[j];
                }
                if (count != 6)
                    continue;

                for (int j = 0; j < 6; j++) {
                    ans.append(temp[j]).append(" ");
                }
                ans.append("\n");
            }
            ans.append("\n");
        }
        System.out.println(ans);

    }
}
