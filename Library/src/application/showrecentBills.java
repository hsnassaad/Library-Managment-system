
	package application;

	import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
import javafx.stage.Stage;
import model.tabless.AllRentEducation;
import model.tabless.showBillsss;

	public class showrecentBills implements Initializable{

		
		private static Statement st;
		
	    @FXML
	    private TableView<showBillsss> billsTable;

	    @FXML
	    private TableColumn<showBillsss, Integer> id;

	    @FXML
	    private TableColumn<showBillsss, String> empName;

	    @FXML
	    private TableColumn<showBillsss, String> clentName;

	    @FXML
	    private TableColumn<showBillsss, Double> total;

	    @FXML
	    private TableColumn<showBillsss, Date> datee;
	    
	    @FXML
	    private Button back;

	    @FXML
	    void backk(ActionEvent event) {
	    	if(login.employee.getId()==1 || login.employee.getId()==2) {
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
		    	
		    	else {
		    		try {
						Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
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
			defineTable();
		}
		
		public void defineTable() {
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
			clentName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
			total.setCellValueFactory(new PropertyValueFactory<>("total"));
			datee.setCellValueFactory(new PropertyValueFactory<>("date"));
			billsTable.setItems(this.getBills());
		}

		
		
public  ObservableList <showBillsss> getBills(){
			
			ObservableList <showBillsss> bills= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				showBillsss bill;

				ResultSet result = st.executeQuery("SELECT * FROM employee,client,bill WHERE bill.emp_id=employee.id AND bill.client_id=client.id");
				result.beforeFirst();
				
				while(result.next()) {
					bill = new showBillsss(result.getInt("bill.id"),result.getString("employee.name"),result.getString("client.name"),result.getDouble("total"),result.getDate("created_at"));
					bills.add(bill);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(bills);
			
			return bills;
		}
		
		
	}

