import java.util.LinkedList;

public class Ruta {
    private String name;
    private LinkedList<Estacion> estaciones;

    public String getName(){
        return name;
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
    
}
