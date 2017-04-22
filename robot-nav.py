from pprint import pprint

line = input().split(" ")
M = int(line[0])
N = int(line[1])
m = int(line[2])

while m != 0:

    # do things
    grid = []
    for i in range(N):
        grid.append(input())
        grid[i] = list(grid[i])

    

    # new case
    line = input().split(" ")
    M = int(line[0])
    N = int(line[1])
    m = int(line[2])
