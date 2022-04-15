import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static hu.elte.t8hgxr.Fibonacci.*;


public class FibonacciTests
{
    @Test
    public void testFiboRecursiveZero()
    {
        int result = fiboRecursive(0);

        assertEquals(0, result);
    }

    @Test
    public void testFiboRecursiveOne()
    {
        int result = fiboRecursive(1);

        assertEquals(1, result);
    }
    @Test
    public void testFiboRecursiveNegative()
    {
        assertThrows(IllegalArgumentException.class , () -> fiboRecursive(-4));
    }

    @Test
    public void testFiboRecursiveSix()
    {
        int result = fiboRecursive(6);

        assertEquals(8, result);
    }

    @Test
    public void testFiboIterativeZero()
    {
        int result = fiboIterative(0);

        assertEquals(0, result);
    }

    @Test
    public void testFiboIterativeOne()
    {
        int result = fiboIterative(1);

        assertEquals(1, result);
    }
    @Test
    public void testFiboIterativeNegative()
    {
        assertThrows(IllegalArgumentException.class , () -> fiboIterative(-4));
    }

    @Test
    public void testFiboIterativeSix()
    {
        int result = fiboIterative(6);

        assertEquals(8, result);
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void testFiboRecursiveInBulk(int n, int expected)
    {
        assertEquals(expected, fiboRecursive(n));
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void testFiboIterativeInBulk(int n, int expected)
    {
        assertEquals(expected, fiboIterative(n));
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void testBothFiboInBulk(int n, int expected)
    {
        assertAll
        (
            () -> assertEquals(expected, fiboIterative(n)),
            () -> assertEquals(expected, fiboRecursive(n))
        );

    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 6, 7, 8, 12 })
    public void compareRecursiveAndIterativeResults(int n)
    {
        assertEquals(fiboIterative(n), fiboRecursive(n));
    }
}
