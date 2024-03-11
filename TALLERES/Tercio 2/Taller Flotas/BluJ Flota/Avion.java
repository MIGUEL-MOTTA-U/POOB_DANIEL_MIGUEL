 

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
}
