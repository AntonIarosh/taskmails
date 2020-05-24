package application.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChoseIdUserByFIO {
	private Connection con;
	private DBConnection conect;
	static Statement answer;
	private int idUser;
	private String Question;
	
	
	public ChoseIdUserByFIO() {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.idUser = 0;
	}
	public void setId(int _id) {
		this.idUser = _id;
	}
	public int getId() {
		return this.idUser;
	}
	
	public Connection  getConnection () {
		return this.con;
	}

	public int checkId() {
		ResultSet res = null;
		int idUser = -1;
		boolean conjunction = false;
		//ѕередача запроса
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("запрос на поиск айди - "+ this.Question);
			res = answer.executeQuery(this.Question);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (res.next()) {
				//String tablePass = res.getString(7);
				//System.out.println("јйди пользовател€"+ res.getInt(1));
				idUser = res.getInt(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {

		}
		return idUser;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}

}
