package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Bill;
import model.Transaction.Buy;
import model.Transaction.Rent;
import model.Types.BookBuy;
import model.Types.BookRent;

public class DBconnection {
	private static Connection con;
	private static Statement st;
	
	private DBconnection() {
	}
	 
	public static Connection getConnection() {
		if(con==null) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?autoReconnect=true&useSSL=false","root","");
				System.out.println("Connection Created");
				try {
					st=DBconnection.getConnection().createStatement();
					ResultSet resultBuy= st.executeQuery("SELECT MAX(id) FROM booksbuy");
					
					resultBuy.beforeFirst();
					resultBuy.next(); 
					if(resultBuy.getInt(1)!=0){
					BookBuy.c=resultBuy.getInt(1);
					}else {
						BookBuy.c=1;
						
					}
					ResultSet resultrent= st.executeQuery("SELECT  MAX(id) FROM booksrent");
					resultrent.beforeFirst();
					resultrent.next(); 
					if(resultrent.getInt(1)!=0) {
					BookRent.c = resultrent.getInt(1);
					}else {
						BookRent.c=0;
					}
					
					ResultSet resultBuyAction= st.executeQuery("SELECT MAX(id) FROM buyaction");
					resultBuyAction.beforeFirst();
					resultBuyAction.next(); 
					if(resultBuyAction.getInt(1)!=0){
					Buy.counta=resultBuyAction.getInt(1);
					}else {
						Buy.counta=0;
						
					}
					
					ResultSet resultRentAction= st.executeQuery("SELECT MAX(id) FROM rentaction");
					resultRentAction.beforeFirst();
					resultRentAction.next(); 
					if(resultRentAction.getInt(1)!=0){
					Rent.counta=resultRentAction.getInt(1);
					}else {
						Rent.counta=1;
						
					}
					
					st=DBconnection.getConnection().createStatement();
					ResultSet resultBill= st.executeQuery("SELECT MAX(id) FROM bill");
					
					resultBill.beforeFirst();
					resultBill.next(); 
					if(resultBill.getInt(1)!=0){
					Bill.id=resultBill.getInt(1);
					}else {
						Bill.id = 1;
						
					}
					
					
					
					System.out.println(Rent.counta);
					System.out.println(Buy.counta);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Alert  alert = new Alert(AlertType.ERROR);
				alert.setContentText("There are no connection to the Data Base try again later");
				alert.setTitle("");
				alert.setHeaderText("");
				alert.showAndWait();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		}
//		else {
//			System.out.println("Connection already created");
//		}
		return con;
	}
	
	
	public static void disconenct() {
		if(con!=null) {
			con=null;
		}
	}
	
}
