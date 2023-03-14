# Pathfinder
This is a Java class for finding a path in a two-dimensional grid using different algorithms based on the distance between the start and end points. The class includes implementations of Dijkstra's algorithm and A* algorithm.

The class has a constructor that initializes a two-dimensional grid of integers with a given size and randomly selects start and end points within the grid. The findPath() method determines which algorithm to use based on the distance between the start and end points and calls the corresponding method (useDijkstra() or useAStar()) to find the shortest path between the two points.

useDijkstra() implements Dijkstra's algorithm to find the shortest path between the start and end points. It initializes the distances and visited arrays with initial values, sets the starting distance to zero, and updates the distances to neighboring nodes until the end node is reached. It prints the shortest distance to the end node.

useAStar() implements the A* algorithm to find the shortest path between the start and end points. It initializes the distances array with initial values, sets the starting distance to zero, and uses a priority queue to prioritize nodes with the lowest estimated total cost. It updates the distances to neighboring nodes until the end node is reached. It prints the shortest distance from start to end.

The class uses several Java libraries such as Random, Arrays, Comparator, HashSet, PriorityQueue, Set, and Queue to implement the algorithms.
