import sys
from collections import deque

def input():
    return sys.stdin.readline().strip()

N, M = map(int, input().split())
Map = [[], [], []] #R, G, B
Map2 = [[0 for _ in range(M)] for j in range(N)]

for i in range(N):
    line = list(map(int, input().split()))
    Map[0].append(line[0::3])
    Map[1].append(line[1::3])
    Map[2].append(line[2::3])

T = int(input())

for i in range(N):
    for j in range(M):
        color = Map[0][i][j] + Map[1][i][j] + Map[2][i][j]
        if color >= T * 3:
            Map2[i][j] = 1 

dy = (0, 0, -1, 1)
dx = (-1, 1, 0, 0)

answer = 0
for i in range(N):
    for j in range(M):
        if Map2[i][j] == 0:
            continue
        que = deque()
        que.append((i, j))
        answer += 1
        while len(que) != 0:
            y, x = que.popleft()
            for k in range(4):
                qy = y + dy[k]
                qx = x + dx[k]
                if 0 > qy or qy >= N or 0 > qx or qx >= M:
                    continue
                if Map2[qy][qx] == 1:
                    Map2[qy][qx] = 0
                    que.append((qy, qx))
print(answer)
