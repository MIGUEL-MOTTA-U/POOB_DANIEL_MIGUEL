package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SquareGUI extends JPanel {
    private static final Color COLOR_HOVER = new Color(235, 235, 235);
    private boolean drawCircle = false;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el círculo solo si drawCircle es verdadero
        if (drawCircle) {
            // Obtener las dimensiones del panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calcular las coordenadas para dibujar el círculo en el centro
            int circleRadius = Math.min(panelWidth, panelHeight) / 4;
            int circleX = (panelWidth - circleRadius * 2) / 2;
            int circleY = (panelHeight - circleRadius * 2) / 2;

            // Dibujar el círculo
            g.setColor(Color.RED);
            g.fillOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
        }
    }
}

