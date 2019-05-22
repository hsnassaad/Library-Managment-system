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
import model.Books.Education;





public class AddEducation implements Initializable{

	public static Education education;
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
	    private TextField subject;

	    @FXML
	    private TextField AcademicLevel;

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
	    		subject.setText("");
	    		AcademicLevel.setText("");
	    	}
	    }

	    @FXML
	    void GoToNext(ActionEvent event) {
	    	Pattern digitPattern = Pattern.compile("[0-9]"); 
	    	//Pattern agePattern = Pattern.compile("[0-9]{0,3}"); 
	    	if(!title.getText().isEmpty() && !id.getText().isEmpty() && !author.getText().isEmpty() && !qt.getText().isEmpty() && !subject.getText().isEmpty() && !AcademicLevel.getText().isEmpty() && !description.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(qt.getText());
	    		if(m.find()) {
		    		
		    			education = new Education(id.getText(),title.getText(),author.getText(),description.getText(),Integer.parseInt(qt.getText()),subject.getText(),AcademicLevel.getText());
		    		
		    			if(CheckEducation(education)==0) {
		    			
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
	    	AlertController.error("U must fill all the fields");
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
			
			if(education!=null) {
				id.setText(education.getId());
	    		title.setText(education.getTitle());
	    		author.setText(education.getAuthor());
	    		description.setText(education.getDescription());
	    		qt.setText(""+education.getQt());
	    		subject.setText(education.getSubject());
	    		AcademicLevel.setText(education.getAcademicLvl());
			}
			else {
				id.setText("");
	    		title.setText("");
	    		author.setText("");
	    		description.setText("");
	    		qt.setText("");
	    		subject.setText("");
	    		AcademicLevel.setText("");
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

		public static void insertEducation(Education education) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("INSERT INTO education(id,title,author,description,quantity,subject,academicLvl,type_id) VALUES('"+education.getId()+"','"+education.getTitle()+"','"+education.getAuthor()+"','"+education.getDescription()+"','"+education.getQt()+"','"+education.getSubject()+"','"+education.getAcademicLvl()+"','"+education.getType().getId()+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void updateEducation(Education education) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE education SET title='"+education.getTitle()+"',author='"+education.getAuthor()+"',description='"+education.getDescription()+"',quantity='"+education.getQt()+"',subject='"+education.getSubject()+"',academicLvl='"+education.getAcademicLvl()+"',type_id='"+education.getType().getId()+"' WHERE id='"+education.getId()+"'");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		public static void updateQTEducation(Book b) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE education SET quantity='"+b.getQt()+"' WHERE id='"+b.getId()+"'");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		
		public static void deleteEducation(String id) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("DELETE FROM education WHERE id='"+id+"'");
			} catch (SQLException e) {
	 			
				e.printStackTrace();
			}
		}
		
		public  int  CheckEducation(Education edu){
			Education ed = null;
			int b=0;
			try {
				st=DBconnection.getConnection().createStatement();	
				ResultSet result = st.executeQuery("SELECT * FROM education WHERE id='"+edu.getId()+"' ");
				result.beforeFirst();
				if(result.next()) {
		    		 ed = new Education(result.getString("id"),result.getString("title"),result.getString("author"),result.getString("description"),result.getInt("quantity"),result.getString("subject"),result.getString("academicLvl"));
				
				if(edu.getId().equals(ed.getId())) {
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
