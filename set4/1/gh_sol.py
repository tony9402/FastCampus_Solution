R, C = map(int, input().split())
_, _, Rp, Cp = map(int, input().split())
ans = 0
for i in range(R):
    '''
    if counting character in string
    use count method rather than list comprehension.
    '''
    ans = ans + input().count('P')
if ans != Rp*Cp:
    print('1')
else:
    print('0')