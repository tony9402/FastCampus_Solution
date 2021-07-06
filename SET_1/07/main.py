import sys

def input():
    return sys.stdin.readline()

N = int(input())
arr = list(map(int, input().split()))

ans = 0
idx = 0
while idx < N:
    found = False
    for j in range(idx+1, N, 2):
        pali = True
        L = idx
        R = j
        while L <= R:
            if arr[L] != arr[R]:
                pali = False
                break
            L += 1
            R -= 1
        if pali:
            found = True
            idx = j - 1
            break
    if found:
        ans += 1
    else:
        ans = -1
        break
    idx += 2

print(ans)

