import sys
from collections import defaultdict
my_input = sys.stdin.readline
sys.setrecursionlimit(100000)
n, c = map(int, my_input().split(' '))
ret_list = list(map(int, my_input().split(' ')))
turn = [[] for _ in range(20001)]
card = [0 for _ in range(c+1)]
for i in range(1, n+1):
    turn[i] = list(map(int, my_input().split(' ')))[1:]
    # turn[i].append(10**9 + i)
for i in range(1, c+1):
    card[i] = int(my_input()[4:])
for i in range(1, c+1):
    for j in range(len(turn[i])):
        card_num = turn[i][j]
        turn[i][j] = card[card_num]
for i in range(1, c+1):
    turn[i].append(10**9 + i)
md = defaultdict(list)
pos = [0] * (n+1)
for i in range(1, n+1):
    md[turn[i][0]].append(i)
ans = [0 for _ in range(c)]


def dfs(depth):
    if depth == c:
        s = " ".join(map(str, ans))
        print(s)
        exit(0)
    key = ret_list[depth]
    # 현재 md에 key 값이 k일 때 어떤 people 이 올 수 있는지 적혀져 있음.
    if key not in md:
        return
    people_list = list(md[key])
    for p in people_list:
        ans[depth] = p
        # (1) turn[p][pos[p]] :: p del
        k, v = turn[p][pos[p]], p
        md[k].remove(v)

        pos[p] = pos[p] + 1
        # (2) turn[p][pos[p]] :: p add
        k, v = turn[p][pos[p]], p
        md[k].append(v)
        dfs(depth + 1)
        # (3) turn[p][pos[p]] :: p del
        k, v = turn[p][pos[p]], p
        md[k].remove(v)

        pos[p] = pos[p] - 1
        # (4) turn[p][pos[p]] :: p add
        k, v = turn[p][pos[p]], p
        md[k].append(v)


dfs(0)
print('-1')