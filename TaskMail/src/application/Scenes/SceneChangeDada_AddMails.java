package application.Scenes;

import java.util.HashMap;

import application.DB.AddUser;
import application.DB.ChanngeUserInfos;
import application.DB.SearchJobs;
import application.Entities.EntityEmail;
import application.Entities.EntityUser;
import application.interfaces.ChangeDataEmails;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.PercentageStringConverter;

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
	@Override
	public Scene createNewScene() {
		//FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
		VBox flowPane = new VBox(50);
		//FlowPane root = new FlowPane(Orientation.HORIZONTAL);
		// ������ ��� ������ ������������ ---/
		VBox userInfo = new VBox();
		
		
		VBox enternName = new VBox(50);
		Label enterLNameLabel = new Label("������� ���");
		TextField textName = new TextField();
		textName.setPromptText("���");
		enternName.getChildren().addAll(enterLNameLabel, textName);
		//enternName.setAlignment(Pos.CENTER);
		enternName.setSpacing(5);
		enternName.getStyleClass().add("EnterUserData");
		
		VBox enternSecondName = new VBox(50);
		Label enterSecondNameLabel = new Label("������� �������");
		TextField textSeconName = new TextField();
		textSeconName.setPromptText("�������");
		enternSecondName.getChildren().addAll(enterSecondNameLabel, textSeconName);
		//enternSecondName.setAlignment(Pos.CENTER);
		enternSecondName.setSpacing(5);
		enternSecondName.getStyleClass().add("EnterUserData");
		
		VBox enternFatherName = new VBox(50);
		Label enterFatherNameLabel = new Label("������� ��������");
		TextField textFatherName = new TextField();
		textFatherName.setPromptText("��������");
		enternFatherName.getChildren().addAll(enterFatherNameLabel, textFatherName);
		//enternFatherName.setAlignment(Pos.CENTER);
		enternFatherName.setSpacing(5);
		enternFatherName.getStyleClass().add("EnterUserData");
		
		/*VBox enterLogin = new VBox(50);
		Label enterLoginLabel = new Label("������� ����� - �����");
		TextField textLogin = new TextField();
		textLogin.setPromptText("email");
		enterLogin.getChildren().addAll(enterLoginLabel, textLogin);
		//enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLogin.getStyleClass().add("EnterUserData");*/
		//enternFatherName.setMargin(child, value);
		
		
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
		
		HBox enterName = new HBox(50);
		enterName.getChildren().addAll(enternName,enternSecondName,enternFatherName);
		enterName.setId("Data");
		enterName.setSpacing(10);
		//enterName.setAlignment(Pos.CENTER);
		enterName.getStyleClass().add("EnterUserData");
		
		HBox enterData = new HBox(50);
		enterData.getChildren().addAll(/*enterLogin,*/enterPass);
		enterData.setId("Data");
		enterData.setSpacing(10);
		//enterName.setAlignment(Pos.CENTER);
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
		
		userInfo.getChildren().setAll(enterName,enterData,enterJob);
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
		//StringConverter<SimpleStringProperty> converterString = new PercentageStringConverter();
		
		secondColEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		secondColEmail.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Email"));
		//secondColEmail.setCellValueFactory(new PropertyValueFactory("Email"));

		// ������ �������
		TableColumn<EntityEmail, String> ColPass = new TableColumn<EntityEmail, String>();
		 ColPass .setText("������");
		 ColPass.setCellFactory(TextFieldTableCell.forTableColumn());
		 ColPass.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Password"));
		 
		TableView<EntityEmail> tableView = new TableView<EntityEmail>();
	    tableView.setItems(data);
	    tableView.getColumns().addAll( firstColId , secondColEmail,ColPass);
		// --- ����� �������� � �������� --- /
		
		
		
		VBox emails = new VBox();
		emails.setId("email");
		VBox enterMail = new VBox(50);
		Label enterMailLabel = new Label("������� ����� ����������� �����");
		TextField textEnterMail = new TextField();
		textEnterMail.setPromptText("email");
		enterMail.getChildren().addAll(enterMailLabel, textEnterMail);
		//enterLogin.setAlignment(Pos.CENTER);
		enterMail.setSpacing(5);
		enterMail.getStyleClass().add("EnterUserData");
		//enternFatherName.setMargin(child, value);
		
		
		HBox enterPassMail = new HBox(50);
		Label enterLoginPassMail = new Label("������� ������");
		Label enterRuleMail = new Label("������ �� ������������ �����");
		enterRuleMail.setMaxWidth(250);
		enterRuleMail.setWrapText(true);
		enterRuleMail.setMinHeight(85);
		TextField passMail = new TextField();
		passMail.setPromptText("������� ������");
		enterPassMail.getChildren().addAll(enterLoginPassMail, passMail,enterRuleMail);
		enterPassMail.setAlignment(Pos.CENTER);
		enterPassMail.setSpacing(5);
		enterPassMail.getStyleClass().add("EnterUserData");
		
		Button buttonSelect = new Button("������� �������� ����� � �������� ������");
		buttonSelect.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonSelect.setId("button");
		Button buttonAdd = new Button("�������� �����");
		buttonAdd.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonAdd.setId("button");
		
		emails.getChildren().setAll(tableView,buttonSelect, enterMail,enterPassMail,buttonAdd);
		emails.setSpacing(10);
		
		// --- ����� ������ ��� ����� ������������---/
		// --- ������� �������������� �������� ����� ---/
		EntityUser user = infos.whoIsThis();
		textName.setText(user.getFirstName());
		textSeconName.setText(user.getSecondName());
		textFatherName.setText(user.getLastName());
		pass.setText(user.getPassword());
		job.setText(user.getNamePost());
		// --- ����� ������� �������� �������������� ---/
		// -- ������ ����� ---/
		Button buttonExit = new Button("�����");
		buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonExit.setId("button");
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.out.print("D����� �����");
				//primaryStage.setScene(firstScene.getScene());//,oldScene);
				
				primaryStage.setScene(oldScene);
				primaryStage.centerOnScreen();
			}
		});
		// -- ����� ������ ����� --/
		
		// -- ������ �������� ������ ������������ --- /
		//int idposts = 0;
		buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
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
			+ "', `email` = '" + user.getEmail()+ "', `password` = '" + pass.getText()+ "' WHERE (`id_user` = '" + Id
			+ "');\r\n" + 
						"";
				infos.updateData(query);
			}
		});
		// -- ����� ������ �������� ������ ������������ --- /
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
