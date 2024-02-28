import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a simple data: boolean ('TRUE', 'FALSE'), numerical (integer or real), character (any character, if it is a digit it is considered numeric)
 *
 * @author ECI-2024-01
 */
public class Data{
    private String originalValue;
    private String value;
    private char type;

    /**
     * Constructs a new Data given its value, If it is not possible, it is assumed FALSE
     */
    public Data(String s){
        originalValue = s;
        s = s.trim(); //Quita los espacios en blanco
        this.value = s;
        if(isBoolean(s)){
            this.type = 'b';
            this.value = s.toUpperCase();
        }else if(isNumeric(s)){
            this.type = 'n';
        }else if(isCharacter(s)){
            this.type = 'c';
        }else{
            this.type = 'b';
            this.value = "FALSE";
        }
    }
  
     /**
     * Add the specified data with this data
     */   
    // boolean + boolean. + is OR
    // numerical + numerical. + is +
    // character + character. + is the largest character
    // boolean + numerical. Transform the number to boolean. If 0, true. False otherwise.
    // boolean + character. Transform the boolean to character. TRUE is T and FALSE is F.
    // + is commutative
    public Data add(Data b){
        if(this.type == 'b' && b.type == 'b'){
            return (this.value.equals("FALSE") && b.value.equals("FALSE")) ? new Data("FALSE") : new Data("TRUE");
        }else if(this.type == 'n' && b.type == 'n'){
            Double sum = Double.parseDouble(this.value) + Double.parseDouble(b.value);
            return new Data(Double.toString(sum));
        }else if(this.type == 'c' && b.type == 'c'){
            String largestCharacter = (this.value.compareTo(b.value) > 0) ? this.value : b.value;
            return new Data(largestCharacter);
        }else if((this.type == 'b' && b.type == 'n') || (this.type == 'n' && b.type == 'b')){
            String newValue;
            if(b.type == 'n'){
                newValue = (Double.parseDouble(b.value) == 0) ? "TRUE" : "FALSE";
                return (this.value.equals("FALSE") && newValue.equals("FALSE")) ? new Data("FALSE") : new Data("TRUE");
            }else{
                newValue = (Double.parseDouble(this.value) == 0) ? "TRUE" : "FALSE";
                    return (newValue.equals("FALSE") && b.value.equals("FALSE")) ? new Data("FALSE") : new Data("TRUE");
            }
        }else{
            char newValue;
            if(b.type == 'b'){
                newValue = (b.value == "TRUE") ? 'T' : 'F';
                String largestCharacter = (this.value.compareTo("" + newValue) > 0) ? this.value : "" + newValue;
                return new Data(largestCharacter);
            }else{
                newValue = (this.value == "TRUE") ? 'T' : 'F';
                String largestCharacter = (("" + newValue).compareTo(b.value) > 0) ? "" + newValue : b.value;
                return new Data(largestCharacter);
            }
        }
    }
    
  
     /**
     * Substract the specified data with this data
     */   
    // boolean - boolean. - is AND
    // numerical - numerical. - is -
    // character - character. - is the smaller character
    // boolean - numerical. Transform the number to boolean. If 0, true. False otherwise.
    // boolean - character. Transform the boolean to character. TRUE is T and FALSE is F.
    // + is commutative
    public Data sub(Data b){
        if(this.type == 'b' && b.type == 'b'){
            return (this.value.equals("TRUE") && b.value.equals("TRUE")) ? new Data("TRUE") : new Data("FALSE");
        }else if(this.type == 'n' && b.type == 'n'){
            Double res = Double.parseDouble(this.value) - Double.parseDouble(b.value);
            BigDecimal resBigDecimal = new BigDecimal(res).setScale(1, RoundingMode.HALF_UP);
            double resFinal = resBigDecimal.doubleValue();
            return new Data(Double.toString(resFinal));
        }else if(this.type == 'c' && b.type == 'c'){
            String largestCharacter = (this.value.compareTo(b.value) < 0) ? this.value : b.value;
            return new Data(largestCharacter);
        }else if((this.type == 'b' && b.type == 'n') || (this.type == 'n' && b.type == 'b')){
            String newValue;
            if(b.type == 'n'){
                newValue = (Double.parseDouble(b.value) == 0) ? "TRUE" : "FALSE";
                return (this.value.equals("TRUE") && newValue.equals("TRUE")) ? new Data("TRUE") : new Data("FALSE");
            }else{
                newValue = (Double.parseDouble(this.value) == 0) ? "TRUE" : "FALSE";
                    return (newValue.equals("TRUE") && b.value.equals("TRUE")) ? new Data("TRUE") : new Data("FALSE");
            }
        }else{
            char newValue;
            if(b.type == 'b'){
                newValue = (b.value == "TRUE") ? 'T' : 'F';
                String largestCharacter = (this.value.compareTo("" + newValue) < 0) ? this.value : "" + newValue;
                return new Data(largestCharacter);
            }else{
                newValue = (this.value == "TRUE") ? 'T' : 'F';
                String largestCharacter = (("" + newValue).compareTo(b.value) < 0) ? "" + newValue : b.value;
                return new Data(largestCharacter);
            }
        }
    }
    
    /**
     * Returns the data type
     * @returns 'b', 'n', or 'c'
     */
    public char type(Data b){
        return b.type;
    }
    
    @Override
    //Return the string representation of the data, not the original string
    public String toString () {
          return this.value;
    }   
    
     /**
     * Return the string used to create the Data without leading or trailing blanks
     */   
    public String string() {
          return this.originalValue.trim();
    }  
    
    /**
     * Checks if the string is a boolean
     * @param   s   the string to check
     * @return  whether or not the string is a boolean
     */
    private boolean isBoolean(String s){
        s = s.toUpperCase();
        boolean result = false;
        if(s.equals("TRUE") || s.equals("FALSE")){
            result = true;
        }
        return result;
    }
    
    /**
     * Checks if the string is a number
     * @param   s   the string to check
     * @return  whether or not the string is a number
     */
    private boolean isNumeric(String s){
        s = s.trim();
        boolean result = false;
        try{
            Double.parseDouble(s);
            result = true;
        }catch(NumberFormatException exception){
            result = false;
        }
        return result;
    }
    
    /**
     * Checks if the string is a character
     * @param   s   the string to check
     * @return  whether or not the string is a character
     */
    private boolean isCharacter(String s){
        s = s.trim();
        boolean result = false;
        if(s.length() == 1){
            result = true;
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Data){
            Data objToCompare = (Data) obj;
            if(this.type == objToCompare.type && this.type != 'n'){
                return this.value.equalsIgnoreCase(objToCompare.value);
            }else if(this.type == objToCompare.type && this.type == 'n'){
                Double thisValue = Double.parseDouble(this.value);
                Double valueToCompare = Double.parseDouble(objToCompare.value);
                return Double.compare(thisValue, valueToCompare) == 0; //Si los dos numeros son iguales devuelve 0
            }else{
                return false;
            }
        }
        return false;
    }
}
