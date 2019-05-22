package model.Types;

public class BookBuy implements Type{

		private int id;
		private int price;
		public static int c = 1;
		
		public BookBuy(int id, int price) {
			super();
			this.id = id;
			this.price = price;
		}
		
		public BookBuy(int price) {
			super();
			this.price = price;
			
		}
		
		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public void setId() {
			c+=2;
			this.id = c;
		}

		@Override
		public String toString() {
			return "BookBuy [id=" + id + ", price=" + price + "]";
		}
	
	
	
}