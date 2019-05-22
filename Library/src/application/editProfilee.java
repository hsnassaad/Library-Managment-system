
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.Pane;
	import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.work;
import model.Person.Employee;

	public class editProfilee implements Initializable{

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
	    private Text text;

	    @FXML
	    private Button submit;

	    @FXML
	    private Button cancel;

	    @FXML
	    private ImageView employeeeee;
	    
	    @FXML
	    private Button backooo;
	    

	    @FXML
	    void goBack(ActionEvent event) {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("No data will bee saved");
			alert.setHeaderText("");
			alert.showAndWait();
			
			if(alert.getResult()==ButtonType.OK) {
	    	
				if(login.employee.getId()==1 || login.employee.getId()==2) {
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
				
				else {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.fxml"));
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
	    }
	    
	    
	    @FXML
	    void cancel(ActionEvent event) {
			name.setText(login.employee.getName());
			email.setText(login.employee.getEmail());
			phone.setText(""+login.employee.getPhone());
			username.setText(login.employee.getUsername());
			password.setText(login.employee.getPassword());
			
			name.setEditable(true);
			email.setEditable(true);
			phone.setEditable(true);
			username.setEditable(true);
			password.setEditable(true);
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
	    			
	    				Alert alert = new Alert(AlertType.CONFIRMATION);
	    				alert.setContentText("Are you sure?");
	    				alert.setHeaderText("");
	    				alert.showAndWait();
	    				
	    				if(alert.getResult()==ButtonType.OK) {
	    					
	    					login.employee.setName(name.getText());
	    					login.employee.setEmail(email.getText());
	    					login.employee.setPhone(Integer.parseInt(phone.getText()));
	    					login.employee.setUsername(username.getText());
	    					login.employee.setPassword(password.getText());
	    					
	    					
	    					this.updateEmp(login.employee);
	    					
	    					try {
	    				    	Parent root =FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.fxml"));
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
			Image Person = new Image("/images/repair-3-512.png");
			employeeeee.setImage(Person);
			
			name.setPromptText(login.employee.getName());
			email.setPromptText(login.employee.getEmail());
			phone.setPromptText(""+login.employee.getPhone());
			username.setPromptText(login.employee.getUsername());
			password.setPromptText(login.employee.getPassword());
						
			name.setEditable(false);
			email.setEditable(false);
			phone.setEditable(false);
			username.setEditable(false);
			password.setEditable(false);
			
			
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
		
		
		public void updateEmp(Employee emp) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE employee SET name='"+emp.getName()+"' , phone='"+emp.getPhone()+"' , email='"+emp.getEmail()+"' , username='"+emp.getUsername()+"' , pass='"+emp.getPassword()+"' , work_id='"+emp.getWork_id().getId()+"' , salary='"+emp.getSalary()+"' where id='"+login.employee.getId()+"'");
				System.out.println(emp+" Has been Updated");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}	
		
	}

