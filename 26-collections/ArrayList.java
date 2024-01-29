import java.util.ArrayList;

class ArrayListExample {
    public static void main(String[] args) {
        // Creating an ArrayList of String type
        ArrayList<String> fruits = new ArrayList<>();

        // Adding elements to the ArrayList
        fruits.add("Apple");  // Adds "Apple" to the list
        fruits.add("Banana"); // Adds "Banana" to the list
        fruits.add("Orange"); // Adds "Orange" to the list

        // Accessing an element
        String firstFruit = fruits.get(0); // Gets the first element, "Apple"

        // Removing an element
        fruits.remove("Banana"); // Removes "Banana" from the list

        // Iterating over ArrayList elements
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Checking if an element exists
        boolean hasApple = fruits.contains("Apple"); // Checks if "Apple" is in the list

        // Getting the size of the ArrayList
        int size = fruits.size(); // Returns the number of elements in the list
    }
}
