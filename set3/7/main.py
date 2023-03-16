import sys

def input():
    return sys.stdin.readline().rstrip()

N, K = map(int, input().split())
arr = list(map(int, input().split()))

erased, answer = 0, 0
R = 0
for i in range(N):
    while R < N:
        if arr[R] % 2 == 0: R += 1
        else:
            if erased == K:
                break
            erased += 1
            R += 1
    answer = max(answer, R - i - erased)
    if arr[i] % 2 == 1:
        erased -= 1
print(answer)