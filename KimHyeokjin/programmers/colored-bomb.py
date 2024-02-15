import sys
from collections import deque
#n -> 격자 크기, m -> 숫자 색깔
n, m =map(int, sys.stdin.readline().split())
#-1 -> 돌, 0 -> 빨폭, 1~m 다른색 폭탄
board = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
ans = 0
#0. 폭탄 묶음 찾아야함
#0-1. 2개 이상의 폭탄으로 이루어져야 함.
#0-2. n**2이 점수이므로 무조건 많이 묶이는게 좋음
#0-3. 빨간색을 포함하여 정확하게 두개 or 한가지 색깔로만
#0-4. 빨간색만 폭탄이면 안됨!
#IDEA. 빨간색은 visit포함하면 안될듯?
while True:
    visited = [[0] * n for _ in range(n)]
    #append할때 총 갯수, 빨간색 갯수, 중심점의 y, 중심점의 x좌표
    bombLst = []
    for i in range(n):
        for j in range(n):
            # 안되는 경우 빼줌
            if visited[i][j] == 1 or board[i][j] == -1 or board[i][j] == 0 or board[i][j] == -2:
                continue
            #초기화
            totalCnt = 1
            redCnt = 0
            y = i
            x = j
            #기본이 되는 색깔
            index = board[i][j]
            #dfs를 돌기 위한 queue 선언
            queue = deque([[i, j]])
            #빨간색 기억해놓기
            redIndex = []
            visited[i][j] = 1
            while queue:
                tmpI, tmpJ = queue.popleft()
                for k in range(4):
                    newY = tmpI + dy[k]
                    newX = tmpJ + dx[k]
                    #방문 처리
                    if newX<0 or newX>=n or newY<0 or newY>=n or visited[newY][newX] == 1:
                        continue
                    # index와 같을 때
                    if board[newY][newX]==index:
                        totalCnt+=1
                        #여기 오래걸림
                        if newY > y:
                            y = newY
                            x = newX
                            
                        elif newY == y and newX < x:
                            x = newX

                        queue.append([newY, newX])
                        visited[newY][newX] = 1
                        continue
                    #빨간 폭탄일때
                    if board[newY][newX] == 0:
                        totalCnt +=1
                        redCnt +=1
                        queue.append([newY, newX])
                        visited[newY][newX] = 1
                        redIndex.append([newY, newX])
                        continue
            if totalCnt>=2:
                bombLst.append([totalCnt, redCnt, y, x])
            # 빨간 폭탄 방문 초기화
            for redY, redX in redIndex:
                visited[redY][redX] = 0
    bombLst.sort(lambda x: (-x[0], x[1], -x[2], x[3]))
    #만약, 폭탄 더 안나온다하면 바로 종료
    if len(bombLst)==0:
        break

    #1. 가장 큰 폭탄 묶음 확인
    #1-1. 크기가 큰 폭탄 묶음들 중 빨간색이 적게 포함된 것 부터
    #1-2. 묶음의 기준점 중 가장 행이 큰 폭탄 묶음을 선택
        #기준점 : 해당 폭탄 묶음중 빨간색이 아니면서 y가 가장큰 칸을 의미
        #행이 똑같은게 여러개면 x가 0에 가까운것
    #1-3. 행이 똑같으면 열이 작은것을 선택
    #bomLst중에 가장 큰 우선순위의 y, x에 대해서 다시 bfs하면서 지워줌
    queue = deque([[bombLst[0][2], bombLst[0][3]]])
    ans += bombLst[0][0] **2
    #다시 visit 확인
    visited = [[0] * n for _ in range(n)]
    #첫 위치는 1로 바꿔주고
    visited[bombLst[0][2]][bombLst[0][3]] = 1
    tmp = board[bombLst[0][2]][bombLst[0][3]]
    while queue:
        y, x = queue.popleft()
        board[y][x] = -2
        for i in range(4):
            newY = y+dy[i]
            newX = x+dx[i]
            if newY<0 or newY>=n or newX <0 or newX >=n or visited[newY][newX] ==1:
                continue
            if board[newY][newX] == tmp or board[newY][newX] == 0:
                board[newY][newX] = -2
                visited[newY][newX] = 1
                queue.append([newY, newX])

#2. 폭탄묶음 전부 제거. 제거 후에 폭탄들은 떨어지지만, 돌은 안떨어짐
    for i in range(n-1, -1, -1):
        for j in range(n):
            #비었다면
            if board[i][j] == -2:
                tmpI = i-1
                while 0<=tmpI:
                    #계속 비어있다면 빈데까지 계속 찾음
                    if board[tmpI][j] == -2:
                        tmpI-=1
                        continue
                    #돌이라면
                    if board[tmpI][j] == -1:
                        break
                    board[i][j] = board[tmpI][j]
                    board[tmpI][j] = -2
                    break
#3. 반시계방향으로 90도 회전
    board = list(map(list, zip(*board)))[::-1]

#4. 다시 중력 작용
    for i in range(n-1, -1, -1):
        for j in range(n):
            #비었다면
            if board[i][j] == -2:
                tmpI = i-1
                while 0<=tmpI:
                    #계속 비어있다면 빈데까지 계속 찾음
                    if board[tmpI][j] == -2:
                        tmpI-=1
                        continue
                    #돌이라면
                    if board[tmpI][j] == -1:
                        break
                    board[i][j] = board[tmpI][j]
                    board[tmpI][j] = -2
                    break

# 중력 작용하는 거 함수화 해야될 듯
print(ans)
