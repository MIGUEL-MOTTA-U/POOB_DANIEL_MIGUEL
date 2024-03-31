import java.util.ArrayList;

public class PortaAviones extends Barco{
    private int capacidad;
    private ArrayList<Avion> aviones;
    private ArrayList<Marino> marinos = super.getMarinos();
    
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
    public ArrayList<Marino> pilotos() throws BatallaNavalExcepcion{
        ArrayList<Marino> marinos = new ArrayList<>();
        for(Avion a : aviones){
            ArrayList<Marino> pilotos = a.pilotos();
            if(!this.marinos.contains(pilotos.get(0))){
                throw new BatallaNavalExcepcion("Error piloto no es marino del porta avion");
            }
            marinos.addAll(pilotos);
        } 
        
        for(Avion a : aviones){ 
            if(!marinos.contains(a.pilotos())){
                throw new BatallaNavalExcepcion("Error piloto asignado a mas de un avion");
            }
        }
        return marinos;
    }
}