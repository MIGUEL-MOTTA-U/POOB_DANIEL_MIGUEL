import javax.swing.JOptionPane;

/**
 * @author ECI, 2024
 *
 */
public class DataMatrix{

    private Data [][] data; // Arreglo de data
    
    /**
     * Creates a matrix with the defined size and value
     * @param data, It's an array of String that becomes an array of Data.
     */
    public DataMatrix(String[][] data) { // It was in the format DataMatrix, but we would have return it in terms of int []
        this.data = new Data[data.length][]; // Create the array by the given dimensions.
        for (int i = 0; i < data.length; i++) {
            this.data[i] = new Data[data[i].length]; // Initialize each row with its length.
            for (int j = 0; j < data[i].length; j++) {
                Data d = new Data(data[i][j]);
                this.data[i][j] = d;
                }
            }
        }

    /**
     * This method returns the shape of the matrix as DataMatrix
     * returns a DataMatrix with two values, the row and the column of the Matrix, in other words, the shape of the Matrix.
     */
    public DataMatrix shape() {
        int rows = this.data.length;
        int columns = this.data[0].length;
        String[][] shapeData = new String[][] { { String.valueOf(rows), String.valueOf(columns) } };
        return new DataMatrix(shapeData);
    }

    
    
    /**
     * This Method takes two ints that represents the position in the Matrix of a value and returns it as a String
     * @param row is the int that represents the row position in the matrix
     * @param column is the int that represents the column position in the matrix
     * returns the value 
     */
    public String toString(int row, int column){
        Data value = this.data[row][column];
        return value.toString();
    }
    
    
    /**
     * Reshapes the matrix to the specified dimensions.
     * This method returns the Transpose
     * @return a new DataMatrix instance with the reshaped matrix
     */
    public DataMatrix reshape() {
        Data[][] newData = new Data[this.getColumns()][this.getRows()];
        for(int i = 0; i< this.getColumns(); i++){
            for (int j = 0; j< this.getRows(); j++){
                newData[i][j] = this.getData()[j][i];
            }
        }
        return new DataMatrix(toArrayString(newData));
    }
    
    
    /**
     * Matrix A - Matrix B
     * Ths Method return a Matrix product of substracion by two given Matrices, the self matrix and other matrix given.
     * @param t is the other Matrix (It got to be the same size)
     * returns the resultant DataMatrix of the substracion of the given Matrices    
     */
    public DataMatrix sub(DataMatrix t){
        DataMatrix result = null;
        if (this.getRows() == t.getRows() && this.getColumns() == t.getColumns()){
            Data [][] output = new Data[t.getRows()][];
            for (int i = 0; i< t.getRows(); i++){
                output[i] = new Data[t.getColumns()];
                for (int j = 0; j< t.getColumns(); j++){
                    output[i][j] = this.data[i][j].sub(t.getData()[i][j]);
                }
            }
            result = new DataMatrix(toArrayString(output));
        }
        return result;
    }
    
    
    /**
     * Matrix A + Matrix B
     * Ths Method return a Matrix product of sum by two given Matrices, the self matrix and other matrix given.
     * @param t is the other Matrix (It got to be the same size)
     * returns the resultant DataMatrix of the sum of the given Matrices    
     */
    public DataMatrix add(DataMatrix t){
        DataMatrix result = null;
        if (this.getRows() == t.getRows() && this.getColumns() == t.getColumns()){
            Data [][] output = new Data[t.getRows()][];
            for (int i = 0; i< t.getRows(); i++){
                output[i] = new Data[t.getColumns()];
                for (int j = 0; j< t.getColumns(); j++){
                    output[i][j] = this.data[i][j].add(t.getData()[i][j]);
                }
            }
            result = new DataMatrix(toArrayString(output));
        }
        return result;
    }


    /**
     * Compares two matrices to determine if they are equal.
     * This method compares their size, and every value of them.
     * @param other the matrix to compare
     * @return true if the matrices are equal in terms of dimensions and elements, false otherwise
     */
    public boolean equals(DataMatrix other) {
        boolean res = true;
        if (this.getRows() == other.getRows() && this.getColumns() == other.getColumns()) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (!this.getData()[i][j].equals(other.getData()[i][j])) {
                    res = false;
                    }
                }
            } 
        } else {
            res = false;
        }
        return res;
    }
    
    /**
     * Assign a given value to an specific position in the Matrix.
     * @param value, It got to be String and is the value that is set to be in the position given.
     * @param row, It is the row position
     * @param column It is the column position
     */
    public void assign(String value, int row, int column){
        Data V = new Data(value);
        if (row < this.getRows() && column < this.getColumns()){
         this.getData()[row][column] = V;    
        }
    }
    
    /**
     * This methos is made to print a value of the matrix by it's given positions (row,column)
     * @param row is the row where is the objective.
     * @param column is the column where is the objective.
     */
    public void print(int row, int column){
        if (row < this.getRows() && column < this.getColumns()){
            Data output = this.getData()[row][column];
            System.out.println(output.toString());
        }
    }
    
    
    @Override
    public boolean equals(Object other) {
            return equals((DataMatrix)other);
    }
    
    @Override
    public String toString () {
        String output = "[";
        for (int i = 0; i < data.length; i++) {
            output += "[";
            for (int j = 0; j < data[i].length; j++) {
                if(j == data[i].length - 1){
                    output += toString(i,j);
                }else{
                    output += toString(i,j) + ", ";
                }
                }
            if(i == data.length - 1){
                output+="]";
            }else{
                output+="], ";
            }
            }
            output += "]";
        return output;
        }
    
    /*
     * This method returns the number of rows in the Matrix
     * returns the number of rows (int)
     */
    private int getRows(){
        return this.data.length;
    }
    
    /*
     * This method returns the number of columns in the Matrix
     * returns the number of columns (int)
     */
    private int getColumns(){
        return this.data[0].length;
    }
    
    /*
     * This method returns the Array of Data [][] of the Object Matrix
     * retuns Data [][] Array type data.
     */
    private Data[][] getData(){
        return this.data;
    }
    
    /*
     * This private method gets a Matrix of Data n*m and returns a Matrix n*m of String
     * @param dataArray is the array of type Data
     * returns an Array of String
     */
    private String[][] toArrayString(Data[][] dataArray){
        String [][] output = new String[dataArray.length][];
        for (int i = 0; i < dataArray.length; i++) {
            output[i] = new String[dataArray[i].length];
            for (int j = 0; j < dataArray[i].length; j++) {
                output[i][j] = dataArray[i][j].toString();
                }
            }
        return output;
        }
    }   

