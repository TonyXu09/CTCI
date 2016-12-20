package com.ctci;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by tonyxu on 12/15/16.
 */

/*
Graphs:
A tree is a connected graph without a cycle

A graph is simply a connection of nodes with edges between (some of) them.
Graphs can be directed or undirected. Directed edges are like a one way street, undirected like two way.

A graph may contain multiple isolated subgraphs. A graph is connected if there is a path between
every pair of vertices.

The graph can also have cycles. An Acyclic graph does not have cycles.

Representing a graph:

Adjacency list:

Most common way to represent a graph. Each vertex(Node) stores a list of adjacency nodes. In an
undirected graph, an edge (a, b) would be stored twice, one in a's adjacency list, and one in b's
adjacency list.

The graph class is used because unlike in a tree, you can't always reach every node.

You don't need a class to represent a graph. You could use an array (or hash table) of lists(array,
arraylist, linkedlist) can store the adjacency list.


Adjacency Matrix:

An adjacency matrix is a N x N boolean matrix where N is the number of nodes. A true value at
matrix[i][j] represents that there is an edge going from node i to j. In an undirected graph, an
adjacency matrix will be symmetric.


Graph Search:

Depth First Search:
We explore each branch to the end before exploring the next branch.
- usually used if we want to search every node, usually simpler than BFS.

Breadth First Search:
We start at the root, and we explore each neighbour before going to its children. We go wide before
deep.
- usually used to find the shortest path between 2 nodes.
- EX: Find closest friendship path between 2 people.


BiDirectional Search:
- Used to find the shortest path between 2 nodes. It operates by running 2 simultaneous BFS between
the source node and the destination node. When their searches collide, we found a path.

- consider a graph where each node has at most k neighbors and the shortest path from node a to b is d.

In BFS: Each search will take k operations, then k^2 for the next level, so total of O(k^d) nodes
In Bidrectional Search: We have 2 searches that will collidge in about K^(d/2) level. so this takes
about 2 k ^ (d/2) so O(k^(d/2))

Remember that k^(d/2) * k^(d/2) = k^d. So it's k^(d/2) faster.

 */
public class Graph {
    public GraphNode[] nodes;

    public static void DepthFirstSerach(GraphNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value);
        node.visisted = true;

        for (int i = 0; i < node.neighbors.length; i++) {
            if (!node.neighbors[i].visisted) {
                DepthFirstSerach(node.neighbors[i]);
            }
        }
    }

    public static void BreadthFirstSearch(GraphNode node) {
        if (node == null) {
            return;
        }

        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        node.visisted = true;

        while (queue != null) {
            GraphNode gn = queue.remove();
            System.out.print(gn.value);

            for (int i = 0; i < node.neighbors.length; i++) {
                if(!node.neighbors[i].visisted) {
                    queue.offer(node.neighbors[i]);
                    node.neighbors[i].visisted = true;
                }
            }
        }
    }

}
