
def print_dict(dt):
    keys = dt.keys()
    keys.sort()
    for elem in keys:
        print dt[elem],
    print

def Dijkstra(arbol, node, last = None):
    dist = { 'A': [float('Inf'), False],
          'B': [float('Inf'), False],
          'C': [float('Inf'), False],
          'D': [float('Inf'), False],
          'E': [float('Inf'), False],
          'F': [float('Inf'), False],
          'G': [float('Inf'), False],
          'H': [float('Inf'), False]
        }

    dist[node][1] = True
    dist[node][0] = 0

    actual = node
    finished = False
    while not finished:
        #print actual
        dist[actual][1] = True
        for i in range(0, len(arbol[actual])):
            d = arbol[actual][i][1] + dist[actual][0]
            if dist[arbol[actual][i][0]][1] == False:
                if d < dist[arbol[actual][i][0]][0]:
                    dist[arbol[actual][i][0]][0] = d
        #print dist
        # get actual
        if actual == last:
            finished = True
        else:
            finished = True
            m = float('Inf')
            for i in range(0, len(dist)):
                if dist.values()[i][1] == False:
                    if dist.values()[i][0] < m:
                        m = dist.values()[i][0]
                        actual = dist.keys()[i]
                        finished = False

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

data_string="A->B     1,B->F    23,C->B    22,C->F    46,D->C     9,D->G    69,D->H    35,E->A    31,F->A    55,F->E    17,F->G     5,G->C     2,H->G    27"

for data in data_string.split(","):
    TREE[data[0]].append((data[3], int(data[4:])))

print TREE

print_dict(Dijkstra(TREE, 'H', 'A'))
