import java.util.*;

class Solution {
    
    static List<Integer>[] adjList;
    static int E, V, a, b, startVertex, donut, line, octa;
    static int[] vertexSize, vertexIn, vertexOut;
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[] isVisited;
    
    public int[] solution(int[][] edges) {
        E = edges.length;
        
        adjList = new List[1000001];
        for(int i =1; i< 1000001; i++){
            adjList[i] = new ArrayList<>();
        }
        
        vertexSize = new int[1000001];
        vertexIn = new int[1000001];
        vertexOut = new int[1000001];
        
        for(int i = 0; i < E; i++){
            a = edges[i][0];
            b = edges[i][1];
            
            vertexSize[a]++;
            vertexSize[b]++;
            vertexIn[b]++;
            vertexOut[a]++;
            
            adjList[a].add(b);
            adjList[b].add(a);
            
            V = Math.max(V, Math.max(a,b));
        }
        
        for(int i = 1; i <= V; i++) {
            if(vertexIn[i] != 0){
                continue;
            }
            if(vertexOut[i] < 2) {
                continue;
            }
            startVertex = i;
            break;
        }
        for(int nextVertex : adjList[startVertex]) {
            vertexSize[nextVertex]--;
            vertexIn[nextVertex]--;
        }
        
        isVisited = new boolean[V+1];
        isVisited[startVertex] = true;
        
        for(int nextVertex : adjList[startVertex]) {
            queue.clear();
            if(vertexOut[nextVertex]==0 || vertexSize[nextVertex] == 1){
            line++;
            continue;
        }
        if(vertexSize[nextVertex] > 2) {
            octa++;
            continue;
        }
            bfs(nextVertex);
        }
        
        int[] answer = {startVertex, donut, line, octa};
        return answer;
    }
    
    public static void bfs(int nextIdx) {
        queue.add(nextIdx);
        isVisited[nextIdx] = true;        
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            
            for(int next : adjList[now]) {
                if(isVisited[next]){
                    continue;
                }
                if(vertexOut[next] == 0 || vertexSize[next] == 1) {
                    line++;
                    return;
                }
                if(vertexSize[next] > 2) {
                    octa++;
                    return;
                }
                isVisited[next] = true;
                queue.add(next);
            }
        }
        donut++;
    }
}
