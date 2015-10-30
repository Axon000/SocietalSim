package soc;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class FluxGraphic {

	private Color color = new Color(0);
	private int posXFrom;
	private int posYFrom;
	private int posXTo;
	private int posYTo;
	private int sizeXBoxFrom;
	private int sizeXBoxTo;
	private int sizeYBoxFrom;
	private int sizeYBoxTo;
	private final int ARR_SIZE = 6;
	private Flux flux;

	
	public void draw(Graphics g1){
		
		Graphics2D g = (Graphics2D) g1.create();
		
		int x2 = posXTo  + (int) (0.5*sizeXBoxTo);
		int x1 = posXFrom   + (int) (0.5*sizeXBoxFrom);
		int y2 = posYTo  + (int) (0.5*sizeYBoxTo);
		int y1 = posYFrom  + (int) (0.5*sizeYBoxFrom);
		

	    
	
		g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        
		g.setColor(Color.BLACK);
		int fontSize = 14;
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
	    g.drawString(this.getFlux().getName(), (int) ((x2+x1)/2),(int) ((y2+y1)/2));
	    g.drawString(String.valueOf(this.getFlux().getQuantity()), (int) ((x2+x1)/2),(int) ((y2+y1)/2)+fontSize);
	    
		g.setColor(color);
       
                
		double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
  
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len}, new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
       
				
	}
	

	public boolean contains(int X, int Y){
		int tolerance=10;
		int x2 = posXTo  + (int) (0.5*sizeXBoxTo);
		int x1 = posXFrom   + (int) (0.5*sizeXBoxFrom);
		int y2 = posYTo  + (int) (0.5*sizeYBoxTo);
		int y1 = posYFrom  + (int) (0.5*sizeYBoxFrom);
		if(x2-x1!=0){
			return ((Y+tolerance-y1>(y2-y1)*(X-x1)/(x2-x1))&&(Y-tolerance-y1<(y2-y1)*(X-x1)/(x2-x1)));
		}
		else{
			return false;
		}
	}


	public int getPosXTo() {return posXTo;}
	public void setPosXTo(int posXTo) {this.posXTo = posXTo;}
	public int getPosXFrom() {return posXFrom;}
	public void setPosXFrom(int posXFrom) {this.posXFrom = posXFrom;}
	public int getPosYFrom() {return posYFrom;}
	public void setPosYFrom(int posYFrom) {this.posYFrom = posYFrom;}
	public int getPosYTo() {return posYTo;}
	public void setPosYTo(int posYTo) {this.posYTo = posYTo;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public int getSizeXBoxFrom() {return sizeXBoxFrom;}
	public void setSizeXBoxFrom(int sizeXBoxFrom) {this.sizeXBoxFrom = sizeXBoxFrom;}
	public int getSizeXBoxTo() {return sizeXBoxTo;}
	public void setSizeXBoxTo(int sizeXBoxTo) {this.sizeXBoxTo = sizeXBoxTo;}
	public int getSizeYBoxFrom() {return sizeYBoxFrom;}
	public void setSizeYBoxFrom(int sizeYBoxFrom) {this.sizeYBoxFrom = sizeYBoxFrom;}
	public int getSizeYBoxTo() {return sizeYBoxTo;}
	public void setSizeYBoxTo(int sizeYBoxTo) {this.sizeYBoxTo = sizeYBoxTo;}
	public Flux getFlux() {return flux;}
	public void setFlux(Flux flux) {this.flux = flux;}
	
	

}
