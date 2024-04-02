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
            for(int i = 0; i <= pilotos.size(); i++){
                if(!this.marinos.contains(pilotos.get(i))){
                    throw new BatallaNavalExcepcion(BatallaNavalExcepcion.INCONSISTENCIA_PILOTO_PORTAAVIONES);
                }
            }
            marinos.addAll(pilotos);
        } 

        return marinos;
    }
}