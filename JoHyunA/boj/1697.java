import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_LOCATION = 100_000;
    public static int N, K;
    public static boolean[] visited = new boolean[MAX_LOCATION+1];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N!=K){
            bw.write(Integer.toString(findAnswer()));
        }
        else{
            bw.write("0");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findAnswer(){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        visited[N] = true;

        int time = 1;
        while(!dq.isEmpty()){
            int dqSize = dq.size();
            for(int i=0;i<dqSize;i++){
                int now = dq.poll();

                if(now-1==K || now+1==K || now*2==K){
                    return time;
                }

                if(now-1>=0 && !visited[now-1]){
                    visited[now-1] = true;
                    dq.add(now-1);
                }

                if(now+1<=MAX_LOCATION && !visited[now+1]){
                    visited[now+1] = true;
                    dq.add(now+1);
                }

                if(now*2<=MAX_LOCATION && !visited[now*2]){
                    visited[now*2] = true;
                    dq.add(now*2);
                }
            }

            time++;
        }

        return time;
    }
}
