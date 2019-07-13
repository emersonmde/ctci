package io.xylite.ctci.graphs;

import java.util.Queue;

public class TopologicalSort {
    public static void main(String[] args) throws DependencyGraph.CycleException {
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

        DependencyGraph<Integer> dependencyGraph = new DependencyGraph<>(ids, dependencies);
        Queue<Node> order = dependencyGraph.getDependencyOrder();
        order.forEach(System.out::println);
    }

}
