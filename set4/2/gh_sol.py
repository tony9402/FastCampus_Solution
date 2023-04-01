import itertools
st = []
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]


def func(start_x, start_y, r, c, q, ma):
    cx = start_x
    cy = start_y
    seti = {}
    ans = 0
    for op in q:
        cx = cx + dx[op]
        cy = cy + dy[op]
        if cx < 0 or cy < 0 or cx >= r or cy >= c:
            return 0
        if ma[cx][cy] == '#':
            return 0
        seti[(cx, cy)] = 1
    for cx, cy in seti:
        if ma[cx][cy] == 'S':
            ans = ans + 1
    return ans


R, C, T = map(int, input().split())
mmx = 0
for i in range(R):
    st.append(input())
    if st[i].find('G') != -1:
        sx, sy = i, st[i].find('G')
for query in itertools.product([0, 1, 2, 3], repeat=T):
    r = func(sx, sy, R, C, query, st)
    if r > mmx:
        mmx = r
print(mmx)