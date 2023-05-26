import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private WelcomeScreen welcomeScreen;
    private ShapeCreationScreen shapeCreationScreen;
    private ViewShapesScreen viewShapesScreen; // Corrected variable name

    public HomeScreen() {
        setTitle("Home");
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#20A090"));

        JLabel titleLabel = new JLabel("Shape Learning App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        titleLabel.setForeground(Color.WHITE);

        ImageIcon backIcon = new ImageIcon("go-back-2.png");
        Image backImage = backIcon.getImage();
        Image resizedBackImage = backImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedBackIcon = new ImageIcon(resizedBackImage);
        JButton backButton = new JButton(resizedBackIcon);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToWelcomeScreen();
            }
        });

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.decode("#20A090"));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(backButton, BorderLayout.WEST);

        JButton createShapeButton = new JButton("Create Shape");
        JButton viewShapesButton = new JButton("View Shapes");
        JButton editShapesButton = new JButton("Edit Shapes");
        JButton settingsButton = new JButton("Settings");

        Dimension buttonSize = new Dimension(300, 70);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        createShapeButton.setPreferredSize(buttonSize);
        createShapeButton.setFont(buttonFont);
        createShapeButton.setBackground(Color.decode("#44C9B0"));
        createShapeButton.setForeground(Color.black);
        createShapeButton.setFocusPainted(false);
        createShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateShapeScreen();
            }
        });
        viewShapesButton.setPreferredSize(buttonSize);
        viewShapesButton.setFont(buttonFont);
        viewShapesButton.setBackground(Color.decode("#76EAD9"));
        viewShapesButton.setForeground(Color.black);
        viewShapesButton.setFocusPainted(false);
        viewShapesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewScreen();
            }
        });

        editShapesButton.setPreferredSize(buttonSize);
        editShapesButton.setFont(buttonFont);
        editShapesButton.setBackground(Color.decode("#76EAD9"));
        editShapesButton.setForeground(Color.black);
        editShapesButton.setFocusPainted(false);
        editShapesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditShapesScreen();
            }
        });

        settingsButton.setPreferredSize(buttonSize);
        settingsButton.setFont(buttonFont);
        settingsButton.setBackground(Color.decode("#88F4E3"));
        settingsButton.setForeground(Color.black);
        settingsButton.setFocusPainted(false);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsScreen();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        buttonPanel.add(createShapeButton);
        buttonPanel.add(viewShapesButton);
        buttonPanel.add(editShapesButton);
        buttonPanel.add(settingsButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void goBackToWelcomeScreen() {
        if (welcomeScreen == null) {
            welcomeScreen = new WelcomeScreen();
        }
        welcomeScreen.setVisible(true);
        dispose();
    }

    private void openCreateShapeScreen() {
        if (shapeCreationScreen == null) {
            shapeCreationScreen = new ShapeCreationScreen();
        }
        shapeCreationScreen.setVisible(true);
        dispose();
    }

    private void openViewScreen() {
        if (viewShapesScreen == null) {
            viewShapesScreen = new ViewShapesScreen();
        }
        viewShapesScreen.setVisible(true);
        dispose();
    }

    private void openEditShapesScreen() {
        // Implement opening edit shapes screen
    }

    private void openSettingsScreen() {
        // Implement opening settings screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen();
            }
        });
    }
}


