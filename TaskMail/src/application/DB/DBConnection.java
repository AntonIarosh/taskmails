package application.DB;
import java.sql.*;

public class DBConnection {
	private String nameDB;
	private Connection con = null;
	
	public DBConnection(String name) {
		this.nameDB = name;
	}
	public Connection connect() {
		String url = "jdbc:mysql://localhost:3380/" +this.nameDB + "?verifyServerCertificate=false" +
				 "&useSSL=false" + "&requireSSL = false" + "&useLegacyDatetimeCode=false" +
				"&amp"+"&serverTimezone = Europe/Moscow";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url/* "jdbc:mysql://localhost:3306/" + db + "?characterEncoding=utf8" */,
					"root", "jhbjy333");

		} catch (ClassNotFoundException e) {
			System.out.println("| Драйвер не найден ");
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("| Ошибка: " + e.getMessage());
			System.exit(1);
		}
		return con;
	}
	
}
