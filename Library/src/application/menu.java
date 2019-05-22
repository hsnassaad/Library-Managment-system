package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;
import java.util.ResourceBundle;

import Controller.AlertController;
import Controller.DBconnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.tabless.notif;

public class menu implements Initializable{

	public static notif n;
	
	private static Statement st;

	    @FXML
	    private Button addMember;

	    @FXML
	    private MenuItem close;
	    

	    @FXML
	    private MenuItem about;

	    
	    @FXML
	    private ImageView member;

	    @FXML
	    private Button addBook;

	    @FXML
	    private ImageView book;

	    @FXML
	    private Button viewMembers;

	    @FXML
	    private ImageView members;

	    
	    @FXML
	    private MenuBar menuBar;

	    @FXML
	    private MenuItem editt;
	    
	    
	    
	    @FXML
	    private Button viewBooks;

	    @FXML
	    private ImageView books;

	    @FXML
	    private Button Home;

	    @FXML
	    private ImageView home;

	    @FXML
	    private Button logout;

	    @FXML
	    private ImageView lolo;

	    @FXML
	    private Tab bookIssue;

	    @FXML
	    private TextField bookID;

	    @FXML
	    private Text bookName;

	    @FXML
	    private Text author;

	    @FXML
	    private TextField MemberID;

	    @FXML
	    private Text memberName;

	    @FXML
	    private Text phone;

	    @FXML
	    private Button Search;

	    @FXML
	    private Tab submission;

	    @FXML
	    private TextField BookID2;

	    @FXML
	    private ListView<?> list;

	    @FXML
	    private BorderPane borderpane;
	    
	    @FXML
	    private VBox vBox;
	    
	    @FXML
	    private ImageView homeImage;

	    @FXML
	    private ImageView online;

	    @FXML
	    private Text empName;
	    
	    @FXML
	    private Button delete;

	    @FXML
	    private Button seen;
	    
	    @FXML
	    private TableView<notif> table;

	    @FXML
	    private TableColumn<notif, Integer> id;

	    @FXML
	    private TableColumn<notif, String> content;
	    
	    @FXML
	    void deleteNotif(ActionEvent event) {
	    	if(table.getSelectionModel().getSelectedItem() !=null) {
	    		this.DeleteNotif(table.getSelectionModel().getSelectedItem());
	    	}
	    	
	    	defineTable();
	    }
	    
	    @FXML
	    void updateSeen(ActionEvent event) {
	    	if(table.getSelectionModel().getSelectedItem() !=null) {
	    		this.updateNotif(table.getSelectionModel().getSelectedItem());
	    	}
	    	defineTable();
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			Image Member = new Image("/images/matt-icons_contact-add.png");
			Image  Book= new Image("/images/add-book-8-767535.png");
			Image  Members= new Image("/images/members-icon.png");
			Image  Books= new Image("/images/Icons-07-512.png");
			Image  Homee= new Image("/images/img_231974.png");
			Image  Lolo= new Image("/images/logging-out-2355227_960_720.png");
			Image  toto= new Image("/images/book-1328584_960_720.png");
			Image  onle= new Image("/images/2000px-Green_sphere.svg.png");
			member.setImage(Member);
			book.setImage(Book);
			members.setImage(Members);
			books.setImage(Books);
			home.setImage(Homee);
			lolo.setImage(Lolo);
			online.setImage(onle);
			homeImage.setImage(toto);
			empName.setText(login.employee.getUsername());
			
			defineTable();
		}
	    

	    @FXML
	    void editoo(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/editProfile.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage) menuBar.getScene().getWindow();
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
	    void addBook(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/AddBook.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Add Book");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

	    @FXML
	    void addEmployee(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/Employee.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Add Employee");
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

//	    @FXML
//	    void search(ActionEvent event) {
//	    	loadPage("");
//	    }
	    
	    
	    public void defineTable() {
	    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	content.setCellValueFactory(new PropertyValueFactory<>("mess"));
	    	
	    	table.setItems(this.getNotification());
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

	    @FXML
	    void viewMembers(ActionEvent event) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/viewMembers.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Members List");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	    void closeApp(ActionEvent event) {
	    	 Platform.exit();
	    }
	    
	    @FXML
	    void abouta(ActionEvent event) {
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
	    
	    
	    
	    
	    
	    // Data Base Functions
	    
		public ObservableList <notif> getNotification(){
			ObservableList <notif> notifs= FXCollections.observableArrayList();
		
			try {
				st=DBconnection.getConnection().createStatement();
				notif Notif;
				ResultSet result = st.executeQuery("SELECT * FROM notif where seen=1");
				result.beforeFirst();
				while(result.next()) 
					{
					Notif = new notif(result.getInt("id"),result.getString(2));
					notifs.add(Notif);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(notifs);
			
			return notifs;
		
		}

		
		public  void updateNotif(notif n) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE notif SET seen=0 where id='"+n.getId()+"'");
				System.out.println(n+" Has been Updated");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		public  void DeleteNotif(notif n) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("Delete FROM notif where id='"+n.getId()+"'");
				System.out.println(n+" Has been deleted");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		

	}

