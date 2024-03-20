package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public interface Thing{
  public static final int ROUND = 1;
  public static final int SQUARE = 2;
  public static final int FLOWER = 3;

   
  public void act();
  
  default public int shape(){
      return SQUARE;
  }
  
  default public Color getColor(){
      return Color.blue;
  };
  
  
  default public boolean is(){
      return true;
  }
  
}
