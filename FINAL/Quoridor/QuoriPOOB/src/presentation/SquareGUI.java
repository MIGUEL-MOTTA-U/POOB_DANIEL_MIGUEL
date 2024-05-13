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
    private boolean drawToken = false;
    private Color colorToken;

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

    private void prepareElements() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

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

    private void prepareActions() {
        buttonWallUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(buttonWallUp);
            }
        });

        buttonWallLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(buttonWallLeft);
            }
        });

        buttonWallDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(buttonWallDown);
            }
        });

        buttonWallRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                showTypeWallDialog(buttonWallRight);
            }
        });
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

    private void addWall(String type, JButton button) {
        String squareSide;

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
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border coloredBorder;

        if (button == buttonWallUp) {
            coloredBorder = BorderFactory.createMatteBorder(2, 0, 0, 0, color);
        } else if (button == buttonWallLeft) {
            coloredBorder = BorderFactory.createMatteBorder(0, 2, 0, 0, color);
        } else if (button == buttonWallDown) {
            coloredBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, color);
        } else {
            coloredBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, color);
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

