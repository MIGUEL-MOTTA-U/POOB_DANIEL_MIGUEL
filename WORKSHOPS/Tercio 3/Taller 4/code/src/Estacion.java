import java.util.HashMap;

public class Estacion {
    private String nombre;
    private String nivelOcupacion;
    private HashMap<String, Integer> tiempoEsperaEstacion;

    public Estacion(String nombre) {
        this.nombre = nombre;
        this.tiempoEsperaEstacion = new HashMap<>();
    }

    public String getName() {
        return nombre;
    }

    public HashMap<String, Integer> tiempoEsperaEstacion() throws SistemaException {
        if (tiempoEsperaEstacion == null)
            throw new SistemaException(SistemaException.TIEMPO_ESPERA_NULO);

        return tiempoEsperaEstacion;
    }
}
