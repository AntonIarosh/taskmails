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
		String urgencyMail = null; // ��� ���������
		String taskCol = null;// �������������
		String itsDone = "0";
		
		FileChooser fileChooserZ = new FileChooser();
		fileChooserZ.setTitle("����� ���������� ����� � �������");
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
					System.out.println("��������� ������ - " + subStr);  
					System.out.println("������ �������� - " + resultStr);  
					System.out.println("���� |" + resultStr + "|");  
					StringBuffer oneStr = new StringBuffer(subStr);
					oneStr.delete(0,subStr.indexOf(":")+1);
					subStr = oneStr.toString();
					subStr = subStr.replaceAll("[;]", "");
					System.out.println(" ������ ������|" + subStr.charAt(0)+"|");  
					if (subStr.charAt(0) == ' ') {
						subStr = subStr.substring(1);
					}
					//subStr = subStr.replaceAll(" ", "");
					subStr.trim();
					switch(resultStr) {
					case "�������������� ��������": {
						System.out.println("��� ����");  
						description = subStr;
						description  = description .replaceAll("[(]", "\\(");
						description  = description .replaceAll("[)]", "\\)");
						break;
					}
					case "����������": {
						
						break;
					}
					case "���� � ����� �������� ������": {
						dataCreate = subStr;
						dataCreate  = dataCreate.replaceAll("[(]", "\\(");
						dataCreate  = dataCreate.replaceAll("[)]", "\\)");
						break;
					}
					case "����� ����������": {
						title  = subStr;
						title   = title.replaceAll("[(]", "\\(");
						title   = title.replaceAll("[)]", "\\)");
						break;
					}
					case "���� � ����� ������ ����������": {
						dateStart = subStr;
						break;
					}
					case "���� � ����� ��������� ����������": {
						dataEnd  = subStr;
						break;
					}
					case "��������� ������": {
						String P = "�������";
						if (subStr.compareTo(P) == 0) {
							urgencyMail = "1";
						} else {
							if (subStr.compareTo("�����") == 0) {
								urgencyMail = "2";
							} else {
								if (subStr.compareTo("������") == 0) {
									urgencyMail = "3";
								}
							}
						}
						//urgencyMail
						break;
					}
					case "������������� ����������": {
						taskCol  = subStr;
						taskCol = taskCol.replaceAll("[(]", "\\(");
						taskCol   = taskCol .replaceAll("[)]", "\\)");
						break;
					}
					case "������������": {
						supervisor = subStr;
						supervisor    = supervisor .replaceAll("[(]", "\\(");
						supervisor    = supervisor .replaceAll("[)]", "\\)");
						break;
					}
					case "������ �� �������������� ���������": {
						link = subStr;
						link   = link .replaceAll("[(]", "\\(");
						link   = link .replaceAll("[)]", "\\)");
						break;
					}
					case "���� ������": {
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
				System.out.println("���� �� ������.");
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("������ ��� ���������� �� �����.");
				scanner.close();
			} finally {
				scanner.close();
			}
			 System.out.println(" ============================================================= -");
				// ������ �� ���������� ������ � ������� ������
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + title +"', "
				+ "'" + body + "', '" + urgencyMail + "', '" + dataCreate + "',"
				+ " '" + dateStart + "', '" + dataEnd + "', '" + supervisor + "', ' " + itsDone + "', '" + taskCol + "', '" + description + "');";
				System.out.println(queryAddTask);
				// ����� ������� �� ���������� ������ � �������
				// ���������� ������ � �� -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// ����� ���������� ������ � �� -- /
				
				// ������ ����� ���� � ����� ����������� ������ -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + title+ "' AND `task`.`body` = '" + body +"';";
				int idNewTask = add.WhoAdd(idNew);
				// ����� ���������� ���� ����� ������ -- /
				
				//�������� ������ � ������� ������������ ������ -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + getIdUser() + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// ����� ���������� ������ � ������� ������ ������������ -- /
				// �������� � ���������� ������ -- /
				if (!link.isEmpty() ) {
				
					// ���������� ������ � ������� ������ � ������ -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + link + "');";
					add.addLink(addLink);
					// ����� ���������� ������ � ������� ������ � ������ -- /
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
