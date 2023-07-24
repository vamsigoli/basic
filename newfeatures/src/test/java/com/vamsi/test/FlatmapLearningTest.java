package com.vamsi.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FlatmapLearningTest {
    @Test
    public void testFlatMap() {

        //Another neat example is
        Order order1 = new Order(1L);
        order1.addItem(new LineItem(1L, 3D));
        order1.addItem(new LineItem(2L, 6D));
        Order order2 = new Order(1L);
        order2.addItem(new LineItem(1L, 3D));
        order2.addItem(new LineItem(2L, 6D));

        //each order is mapped to its lineItemStream. so the return value is stream of order streams.
        Stream<Stream<LineItem>> lineItemStreamStream = Stream.of(order1, order2).map(one -> one.items.stream());


        //if we want the stream of lineitems for an order stream, we use flatMap which flattens streams to single stream
        Stream<LineItem> lineItemStream = Stream.of(order1, order2).flatMap(one -> one.items.stream());



        List<String> familyMembers = List.of("Vamsi", "Geetha", "Acchu", "Akku");
        List<String> cities = List.of("Bangalore", "Atlanta", "Tenali");

        //let's say we want a cartesian product of familyMembers to cities using a stream

        //Stream.of will use the type of the object passed. so if we do Stream.of(familyMembers)
        //it is doing a Stream of List with only one element in the stream
        //we had to convert the list into array of strings so that Stream.of returns a stream of strings.

        //the difference between flatMap and map can be clear from the two declarations below

        //java.util.stream.Stream<T>
        //public abstract <R> Stream<R> map(java.util.function.Function<? super T, ? extends R> mapper )

        Stream<Stream<PersonCity>> personCityMapStream = Stream.of(familyMembers.toArray(new String[0]))
                .map(person ->
                        Stream.of(cities.toArray(new String[0]))
                                .map(city -> new PersonCity(person, city)));
        //stream 1 -> (vamsi,bangalore), (vamsi, atlanta), (vamsi,tenali)
        //stream 2 -> (geetha, bangalore), (geetha,atlnata),(geetha,tenali)
        //...



        //the definition of map is that it takes a stream of T and converts into a stream of R
        //here we have taken a person and applied a map function that returns a steam of cities
        //where each city mapped to a personCity. so a person is converted to stream of personCity
        //T is a string, person and R is stream of PersonCity
        // and the result of whole thing is a stream of personCity streams.

        //java.util.stream.Stream<T>
        //public abstract <R> Stream<R> flatMap(java.util.function.Function<? super T, ? extends Stream<? extends R>> mapper )

        Stream<PersonCity> personCityFlatMapStream = Stream.of(familyMembers.toArray(new String[0]))
                .flatMap(person ->
                        Stream.of(cities.toArray(new String[0]))
                                .map(city -> new PersonCity(person, city)));

        //a person is converted to a stream of personCity. But as its flatMapped, the individual streams of
        //personCity for each person is joined into one single stream of PersonCity

        //the declaration of both map and flatMap are given above.

        //the function after the arrow at line 46 and line 65 is same in both cases.
        //for map as defined at line 42 result of activity at 45 is the R
        //map takes that R and puts it into a stream. so each element of Stream<R> is a stream of personCity

        //for flatMap as defined at line 61 result of activity at 65 is the Stream<R>
        //flatMap takes that Stream<R> and concatenates into one stream. so each element of Stream<R> is a personCity

        //all the above is given in one stack overflow document
        //https://stackoverflow.com/questions/26684562/whats-the-difference-between-map-and-flatmap-methods-in-java-8
        //stored in vamsi-laptop/tech-june23
    }
    static class PersonCity {
        String name;
        String city;

        public PersonCity(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
    static class Order {
        Long orderId;
        List<LineItem> items = new ArrayList<>();

        public void addItem(LineItem item) {
            items.add(item);
        }

        public Order(Long orderId) {
            this.orderId = orderId;
        }
    }
    static class LineItem {
        Long productId;
        Double quantity;

        public LineItem(Long productId, Double quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}
