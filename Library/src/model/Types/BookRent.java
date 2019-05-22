package model.Types;

public class BookRent implements Type{
	
	private int price_Week;
	private int id;
	private int maxDay;
	private int penalty;
	public static int c = 0;
	
	public BookRent(int price_Week, int id, int maxDay, int penalty) {
		super();
		this.price_Week = price_Week;
		this.id = id;
		this.maxDay = maxDay;
		this.penalty = penalty;
	}
	
	public BookRent(int price_Week, int maxDay, int penalty) {
		super();
		this.price_Week = price_Week;
		this.maxDay = maxDay;
		this.penalty = penalty;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public int getPrice_Week() {
		return price_Week;
	}

	public void setPrice_Week(int price_Week) {
		this.price_Week = price_Week;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(int maxDay) {
		this.maxDay = maxDay;
	}

	@Override
	public String toString() {
		return "BookRent [price_Week=" + price_Week + ", id=" + id + ", maxDay=" + maxDay + ", penalty=" + penalty
				+ "]";
	}
	
	
	
}
