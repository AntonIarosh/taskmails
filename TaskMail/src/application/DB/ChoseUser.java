package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import application.Entities.EntityEmail;
import application.Entities.EntityOneUser;
import application.Entities.EntityUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChoseUser {
	private Connection con;
	private DBConnection conect;
	static Statement answer;
	private int idUser;
	private ObservableList<EntityOneUser> data;
	private String searcQquery;
	
	
	public ChoseUser() {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.idUser = 0;
		this.data = FXCollections.observableArrayList();
	}
	public ChoseUser(int id) {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.idUser = id;
		System.out.println("Конструктор айди в объекте запроса - " + this.idUser);
		this.data = FXCollections.observableArrayList();
	}
	
	public ObservableList<EntityOneUser> getData(){
		return this.data;
	}

	public void setId(int _id) {
		this.idUser = _id;
	}
	public void setSearchQuery(String _query) {
		this.searcQquery = _query;
	}
	
	public String getSearchQuery() {
		return this.searcQquery;
	}
	
	public int getId() {
		return this.idUser;
	}
	
	public HashMap <Integer, String>  whatIs() {
		HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
		//String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
		ResultSet res = null;
		String query = this.getSearchQuery();
		System.out.println(query);
		
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
			this.data.clear();
			while (res.next()) {
				EntityOneUser user = new EntityOneUser(res.getInt(1),res.getString(2), res.getString(3),res.getString(4) ,res.getString(6), res.getString(7),res.getInt(5));
				this.data.add(user);
			//	String tablePass = res.getString(7);
				System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7));
				resultItems.put(res.getInt(1), res.getString(2));
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return resultItems;
	}
	
	public EntityUser whoIsThis() {
		EntityUser person= new EntityUser();
		String queryUser = "Select * FROM `taskmail`.`users` WHERE `users`.`id_user` = '"+ this.idUser+"'";
		int idPost = 0;
		ResultSet res = null;
		System.out.println(queryUser);
		
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			res = answer.executeQuery(queryUser);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (res.next()) {
				person.setFirstName(res.getString(2));
				person.setSecondName(res.getString(3));
				person.setLastName(res.getString(4));
				person.setIdPost(res.getInt(5));
				person.setEmail(res.getString(6));
				person.setPassword(res.getString(7));
				idPost = res.getInt(5);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String queryPost = "SELECT * FROM `taskmail`.`post` WHERE `post`.`id_post` = '"+idPost+"'";
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			res = answer.executeQuery(queryPost);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (res.next()) {
				person.setNamePost(res.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return person;
	}
	
	public void updateData(String query) {
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("Добавление чувака");
			System.out.println(query);
			answer.executeUpdate(query);
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
}
