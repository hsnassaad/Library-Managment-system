package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Books.Book;
import model.Books.CookBook;



public class AddCookBook implements Initializable{

	public static CookBook cookBook;
	private static Statement st;
	
	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private StackPane stackPane;

	    @FXML
	    private Text text;

	    @FXML
	    private TextField id;

	    @FXML
	    private TextField title;

	    @FXML
	    private TextField author;

	    @FXML
	    private TextField description;

	    @FXML
	    private TextField qt;

	    @FXML
	    private TextField FoodRegion;

	    @FXML
	    private TextField foodType;

	    @FXML
	    private Button cancel;

	    @FXML
	    private Button next;

	    @FXML
	    private ImageView back;
	    
	    @FXML
	    void Cancel(ActionEvent event) {
	     	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setContentText("Are u sure you want to delete all the information");
	    	alert.setTitle("");
	    	alert.setHeaderText("");
	    	alert.showAndWait();
	    	
	    	if(alert.getResult()==ButtonType.OK) {
	    		id.setText("");
	    		title.setText("");
	    		author.setText("");
	    		description.setText("");
	    		qt.setText("");
	    		FoodRegion.setText("");
	    		foodType.setText("");
	    	}
	    }

	    @FXML
	    void GoToNext(ActionEvent event) {
	    	Pattern digitPattern = Pattern.compile("[0-9]");
	    	if(!title.getText().isEmpty() && !id.getText().isEmpty() && !author.getText().isEmpty() && !qt.getText().isEmpty() && !FoodRegion.getText().isEmpty() && !foodType.getText().isEmpty() && !description.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(qt.getText());
	    		if(m.find()) {
	    		cookBook = new CookBook(id.getText(),title.getText(),author.getText(),description.getText(),Integer.parseInt(qt.getText()),foodType.getText(),FoodRegion.getText());

	    		if(this.CheckCook(cookBook)==0) {
	    		try {
						Parent  parent = FXMLLoader.load(getClass().getResource("FXML/Type.FXML"));
						Scene main = new Scene(parent);
						Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
						window.setScene(main);
						window.show();
					
					} catch (IOException e) {
						e.printStackTrace();
					}
	    		}else {
	    			 AlertController.error("Books exists");
	    			 id.setText("");
	    		}
	    		}else {
	    			AlertController.error("The quantity field must be a digit");
	    			qt.setText("");
	    		}
		    				
	    		}else {
	    			AlertController.error("You must fill all the fields");
	    		
	    		}		
	    }
	    
	    @FXML
	    void goBack(MouseEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/AddBook.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.show();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			Image backo = new Image("/images/39-512.png");
			back.setImage(backo);			
			
			
			if(cookBook!=null) {
				id.setText(cookBook.getId());
	    		title.setText(cookBook.getTitle());
	    		author.setText(cookBook.getAuthor());
	    		description.setText(cookBook.getDescription());
	    		qt.setText(""+cookBook.getQt());
	    		FoodRegion.setText(cookBook.getRegion());
	    		foodType.setText(cookBook.getTypee());
			}
			else {
				id.setText("");
	    		title.setText("");
	    		author.setText("");
	    		description.setText("");
	    		qt.setText("");
	    		FoodRegion.setText("");
	    		foodType.setText("");
			}
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

		public static void insertCookBook(CookBook cookBook) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("INSERT INTO cookbook(id,title,author,description,quantity,type,region,type_id) VALUES('"+cookBook.getId()+"','"+cookBook.getTitle()+"','"+cookBook.getAuthor()+"','"+cookBook.getDescription()+"','"+cookBook.getQt()+"','"+cookBook.getTypee()+"','"+cookBook.getRegion()+"','"+cookBook.getType().getId()+"')");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		public static void updateCookBook(CookBook cookBook) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE cookBook SET title='"+cookBook.getTitle()+"',author='"+cookBook.getAuthor()+"',description='"+cookBook.getDescription()+"',quantity='"+cookBook.getQt()+"',type='"+cookBook.getTypee()+"',region='"+cookBook.getRegion()+"',type_id='"+cookBook.getType().getId()+"' WHERE id='"+cookBook.getId()+"'");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		public static void updateQTCookBook(Book b) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE cookbook SET quantity='"+b.getQt()+"' WHERE id='"+b.getId()+"'");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		public static void deleteCookBook(String id) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("DELETE FROM cookbook WHERE id='"+id+"'");
			} catch (SQLException e) {
	 			
				e.printStackTrace();
			}
		}
		
		
		public  int  CheckCook(CookBook c){
			CookBook cook = null;
			int b=0;
			try {
				st=DBconnection.getConnection().createStatement();	
				ResultSet result = st.executeQuery("SELECT * FROM cookbook WHERE id='"+c.getId()+"' ");
				result.beforeFirst();
				if(result.next()) {
		    		 cook = new CookBook(result.getString("id"),result.getString("title"),result.getString("author"),result.getString("description"),result.getInt("quantity"),result.getString("type"),result.getString("region"));
				
				if(c.getId().equals(cook.getId())) {
					b=1;
				}
				}else {
					b=0;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return b;
		}
		
		
}

