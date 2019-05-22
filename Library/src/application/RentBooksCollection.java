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
import model.tabless.AllRent;
import model.tabless.AllRentCook;
import model.tabless.AllRentEducation;
import model.tabless.AllRentRoman;

public class RentBooksCollection implements Initializable{

	public static AllRent allrent = null;
	private static Statement st;

	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private BorderPane borderoPano;

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
			 allrent = null;
		}
	    
	    @FXML
	    void AddBook(ActionEvent event) {
	    	
	    	if(AllTable.getSelectionModel().getSelectedItem() != null) {
	    		allrent = AllTable.getSelectionModel().getSelectedItem();
		    	System.out.println(allrent);
		    	}
		    	
		    	if(RomanTable.getSelectionModel().getSelectedItem() != null) {
		    		//System.out.println(RomanTable.getSelectionModel().getSelectedItem());
		    		allrent = RomanTable.getSelectionModel().getSelectedItem();
		    		System.out.println(allrent);
		    	}
		    	
		    	if(educationTable.getSelectionModel().getSelectedItem() !=null) {
		    		//System.out.println(educationTable.getSelectionModel().getSelectedItem());
		    		allrent = educationTable.getSelectionModel().getSelectedItem();
		    		System.out.println(allrent);
		    	}
		    	if(cookTable.getSelectionModel().getSelectedItem() !=null) {
		    	//System.out.println(cookTable.getSelectionModel().getSelectedItem());
		    		allrent = cookTable.getSelectionModel().getSelectedItem();
		    	System.out.println(allrent);
		    	}
		    	
		    	if(allrent == null) {
		    		AlertController.information("You need to choose a book");
		    	}else {
		    		if(allrent.getQt()<=6) {
		    			AlertController.information("low in quantity, you can't rent this book");
		    			allrent=null;
		    		}
		    		else {
		    	AlertController.information(allrent.getTitle()+" will be added");
		    		}
		    	}
		    	//System.out.println(booksGet);
	    }

	    @FXML
	    void back(ActionEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/RentAction.FXML"));
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
	    	allrent =null;
	    }
	    
	    public void defineAll() {
			idAll.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleAll.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorAll.setCellValueFactory(new PropertyValueFactory<>("author"));
			descriptionAll.setCellValueFactory(new PropertyValueFactory<>("description"));
			qtAll.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysAll.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			priceWeekk.setCellValueFactory(new PropertyValueFactory<>("price_week"));
			AllTable.setItems(this.getRentBookss());
		}
	    
	    public void defineRoman() {
			idRoman.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleRoman.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorRoman.setCellValueFactory(new PropertyValueFactory<>("author"));
			summaryRoman.setCellValueFactory(new PropertyValueFactory<>("summary"));
			ageRoman.setCellValueFactory(new PropertyValueFactory<>("age"));
			qtRoman.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysRoman.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			RomanTable.setItems(this.getRentRoman());
		}

	    public void defineEducation() {
			idEducation.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleEducation.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorEducation.setCellValueFactory(new PropertyValueFactory<>("author"));
			subjectEducation.setCellValueFactory(new PropertyValueFactory<>("subject"));
			alEducation.setCellValueFactory(new PropertyValueFactory<>("academiclvl"));
			qtEducation.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysEducation.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			educationTable.setItems(this.getRentEducation());
		}
		

		
		public void defineCookBook() {
			idCook.setCellValueFactory(new PropertyValueFactory<>("id"));
			titleCook.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorCook.setCellValueFactory(new PropertyValueFactory<>("author"));
			regionCook.setCellValueFactory(new PropertyValueFactory<>("region"));
			typeCook.setCellValueFactory(new PropertyValueFactory<>("typee"));
			qtCook.setCellValueFactory(new PropertyValueFactory<>("qt"));
			maxDaysCook.setCellValueFactory(new PropertyValueFactory<>("maxDays"));
			cookTable.setItems(this.getRentCookBook());
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

		
		
		
		
		
		
	}

