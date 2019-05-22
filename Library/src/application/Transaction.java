package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import Controller.AlertController;
import Controller.BooksController;
import Controller.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bill;
import model.Books.Book;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Transaction.Buy;
import model.Transaction.Rent;
import model.Transaction.Rewind;
import model.Types.BookRent;
import model.tabless.Rewindo;
import model.tabless.recent;

public class Transaction implements Initializable{
	
	
	private static Statement st;
	public static Bill bill;
	public static double total=0;
	public static double totalSale=0;
	public static recent re;
	
	public static int check=0;
	
	@FXML
    private StackPane sp;
	
	@FXML
    private AnchorPane anchor2;
	
    @FXML
    private AnchorPane anchor;

    @FXML
    private Pane salePane;

    @FXML
    private ImageView BookSale;

    @FXML
    private Pane rentPane;

    @FXML
    private ImageView BookRent;

    @FXML
    private Pane rewindPane;

    @FXML
    private ImageView BookRewind;
    @FXML
    private Text text;
    
    @FXML
    private Text name;

    @FXML
    private Hyperlink edit;

    @FXML
    private Hyperlink seeTransaction;
    
    @FXML
    private Text rt;

    @FXML
    private TableView<recent> rtTable;
    
    @FXML
    private TableColumn<recent, String> namo;

    @FXML
    private TableColumn<recent, String> id;

    @FXML
    private TableColumn<recent, String> category;

    @FXML
    private TableColumn<recent, Integer> qd;

    @FXML
    private TableColumn<recent, String> type;

    @FXML
    private ImageView editImage;
   
    @FXML
    private ImageView deleteImage;
    
    @FXML
    private Button backk;
    
    @FXML
    private ImageView back;

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image sale = new Image("/images/bookForSale.png");
		Image  rent= new Image("/images/RentBook.png");
		Image  returne= new Image("/images/returnBook.png");
		Image  edito= new Image("/images/Edit-validated-icon.png");
		Image  deleto= new Image("/images/627249-delete3-512.png");
		Image  bak= new Image("/images/ic_arrow_back_48px-512.png");
		
		BookSale.setImage(sale);
		BookRent.setImage(rent);
		BookRewind.setImage(returne);
		editImage.setImage(edito);
		deleteImage.setImage(deleto);
		back.setImage(bak);
		
		Tooltip.install(BookSale,new Tooltip("Buy a book"));
		Tooltip.install(BookRent,new Tooltip("Rent a book"));
		Tooltip.install(BookRewind,new Tooltip("Return a book"));
		
		if(check==0) {
		bill = new Bill(login.employee,selectClient.client);
		Bill.id++;
		check++;
		}
		
		anchor.setVisible(true);
		anchor2.setVisible(false);
		name.setText(selectClient.client.getName());
		
		defineTable();
		
		
		
	}
	
	@FXML
    void Back(MouseEvent event) {
		try {
			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.FXML"));
			Scene main = new Scene(parent);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Home");
			window.show();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

	@FXML
    void RentAction(MouseEvent event) {
		try {
			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/RentAction.FXML"));
			Scene main = new Scene(parent);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Rent");
			window.show();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void RewindAction(MouseEvent event) {
    	try {
	    	Parent root =FXMLLoader.load(getClass().getResource("FXML/returnBook.fxml"));
			Scene main = new Scene(root);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Return Book");
			window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }

    @FXML
    void SaleAction(MouseEvent event) {
    	try {
			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/SaleAction.FXML"));
			Scene main = new Scene(parent);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Sale");
			window.show();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public  Bill getBill() {
    	return bill;
    }
    
    @FXML
    void edit_client(ActionEvent event) {
       	try {
    			Parent  parent = FXMLLoader.load(getClass().getResource("FXML/selectClient.FXML"));
    			Scene main = new Scene(parent);
    			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    			window.setScene(main);
    			window.show();
    		
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
       	
       	edit.setVisited(false);
    }

    @FXML
    void seeRecentTransaction(ActionEvent event) {
    	seeTransaction.setVisited(false);
    	
    	BoxBlur bb = new BoxBlur();
        bb.setWidth(10);
        bb.setHeight(10);
        bb.setIterations(3);
        
        anchor.setEffect(bb);
		anchor2.setVisible(true);
    }
    
    @FXML
    void deleteTransaction(MouseEvent event) {

    	int i;
    	if(rtTable.getSelectionModel().getSelectedItem() != null) {
    		re = rtTable.getSelectionModel().getSelectedItem();
    		
    		for(i=0;i<bill.getTransactions().size();i++) {
    			if(bill.getTransactions().get(i).getBook_id().getId().equals(re.getId())) {
    				break;
    			}
    		}
    		System.out.println(bill);
    		if(i!=bill.getTransactions().size()) {
    		bill.getTransactions().remove(i);
    		RentBooksCollection.allrent=null;
    		BuyBooksCollection.allbuy=null;
    		defineTable();
    		System.out.println(bill);
    		}
    	}
    	else {
	    		AlertController.information("You need to select a transaction");
	    	}
    	
    	
    }

    @FXML
    void editTransaction(MouseEvent event) {

    	if(rtTable.getSelectionModel().getSelectedItem() != null) {
    		re = rtTable.getSelectionModel().getSelectedItem();
    		if(re.getType().equals("Buy")) {
	    	System.out.println(re);
	    	try {
				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/SaleEdit.FXML"));
				Scene main = new Scene(parent);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Edit Sale");
				window.show();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
    		}
    		
    		if(re.getType().equals("Rent")) {
    	    	System.out.println(re);
    	    	try {
    				Parent  parent = FXMLLoader.load(getClass().getResource("FXML/RentEdit.FXML"));
    				Scene main = new Scene(parent);
    				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    				window.setScene(main);
    				window.setTitle("Edit Rent");
    				window.show();
    			
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        		}
    		
	    	}else {
	    		AlertController.information("You need to select a transaction");
	    	}
    	
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	anchor.setVisible(true);
    	anchor.setEffect(null);
		anchor2.setVisible(false);
    }
    
    
    private void defineTable() {
		ObservableList <recent> toto= FXCollections.observableArrayList();
		recent r = null;
		model.Transaction.Transaction t;
		for(int i=0;i<bill.getTransactions().size();i++) {
			t=bill.getTransactions().get(i);
			
			if(t instanceof Buy) {
				if(t.getBook_id() instanceof Roman) {
			r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Roman",((Buy) t).getQt(),"Buy");
				}
				if(t.getBook_id() instanceof Education) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Education",((Buy) t).getQt(),"Buy");
						}
				if(t.getBook_id() instanceof CookBook) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"CookBook",((Buy) t).getQt(),"Buy");
						}
			}
			
			if(t instanceof Rent) {
				if(t.getBook_id() instanceof Roman) {
				
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Roman",((Rent) t).getDays(),"Rent");
						}
				if(t.getBook_id() instanceof Education) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Education",((Rent) t).getDays(),"Rent");
						}
				if(t.getBook_id() instanceof CookBook) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"CookBook",((Rent) t).getDays(),"Rent");
						}
			}
			
			if(t instanceof Rewind) {
				if(t.getBook_id() instanceof Roman) {
				
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Roman",((Rewind) t).getPenaltiTaken(),"Rewind");
						}
				if(t.getBook_id() instanceof Education) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"Education",((Rewind) t).getPenaltiTaken(),"Rewind");
						}
				if(t.getBook_id() instanceof CookBook) {
					r = new recent(t.getBook_id().getId(),t.getBook_id().getTitle(),"CookBook",((Rewind) t).getPenaltiTaken(),"Rewind");
						}
			}
			
			
			toto.add(r);
			System.out.println(toto);
		}
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		namo.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		qd.setCellValueFactory(new PropertyValueFactory<>("number"));
		rtTable.setItems(toto);
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
    
//	public static void main(String[] args) {
//	new Transaction().insertRentBooksToClient();
//	}
    
}

