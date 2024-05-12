package domain;

public class GardenException extends Exception {
    public static final String CLASS_NOT_FOUND = "No se pudo encontrar la clase necesaria para deserializar el objeto";
    public static final String FILE_NULL = "El archivo proporcionado es nulo";

    public GardenException(String message) {
        super(message);
    }

    public static final String ERROR_OPEN(String option, String fileName) {
        return "Opcion " + option + " en construccion. Archivo " + fileName;
    }

    public static final String FILE_NOT_FOUND(String fileName) {
        return "No se pudo encontrar el archivo: " + fileName;
    }

    public static final String SAVE_FILE_ERROR(String fileName) {
        return "Error al guardar el archivo: " + fileName;
    }

    public static final String OPEN_FILE_ERROR(String fileName) {
        return "Error al abrir el archivo: " + fileName;
    }
}
