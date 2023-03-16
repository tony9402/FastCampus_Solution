N, K = map(int, input().split())
idx, arr, tmp = [0] * (N + 1), [0] * (N + 1), [0] * (N + 1)
arr[1:N+1]=list(map(int,input().split()))
idx[1:N+1]=list(map(int,input().split()))
for j in range(1, K + 1):
    for i in range(1, N + 1): tmp[idx[i]] = arr[i]
    arr[1:N+1] = list(tmp[1:N+1])
print(" ".join(map(str, arr[1:N+1])))