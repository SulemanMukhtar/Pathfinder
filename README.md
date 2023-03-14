# Pathfinder
This is a Java class for finding a path in a two-dimensional grid using different algorithms based on the distance between the start and end points. The class includes implementations of Dijkstra's algorithm and A* algorithm.

The class has a constructor that initializes a two-dimensional grid of integers with a given size and randomly selects start and end points within the grid. The findPath() method determines which algorithm to use based on the distance between the start and end points and calls the corresponding method (useDijkstra() or useAStar() or useBFS()) to find the shortest path between the two points.

useDijkstra() implements Dijkstra's algorithm to find the shortest path between the start and end points. It initializes the distances and visited arrays with initial values, sets the starting distance to zero, and updates the distances to neighboring nodes until the end node is reached. It prints the shortest distance to the end node.

useAStar() implements the A* algorithm to find the shortest path between the start and end points. It initializes the distances array with initial values, sets the starting distance to zero, and uses a priority queue to prioritize nodes with the lowest estimated total cost. It updates the distances to neighboring nodes until the end node is reached. It prints the shortest distance from start to end.

useBFS() implements the breadth-first search algorithm to find the shortest path between the starting point and the end point in the grid. It initializes a 2D boolean array visited to keep track of visited nodes, and a Queue<int[]> object to hold the nodes to visit. It enqueues the starting node, sets its visited flag to true, and starts a loop that dequeues the next node to visit, checks if it is the end node, and enqueues its unvisited neighbors (if any) in the queue. It continues until the end node is visited or the queue is empty.

The class uses several Java libraries such as Random, Arrays, Comparator, HashSet, PriorityQueue, Set, and Queue to implement the algorithms.
