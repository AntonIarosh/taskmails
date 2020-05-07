package application.Mails;

import java.util.Date;

import application.DB.WriteTaskInDBCreated;

public class ParseText {
	private String subject;
	private String text;
	private int idUser;
	
	public ParseText() {
		this.text = null;
		this.subject = null;
		this.idUser = 0;
	}
	
	public ParseText(int id_user) {
		this.text =  null;
		this.subject = null;
		this.idUser = id_user;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String tubject) {
		this.subject = tubject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public void makeParse() {
		// Части задачи для записи в бд
		String title = null;
		String Oldtitle = null;
		//String comment = null;
		String body = null;
		String supervisor = null;
		String link  = null;
		String description = null;
		String dateStart = null;
		String dataEnd = null;
		String dataCreate = null;
		//Date data = null;
		String urgencyMail = null; // код срочности
		String taskCol = null;// Периодичность
		String itsDone = "0";

		
		Oldtitle = getSubject();
		
		title = Oldtitle.replace("Живое расписание: Задание: ", "").trim();
		
		/*StringBuffer strTitle = new StringBuffer(getSubject());
		System.out.println(" =последнее вхождение двоеточия.-" + title.lastIndexOf("\""));
		strTitle.delete(0,title.lastIndexOf(":")+1);
		title = strTitle.toString();
		title.trim();
		if (title.charAt(0) == ' ') {
			title = title.substring(1);
		}*/
		
		System.out.println(" ============================================================= -");
		System.out.println("  У нас есть тема -|" + title+"|");
		
		String all = this.getText();
		String delimeter = ";<br />"; // Разделитель
		String[] words = all.split(delimeter); // Разбиение строки на слова с помощью разграничителя (пробел)
		/*for (int i =0; i < words.length; i++) {
			 System.out.println("|" + words[i] + "|");  
		}*/
		
		for(String subStr:words) {
			String resultStr = subStr.substring(0, subStr.indexOf(':'));
			
			System.out.println("тема |" + resultStr + "|");  
			StringBuffer oneStr = new StringBuffer(subStr);
			oneStr.delete(0,subStr.indexOf(":")+1);
			subStr = oneStr.toString();
			subStr = subStr.replaceAll("[;]", "");
			System.out.println(" первый символ|" + subStr.charAt(0)+"|");  
			if (subStr.charAt(0) == ' ') {
				subStr = subStr.substring(1);
			}
			//subStr = subStr.replaceAll(" ", "");
			subStr.trim();
			switch(resultStr) {
			case "Дополнительное описание": {
				System.out.println("Доп опис");  
				description = subStr;
				description  = description .replaceAll("[(]", "\\(");
				description  = description .replaceAll("[)]", "\\)");
				break;
			}
			case "Выполнение": {
				
				break;
			}
			case "Дата и время создания задачи": {
				dataCreate = subStr;
				dataCreate  = dataCreate.replaceAll("[(]", "\\(");
				dataCreate  = dataCreate.replaceAll("[)]", "\\)");
				break;
			}
			case "Дата и время начала выполнения": {
				dateStart = subStr;
				break;
			}
			case "Дата и время окончания выполнения": {
				dataEnd  = subStr;
				break;
			}
			case "Срочность задачи": {
				String P = "Планово";
				if (subStr.compareTo(P) == 0) {
					urgencyMail = "1";
				} else {
					if (subStr.compareTo("Важно") == 0) {
						urgencyMail = "2";
					} else {
						if (subStr.compareTo("Срочно") == 0) {
							urgencyMail = "3";
						}
					}
				}
				//urgencyMail
				break;
			}
			case "Периодичность выполнения": {
				taskCol  = subStr;
				taskCol = taskCol.replaceAll("[(]", "\\(");
				taskCol   = taskCol .replaceAll("[)]", "\\)");
				break;
			}
			case "Руководитель": {
				supervisor = subStr;
				supervisor    = supervisor .replaceAll("[(]", "\\(");
				supervisor    = supervisor .replaceAll("[)]", "\\)");
				break;
			}
			case "Ссылка на дополнительные материалы": {
				link = subStr;
				link   = link .replaceAll("[(]", "\\(");
				link   = link .replaceAll("[)]", "\\)");
				break;
			}
			case "Суть задачи": {
				body = subStr;
				body   = body .replaceAll("[(]", "\\(");
				body   = body .replaceAll("[)]", "\\)");
				break;
			}
		}
	         System.out.println("|" + subStr + "|");  
	         
	      }  
		//System.out.println(" У нас есть тело задачи -|" + this.getText() + "|");
		System.out.println(" ============================================================= -");
		// Запрос на добавление записи в таблицу задачи
		String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
		+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + title +"', "
		+ "'" + body + "', '" + urgencyMail + "', '" + dataCreate + "',"
		+ " '" + dateStart + "', '" + dataEnd + "', '" + supervisor + "', ' " + itsDone + "', '" + taskCol + "', '" + description + "');";
		System.out.println(queryAddTask);
		// конец запроса на добавление записи в таблицу
		// Добавление задачи в бд -- /
		WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
		add.execeteQuery();
		// конец добавления задачи в бд -- /
		
		// Узнать какой айди у новой добавленной задачи -- /
		String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + title+ "' AND `task`.`body` = '" + body +"';";
		int idNewTask = add.WhoAdd(idNew);
		// конец узнавайния айди новой задачи -- /
		
		//Добавить запись в таблицу пользователь задача -- /
		String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + getIdUser() + "', '" + idNewTask + "');";
		add.addUserTask(addUserTask);
		// конец добавление записи в таблицу задача пользователя -- /
		// проверка и заполнение ссылки -- /
		if (!link.isEmpty() ) {
		
			// Дабавление записи в таблицу ссылка к задаче -- /
			String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + link + "');";
			add.addLink(addLink);
			// конец Дабавление записи в таблицу ссылка к задаче -- /
		}
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
