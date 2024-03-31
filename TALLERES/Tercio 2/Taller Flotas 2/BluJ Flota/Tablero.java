import java.util.ArrayList;

public class Tablero {
     private ArrayList<Flota> flotas;
     
     public int alNorte(){
         int res = 0;
         for(Flota f: flotas){
             f.alNorte();
         }
         return res;
     }
     
     public int potencia()throws BatallaNavalExcepcion{
         int res = 0;
         for(Flota f :flotas){
             res+= f.potencia();
         }
         return res;
     }
}
