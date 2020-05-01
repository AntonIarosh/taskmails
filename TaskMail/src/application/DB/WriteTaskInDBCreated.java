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
	
	// ���������� ������

	public boolean execeteQuery() {
		ResultSet res = null;
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("���������� ����� ������ � �� - ");
			System.out.println(query);
			answer.executeUpdate(query);
			// ��������� �� ������ -- /
			Alert alert = new Alert(AlertType.INFORMATION,"������� ���� ��������� � ����������, � ����� ��������� � ����� ���� ������");
			alert.setTitle("�������� �������");
			alert.setHeaderText("������� ������������ � ���������!");
			alert.show();
			// ������ ��������� �� ������ -- /
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
		System.out.println("�������� ��������� ������������ ������ (����) - " + query);
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
				System.out.println("������ ����� ���� � ����� ������ - " + idTask);
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
	

	
	//�������� ������ � ������� - ������ ������������
	public void addUserTask(String query) {
		//this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("�������� ������ � ������� - ����� ������������");
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
	
	// ���������� ������ � �������.
	public void addLink(String query) {
		this.con = conect.connect();
		try {
			answer = con.createStatement();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			System.out.println("�������� ������ � ������� - ������");
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
