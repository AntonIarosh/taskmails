package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import application.DB.WriteTaskInDBCreated;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReadTask {
	private int idUser;
	public ReadTask(int id) {
		this.idUser = id;
	}
	public void readTask(Stage primaryStage) {
		
		String title  = null;
		//String Oldtitle = null;
		//String comment = null;
		String body = null;
		String supervisor = null;
		String link  = "";
		String description = null;
		String dateStart = null;
		String dataEnd = null;
		String dataCreate = null;
		//Date data = null;
		String urgencyMail = null; // код срочности
		String taskCol = null;// Периодичность
		String itsDone = "0";
		
		FileChooser fileChooserZ = new FileChooser();
		fileChooserZ.setTitle("Выбор текстового файла с задачей");
		File file = null;
		file = fileChooserZ.showOpenDialog(primaryStage);
		
		if (file.isFile()) {
			Scanner scanner = null;
			try {
				String subStr = null;
				BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
				scanner = new Scanner(reader);
				int numberOfRows = 0;
				while (scanner.hasNext()) {
					subStr = scanner.nextLine();
					if(subStr.length() > 1) {
					String resultStr = subStr.substring(0, subStr.indexOf(':'));
					System.out.println("считанная строка - " + subStr);  
					System.out.println("строка парсинга - " + resultStr);  
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
					case "Живое расписание": {
						title  = subStr;
						title   = title.replaceAll("[(]", "\\(");
						title   = title.replaceAll("[)]", "\\)");
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
					//scanner.nextLine();
					numberOfRows++;
					}
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Файл не найден.");
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Ошибка при считывании из файла.");
				scanner.close();
			} finally {
				scanner.close();
			}
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
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	
}
