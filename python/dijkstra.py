TREE = { 'A': [('B', 26), ('E', 2)],
          'B': [('C', 3)],
          'C': [],
          'D': [('D', 17)],
          'E': [],
          'F': [('A', 15),('B', 49),('C', 53),('E', 22),('G', 4)],
          'G': [('C', 49), ('H', 27)],
          'H': [('C', 16), ('D', 1)]
        }

print TREE

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

print Dijkstra(TREE, 'F', 'H')
print Dijkstra(TREE, 'F', 'H')
