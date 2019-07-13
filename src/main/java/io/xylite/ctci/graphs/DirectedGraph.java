package io.xylite.ctci.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A graph where each node edge is unidirectional
 * @param <T> the type used for the node's ID
 */
class DirectedGraph<T> {
    protected java.util.HashMap<T, Node<T>> nodeMap;

    DirectedGraph() {
        nodeMap = new HashMap<>();
    }

    DirectedGraph(T[] ids, T[][] dependencies) {
        nodeMap = new HashMap<>();
        HashMap<T, ArrayList<T>> dependencyMap = new HashMap<>();
        for (T[] pair : dependencies) {
            if (!dependencyMap.containsKey(pair[0])) {
                dependencyMap.put(pair[0], new ArrayList<>());
            }
            dependencyMap.get(pair[0]).add(pair[1]);
        }
        dependencyMap.forEach(this::addNode);
    }

    public Node<T> addNode(T id, List<T> dependencies) {
        if (!nodeMap.containsKey(id)) {
            nodeMap.put(id, new Node<>(id));
        }
        Node<T> node = nodeMap.get(id);

        for (T dependency : dependencies) {
            if (!nodeMap.containsKey(dependency)) {
                nodeMap.put(dependency, new Node<>(dependency));
            }
            node.addAdjacent(nodeMap.get(dependency));
        }
        return node;
    }
}
