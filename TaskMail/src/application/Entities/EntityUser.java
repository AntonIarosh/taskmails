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
	// ���
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// �������
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	// ��������
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	// ������
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// �����
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// ���� �������������
	public int getIdPost() {
		return idPost;
	}
	
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	// ��� �������������.
	public String getNamePost() {
		return namePost;
	}
	public void setNamePost(String namePost) {
		this.namePost = namePost;
	}
}
