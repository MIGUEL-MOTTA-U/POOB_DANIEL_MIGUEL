import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public class Sistema {
    private ArrayList<Troncal> troncales;
    private HashMap<String, Estacion> estaciones; 
    private Map<String, Ruta> rutas = new TreeMap<>();
    
    public ArrayList<String> nombreRutas(String name){
        ArrayList<String> result = new ArrayList();
        for(Ruta r: rutas.values()){
            result.add(r.getName());
        }
        return result;
    }

    public int numeroParadas(String ruta,String estacion1,String estacion2){
        int res = 0;
        rutas.get(ruta).numeroParadas(estacion1,  estacion2);
        return res;
    }
}