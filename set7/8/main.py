import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

def region(y, x, k):
    y, x = y // 4 * 4, x // 4 * 4
    return y * k + x

def inrange(y, x, N):
    return 0 <= y and y < N and 0 <= x and x < N

def next_pos(y, x):
    by, bx = y // 4 * 4, x // 4 * 4
    y, x = y % 4, x % 4
    return x + by, 3 - y + bx

Dy = (-1,1,0,0)
Dx = (0,0,-1,1)

K = int(input())
N = 4*K
Map = [list(input()) for i in range(N)]
Rotate_Map = [[['.' for j in range(N)] for i in range(N)] for k in range(4)]
dist = [[[-1 for j in range(N)] for i in range(N)] for k in range(4)]
start_point, end_point = (0, 0), (0, 0)

for i in range(N):
    for j in range(N):
        if Map[i][j] == 'S':
            start_point = (i, j)
            Map[i][j] = '.'
        if Map[i][j] == 'E':
            end_point = (i, j)
            Map[i][j] = '.'

for regiony in range(K):
    for regionx in range(K):
        y, x = regiony * 4, regionx * 4
        tmp = []
        for h in range(4): tmp.append(Map[y + h][x:x+4])
        for cnt in range(4):
            for hy in range(4):
                for hx in range(4):
                    Rotate_Map[cnt][y + hy][x + hx] = tmp[hy][hx]
            tmp = [list(line) for line in list(zip(*tmp[::-1]))]

Q = deque([(0, *start_point)])
sy, sx = start_point
dist[0][sy][sx] = 0
while Q:
    turn, y, x = Q.popleft()
    current_region = region(y, x, K)

    for dy, dx in zip(Dy, Dx):
        qy, qx = dy + y, dx + x
        next_region = region(qy, qx, K)
        if not inrange(qy, qx, N): continue
        if next_region == current_region:
            if Rotate_Map[turn][qy][qx] == '#': continue
            qy, qx = next_pos(qy, qx)
            next_turn = (turn + 1) % 4
            if dist[next_turn][qy][qx] != -1: continue
            dist[next_turn][qy][qx] = dist[turn][y][x] + 1
            Q.append((next_turn, qy, qx))
        else:
            if Rotate_Map[0][qy][qx] == '#': continue
            qy, qx = next_pos(qy, qx)
            if dist[1][qy][qx] != -1: continue
            dist[1][qy][qx] = dist[turn][y][x] + 1
            Q.append((1, qy, qx))
    
    next_turn, qy, qx = (turn + 1) % 4, y, x
    qy, qx = next_pos(qy, qx)
    if dist[next_turn][qy][qx] == -1:
        dist[next_turn][qy][qx] = dist[turn][y][x] + 1
        Q.append((next_turn, qy, qx))

ans = -1
y, x = end_point
for k in range(4):
    value = dist[k][y][x]
    y, x = next_pos(y, x)
    if value == -1: continue
    if ans == -1: ans = value
    elif ans > value: ans = value

print(ans)