// Defining an interface Drivable
// This interface represents a contract to be followed by any class that implements it.
interface Drivable {
    // An abstract method
    // Any class implementing Drivable must provide an implementation for this method
    // no need to specify the keyword abstract because they are abstract by default until you change them to default or static
    void drive();

    // A default method - introduced in Java 8
    // This was a major enhancement because before Java 8, interfaces could only have abstract methods(methods without a body).
    // They are useful for adding new functionalities to already used interfaces without affecting the classes that implement these interfaces.
    // It provides a default implementation, which can be overridden by implementing classes
    default void startEngine() {
        System.out.println("Engine started.");
    }

    // A static method - also introduced in Java 8
    // used for providing utility methods that are related to the interface.
    // Static methods belong to the interface, not the implementing class's instances
    // cannot be overridden by implementing classes.
    static void stopEngine() {
        System.out.println("Engine stopped.");
    }
}

// Adding another interface Maintainable
// This interface can be implemented by any class that requires maintenance functionality
interface Maintainable {
    void performMaintenance();
}

// A concrete class Car, implementing both Drivable and Maintainable interfaces
class Car implements Drivable, Maintainable {
    // Implementing the abstract method from Drivable
    @Override
    public void drive() {
        System.out.println("Car is driving.");
    }

    // Overriding the default startEngine method
    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }

    // Implementing method from the Maintainable interface
    @Override
    public void performMaintenance() {
        System.out.println("Car maintenance performed.");
    }
}

// A concrete class Bicycle, implementing the Drivable interface
class Bicycle implements Drivable {
    // Implementing the abstract method from Drivable
    @Override
    public void drive() {
        System.out.println("Bicycle is moving.");
    }

    // Bicycle doesn't override startEngine as it doesn't have an engine
    // No need to override unless specific behavior is needed
}

// Main class to run the example
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive(); // Calls Car's implementation of drive()
        car.startEngine(); // Calls Car's overridden startEngine()
        car.performMaintenance(); // Calling method from Maintainable interface

        Bicycle bike = new Bicycle();
        bike.drive(); // Calls Bicycle's implementation of drive()
        bike.startEngine(); // Calls default implementation from Drivable

        Drivable.stopEngine(); // Calls static method from Drivable interface
    }
}