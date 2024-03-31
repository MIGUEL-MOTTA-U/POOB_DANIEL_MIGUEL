import java.util.ArrayList;

public class Tablero {
     private ArrayList<Flota> flotas;
     
     public int alNorte() throws BatallaNavalExcepcion{
         int res = 0;
         int fallas = 0;
         for(Flota f: flotas){
             try{
                 f.alNorte();
                 res++;
             } catch (BatallaNavalExcepcion ex){
             }
         }
         return res;
     }
     
     public int potencia()throws BatallaNavalExcepcion{
         int res = 0;
         int fallas = 0;
         for(Flota f :flotas){
             try{
             res+= f.potencia();
            } catch (BatallaNavalExcepcion ex){
                fallas +=1;    
            }
         }
         if(fallas > res / 2){
             throw new BatallaNavalExcepcion ("La mitad de las flotas tienen problemas de potencia");
         }
         return res;
     }
     
     public ArrayList<Flota> infiltrados() throws BatallaNavalExcepcion{
         ArrayList<Flota> infiltrados= new ArrayList<>();
         for(Flota f: this.flotas){
            if(f.infiltrados()){
                infiltrados.add(f);
            }
         }
         return infiltrados;
     }
}
