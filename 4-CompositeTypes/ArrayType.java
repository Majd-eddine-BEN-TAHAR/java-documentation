/** Java Arrays
 * 
 *  Arrays are collections of elements that must have the same data type. This means that all the elements in a Java array should be of the same data type, If you need to store elements of different data types together, consider using the ArrayList datatype
 *  You must specify the size of the array when you are using the new keyword for initialization.
 *  The size defines the number of elements the array can hold.
 *  It's important to note that in Java, the size of an array is fixed once it is created.
 *  You cannot omit the size of the array when initializing an array with the new keyword. Doing so will result in a compilation error.
 *  When using the curly brace {} syntax, you don't need to specify the size, becasue it's inferred from the number of elements provided.
 *  If you don't specify the size (and don't use the {} method), Java won't know how much memory to allocate for the array, leading to a compilation error.
 *  Arrays in Java have a fixed size. This means that if you declare an array with 5 slots, and try to add more than five elements to it, you will encounter an ArrayIndexOutOfBoundsException because you are trying to access an index that is beyond the array's capacity.
 *  
 *  To display the contents of an array, avoid direct printing with System.out.println(array).
 *  Instead, use a loop or Arrays.toString() or the Stream API to do that"
 *  
 *  
 *  syntax    =>    dataType[] arrayName = new dataType[arraySize];
 */

// you need to import it when using any array methods
import java.util.Arrays;

class ArrayType {

    public static void main(String[] args) {
        // Java Arrays Documentation Example Code

        // Rule 1: Arrays in Java are objects. Use 'new' for initialization.
        // Example: Initializing an array of integers with 5 elements.
        int[] arrayWithNew = new int[5];

        // Rule 2: Alternative initialization without 'new' using curly braces.
        // This method is convenient when you already know the elements that the array will hold.
        // Example: Directly declaring and initializing an array with values.
        int[] directInitialization = {1, 2, 3, 4, 5};

        // Rule 3: Specifying the size of an array is mandatory with 'new'.
        // The size determines the number of elements it can hold.
        // Uncommenting the following line will cause a compilation error due to missing size.
        // int[] arrayWithoutSize = new int[];

        // Rule 4: Arrays have a fixed size once initialized.
        // Trying to add more elements than the array's size leads to an error.
        // Example:
        int[] fixedSizeArray = new int[5];
        // fixedSizeArray[5] = 6; // Uncommenting this line will and compling will throw ArrayIndexOutOfBoundsException during compilation

        // Accessing Array Elements
        int firstNumber = directInitialization[0]; // Access first element

        // Modifying Array Elements
        directInitialization[0] = 9; // Change the first element to 9
        
        // Displaying the content of an array
        // don't use System.out.println(theArray); becasue you will display the reference of the array not the content
        for (int i = 0; i < directInitialization.length; i++) {
            System.out.println(directInitialization[i]);
        }

        // Length of an Array
        int arrayLength = directInitialization.length; // Length of directInitialization array
        
        // Creating a Subarray
        int[] subArray = Arrays.copyOfRange(directInitialization, 1, 3); // Copies elements from index 1 to 2 (3 is exclusive)
        
        // Using array methods
        // always import => import java.util.Arrays;
        // Example: Sorting an array.
        int[] arrayToSort = {3, 1, 4, 5, 2};
        Arrays.sort(arrayToSort); // Sorts the array in ascending order.

        // Example: Copying an array.
        int[] originalArray = {1, 2, 3};
        int[] copiedArray = Arrays.copyOf(originalArray, originalArray.length); // Copy the entire array.

        // Example: Checking if two arrays are equal.
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};        

        boolean areEqual = Arrays.equals(array1, array2); // Returns true if arrays are equal.

        // Example: Filling an array with a specific value.
        int[] arrayToFill = new int[5];
        Arrays.fill(arrayToFill, 7); // Fills the array with the number 7.
    }
}
