package soc;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseMotionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<BoxGraphic> boxgList = new Vector<BoxGraphic>();
	private Vector<FluxGraphic> fluxgList = new Vector<FluxGraphic>();
	private Boolean inProcedure1;
	private Boolean inProcedure2;
	private int inProcedureFluxIndex;
	private int currentBoxgIndex = -1;
	private int currentFluxgIndex = -1;
	private BoxGraphic[] boxgProcedureTab= new BoxGraphic[2];
	private World world = new World();

	
	
	public DrawPanel(){
		
		inProcedure1 = false;
		inProcedure2 = false;

		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
		        
				int x = evt.getX();
		        int y = evt.getY();
		        currentBoxgIndex = getBox(x, y);
		        currentFluxgIndex = getFlux(x, y);
		      
			}
			
		      public void mouseClicked(MouseEvent evt) {

		          
		          if (currentBoxgIndex >=0 && inProcedure1 && !inProcedure2){
		        	 
		        	  	        	  
		        	  boxgProcedureTab[0]=boxgList.elementAt(currentBoxgIndex);
		        	  changeFrameColor(boxgProcedureTab[0]);
		        	  
		        	  inProcedure2= true;
		        	  
		          }
		          
		          else if (currentBoxgIndex >= 0 && inProcedure2){
			      	
		      
		        	  inProcedure1 = false;
		        	  boxgProcedureTab[1]=boxgList.elementAt(currentBoxgIndex);
		        	  changeFrameColor(boxgProcedureTab[0]);
		        	  addFlux(fluxgList.elementAt(inProcedureFluxIndex),  boxgProcedureTab[0], boxgProcedureTab[1]);
		        	  inProcedure2 = false;
		          }
		        	         
		          else if (evt.getClickCount() >= 2 && currentBoxgIndex >= 0) {
		        	  new BoxWindow(boxgList.elementAt(currentBoxgIndex));
		          }
		          
		          else if (evt.getClickCount() >= 2 && currentFluxgIndex >= 0) {
		           	  new FluxWindow(fluxgList.elementAt(currentFluxgIndex), getThisDrawPanel());
		          }
		          
		
		      }
		});
		
		    addMouseMotionListener(this);
	}
	
	//-----------------------------------------------------------------------------------------------
	
	@Override
    protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
        for (BoxGraphic boxg : boxgList) {
            boxg.draw(g, boxg.getBox().getName());
        }
        for (FluxGraphic fluxg : fluxgList) {
        	fluxg.draw(g);
        }
    }
	

	//-----------------------------------------------------------------------------------------------
	
	public void addBoxg(BoxGraphic boxg) {
	    boxgList.add(boxg);
	    repaint();
	}
	
	public void rmBoxgAt(int index){
		boxgList.removeElementAt(index);
		repaint();
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public void linkProcedure(FluxGraphic fluxg){
			
		fluxgList.add(fluxg);
		inProcedureFluxIndex= fluxgList.size()-1;
		inProcedure1 = true;
	}
	
	public void addFlux(FluxGraphic fluxg, BoxGraphic box1, BoxGraphic box2){
		
  		fluxg.setPosXFrom(box1.getPosX());
		fluxg.setPosYFrom(box1.getPosY());
		fluxg.setPosXTo(box2.getPosX());
		fluxg.setPosYTo(box2.getPosY());
		fluxg.setSizeXBoxFrom(box1.getSizeX());
		fluxg.setSizeXBoxTo(box2.getSizeX());
		fluxg.setSizeYBoxFrom(box1.getSizeY());
		fluxg.setSizeYBoxTo(box2.getSizeY());
		boxgProcedureTab[0].getOutcomingFluxgList().add(fluxg);
		boxgProcedureTab[1].getIncomingFluxgList().add(fluxg);
		boxgProcedureTab[0].setHasOutcomingFlux(true);
		boxgProcedureTab[1].setHasIncomingFlux(true);
		this.world.updateEngine(box1, box2);
		
		
		repaint();
		


	}
	
	public void changeFrameColor(BoxGraphic boxg){
		if (inProcedure1){
			boxg.setFrameColor(Color.RED);
			repaint();
		}
		else {
			boxg.setFrameColor(Color.BLACK);
			repaint();
		}
	}

	
	//-----------------------------------------------------------------------------------------------
	
	
	
	  public int getBox(int x, int y) {
		  for (int i = 0; i < boxgList.size(); i++)
			  if(boxgList.elementAt(i).contains(x,y))
				  return i;
		  return -1;
	  }
	  
	  public int getFlux(int x, int y) {
		  for (int i = 0; i < fluxgList.size(); i++)
			  if(fluxgList.elementAt(i).contains(x,y))
				  return i;
		  return -1;
	  }

	@Override
	public void mouseDragged(MouseEvent evt) {
			    	    
	    if (currentBoxgIndex >= 0) {
	    	int x = Math.min(Math.max(evt.getX(), 0), this.getWidth())-(int) (boxgList.elementAt(currentBoxgIndex).getSizeX()/2);
		    int y = Math.min(Math.max(evt.getY(), 0), this.getHeight())-(int) (boxgList.elementAt(currentBoxgIndex).getSizeY()/2);
   
	    	boxgList.elementAt(currentBoxgIndex).setPosX(x);
	    	boxgList.elementAt(currentBoxgIndex).setPosY(y);
	    	if (boxgList.elementAt(currentBoxgIndex).hasIncomingFlux()){
	    		for (FluxGraphic incomingFluxg : boxgList.elementAt(currentBoxgIndex).getIncomingFluxgList()){
	    			incomingFluxg.setPosXTo(x);
	    			incomingFluxg.setPosYTo(y);
	    		}
	    	}
	    	
	    	if (boxgList.elementAt(currentBoxgIndex).hasOutcomingFlux()){
	    		for (FluxGraphic outcomingFluxg : boxgList.elementAt(currentBoxgIndex).getOutcomingFluxgList()){
	    			outcomingFluxg.setPosXFrom(x);
	    			outcomingFluxg.setPosYFrom(y);
	    		}
	    	}
	     	repaint();
	    	
	    }
		
	}

	@Override
	public void mouseMoved(MouseEvent evt) {

		 int x = evt.getX();
		 int y = evt.getY();
		
		 

		 if (getBox(x, y) >= 0)
			 setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		 else
			 setCursor(Cursor.getDefaultCursor());
		 
		 if (inProcedure1 && inProcedure2){
			 int xf = boxgProcedureTab[0].getPosX()+(int) (boxgProcedureTab[0].getSizeX()/2);
			 int yf = boxgProcedureTab[0].getPosY()+(int) (boxgProcedureTab[0].getSizeY()/2);
			 fluxgList.elementAt(inProcedureFluxIndex).setPosXTo(x);
			 fluxgList.elementAt(inProcedureFluxIndex).setPosYTo(y);
			 fluxgList.elementAt(inProcedureFluxIndex).setPosXFrom(xf);
			 fluxgList.elementAt(inProcedureFluxIndex).setPosYFrom(yf);
			 repaint();
			 
		 }
		
	}
	
	public void setWorld(World pworld){this.world = pworld;}
	public World getWorld(){return this.world;}
	public DrawPanel getThisDrawPanel(){return this;}
	

}
