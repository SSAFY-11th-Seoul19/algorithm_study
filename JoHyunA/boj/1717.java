import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n+1];
        for(int i=0;i<=n;i++){
            nums[i] = i;
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 0){
                union(a,b);
                continue;
            }

            if(findParent(a) == findParent(b)){
                sb.append("YES\n");
                continue;
            }

            sb.append("NO\n");
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void union(int a, int b){
        if(a == b){
            return;
        }

        a = findParent(a);
        b = findParent(b);

        nums[a] = b;
    }

    public static int findParent(int child){
        if(child == nums[child]){
            return child;
        }

        return nums[child] = findParent(nums[child]);
    }

}
