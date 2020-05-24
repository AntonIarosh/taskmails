package application.Entities;

import javafx.beans.property.SimpleIntegerProperty;
public class EntityEmailAll {
		private SimpleIntegerProperty id;
		//private SimpleStringProperty email;
		private String email;
		//private SimpleStringProperty password;
		private String password;
		
		
		private String IMAPserver;
		private SimpleIntegerProperty codeIMAP;
		private String SMPTserver;
		private SimpleIntegerProperty codeSMTP;
		
		private String IMAPneed;
		private String SMTPneed;

		public EntityEmailAll() {
			this.id = null;
			this.email =null;
			this.password=null;
			
			this.codeIMAP = null;
			this.codeSMTP  = null;
			this.SMPTserver  = null;
			this.IMAPserver  = null;
			
			this.SMTPneed  = null;
			this.IMAPneed  = null;
			
		}
		public EntityEmailAll (int _id, String _email, String _password, String _SMPTserver, int _codeSMTP, String _IMAPserver, int _codeIMAP, String _SMTPneed, String _IMAPneed) {
			this.id = new SimpleIntegerProperty( _id);
			this.email = _email;
			//this.email = new SimpleStringProperty(_email);
			//this.password= new SimpleStringProperty(_password);
			this.password= _password;
			
			this.codeIMAP = new SimpleIntegerProperty( _codeIMAP);
			this.codeSMTP  = new SimpleIntegerProperty( _codeSMTP);
			this.SMPTserver  = _SMPTserver;
			this.IMAPserver  = _IMAPserver;
			
			this.SMTPneed  =_SMTPneed;
			this.IMAPneed  = _IMAPneed;
		}
		
		public void setId(int _id) {
			this.id = new SimpleIntegerProperty( _id);
		}
		public void setCodeIMAP(int _CodeIMAP) {
			this.codeIMAP = new SimpleIntegerProperty( _CodeIMAP);
		}
		public void setCodeSMPTserver(int _SMPTserver) {
			this.codeSMTP = new SimpleIntegerProperty( _SMPTserver);
		}
		public void setEmail(String _email) {
			//this.email = new SimpleStringProperty(_email);
			this.email = _email;
		}
		public void setPassword(String _password) {
			//this.password = new SimpleStringProperty(_password);
			this.password = _password;
		}
		public void setSMPTserver(String _SMPTserver) {
			//this.password = new SimpleStringProperty(_password);
			this.SMPTserver = _SMPTserver;
		}
		public void setIMAPserver(String _IMAPserver) {
			//this.password = new SimpleStringProperty(_password);
			this.IMAPserver = _IMAPserver;
		}
		
		public int getId() {
			return this.id.intValue();
		}
		
		public int getCodeIMAP() {
			return this.codeIMAP.intValue();
		}
		public int getCodeSMTP() {
			return this.codeSMTP.intValue();
		}
		public String getSMPTserver() {
			return this.SMPTserver.toString();
		}
		public String getIMAPserver() {
			return this.IMAPserver.toString();
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
		public Class <?> getClassCodeIMAP() {
			Integer intts = 0;
			return intts.getClass();
		}
		public Class <?> getClassCodeSMTP() {
			Integer intss = 0;
			return intss.getClass();
		}
		public Class <?> getClassEmail() {
			String p ="";
			return p.getClass();
			//return this.email.getClass();
		}
		
		public Class <?> getClassSMPTserver() {
			String ff ="";
			return ff.getClass();
			//return this.email.getClass();
		}
		public Class <?> getClassIMAPserver() {
			String f ="";
			return f.getClass();
			//return this.email.getClass();
		}
		public String getIMAPneed() {
			return IMAPneed;
		}
		public void setIMAPneed(String iMAPneed) {
			IMAPneed = iMAPneed;
		}
		public String getSMTPneed() {
			return SMTPneed;
		}
		public void setSMTPneed(String sMTPneed) {
			SMTPneed = sMTPneed;
		}
		
	
}
