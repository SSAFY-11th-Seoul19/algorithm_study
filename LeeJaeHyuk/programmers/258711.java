import java.util.*;
import java.io.*;

class 258711{
    HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        // 자료구조 hashMap으로 옮기기
        // HashMap<Integer, ArrayList<Integer>>
        // key: 시작정점 번호, value: 도착정점들 번호
        for(int i=0; i<edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            if(hashMap.containsKey(start)) {
                ArrayList<Integer> arr = hashMap.get(start);
                arr.add(end);
                hashMap.put(start, arr);
            }
            else {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(end);
                hashMap.put(start, arr);
            }
        }

        // 그래프 중심 정점 찾기
        // << 그래프 중심 정점의 특징 >>
        // - 해당 정점을 시작점으로 해서 뻗어나가는 간선의 개수는 2개 이상 있다.
        // - 해당 정점을 도착점으로 하는 간선은 존재하지 않는다.
        int centerNode = -1;

        // 시작 정점 번호들을 HashSet에 넣는다.
        HashSet<Integer> hashSet = new HashSet<>();
        for(int start : hashMap.keySet()) {
            hashSet.add(start);
        }

        // 모든 간선들을 탐색하면서 도착 정점 번호들을 HashSet에서 지운다. -> 간선들을 통해서 도착하지 못하는 정점들만 존재
        // 간선들을 통해서 도착하지 못하는 정점 후보들: 막대모양그래프 시작점 or 그래프 중심 정점
        for(int start : hashMap.keySet()) {
            for(int end : hashMap.get(start)) {
                if(hashSet.contains(end)) {
                    hashSet.remove(end);
                }
            }
        }

        // 막대모양 그래프 시작점은 간선을 1개 가짐.
        // 그래프 중심 정점은 간선을 2개 이상 가짐.
        // 이를 통해서 그래프 중심 정점을 구분할 수 있다.
        for(int start : hashSet) {
            if(hashMap.get(start).size() >= 2)
                centerNode = start;
        }

        answer[0] = centerNode;

        // 전체 그래프의 중심 정점에서 뻗어나가서 각 그래프의 모양이 뭔지 판단하기
        for(int graphStartNode : hashMap.get(centerNode)) {
            int shape = judgeGraph(graphStartNode);
            answer[shape] += 1;
        }

        return answer;
    }

    // 그래프 탐색 함수 => 인자로 넘어온 정점을 시작으로 해서 그래프를 탐색.
    // - 도넛 모양 그래프 특징: 그래프 탐색하다가 시작점으로 다시 돌아오면 도넛 모양 그래프
    // - 막대 모양 그래프 특징: 그래프 탐색하다가 더이상 뻗어나가지 못하는 정점에 도착(= 간선이 없는 정점에 도착)
    // - 8자 모양 그래프 특징: 그래프 탐색하다가 한 정점에서 2개로 뻗어나가는 간선을 가진 정점을 만난다.

    // return value => 1번: 도넛 모양, 2번: 막대 모양, 3번: 8자 모양
    public int judgeGraph(int startNodeNum) {
        int nextNodeNum = startNodeNum;

        while(true) {
            // 해당 정점을 시작으로 하는 간선이 없다 -> 막대 모양 그래프
            if(!hashMap.containsKey(nextNodeNum)) {
                return 2;
            }

            // 해당 정점을 시작으로 하는 간선이 2개 이상 존재 -> 8자 모양 그래프
            if(hashMap.get(nextNodeNum).size() >= 2) {
                return 3;
            }

            // 다음 정점으로 이동 -> 시작점으로 다시 돌아옴 -> 도넛 모양 그래프
            nextNodeNum = hashMap.get(nextNodeNum).get(0);
            if(startNodeNum == nextNodeNum) {
                break;
            }
        }
        return 1;
    }
}