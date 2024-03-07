import java.io.*;
import java.util.*;

public class 6603 {
    static int k;
    static int[] list;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            if(k == 0)
                break;

            visited = new boolean[k];
            list = new int[k];
            for(int i=0; i<k; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            System.out.println();

        }
    }

    public static void dfs(int index, int depth) {
        if(depth == 6) {
            for(int i=0; i<k; i++) {
                if(visited[i]) {
                    System.out.print(list[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        if(index >= k) return;

        visited[index] = true;
        dfs(index+1, depth+1);
        visited[index] = false;
        dfs(index+1, depth);
    }
}