package io.xylite.ctci;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) throws Graph.CycleException {
        Integer[] ids = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer[][] dependencies = {
                {1, 6}, {1, 5},
                {2, 9}, {2, 10},
                {3, 4}, {3, 7},
                {4, 8},
                {5, 11},
                {6, 2}, {6, 3},
                {10, 8},
        };

        Graph graph = new Graph(ids, dependencies);
        Queue<Node> order = graph.getDependencyOrder();
        order.forEach(System.out::println);
    }

    public static class Graph {
        private HashMap<Integer, Node> nodeMap;

        Graph(Integer[] ids, Integer[][] dependencies) {
            nodeMap = new HashMap<>();
            for (Integer id : ids) {
                nodeMap.put(id, new Node(id));
            }
            for (Integer[] pair : dependencies) {
                nodeMap.get(pair[0]).addAdjacent(nodeMap.get(pair[1]));
            }
        }

        Queue<Node> getDependencyOrder() throws CycleException {
            HashSet<Node> visited = new HashSet<>();
            Queue<Node> finished = new LinkedList<>();
            for (Node node : nodeMap.values()) {
                addDependencies(node, finished, visited);
            }

            return finished;
        }

        void addDependencies(Node startNode, Queue<Node> finished, Set<Node> visited) throws CycleException {
            if (visited.contains(startNode)) {
                return;
            }

            Stack<Node> pending = new Stack<>();
            Stack<Node> workInProgress = new Stack<>();

            workInProgress.push(startNode);
            while (!workInProgress.isEmpty()) {
                Node currentNode = workInProgress.pop();
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
                for (Node adjacentNode : currentNode.getAdjacent()) {
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

        public static class CycleException extends Exception {
        }
    }

    public static class Node {
        private Integer id;
        private LinkedList<Node> adjacent;

        Node(Integer id) {
            this.id = id;
            adjacent = new LinkedList<>();
        }

        void addAdjacent(Node node) {
            adjacent.add(node);
        }

        Integer getId() {
            return id;
        }

        LinkedList<Node> getAdjacent() {
            return adjacent;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Node node : adjacent) {
                sb.append(node.getId()).append(",");
            }
            sb.append("]");
            return "Node{" +
                    "id=" + id +
                    ", adjacent=" + sb +
                    '}';
        }
    }
}
