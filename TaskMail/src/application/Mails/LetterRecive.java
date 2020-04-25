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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;


public class LetterRecive {
	String   IMAP_AUTH_EMAIL = "elandev@yandex.ru" ;
    String   IMAP_AUTH_PWD   = "jhbjy333"           ;
    String   IMAP_Server     = "imap.yandex.ru";
    String   IMAP_Port       = "993"           ;
    private String email;
    private static Stage primaryStage;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public LetterRecive(Stage _primaryStage)
    {
    	this.setPrimaryStage(_primaryStage);
        Properties properties = new Properties();
        properties.put("mail.debug"          , "false"  );
        properties.put("mail.store.protocol" , "imaps"  );
        properties.put("mail.imap.ssl.enable", "true"   );
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

            // Подключение к почтовому серверу
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

            // Папка входящих сообщений
            Folder inbox = store.getFolder("INBOX");
            
            
            
            
            // Открываем папку в режиме только для чтения
            inbox.open(Folder.READ_ONLY);
           
            System.out.println("Количество сообщений : " + 
                                String.valueOf(inbox.getMessageCount()));
            if (inbox.getMessageCount() == 0)
                return;
            
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)); 
            StringBuilder str = new StringBuilder();
            for(int i=0; i < messages.length; i++) {
            	System.out.println(" номер сообщения   " + i + ". Тема сообщения : '" + 
            			messages[i].getSubject() + "'");
            	String subject= messages[i].getSubject();
               	if (subject.startsWith("Живое расписание:")) {
               		str.append(messages[i].getSubject() + "\n");
            		
            		
                    Multipart mp = (Multipart) messages[i].getContent();
                    
                    // Вывод содержимого в консоль
                    for (int j = 0; j < mp.getCount(); j++){
                        BodyPart  bp = mp.getBodyPart(j);
                        if (bp.getFileName() == null) {
                        	str.append("    " + j + ". сообщение : '" + 
                                    bp.getContent() + "'");
                        	//System.out.println("    " + j + ". сообщение : '" + bp.getContent() + "'");
                        } else {
                        	
                        }
                    }
                    String body =str.toString();
                    body.replaceAll("<(.)+?>", "");
                    System.out.println(" Тело сообщения - " + body);
            	}
            }
            ArrayList<String> attachments = new ArrayList<String>(); 
            
            // Последнее сообщение; первое сообщение под номером 1
            Message message = inbox.getMessage(inbox.getMessageCount());
         //   LinkedList<MessageBean> listMessages = getPart(messages, attachments);
           // System.out.println(" Количество новых сообщений:   " + listMessages.size());
          /*  for (int i=0; i < listMessages.size();i++ ) {
            	//System.out.println(" от кого  - " + listMessages.get(i).getFrom());
            	String content = listMessages.get(i).getContent();
            	content.replaceAll("<(.)+?>", "");
            	System.out.println(" Сообщенька начинается: ");
            	System.out.println(content);
            	System.out.println(" Конец Сообщеньки ): ");
            }*/
            
            int count = inbox.getMessageCount();
            Message[]  mes= inbox.getMessages();
            
            StringBuilder sb = new StringBuilder();
            
            StringBuilder sv = new StringBuilder();
            
         /*   for(int i=0; i <count; i++) {
            	String subject= mes[i].getSubject();
            	String description = mes[i].getDescription();
            	 System.out.println(" Тело description - " + description);
            	String flag = mes[i].getFlags().toString();
            	System.out.println(" Тело flag - " + flag);
            	String number = Integer.toString(mes[i].getMessageNumber());
            	System.out.println(" Номер какой -то - " + number);
            	String from = mes[i].getFrom().toString();
            	System.out.println(" от кого  - " + from);
            	
            	
            	if (subject.startsWith("Живое расписание:")) {
            		sb.append(mes[i].getSubject() + "\n");
            		
            		
                    Multipart mp = (Multipart) mes[i].getContent();
                    
                    // Вывод содержимого в консоль
                    for (int j = 0; j < mp.getCount(); j++){
                        BodyPart  bp = mp.getBodyPart(j);
                        if (bp.getFileName() == null) {
                        	sv.append("    " + j + ". сообщение : '" + 
                                    bp.getContent() + "'");
                        	//System.out.println("    " + j + ". сообщение : '" + bp.getContent() + "'");
                        } else {
                        	//System.out.println("    " + j + ". файл : '" + bp.getFileName() + "'");
                        	sv.append("    " + j + ". файл : '" +
                                    bp.getFileName() + "'");
                        }
                    }
                    String body =sv.toString();
                    body.replaceAll("<(.)+?>", "");
                    System.out.println(" Тело сообщения - " + body);
            	}
            	
            	System.out.println("    " + i + ". Тема сообщения : '" + 
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
	
	 private static LinkedList<MessageBean> getPart(Message[] messages, ArrayList<String> attachments) throws MessagingException, IOException { 
	        LinkedList<MessageBean> listMessages = new LinkedList<MessageBean>(); 
	        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 
	        for (Message inMessage : messages) { 
	        	if(inMessage.getSubject().startsWith("Живое расписание:")) {
	        		System.out.println("  Нашел сообщение новое от приложения" ); 
	            attachments.clear(); 
	            if (inMessage.isMimeType("text/plain")) { 
	            	System.out.println("  Записал" ); 
	                MessageBean message = new MessageBean(inMessage.getMessageNumber(), MimeUtility.decodeText(inMessage.getSubject()), inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) inMessage.getContent(), false, null); 
	                listMessages.add(message); 
	            } else if (inMessage.isMimeType("multipart/*")) { 
	                Multipart mp = (Multipart) inMessage.getContent(); 
	                MessageBean message = null; 
	                for (int i = 0; i < mp.getCount(); i++) { 
	                    Part part = mp.getBodyPart(i); 
	                    if ((part.getFileName() == null || part.getFileName() == "") && part.isMimeType("text/plain")) { 
	                    	System.out.println("  Записал новое сообщение" ); 
	                        message = new MessageBean(inMessage.getMessageNumber(), inMessage.getSubject(), inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) part.getContent(), false, null); 
	                    } else if (part.getFileName() != null || part.getFileName() != ""){ 
	                        if ((part.getDisposition() != null) && (part.getDisposition().equals(Part.ATTACHMENT))) { 
	                            attachments.add(saveFile(MimeUtility.decodeText(part.getFileName()), part.getInputStream())); 
	                            if (message != null) message.setAttachments(attachments); 
	                        } 
	                        System.out.println("  Записал новое сообщение" ); 
	                        //message = new MessageBean(inMessage.getMessageNumber(), inMessage.getSubject(), inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) part.getContent(), false, null); 
	                    } 
	                } 
	                listMessages.add(message); 
	            }
	        }
	        } 
	        return listMessages; 
	    } 
	 private static String saveFile(String filename, InputStream input) throws IOException { 
		 String path = null;
		 DirectoryChooser dialog = new DirectoryChooser();
		 dialog.setTitle("Выберете папку для сохранения файла");
		 File result = dialog.showDialog( getPrimaryStage());
		 if(result != null) {
			path = result.getPath() + "\\" + filename ;
		 }
		 else {
			 path = new File(".").getPath()+filename; 
		 }
		 System.out.println("  путь - " +  path); 
	        try { 
	            byte[] attachment = new byte[input.available()]; 
	            input.read(attachment); 
	            File file = new File(path); 
	            FileOutputStream out = new FileOutputStream(file); 
	            out.write(attachment); 
	            input.close(); 
	            out.close(); 
	            return path; 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	        return path; 
	    }
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	} 
}
