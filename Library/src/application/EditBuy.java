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
import model.Transaction.Buy;
import model.Types.BookBuy;

	public class EditBuy implements Initializable{

		Transaction trans=new Transaction();
		private static Statement st;

		
	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private ImageView back;

	    @FXML
	    private Pane pane;

	    @FXML
	    private Text buyText;

	    @FXML
	    private Label label;

	    @FXML
	    private TextField texto;

	    @FXML
	    private Label labelQuantity;

	    @FXML
	    private TextField qt;

	    @FXML
	    private ImageView buy;

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
	    void buyAction(MouseEvent event) {
	    	Buy buy;
	    	Pattern digitPattern = Pattern.compile("[0-9]");
	    	if(!texto.getText().isEmpty() && !qt.getText().isEmpty()) {
	    		Matcher m = digitPattern.matcher(qt.getText());
	    		if(m.find()) {
	    			if(this.getBuyBook(texto.getText()) != null) {
	    				if(Integer.parseInt(qt.getText())>=this.getBuyBook(texto.getText()).getQt()) {
	    					AlertController.error("You can't buy all the quantity");
	    				}else {
	    					if(checkBook(texto.getText())!=trans.getBill().getTransactions().size()) {
	    						Alert alert = new Alert(AlertType.CONFIRMATION);
	    						alert.setContentText("Are u sure u want to update this transaction?");
	    						alert.setHeaderText("");
	    						alert.showAndWait();
	    						
	    						if(alert.getResult()==ButtonType.OK) {
	    							buy = (Buy) trans.getBill().getTransactions().get(checkBook(texto.getText()));
	    							buy.setQt(Integer.parseInt(qt.getText()));
	    						}
	    						}
	    				}
	    			} 
	    		}else {
	    			AlertController.error("The quantity field must be a digit");
	    		}
	    	}
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			Image backk = new Image("/images/ic_arrow_back_48px-512.png");
			Image buyy = new Image("/images/buying_help_242455.png");
			back.setImage(backk);
			buy.setImage(buyy);
			
			texto.setText(Transaction.re.getId());
			texto.setEditable(false);
			qt.setText(""+Transaction.re.getNumber());
			
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
		    
		    public  Book getBuyBook(String id) {
				
				CookBook c;
				Education ed;
				Roman r;
				try {
					st=DBconnection.getConnection().createStatement();
					BookBuy bookBuy;
					ResultSet result= st.executeQuery("select 'cookbook' OriginatingTable,cookbook.id,title,author,description,quantity,type,region,booksbuy.id,price\r\n" + 
							"from cookbook,booksbuy\r\n" + 
							"where cookbook.id='"+id+"' AND cookbook.type_id=booksbuy.id \r\n" + 
							"union all\r\n" + 
							"select 'education',education.id,title,author,description,quantity,subject,academicLvl,booksbuy.id,price\r\n" + 
							"from education,booksbuy\r\n" + 
							"where education.id='"+id+"' AND education.type_id=booksbuy.id \r\n" + 
							"union all\r\n" + 
							"select 'roman',roman.id,title,author,description,quantity,summary,age,booksbuy.id,price\r\n" + 
							"from roman,booksbuy\r\n" + 
							"where roman.id='"+id+"'AND roman.type_id=booksbuy.id");
					result.beforeFirst();
					if(result.next()) {
						bookBuy = new BookBuy(result.getInt(9),result.getInt(10));
						
						if(result.getString(1).equals("roman")) {
							r = new Roman(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookBuy,result.getString(7),result.getInt(8));
							System.out.println(r);
							return r;
						}
						
						if(result.getString(1).equals("education")) {
							ed= new Education(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookBuy,result.getString(7),result.getString(8));
						System.out.println(ed);
						return ed;
						}
			
						if(result.getString(1).equals("cookbook")) {
							c = new CookBook(result.getString(2),result.getString(3),result.getString(4),result.getString(5) , result.getInt(6),bookBuy,result.getString(7),result.getString(8));
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
