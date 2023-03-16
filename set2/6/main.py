import sys

def input():
    return sys.stdin.readline().strip()

N, limit, money = input().split(" ")
N, money = int(N), int(money)
days = [0,31,28,31,30,31,30,31,31,30,31,30,31]

def getTime(time):
    hh, mm = map(int, time.split(':'))
    return hh * 60 + mm

def getDate(date):
    year, month, day = map(int, date.split('-'))
    ret = day - 1
    for i in range(month):
        ret += days[i]
    return ret

def getTime2(date, time):
    hh, mm = map(int, time.split(':'))
    return (hh + getDate(date) * 24) * 60 + mm

def getLine():
    date, time, partname, nickname = input().split()
    return getTime2(date, time), partname, nickname

_D, _T = limit.split("/")
limit = getTime(_T) + int(_D) * 1440
DB = dict()
fine = dict()

for i in range(N):
    curTime, partname, nickname = getLine()
    
    if not nickname in DB.keys():
        DB[nickname] = dict()
        DB[nickname][partname] = curTime
    else:
        if partname in DB[nickname].keys():
            prev = DB[nickname][partname]
            dist = max(0, curTime - prev - limit)
            if not nickname in fine.keys():
                fine[nickname] = 0
            fine[nickname] += dist * money
            del DB[nickname][partname]
        else:
            DB[nickname][partname] = curTime

ans = False
for nickname in sorted(fine.keys()):
    if fine[nickname] == 0:
        continue
    ans = True
    print(f"{nickname} {fine[nickname]}")

if ans == False:
    print(-1)