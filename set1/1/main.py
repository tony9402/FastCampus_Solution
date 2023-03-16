import sys

def input():
    return sys.stdin.readline().rstrip()

N, M = map(int, input().split())
state = list(map(int, input().split()))

for i in range(M):
    command, a, b = map(int, input().split())
    a -= 1
    if command == 1:
        state[a] = b
    elif command == 2:
        for j in range(a, b):
            state[j] ^= 1
    elif command == 3:
        for j in range(a, b):
            state[j] = 0
    else:
        for j in range(a, b):
            state[j] = 1

print(*state)