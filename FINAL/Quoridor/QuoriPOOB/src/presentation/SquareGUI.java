package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SquareGUI extends JPanel {
    private static final Color COLOR_HOVER = new Color(235, 235, 235);
    private QuoridorGUI quoridorGUI;

    // Botones
    JButton topButton;
    JButton bottomButton;
    JButton leftButton;
    JButton rightButton;

    public SquareGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
    }

    private void prepareElements() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        topButton = createButton();
        bottomButton = createButton();
        leftButton = createButton();
        rightButton = createButton();
        
        topButton.setPreferredSize(new Dimension(getWidth(), 5));
        bottomButton.setPreferredSize(new Dimension(getWidth(), 5));
        leftButton.setPreferredSize(new Dimension(5, getHeight())); 
        rightButton.setPreferredSize(new Dimension(5, getHeight()));

        add(topButton, BorderLayout.NORTH);
        add(bottomButton, BorderLayout.SOUTH);
        add(leftButton, BorderLayout.WEST);
        add(rightButton, BorderLayout.EAST);

    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);

         button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setBackground(COLOR_HOVER);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                button.setBackground(Color.WHITE);
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }
}

