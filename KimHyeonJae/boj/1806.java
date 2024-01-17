import sys

N, S = map(int,sys.stdin.readline().split())
numbers = list(map(int, sys.stdin.readline().split()))
left = 0
temp = 0

# 불가능한 경우
if sum(numbers) < S:
    print(0)
else:
    min_length = sys.maxsize
    # 끝 점까지 탐색
    for right in range(N):
        # end 의 값을 더 해나감
        temp += numbers[right]
        # start ~ end 까지의 합이 S보다 크거나 같을 시에
        if temp >= S:
            # start ~ end 까지으 합이 S보다 작을 때까지 start 를 증가
            while temp >= S:
                temp -= numbers[left]
                left += 1
            # start ~ end 까지의 합이 S보다 작은 경우 
            min_length = min(min_length, right - left + 2)
        right += 1
    print(min_length)