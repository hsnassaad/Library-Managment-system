package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bill;
import model.Sale;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Transaction.Buy;
import model.Transaction.Rent;
import model.Transaction.Rewind;
import model.Types.BookBuy;
import model.Types.BookRent;
import model.Types.Type;
import model.tabless.AllBuyCook;
import model.tabless.billati;

public class bill implements Initializable{

	Transaction trans=new Transaction();
	private static Statement st;
	
	public static String salee;
	
	   @FXML
	    private AnchorPane anchor;
	
    @FXML
    private ImageView logo;

    @FXML
    private Label Contactname;

    @FXML
    private Label ContactEmail;

    @FXML
    private Label dato;

    @FXML
    private Label billNum;

    @FXML
    private Label clientName;

    @FXML
    private Label phone;

    @FXML
    private Label clientEmail;
    
    @FXML
    private BorderPane border;

    @FXML
    private Button cards;

    @FXML
    private Button cashs;

    @FXML
    private AnchorPane anchor2;


    @FXML
    private TableView<billati> billTable;

    @FXML
    private TableColumn<billati,String> id;

    @FXML
    private TableColumn<billati, String> desc;

    @FXML
    private TableColumn<billati, Integer> qt;

    @FXML
    private TableColumn<billati, Integer> price;

    @FXML
    private TableColumn<billati, Integer> total;


    @FXML
    private TextField subTotal;

    @FXML
    private TextField discount;

    @FXML
    private TextField TotalBalance;

    @FXML
    private ImageView bachi;

    @FXML
    private Label typo;
    
    @FXML
    private ImageView visa;

    @FXML
    private ImageView hawyati;

    @FXML
    private ImageView master;

    @FXML
    private ImageView nour;
    
    
    @FXML
    private Button back;

    @FXML
    private Button next;

    @FXML
    void Backk(ActionEvent event) {
    	try {
	    	Parent root =FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.fxml"));
			Scene main = new Scene(root);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Home");
			window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    @FXML
    void goNext(ActionEvent event) {
    	
    	int t = 0;
    	
    	Timestamp date= new Timestamp(System.currentTimeMillis());
    	String s = new SimpleDateFormat("yyyy-MM-dd").format(date); 
    	String s2 = new SimpleDateFormat("dd/MM/yyyy").format(date);
    	trans.getBill().setDate(s);
    	
    	if(salee=="Hawyati Card") {
    		t=30;
    	}
    	
		if(salee=="Master Card") {
		    	t=15;	
		    	}
		
		if(salee=="Nour Card") {
			t=20;
		}
		
		if(salee=="Visa Card") {
			t=10;
		}
    	
		
    	defineBillTable();
    	trans.getBill().setTotal_sale(trans.getBill().getTotal()/t);
    	this.insertBill(trans.getBill());
    	
    	Transaction.check=0;
        anchor.setEffect(null);
        anchor2.setVisible(false);
    	
    	
		Contactname.setText(trans.getBill().getEmp().getName());
		ContactEmail.setText(trans.getBill().getEmp().getEmail());
		clientName.setText(trans.getBill().getClient().getName());
		clientEmail.setText(trans.getBill().getClient().getEmail());
		phone.setText(""+trans.getBill().getClient().getPhone());
		billNum.setText(""+trans.getBill().id);
		dato.setText(s2);
		typo.setText(salee);
		subTotal.setText(""+trans.getBill().getTotal());
		discount.setText(""+trans.getBill().getTotal_sale());
		TotalBalance.setText(""+trans.getBill().getTotalBalance());
		selectClient.client=null;
    }

    @FXML
    void back(MouseEvent event) {
    	try {
	    	Parent root =FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.fxml"));
			Scene main = new Scene(root);
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(main);
			window.setTitle("Home");
			window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Image backo = new Image("/images/32-512.png");
		Image logi = new Image("/images/kM2dke2_.png");	
		
		Image visas = new Image("/images/payment-card.png");
		Image masters = new Image("/images/MasterCard_Logo.svg.png");
		Image nouur = new Image("/images/credit_card_payment_visa_platinum-512.png");
		Image haw = new Image("/images/199516.png");

		bachi.setImage(backo);
		logo.setImage(logi);
		
		visa.setImage(visas);
		master.setImage(masters);
		nour.setImage(nouur);
		hawyati.setImage(haw);
		
		Tooltip.install(visa,new Tooltip("Visa Card"));
		Tooltip.install(master,new Tooltip("Master Card"));
		Tooltip.install(nour,new Tooltip("Nour Card"));
		Tooltip.install(hawyati,new Tooltip("Hawyati Card"));
		
		id.setResizable(false);
		desc.setResizable(false);
		qt.setResizable(false);
		total.setResizable(false);
		price.setResizable(false);
		
		BoxBlur bb = new BoxBlur();
        bb.setWidth(10);
        bb.setHeight(10);
        bb.setIterations(3);
        
        anchor.setEffect(bb);
        anchor2.setVisible(true);
		
	}
	
	public void defineBillTable() {
		
		double result=0;
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		qt.setCellValueFactory(new PropertyValueFactory<>("qt"));
		total.setCellValueFactory(new PropertyValueFactory<>("total"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		ObservableList <billati> bill= FXCollections.observableArrayList();
		billati b = null;
		Type t;
		model.Transaction.Transaction tra;
		for(int i=0;i<trans.getBill().getTransactions().size();i++) {
			int u=0;
			t=trans.getBill().getTransactions().get(i).getBook_id().getType();
			tra = trans.getBill().getTransactions().get(i);
			if(t instanceof BookBuy) {
				if(tra instanceof Buy) {
					b = new billati(tra.getBook_id().getId(),tra.getBook_id().getDescription(),((Buy) tra).getQt(),((BookBuy) t).getPrice());
//					result+=b.getTotal();
					u=tra.getBook_id().getQt()-((Buy) tra).getQt();
					tra.getBook_id().setQt(u);
					
					if(tra.getBook_id() instanceof Roman) {
						AddRoman.updateQTRoman(tra.getBook_id());
					}
					if(tra.getBook_id() instanceof Education) {
						AddEducation.updateQTEducation(tra.getBook_id());
					}
					if(tra.getBook_id() instanceof CookBook) {
						AddCookBook.updateQTCookBook(tra.getBook_id());
					}
					
				}
			}
			
			if(t instanceof BookRent) {
				if(tra instanceof Rent) {
				b = new billati(tra.getBook_id().getId(),tra.getBook_id().getDescription(),((Rent) tra).getDays(),((BookRent) t).getPrice_Week());
				}
				
				if(tra instanceof Rewind) {
					b=new billati(tra.getBook_id().getId(),tra.getBook_id().getDescription(),0,0,((Rewind) tra).getPenaltiTaken());
				}
				
				u=tra.getBook_id().getQt()-1;
				tra.getBook_id().setQt(u);
				
				if(tra.getBook_id() instanceof Roman) {
					AddRoman.updateQTRoman(tra.getBook_id());
				}
				if(tra.getBook_id() instanceof Education) {
					AddEducation.updateQTEducation(tra.getBook_id());
				}
				if(tra.getBook_id() instanceof CookBook) {
					AddCookBook.updateQTCookBook(tra.getBook_id());
				}
				}
			
			result+=b.getTotal();
			bill.add(b);
		}
			trans.getBill().setTotal(result);
			billTable.setItems(bill);
			
		}
		
	@FXML
    void hawyatiCard(MouseEvent event) {
		salee = "Hawyati Card";
    }

    @FXML
    void masterCard(MouseEvent event) {
    	salee = "Master Card";
    }

    @FXML
    void noura(MouseEvent event) {
    	salee = "Nour Card";
    }

    @FXML
    void visaCard(MouseEvent event) {
    	salee = "Visa Card";
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
    
	public  void insertRentAction(Rent rent) {
		try {
			Rent.counta+=2;
			rent.setId(Rent.counta);
			st=DBconnection.getConnection().createStatement();
			st.executeUpdate("INSERT INTO rentaction(id,days,book_id,bill_id) VALUES('"+rent.getId()+"','"+rent.getDays()+"','"+rent.getBook_id().getId()+"','"+Bill.id+"')");
			System.out.println(rent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public  void insertBuyAction(Buy buy) {
		try {
			Buy.counta+=2;
			buy.setId(Buy.counta);
			st=DBconnection.getConnection().createStatement();
			st.executeUpdate("INSERT INTO buyaction(id,quantity,book_id,bill_id) VALUES('"+buy.getId()+"','"+buy.getQt()+"','"+buy.getBook_id().getId()+"','"+Bill.id+"')");
			System.out.println(buy);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertToRewind(Rent rent) {
		try {
			st=DBconnection.getConnection().createStatement();
			st.executeUpdate("INSERT INTO rewind(rent_action_id,client_id) VALUES('"+rent.getId()+"','"+selectClient.client.getId()+"')");
			System.out.println(rent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public  void insertBill(Bill b) {
		try {
			st=DBconnection.getConnection().createStatement();	
			st.executeUpdate("INSERT INTO bill VALUES('"+Bill.id+"','"+b.getEmp().getId()+"','"+b.getClient().getId()+"','"+b.getTotal()+"','"+b.getTotal_sale()+ "','"+b.getDate()+ "','"+0+"')");
			
			System.out.println(1);
			for(int i=0;i<b.getTransactions().size();i++) {
				
				if(b.getTransactions().get(i) instanceof Buy) {
					insertBuyAction((Buy) b.getTransactions().get(i));
				}
				
				if (b.getTransactions().get(i) instanceof Rent){
					insertRentAction((Rent) b.getTransactions().get(i));
					insertToRewind((Rent) b.getTransactions().get(i));
				}
				
				
					
				
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(b);
	}
    
    
    
    
    
	}

