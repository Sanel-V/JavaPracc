package hu.elte.t8hgxr;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyLambdas
{
    public static int counter = 0;
    public static Runnable hello()
    {
        return () -> System.out.println("Hello");
    }

    public static Runnable myRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            System.out.println("myRunnable");
        }
    };

    public static Runnable myRunnable2 = () ->
    {
        System.out.println("myRunnable2");
    };

    public static Runnable count = () ->
    {
        counter++;
        System.out.println(counter);
    };

    public static Function<Integer, Integer> counter2 = num ->
    {
        return num + 1;
    };

    public static Runnable counter3 = new Runnable()
    {
        static int number = 0;
        @Override
        public void run()
        {
            ++number;
            System.out.println(number);
        }
    };

    public static BiFunction<
        Map<String, Integer>,
        Map<String, Integer>,
        Map<String, Integer>> concat1 = (map1, map2) ->
            {
                Map<String, Integer> concatMap =
                    new HashMap<>(map1);

                map2.forEach(
                    (String k, Integer v) ->
                    {
                        Integer val = concatMap.get(k);
                        if(val == null)
                        {
                            concatMap.put(k, v);
                        }else
                        {
                            concatMap.put(k, v + val);
                        }
                    }
                );
                return concatMap;
            };
    public static BiFunction<
        Map<String, Integer>,
        Map<String, Integer>,
        Map<String, Integer>> concat2 = (map1, map2) ->
            {
                Map<String, Integer> concatMap =
                    new HashMap<>(map1);
                map2.forEach(
                    (String k, Integer v) ->
                    {
                        concatMap.merge(k, v, (valthis, valthat) -> valthis + valthat);
                    }
                );
                return concatMap;
            };



    public static void main(String[] args)
    {
        hello().run();
        myRunnable.run();
        myRunnable2.run();

        for (int i = 0; i < 5; i++)
        {
            count.run();
        }

        for (int i = 0; i < 4; i++)
        {
            System.out.println(counter2.apply(i));
        }

        for (int i = 0; i < 7; i++)
        {
            counter3.run();
        }

        Map<String, Integer> map1 = Map.of("a", 1, "b", 3, "c", 0);
        Map<String, Integer> map2 = Map.of("a", 2, "b", 3, "d", 4);

        var map4 = concat1.apply(map1, map2);
        Map<String, Integer> map3 = concat2.apply(map1, map2);

        Arrays.sort(args, Comparator.comparingInt(String::length));

    }
}
