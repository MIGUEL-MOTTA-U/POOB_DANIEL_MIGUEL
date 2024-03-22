package domain;
import java.awt.Color;

public class Sand extends Agent implements Thing{
    public Sand(Garden garden,int row, int column){
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = new Color(0.57f, 0.57f, 0.57f);
    }

    @Override
    public void act(){
        this.turn();

        if (super.getTime() != 100) {  
            float fadeRate = 0.005f;
            float red = Math.min((this.color.getRed() / 255.0f) + fadeRate, 1.0f);
            float green = Math.min((this.color.getGreen() / 255.0f) + fadeRate, 1.0f);
            float blue = Math.min((this.color.getBlue() / 255.0f) + fadeRate, 1.0f);

            this.color = new Color(red, green, blue);
        } else {
            garden.setThing(this.row, this.column, null);
        }
    }

    @Override
    public Color getColor(){
        return this.color;
    }

    public void move(){

    }
}
