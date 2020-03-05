package application.Scenes;

import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import application.DB.GetInfoLogin;
import application.Mails.QuickLetterSend;
import application.StyleClasses.ButonStyle;
import application.StyleClasses.VboxStyle;
import application.interfaces.mainWindowUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneMainWindow implements mainWindowUser {
	//protected static final int SceneMainWindow getIdUser() = 0;
	private Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	private int id;
	
	public SceneMainWindow(Stage primaryStage, int UserId) {
		/*
		this.primaryStage = primaryStage;
		this.oldScene = primaryStage.getScene();
		this.ounScene = createNewScene();
		*/
		this.id = UserId;
		this.primaryStage = primaryStage;
		this.ounScene = createNewScene();
		this.oldScene = primaryStage.getScene();
		setNewScene(this.primaryStage,this.ounScene); 
	}
	
	public int getIdUser() {
		return this.id;
	}
	@Override
	public void exitFormProagram() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFromLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean adUserInformatiob() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTask() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addWorkGroup() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Scene createNewScene() {
		
		BorderPane border =new BorderPane();
		// Выход ---- 
		Button exitFromProgram = new Button("Выйти из программы");
		Button exitFromLogin = new Button("Выйти из учётной записи");
		ButonStyle styles = new ButonStyle(exitFromProgram);
		exitFromProgram = styles.getStyleButton();
		styles.setButon(exitFromLogin);
		exitFromLogin = styles.getStyleButton();
		exitFromLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.out.print("DВызов назад");
				//primaryStage.setScene(firstScene.getScene());//,oldScene);
				//setReturnScene();
				primaryStage.setScene(oldScene);
				primaryStage.centerOnScreen();
			}
		});
		exitFromProgram.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.exit(1);
			}
		});
		
		// Выход ----/
		// Аккордион меню ----
		GetInfoLogin info = new GetInfoLogin();
		
		DatePicker picker = new DatePicker();
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();
		
		info.setID(this.id);
		info.wantKnown();
		 Accordion accordion = new Accordion();
		 VboxStyle lk = new VboxStyle ();
		 VBox personalAccounts = lk.getStyleVbox();
		 
	     /*String URLwork = "/application/pictures/work.png";
	     Image ICON_3 = new Image(getClass().getResourceAsStream(URLwork));
	     ImageView work = new ImageView(ICON_3);
		 Label Joblabel = new Label("",work);
		 Joblabel.setId("job");
		 Joblabel.setWrapText(true);
		 Joblabel.setMinWidth(100);
		 Joblabel.setText(info.getJob());
		 Joblabel.setContentDisplay(ContentDisplay.LEFT);*/
		 
	     String URL = "/application/pictures/man.png";
	     Image ICON_1 = new Image(getClass().getResourceAsStream(URL));
	     ImageView man = new ImageView(ICON_1);
	     
	     String URLsName = "/application/pictures/secondName.png";
	     Image ICON_2 = new Image(getClass().getResourceAsStream(URLsName));
	     ImageView secName = new ImageView(ICON_2);
		 Label SecondNamelabel = new Label("");
		 
		 Label Namelabel = new Label("",secName);
		 Namelabel.setId("name");
		 Namelabel.setWrapText(true);
		 Namelabel.setMinWidth(100);
		 Namelabel.setText(info.getName());
		 Namelabel.setContentDisplay(ContentDisplay.LEFT);
		 

		 SecondNamelabel.setId("secondName");
		 SecondNamelabel.setWrapText(true);
		 SecondNamelabel.setMinWidth(100);
		 SecondNamelabel.setText(info.getSecondName()+" " +info.getLastName()+" : "+info.getJob());
		 SecondNamelabel.setContentDisplay(ContentDisplay.LEFT);
	
		 int id_user = this.getIdUser();
		 System.out.println(" айди юзера - " + id_user);
		 Button changMyInfo = new Button("Изменить данные");
		 changMyInfo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneChangeDada_AddMails change = new SceneChangeDada_AddMails(primaryStage,  id_user);
					change.setId(id_user);
				}
			});
		 Button help = new Button("Помощь");
		 Button about = new Button("О программе");
		 help.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 help.setMaxWidth(160);
		 help.setId("button");
		 about.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 about.setMaxWidth(160);
		 about.setId("button");
		// Button mail = new Button("Электронная почта");
		 
		 changMyInfo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 changMyInfo.setMaxWidth(160);
		 changMyInfo.setId("button");
		 
		/* workGroup.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 workGroup.setMaxWidth(160);
		 workGroup.setId("button");
		 
		 LookWorkGroup.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 LookWorkGroup.setMaxWidth(160);
		 LookWorkGroup.setId("button");*/
		 
		// mail.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		// mail.setMaxWidth(160);
		// mail.setId("button");
		 /*styles.setButon(changMyInfo);
		 changMyInfo = styles.getStyleButton(); 
		 styles.setButon(workGroup);
		 workGroup = styles.getStyleButton(); 
		 styles.setButon(LookWorkGroup);
		 LookWorkGroup = styles.getStyleButton(); 
		 styles.setButon(mail );
		 mail  = styles.getStyleButton(); */
		 
		 
		 personalAccounts.getChildren().setAll(Namelabel,SecondNamelabel);
	/*	 if(!Joblabel.getText().isEmpty()) {
			 personalAccounts.getChildren().add(Joblabel);
		 }*/
		 VboxStyle intr = new VboxStyle ();
		 VBox instr = intr.getStyleVbox();
		 personalAccounts.getChildren().addAll(/*mail,*/changMyInfo/*,workGroup,LookWorkGroup*/);
		 personalAccounts.setId("pp");
		 personalAccounts.setMinWidth(250);
		 
		 
		
		 instr.getChildren().setAll(popupContent);
		 VboxStyle ex = new VboxStyle ();
		 VBox exits = ex.getStyleVbox();
		 exits.getChildren().addAll(exitFromProgram,exitFromLogin);
		 exits.setId("exit");
		 //TitledPane personalAccount= new TitledPane("Личный кабинет",personalAccounts);
		 TitledPane  instrument = new TitledPane("Инструменты",instr);
		 TitledPane  exit = new TitledPane("Выход",exits);
	     accordion.getPanes().addAll(/*personalAccount,*/
	    		 instrument,exit);
	        accordion.setMinSize(250, 200);
	        accordion.setPrefSize(250, 200);
	    // Аккордион меню ----/
	        //read doc
	    /// Тулбар ---/
	     ToolBar toolbar = new ToolBar();
	     toolbar.getItems().add(changMyInfo);
	     toolbar.getItems().add(help);
	     toolbar.getItems().add(about);
	        

	        /// конец тулбара ---/
	        // Бокс для инфо ---/
	        
	        // Конец бокса для иноф ---/
	        // Бокс для верха ---/
	    HBox top = new HBox();
	    top.setSpacing(10);
	    top.setAlignment(Pos.CENTER_LEFT);
	    top.setId("H");
	    personalAccounts.setSpacing(10);
	    personalAccounts.setAlignment(Pos.CENTER);
	    toolbar.setStyle("-fx-base: dodgerblue;");
	    top.getChildren().addAll(personalAccounts,toolbar);
	    // конец бокса для верха ---/
	    // Бокс для низа --/
	    VBox bottom = new VBox();
	    bottom .setSpacing(10);
	    bottom .setAlignment(Pos.CENTER_LEFT);
	    bottom .setId("bottom");
	    bottom.setMaxWidth(200);
	  
	    Label quick = new Label("Быстрое электронное письмо ");
		TextField emailThem = new TextField();
		//emailThem.setMinSize(250, 80);
		emailThem.setPromptText("Введите тему сообщения");
		//TextField email = new TextField();
		TextArea email = new TextArea();
		email.setPrefRowCount(6);            
		
		email.setPromptText("Введите сообщение");
		//email.setMinSize(250, 100);
		Button adress = new Button("Выберете адресат");
		Button push = new Button("Отправить");
		push.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		push.setId("button");
		adress.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		adress.setId("button");
		bottom.getChildren().setAll(quick,emailThem, email,adress,push );
		push.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				QuickLetterSend sender = new QuickLetterSend();
				try {
					sender.senMail(emailThem.getText(), email.getText() );
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	    // Конец бокса для низа 
	    TabPane tabpane=new TabPane();
	    Tab tabSh = new Tab("Текущее расписание");
	    tabSh.setClosable(false);
	    Group rootSh = new Group();
	    tabSh.setContent(rootSh);
	    // Новая задача --/
	    Tab tabWork = new Tab("Создать задачу");
	    tabWork.setClosable(false);
	    Group rootWork = new Group();
	    tabWork.setContent(rootWork);
	    // Конец Новая задача
	    tabpane.getTabs().addAll(tabSh,tabWork);
	    
	    Label alarm = new Label(" Добрый день ");
	    HBox alarmBox= new HBox();
	    alarmBox.setId("BoxForAlarm");
	    alarmBox.getChildren().setAll(alarm);
	    
	    Scene scene = new Scene(border, 850, 650);
	    //double widthWindow = accordion.getWidth() + tabpane.getWidth();
	    double widthWindow = primaryStage.getScene().getWidth()*1.7;
	    //border.getWidth();
		alarm.setId("alarm");
		//alarm.setContentDisplay(ContentDisplay.CENTER);
		//alarm.setMinWidth(widthWindow);
		//alarm.setAlignment(Pos.CENTER);
		
		
		//border.setRight(exits); 
		border.setTop(top);
		border.setBottom(alarmBox);
	    border.setCenter(tabpane);
	    border.setLeft(accordion);
	    border.setId("win");
	    border.setRight(bottom );
		
		scene.getStylesheets().add(getClass().getResource("/application/styles/mainWindow.css").toExternalForm());
		return scene;
	}


	@Override
	public void set() {
		this.primaryStage.setScene(this.ounScene);
		this.primaryStage.show();
	}
	public void setNewScene(Stage primaryStage, Scene newScene) {
		primaryStage.close();
		primaryStage.setScene(newScene);
		primaryStage.show();
	}

}
