package io.xylite.ctci.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class DependencyGraph<T> extends DirectedGraph<T> {
    DependencyGraph(T[] ids, T[][] dependencies) {
        super(ids, dependencies);
    }

    Queue<Node> getDependencyOrder() throws CycleException {
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> finished = new LinkedList<>();
        for (Node node : nodeMap.values()) {
            findDependencies(node, finished, visited);
        }

        return finished;
    }

    private void findDependencies(Node startNode, Queue<Node> finished, Set<Node> visited) throws CycleException {
        if (visited.contains(startNode)) {
            return;
        }

        Stack<Node<T>> pending = new Stack<>();
        Stack<Node<T>> workInProgress = new Stack<>();

        workInProgress.push(startNode);
        while (!workInProgress.isEmpty()) {
            Node<T> currentNode = workInProgress.pop();
            // If we've been here, skip it
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            // If we have no neighbors, add this to finished
            if (currentNode.getAdjacent().size() == 0) {
                finished.add(currentNode);
                continue;
            }

            // Add non-visited neighbors to workInProgress
            for (Node<T> adjacentNode : currentNode.getAdjacent()) {
                // If a child is already in pending, we've detected a cycle
                if (pending.contains(adjacentNode)) {
                    throw new CycleException();
                }
                if (!visited.contains(adjacentNode)) {
                    workInProgress.add(adjacentNode);
                }
            }

            pending.add(currentNode);
        }

        while (!pending.isEmpty()) {
            finished.add(pending.pop());
        }
    }

    static class CycleException extends Exception {
    }
}
