package soc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;





public class BoxWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JButton newVar = new JButton("New parameter");
	private JButton apply = new JButton("Apply");
	//private JPanel newPanel = new JPanel();

	private Box box=new Box();
	private BoxGraphic boxg = new BoxGraphic();
	private JTextArea nameArea=new JTextArea(1,20);
	private JTextArea valueArea=new JTextArea(1,20);
	
	private JPanel container = new JPanel();
	private JPanel container2 = new JPanel();
	private JPanel container3 = new JPanel();
	private JPanel varPanel=new JPanel();
	private JPanel titlePanel=new JPanel();
	private JPanel applyPanel = new JPanel();
	private JPanel areaPanel = new JPanel();
	
	
	public BoxWindow(BoxGraphic bg){
    	this.setTitle(bg.getBox().getName());
    	this.setSize(500, 500);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.setVisible(true);

    	
    	

    	setBoxg(bg);
    	setBox(boxg.getBox());

    	apply.addActionListener(new ApplyListener());
    	
    	
    	BorderLayout containerLayout = new BorderLayout();
    	BorderLayout container3Layout = new BorderLayout();
    	BoxLayout container2Layout = new BoxLayout(container2, BoxLayout.PAGE_AXIS);
    	GridLayout titleLayout = new GridLayout(1,2);
    	BoxLayout varLayout = new BoxLayout(varPanel, BoxLayout.PAGE_AXIS);
    	FlowLayout applyLayout = new FlowLayout();
    	GridLayout areaLayout = new GridLayout(1,2);
    	
    	
    	
    	container.setLayout(containerLayout);
    	container2.setLayout(container2Layout);
    	container3.setLayout(container3Layout);
    	
    	titlePanel.setLayout(titleLayout);
    	titlePanel.add(new JLabel("Name"));
    	titlePanel.add(new JLabel("Value"));
    
    	
    	varPanel.setLayout(varLayout);
		varPanel.removeAll();
    	for (Var var : box.getVar()){
    		varPanel.add(new LigneResume(var.getName(), var.getValue(), this.getWidth())); 		
    	}
    	
    	applyPanel.setLayout(applyLayout);
    	applyPanel.add(apply);
    	
    	areaLayout.setHgap(5);
    	areaPanel.setLayout(areaLayout);
    	areaPanel.add(nameArea);
    	areaPanel.add(valueArea);
    	

    	container2.add(varPanel);
    	container2.add(areaPanel);
    	container3.add(container2, BorderLayout.NORTH);
    	
    	container.add(titlePanel, BorderLayout.NORTH);
    	container.add(container3, BorderLayout.CENTER);
    	container.add(applyPanel, BorderLayout.SOUTH);
    	this.getContentPane().add(container);
    	this.setVisible(true);
    	 	
	}
	
	
	 class ApplyListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateBox(getNameArea().getText(), Double.valueOf(getValueArea().getText()));
	
			}
	 }
	 
	
	 
	 public void updateBox(String pname, double pvalue){
		 getBox().getVar().addElement(new Var(pname, pvalue));
	 
		 varPanel.removeAll();
		 for (Var var : box.getVar()){
			varPanel.add(new LigneResume(var.getName(), var.getValue(), this.getWidth()));
		 }

		varPanel.repaint();
	   	this.setVisible(true);
		
		 

	 } 
	 
	 public Box getBox(){return this.box;}
	 public void setBox(Box pbox){this.box=pbox;}
	 public BoxGraphic getBoxg(){return this.boxg;}
	 public void setBoxg(BoxGraphic pboxg){this.boxg=pboxg;}
	 public JTextArea getNameArea(){return this.nameArea;}
	 public JTextArea getValueArea(){return this.valueArea;}



}
