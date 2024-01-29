package com.example.utilities;

public class Utility {
    // Public method - accessible from anywhere
    public void publicMethod() {
        System.out.println("Public method in Utility.");
    }

    // Private method - accessible only within Utility class
    private void privateMethod() {
        System.out.println("Private method in Utility.");
    }

    // Protected method - accessible within the package and in subclasses
    protected void protectedMethod() {
        System.out.println("Protected method in Utility.");
    }

    // Default method - package-private, accessible only within the utilities package
    void defaultMethod() {
        System.out.println("Default (package-private) method in Utility.");
    }
}