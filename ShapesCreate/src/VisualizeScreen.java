import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class VisualizeScreen extends JFrame {

    private String shape;
    private int width;
    private int height;
    private int depth; // New parameter for the y-axis
    private boolean is3D;
    private boolean showShade; // New parameter to control shade display
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private class ShapePanel extends JPanel {
        private Color shapeColor = Color.BLACK;
        private double scale = 1.0;

        public Color getShapeColor() {
            return shapeColor;
        }

        public void setShapeColor(Color color) {
            this.shapeColor = color;
        }

        public void setScale(double scale) {
            this.scale = scale;
            repaint(); // Redraw the shape with the new scale
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Set the color and draw/fill the scaled shape based on its type
            g.setColor(shapeColor);

            int scaledWidth = (int) (width * scale);
            int scaledHeight = (int) (height * scale);

            if (shape.equals("Rectangle")) {
                int x = 50 + (width - scaledWidth) / 2;
                int y = 50 + (height - scaledHeight) / 2;
                g.fillRect(x, y, scaledWidth, scaledHeight);
            } else if (shape.equals("Circle")) {
                int x = 50 + (width - scaledWidth) / 2;
                int y = 50 + (height - scaledHeight) / 2;
                g.fillOval(x, y, scaledWidth, scaledHeight);
            }

            // If in 3D mode, draw the depth
            if (is3D) {
                g.drawLine(50, 50, 50, 50 + depth);
                g.drawLine(50 + width, 50, 50 + width, 50 + depth);
                g.drawLine(50, 50 + height, 50, 50 + height + depth);
                g.drawLine(50 + width, 50 + height, 50 + width, 50 + height + depth);

                // If shade is enabled, draw the shade
                if (showShade) {
                    int shadeOffset = 50;
                    int shadeAlpha = 128;
                    int red = Math.max(shapeColor.getRed() - shadeOffset, 0);
                    int green = Math.max(shapeColor.getGreen() - shadeOffset, 0);
                    int blue = Math.max(shapeColor.getBlue() - shadeOffset, 0);
                    Color shadeColor = new Color(red, green, blue, shadeAlpha);
                    g.setColor(shadeColor);
                    g.fillRect(50, 50, width, height);
                }
            }
        }

    }

    public VisualizeScreen(String shape, int width, int height, int depth) {
        setTitle("Visualize Shape");
        setSize(800, 600); // Set the size of the window

        getContentPane().setBackground(Color.decode("#88F4E3")); // Set the background color

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setLayout(new BorderLayout());

        this.shape = shape;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.is3D = false;
        this.showShade = false;

        ShapePanel shapePanel = new ShapePanel();
        cardPanel.add(new JScrollPane(shapePanel), "2D");
        add(cardPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShapeCreationScreen shapeCreationScreen = new ShapeCreationScreen();
                shapeCreationScreen.setVisible(true);
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Color");
            }
        });

        JButton convertTo3DButton = new JButton("Convert to 3D");
        convertTo3DButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                is3D = true;
                shapePanel.repaint();
            }
        });

        JButton shadeButton = new JButton("Shade"); // New shade button
        shadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showShade = !showShade;
                shapePanel.repaint();
            }
        });

        JButton scale1xButton = new JButton("1x");
        scale1xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setScale(1.0);
            }
        });

        JButton scale2xButton = new JButton("2x");
        scale2xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setScale(2.0);
            }
        });

        JButton scale3xButton = new JButton("3x");
        scale3xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setScale(3.0);
            }
        });

        JButton scale5xButton = new JButton("5x");
        scale5xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setScale(5.0);
            }
        });

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveShapeToFile();
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel dimensionsLabel = new JLabel("Dimensions: " + width + " x " + height + " x " + depth);
        bottomPanel.add(dimensionsLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertTo3DButton);
        buttonPanel.add(colorButton);
        buttonPanel.add(shadeButton);
        buttonPanel.add(scale1xButton);
        buttonPanel.add(scale2xButton);
        buttonPanel.add(scale3xButton);
        buttonPanel.add(scale5xButton);
        buttonPanel.add(doneButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(3, 3));

        Color lighterGreen = new Color(20, 80, 77);
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.GRAY};

        for (Color color : colors) {
            JButton colorBtn = new JButton();
            colorBtn.setBackground(color);
            colorBtn.setPreferredSize(new Dimension(30, 30));
            colorBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shapePanel.setShapeColor(color);
                    cardLayout.show(cardPanel, "2D");
                }
            });
            colorPanel.add(colorBtn);
        }

        cardPanel.add(new JScrollPane(colorPanel), "Color");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void saveShapeToFile() {
        String filePath = "shape_details.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Append to the existing file
            fileWriter.write("Shape: " + shape + "\n");
            fileWriter.write("Dimensions: " + width + " x " + height + " x " + depth + "\n\n"); // Add an extra newline after each shape
            fileWriter.close();
            System.out.println("Shape details saved to file: " + filePath);
            JOptionPane.showMessageDialog(this, "Shape saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VisualizeScreen visualizeScreen = new VisualizeScreen("Rectangle", 100, 200, 0);
                visualizeScreen.setVisible(true);
            }
        });
    }
}



