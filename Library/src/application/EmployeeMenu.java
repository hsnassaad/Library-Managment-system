
	package application;

	import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AlertController;
import javafx.application.Platform;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
	import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.VBox;
	import javafx.scene.text.Text;
import javafx.stage.Stage;

	public class EmployeeMenu implements Initializable{

		Transaction trans=new Transaction();
		
	    @FXML
	    private BorderPane borderpane;

	    @FXML
	    private VBox vBox;
	    
	    @FXML
	    private MenuItem close;

	    @FXML
	    private MenuItem about;

	    @FXML
	    private Button addMember;

	    @FXML
	    private ImageView member;

	    @FXML
	    private Button transaction;

	    @FXML
	    private ImageView book;

	    @FXML
	    private Button viewBooks;

	    @FXML
	    private ImageView books;

	    @FXML
	    private Button Home;

	    @FXML
	    private ImageView home;

	    @FXML
	    private Button bill;

	    @FXML
	    private ImageView members;

	    @FXML
	    private Button logout;

	    @FXML
	    private ImageView lolo;

	    @FXML
	    private Tab bookIssue;

	    @FXML
	    private ImageView logo;

	    @FXML
	    private ImageView online;

	    @FXML
	    private Text empName;

	    @FXML
	    private Tab submission;

	    @FXML
	    private TextField BookID2;

	    @FXML
	    private ListView<?> list;
	    
	    @FXML
	    private MenuBar menuBarr;

	    @FXML
	    private Menu filee;

	    @FXML
	    private Menu editt;

	    @FXML
	    private Menu helpoo;
	    
	    @FXML
	    private MenuItem editProf;
	    

	    @FXML
	    void addClient(ActionEvent event) {
	    	
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/Client.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Add Client");
				window.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    }

	    @FXML
	    void gotransaction(ActionEvent event) {
	    	if(selectClient.client==null) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/selectClient.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Select Client");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    	else {
	    		try {
			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/TransactionPage.fxml"));
					Scene main = new Scene(root);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Transactions");
					window.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	    	}
	    }

	    @FXML
	    void editProfilo(ActionEvent event) {
	    	
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/editProfile.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage) menuBarr.getScene().getWindow();
				//Stage window = new Stage();
				window.setScene(main);
				window.setTitle("Edit Profile");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
	    @FXML
	    void logout(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setContentText("Are you sure?");
	    	alert.setHeaderText("");
	    	alert.setTitle("");
	    	alert.showAndWait();
	    	if(alert.getResult() == ButtonType.OK) {
			Parent parent;
			try {
				parent = FXMLLoader.load(getClass().getResource("FXML/loginPage.FXML"));
				Scene buy = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(buy);
				window.setTitle("login");
				window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	    }

	    @FXML
	    void rt(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/recentBills.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				//Stage window = new Stage();
				window.setScene(main);
				window.setTitle("Recent Bills");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	    @FXML
	    void ShowBill(ActionEvent event) {
	    	
	    	if(trans.getBill() !=null && selectClient.client!=null) {
	    	Parent parent;
	    	try {
				parent = FXMLLoader.load(getClass().getResource("FXML/billPage.FXML"));
				Scene buy = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(buy);
				window.setTitle("Bill");
				window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}else {
	    		AlertController.information("You need to do transactions in order to show the bill ");
	    	}
	    }

	    @FXML
	    void viewBooks(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/viewBooks.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Books List");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			Image Member = new Image("/images/matt-icons_contact-add.png");
			Image  Book= new Image("/images/mony.png");
			Image  Members= new Image("/images/bill-icon-13.jpg");
			Image  Books= new Image("/images/Icons-07-512.png");
			Image  Homee= new Image("/images/img_231974.png");
			Image  Lolo= new Image("/images/276363.png");
			//Image  toto= new Image("/images/19307818.jpg");
			Image  onle= new Image("/images/2000px-Green_sphere.svg.png");
			member.setImage(Member);
			book.setImage(Book);
			members.setImage(Members);
			books.setImage(Books);
			home.setImage(Homee);
			lolo.setImage(Lolo);
			online.setImage(onle);
			//logo.setImage(toto);
			empName.setText(login.employee.getUsername());

		}
		
		
		@FXML
	    void aboutaa(ActionEvent event) {
			try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/about.fxml"));
				Scene main = new Scene(root);
				Stage window = new Stage();
				window.setScene(main);
				window.setTitle("About");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
		
		@FXML
	    void closeApp(ActionEvent event) {
			Platform.exit();
	    }


}
