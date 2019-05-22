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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
	import javafx.scene.layout.Pane;
	import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Books.Book;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Transaction.Rent;
import model.Types.BookRent;

public class rentEditt implements Initializable{

	Transaction trans=new Transaction();
	private static Statement st;

	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private ImageView back;

	    @FXML
	    private Pane pane;

	    @FXML
	    private Text rentText;

	    @FXML
	    private Label label;

	    @FXML
	    private TextField texto;

	    @FXML
	    private Label labelDays;

	    @FXML
	    private TextField days;

	    @FXML
	    private ImageView rent;

	    @FXML
	    void Back(MouseEvent event) {
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/TransactionPage.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Transaction");
				window.show();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void rentoAction(MouseEvent event) {
	    	Rent rent;
	    	Pattern digitPattern = Pattern.compile("[0-9]");
	    	if(!texto.getText().isEmpty() && !days.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(days.getText());
	    		if(m.find()) {
	    			if(this.getRentBook(texto.getText()) != null) {
	    				if(this.getRentBook(texto.getText()).getType() instanceof BookRent) {
	    					if(Integer.parseInt(days.getText())>=((BookRent) this.getRentBook(texto.getText()).getType()).getMaxDay()) {
	    						AlertController.error("You pass the days limit");
	    					}
	    				else {
	    					if(checkBook(texto.getText())!=trans.getBill().getTransactions().size()) {
	    						Alert alert = new Alert(AlertType.CONFIRMATION);
	    						alert.setContentText("Are u sure u want to update this transaction?");
	    						alert.setHeaderText("");
	    						alert.showAndWait();
	    						
	    						if(alert.getResult()==ButtonType.OK) {
	    							rent = (Rent) trans.getBill().getTransactions().get(checkBook(texto.getText()));
	    							rent.setDays(Integer.parseInt(days.getText()));
	    						}
	    						}	
	    				}
	    			}
	    		}
	    		}else {
	    			AlertController.error("The quantity field must be a digit");
	    		}
	    	}   	
	    	
	    }
	    
	    
	    private int checkBook(String id) {
	    	
	    	int i;
	    	for(i=0;i<trans.getBill().getTransactions().size();i++) {
	    		if(trans.getBill().getTransactions().get(i).getBook_id().getId().equals(id)) {
	    			return i;
	    		}
	    			
	    	}
	    	return i;
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image backk = new Image("/images/ic_arrow_back_48px-512.png");
			Image rento = new Image("/images/rent.png");
			back.setImage(backk);	
			rent.setImage(rento);
			
			texto.setText(Transaction.re.getId());
			texto.setEditable(false);
			days.setText(""+Transaction.re.getNumber());
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
