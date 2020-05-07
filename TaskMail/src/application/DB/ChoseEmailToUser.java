package application.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Optional;

import application.Entities.EntityEmail;
import application.Entities.EntityEmailAll;
import application.Entities.EntityUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;

public class ChoseEmailToUser {
		private Connection con;
		private DBConnection conect;
		static Statement answer;
		private int idUser;
		private EntityEmailAll dataEmail;
		private ObservableList<EntityEmailAll> data;

		public ChoseEmailToUser () {
			this.conect = new DBConnection("taskmail");
			this.con = conect.connect();
			answer = null;
			dataEmail = null;
			this.idUser = 0;
			this.data = FXCollections.observableArrayList();
		}
		public ChoseEmailToUser(int idUser) {
			this.conect = new DBConnection("taskmail");
			this.con = conect.connect();
			answer = null;
			dataEmail = null;
			this.idUser = idUser;
			System.out.println("Конструктор айди в объекте запроса - " + this.idUser);
			this.data = FXCollections.observableArrayList();
		}
		
		public ObservableList<EntityEmailAll> getData(){
			return this.data;
		}

		public void setId(int _id) {
			this.idUser = _id;
		}
		public int getId() {
			return this.idUser;
		}
		public void setDataEmail(EntityEmailAll em) {
			this.dataEmail = em;
		}
		public EntityEmailAll getDataEmail() {
			return this.dataEmail;
		}
		public EntityEmailAll  whatMailsIs() {
			String queryUser = "SELECT * FROM `taskmail`.`users` WHERE `users`.`id_user` = '" + idUser + "';";
			ResultSet res = null;
			EntityUser user = new EntityUser();
			// -- Поиск пользователя -- /
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
					//EntityUser user = new EntityUser();
					user.setFirstName(res.getString(2));
					user.setSecondName(res.getString(3));
					user.setLastName(res.getString(4));
					System.out.println(res.getString(2)+" " +res.getString(3)+" "+ res.getString(4) );
					
				//}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
			// -- Конец поиска пользователя -- /
			
			
			HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
			String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
			res = null;
			System.out.println(query);
			
			ChoiceDialog <String> dia = new ChoiceDialog <String>();
			dia.setTitle("Выбор электронной почты пользователя: " + user.getFirstName() + " " + user.getSecondName() + " " + user.getLastName());
			dia.setHeaderText("Выберите один из адресов!");
			dia.setContentText("Для определения конкретного адреса электронной почты в качестве приёма/отправки писем.");
			EntityEmailAll email = null;
			
			try {
				answer = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				res = answer.executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				int count = 0;
				Optional<String> resultss = null;
				while (res.next()) {
					//EntityEmailAll email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
					dia.getItems().add(res.getString(2));
					count ++;
					//System.out.println(" Адрес - " + res.getString(2) + " номер адреса - " + count);
					
				}
				res.beforeFirst();
				if (count > 1) {
					resultss = dia.showAndWait();
				}
				
				while (res.next()) {
					if (count > 1)  {
						System.out.println(" Выполнилось больше одного ");
						if ((res.getString(2).compareTo(resultss.get()) == 0)) {
							System.out.println("Выбранный адресс- " + resultss.get());
							email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
							dia.getItems().add(res.getString(2));
							this.data.add(email);
							this.dataEmail = email;
							//System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
							resultItems.put(res.getInt(1), res.getString(2));
						}

					} else {
						System.out.println(" Выполнилcя один адрес ");
						email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
						dia.getItems().add(res.getString(2));
						this.data.add(email);
						this.dataEmail = email;
						
					//	String tablePass = res.getString(7);
						//System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
						resultItems.put(res.getInt(1), res.getString(2));
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			return email;
		}
}
