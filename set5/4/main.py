import sys

def input():
    return sys.stdin.readline().rstrip()


N = int(input())
schedule = []
for i in range(N):
    a, b = input().split()
    schedule.append((a, 1))
    schedule.append((b, -1))
schedule = sorted(schedule)

s, mx = 0, 0
for a, b in schedule:
    s += b
    mx = max(s, mx)
print(mx)