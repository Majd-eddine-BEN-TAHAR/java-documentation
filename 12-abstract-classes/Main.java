/*  
    An abstract class in Java is a special kind of class that cannot be instantiated directly. 
    This means you cannot create an object of an abstract class directly, instead you extend the abstract class, and then the extended class ca be instantiated.
    Its primary purpose is to provide a base for other classes to build upon and extend.
*/

// Abstract class Shape - This class cannot be instantiated directly.
// It serves as a base class for other classes to extend and build upon.
abstract class Shape {
    // Abstract method draw(). 
    // This method must be implemented by any class that extends Shape.
    // If a class has even one abstract method, the class must be declared abstract.
    abstract void draw();

    // A concrete method - can have a body and be called.
    // This method is inherited by subclasses and can be overridden.
    // Using methods like this reduces code duplication among child classes.
    void displayArea() {
        System.out.println("Displaying area...");
    }

    // Abstract classes can have constructors.
    Shape() {
        System.out.println("Shape constructor called.");
    }
}

// A concrete(normal) class Circle, extending the abstract class Shape.
// Concrete classes (like Circle) are "normal" classes.
// They can be instantiated and used to create objects.
class Circle extends Shape {
    private double radius;

    // Constructor for Circle
    Circle(double r) {
        radius = r;
    }

    // Implementation of the abstract method draw() from Shape.
    // All abstract methods of the parent abstract class must be implemented.
    @Override
    void draw() {
        System.out.println("Drawing Circle with radius: " + radius);
    }

    // Overriding the displayArea method from Shape.
    // We can omit Overriding displayArea becasue it's not an abstract method
    @Override
    void displayArea() {
        System.out.println("Area of Circle: " + Math.PI * radius * radius);
    }
}

// Main class to run the example
public class Main {
    public static void main(String[] args) {
        // Shape shape = new Shape(); // Error: Shape is abstract; cannot be instantiated.

        // Creating an instance of Circle - this is allowed.
        // You can create objects from classes that inherit from an abstract class.
        Circle circle = new Circle(5.0);
        circle.draw(); // Calls Circle's implementation of draw()
        circle.displayArea(); // Calls Circle's implementation of displayArea()
    }
}