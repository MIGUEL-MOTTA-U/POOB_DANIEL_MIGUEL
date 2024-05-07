import java.util.HashMap;

public class Estacion {
    private String name;
    private String nivelOcupacion;
    private HashMap<String, Integer> tiempoEsperaEstacion;

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> tiempoEsperaEstacion() throws SistemaException {
        if (tiempoEsperaEstacion == null)
            throw new SistemaException(SistemaException.TIEMPO_ESPERA_NULO);
            
        return tiempoEsperaEstacion;
    }
}
