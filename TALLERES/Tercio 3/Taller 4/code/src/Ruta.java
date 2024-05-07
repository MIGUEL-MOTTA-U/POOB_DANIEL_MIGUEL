import java.util.LinkedList;

public class Ruta {
    private String nombre;
    private LinkedList<Estacion> estaciones;

    public Ruta(String nombre) {
        this.nombre = nombre;
        this.estaciones = new LinkedList<>();
    }

    public String getName() {
        return nombre;
    }

    public void setEstacion(Estacion estacion) {
        this.estaciones.add(estacion);
    }

    public int numeroParadas(String estacion1, String estacion2) {
        boolean encontradaEstacion1 = false;
        boolean encontradaEstacion2 = false;
        int contadorParadas = 0;

        for (Estacion e : estaciones) {
            if (!encontradaEstacion1 && e.getName().equals(estacion1)) {
                encontradaEstacion1 = true;
            } else if (encontradaEstacion1 && !encontradaEstacion2) {
                contadorParadas++;
            }
            if (encontradaEstacion1 && e.getName().equals(estacion2)) {
                encontradaEstacion2 = true;
                break;
            }
        }

        return contadorParadas;
    }

    public boolean esTransbordo(Estacion estacion1, Estacion estacion2) {
        boolean res = false;
        if (estaciones.contains(estacion1) && !estaciones.contains(estacion2)) {
            res = true;
        }

        return res;
    }

    public boolean contieneEstaciones(Estacion estacion1, Estacion estacion2) {
        boolean contiene = false;
        if (estaciones.contains(estacion1) && estaciones.contains(estacion2)) {
            contiene = true;
        }

        return contiene;
    }
}