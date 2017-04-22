def checkArithmeticSeq(line):
    pass

def checkGeometricSeq(line):
    pass

line = input()

while line != "-1 -1 -1 -1":
    # parse numbers
    numbers = list(map(lambda x: int(x), line.split()))

    # find two consecutive integers
    for i in range(1, len(numbers)):
        if numbers[i - 1] != -1 and numbers[i] != -1:
            consec = (numbers[i - 1], numbers[i])
            consec_index = i - 1
            break

    # assume this is an arithmetic sequence and check
    addend = consec[1] - consec[0]

    # find third given number not in consec
    for i, n in enumerate(numbers):
        if n != -1 and n not in consec:
            index = i
            num = n

    expected = numbers[consec_index] + (index - consec_index) * addend

    # find missing index
    for i, n in enumerate(numbers):
        if n == -1:
            missing_index = i

    if (expected == numbers[index]):
        # is arithmetic
        answer = numbers[consec_index] + (missing_index - consec_index) * addend
    else:
        # assume this is geometric
        multiplier = consec[1] / consec[0]
        expected = numbers[consec_index] * (index - consec_index) * multiplier
        if (expected == numbers[index]):
            # is geometric
            #print("indices", missing_index, consec_index, multiplier)
            answer = numbers[consec_index] * multiplier ** (missing_index - consec_index)
        else:
            answer = -1

    if (1 <= answer <= 10000):
        print(int(answer))
    else:
        print("-1")

    #print(consec, "and", num, "at", index, "expecting", expected)

    line = input()
