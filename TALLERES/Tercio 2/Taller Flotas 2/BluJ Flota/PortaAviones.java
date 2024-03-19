import java.util.ArrayList;

public class PortaAviones extends Barco{
    private int capacidad;
    private ArrayList<Avion> aviones;

    @Override
    public boolean esDebil(){
        boolean res = false;
        if(super.esDebil()){
            res = true;
        }else{
            for(Avion avion : aviones){
                if(avion.getEnAire() && avion.esDebil()){
                    res = true;
                }
            }
        }
        return res;
    }
    
    @Override
    public String autodestruir(String razon){
        return super.autodestruir(razon);
    }
}
