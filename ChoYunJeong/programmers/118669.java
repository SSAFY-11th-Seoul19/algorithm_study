// 최소 신장 트리인가..?
import java.io.*;
import java.util.*;

class Point {
    int vertex, weight;
    Point(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Solution {
    static List<Point>[] pathList;
    static int N;
    static int[] intensities;
    static boolean[] visited;
    static int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE}; // 정답 배열
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n; // 지점 수를 static 변수에 저장
        
        pathList = new ArrayList[N+1];
        for (int i=1; i<=N; i++) {
            pathList[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            pathList[i].add(new Point(j, w)); // i에 j 지점 넣기
            pathList[j].add(new Point(i, w)); // j에 i 지점 넣기
        }

        visited = new boolean[N + 1];
        for (int summit : summits) {
            visited[summit] = true; // 산봉우리라면 true
        }

        intensities = new int[N+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        bfs(gates); // 각 출입구와 각 산봉우리 간의 intensity 배열
        for (int i=1; i<=N; i++) { // 모든 지점들을 돌며
            if (!visited[i]) { // 산봉우리가 아니라면 넘김
                continue;
            }

            if (intensities[i] < min[1]) { // intensity가 최솟값보다 작은 산봉우리를 만나면
                min[0] = i; // 산봉우리를 저장
                min[1] = intensities[i]; // 해당 intensity 저장
            }
        }

        return min;
    }

    private static void bfs(int[] gates) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt((nd)->nd.weight));
        
        // 출입구들을 돌며
        for (int gate : gates) {
            // 우선순위큐에 출입구 추가
            pq.add(new Point(gate, 0));
        }

        while(!pq.isEmpty()) {
            Point point = pq.poll();

            if (intensities[point.vertex] < point.weight) {
                continue;
            }

            // 현재 지점과 연결된 지점들을 돌며
            for (Point destination : pathList[point.vertex]) {
                // 최대 weight 갱신
                int maxWeight = Math.max(destination.weight, point.weight);
                
                // 만약 최대 weight이 intensity보다 작다면
                if (maxWeight < intensities[destination.vertex]) {
                    // intensity를 min하게 max로 바꿔줌
                    intensities[destination.vertex] = maxWeight;

                    // 만약 산봉우리가 아니라면 => 산봉우리라면 추가할 필요X(왔던 길 되돌아가는게 빠름)
                    if (!visited[destination.vertex]) {
                        // 새로 이동할 지점을 우선순위큐에 추가
                        pq.add(new Point(destination.vertex, intensities[destination.vertex]));
                    }
                }
            }
        }
    }
}
