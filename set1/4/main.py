import sys

def input():
    return sys.stdin.readline().rstrip()

N, X = map(int, input().split())
arr = list(map(int, input().split()))

s = sum(arr[:X])
mx, cnt = s, 1

for i in range(X, N):
    s -= arr[i - X]
    s += arr[i]
    if mx < s:
        mx = s
        cnt = 1
    elif mx == s:
        cnt += 1

print("SAD" if mx == 0 else f"{mx} {cnt}")