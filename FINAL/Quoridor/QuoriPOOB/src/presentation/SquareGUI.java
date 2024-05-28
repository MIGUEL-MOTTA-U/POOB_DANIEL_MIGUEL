package presentation;
import domain.QuoriPOOBException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Create the component of the squares in the board and the walls
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class SquareGUI extends JPanel {
    private QuoridorGUI quoridorGUI;
    private BoardGUI boardGUI;
    private int row;
    private int column;
    private boolean wallUp;
    private boolean wallLeft;
    private boolean wallDown;
    private boolean wallRight;
    private boolean drawToken;
    private Color colorToken;
    private Color colorBorderUp;
    private Color colorBorderLeft;
    private Color colorBorderDown;
    private Color colorBorderRight;

    // Botones
    JButton buttonWallUp;
    JButton buttonWallDown;
    JButton buttonWallLeft;
    JButton buttonWallRight;

    /**
     * Constructor os SquareGUI
     * 
     * @param quoridorGUI the main class quoridorGUI
     * @param boardGUI the board GUI where are located the squares and walls
     * @param row the row of the square
     * @param column the column of the square
     */
    public SquareGUI(QuoridorGUI quoridorGUI, BoardGUI boardGUI, int row, int column) {
        this.quoridorGUI = quoridorGUI;
        this.boardGUI = boardGUI;
        this.row = row;
        this.column = column;
        this.wallUp = false;
        this.wallLeft = false;
        this.wallDown = false;
        this.wallRight = false;
        this.drawToken = false;
        this.colorToken = null;
        this.colorBorderUp = Color.BLACK;
        this.colorBorderLeft = Color.BLACK;
        this.colorBorderDown = Color.BLACK;
        this.colorBorderRight = Color.BLACK;
        prepareElements();
    }

    /*
     * Create all the elements
     */
    private void prepareElements() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        buttonWallUp = createButton(getWidth(), 10);
        buttonWallDown = createButton(getWidth(), 10);
        buttonWallLeft = createButton(10, getHeight());
        buttonWallRight = createButton(10, getHeight());

        add(buttonWallUp, BorderLayout.NORTH);
        add(buttonWallDown, BorderLayout.SOUTH);
        add(buttonWallLeft, BorderLayout.WEST);
        add(buttonWallRight, BorderLayout.EAST);
    }

    /*
     * Create the list of walls
     */
    private void showTypeWallDialog(JButton button) {
        JComboBox<String> walls = createWallComboBox();

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(walls);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select the wall type", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String wall = (String) walls.getSelectedItem();
            boolean wallAdded = addWall(wall, button);

            if (wallAdded) {
                boardGUI.refresh();
            }
        }
    }

    /*
     * Create the button of list of walls
     */
    private JComboBox<String> createWallComboBox() {
        JComboBox<String> walls = new JComboBox<>();
        walls.addItem("Normal");
        walls.addItem("Temporary");
        walls.addItem("Long");
        walls.addItem("Allied");

        return walls;
    }

    /*
     * Create a button
     */
    private JButton createButton(int width, int height) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(width, height));

        createMouseListener(button);
        createActionListener(button);

        return button;
    }

    /*
     * Create a mause listener for the interaction of the walls and the user
     */
    private void createMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                Color color = parseColor(quoridorGUI.getPlayerPlaying().getColor());
                setBorder(paintBorder(button, color));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                setBorder(defaultBorder());
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    /*
     * Create a Button
     */
    private void createActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(button);
            }
        });
    }

    /**
     * Draw a token in the board
     */
    public void drawToken() {
        this.drawToken = true;
    }

    /**
     * Erase a token of the board
     */
    public void eraseToken() {
        this.drawToken = false;
    }

    /**
     * Asssign a color to the token
     * 
     * @param color the color of the token
     */
    public void setColorToken(Color color) {
        this.colorToken = color;
    }

    /**
     * Assign a wall to the square
     * @param bool assign a wall up of the square if true, false otherwise
     */
    public void setWallUp(boolean bool) {
        this.wallUp = bool;
    }

    /**
     * Assign a wall to the square
     * @param bool assign a wall up of the square if true, false otherwise
     */
    public void setWallLeft(boolean bool) {
        this.wallLeft = bool;
    }

    public void setWallDown(boolean bool) {
        this.wallDown = bool;
    }

    /**
     * Assign a wall to the square
     * @param bool assign a wall up of the square if true, false otherwise
     */
    public void setWallRight(boolean bool) {
        this.wallRight = bool;
    }

    /**
     * Get a boolean value if is a wall up the square
     * @return true if is a wall up, false otherwise
     */
    public boolean getWallUp() {
        return this.wallUp;
    }

    /**
     * Get a boolean value if is a wall left the square
     * @return true if is a wall left, false otherwise
     */
    public boolean getWallLeft() {
        return this.wallLeft;
    }

    /**
     * Get a boolean value if is a wall down the square
     * @return true if is a wall down, false otherwise
     */
    public boolean getWallDown() {
        return this.wallDown;
    }

    /**
     * Get a boolean value if is a wall right the square
     * @return true if is a wall right, false otherwise
     */
    public boolean getWallRight() {
        return this.wallRight;
    }

    /**
     * Assign a color to the wall up
     * @param color assign a color to a wall up
     */
    public void setWallUp(Color color) {
        setBorder(paintBorder(buttonWallUp, color));
        this.colorBorderUp = color;
        remove(buttonWallUp);
    }

    /**
     * Assign a color to the wall left
     * @param color assign a color to a wall left
     */
    public void setWallLeft(Color color) {
        setBorder(paintBorder(buttonWallLeft, color));
        this.colorBorderLeft = color;
        remove(buttonWallLeft);
    }

    /**
     * Assign a color to the wall down
     * @param color assign a color to a wall down
     */
    public void setWallDown(Color color) {
        setBorder(paintBorder(buttonWallDown, color));
        this.colorBorderDown = color;
        remove(buttonWallDown);
    }

    /**
     * Assign a color to the wall right
     * @param color assign a color to a wall right
     */
    public void setWallRight(Color color) {
        setBorder(paintBorder(buttonWallRight, color));
        this.colorBorderRight = color;
        remove(buttonWallRight);
    }

    /**
     * Remove the up wall
     */
    public void delWallUp() {
        setBorder(paintBorder(buttonWallUp, Color.BLACK));
        this.colorBorderUp = Color.BLACK;
        buttonWallUp = createButton(getWidth(), 10);
        add(buttonWallUp, BorderLayout.NORTH);
    }

    /**
     * Remove the left wall
     */
    public void delWallLeft() {
        setBorder(paintBorder(buttonWallLeft, Color.BLACK));
        this.colorBorderLeft = Color.BLACK;
        buttonWallLeft = createButton(10, getHeight());
        add(buttonWallLeft, BorderLayout.WEST);
    }

    /**
     * Remove the down wall
     */
    public void delWallDown() {
        setBorder(paintBorder(buttonWallDown, Color.BLACK));
        this.colorBorderDown = Color.BLACK;
        buttonWallDown = createButton(getWidth(), 10);
        add(buttonWallDown, BorderLayout.SOUTH);
    }

    /**
     * Remove the right wall
     */
    public void delWallRight() {
        setBorder(paintBorder(buttonWallRight, Color.BLACK));
        this.colorBorderRight = Color.BLACK;
        buttonWallRight = createButton(10, getHeight());
        add(buttonWallRight, BorderLayout.EAST);
    }

    /**
     * Paint the square of a given color
     * 
     * @param color the color of the square background
     */
    public void paintBackgroung(Color color) {
        setBackground(color);
        buttonWallUp.setBackground(color);
        buttonWallLeft.setBackground(color);
        buttonWallDown.setBackground(color);
        buttonWallRight.setBackground(color);
    }

    /*
     * Add a wall to the square
     */
    private boolean addWall(String type, JButton button) {
        String squareSide;
        boolean wallAdded = true;

        if (type.equals("Normal")) {
            type = "NormalWall";
        } else if (type.equals("Long")) {
            type = "LongWall";
        }

        if (button == buttonWallUp) {
            squareSide = "UP";
        } else if (button == buttonWallLeft) {
            squareSide = "LEFT";
        } else if (button == buttonWallDown) {
            squareSide = "DOWN";
        } else {
            squareSide = "RIGHT";
        }

        try {
            quoridorGUI.addWallToBoard(type, this.row, this.column, squareSide);
        } catch (QuoriPOOBException e) {
            wallAdded = false;
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return wallAdded;
    }

    /*
     * Paint the border of a square (the wall)
     */
    private Border paintBorder(JButton button, Color color) {
        Border borderUp = createMatteBorder(button == buttonWallUp ? color : colorBorderUp, 1, 0, 0, 0);
        Border borderLeft = createMatteBorder(button == buttonWallLeft ? color : colorBorderLeft, 0, 1, 0, 0);
        Border borderDown = createMatteBorder(button == buttonWallDown ? color : colorBorderDown, 0, 0, 1, 0);
        Border borderRight = createMatteBorder(button == buttonWallRight ? color : colorBorderRight, 0, 0, 0, 1);
    
        Border topLeft = BorderFactory.createCompoundBorder(borderUp, borderDown);
        Border bottomRight = BorderFactory.createCompoundBorder(borderLeft, borderRight);
        return BorderFactory.createCompoundBorder(topLeft, bottomRight);
    }

    /*
     * Create the default border of a square
     */
    private Border defaultBorder() {
        Border borderUp = createMatteBorder(colorBorderUp, 1, 0, 0, 0);
        Border borderLeft = createMatteBorder(colorBorderLeft, 0, 1, 0, 0);
        Border borderDown = createMatteBorder(colorBorderDown, 0, 0, 1, 0);
        Border borderRight = createMatteBorder(colorBorderRight, 0, 0, 0, 1);
    
        Border topLeft = BorderFactory.createCompoundBorder(borderUp, borderDown);
        Border bottomRight = BorderFactory.createCompoundBorder(borderLeft, borderRight);
        return BorderFactory.createCompoundBorder(topLeft, bottomRight);
    }
    
    /*
     * Create a Border
     */
    private Border createMatteBorder(Color color, int top, int left, int bottom, int right) {
        return BorderFactory.createMatteBorder(top, left, bottom, right, color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawToken) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int circleRadius = Math.min(panelWidth, panelHeight) / 4;
            int circleX = (panelWidth - circleRadius * 2) / 2;
            int circleY = (panelHeight - circleRadius * 2) / 2;

            g.setColor(this.colorToken);
            g.fillOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
        }
    }

    /*
	 * Convert a string into a color
	 */
	private static Color parseColor(String colorString) {
        colorString = colorString.replace("java.awt.Color[", "").replace("]", "");
        String[] components = colorString.split(",");
        int r = 0, g = 0, b = 0;

        for (String component : components) {
            String[] keyValue = component.split("=");
            String key = keyValue[0].trim();
            int value = Integer.parseInt(keyValue[1].trim());

            switch (key) {
                case "r":
                    r = value;
                    break;
                case "g":
                    g = value;
                    break;
                case "b":
                    b = value;
                    break;
            }
        }

        return new Color(r, g, b);
    }
}
