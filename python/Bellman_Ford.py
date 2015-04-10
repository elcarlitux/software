TREE = { 'A': [('E', 1)],
          'B': [('G', 3), ('A',14)],
          'C': [('B',38)],
          'D': [('C', 63), ('H',31)],
          'E': [],
          'F': [('B', 25),('A', 45),('E',24)],
          'G': [('C', 24), ('F', 25)],
          'H': [('C', 23), ('G', 8)]
        }

print TREE    

def Bellman_Ford(arbol, iters, start):
    dist = { 'A': float('Inf'),
          'B': float('Inf'),
          'C': float('Inf'),
          'D': float('Inf'),
          'E': float('Inf'),
          'F': float('Inf'),
          'G': float('Inf'),
          'H': float('Inf')
        }
    
    dist[start[0]] = 0
    for it in range(0, iters):
        for node in dist.keys():        
            for i in range(0, len(arbol[node])):
                d = arbol[node][i][1] + dist[node]
                if d < dist[arbol[node][i][0]]:
                    dist[arbol[node][i][0]] = d

        
    return dist
print Bellman_Ford(TREE, 3, 'D')  



