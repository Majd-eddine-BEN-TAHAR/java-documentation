import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MouseEventExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Click Me");
        JButton button = new JButton("Click Me");

        // Create an anonymous inner class that implements ActionListener under the hood
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button was clicked!");
            }
        });

        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}