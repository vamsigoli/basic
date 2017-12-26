import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.sqrt;
import static java.util.stream.LongStream.range;
import static java.util.stream.LongStream.rangeClosed;


//example to demostrate the problems with ForkJoinPool when used in parallel stream operations
public class ParallelTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(() -> runTask(5)); //incorrect task
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(0));
        es.execute(() -> runTask(5));
        es.execute(() -> runTask(0));


        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);
    }

    private static void runTask(int delay) {
        range(1, 1_000_000).parallel().filter(ParallelTest::isPrime).peek(i -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).max()
                .ifPresent(max -> System.out.println(Thread.currentThread() + " "  + LocalDateTime.now() + " " + max));
    }

    public static boolean isPrime(long n) {
        return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }
}