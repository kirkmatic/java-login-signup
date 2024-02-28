import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MySQLConnection {
	public static Connection conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost/bokshop";
			Connection conn = DriverManager.getConnection(jdbcUrl,"root","");
			System.out.println("SQL connected");
			return conn;
		}catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return null;
	}
}
