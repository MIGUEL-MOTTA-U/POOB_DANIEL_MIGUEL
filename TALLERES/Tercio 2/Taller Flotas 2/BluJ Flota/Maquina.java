import java.util.*;
 

public abstract class Maquina {
    private Ubicacion ubicacion;
    protected boolean destruido;
    protected String razon;
    public void alNorte(){
        try
        {
            this.ubicacion.alNorte();
        }
        catch (BatallaNavalExcepcion e)
        {
            System.out.println("Error generado por: " +  e.getMessage());
        }
    }
    
    public void avance(int DLon, int DLat){
        this.ubicacion.avance(DLon, DLat);
    }
    
    public boolean seraDestruida(int longitud, int latitud){
        return (ubicacion.mismaUbicacion(longitud, latitud)) ? true : false;
    }

    
    public String getRazon(){
        return this.razon;
    }
    
    public abstract boolean esDebil();
    
    public abstract ArrayList<Marino> pilotos() throws BatallaNavalExcepcion;
    
    public abstract void autodestruir(String razon);
}