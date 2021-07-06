import sys

def input():
    return sys.stdin.readline().rstrip()

def isPrime(x):
    if x <= 1:
        return False
    for i in range(2, x):
        if i * i > x:
            break
        if x % i == 0:
            return False
    return True

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def lcm(a, b):
    return a // gcd(a, b) * b

N = int(input())
V = list(map(int, input().split()))
V = [ _ for _ in V if isPrime(_) ]

if len(V) == 0:
    print(-1)
    exit(0)

ans = 1
for i in V:
    ans = lcm(ans, i)

print(ans)
