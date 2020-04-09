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

import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;


public class QuickLetterSend {
	
	public void senMail(String theme, String text,int idChosenUser, int idUser) throws AddressException, MessagingException {
		EntityEmailAll mailFrom = new EntityEmailAll();
		EntityEmailAll mailTo = new EntityEmailAll();
		ChoseEmailToUser who = new ChoseEmailToUser(idUser);
		who.whatMailsIs();
		mailFrom = who.getDataEmail();
		who.setId(idChosenUser);
		who.whatMailsIs();
		mailTo = who.getDataEmail();
		
		String hostSMTPServerMailFrom = mailFrom.getSMPTserver();
		String needAuthorMailFrom = mailFrom.getSMTPneed();
		int codeSMTPFrom = mailFrom.getCodeSMTP();
		
		String emailTo = mailTo.getEmail();
		String emailFrom = mailFrom.getEmail();
		String passFrom = mailFrom.getPassword();
		
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
         
            Properties properties = new Properties();
            
            properties.put("mail.smtp.port", codeSMTPFrom);
            
            
            //Хост или IP-адрес почтового сервера
            //properties.put("mail.smtp.host", "smtp.yandex.ru");
            properties.put("mail.smtp.host", hostSMTPServerMailFrom);
          	//Требуется ли аутентификация для отправки сообщения
            //properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.auth", needAuthorMailFrom);
            //Порт для установки соединения
            //properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.port", codeSMTPFrom);
            //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.ssl.enable", "true");
            
            //prop.put("mail.smtp.port", "465");
            
            
            
            System.out.println ("От кого - " + emailFrom+" c паролем - " + passFrom + " SMTP - " + hostSMTPServerMailFrom + " code - " + codeSMTPFrom + " Кому - " + emailTo);
            Session session = Session.getDefaultInstance(properties,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            //return new PasswordAuthentication("elandev@yandex.ru", "jhbjy333");
                            return new PasswordAuthentication(emailFrom, passFrom);
                        }
                    });
            try {
            //Создаем новое почтовое сообщение
            Message message = new MimeMessage(session);
            //От кого
           // message.setFrom(new InternetAddress("elandev@yandex.ru"));
            message.setFrom(new InternetAddress(emailFrom));
            //Кому
           // message.setRecipient(Message.RecipientType.TO, new InternetAddress("telandev@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
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
}
