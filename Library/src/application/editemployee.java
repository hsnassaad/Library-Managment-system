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
import javafx.scene.control.Hyperlink;
	import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
	import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.Pane;
	import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person.Employee;

	public class editemployee implements Initializable{

		
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
	    private Label LabelPassword;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Label labelSalary;

	    @FXML
	    private TextField salary;

	    @FXML
	    private Label labelWork;

	    @FXML
	    private Label workati;

	    @FXML
	    private Hyperlink change;

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
	    void change(ActionEvent event) {
	    	change.setVisited(false);
	    	
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("FXML/WorkTimes.FXML"));
				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setTitle("Work Times");
				stage.setScene(new Scene(parent));
				stage.show();
				
				stage.setOnCloseRequest( e ->
				    {
				    	
					   workati.setText("Day: "+workTimes.work.getDay()+" start: "+workTimes.work.getStart()+" End: "+workTimes.work.getEnd());
				       Alert alert = new Alert(Alert.AlertType.INFORMATION);
				       alert.setHeaderText("");
				       alert.setContentText("Your choice will be added to your profile");
				       alert.show();
				       stage.close();
				    });
				
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    	
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

	    	if(!name.getText().isEmpty() &&  !email.getText().isEmpty() && !phone.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty()) {
	    		Matcher m = emailPattern.matcher(email.getText());
	    		if(m.find()) {
	    			Matcher m2 = digitPattern.matcher(phone.getText());
	    			if(m2.find()) {
	    	showMembers.e1.setName(name.getText());
	    	showMembers.e1.setEmail(email.getText());
	    	showMembers.e1.setPhone(Integer.parseInt(phone.getText()));
	    	showMembers.e1.setUsername(username.getText());
	    	showMembers.e1.setPassword(password.getText());
	    	showMembers.e1.setSalary(Integer.parseInt(salary.getText()));
	    	
	    	if(workTimes.work!=null) {
	    	showMembers.e1.setWork_id(workTimes.work);
	    	}
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Are you sure?");
			alert.setHeaderText("");
			alert.showAndWait();
			
			if(alert.getResult()==ButtonType.OK) {
	    	System.out.println(showMembers.e1);
	    	this.updateEmp(showMembers.e1);
	    	
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

	    @FXML
	    void showww(ActionEvent event) {
	    	name.setText(showMembers.e1.getName());
			email.setText(showMembers.e1.getEmail());
			phone.setText(""+showMembers.e1.getPhone());
			username.setText(showMembers.e1.getUsername());
			password.setText(showMembers.e1.getPassword());
			salary.setText(""+showMembers.e1.getSalary());
			workati.setText("Day: "+showMembers.e1.getWork_id().getDay()+" start: "+showMembers.e1.getWork_id().getStart()+" End: "+showMembers.e1.getWork_id().getEnd());
			
			name.setEditable(true);
			email.setEditable(true);
			phone.setEditable(true);
			username.setEditable(true);
			password.setEditable(true);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			name.setPromptText(showMembers.e1.getName());
			email.setPromptText(showMembers.e1.getEmail());
			phone.setPromptText(""+showMembers.e1.getPhone());
			username.setPromptText(showMembers.e1.getUsername());
			password.setPromptText(showMembers.e1.getPassword());
			salary.setPromptText(""+showMembers.e1.getSalary());
			workati.setText("Day: "+showMembers.e1.getWork_id().getDay()+" start: "+showMembers.e1.getWork_id().getStart()+" End: "+showMembers.e1.getWork_id().getEnd());
			
			name.setEditable(false);
			email.setEditable(false);
			phone.setEditable(false);
			username.setEditable(false);
			password.setEditable(false);
		}

		
		public void updateEmp(Employee emp) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE employee SET name='"+emp.getName()+"', phone='"+emp.getPhone()+"', email='"+emp.getEmail()+"', username='"+emp.getUsername()+"', pass='"+emp.getPassword()+"', work_id='"+emp.getWork_id().getId()+"', salary='"+emp.getSalary()+"' where id='"+emp.getId()+"'");
				System.out.println(emp+" Has been Updated");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		
	}

