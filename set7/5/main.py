import sys
from collections import deque, defaultdict

def input():
    return sys.stdin.readline().rstrip()

def BFS(S):
    ret = [0, 0]
    Q = deque([(S, 0)])
    used = [0] * (N + 1)
    used[S] = 1
    while Q:
        cur, d = Q[0]
        Q.popleft()
        if ret[0] < d:
            ret = [d, cur]
        for nxt in tree[cur]:
            if used[nxt]: continue
            used[nxt] = True
            Q.append((nxt, d + 1))
    return ret

N = int(input())
tree = defaultdict(list)

circle = []
for i in range(N):
    x, y, r = map(int, input().split())
    circle.append((r, x, y, i + 1))
circle = sorted(circle)
circle.append((1000000000, 0, 0, 0))
for i in range(N + 1):
    for j in range(i + 1, N + 1):
        ar, ax, ay, aidx = circle[i]
        br, bx, by, bidx = circle[j]
        mr, d = ar - br, (ax - bx) * (ax - bx) + (ay - by) * (ay - by)
        if (d == 0 and mr != 0) or d < mr * mr:
            tree[aidx].append(bidx)
            tree[bidx].append(aidx)
            break

print(BFS(BFS(0)[1])[0])