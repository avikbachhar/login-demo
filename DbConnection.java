package awt.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	static Connection con=null;
	
	public static Connection getConnection() {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			con = DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:orcl","Avikbachhar","123456");
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return con;
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
