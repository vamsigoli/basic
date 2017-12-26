import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;


// this example is to demonstrate how we can limit the parallal stream to a particular forkJoinPool
// otherwise, the standard fork join pool will be used for parallal streams and one long running task
// in one parallal stream may cause performance problems at the jvm level. this code is done when doing
// fork join pool research in the prep doc
public class ForkJoinPoolExample {
    public static void main(String args[]) throws InterruptedException {
        List<Integer> numbers = buildIntRangeOf0To99();
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        Thread t1 = new Thread(() -> forkJoinPool.submit(() -> {
            numbers.parallelStream().forEach(n -> {
                System.out.println("sleeping for number " + n);
                sleep(5000);
                System.out.println("Loop 1 : " + Thread.currentThread() + " work number " + n);
            });
        }).invoke());

        ForkJoinPool forkJoinPool2 = new ForkJoinPool(4);
        Thread t2 = new Thread(() -> forkJoinPool2.submit(() -> {
            numbers.parallelStream().forEach(n -> {
                System.out.println("sleeping for number " + n);
                sleep(5000);
                System.out.println("Loop 2 : " + Thread.currentThread() + " work number " + n);
            });
        }).invoke());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static List<Integer> buildIntRangeOf0To99() {
        List<Integer> numbers = new ArrayList<>(5);
        for (int i = 0; i < 100; i++) numbers.add(i);
        return Collections.unmodifiableList(numbers);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}