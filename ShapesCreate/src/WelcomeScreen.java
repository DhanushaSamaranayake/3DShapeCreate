import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen() {
        setTitle("Welcome");
        setSize(800, 600);

        getContentPane().setBackground(Color.decode("#20A090")); 

        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Shape Learning App!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 18));
        proceedButton.setBackground(Color.decode("#88F4E3"));
        proceedButton.setForeground(Color.BLACK);
        proceedButton.setFocusPainted(false);

        // Adjust the button size
        Dimension buttonSize = new Dimension(100, 40);
        proceedButton.setPreferredSize(buttonSize);
        proceedButton.setMinimumSize(buttonSize);
        proceedButton.setMaximumSize(buttonSize);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHomeScreen();
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.decode("#20A090")); // Set the panel background color
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Add padding to the panel
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);
        contentPanel.add(proceedButton, BorderLayout.SOUTH);

        add(contentPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        dispose();
    }

    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
    }
}

