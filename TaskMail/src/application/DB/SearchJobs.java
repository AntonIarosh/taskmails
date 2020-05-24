package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SearchJobs {
	private Connection con;
	private DBConnection conect;
	private static Statement answer;
	private String query;
	private int idPost;
	private String namePost;
	
	
	public SearchJobs() {
		con = null;
		conect =null;
		answer = null;
		query = null;
	}
	
	public void setQuery(String newQuery) {
		this.query = newQuery;
	}
	public int getIdPost() {
		return this.idPost;
	}
	
	public void setNamePost(String post) {
		this.namePost = post;
	}
	
	public HashMap <Integer, String>  doQery() {
	HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
	
	try {
		DBConnection conect = new DBConnection("taskmail");
		con = conect.connect();
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	ResultSet someAnswers = null;
	
	try {
		someAnswers = answer.executeQuery(this.query);
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	try {
		while (someAnswers.next()) {
			resultItems.put(someAnswers.getInt(1), someAnswers.getString(2));
			//System.out.println(gg.getString(3) + gg.getString(2));
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
	
	return resultItems;
	}
	
	public int addPost(String query) {
		int idPost =0;
		try {
			DBConnection conect = new DBConnection("taskmail");
			con = conect.connect();
			try {
				answer = con.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			answer.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		ResultSet someAnswers = null;
		try {
			someAnswers = answer.executeQuery("SELECT * FROM `taskmail`.`post` WHERE `name_post` = '" + this.namePost +"';");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (someAnswers.next()) {
				idPost  = someAnswers.getInt(1);
				//System.out.println(gg.getString(3) + gg.getString(2));
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

		
		
		return idPost;
	}
}

