/* 
 * Código inspirado em DirectedEdge.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
*/

public class DirectedEdge {

    private final int source;           // Vértice-núcleo
    private final int target;           // Vértice-alvo
    private final double weight;        // Peso da aresta

    // Inicialização da aresta direcionada (s->t) "source -> target"
    public DirectedEdge(int s, int t, double w){
        source = s;
        target = t;
        weight = w;
    }

    // Retorna o vértice-núcleo da aresta
    public int from(){
        return source;
    }

    // Retorna o vértice-alvo da aresta
    public int to(){
        return target;
    }

    // Retorna o peso da aresta
    public double weight(){
        return weight;
    }

    // String associada à aresta
    public String toString() {
        return source + " ->";
    }
}
