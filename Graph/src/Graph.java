import java.util.LinkedList;
import java.util.Queue;

public class Graph<V> {
    private Vertex<V>[] vertices;
    private int size;

    @SuppressWarnings("unchecked")
    public Graph(V[] values) {
        this.size = values.length;
        vertices = new Vertex[size];
        for (int i = 0; i < size; i++) {
            vertices[i] = new Vertex<>(values[i]);
        }
    }

    public void union(int i, int j, int weight, String info) {
        unionHelp(i, j, weight, info);
        unionHelp(j, i, weight, info);
    }

    private void unionHelp(int i, int j, int weight, String info) {
        Node node = new Node(i, weight, info);
        Node prev = vertices[j].dummyHead;
        while (prev.next != null)
            prev = prev.next;
        prev.next = node;
    }

    public void DFS() {
        boolean[] visit = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!visit[i])
                DFS(visit, i);
        }
    }

    private void DFS(boolean[] visit, int v) {
        visit[v] = true;
        Node prev = vertices[v].dummyHead;
        while (prev.next != null) {
            int w = prev.next.direction;
            if (!visit[w])
                DFS(visit, w);
            prev = prev.next;
        }
    }

    public void BFS() {
        boolean[] visit = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (!visit[i])
                BFS(visit, i);
        }
    }

    private void BFS(boolean[] visit, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int w = queue.remove();
            visit[w] = true;
            Node prev = vertices[w].dummyHead;
            while (prev.next != null) {
                w = prev.next.direction;
                if (!visit[w])
                    queue.add(w);
                prev = prev.next;
            }
        }
    }


    public void prim(int v) {
        Edges[] close = new Edges[size];
        for (int i = 0; i < close.length; i++) {
            close[i] = new Edges(Integer.MAX_VALUE, i);
        }

        Node prev = vertices[v].dummyHead;
        while (prev.next != null) {
            int w = prev.next.direction;
            close[w].weight = prev.next.weight;
            close[w].pre = v;
            prev = prev.next;
        }
        close[v].weight = 0;
        for (int i = 0; i < size - 1; i++) {
            int k = getMin(close);
            close[k].weight = 0;
            prev = vertices[k].dummyHead;
            while (prev.next != null) {
                int w = prev.next.direction;
                if (close[w].weight != 0 && prev.next.weight < close[w].weight) {
                    close[w].weight = prev.next.weight;
                    close[w].pre = k;
                }
                prev = prev.next;
            }
        }
    }

    private int getMin(Edges[] close) {
        int min = Integer.MAX_VALUE;
        int k = -1;

        for (int i = 0; i < close.length; i++) {
            if (close[i].weight != 0 && close[i].weight < min) {
                min = close[i].weight;
                k = i;
            }
        }
        return k;
    }
}

class Vertex<V> {
    V value;
    Node dummyHead;

    public Vertex(V value) {
        this.value = value;
        dummyHead = new Node(-1, Integer.MAX_VALUE, null);
    }

}

class Node {
    int weight;
    String info;
    int direction;
    Node next;

    public Node(int direction, int weight, String info) {
        this.weight = weight;
        this.info = info;
        this.direction = direction;
        this.next = null;
    }
}

class Edges {
    int weight;
    int pre;

    public Edges(int weight, int pre) {
        this.weight = weight;
        this.pre = pre;
    }
}