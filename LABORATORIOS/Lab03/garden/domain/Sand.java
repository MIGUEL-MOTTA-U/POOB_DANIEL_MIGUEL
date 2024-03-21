package domain;
import java.awt.Color;

public class Sand implements Thing{
    private Color color;
    private int time;

    public Sand(){
        this.time = 0;
        this.color = Color.GRAY;
    }

    @Override
    public void act(){
        this.turn();
        int fadeRate = 10;
        int red = Math.min(this.color.getRed() + fadeRate, 255);
        int green = Math.min(this.color.getGreen() + fadeRate, 255);
        int blue = Math.min(this.color.getBlue() + fadeRate, 255);

        this.color = new Color(red, green, blue);
    }

    public void turn(){
        this.time++;
    }

    @Override
    public final Color getColor(){
        return this.color;
    }
}
