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
		public HashMap <Integer, String>  whatMailsIs() {
			HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
			String query = "SELECT * FROM (`taskmail`.`email` JOIN `taskmail`.`user_email` ON `email`.`id_email`= `user_email`.`id_email`) JOIN `taskmail`.`users` ON `user_email`.`id_user` = `users`.`id_user` WHERE `users`.`id_user` = '" + idUser +"'";
			ResultSet res = null;
			System.out.println(query);
			
			ChoiceDialog <String> dia = new ChoiceDialog <String>();
			dia.setTitle("Выбор электронной почты");
			dia.setHeaderText("Выберите один из адресов!");
			
			
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
				/*int count = 0;
				Optional<String> resultss = null;
				while (res.next()) {
					//EntityEmailAll email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
					dia.getItems().add(res.getString(2));
					count ++;
					
				}
				if (count > 1) {
					resultss = dia.showAndWait();
				}
				System.out.println("Выбранный адресс- " + resultss.toString());*/
				while (res.next()) {
				/*	if ((count > 1) && (res.getString(2).compareTo(resultss.toString()) == 0)) {
					EntityEmailAll email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
					dia.getItems().add(res.getString(2));
					this.data.add(email);
					this.dataEmail = email;
					
				//	String tablePass = res.getString(7);
					System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
					resultItems.put(res.getInt(1), res.getString(2));
					} else {*/
						EntityEmailAll email = new EntityEmailAll(res.getInt(1),res.getString(2), res.getString(3),res.getString(7) ,res.getInt(8) ,res.getString(4),res.getInt(5),res.getString(9),res.getString(6));
						dia.getItems().add(res.getString(2));
						this.data.add(email);
						this.dataEmail = email;
						
					//	String tablePass = res.getString(7);
						System.out.println(res.getString(1) +" "+res.getString(2)+" " +res.getString(3)+" "+ res.getString(4)+" "+ res.getInt(5)+" "+res.getString(7) + " "+res.getInt(8) );
						resultItems.put(res.getInt(1), res.getString(2));
					//}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			return resultItems;
		}
}
