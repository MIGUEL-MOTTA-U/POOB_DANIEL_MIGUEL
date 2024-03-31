import java.util.ArrayList;

public class Flota {    
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Object> autodestruidos;
    
    public void alNorte(){
  
        for(Maquina maquina : maquinas){
            maquina.alNorte();
        }

        }
    
    public void avance(int dLon, int DLat){
        for(Maquina maquina : maquinas){
            maquina.avance(dLon, DLat);
        }
    }
    
    public ArrayList<Maquina> seranDestruidas(int longitud, int latitud){
        ArrayList<Maquina> res = new ArrayList<>();
        for(Maquina maquina : maquinas){
            if(maquina.seraDestruida(longitud, latitud)){
                res.add(maquina);
                maquina.autodestruir(null);
            }
        }
        this.autodestruidos.addAll(res);
        return res;
    }

    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> res = new ArrayList<>();
        for(Maquina maquina : maquinas){
            if(maquina.esDebil()){
                res.add(maquina);
            }
        }
        return res;
    }

    public boolean esBuenAtaque(int longitud, int latitud){
        boolean res = false;
        ArrayList<Maquina> eliminarMaquinas = this.seranDestruidas(longitud, latitud);
        if(!maquinas.isEmpty()){
            
            maquinas.removeAll(eliminarMaquinas);
            res = true;
        }
        return res;
    }

    public void ataquen(int lon, int lat){
        int dLon = 0;
        int dLat = 0;
        for(Maquina maquina : maquinas){
            
            if (!maquina.esDebil()) {
                int maxDistance = Math.max(lon, lat); 
                for (int i = 0; i <= maxDistance; i++) {
                    dLon = (i <= lon) ? i : 0;
                    dLat = (i <= lat) ? i : 0; 
                    this.avance(dLon, dLat);
                }
            }
        }   
    }
    
    public ArrayList<Marino> pilotos() throws BatallaNavalExcepcion{
        ArrayList pilotos = new ArrayList<>();
        
        for(Maquina m : this.maquinas){
            ArrayList<Marino> pilotosMaquina = m.pilotos();
            
            for(int i = 0; i <= pilotosMaquina.size(); i++){
                if(!this.marinos.contains(pilotosMaquina.get(i))){
                    throw new BatallaNavalExcepcion("Error piloto no es marino de la flota");
                }
            }
            pilotos.addAll(pilotosMaquina);
        }
        return pilotos;
    }
    
    public int potencia()throws BatallaNavalExcepcion{
        if (this.maquinas.size() > this.marinos.size()){
            throw new BatallaNavalExcepcion("Hay menos marinos que maquinas");
        }
        int res = 0;
        for(Maquina m : this.maquinas){
            if(!m.esDebil()){
                res+=1;
            }
        }
        return res;
    }
    
        
    public ArrayList<Object> getAutodestruidos(){
        return this.autodestruidos;
    }
    
    
    
    
}
