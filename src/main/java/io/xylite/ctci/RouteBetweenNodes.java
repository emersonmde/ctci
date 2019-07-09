package io.xylite.ctci;

import java.util.*;

public class RouteBetweenNodes {
    public static void main(String[] args) {
        Integer[][] directedGraph = {
                {1, 2}, {1, 3},
                {2, 3},
                {3, 4}, {3, 5},
                {8, 1}, {8, 9}
        };
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (Integer[] pair : directedGraph) {
            if (!nodes.containsKey(pair[0])) {
                nodes.put(pair[0], new Node(pair[0]));
            }
            if (!nodes.containsKey(pair[1])) {
                nodes.put(pair[1], new Node(pair[1]));
            }

            Node current = nodes.get(pair[0]);
            Node adjacent = nodes.get(pair[1]);
            current.addAdjacentNode(adjacent);
        }
        nodes.forEach((k, v) -> System.out.println(k + ", " + v));
        System.out.println("Does route exist? " + breadthFirstSearch(nodes.get(8), nodes.get(5)));
    }

    private static boolean breadthFirstSearch(Node a, Node b) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            System.out.println(queue);
            Node currentNode = queue.remove();
            if (currentNode == b) {
                return true;
            }
            for (Node adjacentNode : currentNode.getAdjacentNodes()) {
                if (adjacentNode == b) {
                    return true;
                }
                queue.add(adjacentNode);
            }
        }
        return false;
    }


    public static class Node {
        private Integer id;
        private LinkedList<Node> adjacentNodes;

        public Node(Integer id) {
            this.id = id;
            this.adjacentNodes = new LinkedList<>();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public LinkedList<Node> getAdjacentNodes() {
            return this.adjacentNodes;
        }

        public void addAdjacentNode(Node node) {
            this.adjacentNodes.add(node);
        }

        public boolean removeAdjacentNode(Node node) {
            return this.adjacentNodes.remove(node);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return id.equals(node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Node node : adjacentNodes) {
                sb.append(node.getId()).append(",");
            }
            sb.append("]");
            return "Node{" +
                    "id=" + id +
                    ", adjacentNodes=" + sb.toString() +
                    '}';
        }
    }

}
