import sys

def input():
    return sys.stdin.readline().rstrip()

N, K = map(int, input().split())
V = list(map(int, input().split()))
DP = [0] * (N)
DP[0] = 1
for i in range(1, N):
    for j in range(1, K + 1):
        if i - j < 0: break
        x = j * (1 + abs(V[i] - V[i - j]))
        if x <= K:
            DP[i] |= DP[i - j]
print("YES" if DP[N - 1] else "NO")