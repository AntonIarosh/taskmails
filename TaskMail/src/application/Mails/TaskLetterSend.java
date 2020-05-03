package application.Mails;

import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import application.DB.ChoseEmailToUser;
import application.Entities.EntityEmailAll;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TaskLetterSend {
	public void senMail(String theme, String text,int idChosenUser, int idUser,String body, String supervisor, String link,
			String description,String dateStart,String dataEnd, String dataCreate,String urgencyMail,String taskCol, String itsDone,
			int idTask,LinkedList<Integer> allIdCh, LinkedList <String> paths) throws AddressException, MessagingException {
		EntityEmailAll mailFrom = new EntityEmailAll();
		//EntityEmailAll mailTo = new EntityEmailAll();
		ChoseEmailToUser who = new ChoseEmailToUser(idUser);
		//who.whatMailsIs();
		//mailFrom = who.getDataEmail();
		mailFrom = who.whatMailsIs();
		
		System.out.println (" ���������� ��������� - " + allIdCh.size());
		Address[] cc = new Address[allIdCh.size()];
		
		String hostSMTPServerMailFrom = mailFrom.getSMPTserver();
		String needAuthorMailFrom = mailFrom.getSMTPneed();
		int codeSMTPFrom = mailFrom.getCodeSMTP();
		
		//String emailTo = mailTo.getEmail();
		String emailFrom = mailFrom.getEmail();
		String passFrom = mailFrom.getPassword();
		
		//������ properties ������ ��������� ����������.
        //��� ������� ��������� ������� ��� ������.
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
            
          //  System.out.println ("�� ���� - " + emailFrom+" c ������� - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " ���� - " + emailTo);
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
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
    		if(!allIdCh.isEmpty()) {
    			for(int i=0; i<allIdCh.size(); i++) {
    				EntityEmailAll mailTo = new EntityEmailAll();
    				who.setId(allIdCh.get(i));
    				//who.whatMailsIs();
    				//mailTo = who.getDataEmail();
    				mailTo = who.whatMailsIs();
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
				//who.whatMailsIs();
				//mailTo = who.getDataEmail();
				mailTo = who.whatMailsIs();
    			String emailTo = mailTo.getEmail();
    			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
    			 System.out.println ("�� ���� - " + emailFrom+" c ������� - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " ���� - " + emailTo);
    		}
            //���� ������
            message.setSubject(theme);
            //����� ������
            // message.setText(text);
            message.setSentDate(new Date());
            body = body.replaceAll("[\n]", "");
            description = description.replaceAll("[\n]", "");
            link = link.replaceAll("[\n]", "");
            supervisor = supervisor.replaceAll("[\n]", "");
            taskCol = taskCol.replaceAll("[\n]", "");
            urgencyMail = urgencyMail.replaceAll("[\n]", "");
            dateStart = dateStart.replaceAll("[\n]", "");
            dataEnd  = dataEnd.replaceAll("[\n]", "");
            //�������� ���������� ������ �� ��������
            MimeMultipart multipart = new MimeMultipart();
            //������ ������� - ����� ������
            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText(   "���� ������: " + body + /*" ����� � " + idTask + */";\n"+
   "������������� ��������: " +description +";\n"+
   "������ �� �������������� ���������: " + link +";\n"+
   "������������: " +supervisor +";\n"+ 
   "������������� ���������: " + taskCol+";\n" + 
   "��������� ������: " + urgencyMail +";\n"+
   "���� � ����� ������ ����������: " + dateStart +";\n"+ 
   "���� � ����� ��������� �����������: " + dataEnd +";\n"+
   "���� � ����� �������� ������:" + dataCreate +";\n"+ 
   "����������: " + itsDone  +";\n");

            
            for(int i=0; i< paths.size(); i++) {
            	System.out.println(" ���� � ����� - " + paths.get(i));
                // �������� ������ �����
                MimeBodyPart p2 = new MimeBodyPart();
             // ���������� ����� �� ������ �����
                FileDataSource fds = new FileDataSource(paths.get(i));
                p2.setDataHandler(new DataHandler(fds));
                p2.setFileName(fds.getName());
                
            	multipart.addBodyPart(p2);
            }

            
            multipart.addBodyPart(part1);
            message.setContent(multipart);
            
            Transport.send(message);
         // ��������� �� ������ -- /
			Alert alert = new Alert(AlertType.INFORMATION,"������� ���� ���������� ����������");
			alert.setTitle("�������� �������");
			alert.setHeaderText("������� ������������ � ����������!");
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
