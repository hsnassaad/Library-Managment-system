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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Books.Book;
import model.Books.Roman;




public class AddRoman implements Initializable{
	
	 private static Statement st;

	public static Roman roman;
	
		@FXML
	    private AnchorPane anchorPane;
		
		 @FXML
		private StackPane stackPane;
	    
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
	    private TextField age;

	    @FXML
	    private TextArea summary;

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
	    		age.setText("");
	    		summary.setText("");
	    	}
	    	
	    }

	    
	    @FXML
	    void GoToNext(ActionEvent event) {
	    	Pattern digitPattern = Pattern.compile("[0-9]"); 
	    	//Pattern agePattern = Pattern.compile("[0-9]{0,3}"); 
	    	if(!title.getText().isEmpty() && !id.getText().isEmpty() && !author.getText().isEmpty() && !qt.getText().isEmpty() && !age.getText().isEmpty() && !summary.getText().isEmpty() && !description.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(qt.getText());
	    		if(m.find()) {
	    			m = digitPattern.matcher(age.getText());
	    			if(m.find()) {
		    		if(Integer.parseInt(age.getText())>7 && Integer.parseInt(age.getText())<200) {
		    		 roman = new Roman(id.getText(),title.getText(),author.getText(),description.getText(),Integer.parseInt(qt.getText()),summary.getText(),Integer.parseInt(age.getText()));
		    		
		    		 if(this.CheckRoman(roman)==0) {
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
		    			AlertController.error("You must be between 7 or 200");
		    			
		    		}
		    		}else {
		    			AlertController.information("Invalid age !!!");
		    			age.setText("");
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
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image backo = new Image("/images/39-512.png");
			back.setImage(backo);			
			
			if(roman!=null) {
				id.setText(roman.getId());
	    		title.setText(roman.getTitle());
	    		author.setText(roman.getAuthor());
	    		description.setText(roman.getDescription());
	    		qt.setText(""+roman.getQt());
	    		age.setText(""+roman.getAge());
	    		summary.setText(roman.getSummary());
			}else {
				id.setText("");
	    		title.setText("");
	    		author.setText("");
	    		description.setText("");
	    		qt.setText("");
	    		age.setText("");
	    		summary.setText("");
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

		public static void insertRoman(Roman roman) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("INSERT INTO roman(id,title,author,description,quantity,summary,age,type_id) VALUES('"+roman.getId()+"','"+roman.getTitle()+"','"+roman.getAuthor()+"','"+roman.getDescription()+"','"+roman.getQt()+"','"+roman.getSummary()+"','"+roman.getAge()+"','"+roman.getType().getId()+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void updateRoman(Roman roman) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE roman SET title='"+roman.getTitle()+"',author='"+roman.getAuthor()+"',description='"+roman.getDescription()+"',quantity='"+roman.getQt()+"',summary='"+roman.getSummary()+"',age='"+roman.getAge()+"',type_id='"+roman.getType().getId()+"' WHERE id='"+roman.getId()+"'");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		public static void updateQTRoman(Book b) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE roman SET quantity='"+b.getQt()+"' WHERE id='"+b.getId()+"'");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		public static void deleteRoman(String id) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("DELETE FROM roman WHERE `roman`.`id`= '"+id+"'");
			} catch (SQLException e) {
	 			
				e.printStackTrace();
			}
		}
		
		public  int  CheckRoman(Roman r){
			Roman roman = null;
			int b=0;
			try {
				st=DBconnection.getConnection().createStatement();	
				ResultSet result = st.executeQuery("SELECT * FROM roman WHERE id='"+r.getId()+"' ");
				result.beforeFirst();
				if(result.next()) {
		    		 roman = new Roman(result.getString("id"),result.getString("title"),result.getString("author"),result.getString("description"),result.getInt("quantity"),result.getString("summary"),result.getInt("age"));
				
				if(r.getId().equals(roman.getId())) {
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
