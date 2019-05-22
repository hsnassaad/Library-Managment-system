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
import model.Types.BookBuy;
import model.tabless.AllBuy;
import model.tabless.AllBuyCook;
import model.tabless.AllBuyEducation;
import model.tabless.AllBuyRoman;

public class BuyBooksCollection implements Initializable{

		private static Statement st;
		public static AllBuy allbuy = null;
		
	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private BorderPane borderoPano;

	    @FXML
	    private TabPane tabPane;

	    @FXML
	    private Tab all;

	    @FXML
	    private TableView<AllBuy> AllTable;

	    @FXML
	    private TableColumn<AllBuy, Integer> idAll;

	    @FXML
	    private TableColumn<AllBuy, String> titleAll;

	    @FXML
	    private TableColumn<AllBuy, String> authorAll;

	    @FXML
	    private TableColumn<AllBuy, String> descriptionAll;

	    @FXML
	    private TableColumn<AllBuy, Integer> qtAll;

	    @FXML
	    private TableColumn<AllBuy, Integer> priceAll;

	    @FXML
	    private Tab roman;

	    @FXML
	    private TableView<AllBuyRoman> RomanTable;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> idRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, String> titleRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, String> authorRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, String> summaryRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> ageRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> qtRoman;

	    @FXML
	    private TableColumn<AllBuyRoman, Integer> priceRoman;

	    @FXML
	    private Tab education;
	    
	    @FXML
	    private TableView<AllBuyEducation> educationTable;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> idEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, String> titleEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, String> authorEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, String> subjectEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, String> alEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> qtEducation;

	    @FXML
	    private TableColumn<AllBuyEducation, Integer> priceEducation;

	    @FXML
	    private Tab cookBook;

	    @FXML
	    private TableView<AllBuyCook> cookTable;
	    
	    @FXML
	    private TableColumn<AllBuyCook, Integer> idCook;

	    @FXML
	    private TableColumn<AllBuyCook, String> titleCook;

	    @FXML
	    private TableColumn<AllBuyCook, String> authorCook;

	    @FXML
	    private TableColumn<AllBuyCook, String> regionCook;

	    @FXML
	    private TableColumn<AllBuyCook, String> typeCook;

	    @FXML
	    private TableColumn<AllBuyCook, Integer> qtCook;

	    @FXML
	    private TableColumn<AllBuyCook, Integer> priceCook;

	    @FXML
	    private Button add;

	    @FXML
	    private Button remove;

	    @FXML
	    private Button back;

	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			defineAll();
			defineRoman();
			defineEducation();
			defineCookBook();
		}
	        
	    @FXML
	    void AddBook(ActionEvent event) {
	    	
	    	if(AllTable.getSelectionModel().getSelectedItem() != null) {
	    	allbuy = AllTable.getSelectionModel().getSelectedItem();
	    	System.out.println(allbuy);
	    	}
	    	
	    	if(RomanTable.getSelectionModel().getSelectedItem() != null) {
	    		//System.out.println(RomanTable.getSelectionModel().getSelectedItem());
	    		allbuy = RomanTable.getSelectionModel().getSelectedItem();
	    		System.out.println(allbuy);
	    	}
	    	
	    	if(educationTable.getSelectionModel().getSelectedItem() !=null) {
	    		//System.out.println(educationTable.getSelectionModel().getSelectedItem());
	    		allbuy = educationTable.getSelectionModel().getSelectedItem();
	    		System.out.println(allbuy);
	    	}
	    	if(cookTable.getSelectionModel().getSelectedItem() !=null) {
	    	//System.out.println(cookTable.getSelectionModel().getSelectedItem());
	    	allbuy = cookTable.getSelectionModel().getSelectedItem();
	    	System.out.println(allbuy);
	    	}
	    	
	    	if(allbuy == null) {
	    		AlertController.information("You need to choose a book");
	    	}else {
	    		if(allbuy.getQt()<=6) {
	    			AlertController.information("low in quantity, you can't rent this book");
	    			allbuy=null;
	    		}else {
	    	AlertController.information(allbuy.getTitle()+" will be added");
	    		}
	    	}
	    	//System.out.println(booksGet);
	    	}

	    @FXML
	    void back(ActionEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/SaleAction.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
	    }

	    @FXML
	    void removeBook(ActionEvent event) {
	    	allbuy=null;
	    }
		
		
		public void defineAll() {
			idAll.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleAll.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorAll.setCellValueFactory(new PropertyValueFactory<>("author"));
			descriptionAll.setCellValueFactory(new PropertyValueFactory<>("description"));
			qtAll.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceAll.setCellValueFactory(new PropertyValueFactory<>("price"));
			AllTable.setItems(this.getBuyBooks());
		}
		
		public void defineRoman() {
			idRoman.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleRoman.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorRoman.setCellValueFactory(new PropertyValueFactory<>("author"));
			summaryRoman.setCellValueFactory(new PropertyValueFactory<>("summary"));
			ageRoman.setCellValueFactory(new PropertyValueFactory<>("age"));
			qtRoman.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceRoman.setCellValueFactory(new PropertyValueFactory<>("price"));
			RomanTable.setItems(this.getBuyRoman());
		}
		
		
		public void defineEducation() {
			idEducation.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleEducation.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorEducation.setCellValueFactory(new PropertyValueFactory<>("author"));
			subjectEducation.setCellValueFactory(new PropertyValueFactory<>("subject"));
			alEducation.setCellValueFactory(new PropertyValueFactory<>("academiclvl"));
			qtEducation.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceEducation.setCellValueFactory(new PropertyValueFactory<>("price"));
			educationTable.setItems(this.getBuyEducation());
		}
		

		
		public void defineCookBook() {
			idCook.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleCook.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorCook.setCellValueFactory(new PropertyValueFactory<>("author"));
			regionCook.setCellValueFactory(new PropertyValueFactory<>("region"));
			typeCook.setCellValueFactory(new PropertyValueFactory<>("typee"));
			qtCook.setCellValueFactory(new PropertyValueFactory<>("qt"));
			priceCook.setCellValueFactory(new PropertyValueFactory<>("price"));
			cookTable.setItems(this.getBuyCookBook());
		}
		
		
		
		// Data Base Functions
		
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












