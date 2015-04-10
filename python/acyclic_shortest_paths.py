TREE = { 'A': [('B', 29), ('E', 83), ('F', 40)],
          'B': [('C', 31), ('F',7),('G',43)],
          'C': [('D',38)],
          'D': [('H', 3)],
          'E': [],
          'F': [('E', 39),('G', 27)],
          'G': [('C', 2), ('D', 39),('H',16)],
          'H': []
        }

ORDER = ['A', 'B', 'F' ,'E' ,'G', 'C', 'D', 'H'] 

    
print TREE    

def acyclic_shortest_paths(arbol, order, last = None):
    dist = { 'A': [float('Inf')],
          'B': [float('Inf')],
          'C': [float('Inf')],
          'D': [float('Inf')],
          'E': [float('Inf')],
          'F': [float('Inf')],
          'G': [float('Inf')],
          'H': [float('Inf')]
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

print acyclic_shortes_paths(TREE, ORDER, 'G')    
            
