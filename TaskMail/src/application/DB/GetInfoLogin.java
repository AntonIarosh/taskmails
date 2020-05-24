package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetInfoLogin extends CheckLogin {
	private String name;
	private String secondName;
	private String job;
	private String lastName;
	
	public GetInfoLogin() {
		name = null;
		secondName=null;
		job =null; 
		lastName = null;
		/*this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;*/
	}
	
	public void setID(int _idUser) {
		//this.id = _idUser;
		super.setId(_idUser);
	}
	public void setJob(String _job) {
		this.job = _job;
	}
	public void setName(String _name) {
		this.name = _name;
	}
	public void setSecondName(String _secondName) {
		this.secondName = _secondName;
	}
	
	public String getJob() {
		return this.job;
	}
	public String getName() {
		return this.name;
	}
	public String getSecondName() {
		return this.secondName;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	public void wantKnown() {
		String query = "Select * from `taskmail`.`users` where `id_user` = '" + super.getId()+"';";
		Connection con = super.getConnection();
		ResultSet res = null;
		//Передача запроса
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
				this.lastName = res.getString(4);
				this.name = res.getString(2);
				this.secondName =  res.getString(3);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String queryNameJob = "SELECT `post`.`name_post` FROM `taskmail`.`post` JOIN `taskmail`.`users` ON `post`.`id_post` = `users`.`id_post` WHERE `users`.`id_user` = '"+super.getId()+"'";
		System.out.println("Выбор специализации");
		System.out.println("queryNameJob");
		ResultSet nameJob = null;
		try {
			answer = con.createStatement(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println(query);
			nameJob = answer.executeQuery(queryNameJob);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (nameJob.next()) {
				this.job = nameJob.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {

		}
	}
}
