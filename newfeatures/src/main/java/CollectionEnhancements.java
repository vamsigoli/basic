import java.util.*;

public class CollectionEnhancements {
    public static void main(String[] args) {

        //The asList returns ArrayList that is fixed size. so add will throw exception but it is mutable.
        //set method works as below. there are no Arrays.asSet or Arrays.asMap implementations
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        System.out.println(integers.getClass());
//        integers.add(5);
        integers.set(2, 7);
        System.out.println(integers);

        //we can get a set out of list by
        Set<Integer> integerSet = new HashSet<>(integers);

        //jdk 9 added of methods
        List<Integer> integers1 = List.of(1, 2, 3, 4);
        Set<Integer> integerSet1 = Set.of(1, 2, 3, 4);
        //type inference is good. all integers. and we get immutableCollection not just a ArrayList
        //also if the size is 1 or 2 it retuns a ArrayList1 or 2
        Map<String, Integer> stringIntegerMap = Map.of("a", 1, "b", 2, "c", 3);

        //type inference is also managed well here
        System.out.println(Map.of("a",1,"b",2,"c",3.4));
        System.out.println(Map.of("a",1,"b",2,"c",3.4).getClass());
         //but this does not work
        // Map<String,Float> stringFloatMap =  Map.of("a",1,"b",2,"c",3.4);
        //we need to explicitly manage the type of value
        Map<String, Double> stringDoubleMap = Map.of("a", Double.valueOf(1), "b", Double.valueOf(2), "c", Double.valueOf(3));

    }
}
