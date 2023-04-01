import sys
import collections
wait_queue = collections.deque()
my_input = sys.stdin.readline
my_print = sys.stdout.write
n, t, w = map(int, my_input().split(' '))
for i in range(n):
    idx, need_time = map(int, my_input().split(' '))
    wait_queue.append([idx, need_time, 0])
m = int(my_input())
ev = {}
for i in range(m):
    idx, need_time, come_time = map(int, my_input().split(' '))
    ev[come_time] = [idx, need_time, 0]
for ti in range(w):
    if ti in ev:
        wait_queue.append(ev[ti])
    if wait_queue[0][1] == 0:
        wait_queue.popleft()
    elif wait_queue[0][2] == t:
        wait_queue[0][2] = 0
        front = wait_queue.popleft()
        wait_queue.append(front)
    else:
        pass
    wait_queue[0][1] -= 1
    wait_queue[0][2] += 1
    my_print(f'{wait_queue[0][0]}\n')
