import java.util.Arrays;
import java.util.List;


public class OrderingInStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 89, 10);

        //process the numbers in sequential manner in the order received and print as they are processed
        //as they are processed sequentially, they are also printed sequentially
        numbers.stream().map(OrderingInStreams::transform).forEach(System.out::println);

        //process the numbers in parallel. printing will happen as they are processed
        //as the stream is parallel stream, forEach can kickoff one thread for each element to invoke
        //the consumer. we can know if a new thread is used by printing the thread name
        //forEach is a terminal operation
        numbers.stream().parallel().map(OrderingInStreams::transform).forEach(System.out::println);

        //invoke printIt instead of println. in parallel mode, each element will have its own thread
        numbers.stream().parallel().map(OrderingInStreams::transform).forEach(OrderingInStreams::printIt);

        //in sequential mode all elements will be printed in the same thread which is the main thread
        numbers.stream().map(OrderingInStreams::transform).forEach(OrderingInStreams::printIt);

        //terminal operations like forEach can make the incoming elements processed in order they were
        //encountered by the stream. List is ordered while Set is not. as this is a parallel stream
        //each element is given to a separate thread by the forEachOrdered. But that element is not
        //processed by the thread until all the elements before it are not processed
        numbers.stream().map(OrderingInStreams::transform).forEachOrdered(OrderingInStreams::printIt);

        //ordering is important for methods like findFirst which has to return first element
        //the filter can be executed in parallel but the first element that satisfies this condition
        //in a ordered list is 4. in a non ordered set any element that satisfies this condition can be returned
        numbers.stream().parallel().filter(e -> e> 4).findFirst();
    }

    public static void printIt(int number) {
        System.out.println("print it " + number + " in thread: " + Thread.currentThread());
    }

    public static int transform(int number) {
        //sample transformation fuction. does nothing. just returns the same
        //when we print the current thread in sequential, only one name appears. in parallel we can see multiple
        System.out.println("transform " + number + " in thread: " + Thread.currentThread());
        return number;
    }
}
