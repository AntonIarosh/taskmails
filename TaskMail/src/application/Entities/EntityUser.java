package application.Entities;

public class EntityUser {
	private String firstName;
	private String secondName;
	private String lastName;
	private String password;
	private String email;
	private int idPost;
	private String namePost;
	
	public EntityUser() {
		this. firstName = null;
		this.  secondName = null;
		this.  lastName = null;
		this.  password = null;
		this.  email = null;
		this.  idPost = 0;
		this.  namePost = null;
	}
	// Имя
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// Фамилия
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	// Отчество
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// Пароль
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// Эмэйл
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// Айди специализации
	public int getIdPost() {
		return idPost;
	}
	
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	// Имя специализации.
	public String getNamePost() {
		return namePost;
	}
	public void setNamePost(String namePost) {
		this.namePost = namePost;
	}
}
