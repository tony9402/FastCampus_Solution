import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
circle = []
for i in range(N):
    k, x, r = map(int, input().split())
    circle.append((r, x, k))
circle = sorted(circle)
circle.append((1000000000, 0, 0))

A, B = map(int, input().split())
idxA, idxB = 0, 0
while idxA <= N and circle[idxA][2] != A: idxA += 1
while idxB <= N and circle[idxB][2] != B: idxB += 1

L, R = [A], [B]
visited = [0] * 200005
visited[A] = True

ax, ar = circle[idxA][1], circle[idxA][0]
bx, br = circle[idxB][1], circle[idxB][0]
for i in range(idxA + 1, N + 1):
    cr, cx, ck = circle[i]
    if cr == ar: continue
    if abs(ax - cx) > abs(ar - cr): continue
    ax, ar = cx, cr
    if ck == B:
        break
    L.append(ck)
    visited[ck] = True
for i in range(idxB + 1, N + 1):
    cr, cx, ck = circle[i]
    if cr == br: continue
    if abs(bx - cx) > abs(br - cr): continue
    bx, br = cx, cr
    if visited[ck]:
        while L[-1] != ck: L.pop(-1)
        break
    R.append(ck)
    
print(len(L) + len(R))
print(" ".join(map(str, L)), end=' ')
print(" ".join(map(str, R[::-1])), end='')