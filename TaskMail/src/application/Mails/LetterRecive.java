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

import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class LetterRecive {
	String   IMAP_AUTH_EMAIL = "elandev@yandex.ru" ;
    String   IMAP_AUTH_PWD   = "jhbjy333"           ;
    String   IMAP_Server     = "imap.yandex.ru";
    String   IMAP_Port       = "993"           ;
    private String email;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public LetterRecive()
    {
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

            // ����������� � ��������� �������
            store.connect(IMAP_Server, IMAP_AUTH_EMAIL, IMAP_AUTH_PWD);

            // ����� �������� ���������
            Folder inbox = store.getFolder("INBOX");
            
            // ��������� ����� � ������ ������ ��� ������
            inbox.open(Folder.READ_ONLY);
           
            System.out.println("���������� ��������� : " + 
                                String.valueOf(inbox.getMessageCount()));
            if (inbox.getMessageCount() == 0)
                return;
            // ��������� ���������; ������ ��������� ��� ������� 1
            Message message = inbox.getMessage(inbox.getMessageCount());
            
            int count = inbox.getMessageCount();
            Message[]  mes= inbox.getMessages();
            
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i <count; i++) {
            	String subject= mes[i].getSubject();
            	if (subject.startsWith("����� ����������:")) {
            		sb.append(mes[i].getSubject() + "\n");
            		
            		
                    Multipart mp = (Multipart) mes[i].getContent();
                    
                    // ����� ����������� � �������
                    for (int j = 0; j < mp.getCount(); j++){
                        BodyPart  bp = mp.getBodyPart(j);
                        if (bp.getFileName() == null)
                        	System.out.println("    " + j + ". ��������� : '" + 
                                                               bp.getContent() + "'");
                        else
                        	System.out.println("    " + j + ". ���� : '" +
                                                               bp.getFileName() + "'");
                    }
            	}
            	
            	System.out.println("    " + i + ". ���� ��������� : '" + 
            			mes[i].getSubject() + "'");
            	
            	
            }
            

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
}
