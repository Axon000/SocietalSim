package soc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private JPanel container2=new JPanel();

	
	public LigneResume(String pname, double pvalue, int size){
		
		this.name=pname;
		this.value=pvalue;
		this.width=size;
		
		container.setLayout(new GridLayout(1,2));
		container2.setLayout(new BorderLayout());
		
		try {
	    	    
	  		BufferedImage img= ImageIO.read(new FileInputStream("resources/rm.bmp"));  
	  		rm.setIcon(new ImageIcon(img));
	    	} catch (IOException ex) {
	    	}
	
	rm.addActionListener(new RMListener());
	


	nameLabel.setText(name);
	valueLabel.setText(new Double(value).toString());


	container.setMinimumSize(new Dimension(width,10));
	container2.add(valueLabel, BorderLayout.CENTER);
	container2.add(rm, BorderLayout.EAST);
	
	container.add(nameLabel);
	container.add(container2);
	this.add(container);
	}
	
	class RMListener implements ActionListener {
		
	 	@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.print(name);

		}
	}
	
	
}
