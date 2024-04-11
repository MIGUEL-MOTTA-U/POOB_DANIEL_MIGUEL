import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;

/**
 * This Spot's subclass has the same behaviors that a normal Spot,
 * except that when the spider reaches this spot, it jump to the next strand.
 * 
 * @author (Daniel Diaz y Miguel Motta)
 * @version (CICLO 4 / 2024 - 01)
 */

public class Bouncy extends Spot {
    private Circle leftArm;
    private Circle rightArm;
    private Circle downArm;
    private Circle upArm;
    /**
     * Constructor for objects of class Bouncy
     */
    public Bouncy(String color, int strand, Web web,  boolean isVisible) {
        super(color, strand, web, isVisible);
    }
    
    private void rotateColors(){
        if (leftArm != null && downArm != null && upArm != null && rightArm != null) {    
            String color1 = leftArm.getColor();
            String color2 = downArm.getColor();
            String color3 = upArm.getColor();
            String color4 = rightArm.getColor();
            rotateArms(-5);
            leftArm.changeColor(color2);
            downArm.changeColor(color4);
            upArm.changeColor(color1);
            rightArm.changeColor(color3);
        }
    }
    
    /*
     * Rotates the arms
    */    
    private void rotateArms(double angleDegrees) {
        double centerX = this.body.getX(); // Coordenada x del centro del cuerpo
        double centerY = this.body.getY(); // Coordenada y del centro del cuerpo
        // Convertir el ángulo de grados a radianes
        double angleRadians = Math.toRadians(angleDegrees);    
        // Calcular las nuevas coordenadas de cada brazo
        double newXLeft = centerX + (leftArm.getX() - centerX) * Math.cos(angleRadians) - (leftArm.getY() - centerY) * Math.sin(angleRadians);
        double newYLeft = centerY + (leftArm.getX() - centerX) * Math.sin(angleRadians) + (leftArm.getY() - centerY) * Math.cos(angleRadians);
        double newXRight = centerX + (rightArm.getX() - centerX) * Math.cos(angleRadians) - (rightArm.getY() - centerY) * Math.sin(angleRadians);
        double newYRight = centerY + (rightArm.getX() - centerX) * Math.sin(angleRadians) + (rightArm.getY() - centerY) * Math.cos(angleRadians);
        double newXDown = centerX + (downArm.getX() - centerX) * Math.cos(angleRadians) - (downArm.getY() - centerY) * Math.sin(angleRadians);
        double newYDown = centerY + (downArm.getX() - centerX) * Math.sin(angleRadians) + (downArm.getY() - centerY) * Math.cos(angleRadians);
        double newXUp = centerX + (upArm.getX() - centerX) * Math.cos(angleRadians) - (upArm.getY() - centerY) * Math.sin(angleRadians);
        double newYUp = centerY + (upArm.getX() - centerX) * Math.sin(angleRadians) + (upArm.getY() - centerY) * Math.cos(angleRadians);
        // Actualizar las coordenadas de cada brazo
        leftArm.setX(newXLeft);
        leftArm.setY(newYLeft);
        rightArm.setX(newXRight);
        rightArm.setY(newYRight);
        downArm.setX(newXDown);
        downArm.setY(newYDown);
        upArm.setX(newXUp);
        upArm.setY(newYUp);
    }

    
    /**
     * Add the spot to the web
     * 
     * @param strands the strands of the web
     */
    @Override
    public void addSpot(HashMap<Integer, Strand> strands) {
        super.addSpot(strands);
        createBody();
        draw();
    }
    
    
     /*
     * Crete the new body of the bouncy spot to differentiate it
     */
    private void createBody() {
        
        double x = this.body.getX();
        double y = this.body.getY();

        this.leftArm = new Circle(x-5, y, "red", this.isVisible, 5);
        this.rightArm = new Circle(x+5, y, "red", this.isVisible, 5);
        this.downArm = new Circle(x, y-5, "green", this.isVisible, 5);
        this.upArm = new Circle(x, y+5, "green", this.isVisible, 5);
    }

    /**
     * Its the behavior of the spot
     */
    @Override
    public void act() {
        for(int i = 0; i<60;i++) rotateColors();
        this.web.spiderJump();
    }
    
    /*
     * Erase the spot on screen.
     */
    @Override
    protected void draw() {
        if (isVisible) {
            this.leftArm.makeVisible();
            this.rightArm.makeVisible();
            this.upArm.makeVisible();
            this.downArm.makeVisible();
        }
        super.draw();
    }
    
    /*
     * Draw the spot with current specifications on screen.
     */
    @Override
    protected void erase() {
        super.erase();
        if (isVisible) {
            this.leftArm.makeInvisible();
            this.rightArm.makeInvisible();
            this.upArm.makeInvisible();
            this.downArm.makeInvisible();
        }
    }
}
