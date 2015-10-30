package soc;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LigneResume extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double value;
	private JButton rm = new JButton();
	private int width;
	private JLabel nameLabel=new JLabel();
	private JLabel valueLabel=new JLabel();
	private JPanel container =new JPanel();
	private JPanel label1 = new JPanel();
	private JPanel label2 = new JPanel();
	private JPanel button = new JPanel();

	private Var vr;
	private BoxWindow bxw;

	
	public LigneResume(Var vr, BoxWindow bxw){
		
		this.vr=vr;
		this.name=vr.getName();
		this.value=vr.getValue();
		this.width=bxw.getWidth();
		this.bxw=bxw;



		

		BoxLayout containerLayout = new BoxLayout(container, BoxLayout.LINE_AXIS);


		
		
		container.setLayout(containerLayout);
		//container2.setLayout(new BorderLayout());
		
		try {
	    	    
	  		BufferedImage img= ImageIO.read(new FileInputStream("resources/rm.bmp"));  
	  		rm.setIcon(new ImageIcon(img));
	    	} catch (IOException ex) {
	    	}
	
	rm.addActionListener(new RMListener());
	


	nameLabel.setText(name);
	valueLabel.setText(new Double(value).toString());

	
	label1.setLayout(new FlowLayout(FlowLayout.LEFT));
	label2.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	label1.add(nameLabel);
	label2.add(valueLabel);
	button.add(rm);
	//
	label1.setPreferredSize(new Dimension((int)(width/2)+30, 40));
	label2.setPreferredSize(new Dimension((int)(width/2)-100, 40));
	button.setPreferredSize(new Dimension(100, 40));

	//container.setMinimumSize(new Dimension(1000,10));
	container.setBackground(Color.RED);
	container.add(label1);
	container.add(label2);
	container.add(button);
	

	//container.add(container2);
	this.add(container);
	}
	
	class RMListener implements ActionListener {
		
	 	@Override
		public void actionPerformed(ActionEvent arg0) {
	 		bxw.removeVar(vr);
		}
	}
	
	
}
