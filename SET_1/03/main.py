import sys

def input():
    return sys.stdin.readline().rstrip()

N, X = map(int, input().split(" "))
arr = list(map(int, input().split(" ")))
prefix = [ 0 ] * (N + 1)

for i in range(N):
    prefix[i + 1] = prefix[i] + arr[i]

L, R = 1, X

ans = 0
answer = 0

while R <= N:
    S = prefix[R] - prefix[L - 1]
    if ans == S:
        answer += 1
    elif ans < S:
        ans = S
        answer = 1
    L += 1
    R += 1

if ans == 0:
    print("SAD")
else:
    print(ans)
    print(answer)

