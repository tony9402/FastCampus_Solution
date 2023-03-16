import sys
import heapq
from collections import defaultdict

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
A, B, C = map(int, input().split())
M = int(input())

G = defaultdict(list)
for i in range(M):
    u, v, w = map(int, input().split())
    G[u].append((v, w))
    G[v].append((u, w))

INF = 2 ** 30
dist = [INF] * (N + 1)

pq = []
for x in [A, B, C]:
    dist[x] = 0
    heapq.heappush(pq, (0, x))

while pq:
    d, cur = heapq.heappop(pq)
    if dist[cur] != d: continue
    for nxt, cost in G[cur]:
        if dist[nxt] > dist[cur] + cost:
            dist[nxt] = dist[cur] + cost
            heapq.heappush(pq, (dist[nxt], nxt))

mx = max(dist[1:N+1])
print(dist.index(mx))