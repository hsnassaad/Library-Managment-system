	package application;

	import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ResourceBundle;

import Controller.AlertController;
import Controller.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import model.Person.Client;
import model.Transaction.Rent;
import model.Transaction.Rewind;
import model.Types.BookRent;
import model.tabless.Rewindo;


public class returnBook implements Initializable{

	private static Statement st;
	Transaction trans=new Transaction();

	    @FXML
	    private AnchorPane anchor;
	    
	    @FXML
	    private SplitPane splitPane;

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
	    private TextField daysField;

	    @FXML
	    private Label labelDays;

	    @FXML
	    private TableColumn<Rewindo, String> days;

	    @FXML
	    private ImageView rent;

	    @FXML
	    private TableView<Rewindo> rewindTable;

	    @FXML
	    private TableColumn<Rewindo, String> idBook;
	    
	    @FXML
	    private TableColumn<Rewindo, String> maxDays;

	    @FXML
	    void Back(MouseEvent event) {
	    	texto.setText("");
	    	days.setText("");
	    	RentBooksCollection.allrent=null;
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
	    void getItems(MouseEvent event) {
	    	texto.setText(rewindTable.getSelectionModel().getSelectedItem().getId());
	    	daysField.setText(""+this.getPenalty(selectClient.client, this.getRentBook(texto.getText())));
	    }

	    
	    
	    @FXML
	    void rentoAction(MouseEvent event) {
	    	Rent r = selectClient.client.getRentByBook(this.getRentBook(texto.getText()));
			Rewind RA=new Rewind(this.getRentBook(texto.getText()),r,Integer.parseInt(daysField.getText()));
			r.setBit(1);
			trans.getBill().transactions.add(RA);
			this.updateBit(r);
	    	texto.setText("");
	    	daysField.setText("");
	    	rewindTable.setItems(this.getRewindos());
	    	System.out.println(trans.getBill());
	    }

	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			idBook.setCellValueFactory(new PropertyValueFactory<>("id"));
			days.setCellValueFactory(new PropertyValueFactory<>("days"));
			maxDays.setCellValueFactory(new PropertyValueFactory<>("MaxDays"));
			rewindTable.setItems(this.getRewindos());
			
			Image backk = new Image("/images/ic_arrow_back_48px-512.png");
			Image rento = new Image("/images/rent.png");
			
			back.setImage(backk);
			rent.setImage(rento);
			
			texto.setEditable(false);
			daysField.setEditable(false);
			
			SplitPane.setResizableWithParent(splitPane, Boolean.FALSE);
			
		}

		
		
		public int getPenalty(Client c,Book b) {
			int penaltati=0;
			
			if(c.getRentByBook(b)!=null) {		
			Rent r=c.getRentByBook(b);
			LocalDate t=r.StartDate;
			BookRent ForRent= (BookRent) b.getType();
			int p=ForRent.getPenalty();
			System.out.println("data "+t);
			int days=r.getDays();
			System.out.println("popo "+days);
			LocalDate CurDate=LocalDate.now();
			System.out.println("pipi "+CurDate);
			long daysBetween=ChronoUnit.DAYS.between(t,CurDate);
			System.out.println("trtrtrtrtqweqwe "+daysBetween);
			int d=(int)daysBetween;
			System.out.println("rtrtrt "+d);
			
			if(d>days) {
				penaltati=p*(d-days);
			}
			
			//Rewind RA=new Rewind(r);
			//trans.getBill().transactions.add(RA);
			//AlertController.information("toto "+daysBetween);
			//AlertController.information("toto22 "+d);
			//AlertController.information("penaltyyyyyyy "+penaltati);
			//r.setBit(1);
			
			}
			
			//AlertController.information("penalty "+penaltati);
			return penaltati;
			
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
	    
		public  ObservableList <Rewindo> getRewindos(){
			ObservableList <Rewindo> rewindos= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				Rewindo rewindo;
				Book b;	     
				ResultSet result = st.executeQuery("SELECT * FROM rewind,rentaction WHERE rewind.rent_action_id=rentaction.id AND rewind.client_id='"+selectClient.client.getId()+"'");
				result.beforeFirst();
				while(result.next()) 
					{
					if(result.getInt("bit")==0){
					String oldDate = new SimpleDateFormat("yyyy-MM-dd").format(result.getTimestamp("rewind.created_at"));
					//System.out.println("Date before Addition: "+oldDate);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					try{
					   c.setTime(sdf.parse(oldDate));
					   
					}catch(ParseException e){
					   e.printStackTrace();
					 }
					c.add(Calendar.DAY_OF_MONTH, result.getInt("days"));  
					String newDate = sdf.format(c.getTime());  
					//System.out.println("Date Incremented by One: "+newDate);
					rewindo = new Rewindo(result.getString("book_id"),oldDate,newDate);
					rewindos.add(rewindo);
					}
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(rewindos);
			
			return rewindos;
		
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
		
		
		
		public void updateBit(Rent a) {
			try {
				st=DBconnection.getConnection().createStatement();
				st.executeUpdate("UPDATE rentaction SET bit=1 where id='"+a.getId()+"'");
				System.out.println(a+" Has been Updated");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}
		
//		public static void main(String[] args) {
//		new returnBook().rewindBook();
//		}
		
}
