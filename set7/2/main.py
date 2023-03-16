import sys

def input():
    return sys.stdin.readline().rstrip()

MAXN = 100000
is_prime = [True] * MAXN
Prime = []
K, M = map(int, input().split())

is_prime[0] = is_prime[1] = False
for i in range(2, MAXN):
    if is_prime[i] == False: continue
    Prime.append(i)
    for j in range(i * i, MAXN, i): is_prime[j] = False

def check_first(x):
    for p in Prime:
        if x - p < 0: break
        if x - p != p and is_prime[x - p]: return True
    return False

def check_second(x):
    while x % M == 0: x //= M
    for p in Prime:
        if p * p > x: break
        if x % p: continue
        if is_prime[x // p]: return True
    return False

def dfs(cnt, bit, x):
    ret = 0
    if cnt == K:
        if check_first(x) and check_second(x): ret += 1
        return ret
    
    for i in range(0 if cnt else 1, 10):
        if bit & (1 << i): continue
        ret += dfs(cnt + 1, bit | (1 << i), x * 10 + i)
    return ret

print(dfs(0, 0, 0))