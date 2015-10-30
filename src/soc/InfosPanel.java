package soc;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfosPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea infosText = new JTextArea(60,15);
	
		
	public void updateText(World world){

		String s = new String();
		for (Box box : world.getBoxList()){
    		s = s + "\n\n"+box.description();
    	}
	    infosText.setText(s);
	}
	

	
	public JTextArea getInfosText(){
		return this.infosText;
	}

}
