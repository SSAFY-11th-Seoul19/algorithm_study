# 산봉우리(summits) 중 "하나만" 방문해야 한다
# 출입구(gates) 중 한 곳에서 출발
# 다시 처음 위치로 돌아와야 한다
# intensity가 최소인 등산 코스에 포함된 봉우리 번호와 그 때의 intensity를 리턴
import heapq
from collections import defaultdict

def solution(n, paths, gates, summits):
    INF = 1e8;
    intensity = [INF] * (n+1) # 출발점부터 i번 노드까지의 intensity
    graph = defaultdict(list);

    for i, j, w in paths:
        graph[i].append((w,j))
        graph[j].append((w,i)) # 양방향

    summits.sort()
    visited = set(summits) # 탐색을 O(N)에서 O(1)로 줄인다

    # 다익스트라
    def dijkstra():
        queue = []

        # 한번에 모든 gate를 넣는다
        for g in gates:
            heapq.heappush(queue, (0, g)) # weight과 게이트 노드
            intensity[g] = 0 # 출발점 자신이므로 0 설정
        
        result = [0, INF] # 산봉우리 번호, 그때의 intensity

        while queue:
            weight, cur = heapq.heappop(queue)
            if cur in visited or weight > intensity[cur]: # 산봉우리 발견
                continue # 산봉우리면 더 이동할 필요 없다

            for next_weight, next_node in graph[cur]:
                new_intensity = max(weight, next_weight) # 선택한 경로중 최대 weight가 intensity
                if new_intensity < intensity[next_node]:
                    intensity[next_node] = new_intensity # 최소 intensity로 갱신
                    heapq.heappush(queue, (new_intensity, next_node))

        # 봉우리 순회
        for s in summits:
            if intensity[s] < result[1]:
                result[0] = s
                result[1] = intensity[s]

        return result

    return dijkstra()
