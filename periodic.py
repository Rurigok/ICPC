def list_to_word(element_list):
    if not element_list:
        return ''
    #print(''.join(element_list))
    return ''.join(element_list).lower()

def compare_elements(first, second):
    first_sum = 0
    for element in first:
        first_sum += atomic_numbers[element]
    sec_sum = 0
    for element in second:
        sec_sum += atomic_numbers[element]
    if first_sum < sec_sum:
        return first_sum
    elif sec_sum < first_sum:
        return sec_sum
    else:
        return None

def make_word(target, current, element_list):

    current_word = list_to_word(current)

    if current == None:

        return None
    if len(current_word) > len(target):
        return None
    if current_word == target:
        return current
    best = None
    for element in element_list:
        temp_list = current[:]
        temp_list.append(element)

        temp = make_word(target, temp_list, element_list)
        if temp == 'Too Obvious':
            return temp
        if temp != None:
            if best == None:
                best = temp
            elif len(temp) < len(best):
                best = temp
            elif len(temp) == len(best):
                temp_cmp = compare_elements(temp, best)
                if temp_cmp == None:
                    return 'Too Obvious'
                best = temp_cmp


    return best

cases = int(input())
atomic_numbers = {}
for i in range(cases):
    elements = []
    words = []
    atomic_numbers = {}
    numelements = int(input())
    for j in range(numelements):
        current_element = input()
        elements.append(current_element)
        atomic_numbers[current_element] = j+1

    numwords = int(input())
    for j in range(numwords):
        words.append(input().lower())

    for word in words:
        print(make_word(word, [], elements))
