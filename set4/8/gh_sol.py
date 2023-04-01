import sys
import bisect
LIM = 1000000000001
INF = 8000000000000
#3 pair
arr = []
ev = {}
ev_time = []
E = []
dp = {}
q = []
for ii in range(0, 1):
    Q, n = map(int, sys.stdin.readline().split())
    #idx, time, prio
    for i in range(n):
        idx, time, prio = map(int, sys.stdin.readline().split())
        prio = prio + LIM
        arr.append((idx, time, prio))
        ev[prio-time] = 1
        ev[prio] = 1
    arr = sorted(arr, key=lambda k: k[0])
    ev_time = sorted(list(ev))
    E = [0] * len(ev_time)
    for i in range(n):
        idx, time, prio = arr[i]
        s = prio - time
        e = prio
        lo_s = bisect.bisect_left(ev_time, s)
        lo_e = bisect.bisect_left(ev_time, e)
        E[lo_s] = E[lo_s] - 1
        E[lo_e] = E[lo_e] + 1
    tot = 0
    for i in range(len(ev_time)-1,-1,-1):
        tot = tot + E[i]
        E[i] = tot
    
    for i in range(Q):
        t = int(sys.stdin.readline()) - 1
        te = 0
        v = []
        for lo in range(len(ev_time)-1,0,-1):
            gugan = ev_time[lo] - ev_time[lo-1]
            temp = t - gugan * E[lo]
            if temp < 0:
                who = t % E[lo]
                for j in range(n):
                    idx, time, prio = arr[j]
                    s = prio - time
                    e = prio
                    if e <= ev_time[lo-1] or ev_time[lo] <= s:
                        continue
                    v.append(idx)
                print(v[who])
                break
            t = temp