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


public class QuickLetterSend {
	
	public void senMail(String theme, String text,int idChosenUser, int idUser,LinkedList<Integer> allIdCh) throws AddressException, MessagingException {
		EntityEmailAll mailFrom = new EntityEmailAll();
		
		ChoseEmailToUser who = new ChoseEmailToUser(idUser);
		who.whatMailsIs();
		mailFrom = who.getDataEmail();
		
		
		System.out.println (" ���������� ��������� - " + allIdCh.size());

		
		String hostSMTPServerMailFrom = mailFrom.getSMPTserver();
		String needAuthorMailFrom = mailFrom.getSMTPneed();
		int codeSMTPFrom = mailFrom.getCodeSMTP();
		
		//String emailTo = mailTo.getEmail();
		String emailFrom = mailFrom.getEmail();
		String passFrom = mailFrom.getPassword();
		
		//������ properties ������ ��������� ����������.
        //��� ������� ��������� ������� ��� ������.
        //���� �� ������ ������ - ���������� � �������������� ��������� �������.
        //�� ��� �������;=)
        //��������� ��� Yandex ��������� ���������� ����� ����������� ���: 
        //https://yandex.ru/support/mail/mail-clients.html (������ "��������� �����")
        /*Properties properties = new Properties();
     	
        Transport.send(message);*/
		//�������� ����������� ��������� �������
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
           // ������� ����������� ���������, ������� ��������� ������ ���������� Java Mail
            Thread.currentThread().setContextClassLoader(javax.mail.Session.class.getClassLoader());
         
            Properties properties = new Properties();
            
            properties.put("mail.smtp.port", codeSMTPFrom);
            
            
            //���� ��� IP-����� ��������� �������
            //properties.put("mail.smtp.host", "smtp.yandex.ru");
            properties.put("mail.smtp.host", hostSMTPServerMailFrom);
          	//��������� �� �������������� ��� �������� ���������
            //properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.auth", needAuthorMailFrom);
            //���� ��� ��������� ����������
            //properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.port", codeSMTPFrom);
            //������� �������, ��� ��� ��� �������� ��������� Yandex ������� SSL-����������
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.ssl.enable", "true");
            
            //prop.put("mail.smtp.port", "465");
            Address[] cc = new Address[allIdCh.size()];
            
            
           
            Session session = Session.getDefaultInstance(properties,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            //return new PasswordAuthentication("elandev@yandex.ru", "jhbjy333");
                            return new PasswordAuthentication(emailFrom, passFrom);
                        }
                    });
            try {
            //������� ����� �������� ���������
            Message message = new MimeMessage(session);
            //�� ����
           // message.setFrom(new InternetAddress("elandev@yandex.ru"));
            message.setFrom(new InternetAddress(emailFrom));
            //����
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
    				System.out.println ("�� ���� - " + emailFrom+" c ������� - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " ���� - " + emailTo);
    			}
    			message.addRecipients(Message.RecipientType.CC, cc);
    			
    		} else {
    			EntityEmailAll mailTo = new EntityEmailAll();
    			who.setId(idChosenUser);
    			who.whatMailsIs();
    			mailTo = who.getDataEmail();
    			String emailTo = mailTo.getEmail();
    			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
    			 System.out.println ("�� ���� - " + emailFrom+" c ������� - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " ���� - " + emailTo);
    		}
          
            //���� ������
            message.setSubject(theme);
            //����� ������
            // message.setText(text);
            message.setSentDate(new Date());
            
            //�������� ���������� ������ �� ��������
            MimeMultipart multipart = new MimeMultipart();
            //������ ������� - ����� ������
            MimeBodyPart part1 = new MimeBodyPart();
            part1.addHeader("Content-Type", "text/html; charset=UTF-8");
            part1.setDataHandler(
                    new DataHandler(
                            "<html><body><h1 style=\"background-color: #8B008B;color: #FFFAFA; alignment: center; padding: 10px; font-weight: bold; border-radius: 6px;\">" +"����� ����������"+ "</h1></body> <br> " +
                    " <h3 style=\"background-color: #ADD8E6; alignment: center; padding: 10px; font-weight: bold; border-radius: 10px; \"> "  + text + "</h3></html>",
                            "text/html; charset=\"utf-8\""
             )
            );
            multipart.addBodyPart(part1);
            message.setContent(multipart);
            
            Transport.send(message);
         // ��������� �� ������ -- /
			Alert alert = new Alert(AlertType.INFORMATION,"������� ��������� ���� ���������� ����������.");
			alert.setTitle("�������� ���������");
			alert.setHeaderText("������� ��������� ������������ � ����������!");
			alert.show();
			// ������ ��������� �� ������ -- /
            }
            catch (MessagingException mex) {
                // ������ ���������� �� ���������� � ������ ��� �������������
                mex.printStackTrace();
            }
        } finally {
            //������ �������� ����������� ��������� �������
            Thread.currentThread().setContextClassLoader(loader);
        }
	}
}
