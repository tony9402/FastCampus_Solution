import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
right_child = [-1] * (N + 1)

for i in range(N):
    cur, l, r = map(int, input().split())
    right_child[cur] = r

ans = 2 * (N - 1)
node = 1
while True:
    if right_child[node] == -1:
        break
    node = right_child[node]
    ans -= 1
print(ans)