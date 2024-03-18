def solution(alp, cop, problems):
    #IDEA. dp를 이용해서 풀것임.
    #row와 col은 각각 알고력과 코딩력이 해당 index일때 도달하는데 걸리는 시간을 의미함.
    
    #최대 알고력과 최대 코딩력을 기억하기 위해 초기화
    max_alp, max_cop = 0, 0
    for lst in problems:
        max_alp = max(max_alp, lst[0])
        max_cop = max(max_cop, lst[1])
    
    #dp배열은 최대 알고력+1, 최대 코딩력+1로 선언
    dp = [[10e9] *(max_cop+1)for _ in range(max_alp+1)]
    
    #시간 초과나서 이쪽부분 고침
    #최소 확인 위치 변경
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    #alp와 cop밑으로는 확인하지 않을것임..!
    dp[alp][cop] = 0
    
    #알고력 해당 수준부터 최대값까지돌면서
    for i in range(alp, max_alp+1):
        # 코딩력 해당 수준부터 최대값까지 돌면서
        for j in range(cop, max_cop+1):
            #만약, 현재 알고력이 최대 알고력을 초과하지 않을때만 값을 더해줌
            if i+1<=max_alp:
                dp[i+1][j] = min(dp[i+1][j], dp[i][j]+1)
            #만약, 현재 코딩력 또한 마찬가지
            if j+1<=max_cop:
                dp[i][j+1] = min(dp[i][j+1], dp[i][j]+1)
            
            #각 문제들 별로 확인하면서
            for lst in problems:
                #만약, 알고력과 코딩력이 수준을 만족한다면
                if i>=lst[0] and j>=lst[1]:
                    #크기를 넘어가지 않는 부분에 대해서 cost값을 더해준값과 비교해줌
                    #2번 실수
                    new_alp = min(i+lst[2], max_alp)
                    new_cop = min(j+lst[3], max_cop)
                    dp[new_alp][new_cop] = min(dp[new_alp][new_cop], dp[i][j]+lst[4])
    return dp[max_alp][max_cop];
