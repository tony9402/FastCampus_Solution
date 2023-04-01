import sys
T = int(sys.stdin.readline())
li = map(int, sys.stdin.readline().split())
s = [0x3f3f3f3f] * 1000001
e = [0] * 1000001
prev = -1
ti = 1
for cur_process in li:
    if prev >= cur_process:
        ti = ti + 1
    s[cur_process] = min(s[cur_process], ti)
    e[cur_process] = max(e[cur_process], ti)
    prev = cur_process
pn = 0
ret = [k for k in range(1000001) if e[k] != 0]
sys.stdout.write(f'{len(ret)}\n')
for i in ret:
    sys.stdout.write(f'{i} {e[i] - s[i] + 1} {1000000 - s[i]}\n')