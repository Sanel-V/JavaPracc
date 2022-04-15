package hu.elte.t8hgxr;

public class Fibonacci
{
    /*
    f(n) = f(n-1) + f(n-2)
    f(1) = 1
    f(0) = 0

     */
    public static int fiboRecursive(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Fibonacci sequence undefined on negative numbers");
        }
        if(n < 2)
        {
            return n;
        }
        return fiboRecursive(n - 1) + fiboRecursive(n - 2);
    }
    public static int fiboIterative(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Fibonacci sequence undefined on negative numbers");
        }
/*
        if(n < 2)
        {
            return n;
        }
*/
        int first = 0;
        int second = 1;
        //int res = first;
        for(int i = 0; i < n; ++i)
        {/*
            res = first + second;
            first = second;
            second = res;
            */
            second += first;
            first = second - first;

        }
        return first;
        //return res;
    }




}
