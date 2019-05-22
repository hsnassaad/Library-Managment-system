package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Controller.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.work;
import model.Person.Client;
import model.Person.Employee;


public class showMembers implements Initializable{

	private static Statement st;
	public static Client c1;
	public static Employee e1;
	
    @FXML
    private TableView<Employee> EmpTable;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, Integer> phone;

    @FXML
    private TableColumn<Employee, String> email;

    @FXML
    private TableColumn<Employee, String> wt;

    @FXML
    private TableColumn<Employee, Integer> salary;

    @FXML
    private TableView<Client> ClientTable;

    @FXML
    private TableColumn<Client, Integer> idClient;

    @FXML
    private TableColumn<Client, String> nameClient;

    @FXML
    private TableColumn<Client, Integer> phoneClient;

    @FXML
    private TableColumn<Client, String> emailClient;

    @FXML
    private TableColumn<Client, Integer> points;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    @FXML
    private ImageView editt;
    
    @FXML
    private ImageView deletee;
    
    @FXML
    private ImageView backatiii;
    
    
    @FXML
    void BackAction(ActionEvent event) {
    	c1 = null;
    	e1 = null;
    	
    	try {
			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/main.FXML"));
			Scene main = new Scene(parent);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setTitle("Home");
			window.setScene(main);
			window.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    
    @FXML
    void deleteAction(ActionEvent event) {
    	
    	if(ClientTable.getSelectionModel().getSelectedItem() !=null) {
    		c1 = ClientTable.getSelectionModel().getSelectedItem();
    		DeleteClient(c1);
    		ClientTable.setItems(this.getClients());
    	}
    	
    			if(EmpTable.getSelectionModel().getSelectedItem()!=null) {
    				e1=EmpTable.getSelectionModel().getSelectedItem();
    				DeleteEmp(e1);
    				EmpTable.setItems(this.getEmployee());
    			}
    }

    
    
    @FXML
    void editAction(ActionEvent event) {
    	
    	if(ClientTable.getSelectionModel().getSelectedItem()!=null) {
    		c1 = ClientTable.getSelectionModel().getSelectedItem();	
    		try {
    			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/editClient.FXML"));
    			Scene main = new Scene(parent);
    			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    			window.setTitle("Home");
    			window.setScene(main);
    			window.show();
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    			if(EmpTable.getSelectionModel().getSelectedItem()!=null) {
    				e1=EmpTable.getSelectionModel().getSelectedItem();
    				try {
    					Parent  parent = FXMLLoader.load(getClass().getResource("FXML/editemployee.FXML"));
    					Scene main = new Scene(parent);
    					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    					window.setTitle("Home");
    					window.setScene(main);
    					window.show();
    					
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	Image ed = new Image("/images/JD-23-512.png");
    	Image del = new Image("/images/JD-22-512.png");
    	Image ba = new Image("/images/arrow_18-512.png");
    	
    	editt.setImage(ed);
    	deletee.setImage(del);
    	backatiii.setImage(ba);
    	
		defineTableClient();
		defineTableEmployee();
	}
    
	
	
    public void defineTableClient() {
    	idClient.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameClient.setCellValueFactory(new PropertyValueFactory<>("name"));
    	phoneClient.setCellValueFactory(new PropertyValueFactory<>("phone"));
    	emailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
    	points.setCellValueFactory(new PropertyValueFactory<>("points"));
    	ClientTable.setItems(this.getClients());
    }
    
    public void defineTableEmployee() {
     	id.setCellValueFactory(new PropertyValueFactory<>("id"));
     	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    	email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	wt.setCellValueFactory(new PropertyValueFactory<>("workk"));
    	salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
		EmpTable.setItems(this.getEmployee());
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

	public  ObservableList <Client> getClients(){
		ObservableList <Client> clients= FXCollections.observableArrayList();
	
		try {
			
			st=DBconnection.getConnection().createStatement();
			Client client;
			ResultSet result = st.executeQuery("SELECT * FROM client");
			result.beforeFirst();
			while(result.next()) 
				{
				client = new Client(result.getString(2),result.getInt(3),result.getString(4),result.getInt(1));
				client.setPoints(result.getInt("point"));
				clients.add(client);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(clients);
		
		return clients;
	
	}

	public  ObservableList <Employee> getEmployee(){
		ObservableList <Employee> employees= FXCollections.observableArrayList();
	
		try {
			
			st=DBconnection.getConnection().createStatement();
			Employee employee;
			work work;
			String wo;
			ResultSet result = st.executeQuery("SELECT * FROM employee,work WHERE employee.work_id=work.id");
			result.beforeFirst();
			while(result.next()) 
				{
				work = new work(result.getInt(9),result.getString(10),result.getTime(11),result.getTime(12));
				wo = result.getString("Day");
				employee = new Employee(result.getString(2),result.getInt(3),result.getString(4),result.getString(5),result.getString(6),work,result.getInt(8),result.getInt(1));	
				employee.setWorkk(wo);
				employees.add(employee);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(employees);
		
		return employees;
	}
    
	
	public void DeleteClient(Client c) {
		try {
			st=DBconnection.getConnection().createStatement();
			st.executeUpdate("Delete FROM client where id='"+c.getId()+"'");
			System.out.println(c+" Has been deleted");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	public void DeleteEmp(Employee e2) {
		try {
			st=DBconnection.getConnection().createStatement();
			st.executeUpdate("Delete FROM employee where id='"+e2.getId()+"'");
			System.out.println(e2+" Has been deleted");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
}
