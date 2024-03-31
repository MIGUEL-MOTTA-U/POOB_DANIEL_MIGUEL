import java.util.ArrayList;

public abstract class Barco extends nodriza{
    private int numero;
    private ArrayList<Marino> marinos;

    @Override
    public boolean esDebil(){
        return (marinos.size() < 5) ? true : false;
    }
    
    public ArrayList<Marino> getMarinos(){
        return this.marinos;
    }
    
    @Override
    public void autodestruir(String razon){
        if(!this.destruido){
            this.destruido = true;
            this.razon = razon;
            for(Marino m : this.marinos){
                m.autodestruir("El Barco fue destruido");
            }
        }
    }
}
