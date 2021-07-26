package PRIM;

public class PrimCopilot {
    // Method signature:
    //   public static void main(String[] args)
    //
    // Purpose:

    public static void main(String[] args) {
        // Initialize:
        Graph g = new Graph();
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(8);
        g.addVertex(9);
        g.addVertex(10);
        g.addVertex(11);
        g.addVertex(12);
        g.addVertex(13);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 7);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);
        g.addEdge(6, 7);
        g.addEdge(6, 8);
        g.addEdge(6, 9);
        g.addEdge(7, 8);
        g.addEdge(7, 9);
        g.addEdge(7, 10);
        g.addEdge(8, 9);
        g.addEdge(8, 10);
        g.addEdge(8, 11);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(10, 11);
        g.addEdge(10, 12);
        g.addEdge(10, 13);
        g.addEdge(11, 12);
        g.addEdge(11, 13);
        g.addEdge(11, 14);
        g.addEdge(12, 13);
        g.addEdge(12, 14);
        g.addEdge(12, 15);
        g.addEdge(13, 14);
        g.addEdge(13, 15);
        g.addEdge(13, 16);
        g.addEdge(14, 15);
        g.addEdge(14, 16);

        // Print:
        System.out.println("Graph: ");
        g.print();
        System.out.println("\nPrim: ");
        PrimCopilot.run(g, 0);
    }

    // Method signature:
    //   public static void run(Graph g, int start)
    //
    // Purpose:
    //   Run Prim's algorithm on the given graph.
    //
    // Arguments:
    //   g: the graph
    //   start: the vertex to start the algorithm at
    //
    // Returns:
    //   void
    public static void run(Graph g, int start) {
        // Initialize:
        int[] parent = new int[g.getSize()];
        int[] rank = new int[g.getSize()];
        for (int i = 0; i < g.getSize(); i++) {
            parent[i] = -1;
            rank[i] = 0;
        }
        parent[start] = start;
        rank[start] = 1;
        int current = start;
        int next = -1;
        int min = -1;
        int min_index = -1;
        int count = 0;
        while (count < g.getSize()) {
            // Find the next vertex:
            for (int i = 0; i < g.getSize(); i++) {
                if (g.getEdge(current, i) != null && parent[i] == -1) {
                    next = i;
                    break;
                }
            }
            // If no next vertex was found, we're done:
            if (next == -1) {
                break;
            }

            // Find the minimum vertex:
            min = Integer.MAX_VALUE;
            for (int i = 0; i < g.getSize(); i++) {
                if (g.getEdge(current, i) != null && parent[i] != -1 &&
                        g.getEdge(current, i).getWeight() < min) {
                    min = g.getEdge(current, i).getWeight();
                    min_index = i;
                }
            }
            // If no minimum vertex was found, we're done:
            if (min == Integer.MAX_VALUE) {
                break;
            }
            // Otherwise, we found a minimum vertex:
            parent[next] = current;
            rank[next] = rank[current] + 1;
            current = next;
            count++;
        }
        // Print:
        System.out.println("\nParent: ");
        for (int i = 0; i < g.getSize(); i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println("\nRank: ");
        for (int i = 0; i < g.getSize(); i++) {
            System.out.print(rank[i] + " ");
        }
    }
}


