package soc;

public class Var {
	private String name;
	private double value;
	
	public Var(){
		setName("");
		setValue(0);
	}
	
	public Var(String pname, double pvalue){
		setName(pname);
		setValue(pvalue);
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public double getValue() {return value;}
	public void setValue(double value) {this.value = value;}
	
	
}
