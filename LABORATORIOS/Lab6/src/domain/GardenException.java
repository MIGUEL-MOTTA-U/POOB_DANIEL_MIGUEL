package domain;

public class GardenException extends Exception {
    public static final String CLASS_NOT_FOUND = "No se pudo encontrar la clase necesaria para deserializar el objeto";
    public static final String FILE_NULL = "El archivo proporcionado es nulo";
    public static final String NUMBER_ERROR = "No se puede convertir la cadena a numero";

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

    public static final String IMPORT_FILE_ERROR(String fileName) {
        return "Error al importar el archivo: " + fileName;
    }

    public static final String CLASS_NOT_FOUND(String type) {
        return "No se pudo encontrar la clase necesaria: " + type;
    }

    public static final String CONSTRUCTOR_NOT_FOUND(String type) {
        return "Constructor no encontrado para la clase: " + type;
    }
    
    public static final String INSTANTIATION_ERROR(String type) {
        return "Error al instanciar la clase: " + type;
    }

    public static final String CONSTRUCTOR_ACCESS_FOUND(String type) {
        return "Error al acceder al constructor de la clase: " + type;
    }

    public static final String ILLEGAL_ARGUMENT(String type) {
        return "Argumento ilegal al llamar al constructor de la clase: " + type;
    }
    
    public static final String INVOCATION_ERROR(String type) {
        return "Error durante la invocación del constructor de la clase: " + type;
    }
    
    public static final String IMPORT_ERROR(int line, String message, String lineContent) {
        return "Error en la linea " + line + ": " + lineContent + ".\n" + "--> " + message;
    }
}
