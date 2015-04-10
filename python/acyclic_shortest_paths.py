
def print_dict(dt):
    keys = dt.keys()
    keys.sort()
    for elem in keys:
        print dt[elem],
    print

def acyclic_shortest_paths(arbol, order, last = None):
    dist = { 'A': float('Inf'),
          'B': float('Inf'),
          'C': float('Inf'),
          'D': float('Inf'),
          'E': float('Inf'),
          'F': float('Inf'),
          'G': float('Inf'),
          'H': float('Inf')
        }

    dist[order[0]] = 0
    for node in order:
        for i in range(0, len(arbol[node])):
            d = arbol[node][i][1] + dist[node]
            if d < dist[arbol[node][i][0]]:
                dist[arbol[node][i][0]] = d
        if last == node:
            return dist

    return dist



TREE = { 'A': [],
          'B': [],
          'C': [],
          'D': [],
          'E': [],
          'F': [],
          'G': [],
          'H': []
        }

ORDER = ['H', 'G', 'C' ,'D' ,'B', 'A', 'F', 'E']


data_string="A->B     1,B->F    23,C->B    22,C->F    46,D->C     9,D->G    69,D->H    35,E->A    31,F->A    55,F->E    17,F->G     5,G->C     2,H->G    27"

for data in data_string.split(","):
    TREE[data[0]].append((data[3], int(data[4:])))

print TREE
print_dict(acyclic_shortest_paths(TREE, ORDER, 'A'))

