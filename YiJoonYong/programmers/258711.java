import java.util.*;

class Solution {
    final int maxLen = 1_000_000;
    HashMap<Integer, List<Integer>> adjList = new HashMap<>();
    int[] inDegreeArr = new int[maxLen+1];
    int[] answer = new int[4];

    public int[] solution(int[][] edges) {
        for (int[] edge : edges) {
            // [0]:from [1]:to
            if (!adjList.containsKey(edge[0])) {
                adjList.put(edge[0], new ArrayList<>());
            }
            if (!adjList.containsKey(edge[1])) {
                adjList.put(edge[1], new ArrayList<>());
            }
            adjList.get(edge[0]).add(edge[1]);
            inDegreeArr[edge[1]]++;
        }

        // 생성한 정점 찾기. 진입차수 0 && 진출차수 2이상
        for (int i : adjList.keySet()) {
            if (inDegreeArr[i]==0 && adjList.get(i).size() > 1) {
                answer[0] = i;
                break;
            }
        }

        int createdNode = answer[0];
        for (int node : adjList.get(createdNode)) {
            inDegreeArr[node]--;

        }

        for (int node : adjList.keySet()) {
            if (node == createdNode) {
                continue;
            }
            if (inDegreeArr[node]==0) {
                answer[2]++;
                continue;
            }
            if (inDegreeArr[node]==2 && adjList.get(node).size()==2) {
                answer[3]++;
            }
        }

        answer[1] = adjList.get(createdNode).size() - answer[2] - answer[3];
        return answer;
    }
}