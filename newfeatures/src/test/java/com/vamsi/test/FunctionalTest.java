package com.vamsi.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalTest {

    @Test
    public void testLists() {

        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //return a new list which has values > 5
        List<Integer> listMoreThan5 = integerList.stream().filter(one -> one > 5).toList();

        //divisible by 2
        List<Integer> divisbleBy2 = integerList.stream().filter(one -> one % 2 == 0).toList();



    }
}
