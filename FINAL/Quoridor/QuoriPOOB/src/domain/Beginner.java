package domain;

import java.awt.Color;

public class Beginner extends Machine{

    public Beginner(String name, Color color) {
        super(name, color);
    }

    @Override
    public void play(String direction) throws QuoriPOOBException{
        moveToken(direction); // No se va a mover si no 
        
    }

    
    
}
