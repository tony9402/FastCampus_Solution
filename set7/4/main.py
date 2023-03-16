import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
arr = list(map(int, input().split()))
L, R = 0, N - 1
ans = 0
while L < R:
    ans = max(ans, (R - L - 1) * min(arr[L], arr[R]))
    if arr[L] <= arr[R]: L += 1
    else: R -= 1
print(ans)