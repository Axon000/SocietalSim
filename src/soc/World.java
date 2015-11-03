package soc;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class World {
	
	private int fluxNbr;
	private int boxNbr;
	private Vector<Box> boxList= new Vector<Box>();
	private Vector<Flux> fluxList= new Vector<Flux>();
	private String infosText = new String();
	private InfosPanel infos = new InfosPanel();
	
	public World(){
		fluxNbr = 0;
		boxNbr =0;
	}
	
	
	public int getFluxNbr() {return fluxNbr;}
	public void setFluxNbr(int fluxNbr) {this.fluxNbr = fluxNbr;}
	public int getBoxNbr() {return boxNbr;}
	public void setBoxNbr(int boxNbr) {this.boxNbr = boxNbr;}
	public Vector<Box> getBoxList() {return boxList;}
	public String getInfosText() {return infosText;}
	public void setInfosText(String infosText) {this.infosText = infosText;}
	public InfosPanel getInfos() {return infos;}
	public void setInfos(InfosPanel infos) {this.infos = infos;}
	
	public void createBox(BoxGraphic boxg){
		boxList.add(new Box("box"+(boxNbr+1), this, boxg));
		boxg.setBox(boxList.lastElement());
		infos.updateText(this);

	}
	
	public void createCompany(BoxGraphic boxg){
		boxList.add(new Box("Company"+(boxNbr+1), this, boxg));
		boxg.setBox(boxList.lastElement());
		infos.updateText(this);

	}
	
	public void createGroup(BoxGraphic boxg){
		boxList.add(new Box("Group"+(boxNbr+1), this, boxg));
		boxg.setBox(boxList.lastElement());
		infos.updateText(this);

	}
	
	public void createState(BoxGraphic boxg){
		boxList.add(new Box("State"+(boxNbr+1), this, boxg));
		boxg.setBox(boxList.lastElement());
		infos.updateText(this);

	}
	
	public void createFlux(FluxGraphic fluxg){
		fluxList.add(new Flux("flux"+(fluxNbr+1), this, fluxg));
		fluxg.setFlux(fluxList.lastElement());
	
	}
	
	//-----------------------------------------------------------------------------------------------
	 
	public void updateEngine(BoxGraphic box1, BoxGraphic box2){
  		
		fluxList.lastElement().linkFrom(box1.getBox());
		fluxList.lastElement().linkTo(box2.getBox());
		infos.updateText(this);
	}

   
    
	//-----------------------------------------------------------------------------------------------
	
	public double eval(final String str) throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    class Parser {
	        int pos = -1, c;

	        void eatChar() {
	            c = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        void eatSpace() {
	            while (Character.isWhitespace(c)) eatChar();
	        }

	        double parse() throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	            eatChar();
	            double v = parseExpression();
	            if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
	            return v;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor | term brackets
	        // factor = brackets | number | factor `^` factor
	        // brackets = `(` expression `)`

	        double parseExpression() throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	            double v = parseTerm();
	            for (;;) {
	                eatSpace();
	                if (c == '+') { // addition
	                    eatChar();
	                    v += parseTerm();
	                } else if (c == '-') { // subtraction
	                    eatChar();
	                    v -= parseTerm();
	                } else {
	                    return v;
	                }
	            }
	        }

	        double parseTerm() throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	            double v = parseFactor();
	            for (;;) {
	                eatSpace();
	                if (c == '/') { // division
	                    eatChar();
	                    v /= parseFactor();
	                } else if (c == '*' || c == '(') { // multiplication
	                    if (c == '*') eatChar();
	                    v *= parseFactor();
	                } else {
	                    return v;
	                }
	            }
	        }

	        double parseFactor() throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	            double v;
	            boolean negate = false;
	            eatSpace();
	            if (c == '+' || c == '-') { // unary plus & minus
	                negate = c == '-';
	                eatChar();
	                eatSpace();
	            }
	            if (c == '(') { // brackets
	                eatChar();
	                v = parseExpression();
	                if (c == ')') eatChar();
	            } 
	            
	            else if (c=='[') {
	            	eatChar();
	            	StringBuilder sb = new StringBuilder();
	                while (c != ']') {
	                    sb.append((char)c);
	                    eatChar();
	                }
	                if (sb.length() == 0) throw new RuntimeException("Error in variable interpretation. String Builder lenght = 0.");
	                
	                v = parseVariable(sb.toString());
	                eatChar();
	            }
	            
	            
	            else { // numbers
	                StringBuilder sb = new StringBuilder();
	                while ((c >= '0' && c <= '9') || c == '.') {
	                    sb.append((char)c);
	                    eatChar();
	                }
	                if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
	                v = Double.parseDouble(sb.toString());
	            }
	            eatSpace();
	            if (c == '^') { // exponentiation
	                eatChar();
	                v = Math.pow(v, parseFactor());
	            }
	            if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
	            return v;
	        }
	        
	        
	        double parseVariable(String var) throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	  
	        	
	        	String[] splt = var.split("\\.");
	    	       
	        	Box b = getBoxList().elementAt(-1+(int) Double.parseDouble(splt[0]));
	       
	        	int i = -1;
	        	String name = new String();
	        	while (!name.equals(splt[1])){
	        		i=i+1;
	        		name = b.getVar().elementAt(i).getName();
	        	}
	        	
	        	return b.getVar().elementAt(i).getValue();

	      
	        }
	        
	        
	        
	    }
	    return new Parser().parse();
	}






	

}
