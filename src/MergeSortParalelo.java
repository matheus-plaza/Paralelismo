import java.util.concurrent.RecursiveAction;

public class MergeSortParalelo extends RecursiveAction {
    private int[] array;
    // O threshold evita criar threads demais para arrays pequenos (overhead)
    private static final int THRESHOLD = 1000;

    public MergeSortParalelo(int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {
        if (array.length <= THRESHOLD) {
            new MergeSortSequencial().sort(array);
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        MergeSortParalelo leftTask = new MergeSortParalelo(left);
        MergeSortParalelo rightTask = new MergeSortParalelo(right);

        // O segredo do paralelismo está aqui:
        invokeAll(leftTask, rightTask);

        MergeSortSequencial.merge(array, left, right);
    }
}