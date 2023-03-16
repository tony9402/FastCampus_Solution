import sys
from collections import defaultdict
# sys.setrecursionlimit(10**5)

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
graph = defaultdict(list)
for i in range(M):
    u, v = map(int, input().split())
    graph[v].append(u)
X = int(input())

visited = [0] * (N + 1)
def dfs(cur):
    visited[cur] = 1
    for nxt in graph[cur]:
        if visited[nxt] == 0:
            dfs(nxt)


dfs(X)
print(sum(visited) - 1)