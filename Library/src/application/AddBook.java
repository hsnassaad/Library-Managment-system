package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddBook implements Initializable{
	

	  	@FXML
	    private AnchorPane anchor;
	  	
    	@FXML
    	private StackPane parentController;

	    @FXML
	    private Hyperlink roman;

	    @FXML
	    private Hyperlink cookBook;

	    @FXML
	    private Hyperlink education;

	    @FXML
	    private ImageView back;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Image backo = new Image("/images/39-512.png");
			back.setImage(backo);
		}
	    
	    
	    @FXML
	    void AddCookBook(ActionEvent event) {
	    	try {
				Parent  root = FXMLLoader.load(getClass().getResource("FXML/CookBook.FXML"));
				Scene main = anchor.getScene();
				parentController.getChildren().add(root);
				root.translateYProperty().set(main.getHeight());
				Timeline timeline = new Timeline();
				  KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
			        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
				timeline.getKeyFrames().add(kf);
				 timeline.setOnFinished(t -> {
					 parentController.getChildren().remove(anchor);
			        });
			        timeline.play();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void AddEducation(ActionEvent event) {
	    	try {
				Parent  root = FXMLLoader.load(getClass().getResource("FXML/Education.FXML"));
				Scene main = anchor.getScene();
				parentController.getChildren().add(root);
				root.translateYProperty().set(main.getHeight());
				Timeline timeline = new Timeline();
				  KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
			        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
				timeline.getKeyFrames().add(kf);
				 timeline.setOnFinished(t -> {
					 parentController.getChildren().remove(anchor);
			        });
			        timeline.play();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void AddRoman(ActionEvent event) {
	    	try {
				Parent  root = FXMLLoader.load(getClass().getResource("FXML/Roman.FXML"));
				Scene main = anchor.getScene();
				parentController.getChildren().add(root);
				root.translateYProperty().set(main.getHeight());
				Timeline timeline = new Timeline();
				KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
			    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
				timeline.getKeyFrames().add(kf);
				timeline.setOnFinished(t -> {
					 parentController.getChildren().remove(anchor);
			        });
			        timeline.play();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    @FXML
	    void goBack(MouseEvent event) {
	    	//if(login.employee.getId()==1 || login.employee.getId()==2) {
	    	try {
		    	Parent root =FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
				Scene main = new Scene(root);
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(main);
				window.setTitle("Home");
				window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
				//}
//	    	}else {
//	    		try {
//			    	Parent root =FXMLLoader.load(getClass().getResource("FXML/EmployeeMain.fxml"));
//					Scene main = new Scene(root);
//					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
//					window.setScene(main);
//					window.setTitle("Home");
//					window.show();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//	    	}
	   

	    }
	

}
