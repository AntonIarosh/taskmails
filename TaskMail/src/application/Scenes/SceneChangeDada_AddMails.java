package application.Scenes;

import java.util.HashMap;
import java.util.LinkedList;

import application.Validation;
import application.DB.AddUser;
import application.DB.ChanngeUserInfos;
import application.DB.DeleteEmail;
import application.DB.SearchJobs;
import application.Entities.EntityEmail;
import application.Entities.EntityUser;
import application.interfaces.ChangeDataEmails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class SceneChangeDada_AddMails implements ChangeDataEmails {
	private Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	private AddUser add;
	private int Id;
	private ObservableList<EntityEmail> data;
	private int idposts;
	public SceneChangeDada_AddMails(Stage primaryStage, int _id) {
		add = null;
		this.Id = _id;
		System.out.println("����������� ����c - " + this.Id);
		System.out.println("��������  - " + _id);
		this.primaryStage = primaryStage;
		data = FXCollections.observableArrayList();
		this.ounScene = createNewScene();
		this.oldScene = primaryStage.getScene();
		setNewScene(this.primaryStage,this.ounScene); 
	}
	
	@Override
	public void setNewScene(Stage primaryStage, Scene newScene) {
		primaryStage.close();
		primaryStage.setScene(newScene);
		primaryStage.show();
	}
	public int getIdUser() {
		System.out.println("������ - " + this.Id);
		return this.Id;
	}
	public void setId(int _id) {
		this.Id = _id;
	}
	public void setData(ObservableList<EntityEmail> _data) {
		this.data = _data;
	}
	public ObservableList<EntityEmail> getData() {
		return this.data;
	}
	@Override
	public Scene createNewScene() {
		Popup popup;
		popup=new Popup();
		popup.setAutoHide(true);
		VBox textPopup = new VBox(50);
		textPopup.setAlignment(Pos.CENTER);
		textPopup.setPrefSize(500, 300);
		textPopup.setStyle("-fx-background-color: #FF00FF;");
		popup.getContent().add(textPopup);
		Label popupL = new Label();
		popupL.setStyle("-fx-background-color: #FFFFE0;");
		popupL.setWrapText(true);
		textPopup .getChildren().add(popupL);
		VBox flowPane = new VBox(50);
		// ������ ��� ������ ������������ ---/
		VBox userInfo = new VBox();
		
		VBox enternName = new VBox(50);
		Label enterLNameLabel = new Label("������� ���");
		TextField textName = new TextField();
		textName.setPromptText("���");
		enternName.getChildren().addAll(enterLNameLabel, textName);
		enternName.setSpacing(5);
		enternName.getStyleClass().add("EnterUserData");
		enternName.setMinWidth(150);
		
		VBox enternSecondName = new VBox(50);
		Label enterSecondNameLabel = new Label("������� �������");
		TextField textSeconName = new TextField();
		textSeconName.setPromptText("�������");
		enternSecondName.getChildren().addAll(enterSecondNameLabel, textSeconName);
		enternSecondName.setSpacing(5);
		enternSecondName.getStyleClass().add("EnterUserData");
		enternSecondName.setMinWidth(150);
		
		VBox enternFatherName = new VBox(50);
		Label enterFatherNameLabel = new Label("������� ��������");
		TextField textFatherName = new TextField();
		textFatherName.setPromptText("��������");
		enternFatherName.getChildren().addAll(enterFatherNameLabel, textFatherName);
		enternFatherName.setSpacing(5);
		enternFatherName.getStyleClass().add("EnterUserData");
		enternFatherName.setMinWidth(150);
	
		HBox enterPass = new HBox(50);
		Label enterLoginPass = new Label("������� ������");
		enterLoginPass.setWrapText(true);
		Label enterRule = new Label("������ ������ ��������� ����� ���������� �������� � �� ������ ����� �����");
		enterRule.setWrapText(true);
		enterRule.setMaxWidth(250);
		enterRule.setWrapText(true);
		enterRule.setMinHeight(85);
		TextField pass = new TextField();
		pass.setPromptText("������� ������");
		enterPass.getChildren().addAll(enterLoginPass, pass,enterRule);
		enterPass.setAlignment(Pos.CENTER);
		enterPass.setSpacing(5);
		enterPass.getStyleClass().add("EnterUserData");
		enterPass.setMinWidth(400);
		
		HBox enterLogin = new HBox(50);
		Label enterLoginLogin = new Label("������� �����");
		enterLoginLogin.setWrapText(true);
		TextField login = new TextField();
		login.setPromptText("������� �����");
		enterLogin.getChildren().addAll(enterLoginLogin, login );
		enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLogin.getStyleClass().add("EnterUserData");
		
		HBox enterLogPas = new HBox(50);
		enterLogPas.getChildren().addAll(enterLogin);
		enterLogPas.setId("Data");
		enterLogPas.setSpacing(10);
		enterLogPas.getStyleClass().add("EnterUserData");
		
		HBox enterName = new HBox(50);
		enterName.getChildren().addAll(enternName,enternSecondName,enternFatherName);
		enterName.setId("Data");
		enterName.setSpacing(10);
		enterName.getStyleClass().add("EnterUserData");
		
		HBox enterData = new HBox(50);
		enterData.getChildren().addAll(/*enterLogin,*/enterPass);
		enterData.setId("Data");
		enterData.setSpacing(10);
		enterData.getStyleClass().add("EnterUserData");
					
		HBox enterJob = new HBox(50);
		Label enterJobLabel = new Label("���� � ���, �� �������");
		enterJobLabel.setWrapText(true);
		Label lookJobLabel = new Label("������ ���� ���������");
		enterJobLabel.setWrapText(true);
		TextField job= new TextField();
		job.setPromptText("���������");
		SearchJobs posts = new SearchJobs();
		posts.setQuery("SELECT * FROM `taskmail`.`post`");
		HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
		resultItems = posts.doQery();
		enterJob.getStyleClass().add("EnterUserData");
		enterJob.setMinWidth(500);
		
        ChoiceBox allJobs = new ChoiceBox();
        allJobs.getItems().addAll(resultItems.values());
        allJobs.getSelectionModel().selectFirst();
		enterJob.getChildren().addAll(lookJobLabel,allJobs,enterJobLabel, job);
		enterJob.setAlignment(Pos.CENTER);
		enterJob.setSpacing(5);
		enterJob.getStyleClass().add("EnterUserData");
		// ������ ���������� ������
		Button buttonUpdate= new Button("��������� ������");
		buttonUpdate.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonUpdate.setId("button");
		
		userInfo.getChildren().setAll(enterName,enterData,enterLogPas,enterJob);
		userInfo.setId("Info");
		userInfo.setSpacing(8);
		
		// --- ����� ������ ��� ������ ������������ ---/
		// ������ ��� ����� ������������ ---/
		
		// --- �������� � �������� ������������ --- /
		int idUserInner = this.getIdUser();
		System.out.println("����� �������� ������� ������� - " + idUserInner);
		ChanngeUserInfos infos = new ChanngeUserInfos(idUserInner);
		infos.whatMailsIs();
		this.data = infos.getData();
		
		// ������ �������
		TableColumn<EntityEmail, Integer> firstColId = new TableColumn<EntityEmail, Integer>();
		firstColId.setText("�����");
		StringConverter<Integer> converter = new IntegerStringConverter();
		firstColId.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		firstColId.setCellValueFactory(new PropertyValueFactory<EntityEmail, Integer>("Id"));
		// ������ �������
		TableColumn<EntityEmail, String> secondColEmail = new TableColumn<EntityEmail, String>();
		secondColEmail.setText("Email");
		
		secondColEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		secondColEmail.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Email"));

		// ������ �������
		TableColumn<EntityEmail, String> ColPass = new TableColumn<EntityEmail, String>();
		 ColPass .setText("������");
		 ColPass.setCellFactory(TextFieldTableCell.forTableColumn());
		 ColPass.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Password"));
		 
			// �������� ������� SMTP ������
			TableColumn<EntityEmail, String> ColSMTP = new TableColumn<EntityEmail, String>();
			ColSMTP.setText("SMPT ������");
			ColSMTP.setCellFactory(TextFieldTableCell.forTableColumn());
			ColSMTP.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("SMPTserver"));
		 
		    //����� ������� SMTP ���
			TableColumn<EntityEmail, Integer> ColCodeSMTP = new TableColumn<EntityEmail, Integer>();
			ColCodeSMTP.setText("��� SMTP");
			ColCodeSMTP.setCellFactory(TextFieldTableCell.forTableColumn(converter));
			ColCodeSMTP.setCellValueFactory(new PropertyValueFactory<EntityEmail, Integer>("codeSMTP"));
			
		    //������ ������� IMAP ���
			TableColumn<EntityEmail, Integer> ColCodeIMAP = new TableColumn<EntityEmail, Integer>();
			 ColCodeIMAP.setText("��� IMAP");
			 ColCodeIMAP.setCellFactory(TextFieldTableCell.forTableColumn(converter));
			 ColCodeIMAP.setCellValueFactory(new PropertyValueFactory<EntityEmail, Integer>("codeIMAP"));
			
			// ������� ������� IMAP ������
			TableColumn<EntityEmail, String> ColIMAP = new TableColumn<EntityEmail, String>();
			ColIMAP.setText("IMAP ������");
			ColIMAP.setCellFactory(TextFieldTableCell.forTableColumn());
			ColIMAP.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("IMAPserver"));
			 
		TableView<EntityEmail> tableView = new TableView<EntityEmail>();
	    tableView.setItems(data);
	    tableView.getColumns().addAll( firstColId , secondColEmail,ColPass,ColSMTP,ColCodeSMTP,ColIMAP,ColCodeIMAP);
	    
		// --- ����� �������� � �������� --- /
		
		HBox enterPassMail = new HBox(50);
		Label enterLoginPassMail = new Label("������� ������");
		Label enterRuleMail = new Label("������ �� ������������ �����");
		enterRuleMail.setMaxWidth(250);
		enterRuleMail.setWrapText(true);
		enterRuleMail.setMinHeight(25);
		TextField passMail = new TextField();
		passMail.setPromptText("������� ������");
		enterPassMail.getChildren().addAll(enterLoginPassMail, passMail,enterRuleMail);
		enterPassMail.setAlignment(Pos.CENTER);
		enterPassMail.setSpacing(5);
		enterPassMail.getStyleClass().add("EnterUserData");
		enterPassMail.setMinWidth(450);
		
		VBox enterInServer = new VBox(50);
		Label InServerLabel = new Label("������� ������ �������� �����");
		InServerLabel.setMaxWidth(300);
		InServerLabel.setMinWidth(200);
		InServerLabel.setWrapText(true);
		InServerLabel.setMinHeight(25);
		TextField InServerField = new TextField();
		InServerField.setPromptText("IMAP ������");
		enterInServer.getChildren().addAll(InServerLabel , InServerField);
		enterInServer.setAlignment(Pos.CENTER);
		enterInServer.setSpacing(5);
		enterInServer.getStyleClass().add("EnterUserData");
		
		VBox enterInServerPort = new VBox(50);
		Label InServerLabelPort = new Label("������� ����� ����� �����");
		InServerLabelPort.setMaxWidth(300);
		InServerLabelPort.setMinWidth(200);
		InServerLabelPort.setMinHeight(25);
		TextField InServerFieldPort = new TextField();
		InServerFieldPort.setPromptText("���� IMAP �������");
		enterInServerPort.getChildren().addAll(InServerLabelPort , InServerFieldPort);
		enterInServerPort.setSpacing(5);
		enterInServerPort.getStyleClass().add("EnterUserData");
		
		VBox enterInAuth = new VBox(50);
		Label enterInAuthLabel = new Label("����� �� ��������������?");
		enterInAuthLabel.setMinWidth(200);
		ChoiceBox InAuth = new ChoiceBox();
		InAuth.getItems().addAll("��", "���");
		InAuth.getSelectionModel().selectFirst();
		enterInAuth.getChildren().addAll(enterInAuthLabel , InAuth);
		enterInAuth.setSpacing(5);
		enterInAuth.getStyleClass().add("EnterUserData");
		
		HBox InInfo = new HBox(50);
		InInfo.getChildren().addAll(enterInServer, enterInServerPort, enterInAuth);
		InInfo.setAlignment(Pos.CENTER);
		InInfo.setSpacing(5);
		InInfo.setId("Info");
		
		VBox  enterOutServer = new VBox();
		Label OutServerLabel = new Label("������� ������ ��������� �����");
		OutServerLabel.setMaxWidth(300);
		OutServerLabel.setWrapText(true);
		OutServerLabel.setMinHeight(25);
		OutServerLabel.setMinWidth(200);
		TextField OutServerField = new TextField();
		OutServerField.setPromptText("SMTP ������");
		enterOutServer.getChildren().addAll(OutServerLabel, OutServerField);
		enterOutServer.setAlignment(Pos.CENTER);
		enterOutServer.setSpacing(5);
		enterOutServer.getStyleClass().add("EnterUserData");
		
		VBox enterOutServerPort = new VBox();
		Label OutServerLabelPort = new Label("������� ����� ����� �����");
		OutServerLabelPort.setMaxWidth(300);
		OutServerLabelPort.setMinWidth(200);
		OutServerLabelPort.setWrapText(true);
		OutServerLabelPort.setMinHeight(25);
		TextField OutServerFieldPort = new TextField();
		OutServerFieldPort.setPromptText("���� SMTP �������");
		enterOutServerPort.getChildren().addAll(OutServerLabelPort, OutServerFieldPort);
		enterOutServerPort.setAlignment(Pos.CENTER);
		enterOutServerPort.setSpacing(5);
		enterOutServerPort.getStyleClass().add("EnterUserData");
		
		VBox enterOutAuth = new VBox(50);
		Label enterOutAuthLabel = new Label("����� �� ��������������?");
		enterOutAuthLabel.setMinWidth(200);
		ChoiceBox OutAuth = new ChoiceBox();
		OutAuth.getItems().addAll("��", "���");
		OutAuth.getSelectionModel().selectFirst();
		enterOutAuth.getChildren().addAll(enterOutAuthLabel,  OutAuth);
		enterOutAuth.setAlignment(Pos.CENTER);
		enterOutAuth.setSpacing(5);
		enterOutAuth.getStyleClass().add("EnterUserData");
		
		VBox emails = new VBox();
		emails.setId("email");
		VBox enterMail = new VBox(50);
		Label enterMailLabel = new Label("������� ����� ����������� �����");
		TextField textEnterMail = new TextField();
		textEnterMail.setOnAction(
				event-> {
					String server = textEnterMail.getText();
					String[] words = server.split("@");
					System.out.println("������ �����" + words[0] + "������ �����" + words[1]);
					switch(words[1]) {
					case "yandex.ru": {
						// ImapServer
						InServerField.setText("imap.yandex.ru");
						//IMApPORT
						InServerFieldPort.setText("993");
						//SMTP Server
						OutServerField.setText("smtp.yandex.ru");
						//SMTP Port
						OutServerFieldPort.setText("465");
						break;
					}
					case "gmail.com": {
						// ImapServer
						InServerField.setText("imap.gmail.com");
						//IMApPORT
						InServerFieldPort.setText("993");
						//SMTP Server
						OutServerField.setText("smtp.gmail.com");
						//SMTP Port
						OutServerFieldPort.setText("465");
						break;
					}
					case "bk.ru":
					case "inbox.ru":
					case "list.ru":
					case "mail.ru": {
						// ImapServer
						InServerField.setText("imap.mail.ru");
						//IMApPORT
						InServerFieldPort.setText("993");
						//SMTP Server
						OutServerField.setText("smtp.mail.ru");
						//SMTP Port
						OutServerFieldPort.setText("465");
						break;
					}
					case "rambler.ru":
					case "ro.ru":
					case "rambler.ua":
					case "autorambler.ru":
					case "myrambler.ru":
					case "lenta.ru": {
						// ImapServer
						InServerField.setText("imap.rambler.ru");
						//IMApPORT
						InServerFieldPort.setText("993");
						//SMTP Server
						OutServerField.setText("smtp.rambler.ru");
						//SMTP Port
						OutServerFieldPort.setText("465");
						break;
					}
					}
					
				}
				);
		textEnterMail.setPromptText("email");
		enterMail.getChildren().addAll(enterMailLabel, textEnterMail);
		enterMail.setSpacing(5);
		enterMail.getStyleClass().add("EnterUserData");
		
		HBox OutInfo = new HBox(50);
		OutInfo.getChildren().addAll(enterOutServer, enterOutServerPort, enterOutAuth );
		OutInfo.setAlignment(Pos.CENTER);
		OutInfo.setSpacing(5);
		OutInfo.setId("Info");
		
		Button buttonAdd = new Button("�������� �����");
		buttonAdd.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonAdd.setId("button");
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				
				validtion.setText(textEnterMail.getText());
				isValid = validtion.validEmail();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� E-mail -\n"+ " E-mail <" +textEnterMail.getText()+">"+ "\n");
					textEnterMail.setStyle("-fx-background-color: #FF7F50;");
				} else {
					textEnterMail.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� IMAP �������
				validtion.setText(InServerFieldPort.getText());
				isValid = validtion.validPort();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� IMAP ������� -\n ���� <"+ InServerFieldPort.getText() + ">\n");
					InServerFieldPort.setStyle("-fx-background-color: #FF7F50;");
				} else {
					InServerFieldPort.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� SMTP �������
				validtion.setText(OutServerFieldPort.getText());
				isValid = validtion.validPort();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� SMTP ������� -\n ���� <"+ OutServerFieldPort.getText() + ">\n");
					OutServerFieldPort.setStyle("-fx-background-color: #FF7F50;");
				} else {
					OutServerFieldPort.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� SMTP �������
				validtion.setText(OutServerField.getText());
				isValid = validtion.validSMTP();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� SMTP ������� -\n ��� <"+ OutServerField.getText() + ">\n");
					OutServerField.setStyle("-fx-background-color: #FF7F50;");
				} else {
					OutServerField.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� IMAP �������
				validtion.setText(InServerField.getText());
				isValid = validtion.validIMAP();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� IMAP ������� -\n ��� <"+ InServerField.getText() + ">\n");
					InServerField.setStyle("-fx-background-color: #FF7F50;");
				} else {
					InServerField.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				// ��������� ������
				validtion.setText(passMail.getText());
				isValid = validtion.validPassword();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ������ ������������\n ������ <" + pass.getText() + "> ������������: " + passMail.getText().length() + " ��������\n");
					passMail.setStyle("-fx-background-color: #FF7F50;");
				} else {
					passMail.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ������� ����� ������
				int countFalse = 0;
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				//������������ ������ ������
				str.append("���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				int idpost;
				String inServerA;
				String outServerA;
				idpost = InAuth.getSelectionModel().getSelectedIndex();
				if (idpost == 0) {
					inServerA = "true";
				} else {
					inServerA = "false";
				}
				idpost = OutAuth.getSelectionModel().getSelectedIndex();
				if (idpost == 0) {
					outServerA = "true";
				} else {
					outServerA = "false";
				}
				String query = "INSERT INTO `taskmail`.`email`(`email`,`password`,`inServer`,`portInServer`,`needInAuth`,`outServer`,`portOutServer`,"
						+ "`needOutAuth`) VALUES ('" + textEnterMail.getText()+ "','" + passMail.getText() + "','" +InServerField.getText() + "','" + 
						InServerFieldPort.getText()+"','"+ inServerA + "','" + OutServerField.getText()+"','" + OutServerFieldPort.getText()
						+"','" + outServerA +"');";
				System.out.print(query);
				add = new AddUser(query);
				String found = "SELECT * FROM `users` WHERE (`id_user` = '"+ Id +"'); ";
				add.setMail(textEnterMail.getText());
				
				String insertUserMAil ="INSERT INTO `taskmail`.`user_email` (`id_user`,`id_email`) VALUES ( '" + add.WhoAdd(found)+"', '" + add.addMail()+"');";
				add.addUserEmail(insertUserMAil);
				
				infos.whatMailsIs();
				ObservableList<EntityEmail> dataf = infos.whatMailsIs();
				 tableView.getItems().setAll(dataf);
				 tableView.refresh();
				 textEnterMail.setText("");
				 passMail.setText("");
				 InServerField.setText("");
				 InServerFieldPort.setText("");
				 OutServerField.setText("");
				 OutServerFieldPort.setText("");
			}
			}

		});
		
		Button delete = new Button("�������");
		delete.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		delete.setId("button");
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				
				validtion.setText(textEnterMail.getText());
				isValid = validtion.validEmail();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� E-mail -\n"+ " E-mail <" +textEnterMail.getText()+">"+ "\n");
					textEnterMail.setStyle("-fx-background-color: #FF7F50;");
				} else {
					textEnterMail.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� IMAP �������
				validtion.setText(InServerFieldPort.getText());
				isValid = validtion.validPort();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� IMAP ������� -\n ���� <"+ InServerFieldPort.getText() + ">\n");
					InServerFieldPort.setStyle("-fx-background-color: #FF7F50;");
				} else {
					InServerFieldPort.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� SMTP �������
				validtion.setText(OutServerFieldPort.getText());
				isValid = validtion.validPort();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� SMTP ������� -\n ���� <"+ OutServerFieldPort.getText() + ">\n");
					OutServerFieldPort.setStyle("-fx-background-color: #FF7F50;");
				} else {
					OutServerFieldPort.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� SMTP �������
				validtion.setText(OutServerField.getText());
				isValid = validtion.validSMTP();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� SMTP ������� -\n ��� <"+ OutServerField.getText() + ">\n");
					OutServerField.setStyle("-fx-background-color: #FF7F50;");
				} else {
					OutServerField.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ����� IMAP �������
				validtion.setText(InServerField.getText());
				isValid = validtion.validIMAP();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� IMAP ������� -\n ��� <"+ InServerField.getText() + ">\n");
					InServerField.setStyle("-fx-background-color: #FF7F50;");
				} else {
					InServerField.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ������� ����� ������
				int countFalse = 0;
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				//������������ ������ ������
				str.append("���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				/*String deleteUserMAil ="DELETE FROM `taskmail`.`email` LEFT JOIN `taskmail`.`user_email` ON `email`.`id_email` = "
						+ " `user_email`.`id_email` WHERE  `user_email`.`id_user` = '" +  getIdUser() + "' AND `email`.`email` = '" + textEnterMail.getText()+ "';";*/
				
				String deleteUserMAil ="DELETE FROM `taskmail`.`email` WHERE `email`.`email` = '" + textEnterMail.getText()+ "'; ";
				// ���������� ���������� �����
				
				String whatIdMail = "SELECT `email`.`id_email` FROM `taskmail`.`email` WHERE `email` = '" + textEnterMail.getText() + "';";
				DeleteEmail oneOrMany = new DeleteEmail(whatIdMail);
				int idOfMail = oneOrMany.whatId();
				String countOfMail =  null;
				boolean countOfUserMail = false;
				String delFromUsersEmail = null;
				boolean ifDel = false;
				if (idOfMail != -1) {
					countOfMail = "SELECT COUNT(`user_email`.`id_email`) FROM `taskmail`.`user_email` WHERE `user_email`.`id_email` = '" +idOfMail+"';";
					oneOrMany.setQuery(countOfMail);
					countOfUserMail = oneOrMany.serchQuery();
					if (countOfUserMail) {
						delFromUsersEmail = "DELETE FROM `taskmail`.`user_email` WHERE `user_email`.`id_email` = '" + idOfMail + "' AND `user_email`.`id_user` = '" + getIdUser() + "';";
						add = new AddUser(delFromUsersEmail);
						ifDel = add.execeteQuery();
					} else {
						add = new AddUser(deleteUserMAil);
						ifDel = add.execeteQuery();
					}
					
				} else {
					// ��������� �� no ������ -- /
					Alert alert = new Alert(AlertType.INFORMATION,"�������� ������� ����������� �����");
					alert.setTitle("��������");
					alert.setHeaderText("�������� �� ���������. ��� ������ ������ ����������� ����� � ���� ������!");
					alert.show();
					// ������ ��������� �� no  ������ -- /
				}
				if(ifDel) {
					// ��������� �� ������ -- /
					Alert alert = new Alert(AlertType.INFORMATION,"�������� ������� ����������� �����");
					alert.setTitle("��������");
					alert.setHeaderText("�������� ����������� �������!");
					alert.show();
					// ������ ��������� �� ������ -- /
				}
				
				
				infos.whatMailsIs();
				ObservableList<EntityEmail> dataf = infos.whatMailsIs();
				//System.out.print("Email -����� ������ - " +dataf.size());
				for (int i=0; i<dataf.size(); i++) {
					System.out.print("Email - new" + dataf.get(i).getEmail());
				}
				 tableView.getItems().setAll(dataf);
				 tableView.refresh();
				 textEnterMail.setText("");
			}
			}

		});
		
		emails.getChildren().setAll(tableView/*,buttonSelect*/, enterMail,delete,enterPassMail,InInfo,OutInfo,buttonAdd);
		emails.setSpacing(10);


		// --- ����� ������ ��� ����� ������������---/
		// --- ������� �������������� �������� ����� ---/
		EntityUser user = infos.whoIsThis();
		textName.setText(user.getFirstName());
		textSeconName.setText(user.getSecondName());
		textFatherName.setText(user.getLastName());
		pass.setText(user.getPassword());
		job.setText(user.getNamePost());
		login.setText(user.getEmail());
		// --- ����� ������� �������� �������������� ---/
		// -- ������ ����� ---/
		Button buttonExit = new Button("�����");
		buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonExit.setId("button");
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.print("D����� �����");
				primaryStage.setScene(oldScene);
				primaryStage.centerOnScreen();
			}
		});
		// -- ����� ������ ����� --/
		
		// -- ������ �������� ������ ������������ --- /
		buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				
				// ��������� �����
				validtion.setText(textName.getText());
				isValid = validtion.validName();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ����� -\n ��� <"+ textName.getText() + "> ������������: " + textName.getText().length() + " ��������\n");
					textName.setStyle("-fx-background-color: #FF7F50;");
				} else {
					textName.setStyle("-fx-background-color: #FFFFFF;");
				}
				//��������� �������
				validtion.setText(textSeconName.getText());
				isValid = validtion.validElseName();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ������� -\n ������� <"+ textSeconName.getText() + ">  ������������: " + textSeconName.getText().length() + " ��������\n");
					textSeconName.setStyle("-fx-background-color: #FF7F50;");
				} else {
					textSeconName.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ��������
				if (!textFatherName.getText().isEmpty()) {
					validtion.setText(textFatherName.getText());
					isValid = validtion.validElseName();
					boolVali.add(isValid);
					if (!isValid) {
						str.append(" ������ � ���������� �������� -\n �������� <"+ textFatherName.getText() + "> ������������: " + textFatherName.getText().length() +" ��������\n");
						textFatherName.setStyle("-fx-background-color: #FF7F50;");
					} else {
						textFatherName.setStyle("-fx-background-color: #FFFFFF;");
					}
				}
				// ��������� ������
				validtion.setText(login.getText());
				isValid = validtion.validLogin();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ������ ������������\n ����� <" + login.getText() + ">\n");
					login.setStyle("-fx-background-color: #FF7F50;");
				} else {
					login.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ������
				validtion.setText(pass.getText());
				isValid = validtion.validPassword();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ������ ������������\n ������ <" + pass.getText() + "> ������������: " + pass.getText().length() + " ��������\n");
					pass.setStyle("-fx-background-color: #FF7F50;");
				} else {
					pass.setStyle("-fx-background-color: #FFFFFF;");
				}
				// �������� ������� ����� ������
				int countFalse = 0;
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				//������������ ������ ������
				str.append(" ���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				int changeText = job.getText().compareTo(user.getNamePost());
				int changeBox = allJobs.getSelectionModel().getSelectedItem().toString().compareTo(user.getNamePost());
				if ((changeText == 0) && ( changeBox==0 )) {
					idposts = user.getIdPost();
					System.out.println("��� ������ - " + idposts);
				} 
				if (changeBox != 0) {
					idposts = allJobs.getSelectionModel().getSelectedIndex();
					idposts +=1;
					System.out.println("��� ������ - " + idposts);
				}
				else {
					posts.setNamePost(job.getText());
					idposts = posts.addPost("INSERT INTO `post` (`name_post`) VALUES ('" + job.getText() +"'); ");
					System.out.println("��� ������ - " + idposts);
				}
				
				
				String query = "UPDATE `taskmail`.`users` SET `firstname` = '" +  textName.getText()
			+"', `secondname` = '"+textSeconName.getText() +"', `lastname` = '" + textFatherName.getText()+ "', `id_post` = '" + idposts
			+ "', `login` = '" + login.getText()+ "', `password` = '" + pass.getText()+ "' WHERE (`id_user` = '" + Id
			+ "');\r\n" + 
						"";
				infos.updateData(query);
			}
			}
		});
		// -- ����� ������ �������� ������ ������������ --- /
		
		ScrollPane Sc =new ScrollPane();
		Sc.setLayoutX(10);
		Sc.setLayoutY(10);
		Sc .setCursor(Cursor.CLOSED_HAND);
		Sc.setMaxWidth(165);
		Sc.setMinWidth(165);
		Sc.setStyle("-fx-alignment: center;");
		
		userInfo.getChildren().add(buttonUpdate);
		HBox root = new HBox(50);
		root.getChildren().setAll(userInfo,emails);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		root.setId("root");
		flowPane.getChildren().setAll(root,buttonExit);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setId("flowPane");
		Scene scene = new Scene(flowPane, 1050, 600);
		scene.getStylesheets().add(getClass().getResource("/application/styles/changeDataemails.css").toExternalForm());
		return scene;
	}

}
