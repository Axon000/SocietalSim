package soc;
import java.awt.Graphics;


public class StateIndicator {


	private String state = new String("");
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;

	
	public void draw(Graphics g){
	
		
	}


	public String getState() {return state;}
	public void setState(String state) {this.state = state;}
	public int getPosX() {return posX;}
	public void setPosX(int posX) {this.posX = posX;}
	public int getPosY() {return posY;}
	public void setPosY(int posY) {this.posY = posY;}
	public int getSizeX() {return sizeX;}
	public void setSizeX(int sizeX) {this.sizeX = sizeX;}
	public int getSizeY() {return sizeY;}
	public void setSizeY(int sizeY) {this.sizeY = sizeY;}
	
}
