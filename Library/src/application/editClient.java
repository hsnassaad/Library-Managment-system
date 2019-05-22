
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
	import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.Pane;
	import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Person.Client;
import model.Person.Employee;

	public class editClient implements Initializable{

		
		private static Statement st;
		
	    @FXML
	    private AnchorPane anchor;

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
	    private Label Labelusername;

	    @FXML
	    private TextField username;

	    @FXML
	    private Text text;

	    @FXML
	    private Button submit;

	    @FXML
	    private Button show;

	    @FXML
	    private Button backooo;

	    @FXML
	    private ImageView employeeeee;

	    @FXML
	    void showww(ActionEvent event) {
	    	name.setText(showMembers.c1.getName());
			email.setText(showMembers.c1.getEmail());
			phone.setText(""+showMembers.c1.getPhone());
			username.setText(""+showMembers.c1.getPoints());
			
			name.setEditable(true);
			email.setEditable(true);
			phone.setEditable(true);
			username.setEditable(true);
	    }

	    @FXML
	    void goBack(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("No data will bee saved");
			alert.setHeaderText("");
			alert.showAndWait();
			
			if(alert.getResult()==ButtonType.OK) {
	    	
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Home");
				window.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    }

	    @FXML
	    void saveEmployee(ActionEvent event) {
	    	Pattern digitPattern = Pattern.compile("[0-9]{8}");
	    	Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

	    	if(!name.getText().isEmpty() &&  !email.getText().isEmpty() && !phone.getText().isEmpty() && !username.getText().isEmpty()) {
	    		Matcher m = emailPattern.matcher(email.getText());
	    		if(m.find()) {
	    			Matcher m2 = digitPattern.matcher(phone.getText());
	    			if(m2.find()) {
	    	showMembers.c1.setName(name.getText());
	    	showMembers.c1.setEmail(email.getText());
	    	showMembers.c1.setPhone(Integer.parseInt(phone.getText()));
	    	showMembers.c1.setPoints(Integer.parseInt(username.getText()));
  	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure?");
			alert.setHeaderText("");
			alert.showAndWait();
			
			if(alert.getResult()==ButtonType.OK) {
	    	System.out.println(showMembers.c1);
	    	this.updateClient(showMembers.c1);
	    	
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Home");
				window.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	
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
			name.setPromptText(showMembers.c1.getName());
			email.setPromptText(showMembers.c1.getEmail());
			phone.setPromptText(""+showMembers.c1.getPhone());
			username.setPromptText(""+showMembers.c1.getPoints());
			
			name.setEditable(false);
			email.setEditable(false);
			phone.setEditable(false);
			username.setEditable(false);			
		}

		
		public void updateClient(Client client) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE client SET name='"+client.getName()+"', phone='"+client.getPhone()+"', email='"+client.getEmail()+"',point='"+client.getPoints()+"'   where id='"+client.getId()+"'");
				System.out.println(client+" Has been Updated");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		
	}


