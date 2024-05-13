package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

import domain.QuoriPOOBException;

public class SquareGUI extends JPanel {
    private static final Color COLOR_HOVER = new Color(235, 235, 235);
    
    private QuoridorGUI quoridorGUI;
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

    public SquareGUI(QuoridorGUI quoridorGUI, int row, int column) {
        this.quoridorGUI = quoridorGUI;
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
        prepareActions();
    }

    public void drawToken() {
        this.drawToken = true;
    }

    public void eraseToken() {
        this.drawToken = false;
    }

    public void setColorToken(Color color) {
        this.colorToken = color;
    }

    public void setWallUp(boolean bool) {
        this.wallUp = bool;
    }

    public void setWallLeft(boolean bool) {
        this.wallLeft = bool;
    }

    public void setWallDown(boolean bool) {
        this.wallDown = bool;
    }

    public void setWallRight(boolean bool) {
        this.wallRight = bool;
    }

    public boolean getWallUp() {
        return this.wallUp;
    }

    public boolean getWallLeft() {
        return this.wallLeft;
    }

    public boolean getWallDown() {
        return this.wallDown;
    }

    public boolean getWallRight() {
        return this.wallRight;
    }

    private void prepareElements() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        buttonWallUp = createButton();
        buttonWallDown = createButton();
        buttonWallLeft = createButton();
        buttonWallRight = createButton();
        
        buttonWallUp.setPreferredSize(new Dimension(getWidth(), 10));
        buttonWallDown.setPreferredSize(new Dimension(getWidth(), 10));
        buttonWallLeft.setPreferredSize(new Dimension(10, getHeight())); 
        buttonWallRight.setPreferredSize(new Dimension(10, getHeight()));

        add(buttonWallUp, BorderLayout.NORTH);
        add(buttonWallDown, BorderLayout.SOUTH);
        add(buttonWallLeft, BorderLayout.WEST);
        add(buttonWallRight, BorderLayout.EAST);
    }

    private void showTypeWallDialog(JButton button) {
        JComboBox<String> walls = createWallComboBox();

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(walls);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select the wall type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String wall = (String) walls.getSelectedItem();
            addWall(wall, button);
        }
    }

    private JComboBox<String> createWallComboBox() {
        JComboBox<String> walls = new JComboBox<>();
        walls.addItem("Normal");
        walls.addItem("Temporary");
        walls.addItem("Long");
        walls.addItem("Allied");
    
        return walls;
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);

        createMouseListener(button);

        return button;
    }

    private void createMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                Color color = quoridorGUI.getPlayerPlaying().getColor();
                setBorder(paintBorder(button, color));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                setBorder(defaultBorder());
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private void prepareActions() {
        createActionListener(buttonWallUp);
        createActionListener(buttonWallLeft);
        createActionListener(buttonWallDown);
        createActionListener(buttonWallRight);
    }

    private void createActionListener(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(button);
            }
        });
    }

    public void setWallUp() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        setBorder(paintBorder(buttonWallUp, color));
        this.colorBorderUp = color;
        removeButtonMouseListeners(buttonWallUp);
    }

    public void setWallLeft() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        setBorder(paintBorder(buttonWallLeft, color));
        this.colorBorderLeft = color;
        removeButtonMouseListeners(buttonWallLeft);
    }

    public void setWallDown() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        setBorder(paintBorder(buttonWallDown, color));
        this.colorBorderDown = color;
        removeButtonMouseListeners(buttonWallDown);
    }

    public void setWallRight() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        setBorder(paintBorder(buttonWallRight, color));
        this.colorBorderRight = color;
        removeButtonMouseListeners(buttonWallRight);
    }
    
    public void delWallUp() {
        setBorder(paintBorder(buttonWallUp, Color.BLACK));
        this.colorBorderUp = Color.BLACK;
        createMouseListener(buttonWallUp);
        createActionListener(buttonWallUp);
    }

    public void delWallLeft() {
        setBorder(paintBorder(buttonWallLeft, Color.BLACK));
        this.colorBorderLeft = Color.BLACK;
        createMouseListener(buttonWallLeft);
        createActionListener(buttonWallLeft);
    }

    public void delWallDown() {
        setBorder(paintBorder(buttonWallDown, Color.BLACK));
        this.colorBorderDown = Color.BLACK;
        createMouseListener(buttonWallDown);
        createActionListener(buttonWallDown);
    }

    public void delWallRight() {
        setBorder(paintBorder(buttonWallRight, Color.BLACK));
        this.colorBorderRight = Color.BLACK;
        createMouseListener(buttonWallRight);
        createActionListener(buttonWallRight);
    }

    private void addWall(String type, JButton button) {
        String squareSide;

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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Border paintBorder(JButton button, Color color) {
        Border borderUp;
        Border borderLeft;
        Border borderDown;
        Border borderRight;

        if (button == buttonWallUp) {
            borderUp = BorderFactory.createMatteBorder(1, 0, 0, 0, color);
            borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, colorBorderLeft);
            borderDown = BorderFactory.createMatteBorder(0, 0,1 , 0, colorBorderDown);
            borderRight = BorderFactory.createMatteBorder(0, 0, 0, 1, colorBorderRight);
        } else if (button == buttonWallLeft) {
            borderUp = BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorderUp);
            borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, color);
            borderDown = BorderFactory.createMatteBorder(0, 0,1 , 0, colorBorderDown);
            borderRight = BorderFactory.createMatteBorder(0, 0, 0, 1, colorBorderRight);
        } else if (button == buttonWallDown) {
            borderUp = BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorderUp);
            borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, colorBorderLeft);
            borderDown = BorderFactory.createMatteBorder(0, 0, 1, 0, color);
            borderRight = BorderFactory.createMatteBorder(0, 0, 0, 1, colorBorderRight);
        } else {
            borderUp = BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorderUp);
            borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, colorBorderLeft);
            borderDown = BorderFactory.createMatteBorder(0, 0,1 , 0, colorBorderDown);
            borderRight = BorderFactory.createMatteBorder(0, 0, 0, 1, color);
        }

        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(borderUp, borderDown),
                BorderFactory.createCompoundBorder(borderLeft, borderRight));
        return compoundBorder;
    }

    private Border defaultBorder() {
        Border borderUp = BorderFactory.createMatteBorder(1, 0, 0, 0, colorBorderUp);
        Border borderLeft = BorderFactory.createMatteBorder(0, 1, 0, 0, colorBorderLeft);
        Border borderDown = BorderFactory.createMatteBorder(0, 0, 1, 0, colorBorderDown);
        Border borderRight = BorderFactory.createMatteBorder(0, 0, 0, 1, colorBorderRight);
        Border compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(borderUp, borderDown),
                BorderFactory.createCompoundBorder(borderLeft, borderRight));
                
        return compoundBorder;
    }

    private void removeButtonMouseListeners(JButton button) {
        for (MouseListener listener : button.getMouseListeners()) {
            button.removeMouseListener(listener);
        }
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
}

