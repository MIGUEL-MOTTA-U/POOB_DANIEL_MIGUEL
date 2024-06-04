import java.util.ArrayList;

public class Flota {    
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Maquina> autodestruidos;
    
    public void alNorte()  throws BatallaNavalExcepcion{
        for(Maquina maquina : maquinas){
            try{
                maquina.alNorte();
            } catch (BatallaNavalExcepcion ex){
                throw ex;
            }
            
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
        ArrayList<Marino> pilotos = new ArrayList<>();
        
        for(Maquina m : this.maquinas){
            if (m instanceof Avion || m instanceof PortaAviones) {
                ArrayList<Marino> pilotosMaquina = m.pilotos();
                for(int i = 0; i <= pilotosMaquina.size(); i++){
                    if(!this.marinos.contains(pilotosMaquina.get(i))){
                        throw new BatallaNavalExcepcion(BatallaNavalExcepcion.INCONSISTENCIA_PILOTO_FLOTA);
                    }
                }
                pilotos.addAll(pilotosMaquina);
            }
        }
        return pilotos;
    }
    
    public int potencia()throws BatallaNavalExcepcion{
        if (this.maquinas.size() > this.marinos.size()){
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.MARINOS_INSUFICIENTES);
        }
        int res = 0;
        for(Maquina m : this.maquinas){
            if(!m.esDebil()){
                res+=1;
            }
        }
        return res;
    }
    
    public boolean infiltrados() throws BatallaNavalExcepcion{
        if(this.marinos.size() == 0){
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.SIN_MARINOS);
        }
        boolean res = false;
        for(Marino m: this.marinos){
            if(m.isInfiltrado()){
                res = true;
            }
        }
        return res;
    }
    
    public ArrayList<Maquina> getAutodestruidos(){
        return this.autodestruidos;
    }
    
}