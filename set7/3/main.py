import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

N, H, D = map(int, input().split())
point = []
S, E = (0, 0), (0, 0)
used = [0] * 15
INF = 2 ** 30
ans = INF

for i in range(N):
    line = input()
    for j in range(N):
        if line[j] == 'S': S = (i, j)
        elif line[j] == 'E': E = (i, j)
        elif line[j] == 'U': point.append((i, j))

def dfs(y, x, h, d, t):
    global ans
    to_end = abs(y - E[0]) + abs(x - E[1])
    if h + d - to_end >= 0:
        ans = min(ans, t + to_end)
        return
    for i in range(len(point)):
        if used[i]: continue
        ny, nx = point[i]
        dis = abs(ny - y) + abs(nx - x)
        if h + d - dis < 0: continue
        used[i] = 1
        nxtH = h - max(0, dis - d - 1)
        dfs(ny, nx, nxtH, D - 1, t + dis)
        used[i] = 0

dfs(S[0], S[1], H, 0, 0)
if ans == INF:
    ans = -1
print(ans)