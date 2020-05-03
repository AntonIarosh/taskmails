package application.Mails;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;

import application.DB.ChoseEmailToUser;
import application.Entities.EntityEmail;
import application.Entities.EntityEmailAll;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
//import sun.security.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;


public class LetterRecive {
	String   IMAP_AUTH_EMAIL = null;
    String   IMAP_AUTH_PWD   = null           ;
    String   IMAP_Server     = null;
    String   IMAP_Port       = null     ;
    String   IMAP_NEED = null;
    private String email;
    private static Stage primaryStage;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public LetterRecive(Stage _primaryStage, int idUser)
    {
    	this.setPrimaryStage(_primaryStage);
    	ParseText parse = new ParseText(idUser);
    	EntityEmailAll mailTo = new EntityEmailAll();
		//EntityEmailAll mailTo = new EntityEmailAll();
		
		// ������ ���������� ��������� -- /
		ChoseEmailToUser who = new ChoseEmailToUser(idUser);
		//who.whatMailsIs();
		//mailTo = who.getDataEmail();
		mailTo = who.whatMailsIs();
		System.out.println("���������� ��������� �������� ������������");
		this.IMAP_NEED = mailTo.getIMAPneed();
		this.IMAP_Port = Integer.toString( mailTo.getCodeIMAP());
		this.IMAP_Server = mailTo.getIMAPserver();
		this.IMAP_AUTH_EMAIL = mailTo.getEmail();
		this.IMAP_AUTH_PWD = mailTo.getPassword();
    	
        Properties properties = new Properties();
        properties.put("mail.debug"          , "false"  );
        properties.put("mail.store.protocol" , "imaps"  );
        //properties.put("mail.imap.ssl.enable", "true"   );
        properties.put("mail.imap.ssl.enable", this.IMAP_NEED   );
        properties.put("mail.imap.port"      , IMAP_Port);

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //return new PasswordAuthentication("elandev@yandex.ru", "jhbjy333");
                        return new PasswordAuthentication(IMAP_AUTH_EMAIL, IMAP_AUTH_EMAIL);
                    }
                });
        
        /*Authenticator auth = new EmailAuthenticator(IMAP_AUTH_EMAIL,
                                                    IMAP_AUTH_PWD);
        Session session = Session.getDefaultInstance(properties, auth);*/
        session.setDebug(false);
        try {
            Store store = session.getStore();

            // ����������� � ��������� �������
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

            // ����� �������� ���������
            Folder inbox = store.getFolder("INBOX");
            // ��������� ����� � ������ ������ ��� ������
            inbox.open(Folder.READ_WRITE);
           
            System.out.println("���������� ��������� : " + 
                                String.valueOf(inbox.getMessageCount()));
            if (inbox.getMessageCount() == 0)
                return;
            
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)); 
            
            
            int iteratorMessages = 0;
            int size = 0;
            
            for(int i=0; i < messages.length; i++) {
            	StringBuilder str = new StringBuilder();
            	/*System.out.println(" ����� ���������   " + i + ". ���� ��������� : '" + 
            			messages[i].getSubject() + "'");*/
            	String subject= messages[i].getSubject();
               	if (subject.startsWith("����� ����������: �������:")) {
               		size++;
               		//messages[i].setFlags(new Flags(Flags.Flag.SEEN), true);
               		
               		//str.append(messages[i].getSubject() + "\n");
               		parse.setSubject(messages[i].getSubject());
                    Multipart mp = (Multipart) messages[i].getContent();
                    
                    // ����� ����������� � �������
                    for (int j = 0; j < mp.getCount(); j++){
                        BodyPart  bp = mp.getBodyPart(j);
                        if (bp.getFileName() == null) {
                        	str.append(bp.getContent());
                        	//System.out.println("    " + j + ". ��������� : '" + bp.getContent() + "'");
                        } else {
                        	String path = null;
                        	String filename = MimeUtility.decodeText(bp.getFileName());
                        	InputStream in = null;
                        	FileOutputStream out = null;
	                   		 DirectoryChooser dialog = new DirectoryChooser();
	                   		 dialog.setTitle("�������� ����� ��� ���������� �����");
	                   		 File result = dialog.showDialog( getPrimaryStage());
	                   		 if(result != null) {
	                   			path = result.getAbsolutePath() + File.separator + filename ;
	                   		 }
	                   		 else {
	                   			 path = new File("").getPath()+filename; 
	                   		 }
	                   		 ////System.out.println("  ���� - " +  path); 
	                   		if ((bp.getDisposition() != null) && (bp.getDisposition().equals(Part.ATTACHMENT))) {  
	             	        try { 
	             	        	//IOUtils.toByteArray(in);
	             	        	in = bp.getInputStream();
	             	        	 File file = new File(path);
	             	        	out = new FileOutputStream(file); 
	             	        	
	             	        	int c;
	             	        	int count =0;
	             	        	while((c = in.read()) != -1) {
	             	        		count ++;
	             	        		out.write(c); 
	             	        		//System.out.print((char)c + " ");
	             	        	}
	             	     
	             	        	System.out.println(" ���� �������� -" + in.available() + " or - " + count);
	            	            in.close(); 
	            	            out.close(); 
	       
	            	        } catch (IOException e) { 
	            	            e.printStackTrace(); 
	            	        } 
	                   		}
	             	      
                        }
                    }
                    String body =str.toString();
                    body.replaceAll("<(.)+?>", "");
                    parse.setText(body);
                   // System.out.println(" ���� ��������� - " + body);
                    //System.out.println(" ����� -�������� " );
                    parse.makeParse();
                   // System.out.println(" ������� �Ѩ" );
            	}
            }
            Message[] Inmessages = new Message[size];
            for(int i=0; i < messages.length; i++) {
            	String subject= messages[i].getSubject();
               	if (subject.startsWith("����� ����������:")) {
               		//messages[i].setFlags(new Flags(Flags.Flag.SEEN), true);
               		Inmessages[iteratorMessages] = messages[i];
               				iteratorMessages++;
               	}
            }
            if (size == 0) {
				// ��������� �� ������ -- /
				Alert alert = new Alert(AlertType.INFORMATION,"�������� � ����������� ����� ����� �����");
				alert.setTitle("����������");
				alert.setHeaderText("����� ������� ���!");
				alert.show();
				// ������ ��������� �� ������ -- /
            }
            iteratorMessages = 0;
            inbox.setFlags(Inmessages, new Flags(Flags.Flag.SEEN), true);
            ArrayList<String> attachments = new ArrayList<String>(); 
            inbox.close(true);
            // ��������� ���������; ������ ��������� ��� ������� 1
           // Message message = inbox.getMessage(inbox.getMessageCount());
         //   LinkedList<MessageBean> listMessages = getPart(messages, attachments);
           // System.out.println(" ���������� ����� ���������:   " + listMessages.size());
          /*  for (int i=0; i < listMessages.size();i++ ) {
            	//System.out.println(" �� ����  - " + listMessages.get(i).getFrom());
            	String content = listMessages.get(i).getContent();
            	content.replaceAll("<(.)+?>", "");
            	System.out.println(" ���������� ����������: ");
            	System.out.println(content);
            	System.out.println(" ����� ���������� ): ");
            }*/
            
            int count = inbox.getMessageCount();
            //Message[]  mes= inbox.getMessages();
            
            StringBuilder sb = new StringBuilder();
            
            StringBuilder sv = new StringBuilder();
            
         /*   for(int i=0; i <count; i++) {
            	String subject= mes[i].getSubject();
            	String description = mes[i].getDescription();
            	 System.out.println(" ���� description - " + description);
            	String flag = mes[i].getFlags().toString();
            	System.out.println(" ���� flag - " + flag);
            	String number = Integer.toString(mes[i].getMessageNumber());
            	System.out.println(" ����� ����� -�� - " + number);
            	String from = mes[i].getFrom().toString();
            	System.out.println(" �� ����  - " + from);
            	
            	
            	if (subject.startsWith("����� ����������:")) {
            		sb.append(mes[i].getSubject() + "\n");
            		
            		
                    Multipart mp = (Multipart) mes[i].getContent();
                    
                    // ����� ����������� � �������
                    for (int j = 0; j < mp.getCount(); j++){
                        BodyPart  bp = mp.getBodyPart(j);
                        if (bp.getFileName() == null) {
                        	sv.append("    " + j + ". ��������� : '" + 
                                    bp.getContent() + "'");
                        	//System.out.println("    " + j + ". ��������� : '" + bp.getContent() + "'");
                        } else {
                        	//System.out.println("    " + j + ". ���� : '" + bp.getFileName() + "'");
                        	sv.append("    " + j + ". ���� : '" +
                                    bp.getFileName() + "'");
                        }
                    }
                    String body =sv.toString();
                    body.replaceAll("<(.)+?>", "");
                    System.out.println(" ���� ��������� - " + body);
            	}
            	
            	System.out.println("    " + i + ". ���� ��������� : '" + 
            			mes[i].getSubject() + "'");
            	
            	
            }*/
            

            this.setEmail(sb.toString());
        } catch (NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	} 
}
