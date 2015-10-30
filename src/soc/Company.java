package soc;
public class Company extends Box {
	

	private int nbrWorker;
	private double revenue;
		
	public Company() {
		super();
	}

	public int getNbrWorker() {return nbrWorker;}
	public void setNbrWorker(int nbrWorker) {this.nbrWorker = nbrWorker;}
	public double getRevenue() {return revenue;}
	public void setRevenue(double revenue) {this.revenue = revenue;}
	
}
