
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

ORDER = ['E', 'A', 'B', 'F', 'G', 'C', 'H', 'D']


data_string="A->B    24,A->F    12,B->C    22,B->F     5,C->D    32,C->H     1,E->A    33,E->F    51,F->C    32,F->G    18,G->C    13,G->H     5,H->D    39"

for data in data_string.split(","):
    TREE[data[0]].append((data[3], int(data[4:])))

print TREE
print_dict(acyclic_shortest_paths(TREE, ORDER, 'C'))

