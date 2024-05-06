package domain;

import java.awt.Color;

public class Allied extends Wall {
    public Allied(Color color) {
        super(color);
    }

    @Override
    public boolean blockToken(Color token) {
        return (token.equals(this.color)) ? false : true;
    }

    @Override
    public void act() {

    }
}
