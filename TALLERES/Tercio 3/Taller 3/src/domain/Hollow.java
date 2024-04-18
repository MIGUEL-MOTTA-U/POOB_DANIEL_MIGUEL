package domain;
public class Hollow{
    private String color;
    private int row;
    private int column;
    public Hollow(String color, int x, int y){
        row = x;
        column = y;
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setColumn(int column) {
        this.column = column;
    }

}
