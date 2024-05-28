package presentation;

import java.awt.*;
import javax.swing.*;

/**
 * This class draws a circle on the panel
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class CirclePanel extends JPanel {
    private Color colorCircle;

    /**
     * Constructor of CirclePanel
     */
    public CirclePanel(Color color) {
        this.colorCircle = color;
        setBackground(Color.WHITE);
    }

    /**
     * Paint a circle on the panel
     * 
     * @param newColor the circle color
     */
    public void setCircleColor(Color newColor) {
        this.colorCircle = newColor;
    }

    /*
     * Paint a circle on the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = (int) (Math.min(getWidth(), getHeight()) * 0.35);
        g.setColor(this.colorCircle);
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }
}
