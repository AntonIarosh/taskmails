package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.interfaces.addToDBUser;

public class AddUser implements addToDBUser {
	private boolean executed;
	private Connection con;
	private DBConnection conect;
	private String query;
	static Statement answer;
	private String email;
	
	public AddUser(String query) {
		this.query = query;
		this.executed = false;
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		this.email = null;
		answer = null;
	}
	
	public void setQuery(String newQuery) {
		this.query = newQuery;
	}
	public void setMail(String newMail) {
		this.email = newMail;
	}
	// Добавление пользователя
	@Override
	public boolean execeteQuery() {
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//System.out.println("Добавление чувака");
			System.out.println(query);
			answer.executeUpdate(query);
			this.executed = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
	try {
		if (con != null)
			con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return executed;
	}
	
	public int WhoAdd(String query) {
		//this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		int idPerson =0;
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			//System.out.println("WhoAdd");
			System.out.println(query);
			res = answer.executeQuery(query);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		try {
			while (res.next()) {
				idPerson = res.getInt(1);
				//System.out.println("узнать какой у нового у чувака - итого" + idPerson);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	try {
		if (con != null)
			con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	
		return idPerson;
	}
	
	public int addMail() {
		// -----Добавление в таблицу эмейл ---/
		
		int idMail = 0;
		ResultSet res = null;
		//this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//System.out.println("Добавление в таблицу эмейл");
			System.out.println(query);
			answer.executeUpdate(query);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		/// ---/
		// узнать какой у нового эмэйла айди. ---/
		try {
			//System.out.println("узнать какой у нового эмэйла айди");
			//System.out.println("SELECT * FROM `taskmail`.`email` WHERE `email` = '"+this.email +"';");
			res = answer.executeQuery("SELECT * FROM `taskmail`.`email` WHERE (`email` = '"+this.email +"');");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		try {
			while (res.next()) {
				idMail = res.getInt(1);
				//System.out.println("узнать какой у нового эмэйла айди - итого" + idMail);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		///---/
		/// Закрытие соединения с БД ---/
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/// ---/
		
		return idMail;
	}
	
	//Добавить записи в таблицу - почта пользователя
	public void addUserEmail(String query) {
		//this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//System.out.println("Добавить записи в таблицу - почта пользователя");
			System.out.println(query);
			answer.executeUpdate(query);
			this.executed = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
	try {
		if (con != null)
			con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
}
