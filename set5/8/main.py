import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
arr = list(map(int, input().split()))
L, R = 1, 1000000
while L <= R:
    mid = (L + R) // 2
    DP = [0] * (N + 1)
    DP[0] = 1
    for i in range(0, N - 1):
        for j in range(1, mid + 1):
            if i + j >= N: break
            x = j * (1 + abs(arr[i] - arr[i + j]))
            if x <= mid:
                DP[i + j] |= DP[i]
    if DP[N - 1]:
        R = mid - 1
    else:
        L = mid + 1
print(L)