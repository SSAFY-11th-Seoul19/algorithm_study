import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans, S;
    static int[] crane;
    static ArrayList<Integer> boxes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        crane = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            crane[i] = -Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crane);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M ; i++){
            boxes.add(-Integer.parseInt(st.nextToken()));
        }
        boxes.sort((o1, o2) -> Integer.compare(o1,o2));

        int idx = 0;
        while(idx < boxes.size()){
            if(-crane[0] < -boxes.get(idx++)){
                System.out.println(-1);
                return;
            }
        }

        while(!boxes.isEmpty()){
            int bIdx = 0, cIdx = 0;
            while(cIdx < N){
                if(-crane[cIdx] >= -boxes.get(bIdx)){
                    boxes.remove(bIdx);
                    cIdx++;
                }
                else{
                    bIdx++;
                }
                if(bIdx == boxes.size()){
                    break;
                }
            }
            ans++;
        }
        ANS_PART:
        System.out.println(ans);
    }
}
