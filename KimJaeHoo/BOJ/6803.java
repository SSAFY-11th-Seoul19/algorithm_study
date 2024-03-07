import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static int[] arr = new int[12];
    static int[] temp = new int[7];
    static boolean[] visited = new boolean[50];
    static StringBuilder ans = new StringBuilder("");

    static void search(int depth){
        if(depth == 6){
            for(int i = 1 ; i <= 6; i++){
                ans.append(temp[i]).append(" ");
            }
            ans.append("\n");
            return;
        }

        for(int i = 0; i < K; i++){
            if(visited[arr[i]] || arr[i] < temp[depth]) continue;

            visited[arr[i]] = true;
            temp[depth+1] = arr[i];

            search(depth+1);

            visited[arr[i]] = false;
        }
    }

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

            search(0);
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
