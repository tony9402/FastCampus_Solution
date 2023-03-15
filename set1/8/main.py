import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
arr = list(map(int, input().split()))

ans = 0

i, j = 0, 0
while i < N:
    flag = False
    j = i + 1
    while j < N:
        l, r = i, j
        is_palindrome = True
        while is_palindrome and l < r:
            if arr[l] != arr[r]: is_palindrome = False
            l += 1
            r -= 1
        if is_palindrome:
            ans += 1
            flag = True
            break
        j += 2
    if not flag:
        ans = -1
        break
    i = j + 1
print(ans)
