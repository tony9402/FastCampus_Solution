import sys
import heapq

def input():
    return sys.stdin.readline().rstrip()

mx_group_heap, mn_group_heap = [[] for i in range(101)], [[] for i in range(101)]
mx_level_heap, mn_level_heap = [[] for i in range(101)], [[] for i in range(101)]
mx_heap, mn_heap = [], []
level, group = [0] * 100001, [0] * 100001

def recommend(g, x):
    while len(mx_group_heap[g]):
        l, p = mx_group_heap[g][0]
        if level[-p] == -l and group[-p] == g: break
        heapq.heappop(mx_group_heap[g])
    while len(mn_group_heap[g]):
        l, p = mn_group_heap[g][0]
        if level[p] == l and group[p] == g: break
        heapq.heappop(mn_group_heap[g])
    print(-mx_group_heap[g][0][1] if x == 1 else mn_group_heap[g][0][1])

def recommend2(x):
    while len(mx_heap):
        l, p = mx_heap[0]
        if level[-p] == -l: break
        heapq.heappop(mx_heap)
    while len(mn_heap):
        l, p = mn_heap[0]
        if level[p] == l: break
        heapq.heappop(mn_heap)
    print(-mx_heap[0][1] if x == 1 else mn_heap[0][1])

def recommend3(x, l):
    if x == 1:
        for i in range(l, 102):
            if i == 101: 
                print(-1)
                break
            while len(mn_level_heap[i]):
                p = mn_level_heap[i][0]
                if level[p] == i:
                    break
                heapq.heappop(mn_level_heap[i])
            if len(mn_level_heap[i]):
                print(mn_level_heap[i][0])
                break
    else:
        for i in range(l - 1, -1, -1):
            if i == 0:
                print(-1)
                break
            while len(mx_level_heap[i]):
                p = mx_level_heap[i][0]
                if level[-p] == i:
                    break
                heapq.heappop(mx_level_heap[i])
            if len(mx_level_heap[i]):
                print(-mx_level_heap[i][0])
                break

def add(p, l, g):
    heapq.heappush(mx_heap, (-l, -p))
    heapq.heappush(mn_heap, (l, p))
    heapq.heappush(mx_group_heap[g], (-l, -p))
    heapq.heappush(mn_group_heap[g], (l, p))
    heapq.heappush(mx_level_heap[l], -p)
    heapq.heappush(mn_level_heap[l], p)
    level[p], group[p] = l, g

def solved(p):
    level[p], group[p] = 0, 0

N = int(input())
for i in range(N):
    P, L, G = map(int, input().split())
    add(P, L, G)
Q = int(input())
for i in range(Q):
    cmd, *args = input().split()
    args = list(map(int, args))
    if cmd == 'solved':
        solved(args[0])
    elif cmd == 'recommend':
        recommend(args[0], args[1])
    elif cmd == 'recommend2':
        recommend2(args[0])
    elif cmd == 'recommend3':
        recommend3(args[0], args[1])
    elif cmd == 'add':
        add(args[0], args[1], args[2])