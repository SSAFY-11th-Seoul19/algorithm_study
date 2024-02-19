import sys

N, K = map(int, sys.stdin.readline().split())
lst = sorted([list(map(int, sys.stdin.readline().split())) for _ in range(N)], key =lambda x : x[0])

dp = [[0] * (K+1) for _ in range(N)]

#IDEA. 평범한 배낭 문제
#2차원 배열로 선언해서 row는 lst를 돌아가면서 순서 대로 row만큼 물품을 넣었을때 col(무게)만큼 몇개 들어가는지 확인
for i in range(N):
    #무게+1만큼 선언해서 확인해줄 것임!
    for j in range(K+1):
        #1. i ==0일때, 자기값만 대입해주면됨
        if i==0:
            if lst[i][0] <=j:
                dp[i][j] = lst[i][1]
                continue
        #2. 그 외의 경우에는
        else:
            #만약 무게가 충분하다면
            if lst[i][0] <=j:
                #하나 덜 가져온거와 자신의 무게를 뺏던것 만큼의 가능 무게 수중 큰 값을 고른다
                dp[i][j] = max(dp[i-1][j], lst[i][1] + dp[i-1][j-lst[i][0]])
                continue
            #그 외의 경우에는 이전값과 동일!
            dp[i][j] = dp[i-1][j]
#제일 큰 값을 확인!
print(dp[N-1][K])
