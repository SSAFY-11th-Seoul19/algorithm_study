import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final long MAX = 100_000L *100_000L;
    static int N,M;
    static int[] isVisible;
    static long[] times;
    static ArrayList<Edge>[] edges;

    static class Edge implements Comparable<Edge>{
        int target;
        long time;

        public Edge(int target, long time){
            this.target = target;
            this.time = time;
        }

        public int compareTo(Edge o){
            return (int)(this.time - o.time);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        isVisible = new int[N];
        times = new long[N];
        edges = new ArrayList[N];
        for(int i=0;i<N;i++){
            isVisible[i] = Integer.parseInt(st.nextToken());
            times[i] = MAX;
            edges[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            long time = Long.parseLong(st.nextToken());

            edges[start].add(new Edge(target,time));
            edges[target].add(new Edge(start,time));
        }

        dijkstra(0);
        bw.write((times[N-1]==MAX)?"-1":Long.toString(times[N-1]));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void dijkstra(int start){
        times[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.time > times[now.target]){
                continue;
            }

            for(Edge next:edges[now.target]){
                if(isVisible[next.target]==1 && next.target!=N-1){
                    continue;
                }

                if(next.time+now.time >= times[next.target]){
                    continue;
                }

                times[next.target] = next.time+now.time;
                pq.add(new Edge(next.target, times[next.target]));
            }
        }
    }
}
