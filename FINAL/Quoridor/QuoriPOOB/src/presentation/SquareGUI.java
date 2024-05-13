package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class SquareGUI extends JPanel {
    private static final Color COLOR_HOVER = new Color(235, 235, 235);
    
    private QuoridorGUI quoridorGUI;
    private int row;
    private int column;
    private boolean drawCircle = false;

    // Botones
    JButton buttonWallUp;
    JButton buttonWallDown;
    JButton buttonWallLeft;
    JButton buttonWallRight;

    public SquareGUI(QuoridorGUI quoridorGUI, int row, int column) {
        this.quoridorGUI = quoridorGUI;
        this.row = row;
        this.column = column;
        prepareElements();
    }

    public void drawCircle() {
        this.drawCircle = true;
    }

    public void eraseCircle() {
        this.drawCircle = false;
    }

    private void prepareElements() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        buttonWallUp = createButton();
        buttonWallDown = createButton();
        buttonWallLeft = createButton();
        buttonWallRight = createButton();
        
        buttonWallUp.setPreferredSize(new Dimension(getWidth(), 5));
        buttonWallDown.setPreferredSize(new Dimension(getWidth(), 5));
        buttonWallLeft.setPreferredSize(new Dimension(5, getHeight())); 
        buttonWallRight.setPreferredSize(new Dimension(5, getHeight()));

        add(buttonWallUp, BorderLayout.NORTH);
        add(buttonWallDown, BorderLayout.SOUTH);
        add(buttonWallLeft, BorderLayout.WEST);
        add(buttonWallRight, BorderLayout.EAST);

    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);

         button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                Color color = quoridorGUI.getPlayerPlaying().getColor();
                setBorder(paintBorder(button, color));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    public void setWallUp() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        paintBorder(buttonWallUp, color);
        removeButtonMouseListeners(buttonWallUp);
    }

    public void setWallLeft() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        paintBorder(buttonWallLeft, color);
        removeButtonMouseListeners(buttonWallLeft);
    }

    public void setWallDown() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        paintBorder(buttonWallDown, color);
        removeButtonMouseListeners(buttonWallDown);
    }

    public void setWallRight() {
        Color color = quoridorGUI.getPlayerPlaying().getColor();
        paintBorder(buttonWallRight, color);
        removeButtonMouseListeners(buttonWallRight);
    }

    private Border paintBorder(JButton button, Color color) {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border coloredBorder;

        if (button == buttonWallUp) {
            coloredBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, color);
        } else if (button == buttonWallLeft) {
            coloredBorder = BorderFactory.createMatteBorder(0, 1, 0, 0, color);
        } else if (button == buttonWallDown) {
            coloredBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, color);
        } else {
            coloredBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, color);
        }
        
        Border compoundBorder = BorderFactory.createCompoundBorder(blackBorder, coloredBorder);
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

        if (drawCircle) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int circleRadius = Math.min(panelWidth, panelHeight) / 4;
            int circleX = (panelWidth - circleRadius * 2) / 2;
            int circleY = (panelHeight - circleRadius * 2) / 2;

            g.setColor(Color.RED);
            g.fillOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
        }
    }
}

