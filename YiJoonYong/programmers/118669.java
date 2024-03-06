import java.util.*;
class Solution {
    static List<Node>[] adjList;
    static int sn;
    static boolean[] isSummits;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        sn = n;

        adjList = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];
            adjList[i].add(new Node(j, w));
            adjList[j].add(new Node(i, w));
        }

        isSummits = new boolean[n + 1];
        for (int summit : summits) {
            isSummits[summit] = true;
        }

        int[] intensity = getIntensity(gates);

        int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i=1; i<=n; i++) {
            if (!isSummits[i]) {
                continue;
            }

            if (intensity[i] < min[1]) {
                min[0] = i;
                min[1] = intensity[i];
            }
        }

        int[] answer = {min[0], min[1]};
        return answer;
    }

    private static int[] getIntensity(int[] gates) {
        int[] intensity = new int[sn+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(nd->nd.w));
        for (int gate : gates) {
            pq.add(new Node(gate, 0)); // 게이트 넣기
        }

        while(!pq.isEmpty()) {
            Node target = pq.poll();

            if (intensity[target.no] < target.w) {
                continue;
            }

            for (Node child : adjList[target.no]) {
                
                int maxWeight = Math.max(child.w, target.w);
                if (intensity[child.no] > maxWeight) {
                    intensity[child.no] = maxWeight;
                    
                    if (!isSummits[child.no]) {
                        pq.add(new Node(child.no, intensity[child.no]));
                    }
                }
            }
        }

        return intensity;
    }

    static class Node {
        int no, w;
        public Node(int no, int w) {
            this.no = no;
            this.w = w;
        }
    }
}

