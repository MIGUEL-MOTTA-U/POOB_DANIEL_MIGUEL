package presentation;

import java.awt.*;
import javax.swing.*;

public class CirclePanel extends JPanel {
    private Color colorCircle;

    public CirclePanel(Color color) {
        this.colorCircle = color;
        setBackground(Color.WHITE);
    }

    public void setCircleColor(Color newColor) {
        this.colorCircle = newColor;
    }

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
