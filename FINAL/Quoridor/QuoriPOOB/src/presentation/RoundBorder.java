package presentation;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * Create a panel with round border
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class RoundBorder extends AbstractBorder {
    private final Color borderColor;
    private final Color backgroundColor;
    private final int radius;

    /**
     * Constructor of RoundBorder
     */
    public RoundBorder(Color borderColor, Color backgroundColor, int radius) {
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.radius = radius;
    }

    /*
     * Paint the round border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibuja el fondo
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, radius, radius);

        // Dibuja el borde
        g2.setColor(borderColor);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        g2.dispose();
    }

    /*
     * Get the border insets
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    /*
     * Get the border insets
     */
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = radius;
        return insets;
    }
}