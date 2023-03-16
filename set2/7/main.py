import sys

def input():
    return sys.stdin.readline().rstrip()


N = int(input())
arr = list(map(int, input().split()))
choose = [0 for _ in range(N)]
used = [0 for _ in range(N)]
operator = [0 for _ in range(N)]
curList = [0 for _ in range(N)]
answer = 0
P, Q = map(int, input().split())

def dfs2(cnt):
    global P, Q, answer
    if cnt == N - 1:
        idx = 0
        curList[idx] = choose[0]
        idx += 1
        for i in range(1, N):
            if operator[i - 1] == 1:
                curList[idx] = choose[i]
                idx += 1
            else:
                curList[idx - 1] += choose[i]

        cur = 1
        for i in range(idx):
            cur *= curList[i]
        answer = max(answer, cur)
        return

    if P:
        P -= 1
        operator[cnt] = 0
        dfs2(cnt + 1)
        P += 1

    if Q:
        Q -= 1
        operator[cnt] = 1
        dfs2(cnt + 1)
        Q += 1

def dfs(cnt):
    if cnt == N:
        dfs2(0)
        return

    for i in range(N):
        if used[i]:
            continue
        used[i] = 1
        choose[cnt] = arr[i]
        dfs(cnt + 1)
        used[i] = 0

dfs(0)
print(answer)