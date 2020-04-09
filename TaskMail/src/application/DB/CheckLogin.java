package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.interfaces.loginCheck;

public class CheckLogin implements loginCheck {
	private Connection con;
	private DBConnection conect;
	static Statement answer;
	private int idUser;
	
	
	public CheckLogin() {
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
	@Override
	public boolean check(String pass, String login) {
		ResultSet res = null;
		String query = "Select * from `users` where login = '"+login+"';";
		boolean conjunction = false;
		//Передача запроса
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			res = answer.executeQuery(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (res.next()) {
				String tablePass = res.getString(7);
				System.out.println(res.getString(3) + res.getString(7));
				int mark = pass.compareTo(tablePass);
				if(mark == 0) {
					this.idUser = res.getInt(1);
					conjunction = true;
				}
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {

		}
		return conjunction;
	}

}
