package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class typePage implements Initializable{

	
		@FXML
		StackPane stackpane;
	
	    @FXML
	    private Button rent;

	    @FXML
	    BorderPane Borderpane;
	    
	    @FXML
	    private ImageView imgRent;

	    @FXML
	    private Button buy;

	    @FXML
	    private ImageView imgBuy;

	    @FXML
	    private AnchorPane anchor;

	    @FXML
	    private Text texto;
	    
	    @FXML
	    private Button backo;
	    
//	    @FXML
//	    private Button selecto;

	    
	    @FXML
	    void back(ActionEvent event) {

	    	if(AddEducation.education!=null) {
	    		try {
			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/Education.fxml"));
					Scene main = new Scene(root);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Education");
					window.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	}
	    	
	    	if(AddCookBook.cookBook!=null) {
	    		try {
			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/CookBook.fxml"));
					Scene main = new Scene(root);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Cook Book");
					window.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	}
	    	
	    	if(AddRoman.roman!=null) {
	    		try {
			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/Roman.fxml"));
					Scene main = new Scene(root);
					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
					window.setScene(main);
					window.setTitle("Roman");
					window.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	}
	    	
	    	System.out.println("ottoto");
	    	
	    	
	    }
	    

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image imgbuy = new Image("/images/buy.png");
			Image imgrent = new Image("/images/_Rent_Sign-512.png");
			imgBuy.setImage(imgbuy);
			imgRent.setImage(imgrent);
		}
	    
	    
	    @FXML
	    void getBuy(MouseEvent event) {
	    	loadPage("ForBuy");
	    }

	    @FXML
	    void getRent(MouseEvent event) {
	    	loadPage("ForRent");
	    }
	    
	    private void loadPage(String ui) {
	    	Parent root = null;
	    	try {
			root =FXMLLoader.load(getClass().getResource("FXML/"+ui+".fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	Borderpane.setCenter(root);
	    	
	    }

//	    @FXML
//	    void select(ActionEvent event) {
//
//	    }


}
