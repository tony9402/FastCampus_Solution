import sys

def input():
    return sys.stdin.readline().rstrip()

N, K = map(int, input().split())

go        = [-1] * (N + 1)
idx       = [0] * (N + 1)
arr       = [0] * (N + 1)
tmp       = [0] * (N + 1)
index_arr = [0] * (N + 1)
numbering = [0] * (N + 1)
V         = [[] for i in range(N + 1)]

arr[1:N+1] = list(map(int, input().split()))
idx[1:N+1] = list(map(int, input().split()))

for i in range(1, N + 1):
    if go[i] == -1:
        cur, number = i, 1
        while True:
            go[cur] = number
            index_arr[cur] = i 
            V[i].append(cur)
            if go[idx[cur]] != -1: break
            cur = idx[cur]
            number += 1
    siz  = len(V[index_arr[i]])
    T    = K % siz
    Vidx = (go[i] + T - 1) % siz
    pre  = V[index_arr[i]][Vidx]
    tmp[pre] = arr[i]
print(" ".join(map(str, tmp[1:N+1])))