class Student extends Person {
    // Additional property for the Student class.
    private String schoolName;

    // Constructor for the Student class.
    public Student(String name, int age, String schoolName) {
        // Calling the constructor of the superclass (Person).
        super(name, age); // The 'super' keyword refers to the superclass' constructor.
        this.schoolName = schoolName;
    }

    // Getter method for schoolName.
    public String getSchoolName() {
        return schoolName;
    }

    // Setter method for schoolName.
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    // Overriding the greet method from the Person class.
    // This annotation indicates that the following method overrides a method in the superclass.
    @Override 
    public void greet() {
        // Calling the greet method of the superclass (Person), you can remove that for sure and do anything you want but i include it to sjow how we can use it
        super.greet(); // The 'super' keyword refers to the superclass' method.
        System.out.println("I am a student at " + schoolName + ".");
    }

    // Main method to demonstrate the usage of the Student class.
    public static void main(String[] args) {
        // Creating a student instance.
        Student student1 = new Student("John", 20, "University of Java");
        student1.greet();
        student1.checkVotingEligibility();

        // Output: Number of Person instances (includes Student instances)
        System.out.println("Number of Person instances: " + Person.getPersonCount());
    }
}