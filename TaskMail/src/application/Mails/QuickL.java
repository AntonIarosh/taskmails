package application.Mails;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import application.DB.ChoseEmailToUser;
import application.Entities.EntityEmail;
import application.Entities.EntityEmailAll;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

public class QuickL {
		private String emailFrom;
		private String passFrom;
		private Message message;
		private Properties properties;
		private Session session;
		EntityEmailAll mailFrom;
		ChoseEmailToUser who;
		
		public QuickL(String theme, String text,int idChosenUser, int idUser,LinkedList<Integer> allIdCh) {
			this.mailFrom = new EntityEmailAll();
			this.who = new ChoseEmailToUser(idUser);
			who.whatMailsIs();
			mailFrom = who.getDataEmail();
			System.out.println ("От кого - " + mailFrom.getEmail()+" c паролем - " +  mailFrom.getPassword() + 
					" SMTP - " +  mailFrom.getSMPTserver() + " code - " +  mailFrom.getCodeSMTP());
			
			System.out.println (" Количество адресатов - " + allIdCh.size());
			String hostSMTPServerMailFrom = mailFrom.getSMPTserver();
			String needAuthorMailFrom = mailFrom.getSMTPneed();
			int codeSMTPFrom = mailFrom.getCodeSMTP();
			
			//String emailTo = mailTo.getEmail();
			this.emailFrom = mailFrom.getEmail();
			this.passFrom = mailFrom.getPassword();
			this.properties = new Properties();
			this.properties.put("mail.smtp.port", codeSMTPFrom);
	            
	            
	            //Хост или IP-адрес почтового сервера
	            //properties.put("mail.smtp.host", "smtp.yandex.ru");
			 this.properties.put("mail.smtp.host", hostSMTPServerMailFrom);
	          	//Требуется ли аутентификация для отправки сообщения
	            //properties.put("mail.smtp.auth", "true");
			 this.properties.put("mail.smtp.auth", needAuthorMailFrom);
	            //Порт для установки соединения
	            //properties.put("mail.smtp.socketFactory.port", "465");
			 this.properties.put("mail.smtp.socketFactory.port", codeSMTPFrom);
	            //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
			 this.properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			 this.properties.put("mail.smtp.ssl.enable", "true");
			
			 this.session = Session.getDefaultInstance(this.properties,
	                    new Authenticator() {
	                        @Override
	                        protected PasswordAuthentication getPasswordAuthentication() {
	                            //return new PasswordAuthentication("elandev@yandex.ru", "jhbjy333");
	                            return new PasswordAuthentication(getEmailFrom(), getPassFrom());
	                        }
	                    });
			 
		}
		
		public void senMail(String theme, String text,int idChosenUser, int idUser,LinkedList<Integer> allIdCh) throws AddressException, MessagingException {
		
			//Объект properties хранит параметры соединения.
	        //Для каждого почтового сервера они разные.
	        //Если не знаете нужные - обратитесь к администратору почтового сервера.
	        //Ну или гуглите;=)
	        //Конкретно для Yandex параметры соединения можно подсмотреть тут: 
	        //https://yandex.ru/support/mail/mail-clients.html (раздел "Исходящая почта")
	        /*Properties properties = new Properties();
	     	
	        Transport.send(message);*/
			//Запомним контекстный загрузчик классов
	        ClassLoader loader = Thread.currentThread().getContextClassLoader();
	        
	        try {
	           // Сделаем контекстным загрузчик, которым загружены классы библиотеки Java Mail
	            Thread.currentThread().setContextClassLoader(javax.mail.Session.class.getClassLoader());
	         
	          
	            
	            //prop.put("mail.smtp.port", "465");
	            Address[] cc = new Address[allIdCh.size()];
	       
	            try {
	            //Создаем новое почтовое сообщение
	           message = new MimeMessage(this.session);
	            //От кого
	           // message.setFrom(new InternetAddress("elandev@yandex.ru"));
	            message.setFrom(new InternetAddress(emailFrom));
	            //Кому
	           // message.setRecipient(Message.RecipientType.TO, new InternetAddress("telandev@gmail.com"));
	    		if(!allIdCh.isEmpty()) {
	    			for(int i=0; i<allIdCh.size(); i++) {
	    				EntityEmailAll mailTo = new EntityEmailAll();
	    				who.setId(allIdCh.get(i));
	    				who.whatMailsIs();
	    				mailTo = who.getDataEmail();
	    				String emailTo = mailTo.getEmail();
	    				cc[i] = new InternetAddress(emailTo);
	    				//message.setRecipient(Message.RecipientType.CC, new InternetAddress(emailTo));
	    				//message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(emailTo, true));
	    				//System.out.println ("От кого - " + emailFrom+" c паролем - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " Кому - " + emailTo);
	    			}
	    			message.addRecipients(Message.RecipientType.CC, cc);
	    			
	    		} else {
	    			EntityEmailAll mailTo = new EntityEmailAll();
	    			who.setId(idChosenUser);
	    			who.whatMailsIs();
	    			mailTo = who.getDataEmail();
	    			String emailTo = mailTo.getEmail();
	    			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
	    			// System.out.println ("От кого - " + emailFrom+" c паролем - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " Кому - " + emailTo);
	    		}
	          
	            //Тема письма
	            message.setSubject(theme);
	            //Текст письма
	            // message.setText(text);
	            message.setSentDate(new Date());
	            
	            //Собираем содержимое письма из кусочков
	            MimeMultipart multipart = new MimeMultipart();
	            //Первый кусочек - текст письма
	            MimeBodyPart part1 = new MimeBodyPart();
	            part1.addHeader("Content-Type", "text/html; charset=UTF-8");
	            part1.setDataHandler(
	                    new DataHandler(
	                            "<html><body><h1 style=\"background-color: #8B008B;color: #FFFAFA; alignment: center; padding: 10px; font-weight: bold; border-radius: 6px;\">" +"Живое расписание"+ "</h1></body> <br> " +
	                    " <h3 style=\"background-color: #ADD8E6; alignment: center; padding: 10px; font-weight: bold; border-radius: 10px; \"> "  + text + "</h3></html>",
	                            "text/html; charset=\"utf-8\""
	             )
	            );
	            multipart.addBodyPart(part1);
	            message.setContent(multipart);
	            
	            Transport.send(message);
	         // Сообщение об успехе -- /
				Alert alert = new Alert(AlertType.INFORMATION,"Быстрое сообщение было отправлено получателю.");
				alert.setTitle("Создание сообщения");
				alert.setHeaderText("Быстрое сообщение сформировано и отправлено!");
				alert.show();
				// Коенец сообщение об успехе -- /
	            }
	            catch (MessagingException mex) {
	                // Печать информации об исключении в случае его возникновения
	                mex.printStackTrace();
	            }
	        } finally {
	            //Вернем исходный контекстный загрузчик классов
	            Thread.currentThread().setContextClassLoader(loader);
	        }
		}



		public String getEmailFrom() {
			return emailFrom;
		}



		public void setEmailFrom(String emailFrom) {
			this.emailFrom = emailFrom;
		}



		public String getPassFrom() {
			return passFrom;
		}



		public void setPassFrom(String passFrom) {
			this.passFrom = passFrom;
		}



		public Session getSession() {
			return session;
		}



		public void setSession(Session session) {
			this.session = session;
		}
	

}
