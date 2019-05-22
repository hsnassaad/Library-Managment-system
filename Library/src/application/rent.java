package application;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Controller.AlertController;
import Controller.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Types.BookRent;

public class rent {

	 private static Statement st;
	
    @FXML
    private AnchorPane anchor;

    @FXML
    private Pane pane;

    @FXML
    private Text text;

    @FXML
    private Label labelppw;

    @FXML
    private TextField pricePerWeek;

    @FXML
    private Label labelPenalty;

    @FXML
    private TextField penalty;

    @FXML
    private Label labelMaxDays;

    @FXML
    private TextField maxDays;

    @FXML
    private Button create;

    @FXML
    void save(ActionEvent event) {
    	Pattern digitPattern = Pattern.compile("[0-9]");
    	if(!pricePerWeek.getText().isEmpty() && !maxDays.getText().isEmpty() && !penalty.getText().isEmpty()) {
    		Matcher m = digitPattern.matcher(maxDays.getText());
    		if(m.find()) {
    			Matcher m2 = digitPattern.matcher(pricePerWeek.getText());
        		if(m2.find()) {
        			Matcher m3 = digitPattern.matcher(penalty.getText());
            		if(m3.find()) {
    		BookRent rent = new BookRent(Integer.parseInt(pricePerWeek.getText()),Integer.parseInt(maxDays.getText()),Integer.parseInt(penalty.getText()));
    		this.insertTypeRent(rent);
    		if(AddRoman.roman != null) {
    		AddRoman.roman.setType(rent);
    		AddRoman.insertRoman(AddRoman.roman);
    		//AddRoman.roman = null;
    		System.out.println(AddRoman.roman);
    		}
    		
    		if(AddCookBook.cookBook != null) {
    			AddCookBook.cookBook.setType(rent);
    			AddCookBook.insertCookBook(AddCookBook.cookBook);
	    		//AddCookBook.cookBook = null;
	    		System.out.println(AddCookBook.cookBook);
	    		}
    		
    		if(AddEducation.education != null) {
    			AddEducation.education.setType(rent);
    			AddEducation.insertEducation(AddEducation.education);
	    		//AddEducation.education = null;
	    		System.out.println(AddEducation.education);
	    		}
    		
    		rent = null;
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
		 	alert.setContentText("Do you want to buy another book ?");
		 	alert.setHeaderText("");
		 	alert.showAndWait();
		 	
		 	if(alert.getResult()==ButtonType.OK) {
		 		AddEducation.education = null;
		 		AddCookBook.cookBook = null;
		 		AddRoman.roman = null;
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
		 	
		 	if(alert.getResult()==ButtonType.CANCEL) {
		 		try {
					Parent  parent = FXMLLoader.load(getClass().getResource("FXML/main.FXML"));
					Scene main = new Scene(parent);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Home");
					window.show();
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}
		 	
            		}else {
            			AlertController.error("Invalid penalty number");
            		}
        		}else {
        			AlertController.error("Invalid maxDays number");
        		}
        		
    		}else {
    			AlertController.error("Invalid pricePerWeek number");
    		}
    		
    		
    	}else {
    		AlertController.error("please fill out the price field :)");
    	}
    }

    
    
 // Data Base Functions
    
   
	
	public void insertTypeRent(BookRent book) {
		
		try {
			st=DBconnection.getConnection().createStatement();
			BookRent.c=BookRent.c+2;
			book.setId(BookRent.c);
			st.executeUpdate("INSERT INTO booksrent(id,price_Week,maxDay,penalty) VALUES('"+book.getId()+"','"+book.getPrice_Week()+"','"+book.getMaxDay()+"','"+book.getPenalty()+"')");
			System.out.println(book.getId()+" Has been inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    
}
