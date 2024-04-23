N, K, P, X = map(int, input().split())

led = [
    [1, 1, 1, 0, 1, 1, 1],  # 0
    [0, 0, 1, 0, 0, 1, 0],  # 1
    [1, 0, 1, 1, 1, 0, 1],  # 2
    [1, 0, 1, 1, 0, 1, 1],  # 3
    [0, 1, 1, 1, 0, 1, 0],  # 4
    [1, 1, 0, 1, 0, 1, 1],  # 5
    [1, 1, 0, 1, 1, 1, 1],  # 6
    [1, 0, 1, 0, 0, 1, 0],  # 7
    [1, 1, 1, 1, 1, 1, 1],  # 8
    [1, 1, 1, 1, 0, 1, 1]   # 9
]

now = []
ans = 0
visited = set()  # 이미 검사한 경우를 기록하기 위한 집합

# 현재 층수를 리스트로 변환
if len(str(N)) != len(str(X)):
    for _ in range(len(str(N))-len(str(X))):
        now.append(0)

now += list(map(int, str(X)))

def change(index, count, num):
    global ans
    if count > P:
        return

    if index == len(now):
        if 1 <= int(''.join(map(str, num))) <= N and 1 <= count <= P:
            ans += 1
        return

    # 이미 검사한 경우라면 더 이상 진행하지 않음
    if (index, count, tuple(num)) in visited:
        return
    visited.add((index, count, tuple(num)))

    now_led = led[now[index]]

    for i in range(10):
        diff = sum(1 for x, y in zip(led[i], now_led) if x != y)

        if diff <= P:
            tmp = num[:]
            tmp[index] = i
            change(index+1, count+diff, tmp)

change(0, 0, now)

print(ans)

# N, K, P, X = map(int, input().split())
#
# # N: 1~N층, K: 자리수, P: 반전 최대 개수, X: 멈춰 있는 층수
#
# led = [
#     [1, 1, 1, 0, 1, 1, 1],  # 0
#     [0, 0, 1, 0, 0, 1, 0],  # 1
#     [1, 0, 1, 1, 1, 0, 1],  # 2
#     [1, 0, 1, 1, 0, 1, 1],  # 3
#     [0, 1, 1, 1, 0, 1, 0],  # 4
#     [1, 1, 0, 1, 0, 1, 1],  # 5
#     [1, 1, 0, 1, 1, 1, 1],  # 6
#     [1, 0, 1, 0, 0, 1, 0],  # 7
#     [1, 1, 1, 1, 1, 1, 1],  # 8
#     [1, 1, 1, 1, 0, 1, 1]   # 9
# ]
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
#
# now = []
# ans = 0
# ans_arr = set()
# fail = []
# arr = []
#
# if len(str(N)) != len(str(X)):
#     for _ in range(len(str(N))-len(str(X))):
#         now.append(0)
#
# now += list(map(int, str(X)))
#
# def change(index, count, num):
#     global ans
#     if count > P:
#         return
#
#     if index == len(now):
#         if 1 <= int(''.join(map(str, num))) <= N and 1 <= count <= P:
#             ans_arr.add(int(''.join(map(str, num))))
#         return
#
#
#     if 1 <= int(''.join(map(str, num))) <= N and 1 <= count <= P:
#         ans_arr.add(int(''.join(map(str, num))))
#
#     now_led = led[now[index]]
#
#     for i in range(10):
#         diff = 0
#         for j in range(7):
#             if diff >= P:
#                 break
#             if led[i][j] != now_led[j]:
#                 diff += 1
#
#         if diff <= P:
#             change(index+1, count, num)
#             num[index] = i
#             change(index+1, count+diff, num)
#             num[index] = now[index]
#
#
# tmp = now[:]
# change(0, 0, tmp)
#
# # print(ans_arr)
# print(len(ans_arr))