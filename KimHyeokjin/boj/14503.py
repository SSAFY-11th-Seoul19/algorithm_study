import sys
N, M = map(int, sys.stdin.readline().split())
y, x, d = map(int, sys.stdin.readline().split())

board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]


ans =0
#1은 벽, 2는 청소된 칸, 0은 청소 안된 칸
while(True):
    #1번 조건, 현재칸이 청소X -> 청소(0에서 2로 바꿔줌)
    if board[y][x]==0:
        print(y, x)
        ans+=1
        board[y][x] = 2

    #2,3번 조건을 위해 4방향을 확인하며 청소되지 않은 칸(0)이 있는지 확인
    #4방향중 0인 곳이 있으면 false, 다 청소되었거나 벽이라면 true
    flag = True
    for i in range(4):
        if board[y+dy[i]][x+dx[i]]==0:
            flag = False
            break

    #2. 4방향으로 빈칸이 없으면
    if flag:
        #2-1. 후진시도, 후진자리 없으면 끝냄
        if board[y+dy[(d+2)%4]][x+dx[(d+2)%4]]==1:
            break
        #2-2. 후진 시도 했는데 자리가 있으면(0이든 2이든)
        #x, y좌표 바꿔주고 1번으로 돌아감
        else:
            y = y+dy[(d+2)%4]
            x = x+dx[(d+2)%4]
            continue
    #3. 4방향중 빈칸이 있다면
    else:
        #방향을 반시계방향으로 회전
        if d==0:
            d =3
        else:
            d-=1
        if board[y+dy[d]][x+dx[d]] ==0:
            y = y+dy[d]
            x = x+dx[d]
            continue

print(ans)
