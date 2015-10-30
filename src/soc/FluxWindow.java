package soc;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class FluxWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea constantArea = new JTextArea(1,20);
	private JPanel constantPanel = new JPanel();
	private JButton set = new JButton("Set");
	//private JButton push = new JButton("Push");
	private Flux flux;
	private DrawPanel from;
	
	public FluxWindow(FluxGraphic fluxg, DrawPanel pfrom){
    	this.setTitle(fluxg.getFlux().getName());
    	this.setSize(500, 500);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.setVisible(true);

    	from = pfrom;
    	setFlux(fluxg.getFlux());
    	set.addActionListener(new SetListener());
 
    	
    	
    	GridLayout constantPaneLayout = new GridLayout(2,2);
    	constantPaneLayout.setHgap(5);
    	constantPaneLayout.setVgap(10);
    	constantPanel.setLayout(constantPaneLayout);
  	   	constantPanel.add(constantArea);
  	   	constantPanel.add(set);
  	   	//constantPanel.add(variableList);
  	    //constantPanel.add(push);
    	
    	this.getContentPane().add(constantPanel, BorderLayout.NORTH);
    	 	
    	    	
	}
	
	 class SetListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateFlux(constantArea.getText(), getFrom());
	
			}
	 }
	 
	 public void updateFlux(String s, DrawPanel from){
		 try {
			this.getFlux().setQuantity(this.getFlux().getInWorld().eval(s));
		} catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.getFlux().getInWorld().getInfos().updateText(this.getFlux().getInWorld());
		 from.repaint();
	 }
	 
	 public void setFlux(Flux flux){this.flux=flux;}
	 public Flux getFlux(){return this.flux;}
	 public DrawPanel getFrom(){return this.from;}



}
