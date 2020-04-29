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
		// ����� ������ ��� ������ � ��
		String title = null;
		//String comment = null;
		String body = null;
		String supervisor = null;
		String link = null;
		String description = null;
		String dateStart = null;
		String dataEnd = null;
		String dataCreate = null;
		Date data = null;
		String urgencyMail = null; // ��� ���������
		String taskCol = null;// �������������
		String itsDone = "0";

		
		title = getSubject();
		
		StringBuffer strTitle = new StringBuffer(getSubject());
		System.out.println(" =��������� ��������� ���������.-" + title.lastIndexOf(":"));
		strTitle.delete(0,title.lastIndexOf(":")+1);
		title = strTitle.toString();
		title.trim();
		if (title.charAt(0) == ' ') {
			title = title.substring(1);
		}
		
		System.out.println(" ============================================================= -");
		System.out.println("  � ��� ���� ���� -|" + title+"|");
		
		String all = this.getText();
		String[] words = all.split("\n"); // ��������� ������ �� ����� � ������� �������������� (������)
		/*for (int i =0; i < words.length; i++) {
			 System.out.println("|" + words[i] + "|");  
		}*/
		
		for(String subStr:words) {
			String resultStr = subStr.substring(0, subStr.indexOf(':'));
			
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
			case "������������� ��������": {
				System.out.println("��� ����");  
				description = subStr;
				break;
			}
			case "����������": {
				
				break;
			}
			case "���� � ����� �������� ������": {
				dataCreate = subStr;
				break;
			}
			case "���� � ����� ������ ����������": {
				dateStart = subStr;
				break;
			}
			case "���� � ����� ��������� �����������": {
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
						urgencyMail = "3";
					}
				}
				//urgencyMail
				break;
			}
			case "������������� ���������": {
				taskCol  = subStr;
				break;
			}
			case "������������": {
				supervisor = subStr;
				break;
			}
			case "������ �� �������������� ���������": {
				link = subStr;
				break;
			}
			case "���� ������": {
				body = subStr;
				break;
			}
		}
	         System.out.println("|" + subStr + "|");  
	         
	      }  
		//System.out.println(" � ��� ���� ���� ������ -|" + this.getText() + "|");
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
