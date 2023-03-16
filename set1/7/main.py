import sys

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())

par = [-1 for i in range(N + 1)]
def find(x):
    if par[x] < 0:
        return x
    par[x] = find(par[x])
    return par[x]

def merge(u, v):
    u, v = find(u), find(v)
    if u == v:
        return False
    par[v] = u
    return True

edges = []
ans = 0
for i in range(M):
    u, v, w = map(int, input().split())
    edges.append((w, u, v))
    ans += w
edges = sorted(edges)

for w, u, v in edges:
    if merge(u, v):
        ans -= w

for i in range(2, N + 1):
    if find(1) != find(i):
        ans = -1

print(ans)