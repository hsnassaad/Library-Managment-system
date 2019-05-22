package model;

public class Sale {

	private int id;
	private String typeSale;
	private int porcentage;
	
	
	
	public Sale(int id, String typeSale, int porcentage) {
		super();
		this.id = id;
		this.typeSale = typeSale;
		this.porcentage = porcentage;
	}
	
	
	public Sale(String typeSale, int porcentage) {
		super();
		this.typeSale = typeSale;
		this.porcentage = porcentage;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeSale() {
		return typeSale;
	}
	public void setTypeSale(String typeSale) {
		this.typeSale = typeSale;
	}
	public int getPorcentage() {
		return porcentage;
	}
	public void setPorcentage(int porcentage) {
		this.porcentage = porcentage;
	}
	@Override
	public String toString() {
		return "Sale [id=" + id + ", typeSale=" + typeSale + ", porcentage=" + porcentage + "]";
	}
	
	
	
	
}
