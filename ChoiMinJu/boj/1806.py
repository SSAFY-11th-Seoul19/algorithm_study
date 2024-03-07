N, S = map(int, input().split()) # 수열의 길이 N, 부분합이 S 이상 되는 것 중 가장 짧은 것의 길이
lst = list(map(int, input().split()))

lp, rp = 0, 0
partial_sum = lst[0] # 부분합 s
ans = 1e9 # 부분합 길이의 최소

while lp <= rp:
    if partial_sum >= S:
        partial_sum -= lst[lp]
        ans = min(ans, rp-lp+1)
        lp += 1
    else:
        rp += 1
        if rp >= N: break
        partial_sum += lst[rp]

print(0) if ans == 1e9 else print(ans)
