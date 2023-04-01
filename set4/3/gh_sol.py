import heapq
import sys
T, n = map(int, sys.stdin.readline().split())
pq = []
for i in range(n):
    idx, time, prio = map(int, sys.stdin.readline().split())
    heapq.heappush(pq, (-prio, idx, time))
for i in range(T):
    prio, idx, time = heapq.heappop(pq)
    time = time - 1
    prio = prio + 1
    sys.stdout.write(f'{idx}\n')
    if time != 0:
        heapq.heappush(pq, (prio, idx, time))
