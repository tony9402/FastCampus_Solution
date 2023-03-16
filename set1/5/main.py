import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
Map = [list(map(int, input().split())) for i in range(N)]
Q = deque([(i, j) for i in range(N) for j in range(M) if Map[i][j] == 9])

dy, dx = (-1,1,0,0),(0,0,-1,1)

def changedir(x, dir):
    if x == 1: return dir if dir in [0, 1] else 5 - dir
    if x == 2: return 1 - dir if dir in [0, 1] else dir
    if x == 3: return 3 - dir
    if x == 4: return 4 - dir if dir in [1, 3] else 2 - dir
    return dir

def inrange(y, x):
    return 0 <= y < N and 0 <= x < M

ans = 0
trace = [[0 for j in range(M)] for i in range(N)]
while Q:
    y, x = Q.popleft()
    if ~trace[y][x] & 16:
        trace[y][x] |= 16
        ans += 1
    for i in range(4):
        dir = i
        qy, qx = y, x
        if trace[qy][qx] & (1 << dir): continue
        trace[qy][qx] |= (1 << dir)
        while True:
            qy, qx = qy + dy[dir], qx + dx[dir]
            if not inrange(qy, qx) or trace[qy][qx] & (1 << dir): break
            trace[qy][qx] |= (1 << dir)
            if ~trace[qy][qx] & 16:
                trace[qy][qx] |= 16
                ans += 1
            dir = changedir(Map[qy][qx], dir)
print(ans)