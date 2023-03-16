import sys

def input():
    return sys.stdin.readline().rstrip()

def func(for_range):
    stack = []
    for i in for_range:
        while stack and H[stack[-1]] <= H[i]:
            stack.pop()
        if stack and (not building[i] or abs(stack[-1] - i) < abs(building[i] - i)):
            building[i] = stack[-1]
        cnt[i] += len(stack)
        stack.append(i)

N = int(input())
H = [0] * (N + 1)
H[1:N+1]=list(map(int,input().split()))
cnt = [0] * (N + 1)
building = [0] * (N + 1)

func(range(1, N + 1, 1))
func(range(N, 0, -1))

for i in range(1, N + 1):
    if cnt[i]:
        print(cnt[i], building[i])
    else:
        print(0)