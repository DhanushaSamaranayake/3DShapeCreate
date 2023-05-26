import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ViewShapesScreen extends JFrame {
    private JButton backButton;
    private JButton deleteButton;
    private JLabel shapeNameLabel;
    private JLabel dimensionsLabel;

    public ViewShapesScreen() {
        setTitle("View Shapes");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel shapePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                createShape(g);
            }
        };

        getContentPane().setBackground(Color.decode("#20A090"));

        JPanel detailsPanel = new JPanel(new BorderLayout());
        shapeNameLabel = new JLabel();
        dimensionsLabel = new JLabel();
        shapeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dimensionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(shapeNameLabel, BorderLayout.NORTH);
        detailsPanel.add(dimensionsLabel, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> goBackToHomeScreen());

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(e -> deleteShape());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(shapePanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);
    }

    private void createShape(Graphics g) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("shape_details.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Shape:")) {
                    String shapeType = line.substring(line.indexOf(":") + 1).trim();
                    String dimensionsLine = reader.readLine();
                    String[] dimensions = dimensionsLine.substring(dimensionsLine.indexOf(":") + 1).trim().split("x");

                    int width = Integer.parseInt(dimensions[0].trim());
                    int height = Integer.parseInt(dimensions[1].trim());

                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.translate(getWidth() / 2 - width / 2, getHeight() / 2 - height / 2);

                    if (shapeType.equalsIgnoreCase("Rectangle")) {
                        g2d.drawRect(0, 0, width, height);
                    } else if (shapeType.equalsIgnoreCase("Circle")) {
                        g2d.drawOval(0, 0, width, height);
                    } else if (shapeType.equalsIgnoreCase("Triangle")) {
                        int[] xPoints = {0, width / 2, width};
                        int[] yPoints = {height, 0, height};
                        g2d.drawPolygon(xPoints, yPoints, 3);
                    }

                    g2d.dispose();

                    // Set shape details
                    shapeNameLabel.setText("Shape: " + shapeType);
                    dimensionsLabel.setText("Dimensions: " + width + " x " + height);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goBackToHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
        dispose();
    }

    private void deleteShape() {
        try {
            File file = new File("shape_details.txt");
            if (file.delete()) {
                shapeNameLabel.setText("");
                dimensionsLabel.setText("");
                repaint();
                JOptionPane.showMessageDialog(this, "Shape deleted");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete shape");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewShapesScreen viewShapesScreen = new ViewShapesScreen();
            viewShapesScreen.setVisible(true);
        });
    }
}

