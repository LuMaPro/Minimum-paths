/* 
 * Código inspirado em BellmanFordSP.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
*/

public class BellmanFordSP {
    
    private EdgeWeightedDigraph G;          // Grafo direcionado com peso nas arestas
    private int s;                          // Vértice-núcleo
    private double[] distTo;                // distTo[v] retorna a mínima distância do caminho s -> ... -> v 
    private DirectedEdge[] edgeTo;          // edgeTo[v] retorna a aresta u->v no caminho mínimo s-> ... -> v

    // Inicialização do método
    public BellmanFordSP(EdgeWeightedDigraph G, int s){
        this.G = G;
        this.s = s;
        
        int V = G.V();
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];
        
        for(int i = 0; i < V; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = null;
        }

        distTo[s] = 0;


        for(int i = 1; i < V; i++){
            for(int j = 0; j < V; j++){
                for(DirectedEdge e : G.adj(j)){
                    int from = e.from();
                    int to = e.to();
                    if(distTo[to] > distTo[from] + e.weight()){
                        distTo[to] = distTo[from] + e.weight();
                        edgeTo[to] = e;
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
        if (!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
