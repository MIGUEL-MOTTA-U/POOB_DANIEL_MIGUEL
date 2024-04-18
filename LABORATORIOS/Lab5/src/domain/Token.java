package domain;
public class Token {
    private String color;
    private int row;
    private int  column;
    private boolean inSite;
    public Token(String color, int x, int y){
        row = x;
        column = y;
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public boolean isInSite() {
        return inSite;
    }
    public void setInSite(boolean inSite) {
        this.inSite = inSite;
    }
}