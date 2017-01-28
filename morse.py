def wordToMorse(word, letters):
    w = ""
    for l in word:
        w = w + letters[l]
    return w

def main():
    letters = dict()
    for i in range(26):
        line = input()
        parse = line.split(" ")
        letters[parse[0]] = parse[1]

    numValidWords = int(input())

    words = []
    for i in range(numValidWords):
        words.append(input())

    #validMorseWords = []
    #for w in words:
        #validMorseWords.append(wordToMorse(w, letters))
    validMorseWords = list(map(lambda x: wordToMorse(x, letters), words))
    print("debug: " + str(validMorseWords))
    print("debug: " + str(words))

    num = int(input())

    while num > 0:
        morseWords = []
        for i in range(num):
            morseWords.append(input())
        # here!
        foundSoFar = []
        flag = True
        for i, m in enumerate(morseWords):
            validWordFound = False
            for j, validWord in enumerate(validMorseWords):
                if m == validWord:
                    foundSoFar.append(words[j])
                    validWordFound = True
                    break
            if not validWordFound:
                print(m + " not in dictionary.")
                flag = False
                break
        if flag:
            print(str(foundSoFar).replace("['","").replace("']","").replace("', '"," "))
        ###
        num = int(input())



if __name__ == '__main__':
    main()
