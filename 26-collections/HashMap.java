import java.util.HashMap;
import java.util.Map;

class HashMapExample {
    public static void main(String[] args) {
        // Creating a HashMap that maps String keys to Integer values
        Map<String, Integer> ageOfFriends = new HashMap<>();

        // Adding key-value pairs to the HashMap
        ageOfFriends.put("Alice", 30); // Puts the key-value pair ("Alice", 30)
        ageOfFriends.put("Bob", 25);   // Puts the key-value pair ("Bob", 25)

        // Accessing a value
        int aliceAge = ageOfFriends.get("Alice"); // Gets the age of "Alice", which is 30

        // Updating a value
        ageOfFriends.put("Alice", 31); // Updates Alice's age to 31

        // Removing a key-value pair
        ageOfFriends.remove("Bob"); // Removes the key "Bob" and its associated value

        // Iterating over key-value pairs
        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " is " + value + " years old.");
        }

        // Checking if a key exists in the map
        boolean hasAlice = ageOfFriends.containsKey("Alice"); // Checks if "Alice" is a key in the map

        // Getting the size of the HashMap
        int size = ageOfFriends.size(); // Returns the number of key-value pairs in the map
    }
}