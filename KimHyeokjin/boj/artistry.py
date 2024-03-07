import sys
from collections import deque, defaultdict
import copy
n = int(sys.stdin.readline())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def getScore():
    visited = [[0] * n for _ in range(n)]
    group = []
    groupCnt = 0

    #group으로 나누기(bfs)
    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                val = board[i][j]
                group.append([(i, j)])
                queue = deque([(i, j)])
                visited[i][j] = True
                while queue:
                    y, x = queue.pop()

                    for k in range(4):
                        newY = y+dy[k]
                        newX = x+dx[k]
                        
                        if newY<0 or newY>=n or newX<0 or newX>=n:
                            continue
                        #만약, 자신의 양옆에 있는애중에 값이 다르고 방문처리가 되었다면.. 이미저장된 값일테니 찾아서 확인

                        if visited[newY][newX] or board[newY][newX]!=val:
                            continue

                        visited[newY][newX] = True
                        group[groupCnt].append((newY, newX))
                        queue.append((newY, newX))
                groupCnt+=1
    contactMap = defaultdict(int)
    for i in range(len(group)-1):
        for j in range(i+1, len(group)):
            for k in range(len(group[i])):
                for l in range(4):
                    if (group[i][k][0] + dy[l], group[i][k][1] + dx[l]) in group[j]:
                        contactMap[(i, j)] +=1
    ans = 0
    #점수 구하기
    for key, value in contactMap.items():
        ans += (len(group[key[0]]) + len(group[key[1]])) * board[group[key[0]][0][0]][group[key[0]][0][1]] * board[group[key[1]][0][0]][group[key[1]][0][1]] * value
    return ans

def mix():
    global board
    newBoard = [[0] * n for _ in range(n)]
    #4위치로 분할
    #0~N//2-1, N//2+1~N
    #왼쪽 상단
    for i in range(n//2):
        for j in range(n//2):
            newBoard[j][n//2-i-1] = board[i][j]
    #오른쪽 상단
    for i in range(n//2+1, n):
        for j in range(n//2):
            newBoard[n//2+j+1][n-i-1] = board[i][j]

    for i in range(n//2):
        for j in range(n//2+1, n):
            newBoard[j-n//2-1][n-i-1] = board[i][j]

    for i in range(n//2+1, n):
        for j in range(n//2+1, n):
            newBoard[j][n-i+n//2] = board[i][j]

    for i in range(n//2, n//2+1):
        for j in range(n):
            newBoard[n-j-1][i] = board[i][j]

    for j in range(n//2, n//2+1):
        for i in range(n):
            newBoard[j][i] = board[i][j]

    board = copy.deepcopy(newBoard)
    # for i in range(n):
    #     print(*board[i])

    # print("=============")
if __name__=="__main__":
    ans = 0
    ans += getScore()
    for i in range(3):
        mix()
        ans+=getScore()
    print(ans)
