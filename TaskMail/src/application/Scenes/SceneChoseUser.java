package application.Scenes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import application.DB.AddUser;
import application.DB.ChanngeUserInfos;
import application.DB.ChoseUser;
import application.DB.SearchJobs;
import application.Entities.EntityEmail;
import application.Entities.EntityOneUser;
import application.Entities.EntityUser;
import application.interfaces.ChangeDataEmails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class SceneChoseUser {
		private Stage primaryStage;
		private Scene ounScene;
		private Scene oldScene;
		private AddUser add;
		private int Id;
		private ObservableList<EntityOneUser> data;
		private int idposts;
		private int selectedIdUser;
		public SceneChoseUser(Stage primaryStage, int _id,int _idChosen) {
			add = null;
			this.Id = _id;
			this.selectedIdUser=_idChosen;
			System.out.println("Конструктор айдиc - " + this.Id);
			System.out.println("передача  - " + _id);
			this.primaryStage = primaryStage;
			data = FXCollections.observableArrayList();
			this.ounScene = createNewScene();
			this.oldScene = primaryStage.getScene();
			setNewScene(this.primaryStage,this.ounScene); 
			

		}
		public void setNewScene(Stage primaryStage, Scene newScene) {
			primaryStage.close();
			primaryStage.setScene(newScene);
			primaryStage.show();
		}
		
		public void setSelectedIdUser(int _id) {
			this.selectedIdUser = _id;
		}
		public int getSelectedIdUser() {
			return this.selectedIdUser;
		}
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
			TextField text = new TextField();
			/*VBox enterLogin = new VBox(50);
			Label enterLoginLabel = new Label("Введите логин - эмейл");
			TextField textLogin = new TextField();
			textLogin.setPromptText("email");
			enterLogin.getChildren().addAll(enterLoginLabel, textLogin);
			//enterLogin.setAlignment(Pos.CENTER);
			enterLogin.setSpacing(5);
			enterLogin.getStyleClass().add("EnterUserData");*/
			//enternFatherName.setMargin(child, value);
			
			
			HBox enterName = new HBox(50);
			enterName.getChildren().addAll(enternName,enternSecondName,enternFatherName);
			enterName.setId("Data");
			enterName.setSpacing(10);
			//enterName.setAlignment(Pos.CENTER);
			//enterName.getStyleClass().add("EnterUserData");
	
						
			HBox enterJob = new HBox(50);
			Label lookJobLabel = new Label("Выберете должность");

			SearchJobs posts = new SearchJobs();
			posts.setQuery("SELECT * FROM `taskmail`.`post`");
			HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
			resultItems = posts.doQery();
			enterJob.getStyleClass().add("EnterUserData");
			
	        ChoiceBox allJobs = new ChoiceBox();
	        allJobs.getItems().addAll(resultItems.values());
	        allJobs.getSelectionModel().selectFirst();
			enterJob.getChildren().addAll(lookJobLabel,allJobs);
			enterJob.setAlignment(Pos.CENTER);
			enterJob.setSpacing(5);
			enterJob.getStyleClass().add("EnterUserData");
			// кнопка обновления данных
			Button search= new Button("Искать");
			search.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			search.setId("button");
			// -- Кнопка искать пользователя --- /
			//int idposts = 0;
			int idUserInner = this.getIdUser();
			
			// --- Табличка с именами пользователей --- /
			
			System.out.println("вызов создание объекта запроса - " + idUserInner);


			
			// Первая колонка
			TableColumn<EntityOneUser, String> LastName = new TableColumn<EntityOneUser, String>();
			LastName.setText("Отчество");
			LastName.setCellFactory(TextFieldTableCell.forTableColumn());
			LastName.setCellValueFactory(new PropertyValueFactory<EntityOneUser, String>("lastName"));
			// Вторая колонка
			TableColumn<EntityOneUser, String> Name = new TableColumn<EntityOneUser, String>();
			Name.setText("Имя");
			//StringConverter<SimpleStringProperty> converterString = new PercentageStringConverter();
			
			Name.setCellFactory(TextFieldTableCell.forTableColumn());
			Name.setCellValueFactory(new PropertyValueFactory<EntityOneUser, String>("firstName"));
			//secondColEmail.setCellValueFactory(new PropertyValueFactory("Email"));

			// Третья колонка
			TableColumn<EntityOneUser, String> SecondName = new TableColumn<EntityOneUser, String>();
			SecondName.setText("Фамилия");
			SecondName.setCellFactory(TextFieldTableCell.forTableColumn());
			SecondName.setCellValueFactory(new PropertyValueFactory<EntityOneUser, String>("secondName"));
			int idUser = 0;	 
			// --- Конец таблички с эмейлами --- /
			TableView<EntityOneUser> tableView = new TableView<EntityOneUser>();
		    //tableView.setItems(data);
		    tableView.getColumns().addAll( Name,SecondName,LastName);
			search.setOnAction(new EventHandler<ActionEvent>() {
				ChoseUser infos = new ChoseUser(idUserInner);
				@Override
				public void handle(ActionEvent e) {
					String tailFirstPart = "SELECT * FROM `taskmail`.`users` WHERE ";
					if (!textName.getText().isEmpty()) {
						tailFirstPart += "`firstname` ='" +textName.getText()+"'";
					}
					if ((!textName.getText().isEmpty()) && (!textSeconName.getText().isEmpty())) {
						tailFirstPart += " AND ";
					}
					
					if (!textSeconName.getText().isEmpty()) {
						tailFirstPart += "`secondname` ='" +textSeconName.getText()+"'";
					}
					if ( (!textSeconName.getText().isEmpty()) && (!textFatherName.getText().isEmpty())) {
						tailFirstPart += " AND ";
					}
					if (!textFatherName.getText().isEmpty()) {
						tailFirstPart += "`lastname` ='" +textFatherName.getText()+"'";
					}
					System.out.println(" Запрос на поиск  - " +tailFirstPart );
					infos.setSearchQuery(tailFirstPart);
					infos.whatIs();
					data.clear();
					data = infos.getData();
					if (!data.isEmpty()) {
						for (int i =0; i < data.size(); i++) {
							EntityOneUser h = data.get(i);
							System.out.println(" логин - " + h.getEmail() + " Имя - " + h.getFirstName() );
						}

					}
					
					tableView.getItems().setAll(data);
					//EntityOneUser chosenUser = (EntityOneUser)tableView.getSelectionModel().getSelectedItem();
					//System.out.println(" пароль - " + chosenUser.getPassword() );
					tableView.refresh();
				}
			});
			
			
			//this.data = infos.getData();
			userInfo.getChildren().setAll(enterName/*,enterJob*/);
			userInfo.setId("Info");
			userInfo.setSpacing(8);
			
			// --- конец панели для данных пользователя ---/
			// Панель для имен пользователей ---/
		
			
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
			
			
			/*Button buttonSelect = new Button("Выбрать почтовый адрес в качестве логина");
			buttonSelect.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			buttonSelect.setId("button");*/
			Button buttonAdd = new Button("Выбрать");
			buttonAdd.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			buttonAdd.setId("button");
			buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					text.setText(Integer.toString(tableView.getSelectionModel().getSelectedItem().getId()));
					
					System.out.println( " Айди - " + text.getText());
					String filePath = "idChosenUser.txt";
					PrintWriter output = null;
					try {
						output = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					try {
						output.println(tableView.getSelectionModel().getSelectedItem().getId());
						System.out.println(tableView.getSelectionModel().getSelectedItem().getId());
						output.close();
						// Сообщение об успехе -- /
						Alert alert = new Alert(AlertType.INFORMATION,"Пользователь выбран! Теперь Вы можете вернуться назад");
						alert.setTitle("Выбор адресата");
						alert.setHeaderText("Получатель");
						alert.show();
						// Коенец сообщение об успехе -- /
					} catch (Exception e2) {
					System.out.println("Ошибка");
					}
					
				}
			});
			
			emails.getChildren().setAll(tableView/*,buttonSelect, enterMail,*/,buttonAdd);
			emails.setSpacing(10);


			// --- конец панели для эмэйл пользователя---/
			// --- Задание первоначальных значений полей ---/
			//EntityUser user = infos.whoIsThis();
			/*textName.setText(user.getFirstName());
			textSeconName.setText(user.getSecondName());
			textFatherName.setText(user.getLastName());*/

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
			

			// -- Конец кнопки обновить данные пользователя --- /
			userInfo.getChildren().add(search);
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
		
		public int getIdUser() {
			System.out.println("геттер - " + this.Id);
			return this.Id;
		}
		public void setId(int _id) {
			this.Id = _id;
		}
}
