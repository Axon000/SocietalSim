package soc;

import java.lang.reflect.InvocationTargetException;

public class Flux {

	private String name;
	private double quantity;
	private Box fromBox;
	private Box toBox;
	private Boolean isConstant;
	private String formula;
	private World inWorld;
	private FluxGraphic fluxg;
	
	
	public Flux(){
		quantity = 0;
		isConstant = true;
		Main.defaultWorld.setFluxNbr(Main.defaultWorld.getFluxNbr()+1);
		setInWorld(Main.defaultWorld);
	}
	
	public Flux(String pname, double pquantity, Box from, Box to){
		name = pname;
		quantity = pquantity;
		linkFrom(from);
		linkTo(to);
		isConstant = true;
		Main.defaultWorld.setFluxNbr(Main.defaultWorld.getFluxNbr()+1);
		setInWorld(Main.defaultWorld);
	}
		
	public Flux(World world){
		quantity = 0;
		isConstant = true;
		world.setFluxNbr(world.getFluxNbr()+1);
		setInWorld(world);
	}
	
	public Flux(String pname, World world, double pquantity, Box from, Box to){
		name = pname;
		quantity = pquantity;
		linkFrom(from);
		linkTo(to);
		isConstant = true;
		world.setFluxNbr(world.getFluxNbr()+1);
		setInWorld(world);
	}
	
	public Flux(String pname, World world, FluxGraphic fluxg){
		name = pname;
		isConstant = true;
		world.setFluxNbr(world.getFluxNbr()+1);
		setInWorld(world);
		setFluxg(fluxg);
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public void linkFrom(Box pfrom){
		this.fromBox = pfrom;
		pfrom.linkOutput(this);
	}
	
	public void linkTo(Box pTo){
		this.toBox = pTo;
		pTo.linkInput(this);
	}
	
	public void delinkFrom(){
		this.fromBox.delinkOutput(this);
		fromBox =  null;			
	}
	
	public void delinkTo(){
		this.toBox.delinkInput(this);
		toBox =  null;			
	}
	
	//-----------------------------------------------------------------------------------------------
	
	
	public void evaluateFormula(){
		if (isConstant){
			System.out.println("Warning: Formula not set. Quantity will remain constant.");
		}
		
		else {
			
/*			ScriptEngineManager mgr = new ScriptEngineManager();
		    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		    this.quantity = (double) engine.eval(this.formula);*/
			
			try {
				this.setQuantity(this.inWorld.eval(this.getFormula()));
			} catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	



	//-----------------------------------------------------------------------------------------------
	
	public double getQuantity(){return this.quantity;}
	public void setQuantity(double pQuantity) {this.quantity = pQuantity;}
	public String getName(){return this.name;}
	
	
	public void setFormula(String pFormula){
		this.isConstant=false;
		this.formula=pFormula;
	}
	
	public String getFormula(){return this.formula;}
	public World getInWorld() {return inWorld;}
	public void setInWorld(World inWorld) {this.inWorld = inWorld;}
	public FluxGraphic getFluxg() {return this.fluxg;}
	public void setFluxg(FluxGraphic pfluxg) {this.fluxg = pfluxg;}

}
