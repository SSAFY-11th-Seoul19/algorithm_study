import sys
N = int(sys.stdin.readline())

dp = [0] * (N+1)
lst = [list(map(int,sys.stdin.readline().split())) for _ in range(N)]

#IDEA.평범한 dp문제 단, 뒤에서 생각할 것
for i in range(N-1, -1, -1):
    #만약, 현재의 index와 해당 일자의 날짜의 합이 N을 넘게 되면
    if i + lst[i][0] > N:
        # i가 마지막날이 아닐 경우에만 이전 날짜의 dp값을 가져옴
        if i!=N-1:
            dp[i] = dp[i+1]
        continue
    #그외의 경우에 대해서는
    else:
        #현재로부터 지금 일자의 끝나는 날을 더해주고, 전날의 값 중 더 큰걸 가져와줌
        dp[i] = max(lst[i][1]+ dp[i+lst[i][0]], dp[i+1])
        
#0일차에 최대 이익을 출력
print(dp[0])
