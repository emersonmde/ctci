package io.xylite.ctci.graphs;

import java.util.LinkedList;

public class Node<T> {
    private T id;
    private LinkedList<Node<T>> adjacent;

    Node(T id) {
        this.id = id;
        adjacent = new LinkedList<Node<T>>();
    }

    void addAdjacent(Node<T> node) {
        adjacent.add(node);
    }

    T getId() {
        return id;
    }

    LinkedList<Node<T>> getAdjacent() {
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