package application.Entities;

import java.util.Date;

public class EntityTask {
	
	private int idTask;
	private String title;
	
	private String body;
	
/*	private String dateStrart;
	private String dateEnd;
	private String dateCreate;*/
	private Date dateStrart;
	private Date dateEnd;
	private Date dateCreate;
	
	private String supervisor;
	private int isDone;
	private String taskCol; // Периодичность
	private String Description;
	
	private int idUrgency;
	private int idParent;
	
	private String urgency;
	
	public EntityTask () {
		this.idTask = 0;
		this.title = null;
		
		this.body = null;
		this.dateStrart = null;
		this.dateEnd = null;
		this.dateCreate = null;
		this.supervisor = null;
		this.isDone= 0;
		this.taskCol = null; // Периодичность
		this.Description = null;
		
		this.idUrgency = 0;
		this.idParent = 0;
		
		this.setUrgency(null);
	}
	
	public EntityTask (int _idTask, String _title, String _body, int _idUrgency, Date _dateCreate, Date _dateStrart, Date _dateEnd, String _supervisor, int _isDone,
			String _taskCol,int _idParent , String _Description) {
		this.idTask = _idTask;
		this.title = _title;
		
		this.body = _body;
		this.dateStrart =  _dateStrart;
		this.dateEnd = _dateEnd;
		this.dateCreate = _dateCreate;
		this.supervisor =  _supervisor;
		this.isDone = _isDone;
		this.taskCol = _taskCol; // Периодичность
		this.Description = _Description;
		
		this.idUrgency = _idUrgency;
		this.idParent = _idParent;
		
		this.setUrgency(null);
	}
	
	public int getIdTask() {
		return idTask;
	}
	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBode() {
		return body;
	}
	public void setBode(String bode) {
		this.body = bode;
	}
	public Date getDateStrart() {
		return dateStrart;
	}
	public void setDateStrart(Date dateStrart) {
		this.dateStrart = dateStrart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public int getIsDone() {
		return isDone;
	}
	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
	public String getTaskCol() {
		return taskCol;
	}
	public void setTaskCol(String taskCol) {
		this.taskCol = taskCol;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getIdUrgency() {
		return idUrgency;
	}
	public void setIdUrgency(int idUrgency) {
		this.idUrgency = idUrgency;
	}
	public int getIdParent() {
		return idParent;
	}
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
}
