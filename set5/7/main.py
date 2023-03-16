import sys
import heapq
from collections import defaultdict

def input():
    return sys.stdin.readline().rstrip()

INF = 2 ** 62

def dijkstra(S):
    dist = [INF] * (N + 1)
    pq = [(0, S)]
    dist[S] = 0
    while pq:
        d, cur = heapq.heappop(pq)
        if dist[cur] != d: continue
        for nxt, cost in G[cur]:
            if used[nxt]: continue
            if dist[nxt] > dist[cur] + cost:
                dist[nxt] = dist[cur] + cost
                heapq.heappush(pq, (dist[nxt], nxt))
    return dist

def eraseEdge(S, E):
    pre = S
    while S != E:
        mn = 2 ** 30
        for nxt, cost in G[S]:
            if nxt == pre: continue
            if distS[S] + cost + distE[nxt] == distS[E]: mn = min(mn, nxt)
        pre = S
        S = mn
        if S != E: used[S] = 1

N, M = map(int, input().split())
G = defaultdict(list)
used = [0] * (N + 1)
for i in range(M):
    u, v, w = map(int, input().split())
    G[u].append((v, w))
    G[v].append((u, w))
S, E = map(int, input().split())

distS, distE = dijkstra(S), dijkstra(E)
eraseEdge(S, E)
print(distS[E] + dijkstra(E)[S])