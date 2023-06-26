import java.util.function.BiFunction;
import java.util.function.Function;

public class PersonBuilder {
    //lets say we have a Person1 object that have 4 properties. firstname, lastname, ssn, gender
    //we can have a constructor that takes four inputs and returns that instance.
    //and we have Person2 object that has two properties firstName, lastName

    //we can have a BiFunction that takes two params and return a Person2
    //like Person2 person2 = twoArgCreator.apply("Vamsi", "Goli");
    static BiFunction<String, String, Person2> twoArgCreator = (firstName, lastName) -> new Person2(firstName, lastName);

    //but there are no triFunctions or QuadraFunctions in java.util. this is where currying comes into picture
    //we can write a curry function for each  arg and finally invoke the  constructor
    static Function<String, Function<String, Person2>> twoArgConstructorCurry = firstName -> lastName -> new Person2(firstName, lastName);
    //and its usage as Person2 person21 = twoArgConstructorCurry.apply("Vamsi").apply("Goli");

    //for a 4 arg curry function, it will be
    static Function<String,Function<String,Function<String,Function<String,Person1>>>> fourArgCurry =
            firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);
    //and can be invoked as Person1 person1 = fourArgCurry.apply("Vamsi").apply("Goli").apply("123").apply("M");

    //or of different order
    static Function<String,Function<String,Function<String,Function<String,Person1>>>> genderFirstCurry =
            gender->firstName->lastName->ssn -> new Person1(firstName,lastName,gender,ssn);

    //this looks bad as there are so many applies and the function definition also looks weired.
    // this is where the builder comes to play. Before that we can have curry functions that prefill arguments
    //we have to carefully choose the order of the parameters in the original function so that the less specific are the first ones.

    static Function<String, Function<String, Function<String, Person1>>> maleCurry = fourArgCurry.apply("M");
    //and can be used as  Person1 person12 = maleCurry.apply("Vamsi").apply("Goli").apply("123"); -- notice that it has one less applies

    //if applies is becoming tedious, we can go for the builder pattern
    //as we know any interface with one method can be treated as a functional interface. so the below one is a functional interface
    // and can replace any Function<> interface that takes a string and returns a Person1

    interface AddGender {
        Person1 withGender(String gender);
    }
    static Function<String,Function<String,Function<String,AddGender>>> fourArgCurryInterface =
            firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);

    //Using the same analogy for all the individual function definitions for the ugly declaration
    interface AddSSN {
        AddGender withSSN(String ssn);
    }
    static Function<String,Function<String,AddSSN>> fourArgCurryInterface2 =
            firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);

    interface AddLastName {
        AddSSN withLastName(String lastName);
    }
    static Function<String,AddLastName> fourArgCurryInterface3 =
            firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);

    interface AddFirstName {
        AddLastName withFirstName(String firstName);
    }
    static AddFirstName fourArgCurryInterface4 =
            firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);

    static AddFirstName builder() {
        return fourArgCurryInterface4;
    }

    // we can return the function as well, but we cannot return the var fourArgCurryInterface because of the type
    static AddFirstName builder2() {
        //return fourArgCurryInterface;
        return firstName->lastName->ssn->gender -> new Person1(firstName,lastName,gender,ssn);
    }

    // prefilled objects builder is also easy to construct
    static AddLastName johnBuilder = fourArgCurryInterface4.withFirstName("John");

    //in the above code we see a problem. we have ordered our functions as firstName->lastName->ssn->gender
    //causing our interfaces to be in the reverse order starting with AddGender
    //this has resulted in withFirstName as the builders first method to be available
    //had we ordered our functions as gender->ssn->firstName->LastName and built interfaces starting with AddFirstName
    //we could have a maleBuilder instead of johnBuilder

    public static void main(String[] args) {
        Person2 person2 = twoArgCreator.apply("Vamsi", "Goli");
        Person2 person21 = twoArgConstructorCurry.apply("Vamsi").apply("Goli");

        Person1 person1 = fourArgCurry.apply("Vamsi").apply("Goli").apply("M").apply("123");
        Person1 person11 = genderFirstCurry.apply("M").apply("Vamsi").apply("Goli").apply("123");
        Person1 person12 = maleCurry.apply("Vamsi").apply("Goli").apply("123");

        Person1 person13 = PersonBuilder.builder().withFirstName("Vamsi").withLastName("Goli").withSSN("123").withGender("M");
        Person1 person14 = johnBuilder.withLastName("Goli").withSSN("123").withGender("M");

    }
}

class Person2
{
    private String firstName;
    private String lastName;

    public Person2(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
class Person1
{
    private String firstName;
    private String lastName;
    private String gender;

    private String ssn;

    public Person1(String firstName, String lastName, String gender, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}