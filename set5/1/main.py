import sys

def input():
    return sys.stdin.readline().rstrip()

def go(t, a, s):
    ret = 0
    if t == 24:
        return s
    if a + A <= M:
        ret = max(ret, go(t + 1, a + A, s + B))
    ret = max(ret, go(t + 1, max(0, a - C), s))
    return ret

A, B, C, M = map(int, input().split())
print(go(0, 0, 0))