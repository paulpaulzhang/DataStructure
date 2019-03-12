public class GraphWithMatrix<V, W extends Comparable<W>, I> {

    private VertexM[] vertices;
    private Edge[][] edges;
    private int size;

    @SuppressWarnings("unchecked")
    public GraphWithMatrix(VertexM[] vertices) {
        this.vertices = vertices;
        this.size = vertices.length;
        Edge[][] edges = new Edge[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                edges[i][j] = new Edge(Integer.MAX_VALUE, null);
            }
        }
    }

    public void union(int i, int j, W weight, I info) {
        edges[i][j].weight = edges[j][i].weight = weight;
        edges[i][j].info = edges[j][i].info = info;
    }
}

class Edge<W extends Comparable<W>, I> {
    W weight;
    I info;

    public Edge(W weight, I info) {
        this.weight = weight;
        this.info = info;
    }
}

class VertexM<V> {
    V value;

    public VertexM(V value) {
        this.value = value;
    }
}