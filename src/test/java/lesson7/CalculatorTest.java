package lesson7;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.ExecutionException;

public class CalculatorTest {

    @Test
    public void calcTest() throws ExecutionException, InterruptedException {
        Calculator calculator = new Calculator();

        float[] a1 = calculator.calc();
        float[] a2 = calculator.calcParallel();
        float[] a3 = calculator.calcParallelWithoutCopy();
        Assertions.assertArrayEquals(a1, a2);
        Assertions.assertArrayEquals(a1, a3);
    }
}
