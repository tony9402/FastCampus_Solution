import sys
from collections import deque

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
graph = [list() for _ in range(N + 1)]
pre = [-1 for _ in range(N + 1)]
visited = [-1 for _ in range(N + 1)]

for i in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, N + 1):
    graph[i].sort()

S, E = map(int, input().split())

def BFS(S, E):
    Q = deque([S])
    visited[S] = 0
    while Q:
        cur = Q.popleft()
        for nxt in graph[cur]:
            if visited[nxt] != -1:
                continue
            visited[nxt] = visited[cur] + 1
            pre[nxt] = cur
            Q.append(nxt)

    return visited[E]

dist1 = BFS(S, E)

visited = [-1 for _ in range(N + 1)]
cur = E

while True:
    if pre[cur] == S:
        break
    visited[pre[cur]] = 0
    cur = pre[cur]

dist2 = BFS(S, E)
answer = 0
if dist1 == -1 or dist2 == -1:
    answer = -1
else:
    answer = dist1 + dist2

print(answer)