import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Esta es la propuesta de un sistema adaptado a Transmilenio con sus
 * respectivas clases.
 * 
 * @version Martes 7 de Mayo 2024
 * @author Daniel Diaz y Miguel Motta
 */
public class Sistema {
    private ArrayList<Troncal> troncales;
    private HashMap<String, Estacion> estaciones;
    private Map<String, Ruta> rutas = new TreeMap<>();

    public HashMap<String, Integer> tiempoEsperaEstacion(String name) throws SistemaException {
        if (!estaciones.containsKey(name))
            throw new SistemaException(SistemaException.NO_EXISTE_ESTACION);

        Estacion estacion = estaciones.get(name);

        return estacion.tiempoEsperaEstacion();
    }

    public ArrayList<String> nombreRutas(String name) {
        ArrayList<String> result = new ArrayList<String>();
        for (Ruta r : rutas.values()) {
            result.add(r.getName());
        }
        Collections.sort(result);

        return result;
    }

    public int numeroParadas(String ruta, String estacion1, String estacion2) throws SistemaException {
        if (!estaciones.containsKey(estacion1) || !estaciones.containsKey(estacion2))
            throw new SistemaException(SistemaException.NO_EXISTE_ESTACION);

        int res = 0;
        rutas.get(ruta).numeroParadas(estacion1, estacion2);

        return res;
    }

    public TreeMap<String, Ruta> rutasConTransbordo(String estacion1, String estacion2) throws SistemaException {
        if (!estaciones.containsKey(estacion1) || !estaciones.containsKey(estacion2))
            throw new SistemaException(SistemaException.NO_EXISTE_ESTACION);

        Estacion e1 = estaciones.get(estacion1);
        Estacion e2 = estaciones.get(estacion2);
        TreeMap<String, Ruta> res = new TreeMap<>();
        for (Ruta r : rutas.values()) {
            if (r.esTransbordo(e1, e2)) {
                res.put(r.getName(), r);
            }
        }
        
        return res;
    }

    public String mejorPlan(String nombreEstacion1, String nombreEstacion2) throws SistemaException {
        if (!estaciones.containsKey(nombreEstacion1) || !estaciones.containsKey(nombreEstacion2))
            throw new SistemaException(SistemaException.NO_EXISTE_ESTACION);

        Estacion estacion1 = this.estaciones.get(nombreEstacion1);
        Estacion estacion2 = this.estaciones.get(nombreEstacion2);
        Ruta rutaMinNumeroParadas = null;
        int minNumeroParadas = Integer.MAX_VALUE;

        for (Ruta ruta : this.rutas.values()) {
            if (ruta.contieneEstaciones(estacion1, estacion2)) {
                int numParadas = ruta.numeroParadas(nombreEstacion1, nombreEstacion2);
                if (numParadas < minNumeroParadas) {
                    minNumeroParadas = numParadas;
                    rutaMinNumeroParadas = ruta;
                }
            }
        }

        return (rutaMinNumeroParadas != null) ? rutaMinNumeroParadas.getName() : null;
    }
}