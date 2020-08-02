package lesson7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Calculator {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public float[] calc() {
        float[] arr = initArray();

        long a = System.currentTimeMillis();
        for (int i=0; i<SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
        System.out.println(System.currentTimeMillis() - a);
        return arr;
    }

    public float[] calcParallel() throws InterruptedException, ExecutionException {
        float[] arr = initArray();

        long a = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(2);
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        List<CalculatorInner> tasks = new ArrayList<>();
        tasks.add(new CalculatorInner(a1, 0, HALF, 0));
        tasks.add(new CalculatorInner(a2, 0, HALF, HALF));

        List<Future<Void>> futures = service.invokeAll(tasks);

        for (Future<Void> future : futures) {
            future.get();
        }

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        System.out.println(System.currentTimeMillis() - a);

        return arr;
    }

    public float[] calcParallelWithoutCopy() throws InterruptedException, ExecutionException {
        float[] arr = initArray();

        long a = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(2);

        List<CalculatorInner> tasks = new ArrayList<>();
        tasks.add(new CalculatorInner(arr, 0, HALF, 0));
        tasks.add(new CalculatorInner(arr, HALF, HALF, 0));

        List<Future<Void>> futures = service.invokeAll(tasks);

        for (Future<Void> future : futures) {
            future.get();
        }

        System.out.println(System.currentTimeMillis() - a);

        return arr;
    }

    protected float[] initArray() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    private class CalculatorInner implements Callable<Void> {
        private float[] arr;
        private int start;
        private int length;
        private int shift;

        private CalculatorInner(float[] arr, int start, int length, int shift) {
            this.arr = arr;
            this.start = start;
            this.length = length;
            this.shift = shift;
        }

        @Override
        public Void call() {
            int iCalc;
            for (int i=start; i<start + length; i++) {
                iCalc = i + shift;
                arr[i] = (float)(arr[i] * Math.sin(0.2f + iCalc / 5f) * Math.cos(0.2f + iCalc / 5f) * Math.cos(0.4f + iCalc / 2f));
            }

            return null;
        }
    }
}
