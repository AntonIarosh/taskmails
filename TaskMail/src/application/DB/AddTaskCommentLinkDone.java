package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddTaskCommentLinkDone {
	private boolean executed;
	private Connection con;
	private DBConnection conect;
	private String query;
	static Statement answer;
	
	public AddTaskCommentLinkDone() {
		this.query = null;
		this.executed = false;
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
	}
	
	public void setQuery(String newQuery) {
		this.query = newQuery;
	}

	// ���������� ������������

	public boolean execeteQuery() {
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//System.out.println("���������� ������");
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
	
	
	/*public int addMail() {
	// -----���������� � ������� ����� ---/
		
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
			System.out.println("���������� � ������� �����");
			System.out.println(query);
			answer.executeUpdate(query);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		/// ---/
		// ������ ����� � ������ ������ ����. ---/
		try {
			System.out.println("������ ����� � ������ ������ ����");
			System.out.println("SELECT * FROM `taskmail`.`email` WHERE `email` = '"+this.email +"';");
			res = answer.executeQuery("SELECT * FROM `taskmail`.`email` WHERE (`email` = '"+this.email +"');");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		try {
			while (res.next()) {
				idMail = res.getInt(1);
				System.out.println("������ ����� � ������ ������ ���� - �����" + idMail);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		///---/
		/// �������� ���������� � �� ---/
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/// ---/
		
		return idMail;
	}*/
	

}
