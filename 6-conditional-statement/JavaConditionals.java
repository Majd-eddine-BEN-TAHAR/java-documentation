
public class JavaConditionals {
    public static void main(String[] args) {
        // Example variables for demonstration
        int x = 10;
        int y = 20;

        // If statement
        if (x < y) {
            System.out.println("x is less than y");
        }

        // If-else statement
        if (x > y) {
            System.out.println("x is greater than y");
        } else {
            System.out.println("x is not greater than y");
        }

        // If-else if-else statement
        if (x == y) {
            System.out.println("x is equal to y");
        } else if (x < y) {
            System.out.println("x is less than y");
        } else {
            System.out.println("x is greater than y");
        }

        // Nested if statements
        if (x < 15) {
            if (y > 15) {
                System.out.println("x is less than 15 and y is greater than 15");
            }
        }

        // Switch statement
        String name = "majd";
        String helloString;
        switch (name) {
            case "majd":  
                helloString = "hello " + name;
                break;
            
            default: 
                helloString = "Invalid name";
                break;
        }
        System.out.println(helloString);

        // Ternary operator
        int result = (x > y) ? x : y;
        System.out.println("The greater number is " + result);
    }
}