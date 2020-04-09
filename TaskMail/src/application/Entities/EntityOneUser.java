package application.Entities;

import javafx.beans.property.SimpleIntegerProperty;

public class EntityOneUser {
	private SimpleIntegerProperty id;
	private String firstName;
	private String secondName;
	private String lastName;
	private String password;
	private String email;
	private SimpleIntegerProperty idPost;
	private String namePost;
	
	public EntityOneUser() {
		this.id = null;
		this. firstName = null;
		this.  secondName = null;
		this.  lastName = null;
		this.  password = null;
		this.  email = null;
		this.  idPost = null;
		this.  namePost = null;
	}
	
	public EntityOneUser(int _id,String _firstName, String _secondName, String _lastName, String _password, String login, int _idPOst) {
		this.id = new SimpleIntegerProperty( _id);
		this. firstName = _firstName;
		this.  secondName = _secondName;
		this.  lastName = _lastName;
		this.  password = _password;
		this.  email = login;
		this.  idPost = new SimpleIntegerProperty(_idPOst);
		this.  namePost = null;
	}
	// Имя
	public String getFirstName() {
		return firstName.toString();
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// Фамилия
	public String getSecondName() {
		return secondName.toString();
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	// Отчество
	public String getLastName() {
		return lastName.toString();
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// Пароль
	public String getPassword() {
		return password.toString();
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// Эмэйл
	public String getEmail() {
		return email.toString();
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// Айди специализации
	public int getIdPost() {
		return this.idPost.intValue();
	}
	
	public int getId() {
		return this.id.intValue();
	}
	
	public void setIdPost(int idPost) {
		this.idPost = new SimpleIntegerProperty( idPost);
	}
	public void setId(int id) {
		this.idPost = new SimpleIntegerProperty( id);
	}
	// Имя специализации.
	public String getNamePost() {
		return namePost;
	}
	public void setNamePost(String namePost) {
		this.namePost = namePost;
	}
	
	
	public Class <?> getClassFirstName() {
		String f ="";
		return f.getClass();
		//return this.email.getClass();
	}
	public Class <?> getClassSecondName() {
		String ff ="";
		return ff.getClass();
		//return this.email.getClass();
	}
	public Class <?> getClassLastName() {
		String fd ="";
		return fd.getClass();
		//return this.email.getClass();
	}
}
