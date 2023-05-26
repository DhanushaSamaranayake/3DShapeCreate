import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeCreationScreen extends JFrame {

    private JComboBox<String> shapeComboBox;

    public ShapeCreationScreen() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Shape Creation");
        setSize(800, 600);// Set the size for a desktop screen
        getContentPane().setBackground(Color.decode("#20A090")); // Set a lighter background color

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Shape Creation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        titleLabel.setForeground(Color.WHITE);

        JLabel widthLabel = new JLabel("Width:");
        JTextField widthTextField = new JTextField(10);
        widthTextField.setPreferredSize(new Dimension(120, 24));

        JLabel heightLabel = new JLabel("Height:");
        JTextField heightTextField = new JTextField(10);
        heightTextField.setPreferredSize(new Dimension(120, 24));

        JLabel shapeLabel = new JLabel("Shape:");
        shapeComboBox = new JComboBox<>();
        shapeComboBox.addItem("Rectangle");
        shapeComboBox.addItem("Circle");
        shapeComboBox.addItem("Triangle");

        JButton visualizeButton = new JButton("Visualize Shape");
        visualizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizeShape(
                        (String) shapeComboBox.getSelectedItem(),
                        widthTextField.getText(),
                        heightTextField.getText()
                );
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        inputPanel.setBackground(Color.decode("#5EE0C6"));
        inputPanel.add(shapeLabel);
        inputPanel.add(shapeComboBox);
        inputPanel.add(widthLabel);
        inputPanel.add(widthTextField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);
        inputPanel.add(new JLabel());
        inputPanel.add(visualizeButton);

        add(titleLabel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void visualizeShape(String shape, String width, String height) {
        int shapeWidth = Integer.parseInt(width);
        int shapeHeight = Integer.parseInt(height);

        VisualizeScreen visualizeScreen = new VisualizeScreen(shape, shapeWidth, shapeHeight, 0);
        visualizeScreen.setVisible(true);

        setVisible(false);
        dispose();
    }

    private void goBack() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapeCreationScreen();
            }
        });
    }
}
