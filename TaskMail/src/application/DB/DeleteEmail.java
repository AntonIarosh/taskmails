package application.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.interfaces.addToDBUser;

public class DeleteEmail {

		private boolean executed;
		private Connection con;
		private DBConnection conect;
		private String query;
		static Statement answer;
		private String email;
		
		public DeleteEmail(String query) {
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
		// Поиск количества пользователей данного адреса

		public boolean serchQuery() {
			ResultSet res = null;
			try {
				answer = con.createStatement();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				System.out.println(query);
				res = answer.executeQuery(query);
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			try {
				while (res.next()) {
					//int count = res.getInt(1);
					if (res.getInt(1) > 1) {
						this.executed = true;
						System.out.println("Количество - " + res.getInt(1));
					} else {
						this.executed = false;
					}
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		

		
		return executed;
		}
		public int whatId() {
			int idMail =-1;
			ResultSet res = null;
			try {
				answer = con.createStatement();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				
					System.out.println(query);
					res = answer.executeQuery(query);
					
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			try {
				while (res.next()) {
					idMail = res.getInt(1);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
		return idMail ;
		}
		
		public void close() {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
