import sys
import itertools


def work(st, op):
    for query in op:
        q, idx = query.split()
        if q == 'ADD':
            st = st + idx
        else:
            it = int(idx)
            if it >= len(st):
                st = 'ERROR'
                return st
            te = st[:it] + st[it+1:]
            st = te
    return st


pe = []
ca = [('', '')]
ans = {}
n, c = map(int, sys.stdin.readline().split())
for i in range(n):
    ix = tuple(map(int, sys.stdin.readline().split()))
    pe.append(list(ix[1:]))
for i in range(c):
    ix = tuple(sys.stdin.readline().replace('\n', '').split(','))
    ca.append(ix)
perm = []
for i in range(n):
    for j in range(len(pe[i])):
        perm.append(i)
for p in itertools.permutations(perm):
    pos = [0] * 15
    oper = []
    for i in range(len(p)):
        pn = p[i]
        oper.append(pe[pn][pos[pn]])
        pos[pn] = pos[pn] + 1

    tar = ''
    for i in oper:
        tar = work(tar, ca[i])
        if tar == 'ERROR':
            break
    if tar == '':
        tar = 'EMPTY'
    ans[tar] = 1
ret = sorted(list(ans))

for t in ret:
    sys.stdout.write(f'{t}\n')
