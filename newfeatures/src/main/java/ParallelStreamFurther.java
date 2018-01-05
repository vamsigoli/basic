import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class ParallelStreamFurther {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //number of cores available for the JVM
        System.out.println("Number of processors " +Runtime.getRuntime().availableProcessors());

        //number of threads in the common pool
        //the number returned is one less than number of processors available
        //the one remaining is allocated to the main thread
        System.out.println("Parallelism in the common pool " + ForkJoinPool.commonPool().getParallelism());

        List<Integer> numbers20 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        //print the thread that is handling transform.
        //when we run this, we can see that three distinct batches of execution happen
        //in first set 7 numbers are processed. then all wait for the 2 seconds, second run then takes 7 more and the
        //rest in the third round.
        // Because the default common pool is 7 , all 7 are engaged for 7 numbers.the common pool now is full.
        //each thread waits 2 seconds after finishing. so we see a pause as all threads are in sleep mode.
        // so the next elements wait for threads. the next lot does the same and we see a pause for 2 seconds
        // then the remaining elements are handled
        numbers20.parallelStream().map(e -> transform(e)).forEach(e-> {});
        //the lesson learnt is if all threads in the common pool are busy, the system waits. so in a web app
        //if another web request comes it has to wait.

        //we can change the number of threads in the common pool by java.util.concurrent.ForkJoinPool.common.parallelism
        //when we give this property as 50, we can see no waiting and all numbers are done at once.
        //this is done at the jvm level and this may affect the system. if the jobs are cpu intensive and 50 threads are
        //present with 4 cores, and 50 jobs are spawned, most of the time is wasted in context switching

        //the terminal operation is forEach. if we execute forEach in a different thread pool, that pool is used
        //for all the operations.
        Stream<Integer> stream20 = numbers20.parallelStream().map(e -> transform(e));

        //stream created in the main thread and sent to process method.
        //process creates a thread pool and invokes the terminal operation
        //the above is by configuration and the below is by programatic thread pool creation. new pool created in process

        process(stream20);

        System.out.println("DONE in " + Thread.currentThread());

    }

    //main thread invokes process and waits for it to finish
    private static void process(Stream<Integer> stream20) throws InterruptedException {
        System.out.println("invoked in " + Thread.currentThread());
        //even though forEach is invoked in the new threadpool, as it is the terminal operation, all the other
        //operations before it also are also invoked in the same thread pool. so transform also happens in
        //the newly created thread pool only. when we run the program we see worker in the threadpool name of
        //transform method instead of common pool
        ForkJoinPool pool = new ForkJoinPool(50);
        pool.submit(() -> stream20.forEach(integer -> {}));
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
    }

    public static void printIt(int number) {
        System.out.println("printing number " + number + " in thread " + Thread.currentThread());
    }

    public static int transform(int number) {
        //sample transformation fuction. does nothing. just returns the same
        //when we print the current thread in sequential, only one name appears. in parallel we can see multiple
        System.out.println("transform number " + number + " in thread " +Thread.currentThread());
        sleep(2000);
        return number;
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
