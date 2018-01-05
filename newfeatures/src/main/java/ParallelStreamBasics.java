import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamBasics {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //used a sequential stream. runs sequentially. as list is ordered, elements are printed in order received
        //the call stream() on numbers returns a sequential stream
        numbers.stream().map(ParallelStreamBasics::transform).forEach(System.out::println);

        //if we are the creator of stream by invoking the stream call, we can make it parallel as below
        numbers.parallelStream().map(ParallelStreamBasics::transform).forEach(System.out::println);

        //if we have a function that takes input as stream then we can make it parallel as below
        streamReceiver(numbers.stream());
    }
    private static void streamReceiver(Stream<Integer> stream) {
        //use the incoming stream in parallel mode
        //if the incoming stream is already a parallel, it returns itself.
        stream.parallel().map(ParallelStreamBasics::transform).forEach(System.out::println);

        //we can know if incoming stream is parallel or not as below
        if (stream.isParallel()) stream.map(ParallelStreamBasics::transform).forEach(System.out::println);

        //we can make a parallel stream as sequential
        if (stream.isParallel()) {
            stream.sequential().map(ParallelStreamBasics::transform).forEach(System.out::println);
        }

        //the last operation on the stream wins whether the execution will be done serial or parallel
        //as the last one is sequential the execution is done sequential
        //intellij flags the parallel call as redundant as it becomes sequential later
        stream.parallel().map(ParallelStreamBasics::transform).sequential().forEach(System.out::println);
    }

    public static int transform(int number) {
        //sample transformation fuction. does nothing. just returns the same
        //when we print the current thread in sequential, only one name appears. in parallel we can see multiple
        System.out.println("transform " + Thread.currentThread());
        return number;
    }
}
