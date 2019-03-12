import java.util.Random;

public class Main {

    public static double testUnionFind(UF uf, int m) {

        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
        UnionFind1 unionFind1 = new UnionFind1(size);
        UnionFind2 unionFind2 = new UnionFind2(size);
        UnionFind3 unionFind3 = new UnionFind3(size);
        UnionFind4 unionFind4 = new UnionFind4(size);
        UnionFind5 unionFind5 = new UnionFind5(size);
        UnionFind6 unionFind6 = new UnionFind6(size);

//        System.out.println("find1: " + testUnionFind(unionFind1, m));
//        System.out.println("find2: " + testUnionFind(unionFind2, m));
        System.out.println("find3: " + testUnionFind(unionFind3, m));
        System.out.println("find4: " + testUnionFind(unionFind4, m));
        System.out.println("find5: " + testUnionFind(unionFind5, m));
        System.out.println("find6: " + testUnionFind(unionFind6, m));

    }
}
