import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
arr = list()

for i in range(N):
    x, r = map(int, input().split())
    arr.append((x - r, i, 0))
    arr.append((x + r, i, 1))

arr = sorted(arr)
stack = list()

pre_x = -1000001
answer = "YES"
for x, idx, ch in arr:
    if x == pre_x:
        answer = "NO"
        break
    if ch == 0:
        stack.append(idx)
    else:
        if stack and stack[-1] == idx:
            stack.pop()
        else:
            answer = "NO"
            break
    pre_x = x

if stack:
    answer = "NO"

print(answer)