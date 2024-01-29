/** Java Methods
 *  
 *  note : You can omit the public keyword from methods in Java, except for the main method which must be public because the program when it will be executed it should be able to access it to execeute it. When you omit the public keyword, the method will have default access level which is private. This means that the method will be accessible to all classes in the same package.
 *  Methods in Java are blocks of code that perform a specific task. 
 * 
 *  They enable code reuse, improve readability, and make your code more organized.
 *  A method must be declared within a class. 
 *  It is defined with the name of the method, followed by parentheses. Java provides several modifiers that dictate how a method can be accessed and used.
 *  The return type of a method indicates the data type of the value the method returns.
 *  If the method does not return a value, its return type is void.
 *  
 *  Static methods belong to the class rather than the object of a class. They can be called without creating an instance of the class, take a look at the sumAll method down in code and how it's called.
 *  Instance methods belong to an instance of a class. To use an instance method, you need to create an object of the class.
 *  
 *  Methods can accept zero or more parameters. 
 *  Parameters allow you to pass data to a method. Parameters are specified inside the parentheses that follow the method name.
 
 *  Method overloading allows a class to have multiple methods with the same name, as long as their parameter lists are different, this is exactly like generics in golang, but in java you will just duplicate the code for the sam method and change the types.
 
 *  Varargs (Variable Arguments) allow you to pass an arbitrary number of arguments to a method, it's the same 3 dots(...) of javascript
 
 *  Syntax for Method Declaration:
 *      accessModifier returnType methodName(parameters) {
 *          // method body
 *      }
 */

class JavaMethodsExample {

    public static void main(String[] args) {
        // Example of calling a static method without the need for an instance from the class
        int result = add(10, 20);
        System.out.println(result);

        // Example of creating an instance and using its methods(i know i'm using the same class i created, it's not an issue, it works)
        // Example of calling an instance method
        JavaMethodsExample example = new JavaMethodsExample();
        example.showMessage("Hello, Java!");

        // Example of calling an overloaded method
        double doubleResult = add(10.5, 20.5);
        System.out.println(doubleResult);

        // Example of calling a method with varargs
        int sum = sumAll(1, 2, 3, 4, 5);
        System.out.println(sum);
    }

    
    // Example of an instance method that should be called using the instance not the class
    public void showMessage(String message) {
        System.out.println(message);
    }
    
    // Example of a static method that can be called on the class without the need for an instance
    public static int add(int a, int b) {
        return a + b; // returns the sum
    }
    // Example of method overloading
    // overloading allows a class to have multiple methods with the same name(in our case the add method is redeclared) as long as their parameter lists are different.
    public static double add(double a, double b) {
        return a + b; // returns the sum for double type
    }

    // Example of a static method using varargs
    public static int sumAll(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum; // returns the total sum
    }
}