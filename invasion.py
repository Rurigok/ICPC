
cases = int(input())

for i in range(cases):
    v = list(map(lambda x: int(x), input().split(" ")))
    for x in range(len(v) - 1, -1, -1):
        if x > 0:
            v[x - 1] += v[x] // 2
            v[x] = v[x] - (v[x] // 2) * 2
    print(' '.join([str(i) for i in v]))
