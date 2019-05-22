package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Controller.AlertController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Books.Book;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Person.Client;
import model.Transaction.Rent;
import model.Types.BookRent;
import model.tabless.clientShow;



public class selectClient implements Initializable{

	private static Statement st;
	public static Client client;
	private Client cliento;
	
	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private TableView<Client> clientTable;

	    @FXML
	    private TableColumn<Client, String> names;

	    @FXML
	    private TableColumn<Client, Integer> phone;
	    
	    @FXML
	    private TableColumn<Client, Integer> points;
	    
	    @FXML
	    private Text texto;

	    @FXML
	    private ImageView backo;

	    @FXML
	    private ImageView nexto;
	    
	    @FXML
	    private Button back;

	    @FXML
	    private Button next;
	    
	    	
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	names.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
	    	points.setCellValueFactory(new PropertyValueFactory<>("points"));
			clientTable.setItems(this.getClients());
			
			Image click = new Image("/images/select-choose-right-person-hr-job-human-resource-512.png");
			Image backi = new Image("/images/39-512.png");
			nexto.setImage(click);
			backo.setImage(backi);
		}

	    

	    @FXML
	    void back(ActionEvent event) {
	    	client = null;
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void next(ActionEvent event) {

	    	if(clientTable.getSelectionModel().getSelectedItem() != null) {
	    		client = clientTable.getSelectionModel().getSelectedItem();
	    		System.out.println(client);
	    		try {
					Parent  parent = FXMLLoader.load(getClass().getResource("FXML/TransactionPage.FXML"));
					Scene main = new Scene(parent);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Transaction");
					window.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
	    		AlertController.error("You need to choose a Client");
	    	}
	    	
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
					insertRentBooksToClient(client);
					clients.add(client);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(clients);
			
			return clients;
		
		}
		
		  public void insertRentBooksToClient(Client c) {
		    	
		    	try {
					st=DBconnection.getConnection().createStatement();
					Rent rento;	     
					ResultSet result = st.executeQuery("SELECT * FROM rewind,rentaction WHERE rewind.rent_action_id=rentaction.id AND rewind.client_id='"+c.getId()+"'");
					result.beforeFirst();
					while(result.next()) 
						{
						rento = new Rent(this.getRentBook(result.getString("book_id")),result.getInt("days"));
						rento.setId(result.getInt("rentaction.id"));
						rento.setStartDate(result.getTimestamp("rentaction.created_at").toLocalDateTime().toLocalDate());
						System.out.println(rento.getStartDate());
						c.rents.add(rento);
						//selectClient.client.rents.add(rento);
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//System.out.println(c.getName()+"   "+c.getRents());
			
		    	
		    }

		  
		  public Book getRentBook(String id) {
				
				CookBook c;
				Education ed;
				Roman r;
				try {
					st=DBconnection.getConnection().createStatement();
					BookRent bookRent;
					ResultSet result= st.executeQuery("select 'cookbook' OriginatingTable,cookbook.id,title,author,description,quantity,type,region,booksrent.id,price_Week,maxDay,penalty\r\n" + 
							"from cookbook,booksrent\r\n" + 
							"where cookbook.id='"+id+"' AND cookbook.type_id=booksrent.id \r\n" + 
							"union all\r\n" + 
							"select 'education',education.id,title,author,description,quantity,subject,academicLvl,booksrent.id,price_Week,maxDay,penalty\r\n" + 
							"from education,booksrent\r\n" + 
							"where education.id='"+id+"' AND education.type_id=booksrent.id \r\n" + 
							"union all\r\n" + 
							"select 'roman',roman.id,title,author,description,quantity,summary,age,booksrent.id,price_Week,maxDay,penalty\r\n" + 
							"from roman,booksrent\r\n" + 
							"where roman.id='"+id+"'AND roman.type_id=booksrent.id");
					result.beforeFirst();
					if(result.next()) {
						bookRent = new BookRent(result.getInt("price_Week"),result.getInt(9),result.getInt("maxDay"),result.getInt("penalty"));
						
						if(result.getString(1).equals("roman")) {
							r = new Roman(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookRent,result.getString(7),result.getInt(8));
							System.out.println(r);
							return r;
						}
						
						if(result.getString(1).equals("education")) {
							ed= new Education(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookRent,result.getString(7),result.getString(8));
						System.out.println(ed);
						return ed;
						}

						if(result.getString(1).equals("cookbook")) {
							c = new CookBook(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookRent,result.getString(7),result.getString(8));
						System.out.println(c);
						return c;
						}	
					//System.out.println(result.getString(1)+" + "+result.getString(2)+" + "+result.getString(3)+" + "+result.getString(4) +" + "+ result.getString(5)+" + "+result.getInt(6)+" + "+result.getString(7)+" + "+result.getInt(8)+" + "+result.getInt(9)+" + "+result.getInt(10));
					}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				//return c;
				System.out.println("null");
				return null;
				
			}
		  
		  
}
