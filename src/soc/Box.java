package soc;
import java.util.Vector;

public class Box {

	private String name;
	private int inputNbr;
	private int outputNbr;
	private double inputValue;
	private double outputValue;
	private Vector<Flux> inputList = new Vector<Flux>();
	private Vector<Flux> outputList = new Vector<Flux>();
	private String inputNameList;
	private String outputNameList;
	private World inWorld;
	private BoxGraphic graph = new BoxGraphic();
	//private double var1=50;
	private Vector<Var> var = new Vector<Var>();
	
	//-----------------------------------------------------------------------------------------------
	
	public Box(){
		setName(null);
		inputNbr = 0;
		outputNbr = 0;
		inputValue = 0;
		outputValue = 0;
		Main.defaultWorld.setBoxNbr(Main.defaultWorld.getBoxNbr()+1);
		setInWorld(Main.defaultWorld);
	}
	
	public Box(String pname){
		setName(pname);
		inputNbr = 0;
		outputNbr = 0;
		inputValue = 0;
		outputValue = 0;
		Main.defaultWorld.setBoxNbr(Main.defaultWorld.getBoxNbr()+1);
		setInWorld(Main.defaultWorld);
	}
	
	
	public Box(World world){
		setName(null);
		inputNbr = 0;
		outputNbr = 0;
		inputValue = 0;
		outputValue = 0;
		world.setBoxNbr(world.getBoxNbr()+1);
		setInWorld(world);
	}
	
	public Box(String pname, World world){
		setName(pname);
		inputNbr = 0;
		outputNbr = 0;
		inputValue = 0;
		outputValue = 0;
		world.setBoxNbr(world.getBoxNbr()+1);
		setInWorld(world);
	}
	
	public Box(String pname, World world, BoxGraphic pgraph){
		setName(pname);
		inputNbr = 0;
		outputNbr = 0;
		inputValue = 0;
		outputValue = 0;
		world.setBoxNbr(world.getBoxNbr()+1);
		setInWorld(world);
		setGraph(pgraph);
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public void linkInput(Flux inputFlux){
		setInputNbr(inputNbr + 1);
		setInputValue(inputValue + inputFlux.getQuantity());
		inputList.addElement(inputFlux);
		updateInputNameList(inputList);
	}
	
	public void linkOutput(Flux outputFlux){
		setOutputNbr(outputNbr + 1);
		setOutputValue(outputValue + outputFlux.getQuantity());
		outputList.addElement(outputFlux);
		updateOutputNameList(outputList);
	}
	
	public void delinkInput(Flux inputFlux){
		setInputNbr(inputNbr - 1);
		setInputValue(inputValue - inputFlux.getQuantity());
		inputList.removeElementAt(inputList.indexOf(inputFlux));
		updateInputNameList(inputList);
		
	}
	
	public void delinkOutput(Flux outputFlux){
		setOutputNbr(outputNbr - 1);
		setOutputValue(outputValue - outputFlux.getQuantity());
		outputList.removeElementAt(outputList.indexOf(outputFlux));
		updateOutputNameList(outputList);
	}
	
	//-----------------------------------------------------------------------------------------------
	

	
	
	
	public void updateInputNameList(Vector<Flux> list){
		inputNameList = "";
		if (list.size() > 1){
			for (int i=0; i < list.size()-1; i++){
				inputNameList=inputNameList+list.elementAt(i).getName()+", ";
			}
			inputNameList=inputNameList+list.elementAt(list.size()-1).getName();
		}
		else if(list.size() == 1){
			inputNameList=inputNameList+list.elementAt(list.size()-1).getName();
		}
			
	}
	
	public void updateOutputNameList(Vector<Flux> list){
		outputNameList = "";
		if (list.size() > 1){
			for (int i=0; i < list.size()-1; i++){
				outputNameList=outputNameList+list.elementAt(i).getName()+", ";
			}
			outputNameList=outputNameList+list.elementAt(list.size()-1).getName();
		}
		else if(list.size() == 1){
			outputNameList=outputNameList+list.elementAt(list.size()-1).getName();
		}
		
			
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public String description(){
		return "Name: "+this.name+"\n inputNbr: "+this.inputNbr+"\n outputNbr: "+this.outputNbr+"\n inputValue: "+this.getInputValue()+"\n outputValue: "+this.getOutputValue()+"\n inputList: "+this.inputNameList+"\n outputList: "+this.outputNameList;
		}
	
	//-----------------------------------------------------------------------------------------------

	public World getInWorld() {return inWorld;}
	public void setInWorld(World inWorld) {this.inWorld = inWorld;}
	public Vector<Flux> getInputFluxList(){return this.inputList;}
	public Vector<Flux> getOutputFluxList(){return this.outputList;}
	
	public double getInputValue(){
		if (getInputFluxList().size()==0){
			this.setInputValue(0);
			return 0;
		}
		else {
			Double a =  0.0 ;
			for (int i=0; i < this.getInputFluxList().size(); i++){
				a= a + getInputFluxList().elementAt(i).getQuantity();
			}
			this.setInputValue(a);
			return a;
		}		
	}
	
	public double getOutputValue(){
		if (getOutputFluxList().size()==0){
			this.setOutputValue(0);
			return 0;
		}
		else {
			Double a =  0.0 ;
			for (int i=0; i < this.getOutputFluxList().size(); i++){
				a= a + getOutputFluxList().elementAt(i).getQuantity();
			}
			this.setOutputValue(a);
			return a;
		}		
	}
	
	//public double getinputValue(){return inputValue;}
	//public double getoutputValue(){return outputValue;}
	
	public void setInputNbr(int pinputNbr){inputNbr= pinputNbr;}
	public void setOutputNbr(int poutputNbr){outputNbr= poutputNbr;}
	public void setInputValue(double pinputValue){inputValue= pinputValue;}
	public void setOutputValue(double poutputValue){outputValue= poutputValue;}
	public BoxGraphic getGraph() {return graph;}
	public void setGraph(BoxGraphic graph) {this.graph = graph;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public Vector<Var> getVar() {return var;}
	public void setVar(Vector<Var> var) {this.var = var;}

	
}
