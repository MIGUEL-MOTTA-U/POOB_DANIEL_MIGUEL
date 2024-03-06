 

import java.util.ArrayList;

public class Flota {    
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    
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
            }
        }
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
}
