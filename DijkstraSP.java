/* 
 * Código inspirado em DijkstraSP.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
*/

import java.util.PriorityQueue;

public class DijkstraSP {
    private EdgeWeightedDigraph G;          // Grafo direcionado com peso nas arestas
    private int s;                          // Vértice-núcleo
    private double[] distTo;                // distTo[v] retorna a mínima distância do caminho s -> ... -> v 
    private DirectedEdge[] edgeTo;          // edgeTo[v] retorna a aresta u->v no caminho mínimo s-> ... -> v
    private PriorityQueue<Node> PQ;         // Fila de prioridade de mínimos
    private BellmanFordSP Bv;               // BellmanFord do vigilante para controle de interseção em vértices

    private static class Node implements Comparable<Node>{
        private int v;
        private double dist;

        private Node(int v, double dist){
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node x){
            return Double.compare(this.dist,x.dist); 
        }
    }

    // Inicialização do método
    public DijkstraSP(EdgeWeightedDigraph G, BellmanFordSP Bv, int s){
        this.G = G;
        this.s = s;
        this.Bv = Bv;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        PQ = new PriorityQueue<Node>();
        Dijkstra(G,Bv,s,distTo,edgeTo,PQ);
    }

    // Execução do algoritmo de Dijkstra
    private void Dijkstra(EdgeWeightedDigraph G, BellmanFordSP Bv, int s, double[] distTo, DirectedEdge[] edgeTo, PriorityQueue<Node> PQ){
        Node[] g_nodes = new Node[G.V()];
        for(int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
            g_nodes[i] = new Node(i,distTo[i]);

        }
        distTo[s] = 0;
        g_nodes[s].dist = 0;
        for(int i = 0; i < G.V(); i++){
            PQ.add(g_nodes[i]);
        }

        while(PQ.size() > 0){
            Node node_v = PQ.poll();
            int v = node_v.v;

            for(DirectedEdge e : G.adj(v)){
                int u = e.to();
                Node node_u = g_nodes[u];
                if(PQ.contains(node_u)){
                    if(node_u.dist > node_v.dist + e.weight() && node_v.dist + e.weight() < Bv.distTo(u)){
                        PQ.remove(node_u);
                        node_u.dist = node_v.dist + e.weight();
                        distTo[u] = node_v.dist + e.weight();
                        edgeTo[u] = e;
                        PQ.add(node_u);
                    }
                }
            }
        }
    }

    // Retorna se há caminho s -> ... -> v
    public boolean hasPathTo(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    // Retorna a distância mínima do caminho s -> ... -> v
    public double distTo(int v){
        return distTo[v];
    }

    // Iterador sobre o caminho mínimo s -> ... -> v
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
