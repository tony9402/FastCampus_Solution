import sys

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
INF = 2 ** 30
dist = [[INF for j in range(N + 1)] for i in range(N + 1)]

for i in range(N + 1):
    dist[i][i] = 0
for i in range(M):
    u, v, w = map(int, input().split())
    dist[u][v] = w
for k in range(1, N + 1):
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if dist[i][j] > dist[i][k] + dist[k][j]:
                dist[i][j] = dist[i][k] + dist[k][j]

K = int(input())
friends = list(map(int, input().split()))

mx = INF
answer = []
for i in range(1, N + 1):
    cur = 0
    for j in range(K):
        cur = max(cur, dist[friends[j]][i] + dist[i][friends[j]])
    if cur >= INF:
        cur = INF
    if mx > cur:
        answer = [i]
        mx = cur
    elif mx == cur:
        answer.append(i)
print(' '.join(map(str,answer)))