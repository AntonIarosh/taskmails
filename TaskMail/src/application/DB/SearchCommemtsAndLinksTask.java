package application.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Locale;
import application.Entities.EntityComment;
import application.Entities.EntityLink;

public class SearchCommemtsAndLinksTask {
	private Connection con;
	private DBConnection conect;
	static Statement answer;
	private int idTask;
	private LinkedList<EntityLink> dataLinks;
	private LinkedList<EntityComment> dataComments;
	private String searcQquery;
	
	public SearchCommemtsAndLinksTask() {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.setIdTask(0);
		this.dataLinks = new LinkedList<EntityLink>();
		this.dataComments = new LinkedList<EntityComment>();
	}
	public SearchCommemtsAndLinksTask(int _idTask) {
		this.conect = new DBConnection("taskmail");
		this.con = conect.connect();
		answer = null;
		this.setIdTask(_idTask);
		System.out.println("Конструктор айди в объекте запроса - " + this.idTask);
		this.dataLinks = new LinkedList<EntityLink>();
		this.dataComments = new LinkedList<EntityComment>();
	}

	public void setSearchQuery(String _query) {
		this.searcQquery = _query;
	}
	
	public String getSearchQuery() {
		return this.searcQquery;
	}
	

	public void whatIs() {
		String query = "SELECT * FROM `taskmail`.`task_link` WHERE `task_link`.`id_task` = '" + idTask + "';";
		ResultSet res = null;
		//String query = this.getSearchQuery();
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
			//this.dataComments.clear();
			this.dataLinks.clear();
			while (res.next()) {
				Locale.setDefault(new Locale("ru","RU"));

				
				EntityLink link = new EntityLink(res.getInt(1),res.getString(2));
				//task.setUrgency( whatUrgency(task.getIdUrgency()));
				this.dataLinks.add(link);
			//	String tablePass = res.getString(7);
				System.out.println (" ссылка " + res.getString(2)  );
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		String queryComment = "SELECT * FROM `taskmail`.`task_comment` WHERE `task_comment`.`id_task` = '" + idTask + "';";
		ResultSet resComment = null;
		//String query = this.getSearchQuery();
		System.out.println(queryComment);
		
		try {
			answer = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			resComment = answer.executeQuery(queryComment);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			this.dataComments.clear();
			//this.dataLinks.clear();
			while (resComment.next()) {
				Locale.setDefault(new Locale("ru","RU"));

				
				EntityComment comment = new EntityComment(resComment.getInt(1),resComment.getString(2));
				//task.setUrgency( whatUrgency(task.getIdUrgency()));
				this.dataComments.add(comment);
			//	String tablePass = res.getString(7);
				//System.out.println (" Комменты -  " + resComment.getString(2)  );
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
	}
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public LinkedList<EntityLink> getDataLinks() {
		return dataLinks;
	}
	public void setDataLinks(LinkedList<EntityLink> dataLinks) {
		this.dataLinks = dataLinks;
	}
	public LinkedList<EntityComment> getDataComments() {
		return dataComments;
	}
	public void setDataComments(LinkedList<EntityComment> dataComments) {
		this.dataComments = dataComments;
	}
}
