
cases = int(input())

for i in range(cases):
    line = input()
    time = 1
    buf = []
    while line != "END OF CASE":

        for c in line:
            if c != ";":
                buf.append(c)
            else:
                print(str(time) + ": " + ''.join(buf) + ";")
                buf = []

        time += 1
        line = input()
