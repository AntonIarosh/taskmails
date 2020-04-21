package application.Scenes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import addManyItems.addFilesToLeterTask;
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
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		private LinkedList<Integer> allIds;
		public SceneChoseUser(Stage primaryStage, int _id,int _idChosen) {
			this.allIds = new LinkedList<Integer>();
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
		public void setData (ObservableList<EntityOneUser> datas) {
			this.data.clear();
			this.data.setAll(datas);
		}
		public void addData (EntityOneUser datase) {
			this.data.add(datase);
	
		}
		
		public int getSelectedIdUser() {
			return this.selectedIdUser;
		}
		public ObservableList<EntityOneUser> getData() {
			return this.data;
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
					ObservableList<EntityOneUser> dataf = FXCollections.observableArrayList();
					//data = infos.getData();
					dataf= infos.getData();
					if (!data.isEmpty()) {
						for (int i =0; i < data.size(); i++) {
							EntityOneUser h = data.get(i);
							System.out.println(" логин - " + h.getEmail() + " Имя - " + h.getFirstName() );
						}

					}
					
					tableView.getItems().setAll(dataf);
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
			 //----------------------------------------------------прикрепление файлов----------------------------/
		    LinkedList <String> usersId = new LinkedList();
			
			InnerShadow ih = new InnerShadow();
			InnerShadow is = new InnerShadow(30.0, Color.MEDIUMPURPLE);
			HBox Allfusers = new HBox(50);
			Allfusers.setEffect(ih);
			Allfusers.setAlignment(Pos.CENTER);
			Allfusers.setSpacing(5);
			Allfusers.setStyle("-fx-alignment: center;-fx-padding: 5px;  -fx-background-color: #FF6347; -fx-background-radius: 6;");
			//Allfusers.setMaxWidth(140);
			HBox users = new HBox(50);
			users.setEffect(is);

			users.getChildren().addAll(Allfusers);
			users.setAlignment(Pos.CENTER);
			users.setSpacing(5);
			users.setStyle("-fx-alignment: center;-fx-padding: 5px;  -fx-background-color: #DC143C; -fx-background-radius: 6;");
			//users.setMaxWidth(500);
			users.setMinWidth(300);
			
			//addFilesToLeterTask adder = new addFilesToLeterTask();

		/*	getFile.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						
					
					}
				});*/
			
			
			
			ScrollPane ScUsers =new ScrollPane();
			ScUsers.setLayoutX(10);
			ScUsers.setLayoutY(10);
			//spComment.setHmin(400);
			ScUsers .setCursor(Cursor.CLOSED_HAND);
			ScUsers.setContent(users);
			ScUsers.setMaxWidth(600);
			ScUsers.setMinWidth(300);
			ScUsers.setMinHeight(160);
			ScUsers.setStyle("-fx-alignment: center; -fx-background-color: #FFA500; -fx-padding: 2px;");
			ScUsers.setEffect(is);

		// --------------------------------- Конец прикрепления файлов -----------------------------------------/
			Label filesLabel = new Label("Адресаты");
			filesLabel.setStyle("-fx-font-weight: bold;"); 
			userInfo.getChildren().addAll(search,filesLabel,ScUsers);
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
			
			LinkedList <Integer> ch = new LinkedList();
			/*Button buttonSelect = new Button("Выбрать почтовый адрес в качестве логина");
			buttonSelect.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			buttonSelect.setId("button");*/
			Button buttonAdd = new Button("Выбрать");
			buttonAdd.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			buttonAdd.setId("button");
			buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					//if (tableView.getSelectionModel().getSelectedItem().getId() != 0) {
						text.setText(Integer.toString(tableView.getSelectionModel().getSelectedItem().getId()));
				//	} else {
						text.setText("0");
					//}
					//getAllIds().add(tableView.getSelectionModel().getSelectedItem().getId())
					ch.add(tableView.getSelectionModel().getSelectedItem().getId());
					int id = 0;
					String name=null;
					String secondName = null;
					String lastName = null;
					name = tableView.getSelectionModel().getSelectedItem().getFirstName();
					secondName = tableView.getSelectionModel().getSelectedItem().getSecondName();
					lastName = tableView.getSelectionModel().getSelectedItem().getLastName();
					id = tableView.getSelectionModel().getSelectedItem().getId();
					setVisibleMenuFiles(Allfusers, id, name, secondName, lastName);
					
					addAllIds(tableView.getSelectionModel().getSelectedItem().getId());
					
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
						System.out.println(" Размер 1 - "+ ch.size());
						for (int i=0; i < ch.size(); i++) {
							System.out.println(" коды - "+ ch.get(i));
						}
						
						
						LinkedList<Integer> allIdsdd = new LinkedList<Integer>();
						allIdsdd.addAll(getAllIds());
						Integer iteratro = null;
						iteratro = allIdsdd.size();
						for (int i=0; i <  iteratro; i++) {
							System.out.println(" кодыs - "+ allIdsdd.get(i));
						}
					} catch (Exception e2) {
					System.out.println("Ошибка");
					}
					
				}
			});
			
			emails.getChildren().setAll(tableView/*,buttonSelect, enterMail,*/,buttonAdd);
			emails.setSpacing(10);
			System.out.println(" Размер 1 - "+ ch.size());
			for (int i=0; i < ch.size(); i++) {
				System.out.println(" коды - "+ ch.get(i));
			}
			
			LinkedList<Integer> allIdsdd = new LinkedList<Integer>();
			allIdsdd.addAll(this.getAllIds());
			Integer iteratro = null;
			iteratro = allIdsdd.size();
			for (int i=0; i <  iteratro; i++) {
				System.out.println(" кодыs - "+ allIdsdd.get(i));
			}
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
			//userInfo.getChildren().add(search);
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
		public HBox setVisibleMenuFiles(HBox vBox, int _id, String _name, String _secondName, String _lastName) {
				System.out.println("код - " + _id + " имя - " + _name + " фам " + _secondName);
				
				Bloom effect = new Bloom();
				Bloom effect_ = new Bloom(0.9);
				Glow ef = new Glow(0.7);
				
				//LinkedList <Integer> allUsers = this.getAllIds();
				///Integer IDS =null;
				//IDS=_id;
				
				//allUsers.add(IDS);
			//	this.setAllIds(allUsers);	
				
				VBox user = new VBox(50);
				Label fileLabel = new Label("Получатель: ");
				fileLabel.setEffect(ef);
				
				Label userName = new Label();
				Label  userSecName = new Label();
				Label userLName = new Label();
				
				userName.setText(_name);
				userName.setWrapText(true);
				userName.setEffect(effect_);
				
				userSecName.setText(_secondName);
				userSecName.setWrapText(true);
				userSecName.setEffect(effect_);
				
				userLName.setText(_lastName);
				userLName.setWrapText(true);
				userLName.setEffect(effect_);
				
				user.getChildren().addAll(fileLabel,userName,userSecName,userLName);
				user.setAlignment(Pos.CENTER);
				user.setSpacing(5);
				
				user.getStyleClass().add("EnterTask");
				
				fileLabel.setStyle("-fx-text-fill: white;");
				userName.setStyle("-fx-text-fill: white;");
				userSecName.setStyle("-fx-text-fill: white;");
				userLName.setStyle("-fx-text-fill: white;");
			
				
				user.setStyle("-fx-border-style: dashed centered; -fx-border-width: 1.5px;-fx-background-color: #7B68EE; -fx-alignment: center;"
						+ "-fx-padding: 5px;");
				//file.setEffect(effect);
				Button delete = new Button("Удалить");
				delete.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				delete.setId("button");
				user.getChildren().add(delete);
				//file.getChildren().add(delete);
				vBox.getChildren().add(user);
				delete.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							deleteThis (vBox, user,_id);
						}
					});
			

			
			
			return vBox;
		}
		public LinkedList<Integer> getAllIds() {
			return this.allIds;
		}
		public void setAllIds(LinkedList<Integer> allIds) {
			this.allIds = allIds;
		}
		public void addAllIds(int o) {
			this.allIds.add(o);
		}
		
		public HBox deleteThis (HBox vBox, VBox c, int ID) {
			vBox.getChildren().remove(c);
			this.getAllIds(). remove(new Integer(ID));
			return vBox;
		}
}
