import time
import bisect
import sys


# strptime is too low
# def str_to_int(tar):
#    from_1970 = time.strptime(tar, '%Y-%m-%d %H:%M:%S')
#    return int(time.mktime(from_1970))


def calc(x1, y1, y2, nx):
    return nx[6][y2] - nx[x1 - 1][y2] - nx[6][y1 - 1] + nx[x1 - 1][y1 - 1]


n, Q = map(int, sys.stdin.readline().split())
ev = []
qu = []
time_set = [""]
arr = [[0 for _ in range(2 * Q + n + 1)] for _ in range(7)]
nj = [[0 for _ in range(2 * Q + n + 1)] for _ in range(7)]
for i in range(n):
    it = sys.stdin.readline().split('#')
    lv = int(it[1])
    ev.append((str(it[0]), lv))
    time_set.append(str(it[0]))
for i in range(Q):
    it = sys.stdin.readline().split('#')
    lv = int(it[2])
    qu.append((str(it[0]), str(it[1]), lv))
    time_set.append(str(it[0]))
    time_set.append(str(it[1]))
time_set = sorted(time_set)
for i in range(n):
    time, lv = ev[i]
    arr[lv][bisect.bisect_left(time_set, time)] += 1
for i in range(1, 7):
    for j in range(1, n + 2 * Q + 1):
        nj[i][j] = nj[i - 1][j] + nj[i][j - 1] - nj[i - 1][j - 1] + arr[i][j]
for i in range(Q):
    time_s, time_e, lv = qu[i]
    time_s = bisect.bisect_left(time_set, time_s)
    time_e = bisect.bisect_left(time_set, time_e)
    ans = calc(lv, time_s, time_e, nj)
    sys.stdout.write(f'{ans}\n')
