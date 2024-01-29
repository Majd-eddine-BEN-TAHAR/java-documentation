/*
 * This is another example but i beleive is not good, becasue you are exposing the 
 * classes outside which is not good
*/

// The inner class is EventHandler here, go down to see it
class UI {
    private String uiState = "Initial State";

    // Constructor
    public UI() {
        System.out.println("Application Started. Initial UI Status is: " + uiState);
    }

    // Method to update status
    public void updateStatus(String newStatus) {
        this.uiState = newStatus;
        System.out.println("Status Updated to: " + uiState);
    }

    // Inner Class for handling UI events
    class EventHandler {
        void onButtonClick() {
            updateStatus("Button Clicked");
            System.out.println("Button Click Event Handled.");
        }

        void onMouseHover() {
            updateStatus("Mouse Hovering");
            System.out.println("Mouse Hover Event Handled.");
        }
    }


    public static void main(String[] args) {
        UI ui = new UI();
        UI.EventHandler eventHandler = ui.new EventHandler();

        // Simulating events
        eventHandler.onButtonClick();
        eventHandler.onMouseHover();

    }
}