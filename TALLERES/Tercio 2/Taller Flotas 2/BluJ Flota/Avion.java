import java.util.*;
 

public class Avion extends Maquina{
    private String placa;
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    
    
    @Override
    public boolean seraDestruida(int longitud, int latitud){
        return (!enAire) ? super.seraDestruida(longitud, latitud) : false;
    }

    @Override
    public boolean esDebil(){
        return (piloto == null) ? true : false;
    }

    public boolean getEnAire(){
        return this.enAire;
    }
    
    @Override
    public ArrayList<Marino> pilotos(){
        ArrayList<Marino> marinos = new ArrayList<>();
        marinos.add(this.piloto);
        return marinos;
    }
    
    public String autodestruir(String razon){
        String res = null;
        if(!this.destruido){
            if(piloto != null){
                piloto.autodestruir("Avión derribado");
            }
            
            if(copiloto != null){
                copiloto.autodestruir("Avión derribado");
            }
        }
        return res;
    }
}