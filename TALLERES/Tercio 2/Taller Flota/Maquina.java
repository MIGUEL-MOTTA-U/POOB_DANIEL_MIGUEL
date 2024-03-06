 

public class Maquina {
    private Ubicacion ubicacion;
    
    public void alNorte(){
        this.ubicacion.alNorte();
    }
    
    public void avance(int DLon, int DLat){
        this.ubicacion.avance(DLon, DLat);
    }
    
    public boolean seraDestruida(int longitud, int latitud){
        return (ubicacion.mismaUbicacion(longitud, latitud)) ? true : false;
    }
}
