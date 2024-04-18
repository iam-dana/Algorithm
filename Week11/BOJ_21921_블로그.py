N, X = map(int, input().split())
arr = list(map(int, input().split()))
ans = 0
cnt = 0

for i in range(1, N):
    arr[i] += arr[i-1]

for i in range(X):   # X 전의 방문자 수 세기
    if ans < arr[i]:
        ans = arr[i]
        cnt = 1
    elif ans == arr[i]:
        cnt += 1

for i in range(X, N):
    if ans < arr[i]-arr[i-X]:
        ans = arr[i]-arr[i-X]
        cnt = 1
    elif ans == arr[i]-arr[i-X]:
        cnt += 1

if ans == 0:
    print("SAD")
else:
    print(ans)
    print(cnt)
