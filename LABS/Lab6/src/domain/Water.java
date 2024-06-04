package domain;

import java.io.Serializable;

/**
 * Represent water in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public final class Water implements Thing, Serializable {
    /**
     * Defines the action to be performed by the water
     */
    public void act(){
    }

    /**
     * Checks if the given Water has the same attributes
     * @param t, the thing to compare this
     * @return the result of the comparison
     */
    @Override
    public boolean equals(Thing t){
        boolean res = false;
        if(t instanceof Water){
            res = true;
        }
        return res;
    }
}
