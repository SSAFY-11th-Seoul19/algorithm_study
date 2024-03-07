import sys
import collections
RED = 0
EMPTY = 'X'
STONE = -1

n, m = map(int, sys.stdin.readline().split())
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
# -1 검은돌, 0 빨간색 폭탄, 그 외 서로다른 색 폭탄
# 크기가 큰 폭탄 묶음들 중 빨간색 폭탄이 가장 적게 포함된것
# 행이 가장 큰 칸 -> 열이 가장 작은 칸 
# 반시계 방향 90 도 격자판 회전 

# 폭탄 제거 -> 중력 작용
# 반시계 90도 회전 -> 중력 작용

def find(y:int, x: int, visited: list):
    find_visited = [[0] * n for _ in range(n)]
    que = collections.deque([[y, x]])
    red = 0
    booms_list = [[y, x]]
    color = graph[y][x]
    find_visited[y][x] = True
    visited[y][x] = True
    max_y = y 
    min_x = x
    while que:
        cy, cx = que.popleft()
        for my, mx in [[0, -1],[0, 1],[-1, 0], [1, 0]]:
            ny, nx = cy + my, cx + mx
            if ny < 0 or ny >= n or nx < 0 or nx >= n:
                continue
            # 같은 색깔 아니고 빨간색도 아니면 
            if graph[ny][nx] != color and graph[ny][nx] != RED:
                continue
            if find_visited[ny][nx]:
                continue
            booms_list.append([ny, nx])
            find_visited[ny][nx] = True
            que.append([ny, nx])
            if graph[ny][nx] == RED:
                red += 1
                continue
            visited[ny][nx] = True
            max_y = max(max_y, y)
            min_x = min(min_x, x)
    return booms_list, red, max_y, min_x



def boom(booms_list: list) -> int:
    size = len(booms_list)
    if (size <= 1):
        return -1
    for y, x in booms_list:
        graph[y][x] = EMPTY
    return size * size

def find_and_boom():
    visited = [[False] * n for _ in range(n)]
    # max_y, min_x, total, red_stone, y, x
    booms_list = []
    cur_red_stone = 0
    cur_max_y = -1
    cur_min_x = float('inf')
    # 터뜨릴 위치 
    for y in range(n - 1, -1 ,-1):
        for x in range(n):
            if visited[y][x]:
                continue
            if graph[y][x] == RED or graph[y][x] == EMPTY or graph[y][x] == STONE:
                continue
            new_booms_list, red_stone, max_y, min_x = find(y, x, visited)
            if len(booms_list) < len(new_booms_list):
                booms_list = new_booms_list
                cur_red_stone = red_stone
                cur_max_y = max_y
                cur_min_x = min_x
            if len(booms_list) > len(new_booms_list):
                continue
            if cur_red_stone > red_stone:
                booms_list = new_booms_list
                cur_red_stone = red_stone
                cur_max_y = max_y
                cur_min_x = min_x
            if cur_red_stone < red_stone:
                continue
            if cur_max_y < max_y:
                booms_list = new_booms_list
                cur_red_stone = red_stone
                cur_max_y = max_y
                cur_min_x = min_x
            if cur_max_y > max_y:
                continue
            if cur_min_x > min_x:
                booms_list = new_booms_list
                cur_red_stone = red_stone
                cur_max_y = max_y
                cur_min_x = min_x
            if cur_min_x < min_x:
                continue
    return boom(booms_list)

def fall():
    for x in range(n):
        for cy in range(n - 2, -1, -1):
            if graph[cy][x] == STONE:
                continue
            if graph[cy][x] == EMPTY:
                continue
            ny = cy + 1
            color = graph[cy][x]
            graph[cy][x] = EMPTY
            while ny < n and graph[ny][x] == EMPTY:
                ny += 1
            graph[ny - 1][x] = color


def rotate():
    new_graph = [[0] * n for _ in range(n)]
    for r in range(n):
        for c in range(n):
            new_graph[r][c] = graph[c][n - r - 1]
    return new_graph 

answer = 0 
while True:
    score = find_and_boom()
    if score == -1:
        break
    fall()
    graph = rotate()
    fall()
    answer += score
print(answer)
