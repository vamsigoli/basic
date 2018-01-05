import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamEnhancements {
    public static void main(String[] args) {
        //there is a filter method that is applied on each object to decide whether it need to go through
        //there is a limit method that is having fixed number as input
        //there is a skip method again that takes fixed number as input

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,2,4,6);

        //filter determines whether it goes to next level on each object. predicate is on each object
        numberList.stream().filter(e -> e < 6).forEach(System.out::println);

        //limit has predicate that is fixed. determines the number of elements that can go and close gate after that
        numberList.stream().limit(2).forEach(System.out::println);

        //same is the case with skip. determines the number of elements that cannot go and open the gate after that
        numberList.stream().skip(2).forEach(System.out::println);

        //java 9 introduced takeWhile which will close the gate when the condition is satisfied.
        //in the filter above, the 2, 4 in the last will go through but takeWhile will close the gate once it sees 6
        //this is the cousin of limit
        numberList.stream().takeWhile(e -> e < 6).forEach(System.out::println);

        //the cousin of skip is dropWhile. same logic as takeWhile.
        numberList.stream().dropWhile(e -> e <6).forEach(System.out::println);

        //what if we want to achieve the functionality as below
        for (int i =0; i < 10; i = i+2) {
            System.out.println(i);
        }

        //this would be done using streams as below. the predicate was outside the iterate
        IntStream.iterate(0, i -> i + 2).limit(9).forEach(System.out::println);

        //in jdk 9 a new method is added for the predicate also. the predicate is optional as this is overloading above
        IntStream.iterate(0, i -> i < 10, i -> i + 2).forEach(System.out::println);

    }

}
