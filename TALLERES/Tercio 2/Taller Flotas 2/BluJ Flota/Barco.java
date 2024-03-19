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
    public String autodestruir(String razon){
        String res = null;
        if(!this.destruido){
            this.destruido = true;
            res = razon;
            for(Marino m : this.marinos){
                m.autodestruir("El Barco fue destruido");
            }
        }
        return res;
    }
}
