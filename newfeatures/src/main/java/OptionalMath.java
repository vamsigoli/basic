import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalMath {
    public static Optional<Double> sqrt(Double d) {
        //this function retuns empty if the input is not valid. caller has to handle empty
        return d >= 0 ? Optional.of(Math.sqrt(d)) : Optional.empty();
        //instead of handling empty, i would prefer sending a NaN and let caller handle NaN
        // like
        //return d >= 0 ? Math.sqrt(d) : Double.NaN;
    }

    public static Optional<Double> inv(Double d) {
        return d != 0 ? Optional.of(1 / d) : Optional.empty();
    }

    //inverse of square root. converts double array to invsqrt double list
    public static List<Double> invsqrtTake1(double[] doubles) {
        List<Double> result = new ArrayList<>();

        //not a pure lambda function as we are modifying externally created object.
        //also parallalism is also lost in this case.
        //also the length of the list is not the same as length of doubles array. one to one mapping is lost
        //had we used the NaN instead of empty, we have one to one mapping. but need to handle NaN on client side
        Arrays.stream(doubles).boxed().forEach(d -> OptionalMath.sqrt(d)
                                                                .flatMap(OptionalMath::inv)
                                                                .ifPresent(result::add)
        );
        return result;
    }

    public static List<Double> invsqrtTake2(double[] doubles) {
        Function<Double, Stream<Double>> invSqrt = d -> OptionalMath.inv(d).flatMap(OptionalMath::sqrt)
                                                                           .map(Stream::of)
                                                                           .orElseGet(Stream::empty);

        // the trick used here is -- we want to work on single double element. but we got a stream of
        // doubles as input. so we wrote a function that takes a double and returns a stream of doubles which
        // contains only one element. by sending a stream we could use the functional style and move forward
        // now the flatMap below takes the stream of stream of doubles and converts
        // into a steam of doubles which is collected into a list

        List<Double> result = Arrays.stream(doubles).boxed().flatMap(invSqrt).collect(Collectors.toList());

        return  result;
    }


}
