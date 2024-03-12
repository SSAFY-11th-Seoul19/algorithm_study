def solution(edges):
    answer = [0, 0, 0, 0]
    graph = {}
    #순서대로 자기로부터 나간 edge, 들어온 edge
    for a, b in edges:
        if a not in graph:
            graph[a] = [0, 0]
        if b not in graph:
            graph[b] = [0, 0]
        graph[a][0]+=1
        graph[b][1]+=1
    
    #시작점으로부터 연결된 값들 중 나가는거 1개, 들어오는거 1개 : 막대
    #2개 이상씩 -> 8
    #그외 -> 도넛
    for i in graph:
        if graph[i][0]>=2 and graph[i][1]==0:
            answer[0] = i
            continue
        if graph[i][0] == 0 and graph[i][1]>=1:
            answer[2]+=1
            continue
        if graph[i][0]>=2 and graph[i][1]>=2:
            answer[3]+=1
    
    answer[1] = graph[answer[0]][0] - answer[2] - answer[3]
  
    return answer
