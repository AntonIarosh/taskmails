package application.Scenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import addManyItems.addFilesToLeterTask;
//import application.DateTimePicker;
import application.DB.AddUser;
import application.DB.GetInfoLogin;
import application.DB.ReadOneTask2;
import application.DB.ReadOunTasks;
import application.DB.ReadOunTasks3;
import application.DB.WriteTaskInDBCreated;
import application.Entities.EntityTask;
import application.Mails.QuickLetterSend;
import application.Mails.TaskLetterSend;
import application.Mails.LetterRecive;
import application.StyleClasses.ButonStyle;
import application.StyleClasses.VboxStyle;
import application.interfaces.mainWindowUser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneMainWindow implements mainWindowUser {
	//protected static final int SceneMainWindow getIdUser() = 0;
	private Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	private int id;
	private LinkedList<Integer> allIds;
	private SceneChoseUser change;
	private LinkedList <String> paths;
	
	public SceneMainWindow(Stage primaryStage, int UserId) {
		this.setPaths(new LinkedList<String>());
		this.allIds = new LinkedList<Integer>();
		change = null;
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


	/**
	 *
	 */
	@Override
	public Scene createNewScene() {
		int idChosenUser=0;
		BorderPane border =new BorderPane();
		Scene scene = new Scene(border, 850, 650);
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
		
		Label LetterRecive = new Label();
		LetterRecive.setWrapText(true);
		LetterRecive.setId("time");
		ScrollPane al =new ScrollPane();
		al.setLayoutX(10);
		al.setLayoutY(10);
		//spComment.setHmin(400);
		al.setCursor(Cursor.CLOSED_HAND);
		al.setContent(LetterRecive);
		al.setMinWidth(250);
		
		 Button mes = new Button("Письмецо");
			//int idChoseUser = 0;
			Label textM = new Label();
			textM.setWrapText(true);
			mes.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						application.Mails.LetterRecive letter = new application.Mails.LetterRecive();
						LetterRecive.setText(letter.getEmail());
					}
				});
			mes.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			mes.setId("button");
			
		
		// Выход ----/
		// Аккордион меню ----
		GetInfoLogin info = new GetInfoLogin();
		
		DatePicker picker = new DatePicker();
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        final Label uiTimer = new Label();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> uiTimer.setText(LocalDateTime.now().format(formatter)))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
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
		 Namelabel.setText(info.getSecondName());
		 Namelabel.setContentDisplay(ContentDisplay.LEFT);
		 

		 SecondNamelabel.setId("secondName");
		 SecondNamelabel.setWrapText(true);
		 SecondNamelabel.setMinWidth(100);
		 SecondNamelabel.setText(info.getName()+" " +info.getLastName()+" : "+info.getJob());
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
		 Button more = new Button("Полное расписание");
		 help.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 help.setMaxWidth(160);
		 help.setId("button");
		 about.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 about.setMaxWidth(160);
		 about.setId("button");
		 more.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 more.setMaxWidth(160);
		 more.setId("button");
		 more.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneOneWeekAndMonth moreTasks = new SceneOneWeekAndMonth(primaryStage,id);
				}
			});
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
		 
		 
		 VBox bottom = new VBox();
		 instr.getChildren().setAll(popupContent,bottom);
		 VboxStyle ex = new VboxStyle ();
		 VBox exits = ex.getStyleVbox();
		 exits.getChildren().addAll(exitFromProgram,exitFromLogin,     mes,         al);
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
	     toolbar.getItems().add(more);
	        

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
	    
	    bottom .setSpacing(10);
	    bottom .setAlignment(Pos.CENTER_LEFT);
	    bottom .setId("bottom");
	    bottom.setMaxWidth(200);
	  
	    Label quick = new Label("Быстрое электронное письмо ");
	    quick.setWrapText(true);
		TextField emailThem = new TextField();
		//emailThem.setMinSize(250, 80);
		emailThem.setPromptText("Введите тему сообщения");
		//TextField email = new TextField();
		TextArea email = new TextArea();
		email.setPrefRowCount(6);            
		
		email.setPromptText("Введите сообщение");
		//email.setMinSize(250, 100);
		Button adress = new Button("Выберите адресата");
		//int idChoseUser = 0;
		TextField text = new TextField();
		adress.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					LinkedList<Integer> all = getAllIds();
					all.clear();
					change = new SceneChoseUser(primaryStage,  id_user, idChosenUser);
					
					change.setId(id_user);
					all.addAll(change.getAllIds());
					System.out.println (" Количество есть - " + change.getAllIds());
					System.out.println (" Количество записано- " + all.size());
					setAllIds(all);
					System.out.println (" Количество в класса- " + getAllIds());
				}
			});
		Button push = new Button("Отправить");
		Button quickMail = new Button("Быстрое сообщение");
		Button quickMailClose = new Button("Закрыть быстрое сообщение");
		push.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		push.setId("button");
		adress.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		adress.setId("button");
		quickMail.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		quickMail.setId("button");
		quickMailClose.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		quickMailClose.setId("button");
		quickMailClose.setWrapText(true);
		bottom.getChildren().setAll(quick,quickMail);
		quickMail.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if ( (!bottom.getChildren().contains(emailThem)) && (!bottom.getChildren().contains(email))) {
					bottom.getChildren().addAll(emailThem, email,adress,push,quickMailClose );
					bottom.getChildren().removeAll(quickMail);
				}
			}
		});
		quickMailClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				bottom.getChildren().removeAll(emailThem, email,adress,push,quickMailClose);
				bottom.getChildren().addAll(quickMail);
			}
		});
		bottom.getChildren().setAll(quickMail/*,quick,emailThem, email,adress,push */);
		//bottom.getChildren().
		push.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LinkedList<Integer> all = getAllIds();
				all.clear();
				change.setId(id_user);
				all.addAll(change.getAllIds());
				System.out.println (" Количество есть - " + change.getAllIds());
				System.out.println (" Количество записано- " + all.size());
				setAllIds(all);
				System.out.println (" Количество в класса- " + getAllIds());
				
				
				int idch = 0;
				Scanner scanner = null;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
					scanner = new Scanner(reader);
					int numberOfRows = 0;
					while (scanner.hasNext()) {
						idch = scanner.nextInt();
						//text.setText(Integer.toString(scanner.nextInt()));
						//System.out.println(" Вывод из главного окна - " + text.getText());
						System.out.println(" Вывод из главного окна - " + idch);
						scanner.nextLine();
						numberOfRows++;
					}
				} catch (FileNotFoundException e1) {
					System.out.println("Файл не найден.");
				} catch (Exception e1) {
					System.out.println("Ошибка при считывании из файла.");
					scanner.close();
				} finally {
					scanner.close();
				}
				
				
				QuickLetterSend sender = new QuickLetterSend();
				try {
					System.out.println (" Количество в классе - " + getAllIds().size());
					sender.senMail(emailThem.getText(), email.getText(),idch,id,getAllIds());
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
	    // Выданные задачи

	    Tab tabTask = new Tab("Выданные задачи");
	    tabTask.setClosable(false);
	    Group rootTask = new Group();
	    tabTask.setContent(rootTask);
	    // месяц --/
	   /* Tab tabMonth = new Tab("Месяц");
	    tabWork.setClosable(false);
	    Group rootMonth = new Group();
	    tabMonth.setContent(rootMonth);*/
	    
	    // --- Бокс для задачи ---/ --------------------------------------------
	    //----------------------------------------------------прикрепление файлов----------------------------/
	   // LinkedList <String> paths = new LinkedList();
		Button getFile = new Button("Прикрепить файл");
		getFile.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		getFile.setId("button");
		
		Button clearAll = new Button("Очистить список");
		clearAll.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		clearAll.setId("button");
		
		InnerShadow ih = new InnerShadow();
		InnerShadow is = new InnerShadow(30.0, Color.MEDIUMPURPLE);
		VBox Allfiles = new VBox(50);
		Allfiles.setEffect(ih);
		Allfiles.setAlignment(Pos.CENTER);
		Allfiles.setSpacing(5);
		Allfiles.getStyleClass().add("EnterTask");
		Allfiles.setMaxWidth(140);
		VBox files = new VBox(50);
		files.setEffect(is);
		Label filesLabel = new Label("Прикрепление файлов");
		filesLabel.setStyle("-fx-font-weight: bold;"); 
		files.getChildren().addAll(filesLabel,getFile,Allfiles,clearAll);
		files.setAlignment(Pos.CENTER);
		files.setSpacing(5);
		files.getStyleClass().add("EnterTask");
		files.setMaxWidth(155);
		
		addFilesToLeterTask adder = new addFilesToLeterTask();

		getFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					
					adder.setVisibleMenuFiles(Allfiles,primaryStage);
					LinkedList <String> paths_ = new LinkedList <String>();
					paths_.addAll(getPaths());
					paths_.addAll(adder.getPaths());
					System.out.println(" Файлы - для прикрепления: " + paths_.size());
					setPaths(paths_);
					
					//paths.addAll(adder.getPaths());
					
					for (int i=0; i <paths_.size(); i++) {
						System.out.println(" Файлы - для прикрепления: " + paths_.get(i));
						
					}
				}
			});
		clearAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Allfiles.getChildren().clear();
				LinkedList <String> paths_ = new LinkedList <String>();
				paths_.addAll(getPaths());
				paths_.clear();
				setPaths(paths_);
			}
		});
		
		
		
		ScrollPane ScFiles =new ScrollPane();
		ScFiles.setLayoutX(10);
		ScFiles.setLayoutY(10);
		//spComment.setHmin(400);
		ScFiles .setCursor(Cursor.CLOSED_HAND);
		ScFiles.setContent(files );
		ScFiles.setMaxWidth(165);
		ScFiles.setMinWidth(165);
		ScFiles.setStyle("-fx-alignment: center;");

	// --------------------------------- Конец прикрепления файлов -----------------------------------------/
	    
	    
	    HBox task = new HBox();
	    task.setSpacing(10);
	    task.setAlignment(Pos.CENTER_LEFT);
	    task.setId("Task");
	    
	    VBox themeTask = new VBox(50);
		Label themeTaskLabel = new Label("Введите тему задачи");
		TextField textTheme = new TextField();
		textTheme.setPromptText("Тема");
		themeTask.getChildren().addAll(themeTaskLabel, textTheme);
		themeTask.setAlignment(Pos.CENTER);
		themeTask.setSpacing(5);
		themeTask.getStyleClass().add("EnterTask");
		textTheme.setOnKeyPressed(event-> {
			int num = 95- textTheme.getText().length();
			themeTaskLabel.setText("Введите тему задачи. Осталось символов: " + num);
			if(textTheme.getText().length() > 95) {
				
				textTheme.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				textTheme.setEditable(true);
			}
		});
	    VBox colTask = new VBox(50);
		Label colTaskLabel = new Label("Введите периодичность задачи");
		TextField textTaskCol = new TextField();
		textTaskCol.setPromptText("Периодичность");
		colTask.getChildren().addAll(colTaskLabel, textTaskCol);
		colTask.setAlignment(Pos.CENTER);
		colTask.setSpacing(5);
		colTask.getStyleClass().add("EnterTask");
		textTaskCol.setOnKeyPressed(event-> {
			int num = 42- textTheme.getText().length();
			colTaskLabel.setText("Введите периодичность задачи. Осталось символов: " + num);
			if(textTaskCol.getText().length() > 42) {
				
				textTaskCol.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				textTaskCol.setEditable(true);
			}
		});
		
		Button adressTask = new Button("Выберите адресата");
		adressTask .getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		adressTask .setId("button");
		TextField textTaskAdress = new TextField();
		adressTask .setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					LinkedList<Integer> all = getAllIds();
					all.clear();
					change = new SceneChoseUser(primaryStage,  id_user, idChosenUser);
					change.setId(id_user);
					
					change.setId(id_user);
					all.addAll(change.getAllIds());
					System.out.println (" Количество есть - " + change.getAllIds());
					System.out.println (" Количество записано- " + all.size());
					setAllIds(all);
					System.out.println (" Количество в класса- " + getAllIds());
				}
			});
		

		
		VBox body = new VBox(50);
		Label bodyLabel = new Label("Задание");
		TextArea bodyMail = new TextArea();
		bodyMail.setPromptText("Суть задачи");
		bodyMail.setPrefRowCount(10); 
		body.getChildren().addAll(bodyLabel, bodyMail);
		body.setAlignment(Pos.CENTER);
		body.setSpacing(5);
		body.getStyleClass().add("EnterTask");
		bodyMail.setOnKeyPressed(event-> {
			int num = 960- bodyMail.getText().length();
			bodyLabel.setText("Задание. Осталось символов: " + num);
			if(bodyMail.getText().length() > 960) {
				
				bodyMail.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				bodyMail.setEditable(true);
			}
		});
		
		VBox description = new VBox(50);
		Label descriptionLabel = new Label("Дополнительное описание");
		TextArea descriptionMail = new TextArea();
		descriptionMail.setPromptText("Подробности выполнения");
		descriptionMail.setPrefRowCount(5); 
		description.getChildren().addAll(descriptionLabel, descriptionMail);
		description.setAlignment(Pos.CENTER);
		description.setSpacing(5);
		description.getStyleClass().add("EnterTask");
		descriptionMail.setOnKeyPressed(event-> {
			int num = 960- descriptionMail.getText().length();
			descriptionLabel.setText("Дополнительное описание. Осталось символов: " + num);
			if(descriptionMail.getText().length() > 960) {
				
				descriptionMail.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				descriptionMail.setEditable(true);
			}
		});
		
		VBox link = new VBox(50);
		Label linkLabel = new Label("Ссылка");
		TextArea linkMail = new TextArea();
		linkMail.setPromptText("Ссылки");
		linkMail.setPrefRowCount(7); 
		linkMail.setOnKeyPressed(event-> {
			int num = 590 - linkMail.getText().length();
			linkLabel.setText("Ссылка. Осталось символов: " + num);
			if(linkMail.getText().length() > 590) {
				
				linkMail.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				linkMail.setEditable(true);
			}
		});
		link.getChildren().addAll(linkLabel, linkMail);
		link.setAlignment(Pos.CENTER);
		link.setSpacing(5);
		link.getStyleClass().add("EnterTask");
		
		VBox dateStart = new VBox(50);
		Label dateStartLabel = new Label("Дата начала задачи");
		DatePicker datePickerStart = new DatePicker();
		
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
		
		dateStart.getChildren().addAll(dateStartLabel, datePickerStart);
		dateStart.setAlignment(Pos.CENTER);
		dateStart.setSpacing(5);
		dateStart.getStyleClass().add("EnterTask");
		
		VBox timeStart = new VBox(50);
		Label timeStartLabel = new Label("Время начала задания");
		HBox timeS = new HBox(50);
		timeS.setAlignment(Pos.CENTER);
		timeS.setSpacing(5);
		timeS.getStyleClass().add("EnterTask");
		Label doter = new Label(" : ");
		TextField startHour = new TextField();
		startHour.setMaxWidth(50);
		startHour.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	startHour.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
		startHour.setOnKeyPressed(event-> {
			int num = 2 - startHour.getText().length();
			//linkLabel.setText("Ссылка. Осталось символов: " + num);
			if(startHour.getText().length() > 2) {
				
				startHour.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				startHour.setEditable(true);
			}
		});
		TextField startMinute = new TextField();
		startMinute.setMaxWidth(50);
		startMinute.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	startMinute.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
		startMinute.setOnKeyPressed(event-> {
			int num = 2 - startMinute.getText().length();
			//linkLabel.setText("Ссылка. Осталось символов: " + num);
			if(startMinute.getText().length() > 2) {
				
				startMinute.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				startMinute.setEditable(true);
			}
		});
            
		timeS.getChildren().addAll(startHour,doter,startMinute);
		timeStart.getChildren().addAll(timeStartLabel,timeS);
		timeStart.setAlignment(Pos.CENTER);
		timeStart.setSpacing(5);
		timeStart.getStyleClass().add("EnterTask");
		
		VBox timeEnd = new VBox(50);
		Label timeEndLabel = new Label("Время окончания задания");
		Label doter_ = new Label(" : ");
		HBox timeE = new HBox(50);
		timeE.setAlignment(Pos.CENTER);
		timeE.setSpacing(5);
		timeE.getStyleClass().add("EnterTask");
		TextField endHour = new TextField();
		endHour.setMaxWidth(50);
		endHour.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		            if (!newValue.matches("\\d*")) {
		            	endHour.setText(newValue.replaceAll("[^\\d]", ""));
		            	if (newValue.matches("[0-9]+$")) {
	                        int number = Integer.parseInt(newValue);
	                        if (number > 24) {
	                            return;
	                        }
	                        endHour.setText(oldValue);
	                    }
		            }
		        }
		    });
		endHour.setOnKeyPressed(event-> {
			int num = 2 - endHour.getText().length();
			//linkLabel.setText("Ссылка. Осталось символов: " + num);
			if(endHour.getText().length() > 2) {
				
				endHour.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				endHour.setEditable(true);
			}
		});
		TextField endMinute = new TextField();
		endMinute.setMaxWidth(50);
		endMinute.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	endMinute.setText(newValue.replaceAll("[^\\d]", ""));
	            	if (newValue.matches("[0-9]+$")) {
                        int number = Integer.parseInt(newValue);
                        if (number > 60) {
                            return;
                        }
                        endMinute.setText(oldValue);
                    }
	            }
	        }
	    });
		endMinute.setOnKeyPressed(event-> {
			int num = 2 - endMinute.getText().length();
			//linkLabel.setText("Ссылка. Осталось символов: " + num);
			if(endMinute.getText().length() > 2) {
				
				endMinute.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				endMinute.setEditable(true);
			}
		});
		timeE.getChildren().addAll(endHour,doter_,endMinute);
		timeEnd.getChildren().addAll(timeEndLabel,timeE);
		timeEnd.setAlignment(Pos.CENTER);
		timeEnd.setSpacing(5);
		timeEnd.getStyleClass().add("EnterTask");
		
		VBox dateEnd = new VBox(50);
		Label dateEndLabel = new Label("Дата конца задачи");
		DatePicker datePickerEnd = new DatePicker();
		dateEnd.getChildren().addAll(dateEndLabel, datePickerEnd);
		dateEnd.setAlignment(Pos.CENTER);
		dateEnd.setSpacing(5);
		dateEnd.getStyleClass().add("EnterTask");
		
		VBox BoxDone = new VBox(50);
		Label isDoneLabel = new Label("Отметка о выполнении задачи");
		CheckBox isDone = new CheckBox();
		BoxDone.getChildren().addAll(isDoneLabel,  isDone);
		BoxDone.setAlignment(Pos.CENTER);
		BoxDone.setSpacing(5);
		BoxDone.getStyleClass().add("EnterTask");
		
	    VBox supervisor = new VBox(50);
		Label supervisorLabel = new Label("Введите Ф.И.О. руководителя задачи");
		supervisorLabel.setWrapText(true);
		TextField textSupervisor = new TextField();
		textSupervisor.setPromptText("Ф.И.О.");
		supervisor.getChildren().addAll(supervisorLabel, textSupervisor);
		supervisor.setAlignment(Pos.CENTER);
		supervisor.setSpacing(5);
		supervisor.getStyleClass().add("EnterTask");
		textSupervisor.setOnKeyPressed(event-> {
			int num = 195 - textSupervisor.getText().length();
			supervisorLabel.setText("Введите Ф.И.О. руководителя задачи. Осталось символов: " + num);
			if(textSupervisor.getText().length() > 195) {
				
				textSupervisor.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				textSupervisor.setEditable(true);
			}
		});
		
		VBox urgencyBox = new VBox(50);
		Label urgencyLabel = new Label("Срочность задачи");
		ChoiceBox urgency = new ChoiceBox();
		urgency.getItems().addAll("Планово", "Важно", "Срочно");
		urgency.getSelectionModel().selectFirst();
		urgencyBox.getChildren().addAll(urgencyLabel,urgency);
		urgencyBox.setAlignment(Pos.CENTER);
		urgencyBox.setSpacing(5);
		urgencyBox.getStyleClass().add("EnterTask");
		
		Button pushTaskOun = new Button("Задать себе задачу");
		pushTaskOun.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		pushTaskOun.setId("button");
		pushTaskOun.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int idch = 0;
				String Theme = null;
				String Text = null;
				
				
				String comment = null;
				String body = null;
				String supervisor = null;
				String link = null;
				String description = null;
				String dateStart = null;
				String dataEnd = null;
				String dataCreate = null;
				Date data = null;
				String urgencyMail = null;
				String taskCol = null;// Периодичность
				String itsDone = "не выполнено";
				body = bodyMail.getText();
				description = descriptionMail.getText();
				link = linkMail.getText();
				supervisor = textSupervisor.getText();
				taskCol = textTaskCol.getText();
				
				String linkForDb = null;
				
				// Код срочности задачи. -- /
				int idurgency;
				idurgency = urgency.getSelectionModel().getSelectedIndex();
				idurgency +=1;
				System.out.println("код выбора - " + idurgency);
				// конец кода срочности задачи -- /
				//urgencyMail = Integer.toString(idurgency); 
				// дата создания задачи -- /
				Date dt = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				// конец даты создания задачи -- /
				// дата начала задачи -- /
				String StartTime = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() + ":" + 0;
				// дата конца начала задачи -- /
				// дата конца задачи -- /
				String EndTime = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() + ":" + 0;
				// дата конца конца задачи -- /
				
				// Выполнение задачи -- /
				int itsDoneToDB = 0;
				// конец выполнения задачи -- /
				
				// Запрос на добавление записи в таблицу задачи
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + textTheme.getText() +"', "
				+ "'" + body + "', '" + idurgency + "', '" + currentTime + "',"
				+ " '" + StartTime + "', '" + EndTime + "', '" + supervisor + "', ' " + itsDoneToDB + "', '" + taskCol + "', '" + description + "');";
				// конец запроса на добавление записи в таблицу
				// Добавление задачи в бд -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// конец добавления задачи в бд -- /
				
				// Узнать какой айди у новой добавленной задачи -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + textTheme.getText()+ "' AND `task`.`body` = '" + body +"';";
				int idNewTask = add.WhoAdd(idNew);
				// конец узнавайния айди новой задачи -- /
				
				//Добавить запись в таблицу пользователь задача -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + id + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// конец добавление записи в таблицу задача пользователя -- /
				

				
				// проверка и заполнение ссылки -- /
				if (!linkMail.getText().isEmpty() ) {
					linkForDb = linkMail.getText(); 
					// Дабавление записи в таблицу ссылка к задаче -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + linkForDb + "');";
					add.addLink(addLink);
					// конец Дабавление записи в таблицу ссылка к задаче -- /
				}
				// конец обработки ссылки -- /
				// Сообщение об успехе -- /
				Alert alert = new Alert(AlertType.INFORMATION,"Задание было добавлено в расписание, а также сохранено в Вашей базе данных");
				alert.setTitle("Создание задания");
				alert.setHeaderText("Задание сформировано и добавлено!");
				alert.show();
				// Коенец сообщение об успехе -- /
			}
		});
		
		
		Button pushTask = new Button("Отправить");
		pushTask.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		pushTask.setId("button");
		pushTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				LinkedList<Integer> all = getAllIds();
				all.clear();
				change.setId(id_user);
				all.addAll(change.getAllIds());
				System.out.println (" Количество есть - " + change.getAllIds());
				System.out.println (" Количество записано- " + all.size());
				setAllIds(all);
				System.out.println (" Количество в класса- " + getAllIds());
				
				
				int idch = 0;
				String Theme = null;
				String Text = null;
				String comment = null;
				String body = null;
				String supervisor = null;
				String link = null;
				String description = null;
				String dateStart = null;
				String dataEnd = null;
				String dataCreate = null;
				Date data = null;
				String urgencyMail = null;
				String taskCol = null;// Периодичность
				String itsDone = "не выполнено";
				body = bodyMail.getText();
				description = descriptionMail.getText();
				link = linkMail.getText();
				supervisor = textSupervisor.getText();
				taskCol = textTaskCol.getText();
				
				String linkForDb = null;

				
				// Код срочности задачи. -- /
				int idurgency;
				idurgency = urgency.getSelectionModel().getSelectedIndex();
				idurgency +=1;
				System.out.println("код выбора срочности - " + idurgency);
				// конец кода срочности задачи -- /
				//urgencyMail = Integer.toString(idurgency); 
				// дата создания задачи -- /
				Date dt = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				// конец даты создания задачи -- /
				// дата начала задачи -- /
				String StartTime = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() + ":" + 0;
				// дата конца начала задачи -- /
				// дата конца задачи -- /
				String EndTime = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() + ":" + 0;
				// дата конца конца задачи -- /
				
				// Выполнение задачи -- /
				int itsDoneToDB = 0;
				// конец выполнения задачи -- /
				
				// Запрос на добавление записи в таблицу задачи
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + textTheme.getText() +"', "
				+ "'" + body + "', '" + idurgency + "', '" + currentTime + "',"
				+ " '" + StartTime + "', '" + EndTime + "', '" + supervisor + "', ' " + itsDoneToDB + "', '" + taskCol + "', '" + description + "');";
				// конец запроса на добавление записи в таблицу
				
				// Добавление задачи в бд -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// конец добавления задачи в бд -- /
				
				// Узнать какой айди у новой добавленной задачи -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + textTheme.getText()+ "' AND `task`.`body` = '" + body +"';";
				//String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`id_task` = LAST_INSERT_ID();";
				
				int idNewTask = add.WhoAdd(idNew);
				// конец узнавайния айди новой задачи -- /
				
				//Добавить запись в таблицу пользователь задача -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + id + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// конец добавление записи в таблицу задача пользователя -- /
		
				// проверка и заполнение ссылки -- /
				if (!linkMail.getText().isEmpty() ) {
					linkForDb = linkMail.getText(); 
					// Дабавление записи в таблицу ссылка к задаче -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + linkForDb + "');";
					add.addLink(addLink);
					// конец Дабавление записи в таблицу ссылка к задаче -- /
				}
				// конец обработки ссылки -- /
				
				urgencyMail = urgency.getSelectionModel().getSelectedItem().toString();
				dateStart = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() ;
				dataEnd = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() ;
				GregorianCalendar thisData = new GregorianCalendar();
				data = thisData.getTime();
				//dataCreate = data.toString();
				dataCreate =  currentTime;
				
				Theme = "Живое расписание: Задание: " + textTheme.getText();
				Text  = "Задание: " + body  + ";\n" +
				"Дополниельное описание: " +description+";\n" + 
				"Ссылка на дополнительные материалы: " + link + ";\n" +
				"Руководитель: " +supervisor+";\n" +
				"Периодичность выполения: " + taskCol + ";\n"+
				"Срочность задачи: " + urgencyMail +";\n" +
				"Дата и время начала выполнения: " + dateStart +";\n" +
				"Дата и время окончания выполенения: " + dataEnd +";\n"+
				"Дата и время создания задачи:" + dataCreate + ";\n" +
				"Выполнение: " + itsDone ; 
				
				Scanner scanner = null;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
					scanner = new Scanner(reader);
					int numberOfRows = 0;
					while (scanner.hasNext()) {
						idch = scanner.nextInt();
						//text.setText(Integer.toString(scanner.nextInt()));
						//System.out.println(" Вывод из главного окна - " + text.getText());
						System.out.println(" Вывод из главного окна - " + idch);
						scanner.nextLine();
						numberOfRows++;
					}
				} catch (FileNotFoundException e1) {
					System.out.println("Файл не найден.");
				} catch (Exception e1) {
					System.out.println("Ошибка при считывании из файла.");
					scanner.close();
				} finally {
					scanner.close();
				}
				
				TaskLetterSend sender = new TaskLetterSend();
				try {
					System.out.println (" Количество в классе - " + getAllIds().size());
					//sender.senMail(emailThem.getText(), email.getText(),idch,id,getAllIds());
					sender.senMail(Theme, Text,idch,id,body,supervisor,link,description,dateStart,dataEnd, dataCreate,urgencyMail,taskCol,itsDone,idNewTask,getAllIds(),getPaths());
					
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		HBox buttons = new HBox(50);
		buttons.getChildren().addAll(adressTask,pushTask,pushTaskOun);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(5);
		buttons.getStyleClass().add("UP1");
		
		VBox mainPart = new VBox(50);
		mainPart.getChildren().addAll(themeTask, supervisor,urgencyBox,colTask,BoxDone,link,buttons/*adressTask,pushTask*/);
		mainPart.setAlignment(Pos.CENTER);
		mainPart.setSpacing(5);
		mainPart.getStyleClass().add("UP3");
		
		HBox Start = new HBox(50);
		HBox End = new HBox(50);
		Start .getChildren().addAll(dateStart,timeStart);
		Start .setAlignment(Pos.CENTER);
		Start .setSpacing(5);
		Start .getStyleClass().add("UP1");
		End.getChildren().addAll(dateEnd,timeEnd);
		End.setAlignment(Pos.CENTER);
		End.setSpacing(5);
		End.getStyleClass().add("UP1");
		
		
		VBox DatePart = new VBox(50);
		DatePart.getChildren().addAll(Start,End);
		DatePart.setAlignment(Pos.CENTER);
		DatePart.setSpacing(5);
		DatePart.getStyleClass().add("UP2");
		
		
		VBox SecondPart = new VBox(50);
		SecondPart.getChildren().addAll(body,description,DatePart);
		SecondPart.setAlignment(Pos.CENTER);
		SecondPart.setSpacing(5);
		SecondPart.getStyleClass().add("UP3");
		
		
	/*	VBox enterLogin = new VBox(50);
		Label enterLoginLabel = new Label("Введите логин - эмейл");
		TextField textLogin = new TextField();
		textLogin.setPromptText("email");
		enterLogin.getChildren().addAll(enterLoginLabel, textLogin);
		enterLogin.setAlignment(Pos.CENTER);
		enterLogin.setSpacing(5);
		enterLogin.getStyleClass().add("EnterUserData");
		//enternFatherName.setMargin(child, value);*/
		
		HBox TASK = new HBox(50);
		TASK .getChildren().addAll(mainPart,SecondPart,ScFiles);
		TASK .setId("Task");
		TASK .setSpacing(10);
		TASK .setAlignment(Pos.CENTER);
		//task
		tabWork.setContent(TASK);
	    // --- Конец бокса для новой задачи ---/
	    // Конец Новая задача
		
		// Начало выданная задача -- /
		
		VBox givenTasks = new VBox(50);
		//givenTasks.getChildren().addAll();
		givenTasks.setId("Task");
		givenTasks.setSpacing(10);
		givenTasks.setAlignment(Pos.CENTER);
		
		Label _Label = new Label(" Выданные задачи: ");
		givenTasks.getChildren().add(_Label);
		//givenTasks.setMinWidth(800);
		givenTasks.setMinWidth(scene.getWidth());
	
		LinkedList <EntityTask> data = new LinkedList <EntityTask>();
		// Запрос
		String query = "SELECT * FROM `taskmail`.`task` WHERE `task`.`supervisor` = '" + info.getSecondName()+" "+ info.getName()+" "+ info.getLastName()+ "';";
		System.out.println("Запрос на задачи - " + query);
		ReadOunTasks ounTasks = new ReadOunTasks(info.getSecondName()+" "+ info.getName()+" "+ info.getLastName());
		ounTasks.setSearchQuery(query);
		ounTasks.whatIs();
		data = ounTasks.getData();
		Label numberTasks = new Label(" Задано: " + data.size() );
		givenTasks.getChildren().add(numberTasks);
		
		for (int i =0; i < data.size(); i++) {
			System.out.println("Начался вывод задач ");
			EntityTask thisTask = data.get(i);
			//String name = Integer.toString(i);
			VBox body_ = new VBox(50);
			Label body_Label = new Label();
			
			Label a_Label = new Label("Описание: ");
			body_Label.setText(data.get(i).getBode());
			body_.getChildren().addAll(a_Label, body_Label);
			body_.setAlignment(Pos.CENTER);
			body_.setSpacing(5);
			body_.getStyleClass().add("OneStringBaze");
			
			VBox title_ = new VBox(50);
			Label t_Label = new Label("Заголовок: ");
			Label title_Label = new Label();
			title_Label.setText(data.get(i).getTitle());
			title_.getChildren().addAll(t_Label,title_Label);
			title_.setAlignment(Pos.CENTER);
			title_.setSpacing(5);
			title_.getStyleClass().add("OneStringBaze");
			
			HBox mean = new HBox(50);
			mean.getChildren().addAll(title_,body_);
			mean.setAlignment(Pos.CENTER);
			mean.setSpacing(5);
			mean.getStyleClass().add("OneString");
			
			// Время
			Locale.setDefault(new Locale("ru","RU"));
			/*GregorianCalendar dateCreate = new GregorianCalendar();
			dateCreate.setTime(res.getDate(5));
			Date dateC = dateCreate.getTime();*/
			Date dateS = data.get(i).getDateStrart();
			GregorianCalendar dateStarts = new GregorianCalendar();
			dateStarts.setTime(dateS);
			int Month = dateStarts.get(Calendar.MONTH) + 1;
			String DateSAllT = " " + dateStarts.get(Calendar.DAY_OF_MONTH) + "." + Month +"."+ dateStarts.get(Calendar.YEAR) + " " +
					dateStarts.get(Calendar.HOUR_OF_DAY) + ":" + dateStarts.get(Calendar.MINUTE);
			
			
			Date dateE = data.get(i).getDateEnd();
			GregorianCalendar dateEnds = new GregorianCalendar();
			dateEnds.setTime(dateE);
			Month = dateEnds.get(Calendar.MONTH) + 1;
			String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Month + "."+ dateEnds.get(Calendar.YEAR) + " " +
					dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
			
			
			
			VBox dateST = new VBox(50);
			Label dateST_Label = new Label();
			Label datest_Label = new Label("Дата и время начала выполнения: ");
			datest_Label.setWrapText(true);
			//dateST_Label.setText(data.get(i).getDateStrart().toString());
			dateST_Label.setText(DateSAllT);
			dateST.getChildren().addAll(datest_Label, dateST_Label);
			dateST.setAlignment(Pos.CENTER);
			dateST.setSpacing(5);
			dateST.getStyleClass().add("OneStringBaze");
			
			VBox dateEn = new VBox(50);
			Label dateEn_Label = new Label();
			Label dateen_Label = new Label("Дата и время окончания выполнения: ");
			dateen_Label.setWrapText(true);
			//dateEn_Label.setText(data.get(i).getDateEnd().toString());
			dateEn_Label.setText(DateEAllT);
			dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
			dateEn.setAlignment(Pos.CENTER);
			dateEn.setSpacing(5);
			dateEn.getStyleClass().add("OneStringBaze");
			
			HBox dates = new HBox(50);
			dates.getChildren().addAll(dateST,dateEn);
			dates.setAlignment(Pos.CENTER);
			dates.setSpacing(5);
			dates.getStyleClass().add("OneString");
			
			 Button inf = new Button("Узнать подробнее");
			 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			 //inf.setMaxWidth(160);
			 inf.setId("button");
			 inf.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						
						SceneOneTask t = new SceneOneTask(primaryStage,id,thisTask);
					}
				});
			 
			VBox name = new VBox(50);
			name.getChildren().addAll(mean,dates,inf);
			name.setId("OneTask");
			name.setSpacing(10);
			name.setAlignment(Pos.CENTER);
			
			givenTasks.getChildren().add(name);
			
		}
		ScrollPane sp=new ScrollPane();
		sp.setLayoutX(10);
		sp.setLayoutY(10);
		sp.setHmin(400);
		sp.setCursor(Cursor.CLOSED_HAND);
		sp.setContent(givenTasks);
		//sp.set
		//sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		
		
		tabTask.setContent(sp);

		// Конец выданная задача -- /
		
		// Текущие задачи -- /
		

		
		// КОнец попытка с циклами ... --- /
		//01
		
		
	
		VBox _tasksAllDay = new VBox(50);
		//_all.setId("C");
		_tasksAllDay.setSpacing(10);
		_tasksAllDay.setAlignment(Pos.CENTER);
		Label _allLabel = new Label(" Расписание ");
		//_allLabel.setId("PULL");
		_tasksAllDay.getChildren().addAll(_allLabel/*,sp01,sp02,sp03,sp04,sp05,sp06,sp07,sp08,sp09,sp10,sp11,
				sp12, sp13, sp14, sp15, sp16, sp17, sp18, sp19, sp20,sp21, sp22, sp23, sp24*/);
		_tasksAllDay.setMinWidth(250);
		
		VBox _44 = new VBox(50);
		//_all.setId("C");
		_44.setSpacing(10);
		_44.setAlignment(Pos.CENTER);
		//Label _allLabel = new Label(" Расписание ");
		//_allLabel.setId("PULL");
		_44.getChildren().addAll(_allLabel/*,sp01,sp02,sp03,sp04,sp05,sp06,sp07,sp08,sp09,sp10,sp11,
				sp12, sp13, sp14, sp15, sp16, sp17, sp18, sp19, sp20,sp21, sp22, sp23, sp24*/);
		_44.setMinWidth(250);

		
		ScrollPane all =new ScrollPane();
		all.setLayoutX(10);
		all.setLayoutY(10);
		//spComment.setHmin(400);
		all.setCursor(Cursor.CLOSED_HAND);
		
		all.setMinWidth(250);
		
		 Button whatTasks = new Button(" Обновить задачи ");
		 whatTasks.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 //inf.setMaxWidth(160);
		 whatTasks.setId("button");
		 whatTasks.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					_tasksAllDay.getChildren().clear();
					_tasksAllDay.getChildren().addAll(_allLabel);
					all.setContent(getTasksOnThisDays (_tasksAllDay));
					//getTaskOnThisDay(_tasksAllDay);
					//getTasksOnThisDays (_tasksAllDay);
				}
			});
		
		 _44.getChildren().addAll(whatTasks,all); 
		 
		 
		// Попытка создать циклы... --- /
		// дата  -- //
	/*	Date thisDate = new Date();
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String currentTime = sdf.format(dt);
		
		GregorianCalendar dateStarts = new GregorianCalendar();
		dateStarts.setTime(thisDate);
		int Month = dateStarts.get(Calendar.MONTH) + 1;
		String DateSAllT = dateStarts.get(Calendar.YEAR) + "-_"+ Month +"-_" + dateStarts.get(Calendar.DAY_OF_MONTH);
		String queryForThisDay ="SELECT * FROM `taskmail`.`task` WHERE `task`.`start_date_time` LIKE '" + DateSAllT + "%'; ";
		System.out.println("Дата для выбора - " + queryForThisDay);
		ReadOunTasks thisDayTasks = new ReadOunTasks();
		thisDayTasks.setSearchQuery(queryForThisDay);
		thisDayTasks.whatIs();
		data = thisDayTasks.getData();
		System.out.println("количество задач на сегодня - " + data.size());
		
		for(int i =0; i <24; i++) {
			int numberTask = i+1;
			HBox allTasksforOneHour = new HBox(50);
			allTasksforOneHour.setId("C");
			allTasksforOneHour.setSpacing(10);
			allTasksforOneHour.setAlignment(Pos.CENTER);
			Label _01_TasksLabels = new Label(" Задачи на этот час: " + numberTask+":00");
			_01_TasksLabels.setId("PULL");
			allTasksforOneHour.getChildren().add(_01_TasksLabels);
			allTasksforOneHour.setMinWidth(250);
			
			if (allTasksforOneHour.getChildren().size() == 1) {
				
			}
			
			ScrollPane allScroolForOneHour =new ScrollPane();
			allScroolForOneHour.setLayoutX(10);
			allScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			allScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			allScroolForOneHour.setContent(allTasksforOneHour);
			allScroolForOneHour.setMinWidth(250);
			
			HBox DoneallTasksforOneHour = new HBox(50);
			DoneallTasksforOneHour.setId("C");
			DoneallTasksforOneHour.setSpacing(10);
			DoneallTasksforOneHour.setAlignment(Pos.CENTER);
			Label DoneTasks = new Label(" Выполненные задачи: " + numberTask+":00");
			DoneTasks.setId("PULL");
			DoneallTasksforOneHour.getChildren().add(DoneTasks);
			DoneallTasksforOneHour.setMinWidth(250);
			
			VBox TasksforOneHour = new VBox(50);
			TasksforOneHour.setId("C");
			TasksforOneHour.setSpacing(10);
			TasksforOneHour.setAlignment(Pos.CENTER);
			Label _01Label1 = new Label(" Час " + numberTask +": ");
			_01Label1.setId("PULL");
			TasksforOneHour.setMinWidth(250);
			//givenComments.getStyleClass().add("Data");
			
			HBox day = new HBox(50);
			day.setId("S");
			day.setSpacing(10);
			day.setAlignment(Pos.CENTER);
			//day.setId("PULL");
			day.getChildren().add(TasksforOneHour );
			day.setMinWidth(250);
			
			ScrollPane ScroolForOneHour =new ScrollPane();
			ScroolForOneHour.setLayoutX(10);
			ScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			ScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			ScroolForOneHour.setContent(day);
			ScroolForOneHour.setMinWidth(250);
			
			_tasksAllDay.getChildren().add(ScroolForOneHour);
			
			for (int j =0; j < data.size(); j++) {
				int iterator = j+1;
				Label numbersTask = new Label(" Задание №" +iterator+" : ");
				Locale.setDefault(new Locale("ru","RU"));

				Date dateS = data.get(j).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
				
				if (i+1 == dateStartss.get(Calendar.HOUR_OF_DAY)) {
				System.out.println("Начался вывод задач ");
				EntityTask thisTask = data.get(j);
				//String name = Integer.toString(i);
				VBox body_ = new VBox(50);
				Label body_Label = new Label();
				
				Label a_Label = new Label("Описание: ");
				body_Label.setText(data.get(j).getBode());
				body_.getChildren().addAll(a_Label, body_Label);
				body_.setAlignment(Pos.CENTER);
				body_.setSpacing(5);
				body_.getStyleClass().add("OneStringBaze");
				
				VBox title_ = new VBox(50);
				Label t_Label = new Label("Заголовок: ");
				Label title_Label = new Label();
				title_Label.setText(data.get(j).getTitle());
				title_.getChildren().addAll(t_Label,title_Label);
				title_.setAlignment(Pos.CENTER);
				title_.setSpacing(5);
				title_.getStyleClass().add("OneStringBaze");
				
				HBox mean = new HBox(50);
				mean.getChildren().addAll(title_,body_);
				mean.setAlignment(Pos.CENTER);
				mean.setSpacing(5);
				mean.getStyleClass().add("OneString");
				
				// Время
				Date dateE = data.get(j).getDateEnd();
				GregorianCalendar dateEnds = new GregorianCalendar();
				dateEnds.setTime(dateE);
				Months = dateEnds.get(Calendar.MONTH) + 1;
				String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
						dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
				
				
				VBox dateST = new VBox(50);
				Label dateST_Label = new Label();
				Label datest_Label = new Label("Дата и время начала выполнения: ");
				datest_Label.setWrapText(true);
				//dateST_Label.setText(data.get(i).getDateStrart().toString());
				dateST_Label.setText(DateSAllTs);
				dateST.getChildren().addAll(datest_Label, dateST_Label);
				dateST.setAlignment(Pos.CENTER);
				dateST.setSpacing(5);
				dateST.getStyleClass().add("OneStringBaze");
				
				VBox dateEn = new VBox(50);
				Label dateEn_Label = new Label();
				Label dateen_Label = new Label("Дата и время окончания выполнения: ");
				dateen_Label.setWrapText(true);
				//dateEn_Label.setText(data.get(i).getDateEnd().toString());
				dateEn_Label.setText(DateEAllT);
				dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
				dateEn.setAlignment(Pos.CENTER);
				dateEn.setSpacing(5);
				dateEn.getStyleClass().add("OneStringBaze");
				
				VBox _urgency = new VBox(50);
				Label _urgencyLabel = new Label("Срочность задачи: ");
				Label _urgency_Label = new Label();
				_urgency_Label.setText(data.get(j).getUrgency());
				_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
				_urgency.setAlignment(Pos.CENTER);
				_urgency.setSpacing(5);
				_urgency.getStyleClass().add("OneStringBaze");
				_urgency.setId("urgency");
				_urgency_Label.setTextFill(Color.web("#E0FFFF"));
				_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
				
				HBox dates = new HBox(50);
				dates.getChildren().addAll( _urgency,dateST,dateEn);
				dates.setAlignment(Pos.CENTER);
				dates.setSpacing(5);
				dates.getStyleClass().add("OneString");
				
				 Button inf = new Button("Узнать подробнее");
				 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				 inf.setId("button");
				 inf.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							
							SceneOneTask t = new SceneOneTask(primaryStage,id,thisTask);
						}
					});
				 
				VBox name = new VBox(50);
				name.getChildren().addAll(numbersTask, mean,dates,inf);
				name.setId("OneTask");
				name.setSpacing(10);
				name.setAlignment(Pos.CENTER);
				if(data.get(j).getIsDone() > 0) {
					DoneallTasksforOneHour.getChildren().add(name);
				} else {
					if(data.get(j).getIdUrgency() == 3) {
						allTasksforOneHour.getChildren().add(1,name);
					}else {
						allTasksforOneHour.getChildren().add(name);
						//allTasksforOneHour.getChildren().add
					}
				}
				}
			}
			if (allTasksforOneHour.getChildren().size() == 1) {
				TasksforOneHour.getChildren().addAll(_01Label1);
			} else {
				TasksforOneHour.getChildren().addAll(_01Label1,allTasksforOneHour);
			}
			
			if (DoneallTasksforOneHour.getChildren().size() > 1) {
				day.getChildren().add(DoneallTasksforOneHour);
			}
		}
		all.setContent(_tasksAllDay);
		
		*/
			VBox month = new VBox(50);
		
			Label month_Label = new Label("Распиание на месяц: ");
			month_Label.setWrapText(true);
			//dateEn_Label.setText(data.get(i).getDateEnd().toString());
			
			Date dt = new Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");
			String currentTime = sdf.format(dt);
			GregorianCalendar c = new GregorianCalendar();
			c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		
			month.getChildren().addAll(month_Label);
			month.setAlignment(Pos.CENTER);
			month.setSpacing(5);
			month.getStyleClass().add("OneStringBaze");
			
			GregorianCalendar cs = new GregorianCalendar();
			cs.set(Calendar.DAY_OF_MONTH, 1);
			
			GregorianCalendar ce = new GregorianCalendar();
			
			
			Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
			myCalendar.setTime(dt);// set(your_year, your_month, 1);
			int max_date = myCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
			
		
			
			String n = "" + max_date;
			
			month.getChildren().add(new Label(n));
			
			
		// tabMonth.setContent(month);
		 tabSh.setContent(_44);
		// Конец текущей задачи -- /
	    tabpane.getTabs().addAll(tabSh,tabWork,tabTask/*,tabMonth*/);
	    
	    Label alarm = new Label(" Приветствую  ");
	    HBox alarmBox= new HBox();
	    alarmBox.setId("BoxForAlarm");
	    alarmBox.getChildren().setAll(alarm,uiTimer);
	    
	    
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
	   // border.setRight(bottom );
		
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
	
	public void startNewScene() {
		//this.
	}

	public VBox getTaskOnThisDay(VBox _tasksAllDay){/*
		LinkedList <EntityTask> data = new LinkedList <EntityTask>();
		LinkedList <EntityTask> dataEnd = new LinkedList <EntityTask>();
		Date thisDate = new Date();
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String currentTime = sdf.format(dt);
		
		GregorianCalendar dateStarts = new GregorianCalendar();
		dateStarts.setTime(thisDate);
		int Month = dateStarts.get(Calendar.MONTH) + 1;
		String DateSAllT = dateStarts.get(Calendar.YEAR) + "-_"+ Month +"-_" + dateStarts.get(Calendar.DAY_OF_MONTH);
		String queryForThisDay ="SELECT * FROM `taskmail`.`task` WHERE `task`.`start_date_time` LIKE '" + DateSAllT + "%'; ";
		System.out.println("Дата для выбора - " + queryForThisDay);
		ReadOunTasks thisDayTasks = new ReadOunTasks();
		thisDayTasks.setSearchQuery(queryForThisDay);
		thisDayTasks.whatIs();
		data = thisDayTasks.getData();
		
		
		String queryForThisDayAnd ="SELECT * FROM `taskmail`.`task` WHERE `task`.`end_date_time` LIKE '" + DateSAllT +
				"%' AND `start_date_time` NOT LIKE '" + DateSAllT + "'; ";
		System.out.println("Дата для выбора - " + queryForThisDayAnd);
		thisDayTasks.setSearchQuery(queryForThisDayAnd);
		thisDayTasks.whatIs();
		dataEnd =  thisDayTasks.getData();
		
		
		System.out.println("количество задач на сегодня - " + data.size());
		
		for(int i =0; i <24; i++) {
			int numberTask = i+1;
			HBox allTasksforOneHour = new HBox(50);
			allTasksforOneHour.setId("C");
			allTasksforOneHour.setSpacing(10);
			allTasksforOneHour.setAlignment(Pos.CENTER);
			Label _01_TasksLabels = new Label(" Задачи на этот час: " + numberTask+":00");
			_01_TasksLabels.setId("PULL");
			allTasksforOneHour.getChildren().add(_01_TasksLabels);
			allTasksforOneHour.setMinWidth(250);
			
			if (allTasksforOneHour.getChildren().size() == 1) {
				
			}
			
			ScrollPane allScroolForOneHour =new ScrollPane();
			allScroolForOneHour.setLayoutX(10);
			allScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			allScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			allScroolForOneHour.setContent(allTasksforOneHour);
			allScroolForOneHour.setMinWidth(250);
			
			HBox DoneallTasksforOneHour = new HBox(50);
			DoneallTasksforOneHour.setId("C");
			DoneallTasksforOneHour.setSpacing(10);
			DoneallTasksforOneHour.setAlignment(Pos.CENTER);
			Label DoneTasks = new Label(" Выполненные задачи: " + numberTask+":00");
			DoneTasks.setId("PULL");
			DoneallTasksforOneHour.getChildren().add(DoneTasks);
			DoneallTasksforOneHour.setMinWidth(250);

			
			
			HBox Done = new HBox(50);
			Done.setId("C");
			Done.setSpacing(10);
			Done.setAlignment(Pos.CENTER);
			Label DoneT = new Label(" Выполненные задачи: ");
			DoneT.setId("PULL");
			Done.getChildren().add(DoneT);
			Done.setMinWidth(250);
			
			//Окончание работ сегодня
			HBox TaskEndforOneHour = new HBox(50);
			TaskEndforOneHour.setId("C");
			TaskEndforOneHour.setSpacing(10);
			TaskEndforOneHour.setAlignment(Pos.CENTER);
			Label _endLabel1 = new Label(" Работы надо законачить к этому времени: ");
			_endLabel1.setId("PULL");
			_endLabel1.setWrapText(true);
			TaskEndforOneHour.setMinWidth(250);
			//
			ScrollPane alland =new ScrollPane();
			alland.setLayoutX(10);
			alland.setLayoutY(10);
			//spComment.setHmin(400);
			alland.setCursor(Cursor.CLOSED_HAND);
			alland.setContent(TaskEndforOneHour);
			alland.setMinWidth(250);
			//
			
			
			VBox TasksforOneHour = new VBox(50);
			TasksforOneHour.setId("C");
			TasksforOneHour.setSpacing(10);
			TasksforOneHour.setAlignment(Pos.CENTER);
			Label _01Label1 = new Label(" Час " + numberTask +": ");
			_01Label1.setId("PULL");
			TasksforOneHour.setMinWidth(250);
			//givenComments.getStyleClass().add("Data");
			
			HBox day = new HBox(50);
			day.setId("S");
			day.setSpacing(10);
			day.setAlignment(Pos.CENTER);
			//day.setId("PULL");
			day.getChildren().addAll(TasksforOneHour);
			day.setMinWidth(250);
			
			ScrollPane ScroolForOneHour =new ScrollPane();
			ScroolForOneHour.setLayoutX(10);
			ScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			ScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			ScroolForOneHour.setContent(day);
			ScroolForOneHour.setMinWidth(250);
			
			_tasksAllDay.getChildren().add(ScroolForOneHour);
			
			for (int j =0; j < data.size(); j++) {
				System.out.println(" Всё что есть в дата Время - " + i + " Задача - " + data.get(j).getTitle() + " Время начала" + data.get(j).getDateStrart());
				int iterator = j+1;
				Label numbersTask = new Label(" Задание №" +iterator+" : ");
				Locale.setDefault(new Locale("ru","RU"));
				/*GregorianCalendar dateCreate = new GregorianCalendar();
				dateCreate.setTime(res.getDate(5));*/
			/*	Date dateEs = data.get(j).getDateEnd();
				Date dateS = data.get(j).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				
				GregorianCalendar dateEndss = new GregorianCalendar();
				dateEndss.setTime(dateEs);
				
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
				
				
				if ((i+1 == dateStartss.get(Calendar.HOUR_OF_DAY)) )/* && ( dateStarts.get(Calendar.YEAR) == dateStartss.get(Calendar.YEAR)) 
						&& (dateStarts.get(Calendar.MONTH) == dateStartss.get(Calendar.MONTH)) 
						&& (dateStarts.get(Calendar.DAY_OF_MONTH) == dateStartss.get(Calendar.DAY_OF_MONTH)) )*/ {
					
	/*			System.out.println("Начался вывод задач ");
				System.out.println("Время - " + i + " Задача - " + data.get(j).getTitle() + " Время начала" + data.get(j).getDateStrart());
				EntityTask thisTask = data.get(j);
				//String name = Integer.toString(i);
				VBox body_ = new VBox(50);
				Label body_Label = new Label();
				
				Label a_Label = new Label("Описание: ");
				body_Label.setText(data.get(j).getBode());
				body_.getChildren().addAll(a_Label, body_Label);
				body_.setAlignment(Pos.CENTER);
				body_.setSpacing(5);
				body_.getStyleClass().add("OneStringBaze");
				
				VBox title_ = new VBox(50);
				Label t_Label = new Label("Заголовок: ");
				Label title_Label = new Label();
				title_Label.setText(data.get(j).getTitle());
				title_.getChildren().addAll(t_Label,title_Label);
				title_.setAlignment(Pos.CENTER);
				title_.setSpacing(5);
				title_.getStyleClass().add("OneStringBaze");
				
				HBox mean = new HBox(50);
				mean.getChildren().addAll(title_,body_);
				mean.setAlignment(Pos.CENTER);
				mean.setSpacing(5);
				mean.getStyleClass().add("OneString");
				
				// Время
				Date dateE = data.get(j).getDateEnd();
				GregorianCalendar dateEnds = new GregorianCalendar();
				dateEnds.setTime(dateE);
				Months = dateEnds.get(Calendar.MONTH) + 1;
				String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
						dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
				
				
				VBox dateST = new VBox(50);
				Label dateST_Label = new Label();
				Label datest_Label = new Label("Дата и время начала выполнения: ");
				datest_Label.setWrapText(true);
				//dateST_Label.setText(data.get(i).getDateStrart().toString());
				dateST_Label.setText(DateSAllTs);
				dateST.getChildren().addAll(datest_Label, dateST_Label);
				dateST.setAlignment(Pos.CENTER);
				dateST.setSpacing(5);
				dateST.getStyleClass().add("OneStringBaze");
				
				VBox dateEn = new VBox(50);
				Label dateEn_Label = new Label();
				Label dateen_Label = new Label("Дата и время окончания выполнения: ");
				dateen_Label.setWrapText(true);
				//dateEn_Label.setText(data.get(i).getDateEnd().toString());
				dateEn_Label.setText(DateEAllT);
				dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
				dateEn.setAlignment(Pos.CENTER);
				dateEn.setSpacing(5);
				dateEn.getStyleClass().add("OneStringBaze");
				
				VBox _urgency = new VBox(50);
				Label _urgencyLabel = new Label("Срочность задачи: ");
				Label _urgency_Label = new Label();
				_urgency_Label.setText(data.get(j).getUrgency());
				_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
				_urgency.setAlignment(Pos.CENTER);
				_urgency.setSpacing(5);
				_urgency.getStyleClass().add("OneStringBaze");
				_urgency.setId("urgency");
				_urgency_Label.setTextFill(Color.web("#E0FFFF"));
				_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
				
				HBox dates = new HBox(50);
				dates.getChildren().addAll( _urgency,dateST,dateEn);
				dates.setAlignment(Pos.CENTER);
				dates.setSpacing(5);
				dates.getStyleClass().add("OneString");
				
				 Button inf = new Button("Узнать подробнее");
				 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				 inf.setId("button");
				 inf.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							
							SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
						}
					});
				 
				VBox name = new VBox(50);
				name.getChildren().addAll(numbersTask, mean,dates,inf);
				name.setId("OneTask");
				name.setSpacing(10);
				name.setAlignment(Pos.CENTER);
				if(data.get(j).getIsDone() > 0) {
					DoneallTasksforOneHour.getChildren().add(name);
				} else {
					if(data.get(j).getIdUrgency() == 3) {
						allTasksforOneHour.getChildren().add(1,name);
					}else {
						allTasksforOneHour.getChildren().add(name);
						//allTasksforOneHour.getChildren().add
					}
				}
				} 
			}
			// - не работает
			for (int f = 0; f < dataEnd.size(); f++ ) {
				int iterator = f+1;
				Label numbersTask = new Label(" Задание №" +iterator+" : ");
				System.out.println("Время - " + i + " Задача - " + dataEnd.get(f).getTitle() + " Время начала" + dataEnd.get(f).getDateStrart());
				Locale.setDefault(new Locale("ru","RU"));
				/*GregorianCalendar dateCreate = new GregorianCalendar();
				dateCreate.setTime(res.getDate(5));*/
	/*			Date dateEs = dataEnd.get(f).getDateEnd();
				Date dateS = dataEnd.get(f).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				
				GregorianCalendar dateEndss = new GregorianCalendar();
				dateEndss.setTime(dateEs);
				
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
			
				
				if (i+1 == dateEndss.get(Calendar.HOUR_OF_DAY)  && ( dateStarts.get(Calendar.YEAR) == dateEndss.get(Calendar.YEAR)) 
						&& (dateStarts.get(Calendar.MONTH) == dateEndss.get(Calendar.MONTH)) 
						&& (dateStarts.get(Calendar.DAY_OF_MONTH) == dateEndss.get(Calendar.DAY_OF_MONTH)) ) {
						// Вывод задач которые будут заканчиваться сегодня
						System.out.println("Вывод задач которые будут заканчиваться сегодня " + dataEnd.size());
						
						EntityTask thisTask = dataEnd.get(f);
						//String name = Integer.toString(i);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("Описание: ");
						body_Label.setText(data.get(f).getBode());
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("Заголовок: ");
						Label title_Label = new Label();
						title_Label.setText(dataEnd.get(f).getTitle());
						title_.getChildren().addAll(t_Label,title_Label);
						title_.setAlignment(Pos.CENTER);
						title_.setSpacing(5);
						title_.getStyleClass().add("OneStringBaze");
						
						HBox mean = new HBox(50);
						mean.getChildren().addAll(title_,body_);
						mean.setAlignment(Pos.CENTER);
						mean.setSpacing(5);
						mean.getStyleClass().add("OneString");
						System.out.println("111");
						// Время
						Date dateE = dataEnd.get(f).getDateEnd();
						GregorianCalendar dateEnds = new GregorianCalendar();
						dateEnds.setTime(dateE);
						Months = dateEnds.get(Calendar.MONTH) + 1;
						String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
								dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
						
						
						VBox dateST = new VBox(50);
						Label dateST_Label = new Label();
						Label datest_Label = new Label("Дата и время начала выполнения: ");
						datest_Label.setWrapText(true);
						//dateST_Label.setText(data.get(i).getDateStrart().toString());
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("Дата и время окончания выполнения: ");
						dateen_Label.setWrapText(true);
						//dateEn_Label.setText(data.get(i).getDateEnd().toString());
						dateEn_Label.setText(DateEAllT);
						dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
						dateEn.setAlignment(Pos.CENTER);
						dateEn.setSpacing(5);
						dateEn.getStyleClass().add("OneStringBaze");
						System.out.println("222");
						VBox _urgency = new VBox(50);
						Label _urgencyLabel = new Label("Срочность задачи: ");
						Label _urgency_Label = new Label();
						_urgency_Label.setText(dataEnd.get(f).getUrgency());
						_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
						_urgency.setAlignment(Pos.CENTER);
						_urgency.setSpacing(5);
						_urgency.getStyleClass().add("OneStringBaze");
						_urgency.setId("urgency");
						_urgency_Label.setTextFill(Color.web("#E0FFFF"));
						_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
						
						
						HBox dates = new HBox(50);
						dates.getChildren().addAll( _urgency,dateST,dateEn);
						dates.setAlignment(Pos.CENTER);
						dates.setSpacing(5);
						dates.getStyleClass().add("OneString");
						System.out.println("333");
						 Button inf = new Button("Узнать подробнее");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 //inf.setMaxWidth(160);
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
						 
						VBox name = new VBox(50);
						name.getChildren().addAll(numbersTask, mean,dates,inf);
						name.setId("OneTask");
						name.setSpacing(10);
						name.setAlignment(Pos.CENTER);
						System.out.println("444");
						if(dataEnd.get(f).getIsDone() > 0) {
							Done.getChildren().add(name);
							System.out.println("555");
						} else {
							if(dataEnd.get(f).getIdUrgency() == 3) {
								TaskEndforOneHour.getChildren().add(1,name);
								System.out.println("777");
							}else {
								TaskEndforOneHour.getChildren().add(name);
								//allTasksforOneHour.getChildren().add
								System.out.println("888");
							}
						}
					}
			}

			//
			
			System.out.println("999");
			if (allTasksforOneHour.getChildren().size() == 1) {
				TasksforOneHour.getChildren().addAll(_01Label1);
			} else {
				TasksforOneHour.getChildren().addAll(_01Label1,allTasksforOneHour);
			}
			
			if (DoneallTasksforOneHour.getChildren().size() > 1) {
				day.getChildren().add(DoneallTasksforOneHour);
			}
			
			System.out.println("101010");
			if (Done.getChildren().size() > 1) {
				TaskEndforOneHour.getChildren().add(Done);
			}
			if (TaskEndforOneHour.getChildren().size() > 1) {
				day.getChildren().addAll(new Label("Задачи, которые нужнов выполнить к этому часу."),alland);
			}
			System.out.println("11 11 11");

		}
		
*/
		return _tasksAllDay;
	}
	}
						
	public VBox getTasksOnThisDays (VBox _tasksAllDay) {
		LinkedList <EntityTask> data = new LinkedList <EntityTask>();
		LinkedList <EntityTask> dataEnd = new LinkedList <EntityTask>();
		Date thisDate = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(thisDate);
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String currentTime = sdf.format(dt);
		java.sql.Date sqlDate = new java.sql.Date(thisDate.getTime());
		GregorianCalendar dateStarts = new GregorianCalendar();
		dateStarts.setTime(thisDate);
		int Month = dateStarts.get(Calendar.MONTH) + 1;
		String DateSAllT = dateStarts.get(Calendar.YEAR) + "-_"+ Month +"-" + dateStarts.get(Calendar.DAY_OF_MONTH);
		//String queryForThisDay ="SELECT * FROM `taskmail`.`task` WHERE `task`.`start_date_time` LIKE '" + DateSAllT + "%' AND ='" + this.id + "'; ";
		String queryForThisDay ="SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
				+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
				+ "`user_task`.`id_user` = `users`.`id_user` "
				+ "WHERE `task`.`start_date_time` LIKE '" + sqlDate + "%' AND `users`.`id_user` ='" + this.id + "'; ";
		System.out.println("Дата для выбора - " + queryForThisDay);
		ReadOunTasks thisDayTasks = new ReadOunTasks();
		thisDayTasks.setSearchQuery(queryForThisDay);
		thisDayTasks.whatIs();
		data = thisDayTasks.getData();
		System.out.println("количество задач на сегодня - " + data.size());
		
		
		//String queryForThisDayAnd ="SELECT * FROM `taskmail`.`task` WHERE `task`.`end_date_time` LIKE '" + DateSAllT +
		//		"%' AND `start_date_time` NOT LIKE '" + DateSAllT + "'; ";
		
		String queryForThisDayAnd = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
				+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
				+ "`user_task`.`id_user` = `users`.`id_user` "
				+ "WHERE `task`.`end_date_time` LIKE '" + sqlDate + 
				"%' AND `start_date_time` NOT LIKE '" + sqlDate + "' AND `users`.`id_user` ='" + this.id + "'; ";
		System.out.println("Дата для выбора - " + queryForThisDayAnd);
	/*	thisDayTasks.setSearchQuery(queryForThisDayAnd);
		thisDayTasks.whatIs();
		dataEnd.addAll(thisDayTasks.what());*/
		
		ReadOneTask2 thisDayTasksAnd = new ReadOneTask2();
		thisDayTasksAnd.setSearchQuery(queryForThisDayAnd);
		thisDayTasksAnd.whatIs();
		dataEnd = thisDayTasksAnd.getData();
		
		
		
		System.out.println("sql data - " + sqlDate);
		
		String queryForBetweenDate = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
				+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
				+ "`user_task`.`id_user` = `users`.`id_user` "
				+ "WHERE `task`.`end_date_time` >= '" + sqlDate + 
				"' AND `start_date_time`  <= '" + sqlDate + "' AND `users`.`id_user` ='" + this.id + "' AND `task`.`is_done` = '0'; ";
	/*	String queryForBetweenDate = "Select * FROM taskmail`.`task` WHERE '" + sqlDate + "' BETWEEN `task`.`start_date_time` "
				+ "AND `task`.`end_date_time`;";
		*/
		/*String queryForBetweenDate = "Select * FROM taskmail`.`task` WHERE CURDATE() BETWEEN `task`.`start_date_time` "
				+ "AND `task`.`end_date_time`;";*/
		System.out.println("Дата для выбора задач между текущей датой - " + queryForBetweenDate);
		ReadOunTasks3 thru = new ReadOunTasks3();
		thru.setSearchQuery(queryForBetweenDate);
		thru.whatIs();
		LinkedList <EntityTask> dataThru = new LinkedList <EntityTask>();
		dataThru = thru.getData();
		System.out.println("Количество задач прохожящих через этот день - " + dataThru.size());
		
		
		System.out.println("Проверка содержания списков - ");
		for (int i =0; i < data.size(); i ++ ) {
			System.out.println("i - начало - " + i + " время - " + data.get(i).getDateStrart() + " титл " + data.get(i).getTitle());
		}
		
		for (int i =0; i < dataEnd.size(); i ++ ) {
			System.out.println("i - " + i + " время - " + dataEnd.get(i).getDateStrart() + " титл " + dataEnd.get(i).getTitle());
		}
		System.out.println(" //////////////////////////// - ");
		HBox TaskBetween = new HBox(50);
		TaskBetween.setId("C");
		TaskBetween.setSpacing(10);
		TaskBetween.setAlignment(Pos.CENTER);
		Label BetweenLabel = new Label(" Работы которые нужно\n выполнять в течении дня: ");
		BetweenLabel.setId("PULL");
		BetweenLabel.setWrapText(true);
		TaskBetween.getChildren().add(BetweenLabel);
		TaskBetween.setMinWidth(250);
		//
		ScrollPane betweenSc =new ScrollPane();
		betweenSc.setLayoutX(10);
		betweenSc.setLayoutY(10);
		//spComment.setHmin(400);
		betweenSc.setCursor(Cursor.CLOSED_HAND);
		betweenSc.setContent(TaskBetween);
		betweenSc.setMinWidth(250);
		
		// Конец задач проходящих через этот день -- /
		for(int i =0; i <24; i++) {
			int numberTask = i+1;
			HBox allTasksforOneHour = new HBox(50);
			allTasksforOneHour.setId("C");
			allTasksforOneHour.setSpacing(10);
			allTasksforOneHour.setAlignment(Pos.CENTER);
			Label _01_TasksLabels = new Label(" Задачи на этот час: " + numberTask+":00");
			_01_TasksLabels.setId("PULL");
			allTasksforOneHour.getChildren().add(_01_TasksLabels);
			allTasksforOneHour.setMinWidth(250);
			
			if (allTasksforOneHour.getChildren().size() == 1) {
				
			}
			
			ScrollPane allScroolForOneHour =new ScrollPane();
			allScroolForOneHour.setLayoutX(10);
			allScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			allScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			allScroolForOneHour.setContent(allTasksforOneHour);
			allScroolForOneHour.setMinWidth(250);
			
			HBox DoneallTasksforOneHour = new HBox(50);
			DoneallTasksforOneHour.setId("C");
			DoneallTasksforOneHour.setSpacing(10);
			DoneallTasksforOneHour.setAlignment(Pos.CENTER);
			Label DoneTasks = new Label(" Выполненные задачи: " + numberTask+":00");
			DoneTasks.setId("PULL");
			DoneallTasksforOneHour.getChildren().add(DoneTasks);
			DoneallTasksforOneHour.setMinWidth(250);
			
			VBox TasksforOneHour = new VBox(50);
			TasksforOneHour.setId("C");
			TasksforOneHour.setSpacing(10);
			TasksforOneHour.setAlignment(Pos.CENTER);
			Label _01Label1 = new Label(" Час " + numberTask +": ");
			_01Label1.setId("PULL");
			TasksforOneHour.setMinWidth(250);
			//givenComments.getStyleClass().add("Data");
			
			HBox Done = new HBox(50);
			Done.setId("C");
			Done.setSpacing(10);
			Done.setAlignment(Pos.CENTER);
			Label DoneT = new Label(" Выполненные задачи: ");
			DoneT.setId("PULL");
			Done.getChildren().add(DoneT);
			Done.setMinWidth(250);
			
			//Окончание работ сегодня
			HBox TaskEndforOneHour = new HBox(50);
			TaskEndforOneHour.setId("C");
			TaskEndforOneHour.setSpacing(10);
			TaskEndforOneHour.setAlignment(Pos.CENTER);
			Label _endLabel1 = new Label(" Работы надо законачить\n к этому времени: ");
			_endLabel1.setId("PULL");
			_endLabel1.setWrapText(true);
			TaskEndforOneHour.setMinWidth(250);
			//
			ScrollPane alland =new ScrollPane();
			alland.setLayoutX(10);
			alland.setLayoutY(10);
			//spComment.setHmin(400);
			alland.setCursor(Cursor.CLOSED_HAND);
			alland.setContent(TaskEndforOneHour);
			alland.setMinWidth(250);
			// - Задачи проходящие через этот день -- /
			//Окончание работ сегодня
			
			
			
			HBox day = new HBox(50);
			day.setId("S");
			day.setSpacing(10);
			day.setAlignment(Pos.CENTER);
			//day.setId("PULL");
			day.getChildren().add(TasksforOneHour);
			day.setMinWidth(250);
			
			ScrollPane ScroolForOneHour =new ScrollPane();
			ScroolForOneHour.setLayoutX(10);
			ScroolForOneHour.setLayoutY(10);
			//spComment.setHmin(400);
			ScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			ScroolForOneHour.setContent(day);
			ScroolForOneHour.setMinWidth(250);
			
			_tasksAllDay.getChildren().addAll(ScroolForOneHour);
			
			for (int j =0; j < data.size(); j++) {
				int iterator = j+1;
				Label numbersTask = new Label(" Задание №" +iterator+" : ");
				Locale.setDefault(new Locale("ru","RU"));

				Date dateS = data.get(j).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
				
				if ((i+1 == dateStartss.get(Calendar.HOUR_OF_DAY)) || ( (i==0) && (0 == dateStartss.get(Calendar.HOUR_OF_DAY)))) {
				System.out.println("Начался вывод задач ");
				EntityTask thisTask = data.get(j);
				//String name = Integer.toString(i);
				VBox body_ = new VBox(50);
				Label body_Label = new Label();
				
				Label a_Label = new Label("Описание: ");
				body_Label.setText(data.get(j).getBode());
				body_.getChildren().addAll(a_Label, body_Label);
				body_.setAlignment(Pos.CENTER);
				body_.setSpacing(5);
				body_.getStyleClass().add("OneStringBaze");
				
				VBox title_ = new VBox(50);
				Label t_Label = new Label("Заголовок: ");
				Label title_Label = new Label();
				title_Label.setText(data.get(j).getTitle());
				title_.getChildren().addAll(t_Label,title_Label);
				title_.setAlignment(Pos.CENTER);
				title_.setSpacing(5);
				title_.getStyleClass().add("OneStringBaze");
				
				HBox mean = new HBox(50);
				mean.getChildren().addAll(title_,body_);
				mean.setAlignment(Pos.CENTER);
				mean.setSpacing(5);
				mean.getStyleClass().add("OneString");
				
				// Время
				Date dateE = data.get(j).getDateEnd();
				GregorianCalendar dateEnds = new GregorianCalendar();
				dateEnds.setTime(dateE);
				Months = dateEnds.get(Calendar.MONTH) + 1;
				String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
						dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
				
				
				VBox dateST = new VBox(50);
				Label dateST_Label = new Label();
				Label datest_Label = new Label("Дата и время\n начала выполнения: ");
				datest_Label.setWrapText(true);
				//dateST_Label.setText(data.get(i).getDateStrart().toString());
				dateST_Label.setText(DateSAllTs);
				dateST.getChildren().addAll(datest_Label, dateST_Label);
				dateST.setAlignment(Pos.CENTER);
				dateST.setSpacing(5);
				dateST.getStyleClass().add("OneStringBaze");
				
				VBox dateEn = new VBox(50);
				Label dateEn_Label = new Label();
				Label dateen_Label = new Label("Дата и время\n окончания выполнения: ");
				dateen_Label.setWrapText(true);
				//dateEn_Label.setText(data.get(i).getDateEnd().toString());
				dateEn_Label.setText(DateEAllT);
				dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
				dateEn.setAlignment(Pos.CENTER);
				dateEn.setSpacing(5);
				dateEn.getStyleClass().add("OneStringBaze");
				
				VBox _urgency = new VBox(50);
				Label _urgencyLabel = new Label("Срочность задачи: ");
				Label _urgency_Label = new Label();
				_urgency_Label.setText(data.get(j).getUrgency());
				_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
				_urgency.setAlignment(Pos.CENTER);
				_urgency.setSpacing(5);
				_urgency.getStyleClass().add("OneStringBaze");
				_urgency.setId("urgency");
				_urgency_Label.setTextFill(Color.web("#E0FFFF"));
				_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
				
				HBox dates = new HBox(50);
				dates.getChildren().addAll( _urgency,dateST,dateEn);
				dates.setAlignment(Pos.CENTER);
				dates.setSpacing(5);
				dates.getStyleClass().add("OneString");
				
				 Button inf = new Button("Узнать подробнее");
				 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				 inf.setId("button");
				 inf.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							
							SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
						}
					});
				 
				VBox name = new VBox(50);
				name.getChildren().addAll(numbersTask, mean,dates,inf);
				name.setId("OneTask");
				name.setSpacing(10);
				name.setAlignment(Pos.CENTER);
				if(data.get(j).getIsDone() > 0) {
					DoneallTasksforOneHour.getChildren().add(name);
				} else {
					if(data.get(j).getIdUrgency() == 3) {
						allTasksforOneHour.getChildren().add(1,name);
					
					}else {
						allTasksforOneHour.getChildren().add(name);
					
						//allTasksforOneHour.getChildren().add
					}
				}
				}
			}
			for (int f = 0; f < dataEnd.size(); f++ ) {
				int iterator = f+1;
				Label numbersTask = new Label(" Задание №" +iterator+" : ");
				System.out.println("Время - " + i + " Задача - " + dataEnd.get(f).getTitle() + " Время начала" + dataEnd.get(f).getDateStrart());
				Locale.setDefault(new Locale("ru","RU"));
				/*GregorianCalendar dateCreate = new GregorianCalendar();
				dateCreate.setTime(res.getDate(5));*/
				Date dateEs = dataEnd.get(f).getDateEnd();
				Date dateS = dataEnd.get(f).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				
				GregorianCalendar dateEndss = new GregorianCalendar();
				dateEndss.setTime(dateEs);
				
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
			
				
				if (i+1 == dateEndss.get(Calendar.HOUR_OF_DAY) ) {
						// Вывод задач которые будут заканчиваться сегодня
						System.out.println("Вывод задач которые будут заканчиваться сегодня " + dataEnd.size());
						
						EntityTask thisTask = dataEnd.get(f);
						//String name = Integer.toString(i);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("Описание: ");
						body_Label.setText(dataEnd.get(f).getBode());
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("Заголовок: ");
						Label title_Label = new Label();
						title_Label.setText(dataEnd.get(f).getTitle());
						title_.getChildren().addAll(t_Label,title_Label);
						title_.setAlignment(Pos.CENTER);
						title_.setSpacing(5);
						title_.getStyleClass().add("OneStringBaze");
						
						HBox mean = new HBox(50);
						mean.getChildren().addAll(title_,body_);
						mean.setAlignment(Pos.CENTER);
						mean.setSpacing(5);
						mean.getStyleClass().add("OneString");
						System.out.println("111");
						// Время
						Date dateE = dataEnd.get(f).getDateEnd();
						GregorianCalendar dateEnds = new GregorianCalendar();
						dateEnds.setTime(dateE);
						Months = dateEnds.get(Calendar.MONTH) + 1;
						String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
								dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
						
						
						VBox dateST = new VBox(50);
						Label dateST_Label = new Label();
						Label datest_Label = new Label("Дата и время\n начала выполнения: ");
						datest_Label.setWrapText(true);
						//dateST_Label.setText(data.get(i).getDateStrart().toString());
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("Дата и время\n окончания выполнения: ");
						dateen_Label.setWrapText(true);
						//dateEn_Label.setText(data.get(i).getDateEnd().toString());
						dateEn_Label.setText(DateEAllT);
						dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
						dateEn.setAlignment(Pos.CENTER);
						dateEn.setSpacing(5);
						dateEn.getStyleClass().add("OneStringBaze");
						System.out.println("222");
						VBox _urgency = new VBox(50);
						Label _urgencyLabel = new Label("Срочность задачи: ");
						Label _urgency_Label = new Label();
						_urgency_Label.setText(dataEnd.get(f).getUrgency());
						_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
						_urgency.setAlignment(Pos.CENTER);
						_urgency.setSpacing(5);
						_urgency.getStyleClass().add("OneStringBaze");
						_urgency.setId("urgency");
						_urgency_Label.setTextFill(Color.web("#E0FFFF"));
						_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
						
						
						HBox dates = new HBox(50);
						dates.getChildren().addAll( _urgency,dateST,dateEn);
						dates.setAlignment(Pos.CENTER);
						dates.setSpacing(5);
						dates.getStyleClass().add("OneString");
						System.out.println("333");
						 Button inf = new Button("Узнать подробнее");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 //inf.setMaxWidth(160);
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
						 
						VBox name = new VBox(50);
						name.getChildren().addAll(numbersTask, mean,dates,inf);
						name.setId("OneTask");
						name.setSpacing(10);
						name.setAlignment(Pos.CENTER);
						System.out.println("444");
						if(dataEnd.get(f).getIsDone() > 0) {
							Done.getChildren().add(name);
							System.out.println("555");
						} else {
							if(dataEnd.get(f).getIdUrgency() == 3) {
								TaskEndforOneHour.getChildren().add(1,name);
								System.out.println("777");
							}else {
								TaskEndforOneHour.getChildren().add(name);
								//allTasksforOneHour.getChildren().add
								System.out.println("888");
							}
						}
					}
			}

			if (allTasksforOneHour.getChildren().size() == 1) {
				TasksforOneHour.getChildren().addAll(_01Label1);
			} else {
				TasksforOneHour.getChildren().addAll(_01Label1,allTasksforOneHour);
			}
			
			if (DoneallTasksforOneHour.getChildren().size() > 1) {
				day.getChildren().add(DoneallTasksforOneHour);
			}
			
			if (Done.getChildren().size() > 1) {
				TaskEndforOneHour.getChildren().add(Done);
			}
			if (TaskEndforOneHour.getChildren().size() > 1) {
				day.getChildren().addAll(new Label("Задачи, которые нужнов\n выполнить к этому часу."),alland);
			}
			
		}
		// Задачи проходящие через этот день -- /
		for (int h =0; h < dataThru.size(); h++) {
			int iterator = h+1;
			Label numbersTask = new Label(" Задание №" +iterator+" : ");
			Locale.setDefault(new Locale("ru","RU"));

			Date dateS = dataThru.get(h).getDateStrart();
			GregorianCalendar dateStartss = new GregorianCalendar();
			dateStartss.setTime(dateS);
			int Months = dateStartss.get(Calendar.MONTH) + 1;
			String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
					dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
			
			
			System.out.println("Вывод задач проходящие через этот день: ");
			EntityTask thisTask = dataThru.get(h);
			//String name = Integer.toString(i);
			VBox body_ = new VBox(50);
			Label body_Label = new Label();
			
			Label a_Label = new Label("Описание: ");
			body_Label.setText(dataThru.get(h).getBode());
			body_.getChildren().addAll(a_Label, body_Label);
			body_.setAlignment(Pos.CENTER);
			body_.setSpacing(5);
			body_.getStyleClass().add("OneStringBaze");
			
			VBox title_ = new VBox(50);
			Label t_Label = new Label("Заголовок: ");
			Label title_Label = new Label();
			title_Label.setText(dataThru.get(h).getTitle());
			title_.getChildren().addAll(t_Label,title_Label);
			title_.setAlignment(Pos.CENTER);
			title_.setSpacing(5);
			title_.getStyleClass().add("OneStringBaze");
			
			HBox mean = new HBox(50);
			mean.getChildren().addAll(title_,body_);
			mean.setAlignment(Pos.CENTER);
			mean.setSpacing(5);
			mean.getStyleClass().add("OneString");
			
			// Время
			Date dateE = dataThru.get(h).getDateEnd();
			GregorianCalendar dateEnds = new GregorianCalendar();
			dateEnds.setTime(dateE);
			Months = dateEnds.get(Calendar.MONTH) + 1;
			String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
					dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
			
			
			VBox dateST = new VBox(50);
			Label dateST_Label = new Label();
			Label datest_Label = new Label("Дата и время\n начала выполнения: ");
			datest_Label.setWrapText(true);
			//dateST_Label.setText(data.get(i).getDateStrart().toString());
			dateST_Label.setText(DateSAllTs);
			dateST.getChildren().addAll(datest_Label, dateST_Label);
			dateST.setAlignment(Pos.CENTER);
			dateST.setSpacing(5);
			dateST.getStyleClass().add("OneStringBaze");
			
			VBox dateEn = new VBox(50);
			Label dateEn_Label = new Label();
			Label dateen_Label = new Label("Дата и время\n окончания выполнения: ");
			dateen_Label.setWrapText(true);
			//dateEn_Label.setText(data.get(i).getDateEnd().toString());
			dateEn_Label.setText(DateEAllT);
			dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
			dateEn.setAlignment(Pos.CENTER);
			dateEn.setSpacing(5);
			dateEn.getStyleClass().add("OneStringBaze");
			
			VBox _urgency = new VBox(50);
			Label _urgencyLabel = new Label("Срочность задачи: ");
			Label _urgency_Label = new Label();
			_urgency_Label.setText(dataThru.get(h).getUrgency());
			_urgency.getChildren().addAll(_urgencyLabel,_urgency_Label);
			_urgency.setAlignment(Pos.CENTER);
			_urgency.setSpacing(5);
			_urgency.getStyleClass().add("OneStringBaze");
			_urgency.setId("urgency");
			_urgency_Label.setTextFill(Color.web("#E0FFFF"));
			_urgencyLabel.setTextFill(Color.web("#E0FFFF"));
			
			HBox dates = new HBox(50);
			dates.getChildren().addAll( _urgency,dateST,dateEn);
			dates.setAlignment(Pos.CENTER);
			dates.setSpacing(5);
			dates.getStyleClass().add("OneString");
			
			 Button inf = new Button("Узнать подробнее");
			 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			 //inf.setMaxWidth(160);
			 inf.setId("button");
			 inf.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						
						SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
					}
				});
			 
			VBox name = new VBox(50);
			name.getChildren().addAll(numbersTask, mean,dates,inf);
			name.setId("OneTask");
			name.setSpacing(10);
			name.setAlignment(Pos.CENTER);
			
				if(dataThru.get(h).getIdUrgency() == 3) {
					TaskBetween.getChildren().add(1,name);
				
				}else {
					TaskBetween.getChildren().add(name);
				
					//allTasksforOneHour.getChildren().add
				}
			
			
		}
		// Конец задач проходящих через этот день -- /
		_tasksAllDay.getChildren().add(TaskBetween);
		return _tasksAllDay;
	}
	
	public static Date getWeekStartDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, -1);
	    }
	    return calendar.getTime();
	}

	public static Date getWeekEndDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, 1);
	    }
	    calendar.add(Calendar.DATE, -1);
	    return calendar.getTime();
	}
	public VBox setVisibleMenuFiles(VBox vBox, LinkedList <String> somePaths) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Выбор файлов");
		List <File> files = fileChooser.showOpenMultipleDialog(primaryStage);
		//for
		return vBox;
	}

	public LinkedList<Integer> getAllIds() {
		return allIds;
	}

	public void setAllIds(LinkedList<Integer> allIds) {
		this.allIds = allIds;
	}

	public LinkedList <String> getPaths() {
		return paths;
	}

	public void setPaths(LinkedList <String> paths) {
		this.paths = paths;
	}

}
