package model.tabless;

public class billati {

	private String id;
	private String desc;
	private int qt;
	private int price;
	private int total;
	
	public billati(String id, String desc, int qt, int price) {
		super();
		this.id = id;
		this.desc = desc;
		this.qt = qt;
		this.price = price;
		this.total = price*qt;
	}

	public billati(String id2, String description, int i, int j, int penaltiTaken) {
		super();
		this.id = id2;
		this.desc = description;
		this.qt = i;
		this.price = j;
		this.total = penaltiTaken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "billati [id=" + id + ", desc=" + desc + ", qt=" + qt + ", price=" + price + "]";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
	
}
