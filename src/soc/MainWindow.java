package soc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

	 
public class MainWindow extends JFrame {
	    
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private World world = new World();
		private JButton newBox = new JButton("New Box");
    	private JButton newLink = new JButton("New Link");
    	private JButton newCompany = new JButton("New Company");
    	private JButton newGroup = new JButton("New Group");
    	private JButton newState = new JButton("New State");
    	private DrawPanel drawPane = new DrawPanel();
    	private JPanel margin = new JPanel();
    	private JPanel buttonPane = new JPanel();
    	private int nbrBoxg;
    	
	
	    public MainWindow(){
	    	this.setTitle("Societal Sim");
	    	this.setSize(1000, 1000);
	    	this.setLocationRelativeTo(null);
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.setVisible(true);
  	
  	
	    	newBox.addActionListener(new BoxListener());
	    	newLink.addActionListener(new FluxListener());
	    	newCompany.addActionListener(new CompanyListener());
	    	newGroup.addActionListener(new GroupListener());
	    	newState.addActionListener(new StateListener());
	    
	    	drawPane.setBackground(Color.WHITE);
	    	drawPane.setSize(800,800);
	    	
	    	drawPane.setWorld(world);
	    		    	
	    	GridLayout buttonPaneLayout = new GridLayout(5,1);
	    	
	    	buttonPane.setLayout(buttonPaneLayout);
	    	buttonPane.add(newBox);
	    	buttonPane.add(newCompany);
	    	buttonPane.add(newGroup);
	    	buttonPane.add(newState);
	    	buttonPane.add(newLink);
	    	
	       	this.world.getInfos().getInfosText().setEditable(false);
	    	JScrollPane scroll = new JScrollPane(this.world.getInfos().getInfosText());
	    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    	
	    	this.world.getInfos().add(scroll);
	        this.getContentPane().add(margin, BorderLayout.EAST);
	        this.getContentPane().add(drawPane, BorderLayout.CENTER);
	        this.getContentPane().add(this.world.getInfos(), BorderLayout.WEST);
	        margin.add(buttonPane, BorderLayout.NORTH);
	       
	        this.setVisible(true);
	          	    	
	    }
	    
	    //-----------------------------------------------------------------------------------------------
	    
	    
	    class BoxListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				BoxGraphic boxg = new BoxGraphic();
				world.createBox(boxg);
				

				boxg.setColor(Color.RED);
				boxg.setType("Box");
				int sX = 60;
		    	int sY = 40;
		    	
				drawBox(boxg, sX, sY);
			}

	    }
	    
	    class CompanyListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				BoxGraphic boxg = new BoxGraphic();
				world.createCompany(boxg);
				
				boxg.setColor(Color.BLUE);
				boxg.setType("Company");
				int sX = 80;
		    	int sY = 40;
		    	
		    	
				drawBox(boxg, sX, sY);
			}

	    }
	    
	    class GroupListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				

				BoxGraphic boxg = new BoxGraphic();
				world.createGroup(boxg);
		    	
				
				boxg.setColor(Color.ORANGE);
				boxg.setType("Group");
				int sX = 60;
		    	int sY = 40;
		    	
		    	
				drawBox(boxg, sX, sY);
			}

	    }
	    
	    class StateListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				


				BoxGraphic boxg = new BoxGraphic();
				world.createState(boxg);
				
				boxg.setColor(Color.DARK_GRAY);
				boxg.setType("State");
				int sX = 60;
		    	int sY = 40;
		    	
		    	
				drawBox(boxg, sX, sY);
			}

	    }
	    
	    //-----------------------------------------------------------------------------------------------
	    
	    public void drawBox(BoxGraphic boxg, int sX, int sY){
			
	    	this.setNbrBoxg(this.getNbrBoxg()+1);

	       
			boxg.setPosX( sX+(int)(sX*1.5)*this.getNbrBoxg() % (this.drawPane.getWidth()-100));
			boxg.setPosY( sY+(int)(sY*1.5)* (int) ((sX+(sX*1.5)*this.getNbrBoxg()) /  (this.drawPane.getWidth()-100)));
			boxg.setSizeX(sX);
			boxg.setSizeY(sY);
			boxg.setFrameColor(Color.BLACK);

			
	   		drawPane.addBoxg(boxg);
	   		
	    }
	    
	    //-----------------------------------------------------------------------------------------------
	    
	    class FluxListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				

				FluxGraphic fluxg = new FluxGraphic();
				world.createFlux(fluxg);
		    	fluxg.setColor(Color.GREEN);
	       	
		    	launchLinkProcedure(fluxg);
				
				
			}

	    }
	    
	  //-----------------------------------------------------------------------------------------------
	    
	    public void launchLinkProcedure(FluxGraphic fluxg){
	    	
	     	drawPane.linkProcedure(fluxg);
    	
	    }
	    
	    
	    public void setNbrBoxg(int nbr){this.nbrBoxg = nbr;}
	    public int getNbrBoxg() {return this.nbrBoxg;}
	    

}
