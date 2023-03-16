N, K = map(int, input().split())
DP = [[0 for j in range(N + 1)] for i in range(K + 1)]

arr = list(map(int, input().split()))
for i in range(1, N + 1):
    x = arr[i - 1]
    if x % 2 == 0: DP[0][i] = DP[0][i - 1] + 1
    for j in range(1, K + 1):
        if x % 2 == 0:
            DP[j][i] = DP[j][i - 1] + 1
        else:
            DP[j][i] = DP[j - 1][i - 1]

print(max(DP[K][1:N+1]))