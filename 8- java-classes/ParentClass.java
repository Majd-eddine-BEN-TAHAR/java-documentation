/*
 * Java Access Modifiers: Understanding Public, Private, and Protected
 *
 * In Java, access modifiers determine the visibility and accessibility of classes, 
 * methods, and variables. They are the pillars of Java's encapsulation principle.
 *
 * 1. Public:
 *    - The 'public' keyword makes a class, method, or variable accessible from any 
 *      other class. When applied to a class, it can be accessed globally. For methods 
 *      and variables, it means they can be accessed from any other class in the 
 *      application.
 *
 * 2. Private:
 *    - The 'private' keyword restricts the visibility to the class itself. It's used 
 *      to safeguard the internal data of the class. Private members cannot be 
 *      accessed from outside the class, ensuring encapsulation and data hiding.
 * 
 * 3. Protected:
 *    - The 'protected' keyword allows access within the same package or subclasses. 
 *      It's a middle ground between public and private. Protected members are 
 *      accessible within their own package and also in subclasses, even if they are 
 *      in different packages.
 *
 * 
 * 
 * Why Use Private Properties?
 * 1. Controlled Access:
 *    - Private properties control access to the data. This means data cannot be
 *      accesses directly using the proprety, secondly the data can be 
 *      validated before being set (using setter methods) and controlled when 
 *      accessed (using getter methods), maintaining data integrity.
 
 */


// Example of a Java class with properties, methods, and a constructor.

class Person {

    // Properties (or fields) of the class.
    private String name;
    private int age;
    private static int personCount = 0; // Static field to keep track of the number of Person instances created.

    // Constructor of the class.
    // A constructor is used to create instances of the class.
    // This constructor initializes the name and age properties.
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        personCount++; // Increment the count whenever a new instance is created.
    }

    // A method to get the person's name.
    // Methods allow you to define behavior for your objects.
    // when you want to execute a method on an object it should be public
    public String getName() {
        return name;
    }

    // A method to set the person's name.
    public void setName(String name) {
        this.name = name;
    }

    // A method to get the person's age.
    public int getAge() {
        return age;
    }

    // A method to set the person's age.
    public void setAge(int age) {
        this.age = age;
    }

    // An example of a custom method that provides specific behavior.
    // This method prints a greeting to the console.
    public void greet() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }

    // public method to check voting eligibility.
    public void checkVotingEligibility() {
        if (age >= 18) {
            System.out.println(name + " is eligible to vote.");
        } else {
            System.out.println(name + " is not eligible to vote.");
        }
    }

    // static method to get the number of Person instances created.
    public static int getPersonCount() {
        return personCount;
    }

    // Main method to demonstrate the usage of the Person class.
    public static void main(String[] args) {
        // Creating the first person
        Person person1 = new Person("Alice", 30);
        person1.greet();
        person1.checkVotingEligibility();

        // Output: Number of Person instances
        System.out.println("Number of Person instances: " + Person.getPersonCount());

        // Creating the second person
        Person person2 = new Person("Bob", 17);
        person2.greet();
        person2.checkVotingEligibility();

        // Changing name using setName
        person2.setName("Charlie");
        // Using getName to get the updated name
        System.out.println("Updated name: " + person2.getName());
        person2.greet();

        // Output: Number of Person instances
        System.out.println("Number of Person instances: " + Person.getPersonCount());
    }
}