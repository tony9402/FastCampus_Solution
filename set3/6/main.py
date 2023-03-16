import sys
from collections import defaultdict, deque

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
files, folders = defaultdict(set), defaultdict(set)

for i in range(N + M):
    P, F, C = input().split()
    if C == '1':
        folders[P].add(F)
    else:
        files[P].add(F)

K = int(input())
for i in range(K):
    full_src, full_dst = input().split()
    par, src = full_src.split('/')[-2:]
    dst = full_dst.split('/')[-1]
    if src in folders:
        for folder in folders[src]:
            folders[dst].add(folder)
        folders.pop(src)
    if src in files:
        for file in files[src]:
            files[dst].add(file)
        files.pop(src)
    folders[par].remove(src)

Q = int(input())
for i in range(Q):
    src = input().split('/')[-1]
    file_set, file_count = set(), 0
    dq = deque([src])
    while dq:
        cur = dq.popleft()
        if cur in folders:
            for nxt in folders[cur]:
                dq.append(nxt)
        if cur in files:
            for nxt in files[cur]:
                file_set.add(nxt)
                file_count += 1
    print(f"{len(file_set)} {file_count}")