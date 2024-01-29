/**
 * Access Modifiers in Java:
 * - Public: Accessible from any other class or package.
 * - Private: Accessible only within the class where it is declared.
 * - Protected: Accessible within the same package and subclasses.
 * - Default (No Modifier): Accessible only within the same package.
 *
 */

package com.example.main;

import com.example.utilities.Utility;
import com.example.utilities.ExtendedUtility;

public class Main {
    public static void main(String[] args) {
        Utility utility = new Utility();

        // Accessing the public method
        utility.publicMethod();

        // The following lines, if uncommented, will cause an error due to access restrictions, check the Utility.java file:
        // utility.privateMethod(); // Error: privateMethod() has private access in Utility
        // utility.protectedMethod(); // Error: protectedMethod() has protected access in Utility
        // utility.defaultMethod(); // Error: defaultMethod() is not public in Utility; cannot be accessed from outside package

        ExtendedUtility extendedUtility = new ExtendedUtility();
        // Accessing protected method through a method in the subclass
        extendedUtility.useProtectedMethod();

        // The following line, if uncommented, will also cause an error:
        // extendedUtility.protectedMethod(); // Error: protectedMethod() has protected access in Utility
    }
}

