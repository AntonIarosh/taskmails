package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import application.Entities.EntityEmail;
import application.Entities.EntityUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChanngeUserInfos {
	private Connection con;
	private DBConnection conect;
	static Statement answer;
	private int idUser;
	private ObservableList<EntityEmail> data;
	
	
	public ChanngeUserInfos() {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.idUser = 0;
		this.data = FXCollections.observableArrayList();
	}
	public ChanngeUserInfos(int id) {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.idUser = id;
		System.out.println("Конструктор айди в объекте запроса - " + this.idUser);
		this.data = FXCollections.observableArrayList();
	}
	
	public ObservableList<EntityEmail> getData(){
		return this.data;
	}

	public void setId(int _id) {
		this.idUser = _id;
	}
	public int getId() {
		return this.idUser;
	}
	
	public ObservableList<EntityEmail>   whatMailsIs() {
	//public HashMap <Integer, String>  whatMailsIs() {
		HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
		ObservableList<EntityEmail> dataf = FXCollections.observableArrayList();
		String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
		ResultSet res = null;
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
				EntityEmail email = new EntityEmail(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5));
				this.data.add(email);
				dataf.add(email);
			//	String tablePass = res.getString(7);
				System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
				resultItems.put(res.getInt(1), res.getString(2));
				System.out.println(" Размер данных в запросе - " + this.data.size());
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return dataf;
		//return resultItems;
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
			//System.out.println("Добавление чувака");
			System.out.println(query);
			answer.executeUpdate(query);
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	public ObservableList<EntityEmail> whatMailsIsOnWorker(String _name, String _secondName, String _lastName) {
		//public HashMap <Integer, String>  whatMailsIs() {
		System.out.println(" Имя - |" +_name + "| фамилиya - |" +  _secondName + "| отчество - |" + _lastName +"|");
			HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
			ObservableList<EntityEmail> dataf = FXCollections.observableArrayList();
			String query = null;
			if ((_name.compareTo("") != 0) && (_secondName.compareTo("") != 0) && (_lastName.compareTo("") != 0)) {
			query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` "
					+ "WHERE `users`.`firstname` = '" +_name +"' AND `users`.`secondname` = '" + _secondName + "' AND `users`.`lastname` = '" + _lastName+ "';";
			}
			if ((_name.compareTo("") != 0) && (_secondName.compareTo("") != 0) && (_lastName.compareTo("") == 0)) {
			query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` "
					+ "WHERE `users`.`firstname` = '" +_name +"' AND `users`.`secondname` = '" + _secondName + "';";
			}
			if ((_name.compareTo("") != 0) && (_secondName.compareTo("") == 0) && (_lastName.compareTo("") == 0)) {
			query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` "
					+ "WHERE `users`.`firstname` = '" +_name +"';";
			}
			if ((_name.compareTo("") == 0) && (_secondName.compareTo("") != 0) && (_lastName.compareTo("") == 0)) {
			query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` "
					+ "WHERE `users`.`secondname` = '" + _secondName + "';";
			}
			
			
			ResultSet res = null;
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
					EntityEmail email = new EntityEmail(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5));
					this.data.add(email);
					dataf.add(email);
				//	String tablePass = res.getString(7);
					//System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
					resultItems.put(res.getInt(1), res.getString(2));
					//System.out.println(" Размер данных в запросе - " + this.data.size());
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return dataf;
			//return resultItems;
		}
}
