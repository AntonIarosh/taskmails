package application.Scenes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import application.DB.AddUser;
import application.DB.SearchJobs;
import application.interfaces.enterDataUsers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneUserData implements enterDataUsers{
	private Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	private AddUser add;
	public SceneUserData(Stage primaryStage) {
		add = null;
		this.primaryStage = primaryStage;
		this.ounScene = createScene();
		this.oldScene = primaryStage.getScene();
		setNewScene(this.primaryStage,this.ounScene); 
	}
	@Override
	public void setNewScene(Stage primaryStage, Scene newScene) {
		primaryStage.close();
		primaryStage.setScene(newScene);
		primaryStage.show();
	}

	@Override
	public Scene createScene() {
		//HBox windiw = new HBox();
		FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
		
		String IMAGE = "/application/pictures/big.png";
		Image ICON = new Image(getClass().getResourceAsStream(IMAGE));
		ImageView image = new ImageView(ICON);
		image.setStyle("-fx-opacity: 0.6;");
		image.setId("come");
		//windiw.getChildren().setAll(flowPane);
		//windiw.setMinSize(850, 300);
		//windiw.setId("win");
		VBox enternName = new VBox(50);
		Label enterLNameLabel = new Label("Введите имя");
		TextField textName = new TextField();
		textName.setPromptText("Имя");
		enternName.getChildren().addAll(enterLNameLabel, textName);
		enternName.setAlignment(Pos.CENTER);
		enternName.setSpacing(5);
		enternName.getStyleClass().add("EnterUserData");
		
		VBox enternSecondName = new VBox(50);
		Label enterSecondNameLabel = new Label("Введите фамилию");
		TextField textSeconName = new TextField();
		textSeconName.setPromptText("Фамилия");
		enternSecondName.getChildren().addAll(enterSecondNameLabel, textSeconName);
		enternSecondName.setAlignment(Pos.CENTER);
		enternSecondName.setSpacing(5);
		enternSecondName.getStyleClass().add("EnterUserData");
		
		VBox enternFatherName = new VBox(50);
		Label enterFatherNameLabel = new Label("Введите отчество");
		TextField textFatherName = new TextField();
		textFatherName.setPromptText("Отчество");
		enternFatherName.getChildren().addAll(enterFatherNameLabel, textFatherName);
		enternFatherName.setAlignment(Pos.CENTER);
		enternFatherName.setSpacing(5);
		enternFatherName.getStyleClass().add("EnterUserData");
		
		VBox enterLogin = new VBox(50);
		Label enterLoginLabel = new Label("Введите логин");
		TextField textLogin = new TextField();
		textLogin.setPromptText("логин");
		enterLogin.getChildren().addAll(enterLoginLabel, textLogin);
		enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLogin.getStyleClass().add("EnterUserData");
		//enternFatherName.setMargin(child, value);
		
		HBox enterName = new HBox(50);
		enterName.getChildren().addAll(enternName,enternSecondName,enternFatherName,enterLogin);
		enterName.setId("Data");
		enterName.setSpacing(10);
		enterName.setAlignment(Pos.CENTER);
		
		
		
		HBox enterPass = new HBox(50);
		Label enterLoginPass = new Label("Введите пароль");
		Label enterRule = new Label("Пароль должен содержать буквы латинского алфавита и не меннее одной цифры");
		enterRule.setMaxWidth(250);
		enterRule.setWrapText(true);
		enterRule.setMinHeight(85);
		TextField pass = new TextField();
		pass.setPromptText("Введите пароль");
		enterPass.getChildren().addAll(enterLoginPass, pass,enterRule);
		enterPass.setAlignment(Pos.CENTER);
		enterPass.setSpacing(5);
		enterPass.getStyleClass().add("EnterUserData");
		
		HBox enterJob = new HBox(50);
		Label enterJobLabel = new Label("Если её нет, то введите");
		Label lookJobLabel = new Label("Выбере Вашу должность");
		TextField job= new TextField();
		job.setPromptText("Должность");
		SearchJobs posts = new SearchJobs();
		posts.setQuery("SELECT * FROM `taskmail`.`post`");
		HashMap <Integer, String> resultItems = new HashMap <Integer, String>();
		resultItems = posts.doQery();
		enterJob.getStyleClass().add("EnterInfo");
		
		//String itms[] = {"Dog", "Cat", "Horse"};
        ChoiceBox allJobs = new ChoiceBox();
        allJobs.getItems().addAll(resultItems.values());
        allJobs.getSelectionModel().selectFirst();
		enterJob.getChildren().addAll(lookJobLabel,allJobs,enterJobLabel, job);
		enterJob.setAlignment(Pos.CENTER);
		enterJob.setSpacing(5);
		//flowPane.setSpacing(15);
		Button readData = new Button("Ознакомиться");
		readData.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		readData.setId("button");
		readData.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Dialog <ButtonType> dialog = new Dialog <ButtonType>();
				dialog.setTitle("Обработка персональных данных");
				dialog.setHeaderText("Вы даёте согласие на это!");
				//dialog.setContentText
				ScrollPane sp=new ScrollPane();
				//dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		 
		        final DialogPane dialogPane = dialog.getDialogPane();
		        dialogPane.setContentText("Настоящая политика обработки персональных данных части 1, 5 пункта 1 статьи 6 Федерального закона РФ «О персональных данных»." + 
						"\r\n" + 
						"2. Согласие пользователя на обработку персональных данных\r\n" + 
						"Отправляя сообщение через формы обратной связи, размещенные на сайте, пользователь сайта выражает свое согласие на обработку персональных данных в определенных настоящей Политикой целях и объеме." + 
						"" + 
						"Пользователь может подписаться на получение рассылки по электронной почте, если такая возможность предоставляется. Рассылка может содержать сведения о новостях, аналитических материалах, мероприятиях, проводимых Компанией, и тому подобное. Заполняя поле «e-mail», пользователь дает свое согласие на получение таких рассылок. Пользователь в любой момент может отозвать свое согласие на получение рассылки. Возможность отписаться от рассылки предоставляется пользователю в каждом письме." + 
						"\r\n" + 
						"3. Цель обработки персональных данных\r\n" + 
						"Целью обработки персональных данных является предоставление пользователю информации о компании, в том числе условий заключения договоров, образцов продукции и т. п. Если пользователь сайта просто просматривает сайт, то персональные данные не обрабатываются." + 
						"\r\n" + 
						"4. Объем обрабатываемых персональных данных\r\n" + 
						"В программе пользователь может указать следующие персональные данные: фамилию, имя, отчество, адрес электронной почты." + 
						"" + 
						"\r\n" + 
						"5. Конфиденциальность персональных данных\r\n" + 
						"Компания не раскрывает третьим лицам и не распространяет персональные данные пользователей без их согласия, кроме случаев, предусмотренных федеральным законом.\r\n" + 
						"\r\n" + 
						"6. Срок обработки персональных данных\r\n" + 
						"Предоставленные пользователем данные обрабатываются бессрочно. Обработка персональных данных прекращается в случае отзыва пользователем согласия на обработку персональных данных либо удаления его учетной записи." + 
						"\r\n" + 
						"7. Права субъекта персональных данных" + 
						"Субъект персональных данных вправе направить запрос администратору сайта на получение информации, касающейся обработки его персональных данных в соответствии с требованиями статьи 14 Федерального закона РФ «О персональных данных»." + 
						"\r\n" + 
						"Данный запрос может быть направлен в «Службу поддержки».\r\n" + 
						"\r\n" + 
						"8. Защита персональных данных\r\n" + 
						"Компания принимает меры, необходимые и достаточные для обеспечения выполнения обязанностей, предусмотренных Федеральным законом «О персональных данных» и принятыми в соответствии с ним нормативными правовыми актами. Компания самостоятельно определяет состав и перечень мер, необходимых и достаточных для обеспечения выполнения таких обязанностей." + 
						"\r\n" + 
						"Доступ к персональным данным имеют только уполномоченные сотрудники Компании. Все сотрудники Компании, имеющие доступ к персональным данным, должны придерживаться политики по обеспечению конфиденциальности и защиты персональных данных. В целях обеспечения конфиденциальности информации и защиты персональных данных Компания поддерживает соответствующую ИТ-среду и принимает все меры, необходимые для предотвращения несанкционированного доступа");;
		        dialogPane.getButtonTypes().addAll(ButtonType.OK);
		        dialogPane.setMinSize(1100, 650);
		        dialogPane.getChildren().add(sp);
		        dialog.setWidth(1100);
		    	dialog.setHeight(650);
		        //dialog.initModality(Modality.APPLICATION_MODAL);
		        dialog.setDialogPane(dialogPane);
		        /*dialog.showAndWait()
		               .filter(response -> response == ButtonType.OK)
		               .ifPresent(response -> System.out.println("The exception was approved"));*/
		        
		        dialog.setWidth(1000);
		    	dialog.setHeight(750);
				//ScrollPane sp=new ScrollPane();
				sp.setLayoutX(10);
				sp.setLayoutY(10);
				sp.setCursor(Cursor.CLOSED_HAND);
				
				dialog.show();
			}
		});
		/*String IMAGE = "/application/DataAdd.png";
		Image ICON = new Image(getClass().getResourceAsStream(IMAGE));
		ImageView image = new ImageView(ICON);*/
		Label alarm = new Label();
		alarm.setId("alarm");
		CheckBox box = new CheckBox();
		Button Enter = new Button("Регистрация");
		Enter.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		Enter.setId("button");
		Enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				alarm.setText("");
				if(box.selectedProperty().getValue()==true) {
					if (textName.getText().isEmpty() && textSeconName.getText().isEmpty() && textFatherName.getText().isEmpty() && pass.getText().isEmpty()) {
						alarm.setText("Вы не ввели Ваше Ф.И.О. и пароль!");
					}
					else {
						String currentJob;
						int idpost;
						if (job.getText().isEmpty()) {
							idpost = allJobs.getSelectionModel().getSelectedIndex();
							idpost +=1;
							System.out.println("код выбора - " + idpost);
						} else {
							posts.setNamePost(job.getText());
							idpost = posts.addPost("INSERT INTO `post` (`name_post`) VALUES ('" + job.getText() +"'); ");
							System.out.println("код выбора - " + idpost);
						}
						
						String login = textLogin.getText();
						//alarm.setText("ВНиМАНИЕ! Вы не ввели Ваше Ф.И.О. и пароль!");
						String ins = "INSERT INTO `users` (`firstname`,`secondname`,`lastname`,`id_post`,`login`,`password`) VALUES ('"+ textName.getText() 
						+ "', '" +textSeconName.getText() + "','" + textFatherName.getText() + "','" +  idpost + "','" + textLogin.getText() + "','" + pass.getText()+"');";
						System.out.println(login);
						System.out.println(ins);
						add = new AddUser(ins);
						

						boolean execTrue = add.execeteQuery();
						if(execTrue) {
							String found = "SELECT * FROM `users` WHERE (`login` = '"+ textLogin.getText()+"' AND `password` = '"+pass.getText()+"') ";
							String insertMail = "INSERT INTO `taskmail`.`email`(`email`) VALUES ('"+textLogin.getText() +"');";
							//add.setQuery(insertMail);
							//add.setMail(textLogin.getText());
							//String insertUserMAil ="INSERT INTO `taskmail`.`user_email` (`id_user`,`id_email`) VALUES ( '" + add.WhoAdd(found)+"', '" + add.addMail()+"');";
							//add.addUserEmail(insertUserMAil);
							Alert alert = new Alert(AlertType.INFORMATION,"Регистрация прошла успешно! Теперь Вы можете войти в систему");
							alert.setTitle("Регистрация");
							alert.setHeaderText("Вы зарегистрированы");
							alert.show();
							alarm.setText("Вы зарегистрированы! ");
						}
					}
				}
				else {
					alarm.setText("Вы не согласны с требованием, касающиеся обработки Ваших персональных данных!");
				}
			}
		});
		HBox reg = new HBox(50);
		reg.setAlignment(Pos.CENTER);
		reg.setSpacing(15);
		reg.setPadding(new Insets(10.0,10.0,10.0,10.0));
		Label personalData = new Label("Я согласен на обработку персональных данных");
		personalData.setWrapText(true);
		
		reg.getChildren().addAll(box,personalData, readData, Enter  );
		reg.setId("Info");
		
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
		
		VBox inner = new VBox(50);
		
		inner.getChildren().addAll(enterName,enterPass, enterJob,reg, alarm,buttonExit);
		inner.setAlignment(Pos.CENTER);
		inner.setSpacing(7);
		inner.setId("inner");
		
		
		flowPane.getChildren().setAll(/*image, enterName,enterPass, enterJob,reg, alarm,buttonExit*/inner);
		flowPane.setHgap(10);
		flowPane.setVgap(10);
//flowPane.setLayoutX(10);
		//flowPane.setLayoutY(10);
		flowPane.setColumnHalignment(HPos.CENTER);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setId("w");

		Scene scene = new Scene(flowPane, 850, 650);
		scene.getStylesheets().add(getClass().getResource("/application/styles/enterData.css").toExternalForm());
		return scene;
	}

	@Override
	public boolean safeData(String name, String secondName, String fatherName, String job, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

}
