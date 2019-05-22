package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Controller.AlertController;
import Controller.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.work;
import model.Person.Employee;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class login implements Initializable{

	public static Employee employee=null; 
	
	private static Statement st;

	    @FXML
	    private TextField username;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Button login;
	    
	    @FXML
	    private ImageView lock;

	    @FXML
	    private ImageView person;

	    @FXML
	    private ImageView key;
	    

	    @FXML
	    void logIN(ActionEvent event) {
	    	
	    	if(!username.getText().isEmpty() && !password.getText().isEmpty()) {
	    		
	    		if((employee =this.getEmployee(username.getText(), password.getText())) != null) {
	    			System.out.println(employee);
	    			if(employee.getId()==1 || employee.getId()==2) {
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
							Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
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
	    		}
	    		else {
	    			AlertController.information("User not found!");
		    	    username.getStyleClass().add("wrong_inputs");
		    	    password.getStyleClass().add("wrong_inputs");
	    		}
	    		
	    	}
	    	else {
	    	AlertController.information("Please enter your username and password");
	    	}
	    	
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			Image lockIcon = new Image("/images/Lock-icon.png");
//			Image Person = new Image("/images/538642-user_512x512.png");
			//Image Key = new Image("/images/key-icon.png");
			lock.setImage(lockIcon);
//			person.setImage(Person);
			//key.setImage(Key);
		}
				
		public Employee getEmployee(String username,String password){
			Employee employee = null;
			work work;
			try {
				
				st=DBconnection.getConnection().createStatement();
				ResultSet result= st.executeQuery("SELECT * FROM employee,work where username='"+username+"' AND pass='"+password+"' AND employee.work_id=work.id");
				result.beforeFirst();
				if(result.next()) {
					work = new work(result.getInt(9),result.getString(10),result.getTime(11),result.getTime(12));
					//System.out.println(result.getInt(8));
					employee = new Employee(result.getString(2),result.getInt(3),result.getString(4),result.getString(5),result.getString(6),work,result.getInt(8),result.getInt(1));	
					//System.out.println(result.getString(2)+" , "+result.getString(3)+" , "+result.getString(4)+" , "+result.getString(5));
				}else {
					System.out.println("No user found");
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(employee);
			return employee;
		}
		
	
}
