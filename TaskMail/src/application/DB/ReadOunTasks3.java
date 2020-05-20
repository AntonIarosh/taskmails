package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.SimpleTimeZone;
import application.Entities.EntityTask;
import application.Entities.EntityUser;

public class ReadOunTasks3 {

			private Connection con;
			private DBConnection conect;
			static Statement answer;
			private int idUser;
			private LinkedList<EntityTask> data;
			private String searcQquery;
			private String person;
			
			
			public ReadOunTasks3() {
				this.conect = new DBConnection("taskmail");
				this.con = conect.connect();
				answer = null;
				this.idUser = 0;
				this.data = new LinkedList<EntityTask>();
				this.person = null;
			}
			public ReadOunTasks3(String _person) {
				this.conect = new DBConnection("taskmail");
				this.con = conect.connect();
				answer = null;
				this.idUser = 0;
				this.person = _person;
				System.out.println("Конструктор айди в объекте запроса - " + this.idUser);
				this.data = new LinkedList<EntityTask>();
			}
			
			public LinkedList <EntityTask> getData(){
				return this.data;
			}
			public void setData(LinkedList <EntityTask> d) {
				this.data = d;
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
			
			public HashMap <Date, EntityTask>   whatIs() {
				HashMap <Date, EntityTask> resultItems = new HashMap <Date, EntityTask>();
				//String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
				LinkedList<EntityTask> datas = new LinkedList<EntityTask>();
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
						Locale.setDefault(new Locale("ru","RU"));
						
						GregorianCalendar dateCreate = new GregorianCalendar();
						dateCreate.setTime(res.getTimestamp(5));
						Date dateC = dateCreate.getTime();
						//Date dateC = res.getDate(5);
						
						GregorianCalendar dateStart = new GregorianCalendar(new Locale("ru","RU"));
						dateStart.setTime(res.getTimestamp(6));
						//dateStart.set setTime(res.getDate(6));
						//Date dateS = dateStart.getTime();
						Date dateS = dateStart.getTime();
						//Time s = res.getTime(6);
						
						GregorianCalendar dateEnd = new GregorianCalendar(new SimpleTimeZone(3*60*60*1000,"Europe/Moscow"));
						dateEnd.setTime(res.getTimestamp(7));
						Date dateE = dateEnd.getTime();
						
						EntityTask task = new EntityTask(res.getInt(1),res.getString(2), res.getString(3),res.getInt(4) ,/*res.getDate(5), 
						res.getDate(6),res.getDate(7)*/dateC, dateS ,dateE ,res.getString(8),res.getInt(9),res.getString(10),res.getInt(11),res.getString(12));
						task.setUrgency( whatUrgency(task.getIdUrgency()));
						this.data.add(task);
						datas.add(task);
					
					//	String tablePass = res.getString(7);
						//System.out.println (" Даты: " + res.getTime(6) + " | " + dateS + " | " +  res.getDate(6) );
						//resultItems.put(res.getDate(5), task);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				this.setData(datas);
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
			

			public String getPerson() {
				return person;
			}
			public void setPerson(String person) {
				this.person = person;
			}
			
			// узнать срочность -- /
			public String whatUrgency(int idUrgency) {
				String urgency = null;
				String query = "SELECT `urgency` FROM `taskmail`.`urgency` WHERE `urgency`.`id_urgency` = " +idUrgency + "; ";
				
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
					while (res.next()) {
						urgency  = res.getString(1);
					//	String tablePass = res.getString(7);
						//System.out.println("Срочность -  "+res.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				return urgency;
			}
			// конец срочности -- /
			public /*HashMap <Date, EntityTask>  */ LinkedList<EntityTask> what() {
				//HashMap <Date, EntityTask> resultItems = new HashMap <Date, EntityTask>();
				//String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
				LinkedList<EntityTask> dataset = new LinkedList<EntityTask>();
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
						Locale.setDefault(new Locale("ru","RU"));
						
						GregorianCalendar dateCreate = new GregorianCalendar();
						dateCreate.setTime(res.getTimestamp(5));
						Date dateC = dateCreate.getTime();
						//Date dateC = res.getDate(5);
						
						GregorianCalendar dateStart = new GregorianCalendar(new Locale("ru","RU"));
						dateStart.setTime(res.getTimestamp(6));
						//dateStart.set setTime(res.getDate(6));
						//Date dateS = dateStart.getTime();
						Date dateS = dateStart.getTime();
						//Time s = res.getTime(6);
						
						GregorianCalendar dateEnd = new GregorianCalendar(new SimpleTimeZone(3*60*60*1000,"Europe/Moscow"));
						dateEnd.setTime(res.getTimestamp(7));
						Date dateE = dateEnd.getTime();
						
						EntityTask task = new EntityTask(res.getInt(1),res.getString(2), res.getString(3),res.getInt(4) ,/*res.getDate(5), 
						res.getDate(6),res.getDate(7)*/dateC, dateS ,dateE ,res.getString(8),res.getInt(9),res.getString(10),res.getInt(11),res.getString(12));
						task.setUrgency( whatUrgency(task.getIdUrgency()));
				
						dataset.add(task);
					//	String tablePass = res.getString(7);
						//System.out.println (" Даты: " + res.getTime(6) + " | " + dateS + " | " +  res.getDate(6) );
						//resultItems.put(res.getDate(5), task);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				return dataset;//resultItems;
			}
}
