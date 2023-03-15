import sys

def input():
    return sys.stdin.readline().rstrip()

def gcd(a, b):
    return a if b == 0 else gcd(b, a % b)

N = int(input())
arr = list(map(int, input().split()))
X = int(input())

arr = [x for x in arr if gcd(x, X) == 1]
print(f"{sum(arr)/len(arr):.6f}")