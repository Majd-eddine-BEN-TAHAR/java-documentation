/**
 *  1. Final Variables: 
 *    - if a final variable is initialized(not the case when it's declared only) it cannot be reassigned.
 *    - A final variable, if it was only declared, it must be initialized in the constructor, and then it cannot be modified.
 * 
  * 2. Final Methods: 
      - A final method cannot be overridden by subclasses. 
      - This ensuresthat the method's behavior remains consistent across all derived classes.
 *
 *  3. Final Classes: 
 *    - A final class cannot be extended. 
 *    - This is useful for maintaining the integrity and security of the class design.
 */

 final class ImmutableClass {
    private final int value; // Final Variable, I will initialized in the constructor
    private final int anotherValue = 100; // Final Variable, initialized at declaration, it cannot be ovrriden again now.

    public ImmutableClass(int value) {
        this.value = value;
        // anotherValue = 200; // This line would cause an error because 'anotherValue' is already initialized
    }

    public int getValue() {
        return value;
    }

    public int getAnotherValue() {
        return anotherValue;
    }

    final void display() {
        System.out.println("Value: " + value + ", Another Value: " + anotherValue);
    }
}

// The following class would cause a compile-time error
// class ExtendedClass extends ImmutableClass {
//     ExtendedClass(int value) {
//         super(value);
//     }
// }

class FinalKeywordExample {
    public static void main(String[] args) {
        ImmutableClass example = new ImmutableClass(10);

        System.out.println("The value is: " + example.getValue());
        System.out.println("Another value is: " + example.getAnotherValue());
        example.display();

        // Uncommenting the following line would cause an error
        // example.value = 20; // Error: cannot assign a value to final variable 'value'

        // Uncommenting the following line would also cause an error
        // example.anotherValue = 200; // Error: cannot assign a value to final variable 'anotherValue'
    }
}