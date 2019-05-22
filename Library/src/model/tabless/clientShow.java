package model.tabless;

public class clientShow {

	private int id;
	private String name;
	private int phone;
	private int point;
	
	
	public clientShow(int id, String name, int phone, int point) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.point = point;
	}
	
//	public clientShow(String name, int phone, int point) {
//		super();
//		this.name = name;
//		this.phone = phone;
//		this.point = point;
//	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	@Override
	public String toString() {
		return "clientShow [id=" + id + ", name=" + name + ", phone=" + phone + ", point=" + point + "]";
	}
	
	
	
	
}
