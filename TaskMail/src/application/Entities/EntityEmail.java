package application.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EntityEmail {
	private SimpleIntegerProperty id;
	//private SimpleStringProperty email;
	private String email;
	//private SimpleStringProperty password;
	private String password;

	public EntityEmail() {
		this.id = null;
		this.email =null;
		this.password=null;
	}
	public EntityEmail(int _id, String _email, String _password) {
		this.id = new SimpleIntegerProperty( _id);
		this.email = _email;
		//this.email = new SimpleStringProperty(_email);
		//this.password= new SimpleStringProperty(_password);
		this.password= _password;
	}
	
	public void setId(int _id) {
		this.id = new SimpleIntegerProperty( _id);
	}
	public void setEmail(String _email) {
		//this.email = new SimpleStringProperty(_email);
		this.email = _email;
	}
	public void setPassword(String _password) {
		//this.password = new SimpleStringProperty(_password);
		this.password = _password;
	}
	
	public int getId() {
		return this.id.intValue();
	}
	
	public String getEmail() {
		return this.email.toString();
	}
	public String getPassword() {
		return this.password.toString();
	}
	// Тип класса поля.
	public Class <?> getClassPassword() {
		String p ="";
		return p.getClass();
		//return this.password.getClass();
		
	}
	public Class <?> getClassId() {
		Integer ints = 0;
		return ints.getClass();
	}
	public Class <?> getClassEmail() {
		String p ="";
		return p.getClass();
		//return this.email.getClass();
	}
}
