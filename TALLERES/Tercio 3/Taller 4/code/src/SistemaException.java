public class SistemaException extends Exception {
    public static final String NO_EXISTE_ESTACION = "La estacion no existe en el sistema.";
    public static final String NO_EXISTE_TRONCAL = "La troncal no existe en el sistema.";
    public static final String TIEMPO_ESPERA_NULO = "No esta definido el tiempo de espera de la estacion.";

    public SistemaException(String message) {
        super(message);
    }
}
