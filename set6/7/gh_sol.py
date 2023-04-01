import sys
import collections
queue = collections.deque()
my_input = sys.stdin.readline
di = {(-1, 0): 'U', (0, 1): 'R', (1, 0): 'D', (0, -1): 'L'}
r, c = map(int, my_input().split(' '))
ma = [[] for _ in range(r)]
new_ma = [['.' for _ in range(c)] for _ in range(r)]

turtle = []
turtle_x = []
turtle_y = []
gh_home = []
jang = []
for i in range(r):
    ma[i] = my_input().replace('\n', '')
    for j in range(c):
        if ma[i][j] == 'T':
            turtle.append(tuple([i, j]))
            turtle_x.append(i)
            turtle_y.append(j)
        elif ma[i][j] == 'H':
            gh_home.append(tuple([i, j]))
        elif ma[i][j] == '#':
            jang.append(tuple([i, j]))
bbox = ([min(turtle_x), max(turtle_x), min(turtle_y), max(turtle_y)])


def chk(x, y, gijun, target):
    _dx = x - gijun[0]
    _dy = y - gijun[1]
    moved_tx = target[0] - _dx
    moved_ty = target[1] - _dy
    if 0 <= moved_tx < len(ma) and 0 <= moved_ty < len(ma[0]):
        return ma[moved_tx][moved_ty] == 'T'
    return False


# H부터
for i in range(r):
    for j in range(c):
        if chk(i, j, turtle[0], gh_home[0]):
            new_ma[i][j] = 'H'
# #
for i in range(r):
    for j in range(c):
        for point in jang:
            if chk(i, j, turtle[0], point):
                new_ma[i][j] = '#'
# Bounding box
for i in range(r):
    for j in range(c):
        dx = i - turtle[0][0]
        dy = j - turtle[0][1]
        sx, ex, sy, ey = bbox[0] + dx, bbox[1] + dx, bbox[2] + dy, bbox[3] + dy
        if sx < 0 or ex < 0 or sx >= r or ex >= r:
            new_ma[i][j] = '#'
        if sy < 0 or ey < 0 or sy >= c or ey >= c:
            new_ma[i][j] = '#'
# new_ma 다 구축했다면 이제..
visit = [[0 for _ in range(c)] for _ in range(r)]
wif = [[(-1, -1) for _ in range(c)] for _ in range(r)]
dx_arr = [-1, 0, 1, 0]
dy_arr = [0, -1, 0, 1]
sx, sy = turtle[0]
queue.append(tuple([sx, sy]))
visit[sx][sy] = 1
while queue:
    cx, cy = queue.popleft()
    if new_ma[cx][cy] == 'H':
        ans = collections.deque()
        while cx != sx or cy != sy:
            dx, dy = cx - wif[cx][cy][0], cy - wif[cx][cy][1]
            if dx == -1 and dy == 0:
                ans.appendleft('U')
            elif dx == 0 and dy == 1:
                ans.appendleft('R')
            elif dx == 1 and dy == 0:
                ans.appendleft('D')
            else:
                ans.appendleft('L')
            cx, cy = wif[cx][cy]
        print("".join(ans))
        exit(0)
    for i in range(4):
        tx = cx + dx_arr[i]
        ty = cy + dy_arr[i]
        if tx < 0 or ty < 0 or tx >= r or ty >= c:
            continue
        if visit[tx][ty] == 1 or new_ma[tx][ty] == '#':
            continue
        queue.append(tuple([tx, ty]))
        visit[tx][ty] = 1
        wif[tx][ty] = tuple([cx, cy])
print('-1')
