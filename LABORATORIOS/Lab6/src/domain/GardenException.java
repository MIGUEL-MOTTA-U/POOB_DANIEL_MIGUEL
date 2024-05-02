package domain;
public class GardenException extends Exception{
    public GardenException(String message){
        super(message);
    }

    public static final String ERROR_OPEN(String option, String fileName){
        return "Opcion " + option + " en construccion. Archivo " + fileName;
    }
}
