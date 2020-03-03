package application.Scenes;



import application.DB.CheckLogin;
import application.StyleClasses.ButonStyle;
import application.interfaces.loginCheck;
import application.interfaces.loginScene;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.event.EventHandler;

public class Login implements loginScene {
	private Scene innerScene;
	private CheckLogin cheker;
	private int userId;
	
	public Login() {
		innerScene = null;
		//mainWindiw = null;
	}
	public Login(Stage primaryStage) {
		createScene(primaryStage);
		this.cheker = null;
		
	}
	@Override
	public Scene getScene() {
		return innerScene;
	}
	
	public void setScene(Scene newScene) {
		this.innerScene = newScene;
	}

	@Override
	public Scene createScene(Stage primaryStage) {
		
		VBox window = new VBox(50);
		window.setAlignment(Pos.CENTER);
		//FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 4, 2);
		Scene scene = new Scene(window, 600, 670);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		VBox present = new VBox(50);
		Label presents = new Label("Добро пожаловать");
		Label enter = new Label("Введит Ваши регистрационные данные для входа в систему");
		present.getChildren().addAll(presents, enter);
		present.setAlignment(Pos.CENTER);
		present.setSpacing(15);
		present.setId("Gritings");
		present.setMaxWidth(520);
		presents.setStyle("-fx-text-fill: #00137F; -fx-font-size: 16pt; ");
		enter.setStyle("-fx-text-fill: #00137F; -fx-font-size: 12pt; ");
		
		HBox enterLogin = new HBox(50);
		 enterLogin .getStyleClass().add("Enter");
		Label enterLoginLabel = new Label("Введите логин");
		TextField text = new TextField();
		text.setPromptText("Введите Эмейл");
		enterLogin.getChildren().addAll(enterLoginLabel, text);
		enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLoginLabel.setId("login");
		
		
		HBox enterPass = new HBox(50);
		enterPass.getStyleClass().add("Enter");
		Label enterLoginPass = new Label("Введите пароль");
		PasswordField pass = new PasswordField();
		pass.setPromptText("Введите пароль");
		enterPass.getChildren().addAll(enterLoginPass, pass);
		enterPass.setAlignment(Pos.CENTER);
		enterPass.setSpacing(5);
		enterLoginPass.setId("pass");
		
		Rectangle rec  = new Rectangle(200.0,20.0);
		rec.setId("Rectangle");
		
		
		VBox need = new VBox(50);
		
	     String URLwork = "/application/pictures/ops.png";
	     Image ICON_3 = new Image(getClass().getResourceAsStream(URLwork));
	     ImageView work = new ImageView(ICON_3);
	     
	     String URL = "/application/pictures/yes.png";
	     Image ICON_4 = new Image(getClass().getResourceAsStream(URLwork));
	     ImageView yes = new ImageView(ICON_4);
		 //Label Joblabel = new Label("",work);
		Label alarm = new Label("");
		alarm.setStyle("-fx-text-fill: ff0000; -fx-font-size: 14pt; -fx-font-weight: bold;");
		//alarm.setWrapText(true);
		//alarm.setTextAlignment(TextAligment.);
		Label needs = new Label("Вы должны быть зарегистрированным");
		needs.setId("needs");
		Button button = new Button("Регистрация");

		ButonStyle styles = new ButonStyle(button);
		button = styles.getStyleButton();
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				SceneUserData usersAd = new SceneUserData(primaryStage);
			}
		});
		need.getChildren().addAll(alarm, needs, button);
		need.setAlignment(Pos.CENTER);
		need.setSpacing(5);
		
		VBox logPas = new VBox(50);
		logPas.getChildren().addAll(enterLogin, enterPass);
		//logPas.setAlignment(Pos.TOP_LEFT);
		logPas.setSpacing(5);
		logPas.setId("LogPas");
		
		Button buttonEnter = new Button("Вход");
		buttonEnter.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonEnter.setId("button");
		buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("|"+pass.getText()+"|"+ text.getText()+"|");
				if (check(pass.getText(), text.getText())) {
					alarm.setText("Вы зарегистрированы!");
					alarm.setGraphic(yes);
					//mainWindiw.set();
					SceneMainWindow mainWindiw = new SceneMainWindow(primaryStage,userId);
					
				} else {
					alarm.setText("Вы не правильно ввели данные\n   или не зарегистрированы!");
					alarm.setGraphic(work);
				}
			}
		});
		logPas.getChildren().add(buttonEnter);
		logPas.setMaxWidth(500);
		logPas.setAlignment(Pos.CENTER);
		String IMAGE = "/application/3.png";
		Image ICON = new Image(getClass().getResourceAsStream(IMAGE));
		ImageView image = new ImageView(ICON);
		
		//flowPane.setId("win");
		//flowPane.getChildren().addAll(present, enterLogin, enterPass, buttonEnter, need);
		window.getChildren().addAll(present,image, /*enterLogin, enterPass,buttonEnter,*/logPas,  need);
		//window.setAlignment(Pos.);
		window.setSpacing(15);
		window.setId("win");
		//window.setStyle("-fx-background-image: url(/application/todo-list.png);");
		setScene(scene);
		
		return scene;
	}
	@Override
	public boolean check(String pass,String text) {
		boolean exist = false;
		this.cheker = new CheckLogin();
		exist = this.cheker.check(pass,text);
		this.userId = this.cheker.getId();
		return exist ;
	}


}
