/**
 * StringTokenizer is a utility class used to split a string into tokens(like javascript .join)
 * It is especially useful for parsing strings where you need to divide a string into
 * parts (tokens) based on delimiters like commas, spaces, etc.
 */
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        // Creating a StringTokenizer with a comma (,) as the delimiter
        StringTokenizer st = new StringTokenizer("Java,Python,Ruby", ",");

        // Iterating over each token
        while (st.hasMoreTokens()) {
            // Printing each token (word)
            System.out.println(st.nextToken());
        }
    }
}