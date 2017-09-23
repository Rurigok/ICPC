import sys
import itertools
from pprint import pprint
from collections import Counter

vowels = [
    "110101",
    "101101",
    "010101",
    "111011",
]

symbols = [
    "110111",
    "110011",
    "110000",
    "101111",
    "101011",
    "101000",
    "010111",
    "010011",
    "010000",
    "111101",
    "111111",
    "111000",
]

vowelMatches = {}
consMatches = {}


def cmp_heiroglyph(complete_char, with_missing_chars):
    for i, c in enumerate(complete_char):
        if c != with_missing_chars[i] and with_missing_chars != "?":
            return False
    return True

perms = list(itertools.permutations(["1", "0", "?"], 6))
for c in perms:
    print(c)
    vowelMatches[c] = 0
    consMatches[c] = 0
    for v in vowels:
        if cmp_heiroglyph(v, c):
            vowelMatches[c] += 1
    for con in symbols:
        if cmp_heiroglyph(con, c):
            consMatches[c] += 1

pprint(len(vowelMatches))

def solve_word():

    num_chars = int(input())

    chars = [input() for _ in range(num_chars)]

def num_words(tablet, cur, vowelPresent, vowelPrev):

    if cur == len(tablet):
        return 1

    if cur == len(tablet) - 1 and not vowelPresent:
        return vowelMatches[tablet[cur]]



def main():

    sys.stdin = open("heiroglyphics.txt")

    num_words = int(input())

    for _ in range(num_words):
        solve_word()

if __name__ == '__main__':
    main()