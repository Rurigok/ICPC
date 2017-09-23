
def sim(n, line):

    print("Simulation {}".format(n))

    cache_size, commands = line.split(" ")
    cache_size = int(cache_size)
    cache = []

    for c in commands:

        if c == "!":
            for i in cache:
                print(i, end="")
            print()
            continue

        if c in cache:
            cache.remove(c)
            cache.append(c)
            continue

        if len(cache) < cache_size:
            cache.append(c)
        else:
            cache.pop(0)
            cache.append(c)


def main():

    line = input()
    n = 1

    while line:

        sim(n, line)

        n += 1
        line = input()

if __name__ == '__main__':
    main()