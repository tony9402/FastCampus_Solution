import sys
from collections import defaultdict, deque

def input():
    return sys.stdin.readline().rstrip()

N, M = list(map(int, input().split()))
files, folders = defaultdict(set), defaultdict(set)

for i in range(N + M):
    P, F, C = input().split()
    if C == '1':
        folders[P].add(F)
    else:
        files[P].add(F)

Q = int(input())
for i in range(Q):
    src = input().split("/")[-1]
    file_set, file_count = set(), 0
    dq = deque([src])
    while dq:
        cur = dq.popleft()
        if cur in folders:
            for nxt in folders[cur]: dq.append(nxt)
        if cur in files:
            for nxt in files[cur]:
                file_set.add(nxt)
                file_count += 1
    print(f"{len(file_set)} {file_count}")