package application;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;

import Controller.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.work;

public class workTimes implements Initializable{
	
	private static Statement st;
	
	public static work work=null;
	    @FXML
	    private TableColumn<work,Time> days;

	    @FXML
	    private TableColumn<work,Time> StartTime;
	    @FXML
	    private TableView<work> workTime;
	    @FXML
	    private TableColumn<work,Time> EndTime;
	    
	    @Override
	    public void initialize(URL url,ResourceBundle rb) {
	    	days.setCellValueFactory(new PropertyValueFactory<>("Day"));
			StartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
			EndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
			workTime.setItems(this.getAllwork());
	    }
	    
	    @FXML
	   public void setTime(MouseEvent event) {
			work = workTime.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("");
			alert.setContentText("Are u sure u want to choose this Date: \n"+work.getDay()+"\nStarting at: "+work.getStart()+"\nEnd on: "+work.getEnd());
			alert.showAndWait();
			
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
	    
		public  ObservableList <work> getAllwork(){
			ObservableList <work> works = FXCollections.observableArrayList();
			work work;
			try {
				st=DBconnection.getConnection().createStatement();
				ResultSet result= st.executeQuery("SELECT * FROM work");
				result.beforeFirst();
				while(result.next()) {
					work=new work(result.getInt(1),result.getString(2),result.getTime(3),result.getTime(4));
					works.add(work);		
					}
			System.out.println(works.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return works;
		}
	    
	    
	    
}
	    

