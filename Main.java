/*EP02 - MAC0323*/
/*Nome: Lucas Martins Próspero*/
/*NUSP.: 15471925*/


/* Compilação: javac Main.java
 * Execução: java Main (arquivo).txt
 * Observação: A leitura de txt para construção do grafo é feita a partir de
 * In.java de Algorithms, 4th Edition - Robert Sedgewick & Kevin Wayne.
 */

public class Main {

    // Inicialização do grafo dos guerrilheiros (Gg) e do vigilante (Gv) 
    public static void makeGraphs(In input, int M, EdgeWeightedDigraph Gg, EdgeWeightedDigraph Gv){
        for(int i = 0; i < M; i++){
            int A = input.readInt(), B = input.readInt();
            int Tg = input.readInt(), Tv = input.readInt();
            DirectedEdge Eg = new DirectedEdge(A,B,Tg);
            DirectedEdge Ev = new DirectedEdge(A,B,Tv);
            Gg.addEdge(Eg);
            Gv.addEdge(Ev);
        }
    }

    // Busca de abrigos seguros
    public static void checkDist(DijkstraSP[] Dg, BellmanFordSP Bv, int N, int K){
        Stack<Integer> safety = new Stack<>();

        for(int i = 0; i < N; i++){
            double max = Dg[0].distTo(i);
            for(int k = 0; k < K; k++){
                if(max < Dg[k].distTo(i)) max = Dg[k].distTo(i);
            }
            double distR = Bv.distTo(i);
            if(distR > max){
                safety.push(i);
            }
        }

        if(safety.length() == 0) System.out.println("INFELIZMENTE, A ESPERANÇA ACABOU");

        else{
            int size = safety.length();
            int[] safety_idx = new int[size];
            for(int i = size-1; i >= 0; i--){
                safety_idx[i] = safety.pop();
            }
            System.out.println(size);
            for(int i = 0; i < size; i++){
                System.out.printf("%d ",safety_idx[i]);
            }
            System.out.println();
            for(int i = 0; i < size; i++){
                for(int k = 0; k < K; k++){
                    System.out.print(Dg[k].pathTo(safety_idx[i]));
                    System.out.printf("%d",safety_idx[i]);
                    System.out.println();
                }
            }
        }

    }


    public static void main(String[] args) {
        In input = new In(args[0]);
        int N = input.readInt();
        int M = input.readInt();
        int K = input.readInt();
        EdgeWeightedDigraph Gg = new EdgeWeightedDigraph(N);
        EdgeWeightedDigraph Gv = new EdgeWeightedDigraph(N);
        makeGraphs(input,M,Gg,Gv);
        
        int[] warriors_source = new int[K];
        for(int i = 0; i < K; i++){
            int s = input.readInt();
            warriors_source[i] = s;
        }

        double[] distR = new double[N];
        int s = input.readInt();
        BellmanFordSP Bv = new BellmanFordSP(Gv,s);

        DijkstraSP[] Dg = new DijkstraSP[K];
        for(int i = 0; i < K; i++){
            Dg[i] = new DijkstraSP(Gg,Bv,warriors_source[i]);
        }
        
        checkDist(Dg,Bv,N,K);
    }
}
