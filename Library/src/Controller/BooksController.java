	package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Books.Book;
import model.Books.CookBook;
import model.Books.Education;
import model.Books.Roman;
import model.Transaction.Buy;
import model.Transaction.Transaction;
import model.Types.BookBuy;
import model.Types.BookRent;
import model.tabless.AllBuy;
import model.tabless.AllBuyCook;
import model.tabless.AllBuyEducation;
import model.tabless.AllBuyRoman;
import model.tabless.AllRent;
import model.tabless.AllRentCook;
import model.tabless.AllRentEducation;
import model.tabless.AllRentRoman;


public class BooksController {


		private static Statement st;
		
	
		public static ObservableList <Book> getRentBooks(){
			
			ObservableList <Book> books= FXCollections.observableArrayList();
			try {
				
				st=DBconnection.getConnection().createStatement();
				Roman roman;
				Education education;
				CookBook cookBook;
				BookRent bookRent;
				
				ResultSet resultRoman = st.executeQuery("SELECT * FROM roman,booksrent WHERE booksrent.id=roman.type_id");
				resultRoman.beforeFirst();
				
				
				
				while(resultRoman.next()) {
					bookRent = new BookRent(resultRoman.getInt(10),resultRoman.getInt(9),resultRoman.getInt(11),resultRoman.getInt(12));
					roman = new Roman(resultRoman.getString(1),resultRoman.getString(2),resultRoman.getString(3),resultRoman.getString(4),resultRoman.getInt(5),bookRent,resultRoman.getString(6),resultRoman.getInt(7));
					books.add(roman);
					}
				
				ResultSet resultEducation = st.executeQuery("SELECT * FROM education,booksrent WHERE booksrent.id=education.type_id");
				resultEducation.beforeFirst();
				
				while(resultEducation.next()) {
					bookRent = new BookRent(resultEducation.getInt(10),resultEducation.getInt(9),resultEducation.getInt(11),resultEducation.getInt(12));
					education = new Education(resultEducation.getString(1),resultEducation.getString(2),resultEducation.getString(3),resultEducation.getString(4),resultEducation.getInt(5),bookRent,resultEducation.getString(6),resultEducation.getString(7));
					books.add(education);
					}
				
				ResultSet resultCookBook = st.executeQuery("SELECT * FROM cookbook,booksrent WHERE booksrent.id=cookbook.type_id");
				resultCookBook.beforeFirst();
				
				while(resultCookBook.next()) {
					bookRent = new BookRent(resultCookBook.getInt(10),resultCookBook.getInt(9),resultCookBook.getInt(11),resultCookBook.getInt(12));
					cookBook = new CookBook(resultCookBook.getString(1),resultCookBook.getString(2),resultCookBook.getString(3),resultCookBook.getString(4),resultCookBook.getInt(5),bookRent,resultCookBook.getString(6),resultCookBook.getString(7));
					books.add(cookBook);
					}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			System.out.println(books);
			
			return books;
		}
		
		
		







	public static ObservableList <Roman> getRomanBooks(){
	ObservableList <Roman> Romans = FXCollections.observableArrayList();
	Roman roman;
	try {
		st=DBconnection.getConnection().createStatement();
		BookBuy bookBuy;
		BookRent bookRent;
		ResultSet resultBuy= st.executeQuery("SELECT * FROM roman,booksbuy WHERE roman.type_id=booksbuy.id");	
		resultBuy.beforeFirst();
		
		while(resultBuy.next()) {
			bookBuy = new BookBuy(resultBuy.getInt(9),resultBuy.getInt(10));
			System.out.println(resultBuy.getInt(9)+"  "+resultBuy.getInt(10));
			roman = new Roman(resultBuy.getString(1),resultBuy.getString(2),resultBuy.getString(3),resultBuy.getString(4),resultBuy.getInt(5),bookBuy,resultBuy.getString(6),resultBuy.getInt(7));
			Romans.add(roman);
		}
		
		ResultSet resultrent= st.executeQuery("SELECT * FROM roman,booksrent WHERE roman.type_id=booksrent.id");
		resultrent.beforeFirst();
		while(resultrent.next()) {
			bookRent = new BookRent(resultrent.getInt(10),resultrent.getInt(9),resultrent.getInt(11),resultrent.getInt(12));
			roman = new Roman(resultrent.getString(1),resultrent.getString(2),resultrent.getString(3),resultrent.getString(4),resultrent.getInt(5),bookRent,resultrent.getString(6),resultrent.getInt(7));
			Romans.add(roman);
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(Romans);
	return Romans;
}

	public static ObservableList <Education> getEducationBooks(){
		ObservableList <Education> Educations = FXCollections.observableArrayList();
		Education education;
		try {
			st=DBconnection.getConnection().createStatement();
			BookBuy bookBuy;
			BookRent bookRent;
			ResultSet resultBuy= st.executeQuery("SELECT * FROM education,booksbuy WHERE education.type_id=booksbuy.id");	
			resultBuy.beforeFirst();
			
			while(resultBuy.next()) {
				bookBuy = new BookBuy(resultBuy.getInt(9),resultBuy.getInt(10));
				System.out.println(resultBuy.getInt(9)+"  "+resultBuy.getInt(10));
				education = new Education(resultBuy.getString(1),resultBuy.getString(2),resultBuy.getString(3),resultBuy.getString(4),resultBuy.getInt(5),bookBuy,resultBuy.getString(6),resultBuy.getString(7));
				Educations.add(education);
			}
			
			ResultSet resultrent= st.executeQuery("SELECT * FROM education,booksrent WHERE education.type_id=booksrent.id");
			resultrent.beforeFirst();
			while(resultrent.next()) {
				bookRent = new BookRent(resultrent.getInt(10),resultrent.getInt(9),resultrent.getInt(11),resultrent.getInt(12));
				education = new Education(resultrent.getString(1),resultrent.getString(2),resultrent.getString(3),resultrent.getString(4),resultrent.getInt(5),bookRent,resultrent.getString(6),resultrent.getString(7));
				Educations.add(education);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(Educations);
		return Educations;
	}

	public static ObservableList <CookBook> getCookBookBooks(){
		ObservableList <CookBook> CookBooks = FXCollections.observableArrayList();
		CookBook cookBook;
		try {
			st=DBconnection.getConnection().createStatement();
			BookBuy bookBuy;
			BookRent bookRent;
			ResultSet resultBuy= st.executeQuery("SELECT * FROM cookbook,booksbuy WHERE cookbook.type_id=booksbuy.id");	
			resultBuy.beforeFirst();
			
			while(resultBuy.next()) {
				bookBuy = new BookBuy(resultBuy.getInt(9),resultBuy.getInt(10));
				System.out.println(resultBuy.getInt(9)+"  "+resultBuy.getInt(10));
				cookBook = new CookBook(resultBuy.getString(1),resultBuy.getString(2),resultBuy.getString(3),resultBuy.getString(4),resultBuy.getInt(5),bookBuy,resultBuy.getString(6),resultBuy.getString(7));
				CookBooks.add(cookBook);
			}
			
			ResultSet resultrent= st.executeQuery("SELECT * FROM cookBook,booksrent WHERE cookBook.type_id=booksrent.id");
			resultrent.beforeFirst();
			while(resultrent.next()) {
				bookRent = new BookRent(resultrent.getInt(10),resultrent.getInt(9),resultrent.getInt(11),resultrent.getInt(12));
				cookBook = new CookBook(resultrent.getString(1),resultrent.getString(2),resultrent.getString(3),resultrent.getString(4),resultrent.getInt(5),bookRent,resultrent.getString(6),resultrent.getString(7));
				CookBooks.add(cookBook);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(CookBooks);
		return CookBooks;
	}


	
	
	
	
	
	
//public static void main(String[] args) {
	//BookBuy b =new BookBuy(99);
//	BookRent r =new BookRent(12,4,1);
	//BooksController.insertTypeBuy(b);
//	new BooksController().insertTypeRent(r);
//	new BooksController().insertRoman(new Roman("HP200","Harry Potter","JK","HARRY POTTERRRR",50,b,"toto",20));
//	new BooksController().insertRoman(new Roman("MH","Man from Mars","MGH","Hassan assaad",2,r,"titi",10));
//	new BooksController().insertEducation(new Education("M11200","Math200","doc toto","qeqeqe",2,r,"ta5a5","10"));
	//new BooksController().getBuyBooks();
	//new BooksController().getRentBooks();
	//BooksController.getBuyBook("14");
//	BooksController.getRentBookss();
	//BooksController.getRentBook("MH");
	
//}

//select 'cookbook' OriginatingTable,id,title,author,description,quantity,type,type_id
//from cookbook,booksbuy
//where id="C12" AND type_id=booksbuy.id 
//union all
//select 'education',id,title,author,description,quantity,subject,type_id
//from education,booksbuy
//where id="C12" AND type_id=booksbuy.id 
//union all
//select 'roman',id,title,author,description,quantity,summary,type_id
//from roman,booksbuy
//where id="C12" AND type_id=booksbuy.id 
}
