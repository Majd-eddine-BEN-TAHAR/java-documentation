public class JavaLoops {
    public static void main(String[] args) {
        // For loop
        for (int i = 0; i < 5; i++) {
            System.out.println("For loop iteration: " + i);
        }

        // for-each loop
        // Define an array of integers
        int[] numbers = {1, 2, 3, 4, 5};
        for (int number : numbers) {
            System.out.println("Enhanced for loop iteration: " + number);
        }


        // While loop
        // In a while loop, this condition is checked first before trying to enter.
        // If the condition is false initially, the loop body will not execute at all.
        // So it's possible that the loop body never runs if the condition is false from the beginning.
        int whileCounter = 0;
        while (whileCounter < 5) {
            System.out.println("While loop iteration: " + whileCounter);
            whileCounter++;
        }

        // Do-while loop
        // It's useful when you need to ensure the loop body is executed before the condition is checked, so we are sure that the code inside it will at least be executed one time
        // The condition is checked after the loop body has been executed.
        // If the condition is true, the loop continues; otherwise, it stops.
        int doWhileCounter = 0;
        do {
            // This block of code will execute at least once regardless of the condition.
            System.out.println("Do-While loop iteration: " + doWhileCounter);
            doWhileCounter++;
        } while (doWhileCounter < 5);

    }
}