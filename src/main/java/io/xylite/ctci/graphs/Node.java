package io.xylite.ctci.graphs;

import java.util.LinkedList;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return id.equals(node.id) &&
                adjacent.equals(node.adjacent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adjacent);
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