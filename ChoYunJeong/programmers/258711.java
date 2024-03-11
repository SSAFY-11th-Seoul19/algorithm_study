import java.util.*;

class Solution {
    // 정점들의 연결 정보를 저장하는 맵
    static Map<Integer, int[]> nodes;
    
    public int[] solution(int[][] edges) {
        // 맵 초기화
        nodes = new HashMap<>();
        // 결과를 담을 배열 초기화
        int[] answer = {0, 0, 0, 0};
        // 정점별 간선 개수를 담을 배열
        int[] edgeCounts;
        
        // 각 간선 정보를 순회하며 정점들의 연결 정보를 저장
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // 정점이 맵에 없을 경우 초기화
            if (!nodes.containsKey(from)) {
                nodes.put(from, new int[]{0, 0});
            }
            if (!nodes.containsKey(to)) {
                nodes.put(to, new int[]{0, 0});
            }
            // 각 정점의 간선 개수 업데이트
            nodes.get(from)[0] += 1;
            nodes.get(to)[1] += 1;
        }
        
        // 각 정점을 순회하며 도넛, 막대, 8자 그래프의 개수를 계산
        for (int vertex : nodes.keySet()) {
            edgeCounts = nodes.get(vertex);
            // 도넛 그래프인 경우
            if (edgeCounts[0] >= 2 && edgeCounts[1] == 0) {
                answer[0] = vertex;
                continue;
            }
            // 막대 그래프인 경우
            if (edgeCounts[0] == 0 && edgeCounts[1] > 0) {
                answer[2]++;
                continue;
            } 
            // 8자 그래프인 경우
            if (edgeCounts[0] >= 2 && edgeCounts[1] >= 2) {
                answer[3]++;
            }
        }
        
        // 도넛 그래프를 제외한 정점의 간선 개수 계산
        answer[1] = nodes.get(answer[0])[0] - answer[2] - answer[3];
        
        // 결과 반환
        return answer;
    }
}
