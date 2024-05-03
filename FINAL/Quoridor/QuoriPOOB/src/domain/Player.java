package domain;
import java.awt.Color;
public class Player {
	private String name;
	private Color color;
	private Board board;
	public Player(String name, Color color) {
		this.color = color;
		this.name = name;
	}
	
	public void setBoard(Board b) {
		this.board = b;
	}
}
