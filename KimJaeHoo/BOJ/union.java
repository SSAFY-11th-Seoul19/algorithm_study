import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, o, x, y;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder ans = new StringBuilder("");
    static int[] arr;

    public static int find(int a){
        if(arr[a] == a)
            return arr[a];
        return (arr[a] = find(arr[a]));
    }
    public static void union(int a, int b){
        int groupA = find(a);
        int groupB = find(b);
        arr[groupB] = groupA;

    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = i;
        }

        for(int i =0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            o = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if(o == 0)
                union(x,y);
            else if(find(x) == find(y))
                ans.append("YES").append("\n");
            else
                ans.append("NO").append("\n");
        }

        System.out.println(ans);
    }
}
