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
		System.out.println("Конструктор айдиc - " + this.Id);
		System.out.println("передача  - " + _id);
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
		System.out.println("геттер - " + this.Id);
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
		// Панель для данных пользователя ---/
		VBox userInfo = new VBox();
		
		
		VBox enternName = new VBox(50);
		Label enterLNameLabel = new Label("Введите имя");
		TextField textName = new TextField();
		textName.setPromptText("Имя");
		enternName.getChildren().addAll(enterLNameLabel, textName);
		//enternName.setAlignment(Pos.CENTER);
		enternName.setSpacing(5);
		enternName.getStyleClass().add("EnterUserData");
		
		VBox enternSecondName = new VBox(50);
		Label enterSecondNameLabel = new Label("Введите фамилию");
		TextField textSeconName = new TextField();
		textSeconName.setPromptText("Фамилия");
		enternSecondName.getChildren().addAll(enterSecondNameLabel, textSeconName);
		//enternSecondName.setAlignment(Pos.CENTER);
		enternSecondName.setSpacing(5);
		enternSecondName.getStyleClass().add("EnterUserData");
		
		VBox enternFatherName = new VBox(50);
		Label enterFatherNameLabel = new Label("Введите отчество");
		TextField textFatherName = new TextField();
		textFatherName.setPromptText("Отчество");
		enternFatherName.getChildren().addAll(enterFatherNameLabel, textFatherName);
		//enternFatherName.setAlignment(Pos.CENTER);
		enternFatherName.setSpacing(5);
		enternFatherName.getStyleClass().add("EnterUserData");
		
		/*VBox enterLogin = new VBox(50);
		Label enterLoginLabel = new Label("Введите логин - эмейл");
		TextField textLogin = new TextField();
		textLogin.setPromptText("email");
		enterLogin.getChildren().addAll(enterLoginLabel, textLogin);
		//enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLogin.getStyleClass().add("EnterUserData");*/
		//enternFatherName.setMargin(child, value);
		
		
		HBox enterPass = new HBox(50);
		Label enterLoginPass = new Label("Введите пароль");
		enterLoginPass.setWrapText(true);
		Label enterRule = new Label("Пароль должен содержать буквы латинского алфавита и не меннее одной цифры");
		enterRule.setWrapText(true);
		enterRule.setMaxWidth(250);
		enterRule.setWrapText(true);
		enterRule.setMinHeight(85);
		TextField pass = new TextField();
		pass.setPromptText("Введите пароль");
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
		Label enterJobLabel = new Label("Если её нет, то введите");
		enterJobLabel.setWrapText(true);
		Label lookJobLabel = new Label("Выбере Вашу должность");
		enterJobLabel.setWrapText(true);
		TextField job= new TextField();
		job.setPromptText("Должность");
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
		// кнопка обновления данных
		Button buttonUpdate= new Button("Обновисть данные");
		buttonUpdate.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonUpdate.setId("button");
		
		userInfo.getChildren().setAll(enterName,enterData,enterJob);
		userInfo.setId("Info");
		userInfo.setSpacing(8);
		
		// --- конец панели для данных пользователя ---/
		// Панель для эмэйл пользователя ---/
		
		// --- Табличка с эмейлами пользователя --- /
		int idUserInner = this.getIdUser();
		System.out.println("вызов создание объекта запроса - " + idUserInner);
		ChanngeUserInfos infos = new ChanngeUserInfos(idUserInner);
		infos.whatMailsIs();
		this.data = infos.getData();
		
		
		
		// Первая колонка
		TableColumn<EntityEmail, Integer> firstColId = new TableColumn<EntityEmail, Integer>();
		firstColId.setText("Номер");
		StringConverter<Integer> converter = new IntegerStringConverter();
		firstColId.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		firstColId.setCellValueFactory(new PropertyValueFactory<EntityEmail, Integer>("Id"));
		// Вторая колонка
		TableColumn<EntityEmail, String> secondColEmail = new TableColumn<EntityEmail, String>();
		secondColEmail.setText("Email");
		//StringConverter<SimpleStringProperty> converterString = new PercentageStringConverter();
		
		secondColEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		secondColEmail.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Email"));
		//secondColEmail.setCellValueFactory(new PropertyValueFactory("Email"));

		// Третья колонка
		TableColumn<EntityEmail, String> ColPass = new TableColumn<EntityEmail, String>();
		 ColPass .setText("Пароль");
		 ColPass.setCellFactory(TextFieldTableCell.forTableColumn());
		 ColPass.setCellValueFactory(new PropertyValueFactory<EntityEmail, String>("Password"));
		 
		TableView<EntityEmail> tableView = new TableView<EntityEmail>();
	    tableView.setItems(data);
	    tableView.getColumns().addAll( firstColId , secondColEmail,ColPass);
		// --- Конец таблички с эмейлами --- /
		
		
		
		VBox emails = new VBox();
		emails.setId("email");
		VBox enterMail = new VBox(50);
		Label enterMailLabel = new Label("Введите адрес электронной почты");
		TextField textEnterMail = new TextField();
		textEnterMail.setPromptText("email");
		enterMail.getChildren().addAll(enterMailLabel, textEnterMail);
		//enterLogin.setAlignment(Pos.CENTER);
		enterMail.setSpacing(5);
		enterMail.getStyleClass().add("EnterUserData");
		//enternFatherName.setMargin(child, value);
		
		
		HBox enterPassMail = new HBox(50);
		Label enterLoginPassMail = new Label("Введите пароль");
		Label enterRuleMail = new Label("Пароль от электронноый почты");
		enterRuleMail.setMaxWidth(250);
		enterRuleMail.setWrapText(true);
		enterRuleMail.setMinHeight(85);
		TextField passMail = new TextField();
		passMail.setPromptText("Введите пароль");
		enterPassMail.getChildren().addAll(enterLoginPassMail, passMail,enterRuleMail);
		enterPassMail.setAlignment(Pos.CENTER);
		enterPassMail.setSpacing(5);
		enterPassMail.getStyleClass().add("EnterUserData");
		
		Button buttonSelect = new Button("Выбрать почтовый адрес в качестве логина");
		buttonSelect.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonSelect.setId("button");
		Button buttonAdd = new Button("Добавить почту");
		buttonAdd.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonAdd.setId("button");
		
		emails.getChildren().setAll(tableView,buttonSelect, enterMail,enterPassMail,buttonAdd);
		emails.setSpacing(10);
		
		// --- конец панели для эмэйл пользователя---/
		// --- Задание первоначальных значений полей ---/
		EntityUser user = infos.whoIsThis();
		textName.setText(user.getFirstName());
		textSeconName.setText(user.getSecondName());
		textFatherName.setText(user.getLastName());
		pass.setText(user.getPassword());
		job.setText(user.getNamePost());
		// --- Конец задания значений первоначальныч ---/
		// -- Кнопка назад ---/
		Button buttonExit = new Button("Назад");
		buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonExit.setId("button");
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.out.print("DВызов назад");
				//primaryStage.setScene(firstScene.getScene());//,oldScene);
				
				primaryStage.setScene(oldScene);
				primaryStage.centerOnScreen();
			}
		});
		// -- Конец кнопки назад --/
		
		// -- Кнопка обновить данные пользователя --- /
		//int idposts = 0;
		buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int changeText = job.getText().compareTo(user.getNamePost());
				int changeBox = allJobs.getSelectionModel().getSelectedItem().toString().compareTo(user.getNamePost());
				if ((changeText == 0) && ( changeBox==0 )) {
					idposts = user.getIdPost();
					System.out.println("код выбора - " + idposts);
				} 
				if (changeBox != 0) {
					idposts = allJobs.getSelectionModel().getSelectedIndex();
					idposts +=1;
					System.out.println("код выбора - " + idposts);
				}
				else {
					posts.setNamePost(job.getText());
					idposts = posts.addPost("INSERT INTO `post` (`name_post`) VALUES ('" + job.getText() +"'); ");
					System.out.println("код выбора - " + idposts);
				}
				String query = "UPDATE `taskmail`.`users` SET `firstname` = '" +  textName.getText()
			+"', `secondname` = '"+textSeconName.getText() +"', `lastname` = '" + textFatherName.getText()+ "', `id_post` = '" + idposts
			+ "', `email` = '" + user.getEmail()+ "', `password` = '" + pass.getText()+ "' WHERE (`id_user` = '" + Id
			+ "');\r\n" + 
						"";
				infos.updateData(query);
			}
		});
		// -- Конец кнопки обновить данные пользователя --- /
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
