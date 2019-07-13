package io.xylite.ctci.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RouteBetweenNodes {
    public static void main(String[] args) {
        Integer[][] directedGraph = {
                {1, 2}, {1, 3},
                {2, 3},
                {3, 4}, {3, 5},
                {8, 1}, {8, 9}
        };
        java.util.HashMap<Integer, Node<Integer>> nodes = new java.util.HashMap<>();
        for (Integer[] pair : directedGraph) {
            if (!nodes.containsKey(pair[0])) {
                nodes.put(pair[0], new Node<>(pair[0]));
            }

            if (!nodes.containsKey(pair[1])) {
                nodes.put(pair[1], new Node<>(pair[1]));
            }

            Node<Integer> current = nodes.get(pair[0]);
            Node<Integer> adjacent = nodes.get(pair[1]);
            current.addAdjacent(adjacent);
        }
        nodes.forEach((k, v) -> System.out.println(k + ", " + v));
        System.out.println("Does route exist? " + breadthFirstSearch(nodes.get(8), nodes.get(5)));
    }

    private static boolean breadthFirstSearch(Node<Integer> a, Node<Integer> b) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            System.out.println(queue);
            Node<Integer> currentNode = queue.remove();
            if (currentNode == b) {
                return true;
            }
            for (Node<Integer> adjacentNode : currentNode.getAdjacent()) {
                if (adjacentNode == b) {
                    return true;
                }
                queue.add(adjacentNode);
            }
        }
        return false;
    }



}
