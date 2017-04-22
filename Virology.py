
def is_triple(genes):
    return len(set(genes)) == 1

def vir_recurse(genes, processed, pairs, triples, straights):


lines = int(input())

for i in range(lines):
    line = input()
    genes = sorted(list(map(lambda x: int(x), line.split(" "))))

    processed = []
    for i in genes:
        pass
