import sys
from collections import defaultdict, deque

def input():
    return sys.stdin.readline().rstrip()

N, M, K = map(int, input().split())
V = list(map(int, input().split()))
ind = [0] * N
G = defaultdict(list)
ans = 2 ** 63

for i in range(M):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    G[u].append(v)
    ind[v] += 1

def getLastNode():
    ind2 = list(ind)
    Q = deque([0])
    lastNode = 0
    while Q:
        cur = Q.popleft()
        lastNode = cur
        for nxt in G[cur]:
            ind2[nxt] -= 1
            if ind2[nxt] == 0:
                Q.append(nxt)
    return lastNode

def go():
    ind2 = list(ind)
    DP = [0] * N
    Q = deque([0])
    DP[0] = V[0]
    while Q:
        cur = Q.popleft()
        lastNode = cur
        for nxt in G[cur]:
            ind2[nxt] -= 1
            if ind2[nxt] == 0:
                Q.append(nxt)
            DP[nxt] = max(DP[nxt], DP[cur] + V[nxt])
    return max(DP)

def select(idx, cnt, lastNode):
    global ans
    if cnt == K:
        ans = min(ans, go())
        return
    
    for i in range(idx, N):
        if i == lastNode: continue
        tmp, V[i] = V[i], 0
        select(i + 1, cnt + 1, lastNode)
        V[i] = tmp

select(1, 0, getLastNode())
print(ans)