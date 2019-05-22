package application;

	import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Controller.AlertController;
import Controller.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.Tab;
	import javafx.scene.control.TabPane;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Books.Book;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Types.BookBuy;
import model.Types.BookRent;
import model.tabless.AllBuy;
import model.tabless.AllBuyCook;
import model.tabless.AllBuyEducation;
import model.tabless.AllBuyRoman;
import model.tabless.AllRent;
import model.tabless.AllRentCook;
import model.tabless.AllRentEducation;
import model.tabless.AllRentRoman;

	public class showBooks implements Initializable{

		public static AllRent allrent = null;
		public static AllBuy allbuy = null;
		
		public static Book b;
		
		public static AllRentRoman  allrentroman=null;
		public static AllRentCook allrentcook = null;
		public static AllRentEducation allrenteducation = null;
		
		public static AllBuyRoman allbuyroman=null;
		public static AllBuyCook allbuycook=null;
		public static AllBuyEducation allbuyeducation=null;

		private static Statement st;
		
	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private BorderPane borderoPano;

	    @FXML
	    private TabPane tabPane1;

	    @FXML
	    private Tab all1;

	    @FXML
	    private TableView<AllBuy> AllTable1;

	    @FXML
	    private TableColumn<AllBuy, Integer> idAll1;

	    @FXML
	    private TableColumn<AllBuy, String> titleAll1;

	    @FXML
	    private TableColumn<AllBuy, String> authorAll1;

	    @FXML
	    private TableColumn<AllBuy, String> descriptionAll1;

	    @FXML
	    private TableColumn<AllBuy, Integer> qtAll1;

	    @FXML
	    private TableColumn<AllBuy, Integer> priceAll;

	    @FXML
	    private Tab roman1;

	    @FXML
	    private TableView<AllBuyRoman> RomanTable1;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> idRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, String> titleRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, String> authorRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, String> summaryRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> ageRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> qtRoman1;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> priceRoman;

	    @FXML
	    private Tab education1;
	    
	    @FXML
	    private TableView<AllBuyEducation> educationTable1;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> idEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, String> titleEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, String> authorEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, String> subjectEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, String> alEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> qtEducation1;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> priceEducation;

	    @FXML
	    private Tab cookBook1;

	    @FXML
	    private TableView<AllBuyCook> cookTable1;
	    
	    @FXML
	    private TableColumn<AllBuyCook, Integer> idCook1;

	    @FXML
	    private TableColumn<AllBuyCook, String> titleCook1;

	    @FXML
	    private TableColumn<AllBuyCook, String> authorCook1;

	    @FXML
	    private TableColumn<AllBuyCook, String> regionCook1;

	    @FXML
	    private TableColumn<AllBuyCook, String> typeCook1;

	    @FXML
	    private TableColumn<AllBuyCook, Integer> qtCook1;

	    @FXML
	    private TableColumn<AllBuyCook, Integer> priceCook;

	    @FXML
	    private TabPane tabPane;

	    @FXML
	    private Tab all;

	    @FXML
	    private TableView<AllRent> AllTable;

	    @FXML
	    private TableColumn<AllRent, String> idAll;

	    @FXML
	    private TableColumn<AllRent, String> titleAll;

	    @FXML
	    private TableColumn<AllRent, String> authorAll;

	    @FXML
	    private TableColumn<AllRent, String> descriptionAll;

	    @FXML
	    private TableColumn<AllRent, Integer> qtAll;

	    @FXML
	    private TableColumn<AllRent, Integer> maxDaysAll;

	    @FXML
	    private TableColumn<AllRent, Integer> priceWeekk;

	    @FXML
	    private Tab roman;

	    @FXML
	    private TableView<AllRentRoman> RomanTable;

	    @FXML
	    private TableColumn<AllRentRoman, String> idRoman;

	    @FXML
	    private TableColumn<AllRentRoman, String> titleRoman;

	    @FXML
	    private TableColumn<AllRentRoman, String> authorRoman;

	    @FXML
	    private TableColumn<AllRentRoman, String> summaryRoman;

	    @FXML
	    private TableColumn<AllRentRoman, Integer> ageRoman;

	    @FXML
	    private TableColumn<AllRentRoman, Integer> qtRoman;

	    @FXML
	    private TableColumn<AllRentRoman, Integer> maxDaysRoman;

	    @FXML
	    private Tab education;

	    @FXML
	    private TableView<AllRentEducation> educationTable;

	    @FXML
	    private TableColumn<AllRentEducation, String> idEducation;

	    @FXML
	    private TableColumn<AllRentEducation, String> titleEducation;

	    @FXML
	    private TableColumn<AllRentEducation, String> authorEducation;

	    @FXML
	    private TableColumn<AllRentEducation, String> subjectEducation;

	    @FXML
	    private TableColumn<AllRentEducation, String> alEducation;

	    @FXML
	    private TableColumn<AllRentEducation, Integer> qtEducation;

	    @FXML
	    private TableColumn<AllRentEducation, Integer> maxDaysEducation;

	    @FXML
	    private Tab cookBook;

	    @FXML
	    private TableView<AllRentCook> cookTable;

	    @FXML
	    private TableColumn<AllRentCook, String> idCook;

	    @FXML
	    private TableColumn<AllRentCook, String> titleCook;

	    @FXML
	    private TableColumn<AllRentCook, String> authorCook;

	    @FXML
	    private TableColumn<AllRentCook, String> regionCook;

	    @FXML
	    private TableColumn<AllRentCook, String> typeCook;

	    @FXML
	    private TableColumn<AllRentCook, Integer> qtCook;
	    
	    @FXML
	    private TableColumn<AllRentCook, Integer> maxDaysCook;


	    @FXML
	    private Button back;

	    @FXML
	    void goBack(ActionEvent event) {
	    	
	    	if(login.employee.getId()==1 || login.employee.getId()==2) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/main.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setTitle("Home");
				window.setScene(main);
				window.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	}
	    	
	    	else {
	    		try {
					Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
					Scene main = new Scene(parent);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setTitle("Home");
					window.setScene(main);
					window.show();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		
	    	}
	    }

	   

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			defineAllRent();
			defineRomanRent();
			defineEducationRent();
			defineCookBookRent();
			
			
			defineAllBuy();
			defineRomanBuy();
			defineEducationBuy();
			defineCookBookBuy();
		}

		
		public void defineAllRent() {
			idAll.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleAll.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorAll.setCellValueFactory(new PropertyValueFactory<>("author"));
			descriptionAll.setCellValueFactory(new PropertyValueFactory<>("description"));
			qtAll.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysAll.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			priceWeekk.setCellValueFactory(new PropertyValueFactory<>("price_week"));
			AllTable.setItems(this.getRentBookss());
		}
	    
	    public void defineRomanRent() {
			idRoman.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleRoman.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorRoman.setCellValueFactory(new PropertyValueFactory<>("author"));
			summaryRoman.setCellValueFactory(new PropertyValueFactory<>("summary"));
			ageRoman.setCellValueFactory(new PropertyValueFactory<>("age"));
			qtRoman.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysRoman.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			RomanTable.setItems(this.getRentRoman());
		}

	    public void defineEducationRent() {
			idEducation.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleEducation.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorEducation.setCellValueFactory(new PropertyValueFactory<>("author"));
			subjectEducation.setCellValueFactory(new PropertyValueFactory<>("subject"));
			alEducation.setCellValueFactory(new PropertyValueFactory<>("academiclvl"));
			qtEducation.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysEducation.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			educationTable.setItems(this.getRentEducation());
		}
		

		
		public void defineCookBookRent() {
			idCook.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleCook.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorCook.setCellValueFactory(new PropertyValueFactory<>("author"));
			regionCook.setCellValueFactory(new PropertyValueFactory<>("region"));
			typeCook.setCellValueFactory(new PropertyValueFactory<>("typee"));
			qtCook.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysCook.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			cookTable.setItems(this.getRentCookBook());
		}
		
		
		public void defineAllBuy() {
			idAll1.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleAll1.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorAll1.setCellValueFactory(new PropertyValueFactory<>("author"));
			descriptionAll1.setCellValueFactory(new PropertyValueFactory<>("description"));
			qtAll1.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceAll.setCellValueFactory(new PropertyValueFactory<>("price"));
			AllTable1.setItems(this.getBuyBooks());
		}
		
		public void defineRomanBuy() {
			idRoman1.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleRoman1.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorRoman1.setCellValueFactory(new PropertyValueFactory<>("author"));
			summaryRoman1.setCellValueFactory(new PropertyValueFactory<>("summary"));
			ageRoman1.setCellValueFactory(new PropertyValueFactory<>("age"));
			qtRoman1.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceRoman.setCellValueFactory(new PropertyValueFactory<>("price"));
			RomanTable1.setItems(this.getBuyRoman());
		}
		
		
		public void defineEducationBuy() {
			idEducation1.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleEducation1.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorEducation1.setCellValueFactory(new PropertyValueFactory<>("author"));
			subjectEducation1.setCellValueFactory(new PropertyValueFactory<>("subject"));
			alEducation1.setCellValueFactory(new PropertyValueFactory<>("academiclvl"));
			qtEducation1.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceEducation.setCellValueFactory(new PropertyValueFactory<>("price"));
			educationTable1.setItems(this.getBuyEducation());
		}
		

		
		public void defineCookBookBuy() {
			idCook1.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleCook1.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorCook1.setCellValueFactory(new PropertyValueFactory<>("author"));
			regionCook1.setCellValueFactory(new PropertyValueFactory<>("region"));
			typeCook1.setCellValueFactory(new PropertyValueFactory<>("typee"));
			qtCook1.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceCook.setCellValueFactory(new PropertyValueFactory<>("price"));
			cookTable1.setItems(this.getBuyCookBook());
		}
	    
		
		/* **********
		 * *********
		 * ********
		 * *******
		 * ******
		 * *****
		 * ****
		 * ***
		 * **
		 * Data
		 *  Base 
		 *   Functions
		 * **  
		 * ***
		 * ****
		 * *****
		 * ******
		 * *******
		 * ********
		 * *********
		 * **********
		 */
		
		public  ObservableList <AllRent> getRentBookss(){
			
			ObservableList <AllRent> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				AllRent book;
				
				ResultSet resultRoman = st.executeQuery("SELECT * FROM roman,booksrent WHERE booksrent.id=roman.type_id");
				resultRoman.beforeFirst();
				
				
				
				while(resultRoman.next()) {
					book = new AllRent(resultRoman.getString(1),resultRoman.getString("title"),resultRoman.getString("author"),resultRoman.getString("description"),resultRoman.getInt("maxDay"),resultRoman.getInt("price_Week"),resultRoman.getInt("quantity"));
					books.add(book);
					}
				
				ResultSet resultEducation = st.executeQuery("SELECT * FROM education,booksrent WHERE booksrent.id=education.type_id");
				resultEducation.beforeFirst();
				
				while(resultEducation.next()) {
					book = new AllRent(resultEducation.getString(1),resultEducation.getString("title"),resultEducation.getString("author"),resultEducation.getString("description"),resultEducation.getInt("maxDay"),resultEducation.getInt("price_Week"),resultEducation.getInt("quantity"));
					books.add(book);
					}
				
				ResultSet resultCookBook = st.executeQuery("SELECT * FROM cookbook,booksrent WHERE booksrent.id=cookbook.type_id");
				resultCookBook.beforeFirst();
				
				while(resultCookBook.next()) {
					book = new AllRent(resultCookBook.getString(1),resultCookBook.getString("title"),resultCookBook.getString("author"),resultCookBook.getString("description"),resultCookBook.getInt("maxDay"),resultCookBook.getInt("price_Week"),resultCookBook.getInt("quantity"));
					books.add(book);
					}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}





		public  ObservableList <AllRentRoman> getRentRoman(){
			
			ObservableList <AllRentRoman> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				AllRentRoman allRentRoman;
				ResultSet resultRoman = st.executeQuery("SELECT * FROM roman,booksrent WHERE booksrent.id=roman.type_id");
				resultRoman.beforeFirst();
			
				while(resultRoman.next()) {
					allRentRoman = new AllRentRoman(resultRoman.getString(1),resultRoman.getString("title"),resultRoman.getString("author"),resultRoman.getString("summary"),resultRoman.getInt(7),resultRoman.getInt("maxDay"),resultRoman.getInt("price_Week"),resultRoman.getInt("quantity"));
					books.add(allRentRoman);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}

		public  ObservableList <AllRentEducation> getRentEducation(){
			
			ObservableList <AllRentEducation> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				AllRentEducation allRentEducation;

				ResultSet resultEducation = st.executeQuery("SELECT * FROM education,booksrent WHERE booksrent.id=education.type_id");
				resultEducation.beforeFirst();
				
				while(resultEducation.next()) {
					allRentEducation = new AllRentEducation(resultEducation.getString(1),resultEducation.getString(2),resultEducation.getString(3),resultEducation.getInt("maxDay"),resultEducation.getInt("price_Week"),resultEducation.getString(6),resultEducation.getString(7),resultEducation.getInt("quantity"));
					books.add(allRentEducation);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}


		public  ObservableList <AllRentCook> getRentCookBook(){
			
			ObservableList <AllRentCook> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
					AllRentCook allRentCook;
					ResultSet resultCookBook = st.executeQuery("SELECT * FROM cookbook,booksrent WHERE booksrent.id=cookbook.type_id");
					resultCookBook.beforeFirst();
				
				while(resultCookBook.next()) {
					allRentCook = new AllRentCook(resultCookBook.getString(1),resultCookBook.getString(2),resultCookBook.getString(3),resultCookBook.getInt(5),resultCookBook.getInt(10),resultCookBook.getString(6),resultCookBook.getString(7),resultCookBook.getInt("quantity"));
					books.add(allRentCook);
					}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}
		
		
		
		
		
		public  ObservableList <AllBuy> getBuyBooks(){
			
			ObservableList <AllBuy> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				AllBuy allBuy;
				ResultSet resultRoman = st.executeQuery("SELECT * FROM roman,booksBuy WHERE booksbuy.id=roman.type_id");
				resultRoman.beforeFirst();
				
				
				
				while(resultRoman.next()) {
					allBuy = new AllBuy(resultRoman.getString(1),resultRoman.getString(2),resultRoman.getString(3),resultRoman.getString(4),resultRoman.getInt(5),resultRoman.getInt(10));
					books.add(allBuy);
					}
				
				ResultSet resultEducation = st.executeQuery("SELECT * FROM education,booksbuy WHERE booksbuy.id=education.type_id");
				resultEducation.beforeFirst();
				
				while(resultEducation.next()) {
					allBuy = new AllBuy(resultEducation.getString(1),resultEducation.getString(2),resultEducation.getString(3),resultEducation.getString(4),resultEducation.getInt(5),resultEducation.getInt(10));
					books.add(allBuy);
					}
				
				ResultSet resultCookBook = st.executeQuery("SELECT * FROM cookbook,booksbuy WHERE booksbuy.id=cookbook.type_id");
				resultCookBook.beforeFirst();
				
				while(resultCookBook.next()) {
					allBuy = new AllBuy(resultCookBook.getString(1),resultCookBook.getString(2),resultCookBook.getString(3),resultCookBook.getString(4),resultCookBook.getInt(5),resultCookBook.getInt(10));
					books.add(allBuy);
					}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}


public  ObservableList <AllBuyRoman> getBuyRoman(){
	
	ObservableList <AllBuyRoman> books= FXCollections.observableArrayList();
	try {
		
		st=DBconnection.getConnection().createStatement();
		AllBuyRoman allBuyRoman;
		ResultSet resultRoman = st.executeQuery("SELECT * FROM roman,booksBuy WHERE booksbuy.id=roman.type_id");
		resultRoman.beforeFirst();
	
		while(resultRoman.next()) {
			allBuyRoman = new AllBuyRoman(resultRoman.getString(1),resultRoman.getString(2),resultRoman.getString(3),resultRoman.getString(6),resultRoman.getInt(7),resultRoman.getInt(5),resultRoman.getInt(10));
			books.add(allBuyRoman);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(books);
	
	return books;
}


public  ObservableList <AllBuyEducation> getBuyEducation(){
	
	ObservableList <AllBuyEducation> books= FXCollections.observableArrayList();
	try {
		
		st=DBconnection.getConnection().createStatement();
		AllBuyEducation allBuyEducation;

		ResultSet resultEducation = st.executeQuery("SELECT * FROM education,booksbuy WHERE booksbuy.id=education.type_id");
		resultEducation.beforeFirst();
		
		while(resultEducation.next()) {
			allBuyEducation = new AllBuyEducation(resultEducation.getString(1),resultEducation.getString(2),resultEducation.getString(3),resultEducation.getInt(5),resultEducation.getInt(10),resultEducation.getString(6),resultEducation.getString(7));
			books.add(allBuyEducation);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(books);
	
	return books;
}


public  ObservableList <AllBuyCook> getBuyCookBook(){
	
	ObservableList <AllBuyCook> books= FXCollections.observableArrayList();
	try {
		
		st=DBconnection.getConnection().createStatement();
		AllBuyCook allBuyCook;
			ResultSet resultCookBook = st.executeQuery("SELECT * FROM cookbook,booksbuy WHERE booksbuy.id=cookbook.type_id");
			resultCookBook.beforeFirst();
		
		while(resultCookBook.next()) {
			allBuyCook = new AllBuyCook(resultCookBook.getString(1),resultCookBook.getString(2),resultCookBook.getString(3),resultCookBook.getInt(5),resultCookBook.getInt(10),resultCookBook.getString(6),resultCookBook.getString(7));
			books.add(allBuyCook);
			}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(books);
	
	return books;
}
				
	}
