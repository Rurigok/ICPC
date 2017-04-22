from base64 import b64encode, b64decode

def b64_to_bin(b64string):
    return ''.join([bin(x)[2:].zfill(8) for x in b64decode(b64string)])

def rot_cmp(string1, string2):

    length = len(string1)
    correct_so_far = 0
    for start in range(length): # offset to start comparison in str2
        index = 0
        for i in range(length): # index to compare
            correct_so_far += 1 if string1[index] == string2[(i+start)%length] else -correct_so_far
            index += 1
            if correct_so_far == 0:
                break
        if correct_so_far == length:
            return True
    return correct_so_far == length


def main():
    num_fingerprints = int(input())
    case = 1
    while num_fingerprints > 0:
        fingerprints = []
        for _ in range(num_fingerprints):
            fingerprints.append(input())
        # do things


        print("Case {}:".format(case))
        #print(fingerprints)

        prints = {}

        fingerprints = list(map(b64_to_bin, fingerprints))

        for fingerprint in fingerprints:
            if len(prints) == 0:
                prints[fingerprint] = 1
                continue
            keys = list(prints.keys())
            for key in keys:
                if rot_cmp(key, fingerprint):
                    prints[key] += 1
                else:
                    prints[fingerprint] = 1


        print(prints)
        # done doing things
        num_fingerprints = int(input())
        case += 1

if __name__ == '__main__':
    main()
