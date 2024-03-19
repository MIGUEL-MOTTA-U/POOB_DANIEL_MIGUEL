 

public class Ubicacion {
    private int longitud;
    private int latitud;
    
    private boolean inLimits(int lon, int lat){
        boolean res = false;
        res = (Math.pow((this.longitud + lon) - 90 ,2) + Math.pow(this.latitud + lat, 2) <= Math.pow(90, 2)) ? true : false;
        return res;
    }
    
    public void alNorte(){
        if(inLimits(0, 1)){
            this.latitud += 1;
        }else{
            System.out.println("Out of limits");
        }
    }
    
    public void avance(int DLon, int DLat){
        if(inLimits(DLon, DLat)){
            this.latitud += DLat;
            this.longitud += DLon;
        }else{
            System.out.println("Out of limits");
        }
    }
    
    public boolean mismaUbicacion(int lon, int lat){        
        return (this.longitud == lon && this.latitud == lat) ? true : false;
    }
}
