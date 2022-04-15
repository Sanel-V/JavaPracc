package hu.elte.t8hxgr;

import jdk.jfr.StackTrace;

public class Fibonacci
{
    /*
    f(n) = f(n-1) + f(n-2)
    f(1) = 1
    f(0) = 0

     */
    public static int FiboRecursive(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Fibonacci sequence undefined on negative numbers");
        }
        if(n < 2)
        {
            return n;
        }
        return FiboRecursive(n - 1) + FiboRecursive(n - 2);
    }
    public static int FiboIterative(int n)
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
