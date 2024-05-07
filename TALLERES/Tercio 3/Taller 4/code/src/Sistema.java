import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

    public ArrayList<String> nombreRutas() {
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

    public void importarRuta(File file) {
        try {
            BufferedReader bIn = new BufferedReader(new FileReader(file));
            String line = bIn.readLine();
            Ruta rutaNueva = new Ruta(line);

            while (line != null) {
                line = line.trim();
                line = bIn.readLine();
                Estacion estacion = new Estacion(line);
                rutaNueva.setEstacion(estacion);
            }

            bIn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportarArchivo(String nombreArchivo, String nombreEstacion1, String nombreEstacion2) throws SistemaException {
        if (!estaciones.containsKey(nombreEstacion1) || !estaciones.containsKey(nombreEstacion2))
            throw new SistemaException(SistemaException.NO_EXISTE_ESTACION);

        Estacion estacion1 = this.estaciones.get(nombreEstacion1);
        Estacion estacion2 = this.estaciones.get(nombreEstacion2);

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(nombreArchivo));
            pw.println("Rutas que van de " + nombreEstacion1 + " a " + nombreEstacion2 + " sin transbordos:");

            for (Ruta ruta : this.rutas.values()) {
                if (ruta.contieneEstaciones(estacion1, estacion2)) {
                    pw.println(ruta.getName());
                }
            }

            pw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarTroncal(String nombreTroncal) throws SistemaException {
        Troncal troncal = null;

        for (Troncal t : this.troncales) {
            if (t.getName().equals(nombreTroncal)) {
                troncal = t;
                break;
            }
        }

        if (troncal == null) throw new SistemaException(SistemaException.NO_EXISTE_TRONCAL);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nombreTroncal));
            out.writeObject(troncal);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}