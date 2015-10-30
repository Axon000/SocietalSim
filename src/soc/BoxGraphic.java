package soc;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class BoxGraphic {
	
	private Color color = new Color(0);
	private Color frameColor = new Color(0);
	private String type = new String("Box");
	
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private boolean hasIncomingFlux;
	private boolean hasOutcomingFlux;
	private Vector<FluxGraphic> incomingFluxgList = new Vector<FluxGraphic>();
	private Vector<FluxGraphic> outcomingFluxgList = new Vector<FluxGraphic>();
	private Box box;
	
	public void draw(Graphics g, String label){
		
		
		g.setColor(frameColor);
		g.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
		g.setColor(color);
	    g.fillRect(this.getPosX()+3, this.getPosY()+3, this.getSizeX()-6, this.getSizeY()-6);
		g.setColor(Color.WHITE);
		g.drawString(label, this.getPosX()+5, this.getPosY()+(int) this.getSizeY()/2 );
		

		
	}
	

	
	public boolean contains(int X, int Y){
		return ((X - this.getPosX()) < this.getSizeX() && (Y - this.getPosY()) < this.getSizeY() && (X - this.getPosX())>0 && (Y - this.getPosY())>0 );
	}

	public int getPosY() {return posY;}
	public void setPosY(int posY) {this.posY = posY;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public Color getFrameColor() {return frameColor;}
	public void setFrameColor(Color color) {this.frameColor = color;}
	public int getPosX() {return posX;}
	public void setPosX(int posX) {this.posX = posX;}
	public int getSizeX() {return sizeX;}
	public void setSizeX(int sizeX) {this.sizeX = sizeX;}
	public int getSizeY() {return sizeY;}
	public void setSizeY(int sizeY) {this.sizeY = sizeY;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public boolean hasIncomingFlux() {return this.hasIncomingFlux;}
	public boolean hasOutcomingFlux() {return this.hasOutcomingFlux;}
	public void setHasIncomingFlux(Boolean set) {this.hasIncomingFlux=set;}
	public void setHasOutcomingFlux(Boolean set) {this.hasOutcomingFlux=set;}
	public Vector<FluxGraphic> getIncomingFluxgList() {return incomingFluxgList;}
	public Vector<FluxGraphic> getOutcomingFluxgList() {return outcomingFluxgList;}
	public Box getBox() {return box;}
	public void setBox(Box box) {this.box = box;}
	

	
}
