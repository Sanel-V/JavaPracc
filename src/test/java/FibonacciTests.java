import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static hu.elte.t8hxgr.Fibonacci.*;


public class FibonacciTests
{
    @Test
    public void TestFiboRecursiveZero()
    {
        int result = FiboRecursive(0);

        assertEquals(0, result);
    }

    @Test
    public void TestFiboRecursiveOne()
    {
        int result = FiboRecursive(1);

        assertEquals(1, result);
    }
    @Test
    public void TestFiboRecursiveNegative()
    {
        assertThrows(IllegalArgumentException.class , () -> FiboRecursive(-4));
    }

    @Test
    public void TestFiboRecursiveSix()
    {
        int result = FiboRecursive(6);

        assertEquals(8, result);
    }

    @Test
    public void TestFiboIterativeZero()
    {
        int result = FiboIterative(0);

        assertEquals(0, result);
    }

    @Test
    public void TestFiboIterativeOne()
    {
        int result = FiboIterative(1);

        assertEquals(1, result);
    }
    @Test
    public void TestFiboIterativeNegative()
    {
        assertThrows(IllegalArgumentException.class , () -> FiboIterative(-4));
    }

    @Test
    public void TestFiboIterativeSix()
    {
        int result = FiboIterative(6);

        assertEquals(8, result);
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void TestFiboRecursiveInBulk(int n, int expected)
    {
        assertEquals(expected, FiboRecursive(n));
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void TestFiboIterativeInBulk(int n, int expected)
    {
        assertEquals(expected, FiboIterative(n));
    }

    @ParameterizedTest(name = "f({0}) = {1}")
    @CsvSource(value = {"0, 0", "1, 1", "2, 1", "6, 8", "7, 13", "23, 28657"})
    public void TestBothFiboInBulk(int n, int expected)
    {
        assertAll
        (
            () -> assertEquals(expected, FiboIterative(n)),
            () -> assertEquals(expected, FiboRecursive(n))
        );

    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 6, 7, 8, 12 })
    public void CompareRecursiveAndIterativeResults(int n)
    {
        assertEquals(FiboIterative(n), FiboRecursive(n));
    }
}
