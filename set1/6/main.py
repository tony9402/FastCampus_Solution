import sys

def input():
    return sys.stdin.readline().rstrip()

INT_MIN = -2 ** 30
N, M = map(int, input().split())
arr = [[0 for j in range(M + 3)] for i in range(N + 3)]
DP = [[[INT_MIN for j in range(M + 3)] for i in range(N + 3)] for k in range(2)]

for i in range(1, N + 1): arr[i][1:N+1]=list(map(int,input().split()))

DP[0][N][1], DP[1][N][M] = arr[N][1], arr[N][M]
for i in range(N, 0, -1):
    for j in range(1, M + 1, 1):
        if i == N and j == 1: continue
        DP[0][i][j] = max(DP[0][i + 1][j], DP[0][i][j - 1]) + arr[i][j]

for i in range(N, 0, -1):
    for j in range(M, 0, -1):
        if i == N and j == M: continue
        DP[1][i][j] = max(DP[1][i + 1][j], DP[1][i][j + 1]) + arr[i][j]

ans = INT_MIN
for i in range(1, N + 1):
    for j in range(1, M + 1):
        ans = max(ans, DP[0][i][j] + DP[1][i][j])
print(ans)