import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LazynessLearning {
    private static List<Person> createPersons() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sandra", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Joshua", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 42),
                new Person("Liam", Gender.MALE, 27),
                new Person("Lindsy", Gender.FEMALE, 18)
        );
    }

    public static void main(String[] args) {
        List<Person> persons = createPersons();
        Set<Person> personSet = new HashSet<>(createPersons());

        System.out.println("------------RUN START--------");
        //find the name in uppoercase of the first female older than 30
        //as list is ordered and we have a sequential stream the execution
        //will stop as soon as we reach Paula as we asked only one and the first one
        System.out.println("sequential run " + persons.stream()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(person -> person.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NONE FOUND"));

        //even though we run in parallal, as findFirst is running on ordered stream
        //it still returns PAULA all the time.
        System.out.println("parallel run " + persons.stream().parallel()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(person -> person.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NONE FOUND"));

        //as set is not ordered, parallel execution on the set may give any element that
        //satisfies the condition. the result may be PAULA or JILL
        //sequential execution also may return any result as the next element returned by set has no order
        //as soon as the first element is found no further evaluation is done
        System.out.println("steam parallel set " + personSet.stream().parallel()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(person -> person.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NONE FOUND"));

        System.out.println("steam sequential set " + personSet.stream()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(person -> person.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .findFirst()
                .orElse("NONE FOUND"));

        //if we do a findAny all bets are off.
        System.out.println("steam on findAny of list " + persons.stream().parallel()
                .filter(person -> person.getGender() == Gender.FEMALE)
                .filter(person -> person.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .findAny()
                .orElse("NONE FOUND"));

    }

}
