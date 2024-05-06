public class SistemaException extends Exception {
    public static final String NO_EXISTE_ESTACION = "La estacion no existe en el sistema.";
    public SistemaException(String message){
        super(message);
    }
}
