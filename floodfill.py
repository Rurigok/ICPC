import sys

def parse_case():

    row_len = int(input())
    column_len = int(input())

    # parse retention values
    retention_grid = [[int(w) for w in input().split()] for _ in range(column_len)]
    cells = [[0 for _ in range(row_len)] for _ in range(column_len)]

    sim_days = int(input()) * 2

    for _ in range(sim_days):
        (x, y, rainfall) = [int(i) for i in input().split()]
        simulate_rainfall(cells, retention_grid, x, y, rainfall)

directions = [(1, -1), (1, 0), (1, 1), (0, -1), (0, 1), (-1, -1), (-1, 0), (-1, 1)]

def simulate_rainfall(cells, retention_grid, x, y, rainfall):

    capacity = retention_grid[x][y] - cells[x][y]

    if capacity >= rainfall:
        cells[x][y] += rainfall
        return True

    # cell x y has flooded
    cells[x][y] += capacity
    rainfall -= capacity

    # spread remaining water
    for dir_x, dir_y in directions:
        if cells[x+dir_x][y+dir_y] < retention_grid[x+dir_x][y+dir_y]:
            #unsaturated
            pass
        else:
            #saturated
            pass


def main():

    sys.stdin = open("floodfill_in.txt")
    cases = int(input())

    for _ in range(cases):
        parse_case()


if __name__ == '__main__':
    main()