import java.util.function.Function;

public class CurryExample {
    public Integer sumWIth2Params(Integer a, Integer b) {
        return a + b;
    }

    //same thing can be done in currying as follows
    public Integer sumWithCurry(Integer a, Integer b) {
        Function<Integer, Function<Integer, Integer>> curryAdder = u -> v -> u + v;
        return curryAdder.apply(a).apply(b);
    }

    //the above function on line 10 is actually done behind the scenes as follows

    // a simple function that takes one integer and returns another integer
    Function<Integer, Integer> doubleValue = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer integer) {
            return integer * 2;
        }
    };

    // and you can invoke the function as doubleValue.apply(50);

    //taking it a step further, read the first chapter of functional java epub
    //the function that takes u should return a function that takes v and returns u + v
    //so the signature would be
    Function<Integer, Function<Integer,Integer>> step1 = new Function<Integer, Function<Integer, Integer>>() {
        @Override
        public Function<Integer, Integer> apply(Integer a) {
            Function<Integer,Integer> step2 = new Function<Integer, Integer>() {
                @Override
                public Integer apply(Integer b) {
                    return a + b; // notice a is outside the scope of the function. similar to u above
                }
            };
            return step2;
        }
    };

    public Integer sumWithCurry2(Integer a, Integer b) {
        return step1.apply(a).apply(b);
    }

    public static void main(String[] args) {
        CurryExample curryExample = new CurryExample();
        System.out.println(curryExample.sumWithCurry2(5, 10));
    }
}
