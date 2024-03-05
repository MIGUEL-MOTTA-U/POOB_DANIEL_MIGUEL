 

public class Avion extends Maquina{
    private String placa;
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    
    @Override
    public boolean seraDestruida(int longitud, int latitud){
        boolean res = false;
        if(!enAire){
            res = super.seraDestruida(longitud, latitud);
        }
        return res;
    }
}
