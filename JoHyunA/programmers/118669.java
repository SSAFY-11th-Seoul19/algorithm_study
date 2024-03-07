import java.util.*;

class Solution {
    static int[] minDists;
    static ArrayList<Edge>[] edges;
    static boolean[] isSummit;
    
    static class Edge implements Comparable<Edge>{
        int to;
        int weight;
        
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        edges = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] path:paths){
            edges[path[0]].add(new Edge(path[1],path[2]));
            edges[path[1]].add(new Edge(path[0],path[2]));
        }
        
        isSummit = new boolean[n+1];
        for(int summit:summits){
            isSummit[summit] = true;
        }
        
        minDists = new int[n+1];
        Arrays.fill(minDists,Integer.MAX_VALUE);
        
        findIntentisy(gates);
        
        int minIdx = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        for(int summit:summits){
            if(minIntensity > minDists[summit]){
                minIntensity = minDists[summit];
                minIdx = summit;
                continue;
            }
            
            if(minIntensity == minDists[summit]){
                minIdx = Math.min(minIdx, summit);
            }
        }
        
        return new int[]{minIdx, minIntensity};
    }
    
    public void findIntentisy(int[] gates){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int gate:gates){
            pq.add(new Edge(gate,0));
            minDists[gate] = 0;
        }
        
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            
            if(minDists[now.to] < now.weight){
                continue;
            }
            
            for(Edge edge:edges[now.to]){
                int intensity = Math.max(edge.weight, now.weight);
                
                if(minDists[edge.to] <= intensity){
                    continue;
                }
                
                minDists[edge.to] = intensity;
                
                if(!isSummit[edge.to]){
                    pq.add(new Edge(edge.to,intensity));
                }
            }
        }
    
    }
}
