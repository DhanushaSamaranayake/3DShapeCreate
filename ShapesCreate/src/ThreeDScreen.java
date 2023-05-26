import javax.swing.*;
import java.awt.*;

public class ThreeDScreen extends JPanel {

    public ThreeDScreen() {
        setLayout(new BorderLayout());

        // Create a label to display "Visualize in 3D"
        JLabel label = new JLabel("Visualize in 3D", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a button for going back
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "2D");
        });

        // Create a panel for the back button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);

        // Add components to the ThreeDScreen panel
        add(buttonPanel, BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
    }
}
