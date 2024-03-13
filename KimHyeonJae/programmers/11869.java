import java.util.*;

class Solution {
    List<List<int[]>> graph = new ArrayList<>();
    Set<Integer> gateSet = new HashSet<>();
    Set<Integer> summitSet = new HashSet<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        // 1번 지점, 2번 지점, 3번지점
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        // 그래프 지정 
        for (int[] path: paths) {
            int v1 = path[0];
            int v2 = path[1];
            int w = path[2];
            graph.get(v1).add(new int[]{v2, w});
            graph.get(v2).add(new int[]{v1, w});
        }
        // 최대값으로 세팅
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 우선순위 큐 세팅
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] < o2[0]) {
                return -1;
            }
            return 1;
        });
        // gate Set로 묶기
        for (int gate: gates) {
            gateSet.add(gate);
            dist[gate] = 0;
            que.add(new int[]{0, gate});
        }
        // 정상들 Set 로 묶기 
        for (int summit:summits) {
            summitSet.add(summit);
        }
        while (!que.isEmpty()) {
            int[] node = que.remove();
            int w = node[0];
            int v = node[1];
            if (dist[v] < w) {
                continue;
            }
            for (int[] newNode: graph.get(v)) {
                int nv = newNode[0];
                int nw = Math.max(newNode[1], w);
                if (dist[nv] <= nw) {
                    continue;
                }
                if (gateSet.contains(nv)) {
                    continue;
                }
                if (summitSet.contains(nv)) {
                    dist[nv] = nw;
                    if (nw < answer[1]) {
                        answer = new int[]{nv, nw};
                    }
                    if (nw == answer[1] && nv < answer[0]) {
                        answer = new int[]{nv, nw};
                    }
                    continue;
                }
                que.add(new int[]{nw, nv});
                dist[nv] = nw;
            }
        }
        return answer;
    }
}