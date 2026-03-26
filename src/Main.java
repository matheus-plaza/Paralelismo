import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int size = 10_000_000;
        int[] array1 = new Random().ints(size, 0, size).toArray();
        int[] array2 = array1.clone();

        // Teste Sequencial
        long start = System.currentTimeMillis();
        new MergeSortSequencial().sort(array1);
        System.out.println("Sequencial: " + (System.currentTimeMillis() - start) + "ms");

        // Teste Paralelo
        ForkJoinPool pool = new ForkJoinPool();
        start = System.currentTimeMillis();
        pool.invoke(new MergeSortParalelo(array2));
        System.out.println("Paralelo: " + (System.currentTimeMillis() - start) + "ms");
    }
}