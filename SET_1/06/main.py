import sys

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())

par = [ -1 for i in range(N + 1) ]
arr = [ ]

def find(x):
    if par[x] < 0:
        return x
    par[x] = find(par[x])
    return par[x]

def merge(a, b):
    a, b = find(a), find(b)
    if a == b:
        return False
    par[b] = a
    return True

answer = 0
for i in range(M):
    a, b, c = map(int, input().split())
    arr.append((c, a, b))
    answer += c

arr.sort()

connect = 0
for i in range(M):
    c, a, b = arr[i]
    if merge(a, b):
        answer -= c
        connect += 1

if connect != N - 1:
    answer = -1

print(answer)
