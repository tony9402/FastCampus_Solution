import sys
my_input = sys.stdin.readline
special = {
    210: 3,
    220: 4,
    221: 4,
    222: 3,
    225: 3,
    238: 3,
    245: 4,
    247: 5,
    249: 4,
    250: 3,
    256: 3,
    266: 3
}
hh, mm, ss = map(int, my_input().split(':'))
n = int(my_input())
train = []
for i in range(n):
    s, e, ti = my_input().split(' ')
    sec = int(ti.split(":")[0]) * 60 * 60 + int(ti.split(":")[1]) * 60
    train.append(tuple([int(s[1:]), int(e[1:]), sec]))
cur_sec = 3600*hh + 60*mm + ss
arr = [0] * 300
nj = [0] * 300
for t in range(1, 300):
    if t in special:
        arr[t] = special[t] * 60 + 20
    else:
        arr[t] = 2 * 60 + 20
for t in range(1, 300):
    nj[t] = nj[t-1] + arr[t]


def get_duration(station_s, station_e):
    return nj[station_e-1] - nj[station_s-1]


stop = [225, 233, 246, 258, 272]
for i in range(0, 4):
    cur_station = stop[i]
    next_station = stop[i+1]
    mmn_time = 0x3f3f3f3f
    for t in train:
        ts, te = t[0], t[1]
        if te <= cur_station or cur_station < ts:
            continue
        time_start = t[2]
        leave_time = time_start + get_duration(ts, cur_station)
        if cur_sec < leave_time < mmn_time:
            mmn_time = leave_time
    if mmn_time == 0x3f3f3f3f:
        print('-1')
        exit(0)
    cur_sec = mmn_time + get_duration(cur_station, next_station) - 20
if cur_sec >= 86400:
    print('-1')
else:
    print(f'{cur_sec//3600:02d}:{(cur_sec%3600)//60:02d}:{(cur_sec%3600)%60:02d}')
