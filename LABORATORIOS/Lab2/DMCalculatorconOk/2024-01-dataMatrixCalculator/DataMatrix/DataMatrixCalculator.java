import java.util.HashMap;
import java.util.Map;

/** Data Matrix Calculator
 * 
 * @author CIS 2024-01
 */
    
public class DataMatrixCalculator{
    
    private HashMap<String, DataMatrix> variables;
    private boolean status;
    
    public DataMatrixCalculator(){
        variables = new HashMap<>();
        status = true;
    }


    /**
     * Assign a matrix to a variable
     * @param name of the matrix
     * @param values to assign to the Matrix
     */
    public void assign(String name, String values[][] ){
        this.variables.put(name, new DataMatrix(values));
    }
    
    //Consult the variables of a calculator
    /**
     * This Method consults all the Matrices in the calculator and return an array of their variables.
     * return variables, an array with all the variables of the calculator (all the matrices)
     */
    public String[] variables(){
        String[] output = new String[variables.size()];
        int index = 0;
        for (Map.Entry<String, DataMatrix> element : variables.entrySet()){
            DataMatrix value = element.getValue();
            output[index] = value.toString();
            index +=1;
        }
        return output; 
    }   
       
  
    //Assigns the value of an operation to a variable (unary operations)
    // a := unary b
    //The unary operators are: s (hape), r (eshape)
    // shape returns a (1x2) matrix with the shape (rows, columns)
    // In reshape b is a (1x2) matrix with the reshape (rows, columns)
    //If this is not possible, it returns the (1x1) matrix with a false value
    /**
     * This method assigns a value to matrix a, according to the unary operation, it will assign the Transpose Matrix, or the shape.
     * @param a, the Matrix A where it saves the answer.
     * @param unary, the operation, perhaps s(hape) or r(eshape)
     * @param b, the Matrix in The array on which the operation is performed.
     */
    public void assign(String a, char unary, String b){ // Supongo que a es la matriz, 'unary' no se y 'b' no se
        try{
            DataMatrix aMatrix = this.variables.get(a);
            DataMatrix bMatrix = this.variables.get(b);
            if (unary == 's'){
                aMatrix = bMatrix.shape();
            } else {
                aMatrix = bMatrix.reshape();
            }
            this.variables.replace(a, aMatrix);
            this.status = true;
        } catch (NullPointerException e){
            this.status = false;
        } catch (ArrayIndexOutOfBoundsException e){
            this.status = false;
        }
    }
    
    /**
     * Consult the value of a variable of a given Matrix
     * @param name, the name of the Matrix
     * @param row, the position of the Matrix row
     * @param column, the position of the Matrix column
     * returns the String of the variable if its found, "Not found" otherwise.
     */
    public String consult(String name, int row, int column){
        String res = "Not Found";
        DataMatrix matrix = this.variables.get(name);
        try{
            if (matrix != null){
                res = matrix.toString(row, column);
                this.status = true;
            }
        }   catch (NullPointerException e){
            this.status = false;
        } catch (ArrayIndexOutOfBoundsException e){
            this.status = false;
        }
        return res;
    }
    //Assigns the value of an operation to a variable (simple binary operations)
    // a := b simple c
    //The simple operators are: +, -
     //If this is not possible, it returns the (1x1) matrix with a false value
     
    /**
     * This method gets two Matrices b, c and according to an operation 'sBinary' it saves the result in the matrix a.
     * @param a, the Matrix A
     * @param b, the Matrix B
     * @param c, the Matrix C
     * @param sBinary, the operation: '+' or '-'
     */
    public void assign(String a, String b, char sBinary, String c){
        try{
            DataMatrix bMatrix = this.variables.get(b);
            DataMatrix cMatrix = this.variables.get(c);
            DataMatrix aMatrix = this.variables.get(a);
            DataMatrix result = null;
            if (sBinary == '+'){
                aMatrix = bMatrix.add(cMatrix);
            } else {
                aMatrix = bMatrix.sub(cMatrix);
            }
            this.variables.replace(a, aMatrix);
            this.status = true;
        } catch (NullPointerException e){
            this.status = false;
        } catch (ArrayIndexOutOfBoundsException e){
            this.status = false;
        }
    }
    

    
    //Returns the string represention of a matrix. Columns must be aligned.
    /**
     * This method takes the given name of a Matrix from the collection of matrices and returns it as an String.
     * @param variables, is the name of the Matrix
     * returns the Matrix as a String
     */
    public String toString(String variable){
        try{
            DataMatrix result = this.variables.get(variable);
            this.status = true;
            return result.toString();
        } catch (NullPointerException e){
            this.status = false;
            return "FALSE";
        }
    }
    
    //If the last operation was successful
    public boolean ok(){
        return this.status;
    }
}
    



