 

import java.util.ArrayList;

public class Barco extends nodriza{
    private int numero;
    private ArrayList<Marino> marinos;

    @Override
    public boolean esDebil(){
        return (marinos.size() < 5) ? true : false;
    }
}
