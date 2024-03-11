import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, minBox = Integer.MAX_VALUE;
    static int[] craneArr;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        craneArr = new int[N];
        for (int i=0; i<N; i++) {
            craneArr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i=0; i<M; i++) {
            int box = Integer.parseInt(st.nextToken());
            pq.add(box);
            minBox = Math.min(minBox, box);
        }

        Arrays.sort(craneArr);
        int cIdxMin = 0;
        for (int i=0; i<N; i++) {
            if (craneArr[i] >= minBox) {
                cIdxMin = i;
                break;
            }
        }

        if (craneArr[N-1] < pq.peek()) {
            pq.clear();
        }

        int time = 0;
        List<Integer> tempStorage = new ArrayList<>();
        while(!pq.isEmpty()) {
            int cIdx = N-1;
            while ((cIdx >= cIdxMin) && !pq.isEmpty()) {
                Integer target = pq.poll();
                if (craneArr[cIdx] < target) {
                    tempStorage.add(target);
                    continue;
                }
                cIdx--;
            }

            time++;
            pq.addAll(tempStorage);
            tempStorage.clear();
        }

        System.out.println(time>0 ? time : -1);
    }
}

