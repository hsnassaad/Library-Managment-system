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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person.Employee;


public class AddEmployee implements Initializable{

	
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
	    private Label labelWork;

	    @FXML
	    private Button work;

	    @FXML
	    private Label labelSalary;

	    @FXML
	    private TextField salary;

	    @FXML
	    private Text text;

	    @FXML
	    private Button submit;

	    @FXML
	    private Button cancel;

	    @FXML
	    private ImageView back;
	    
	    @FXML
	    private ImageView employeeeee;

	    @FXML
	    void backToHome(MouseEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/main.FXML"));
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
	    	username.setText("");
	    	password.setText("");
	    	salary.setText("");
	    	}
	    }

	    @FXML
	    void saveEmployee(ActionEvent event) {
	    	Pattern digitPattern = Pattern.compile("[0-9]{8}");
	    	Pattern digitoPattern = Pattern.compile("[0-9]");
	    	Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

	    	if(!name.getText().isEmpty() &&  !email.getText().isEmpty() && !phone.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty() && !salary.getText().isEmpty() && workTimes.work!=null) {
	    		Matcher m = emailPattern.matcher(email.getText());
	    		if(m.find()) {
	    			Matcher m2 = digitPattern.matcher(phone.getText());
	    			if(m2.find()) {
	    				
	    				m = digitoPattern.matcher(salary.getText());
		    			if(m.find()) {
	    				Employee emp = new Employee(name.getText(),Integer.parseInt(phone.getText()),email.getText(),username.getText(),password.getText(),workTimes.work,Integer.parseInt(salary.getText()));
	    				
	    				if(this.CheckEmployee(emp)==0) {
	    				this.insertEmployee(emp);
	    				
	    				Alert alert = new Alert(AlertType.INFORMATION);
		    			alert.setTitle("");
		    			alert.setHeaderText("");
		    			alert.setContentText("An employee has been added successfully");
		    			alert.showAndWait();
		    			
		    			if(alert.getResult()==ButtonType.OK) {
		    				name.setText("");
		    		    	email.setText("");
		    		    	phone.setText("");
		    		    	username.setText("");
		    		    	password.setText("");
		    		    	salary.setText("");
		    		    	
		    			}
	    				}else {
	    					AlertController.error("Employee exist");
	    				}
		    			}else {
		    				AlertController.error("Salary field must me an integer");
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
	    void showWorkTimes(ActionEvent event) {
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("FXML/WorkTimes.FXML"));
				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setTitle("Work Times");
				stage.setScene(new Scene(parent));
				stage.show();
				stage.setOnCloseRequest( e ->
				    {
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

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image backo = new Image("/images/Action___States_-_Vol._3-19-512.png");
			Image Person = new Image("/images/1120619.png");		
			back.setImage(backo);
			employeeeee.setImage(Person);
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
		
		public  void insertEmployee(Employee employee) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("INSERT INTO employee(name,phone,email,username,pass,work_id,salary) VALUES('"+employee.getName()+"','"+employee.getPhone()+"','"+employee.getEmail()+"','"+employee.getUsername()+"','"+employee.getPassword()+"','"+employee.getWork_id().getId()+"','"+employee.getSalary()+"')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		public  int  CheckEmployee(Employee emp){
			Employee employee = null;
			int b=0;
			try {
				st=DBconnection.getConnection().createStatement();	
				ResultSet result= st.executeQuery("SELECT * FROM employee where username='"+emp.getUsername()+"' OR email='"+emp.getEmail()+"'");
				result.beforeFirst();
				if(result.next()) {
					employee = new Employee(result.getString(2),result.getInt(3),result.getString(4),result.getString(5),result.getString(6),null,result.getInt(8),result.getInt(1));					
				if(emp.getEmail().equals(employee.getEmail()) || emp.getPhone()==employee.getPhone() || emp.getUsername().equals(employee.getUsername())) {
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

