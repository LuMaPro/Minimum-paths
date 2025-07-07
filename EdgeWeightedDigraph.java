/* 
 * Código inspirado em EdgeWeightedDigraph.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
*/

public class EdgeWeightedDigraph {

    private Stack<DirectedEdge>[] adj;          // Array de listas de adjacência
    private final int V;                        // Número de vértices do grafo

    // Inicialização do grafo direcionado com peso nas arestas
    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int N){
        V = N;
        adj = new Stack[N];
        for(int i = 0; i < N; i++){
            adj[i] = new Stack<DirectedEdge>();
        }
    }

    // Retorna a quantidade de arestas do grafo
    public int V(){
        return V;
    }

    // Retorna a lista de adjacência com os vizinhos de v
    public Stack<DirectedEdge> adj(int v){
        return adj[v];
    }

    // Adiciona uma aresta direcionada ao grafo
    public void addEdge(DirectedEdge e){
        int from = e.from();
        adj[from].push(e);
    }

    // Iterador sobre as arestas do grafo
    public Iterable<DirectedEdge> edges() {
        Stack<DirectedEdge> list = new Stack<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.push(e);
            }
        }
        return list;
    }
}
