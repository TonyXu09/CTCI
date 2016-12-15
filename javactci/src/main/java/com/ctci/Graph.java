package com.ctci;

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


 */
public class Graph {
    public GraphNode[] nodes;
}
