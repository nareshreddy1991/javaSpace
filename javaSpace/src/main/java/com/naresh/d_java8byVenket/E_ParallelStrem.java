package com.naresh.d_java8byVenket;

import java.util.Arrays;
import java.util.List;

public class E_ParallelStrem {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //imperative style coding
        /*
        It's having accidental complexcity
        Difficult to understand the code
        Difficult to parallelize
         */
        int sum = 0;
        for (Integer e : integers) {
            if (e % 2 == 0)
                sum += e;
        }

        //functional style
        /*
        Advantages:
            Easy to read & understand, single pass through, no need to go up and down to understand
            We can easily convert this to parallel stream
            THis is immutable

         */
        integers
//                .stream()
//                .parallelStream()  //its like a switch we can easily convert to parallel
                .stream().parallel() // if stream is created somewhere it is useful
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e)
                .sum();

        integers
                .parallelStream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e)
                .sequential()   //This complete stream runs in sequential, the last one has the priority
                .sum();

        /*
        TODO
        Streams    (java8)                                      Reactive Stream
        Sequential vs parallel                                  Synch vs Async
        entire stream is either Seq or parallel, no segments    Have segments
         */

        /*
        Thread history
        Java 1 - Thread was introduced
        Java 5 - Executor services
            here we have pool induced deadlock issue -- if pool is having two threads, one thread received a task and divided into two, one task will be picked by other thread
            and current thread keeps waiting for some other thread to complete the task. THis causes deadlock.
        java 7 -- Fork join pool (work stealing ) -- here that issue is resolved, the thread that splited the job also participate in solving the problem.
        TODO finally parallel streams uses common forkjoin pool threads
         */

        //TODO order of parallel stream
        integers.stream()
                .parallel()
                .filter(e -> e > 20)//it runs in any order
                .map(String::valueOf)//transformation happens in any order by different threads from common FJP
//                .forEach(System.out::println); //order is not quarented
                .forEachOrdered(System.out::println); //order is quarented, once all the previous steps are done then it will start running

        //TODO parallel with reduce
        integers.stream()
                .parallel()
                .filter(e -> e > 20)
//                .reduce(0, (carray, e) -> carray + e);// THis works well
                .reduce(10, (carray, e) -> carray + e);// having issue - when we pass identity value we will get wrong results(10 is identity value not initial value)
                /*
                Reduce doesn't take initial value, it takes identity value
                int + identity is 0 x+0=x  (for addition 0 is identity, it will work fine in parallel stream)
                int * identity is 1 x*1=x   (for multiplication 1 is identity value)
                 */
        //TODO How many threads should I create?
        /*
        Computation intensive           vs      IO intensive
        For computation intensive   Threads <= no of cores
        For IO intensive            Threads > no of cores
                    No of cores
        #T = ---------------------
                1- blocking factors
              0<= blocking factors <1
         in FJP if you have 8 cores, pool has 7 threads , one will allocated to main thread
         We can create FJP with custom number of threads, when a terminal operation is running in custom FJP it will use all of it threads
         */

        //TODO parallel with lazy evaluation
        integers.stream()
                .filter(e->e>5)
//                .findFirst()//it is ordered, either its parallel or seq we always get correct result, once the result is found it give result
                .findAny()//it is not ordered, it gives random results in parallel streams and its evaluates all the element
                .orElse(null);
    }
}
