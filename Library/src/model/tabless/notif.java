package model.tabless;

public class notif {

	private int id;
	private String mess;
	private int bit;
	

	public notif(int id, String mess) {
		super();
		this.setId(id);
		this.setMess(mess);
		setBit(1);
	}
	
	
	

	public notif(int id, String mess, int bit) {
		super();
		this.id = id;
		this.mess = mess;
		this.bit = bit;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	
	@Override
	public String toString() {
		return "notif [id=" + id + ", mess=" + mess + "]";
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}
	
	
}
