/**
 * Demonstrates the use of Comparator in Java.
 * Comparator is used for defining a custom sorting order for objects(everything is object in jva, so don't thinl of it like in javascript).
 * In this example, we define a Person class and a custom Comparator
 * to sort persons based on their age.
 */
import java.util.Arrays;
import java.util.Comparator;


// Define the Person class with name and age attributes
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter for age, used in the Comparator
    public int getAge() {
        return age;
    }

    // Getter for name, for displaying the person's name
    public String getName() {
        return name;
    }
}

// Define a Comparator for the Person class that compares persons by age
class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an array of Person objects
        Person[] people = {
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35)
        };

        // Sort the people array using the AgeComparator
        Arrays.sort(people, new AgeComparator());

        // Print sorted results
        for (Person person : people) {
            System.out.println(person.getName() + " - Age: " + person.getAge());
        }
    }
}