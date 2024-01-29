// Import necessary classes
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This file demonstrates the use of Java Generics, including generic classes,
 * methods, bounded type parameters, and wildcards.
 */

// 1. Generic Class Example
class Box<T> {
    private T t; // T stands for "Type"

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

// 2. Generic Method Example
class Util {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

// 3. Restricted Type Parameters Example
class NumericBox<T extends Number> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

// 4. Wildcard Examples
class WildcardExample {
    // Unbounded Wildcard
    // you can avoid the <?> in our case and java will infer automatically that every type is allowed
    // wildcards are only used in parameters
    public void printList(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    // Upper Bounded Wildcard
    public double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Lower Bounded Wildcard
    public void addNumbersToList(List<? super Integer> list) {
        list.add(1);
        list.add(2);
    }
}

// Main class to demonstrate the usage of the above classes and methods
public class Main {
    public static void main(String[] args) {
        // Using Box class
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.set(10); // Storing an Integer
        stringBox.set("Hello World"); // Storing a String

        System.out.println("Integer Value: " + integerBox.get());
        System.out.println("String Value: " + stringBox.get());

        // Using Util class
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"Hello", "World"};

        Util.printArray(intArray);
        Util.printArray(stringArray);

        // Using NumericBox class
        NumericBox<Integer> intNumericBox = new NumericBox<>();
        NumericBox<Double> doubleNumericBox = new NumericBox<>();

        intNumericBox.set(10); // Valid
        doubleNumericBox.set(3.14); // Valid

        System.out.println("Integer Value in NumericBox: " + intNumericBox.get());
        System.out.println("Double Value in NumericBox: " + doubleNumericBox.get());

        // Using WildcardExample class
        WildcardExample example = new WildcardExample();

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> stringList = Arrays.asList("Hello", "World");

        example.printList(intList);
        example.printList(stringList);

        System.out.println("Sum of intList: " + example.sumOfList(intList));
        
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println("Sum of doubleList: " + example.sumOfList(doubleList));

        List<Number> numList = new ArrayList<>();
        example.addNumbersToList(numList);
        example.printList(numList);
    }
}