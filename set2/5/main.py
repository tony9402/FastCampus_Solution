import sys

def input():
    return sys.stdin.readline().rstrip()

S = input()
L = len(S)
n = int(input())

DP = [0 for i in range(L + 1)]
ava = [[] for i in range(L)]
inputList = [input().split() for _ in range(n)]
for Str, Score in inputList:
    Score = int(Score)
    if len(Str) >= Score:
        continue
    
    idx = 0
    while idx < L:
        cur = S.find(Str, idx)
        if cur == -1:
            break
        ava[cur].append((len(Str), Score))
        idx = cur + 1

for i in range(L):
    for j in range(len(ava[i])):
        siz, score = ava[i][j]
        DP[i + siz] = max(DP[i + siz], DP[i] + score)
    DP[i + 1] = max(DP[i + 1], DP[i] + 1)

print(DP[L])