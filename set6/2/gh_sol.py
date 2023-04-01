import sys
my_input = sys.stdin.readline
my_print = sys.stdout.write
keyword_dic = {}
n, m = map(int, my_input().split(' '))
for i in range(n):
    s = my_input().replace('\n', '')
    keyword_dic[s] = ''
for i in range(m):
    s = my_input().replace('\n', '').split(',')
    for keyword in s:
        if keyword in keyword_dic:
            keyword_dic.pop(keyword)
    my_print(str(len(keyword_dic))+'\n')
