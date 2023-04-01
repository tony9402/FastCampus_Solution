dp = [0] * 20002
n, M = map(int, input().split(' '))
dp[0] = dp[2] = 1
for i in range(4, n, 2):
    dp[i] = dp[i-2]
    for j in range(2, i, 2):
        dp[i] = dp[i] + dp[j] * dp[i-j-2]
    dp[i] %= M
print(dp[n-2])