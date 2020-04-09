package application.Mails;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
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

public class TaskLetterSend {
	public void senMail(String theme, String text,int idChosenUser, int idUser,String body, String supervisor, String link, String description,String dateStart,String dataEnd, String dataCreate,String urgencyMail,String taskCol, String itsDone, int idTask) throws AddressException, MessagingException {
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
            
			/*Text  = "Задание: " + body  + ";\n" +
			"Дополниельное описание: " +description+";\n" + 
			"Ссылка на дополнительные материалы: " + link + ";\n" +
			"Руководитель: " +supervisor+";\n" +
			"Периодичность выполения: " + taskCol + ";\n"+
			"Срочность задачи: " + urgencyMail +";\n" +
			"Дата и время начала выполнения: " + dateStart +";\n" +
			"Дата и время окончания выполенения: " + dataEnd +";\n"+
			"Дата и время создания задачи:" + dataCreate + ";\n" +
			"Выполнение: " + itsDone ; */
            
            //Собираем содержимое письма из кусочков
            MimeMultipart multipart = new MimeMultipart();
            //Первый кусочек - текст письма
            MimeBodyPart part1 = new MimeBodyPart();
            part1.addHeader("Content-Type", "text/html; charset=UTF-8");
            part1.setDataHandler(
                    new DataHandler(
                            "<html><body> " + /*" <h3 style=\"background-color: #ADD8E6; alignment: center; padding: 10px; font-weight: bold; border-radius: 10px;\">"  + text + "</h3>"+*/
                   /* "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Задание: " + body +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Дополниельное описание: " +description +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Ссылка на дополнительные материалы: " + link +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Руководитель: " +supervisor +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Периодичность выполения: " + taskCol+";" + "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Срочность задачи: " + urgencyMail +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Дата и время начала выполнения: " + dateStart +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Дата и время окончания выполенения: " + dataEnd +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Дата и время создания задачи:" + dataCreate +";"+ "</h4><br>"+
                    "<h4 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Выполнение: " + itsDone  +";"+ "</h4>"*/
                    
                    
   "<h3 style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Задание: " + body + /*" Номер № " + idTask + */";"+ "</h3><br>"+
   "<p style=\"background-color: #ADD8E6; alignment: center; padding: 2px; font-weight: bold; border-radius: 10px;\">"  + "Дополниельное описание: " +description +";"+ "<br>"+
   "Ссылка на дополнительные материалы: " + link +";"+ "<br>"+
   "Руководитель: " +supervisor +";"+ "<br>"+
   "Периодичность выполения: " + taskCol+";" + "<br>"+
   "Срочность задачи: " + urgencyMail +";"+ "<br>"+
   "Дата и время начала выполнения: " + dateStart +";"+ "<br>"+
   "Дата и время окончания выполенения: " + dataEnd +";"+ "<br>"+
   "Дата и время создания задачи:" + dataCreate +";"+ "<br>"+
   "Выполнение: " + itsDone  +";"+ "</p>"
                  
                  /*  "<h3 style=\"background-color: #ADD8E6; alignment: center; padding: 10px; font-weight: bold; border-radius: 10px;\"> <p>"  + "Задание: " + body +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Дополниельное описание: " +description +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Ссылка на дополнительные материалы: " + link +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Руководитель: " +supervisor +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Периодичность выполения: " + taskCol+";" + "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Срочность задачи: " + urgencyMail +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Дата и время начала выполнения: " + dateStart +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Дата и время окончания выполенения: " + dataEnd +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Дата и время создания задачи:" + dataCreate +";"+ "</p><br>"+
                    "<p style=\"background-color: #ADD8E6; alignment: center; padding: 5px; font-weight: bold; border-radius: 5px;> Выполнение: " + itsDone  +";"+ "</p></h3>"*/
                            
                            		+ "</body> <br> " +
                    " </html>",
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
