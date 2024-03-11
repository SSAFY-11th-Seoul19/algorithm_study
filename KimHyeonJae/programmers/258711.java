import java.util.*;

class Solution {
    Map<Integer, Integer> inNode = new HashMap<>();
    Map<Integer, Integer> outNode = new HashMap<>();
    Map<Integer, List<Integer>> outs = new HashMap<>();
    Set<Integer> nodes = new HashSet<>();
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for (int[] edge: edges) {
            int start = edge[0];
            int end = edge[1];
            nodes.add(start);
            nodes.add(end);
            outNode.put(start, outNode.getOrDefault(start, 0) + 1);
            List<Integer> outArrays = outs.getOrDefault(start, new ArrayList<>());
            outArrays.add(end);
            outs.put(start, outArrays);
            inNode.put(end, inNode.getOrDefault(end, 0) + 1);
        }
        
        int total = 0;
        for (int node: nodes) {
            if (!inNode.containsKey(node) && outNode.containsKey(node) && outNode.get(node) >= 2) {
                answer[0] = node;
                for (int newNode: outs.get(node)) {
                    total += 1;
                    inNode.put(newNode, inNode.get(newNode) - 1);
                }
                break;
            }
        }
        nodes.remove(answer[0]);
        for (int node:nodes) {
            int in = inNode.getOrDefault(node, 0);
            int out = outNode.getOrDefault(node, 0);
            if (in >= 0 && out == 0) {
                total -= 1;
                answer[2] += 1;
                continue;
            }
            if (in >= 2 && out == 2) {
                total -= 1;
                answer[3] += 1;
                continue;
            }
        }
        answer[1] = total;
        return answer;
    }
}