package mysql;


import java.sql.*;
import java.util.Scanner;
public class Mysql {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	static final String USER = "root";
	static final String PASS = "";
	
	public static void main(String[] args)
	{
		
		Statement stmt = null;
		Connection conn = null;
		Scanner in = new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String []sql = new String[10];
			String s;
			int choice;
			do
			{
			System.out.println("MENU\n1.Create database \n 2.Create table \n 3.Insertion \n4.Select records \n5.Update records"+
					"\n6.Delete records\n7.Drop Table \n Enter your choice (0 for exit)");
			choice=in.nextInt();
			switch(choice)
			{
			case 1:
			//Creation of database
			System.out.println("Creating a Database...");
			stmt = conn.createStatement();
			sql[0] = "Create database bookshop;";
			stmt.executeUpdate(sql[0]);
			sql[0] = "use  bookshop;";
			stmt.executeQuery(sql[0]);
			System.out.println("Database created");
			break;
			
			case 2:
			//Creation of Table
			System.out.println("Creating a Table...");
			stmt = conn.createStatement();
			sql[1]="Create table books"+
					"(bookname varchar(30),"+
					"ISBNno varchar(15),"+
					"price integer)";
			stmt.executeUpdate(sql[1]);
			System.out.println(" Table created .");
			break;
			
			case 3:
			//Insertion
			stmt = conn.createStatement();
			sql[2] = "insert into books values('let us c',5845441582,203);";
			stmt.executeUpdate(sql[2]);
			sql[2] = "Insert into books values('The C programming',2587196564,255);";
			stmt.executeUpdate(sql[2]);
			sql[2] = "Insert into books values('Introduction to algorithms',9686824576,150);";
			stmt.executeUpdate(sql[2]);	
			sql[2] = "Insert into books values('Computer Networks',5248261297,365);";
			stmt.executeUpdate(sql[2]); 
			System.out.println("Records inserted ...");
			break;
			
			case 4:
				//select records
				stmt = conn.createStatement();
				sql[3] = "select * from books";
				ResultSet rs = stmt.executeQuery(sql[3]);
				while(rs.next()){
			         //Retrieve by column name
			         String bookname = rs.getString("bookname");
			         String ISBN = rs.getString("ISBNno");
			         int price = rs.getInt("price");
			         
			         //Display values
			         System.out.print("bookname: " + bookname);
			         System.out.print("  ISBNno: " + ISBN );
			         System.out.println("  price: " + price);
			      }
			      rs.close();
				break;
				
			case 5:
				//update record
				stmt = conn.createStatement();
			    sql[4] = "UPDATE books SET price = 400 WHERE ISBNno = 5248261297; ";
			    stmt.executeUpdate(sql[4]);
			    System.out.println("Records updated..");
			 break;
			
			case 6:
				//delete record
				stmt = conn.createStatement();
			    sql[5] = "DELETE FROM books " +
			                   "WHERE ISBNno = \"5248261297\"";
			      stmt.executeUpdate(sql[5]);
			      System.out.println("Records deleted..");
			 break;
			 
			case 7:
				//drop table
				stmt = conn.createStatement();
			    sql[6] = "drop table books ";           
			    stmt.executeUpdate(sql[6]);
			    System.out.println("Table  deleted in given database...");
			 break;
				
			}
			
			
			}while(choice!=0);
			
		}catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				// nothing
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		
	}

}