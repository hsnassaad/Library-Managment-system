package application;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Controller.AlertController;
import Controller.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Types.BookBuy;



public class buy implements Initializable{


	private static Statement st;
	    @FXML
	    private Text text;

	    @FXML
	    private TextField price;

	    @FXML
	    private ImageView dollaricon;
	    @FXML
	    private Button create;

	    @FXML
	    void saveBook(ActionEvent event) {
	    	
	    	//Add Alert box to Ask the Employee if he want to add a new book or return to home page
	    	Pattern digitPattern = Pattern.compile("[0-9]");
	    	if(!price.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(price.getText());
	    		if(m.find()) {
	    		BookBuy buy = new BookBuy(Integer.parseInt(price.getText()));
	    		this.insertTypeBuy(buy);
	    		
	    		if(AddRoman.roman != null) {
	    		AddRoman.roman.setType(buy);
	    		AddRoman.insertRoman(AddRoman.roman);
	    		//AddRoman.roman = null;
	    		}
	    		
	    		if(AddCookBook.cookBook != null) {
	    			AddCookBook.cookBook.setType(buy);
	    			AddCookBook.insertCookBook(AddCookBook.cookBook);
		    		//AddCookBook.cookBook = null;
		    		}
	    		
	    		if(AddEducation.education != null) {
	    			AddEducation.education.setType(buy);
	    			AddEducation.insertEducation(AddEducation.education);
		    		//AddEducation.education = null;
		    		}
	    		 	buy = null;
	    		 	Alert alert = new Alert(AlertType.CONFIRMATION);
	    		 	alert.setContentText("Do you want to buy another book ?");
	    		 	alert.setHeaderText("");
	    		 	alert.showAndWait();
	    		 	
	    		 	if(alert.getResult()==ButtonType.OK) {
	    		 		AddEducation.education = null;
	    		 		AddCookBook.cookBook = null;
	    		 		AddRoman.roman = null;
	    		 		try {
	    			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/AddBook.fxml"));
	    					Scene main = new Scene(root);
	    					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	    					window.setScene(main);
	    					window.setTitle("Add Book");
	    					window.show();
	    					} catch (IOException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    		 	}
	    		 	
	    		 	if(alert.getResult()==ButtonType.CANCEL) {
	    		 		try {
							Parent  parent = FXMLLoader.load(getClass().getResource("FXML/main.FXML"));
							Scene main = new Scene(parent);
							Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
							window.setScene(main);
							window.setTitle("Home");
							window.show();
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    		 	}
	    		 	
	    		 	
	    		System.out.println(AddRoman.roman);
	    		}else {
	    			AlertController.error("Invalid price number");
	    		}
	    	}else {
	    		AlertController.error("please fill out the price field :)");
	    	}
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			Image dolar = new Image("/images/dollar-flat.png");
			dollaricon.setImage(dolar);

		}

		
		// Data Base Functions

		public  void insertTypeBuy(BookBuy book) {
			
			try {
				st=DBconnection.getConnection().createStatement();
				book.setId();
				st.executeUpdate("INSERT INTO booksbuy(id,price) VALUES('"+book.getId()+"','"+book.getPrice()+"')");
				System.out.println(book.getId()+" Has been inserted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
