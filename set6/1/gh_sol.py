import sys
my_input = sys.stdin.readline
my_print = sys.stdout.write
files = []
n, m = map(int, my_input().split(' '))
for i in range(n):
    name, ext = my_input().split('.')
    files.append([name, 0, ext])
dic = {}
for i in range(m):
    e = my_input()
    dic[e] = ''
for i in range(n):
    if files[i][2] not in dic:
        files[i][1] = 1
files = sorted(files)
for i in range(n):
    my_print(f'{files[i][0]}.{files[i][2]}')
