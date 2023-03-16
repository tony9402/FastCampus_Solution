import sys

def input():
    return sys.stdin.readline().rstrip()

def gcd(a: int, b: int) -> int:
    return a if b == 0 else gcd(b, a % b)

def lcm(a: int, b: int) -> int:
    return a // gcd(a, b) * b

def check_prime(num: int) -> bool:
    if num < 2: return False
    x = 2
    while x * x <= num:
        if num % x == 0: return False
        x += 1
    return True
    
N = int(input())
arr = [x for x in map(int, input().split()) if check_prime(x)]

if len(arr) == 0:
    print(-1)
else:
    ans = 1
    for x in arr:
        ans = lcm(ans, x)
    print(ans)