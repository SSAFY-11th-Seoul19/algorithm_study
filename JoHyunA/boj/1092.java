import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static Integer[] cranes;
    static Integer[] weights;
    static int[] lastChecked;
    static int remainedCnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        cranes = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        weights = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(weights, Collections.reverseOrder());
        if(cranes[0] < weights[0]){
            bw.write("-1");
            bw.flush();
            return;
        }

        lastChecked = new int[N];
        int time = 0;
        remainedCnt = M;
        while(remainedCnt>0){
            for(int i=0;i<N;i++){
                int crane = cranes[i];
                boolean removed = false;

                for(int j=lastChecked[i];j<M;j++){
                    if(weights[j] == -1){
                        continue;
                    }

                    lastChecked[i]++;
                    if(crane >= weights[j]){
                        weights[j] = -1;
                        remainedCnt--;
                        removed = true;
                        break;
                    }
                }

                if(!removed){
                    break;
                }
            }
            time++;
        }

        bw.write(Integer.toString(time));
        bw.flush();

        br.close();
        bw.close();
    }
}

