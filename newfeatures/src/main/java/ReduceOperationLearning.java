import java.util.Arrays;
import java.util.List;

public class ReduceOperationLearning {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3,4);

        System.out.println("step 1 : ");
        int step1 = numbers.stream().reduce(0, (total, e) -> add(total, e));
        //the above is a sequential opration that starts with (0,1) -> 1 , (1,2) -> 3, (3,3) -> 6 , (6,4) -> 10
        System.out.println(step1);

        System.out.println("step 2 : ");
        int step2 = numbers.stream().parallel().reduce(0, (a, b) -> add(a, b));
        System.out.println(step2);
        //when we run in parallel the results are much more complex.
        //the operation is to reduce the stream into single element. for that two elements are combined into one
        //in the beginning for each element, identity is used as the other element. so we get
        // (0,1), (0,2), (0,3), (0,4)
        //    (1,2)         (3,4)              or (1,4)  (2,3)     or     (1,3) (2,4)
        //       (3,         7)                     (5  ,  5)                (4, 6)
        //             10                               10                     10
        // as there is only single element return.

        //the crucial thing out of all this is the identity value. if we give the identity to be wrong,
        //the whole reduce operation we want will result in wrong value

        System.out.print("step 3 : ");
        int step3 = numbers.stream().parallel().reduce(21, (a, b) -> add(a, b));
        System.out.println(step3);
        //the above will return wrong value for the expected reduce operation which is summation
        //because we need to use 21 as identity not as initial value. in case of serial it works
        //because identity is used only once. but for parallel it is used with all the elements once.

        System.out.print("step 4 : ");
        int step4 = numbers.stream().reduce(21, (a, b) -> add(a, b));
        System.out.println(step4);

        //to make reduce work in parallel scenario, we need to use the initial value outside of streaming
        System.out.print("step 5 ");
        int step5 = numbers.stream().parallel().reduce(0, (a, b) -> add(a, b)) + 21;
        System.out.println(step5);

    }

    private static Integer add(Integer total, Integer e) {
        System.out.print("adding " + e + " to total " + total);
        int result =  total + e;
        System.out.println(" resulting in " + result);
        return result;
    }
}
