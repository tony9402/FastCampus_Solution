import sys

def input():
    return sys.stdin.readline().rstrip()

N = int(input())
V = list(map(int, input().split(" ")))
X = int(input())

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)

Sum = 0
Cnt = 0
for i in range(len(V)):
    if gcd(V[i], X) == 1:
        Sum += V[i]
        Cnt += 1

print(f"{Sum/Cnt:.6f}")

