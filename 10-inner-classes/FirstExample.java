/*
 * Inner classes are often used to logically group and encapsulate related functionalities that are meant to be used by the outer class only.
 * 
 * An inner class can access all the members (variables and methods) of its outer class, including private members.
 * An instance of a Nested Inner Class is related only to an instance of its outer class. This means you cannot create an object of the inner class without first creating an object of the outer class.
 *
 * Benefits: it helps encapsulate the classes better.
 * Considerations: Can make the code more complex
*/

// The inner class is Processor here, go down to see it
class Smartphone {
    private String brand;
    private boolean isPoweredOn;
    private Processor processor;

    // Constructor
    public Smartphone(String brand) {
        this.brand = brand;
        this.isPoweredOn = false;
        this.processor = new Processor();
    }

    // Method to power on the smartphone
    public void powerOn() {
        isPoweredOn = true;
        processor.startProcessor();
    }

    // Method to power off the smartphone
    public void powerOff() {
        isPoweredOn = false;
        processor.stopProcessor();
    }

    // Method to increase processor clock speed
    public void increaseProcessorSpeed(int increment) {
        if (isPoweredOn) {
            processor.increaseClockSpeed(increment);
        } else {
            System.out.println("Cannot increase processor speed. The smartphone is powered off.");
        }
    }

    // Inner Class: Processor
    class Processor {
        private int clockSpeedGHz;

        // Starts the processor
        void startProcessor() {
            clockSpeedGHz = 2; // Set clock speed
            System.out.println(brand + " smartphone processor started at " + clockSpeedGHz + " GHz.");
        }

        // Stops the processor
        void stopProcessor() {
            clockSpeedGHz = 0;
            System.out.println(brand + " smartphone processor stopped.");
        }

        // Increases processor clock speed
        void increaseClockSpeed(int increment) {
            clockSpeedGHz += increment;
            System.out.println(brand + " smartphone processor clock speed increased to " + clockSpeedGHz + " GHz.");
        }
    }

    public static void main(String[] args) {
        Smartphone myPhone = new Smartphone("Samsung");
        myPhone.powerOn();

        // Increase processor clock speed directly on myPhone
        myPhone.increaseProcessorSpeed(1);

        myPhone.powerOff();
    }
}