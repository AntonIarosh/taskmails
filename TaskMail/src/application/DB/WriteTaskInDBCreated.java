package application.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WriteTaskInDBCreated {
	private boolean executed;
	private Connection con;
	private DBConnection conect;
	private String query;
	static Statement answer;
	private String email;
	
	public WriteTaskInDBCreated(String query) {
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
	
	// Добавление задачи

	public boolean execeteQuery() {
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("Добавление новой задачи в бд - ");
			System.out.println(query);
			answer.executeUpdate(query);
			// Сообщение об успехе -- /
			Alert alert = new Alert(AlertType.INFORMATION,"Задание было добавлено в расписание, а также сохранено в Вашей базе данных");
			alert.setTitle("Создание задания");
			alert.setHeaderText("Задание сформировано и добавлено!");
			alert.show();
			// Коенец сообщение об успехе -- /
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
		System.out.println("Проверка последней добавленнной задачи (айди) - " + query);
		this.con = conect.connect();
		int idTask =0;
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println("WhoAdd");
			System.out.println(query);
			res = answer.executeQuery(query);
		
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		try {
			while (res.next()) {
				idTask = res.getInt(1);
				System.out.println("узнать какой айди у новой задачи - " + idTask);
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

	
		return idTask;
	}
	

	
	//Добавить записи в таблицу - задача пользователя
	public void addUserTask(String query) {
		//this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("Добавить записи в таблицу - задач пользователя");
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
	
	// Добавление ссылки к таблице.
	public void addLink(String query) {
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("Добавить записи в таблицу - ссылка");
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
