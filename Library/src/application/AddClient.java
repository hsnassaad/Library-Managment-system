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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person.Client;

public class AddClient  implements Initializable{

	private static Statement st;

	    @FXML
	    private ImageView back;

	    @FXML
	    private Pane pane;

	    @FXML
	    private Label labelName;

	    @FXML
	    private TextField name;

	    @FXML
	    private Label labelEmail;

	    @FXML
	    private TextField email;

	    @FXML
	    private Label labelPhone;

	    @FXML
	    private TextField phone;

	    @FXML
	    private Text text;

	    @FXML
	    private Button submit;

	    @FXML
	    private Button cancel;

	    @FXML
	    private ImageView clientt;

	    @FXML
	    void backToHome(MouseEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Home");
				window.show();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void cancel(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setContentText("Are you sure you want to cancel ?");
	    	alert.setHeaderText("");
	    	alert.showAndWait();
	    	if(alert.getResult() == ButtonType.OK) {
	    	name.setText("");
	    	email.setText("");
	    	phone.setText("");
	    	}
	    	
	    }

	    @FXML
	    void saveClient(ActionEvent event) {
	    	Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
	    	Pattern digitPattern = Pattern.compile("[0-9]{8}");
	    	if(!name.getText().isEmpty() &&  !email.getText().isEmpty() && !phone.getText().isEmpty()) {
	    		Matcher m =  emailPattern.matcher(email.getText());
	    		if(m.find()) {
	    			
	    			m = digitPattern.matcher(phone.getText());
	    			if(m.find()) {
	    			Client client = new Client(name.getText(),Integer.parseInt(phone.getText()),email.getText());
	    			if(this.CheckClient(client)==0) {
	    			this.insertClient(client);
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("");
	    			alert.setHeaderText("");
	    			alert.setContentText("Client has been added successfully");
	    			alert.showAndWait();
	    			
	    			if(alert.getResult()==ButtonType.OK) {
	    				name.setText("");
	    		    	email.setText("");
	    		    	phone.setText("");
	    		    	
	    			}
	    			
	    			}else {
	    				AlertController.error("Client exist");
	    			}
	    			
	    			}else {
	    				AlertController.error("Invalid phone number");
	    			}
	    		}else {
	    			
	    			AlertController.error("Invalid email");
	    		}
	    		
	    		
	    		
	    	}else {
	    		AlertController.error("You must fill all the fields");
	    	}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image backo = new Image("/images/Action___States_-_Vol._3-19-512.png");
			Image Person = new Image("/images/man-icon.png");		
			back.setImage(backo);
			clientt.setImage(Person);
			
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
		
		public  void insertClient(Client client) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("INSERT INTO client(name,phone,email) VALUES('"+client.getName()+"','"+client.getPhone()+"','"+client.getEmail()+"')");
				System.out.println(client.getName()+" Has been inserted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		
		public  int  CheckClient(Client c){
			Client client = null;
			int b=0;
			try {
				st=DBconnection.getConnection().createStatement();	
				ResultSet result = st.executeQuery("SELECT * FROM client WHERE email='"+c.getEmail()+"' ");
				result.beforeFirst();
				if(result.next()) {
				client = new Client(result.getString(2),result.getInt(3),result.getString(4),result.getInt(1));	
				client.setPoints(result.getInt("point"));
				
				if(c.getEmail().equals(client.getEmail()) || c.getPhone()==client.getPhone()) {
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
