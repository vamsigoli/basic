import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalEnhancements {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //map transforms Integer given by findFirst to Double. if empty returns the Double empty
        //the mapping function is invoked only if the returned value from findFirst is non empty
        //also if the invoked function retuns null, it is transformed into Optional empty
        //if the findFirst gives empty, the map will return an Optional empty of the return type of mapping function.
        //the mapping function is not invoked at all
        Optional<Double> first = numbers.stream().filter(e -> e > 5).findFirst().map(e -> Double.valueOf(e));
        Optional<Integer> first1 = numbers.stream().filter(e -> e > 5).findFirst();

        //orElse will give the integer value if findFirst gives non empty. if not it will return -1
        Integer first2 = numbers.stream().filter(e -> e > 5).findFirst().orElse(-1);

        //but there is no way we can get a new Optional if the findFirst returns a empty. -1 wrapped in Optional skin
        // this is added in java 9. this will let the program move forward with Optional integer
        Optional<Integer> first3 = numbers.stream().filter(e -> e > 5).findFirst().or(() -> Optional.of(-1));

        //invoke consumer if present. nothing is available to deal with not available.
        // we may have to go to imperative style for the else
        first3.ifPresent(e -> System.out.println(e));

        //jdk 9 introduces a new method to solve this problem. a function that takes nothing and gives nothing.
        //if you see the parameters, first one is consumer and the second one is actually a Runnable
        first3.ifPresentOrElse(e -> System.out.println(e), () -> System.out.println("nothing"));

        //lets say we have a method process that takes a stream and does some process as given below
        // now with first3 we cannot call process.
        // we have to check if first3 is non empty, construct a new stream and then call as below

        if (first3.isPresent()) {
            process(Stream.of(first3.get()));
        }

        //or we can construct an empty stream
        Stream<Integer> intermediate = first3.isPresent() ? Stream.of(first3.get()) : Stream.empty();
        process(intermediate);

        //in jdk 9 we got a stream method on Optional
        process(numbers.stream().filter(e -> e > 5).findFirst().stream());

    }

    //the advantage of process taking in a stream as parameter is it can take any number of values. 0 to n
    //if it was taking an optional, it would be taking only two values. 0 or 1
    //we can think of a Optional as a stream that can have zero or one element and hence the new method stream on it
    public static void process(Stream<Integer> numbers) {
        numbers.forEach(System.out::println);
    }
}
