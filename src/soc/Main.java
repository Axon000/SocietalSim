package soc;

//import java.util.Scanner;

public class Main {

	public static World defaultWorld = new World();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		new MainWindow();
		
	}


	public void deleteFlux(Flux flux){
		flux.getInWorld().setFluxNbr(flux.getInWorld().getFluxNbr()-1);
		flux = null;
	}
	
	public void deleteBox(Box box){
		box.getInWorld().setFluxNbr(box.getInWorld().getFluxNbr()-1);
		box = null;
	}
}
