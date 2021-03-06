package application.Scenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import application.ReadTask;
import application.Validation;
import application.WriteTask;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
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
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneMainWindow implements mainWindowUser {
	private Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	private int id;
	private LinkedList<Integer> allIds;
	private SceneChoseUser change;
	private LinkedList <String> paths;
	private QuickLetterSend senderQuich;
	private TaskLetterSend sender;
	
	public SceneMainWindow(Stage primaryStage, int UserId) {
		this.setPaths(new LinkedList<String>());
		this.allIds = new LinkedList<Integer>();
		this.sender = null;
		change = null;
		
		this.id = UserId;
		this.primaryStage = primaryStage;
		this.primaryStage.setMaximized(true);
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
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getBounds();
		
		int idChosenUser=0;
		BorderPane border =new BorderPane();
		//Scene scene = new Scene(border, 850, 650);
		Scene scene = new Scene(border,  bounds.getWidth(),  bounds.getHeight());
		// ����� ---- 
		Button exitFromProgram = new Button("����� �� ���������");
		Button exitFromLogin = new Button("����� �� ������� ������");
		ButonStyle styles = new ButonStyle(exitFromProgram);
		exitFromProgram = styles.getStyleButton();
		styles.setButon(exitFromLogin);
		exitFromLogin = styles.getStyleButton();
		exitFromLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.out.print("D����� �����");
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
		
		// ����� ----/
		Popup popup;
		popup=new Popup();
		popup.setAutoHide(true);
		//popup.setAutoFix(true);
		VBox textPopup = new VBox(50);
		textPopup.setAlignment(Pos.CENTER);
		textPopup.setPrefSize(500, 300);
		textPopup.setStyle("-fx-background-color: #FF00FF;");
		popup.getContent().add(textPopup);
		Label popupL = new Label();
		popupL.setStyle("-fx-background-color: #FFFFE0;");
		popupL.setWrapText(true);
		textPopup .getChildren().add(popupL);
		// ��������� ���� ----
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
		 System.out.println(" ���� ����� - " + id_user);
		 Button changMyInfo = new Button("�������� ������");
		 changMyInfo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneChangeDada_AddMails change = new SceneChangeDada_AddMails(primaryStage,  id_user);
					change.setId(id_user);
				}
			});
		 Button help = new Button("������");
		 Button about = new Button("� ���������");
		 Button more = new Button("������ ����������");
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
		 about.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneAbout ds = new SceneAbout(primaryStage);				}
			});
		 help.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneHelp ds = new SceneHelp(primaryStage);				}
			});
		 
		 changMyInfo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 changMyInfo.setMaxWidth(160);
		 changMyInfo.setId("button");
		 
		 Button contacts = new Button("�������� ��������");
		 contacts.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					SceneAddWorker change = new SceneAddWorker(primaryStage,  id_user);
					change.setId(id_user);
				}
			});
		 contacts.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 contacts.setMaxWidth(160);
		 contacts.setId("button");
		 
		 Button Update = new Button(" ��������� ����� ������ ");
		 Update.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 //inf.setMaxWidth(160);
		 Update.setId("button");
		 Update.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					application.Mails.LetterRecive letter = new LetterRecive(primaryStage,id);
					LetterRecive.setText(letter.getEmail());
				}
			});
		 
		 personalAccounts.getChildren().setAll(Namelabel,SecondNamelabel);
		 VboxStyle intr = new VboxStyle ();
		 VBox instr = intr.getStyleVbox();
		 personalAccounts.getChildren().addAll(changMyInfo);
		 personalAccounts.setId("pp");
		 personalAccounts.setMinWidth(250);
		 
		 VBox bottom = new VBox();
		 instr.getChildren().setAll(popupContent,bottom);
		 VboxStyle ex = new VboxStyle ();
		 VBox exits = ex.getStyleVbox();
		 exits.getChildren().addAll(exitFromProgram,exitFromLogin/*,mes,al*/);
		 exits.setId("exit");
		 TitledPane  instrument = new TitledPane("�����������",instr);
		 TitledPane  exit = new TitledPane("�����",exits);
	     accordion.getPanes().addAll(/*personalAccount,*/
	    		 instrument,exit);
	        accordion.setMinSize(250, 200);
	        accordion.setPrefSize(250, 200);
	    // ��������� ���� ----/
			
			Button readTask = new Button("������ ������ �� �����");
			readTask.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			readTask.setId("button");
			readTask.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					ReadTask reader = new ReadTask(id );
					reader.readTask(primaryStage);
				}
			});
	    /// ������ ---/
	     ToolBar toolbar = new ToolBar();
	     toolbar.getItems().add(changMyInfo);
	     toolbar.getItems().add(more);
	     toolbar.getItems().add(Update);
	     toolbar.getItems().add(contacts);
	     toolbar.getItems().add(help);
	     toolbar.getItems().add(about);
	     toolbar.getItems().add(readTask);
	        

	        /// ����� ������� ---/
	        // ���� ��� ���� ---/
	        
	        // ����� ����� ��� ���� ---/
	        // ���� ��� ����� ---/
	    HBox top = new HBox();
	    top.setSpacing(10);
	    top.setAlignment(Pos.CENTER_LEFT);
	    top.setId("H");
	    personalAccounts.setSpacing(10);
	    personalAccounts.setAlignment(Pos.CENTER);
	    toolbar.setStyle("-fx-base: dodgerblue;");
	    top.getChildren().addAll(personalAccounts,toolbar);
	    // ����� ����� ��� ����� ---/
	    // ���� ��� ���� --/
	    
	    bottom .setSpacing(10);
	    bottom .setAlignment(Pos.CENTER_LEFT);
	    bottom .setId("bottom");
	    bottom.setMaxWidth(200);
	  
	    Label quick = new Label("������� ����������� ������ ");
	    quick.setWrapText(true);
		TextField emailThem = new TextField();
		emailThem.setPromptText("������� ���� ���������");
		TextArea email = new TextArea();
		email.setPrefRowCount(6);            
		
		email.setPromptText("������� ���������");
		Button adress = new Button("�������� ��������");
		TextField text = new TextField();
		adress.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					LinkedList<Integer> all = getAllIds();
					all.clear();
					change = new SceneChoseUser(primaryStage,  id_user, idChosenUser);
				
					change.setId(id_user);
					/*all.addAll(change.getAllIds());
					System.out.println (" ���������� ���� - " + change.getAllIds());
					System.out.println (" ���������� ��������- " + all.size());
					setAllIds(all);
					System.out.println (" ���������� � ������- " + getAllIds());*/
				}
			});
		Button push = new Button("���������");
		Button quickMail = new Button("������� ���������");
		Button quickMailClose = new Button("������� ������� ���������");
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
		push.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LinkedList<Integer> all = getAllIds(); // ����� ���� �� ��������
				all.clear(); // ������� ������
				change.setId(id_user); 
				all.addAll(change.getAllIds());//���������� ���������� ���� 
				setAllIds(all);   // ��������� � ���� ������ ������ ������
				int idch = 0;
				Scanner scanner = null;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
					scanner = new Scanner(reader);
					int numberOfRows = 0;
					while (scanner.hasNext()) {
						idch = scanner.nextInt();
						scanner.nextLine();
						numberOfRows++;
					}
				} catch (FileNotFoundException e1) {
					System.out.println("���� �� ������.");
				} catch (Exception e1) {
					System.out.println("������ ��� ���������� �� �����.");
					scanner.close();
				} finally {
					scanner.close();
				}
				senderQuich = new QuickLetterSend();
				
				try {
					senderQuich.senMail(emailThem.getText(), email.getText(),idch,id,getAllIds());
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		});
	    // ����� ����� ��� ���� 
	    TabPane tabpane=new TabPane();
	    Tab tabSh = new Tab("������� ����������");
	    tabSh.setClosable(false);
	    Group rootSh = new Group();
	    tabSh.setContent(rootSh);
	    // ����� ������ --/
	    Tab tabWork = new Tab("������� ������");
	    tabWork.setClosable(false);
	    Group rootWork = new Group();
	    tabWork.setContent(rootWork);
	    // �������� ������

	    Tab tabTask = new Tab("�������� ������");
	    tabTask.setClosable(false);
	    Group rootTask = new Group();
	    tabTask.setContent(rootTask);
	    // --- ���� ��� ������ ---/ --------------------------------------------
	    //----------------------------------------------------������������ ������----------------------------/
		Button getFile = new Button("���������� ����");
		getFile.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		getFile.setId("button");
		
		Button clearAll = new Button("�������� ������");
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
		Label filesLabel = new Label("������������ ������");
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
					System.out.println(" ����� - ��� ������������: " + paths_.size());
					setPaths(paths_);
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
		ScFiles .setCursor(Cursor.CLOSED_HAND);
		ScFiles.setContent(files );
		ScFiles.setMaxWidth(165);
		ScFiles.setMinWidth(165);
		ScFiles.setStyle("-fx-alignment: center;");

	// --------------------------------- ����� ������������ ������ -----------------------------------------/
	    
	    
	    HBox task = new HBox();
	    task.setSpacing(10);
	    task.setAlignment(Pos.CENTER_LEFT);
	    task.setId("Task");
	    
	    VBox themeTask = new VBox(50);
		Label themeTaskLabel = new Label("������� ���� ������");
		TextField textTheme = new TextField();
		textTheme.setPromptText("����");
		themeTask.getChildren().addAll(themeTaskLabel, textTheme);
		themeTask.setAlignment(Pos.CENTER);
		themeTask.setSpacing(5);
		themeTask.getStyleClass().add("EnterTask");
		textTheme.setOnKeyPressed(event-> {
			int num = 95- textTheme.getText().length();
			themeTaskLabel.setText("������� ���� ������. �������� ��������: " + num);
			if(textTheme.getText().length() > 95) {
				
				textTheme.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				
				textTheme.setEditable(true);
			}
		});
	    VBox colTask = new VBox(50);
		Label colTaskLabel = new Label("������� ������������� ������");
		TextField textTaskCol = new TextField();
		textTaskCol.setPromptText("�������������");
		colTask.getChildren().addAll(colTaskLabel, textTaskCol);
		colTask.setAlignment(Pos.CENTER);
		colTask.setSpacing(5);
		colTask.getStyleClass().add("EnterTask");
		textTaskCol.setOnKeyPressed(event-> {
			int num = 42- textTaskCol.getText().length();
			colTaskLabel.setText("������� ������������� ������. �������� ��������: " + num);
			if(textTaskCol.getText().length() > 42) {
				textTaskCol.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				textTaskCol.setEditable(true);
			}
		});
		
		Button adressTask = new Button("�������� ��������");
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
					/*all.addAll(change.getAllIds());
					System.out.println (" ���������� ���� - " + change.getAllIds());
					System.out.println (" ���������� ��������- " + all.size());
					setAllIds(all);
					System.out.println (" ���������� � ������- " + getAllIds());*/
				}
			});
		
		VBox body = new VBox(50);
		Label bodyLabel = new Label("�������");
		TextArea bodyMail = new TextArea();
		bodyMail.setPromptText("���� ������");
		bodyMail.setPrefRowCount(10); 
		body.getChildren().addAll(bodyLabel, bodyMail);
		body.setAlignment(Pos.CENTER);
		body.setSpacing(5);
		body.getStyleClass().add("EnterTask");
		bodyMail.setOnKeyPressed(event-> {
			int num = 960- bodyMail.getText().length();
			bodyLabel.setText("�������. �������� ��������: " + num);
			if(bodyMail.getText().length() > 960) {
				bodyMail.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				bodyMail.setEditable(true);
			}
		});
		
		VBox description = new VBox(50);
		Label descriptionLabel = new Label("�������������� ��������");
		TextArea descriptionMail = new TextArea();
		descriptionMail.setPromptText("����������� ����������");
		descriptionMail.setPrefRowCount(5); 
		description.getChildren().addAll(descriptionLabel, descriptionMail);
		description.setAlignment(Pos.CENTER);
		description.setSpacing(5);
		description.getStyleClass().add("EnterTask");
		descriptionMail.setOnKeyPressed(event-> {
			int num = 960- descriptionMail.getText().length();
			descriptionLabel.setText("�������������� ��������. �������� ��������: " + num);
			if(descriptionMail.getText().length() > 960) {
				descriptionMail.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				descriptionMail.setEditable(true);
			}
		});
		
		VBox link = new VBox(50);
		Label linkLabel = new Label("������");
		TextArea linkMail = new TextArea();
		linkMail.setPromptText("������");
		linkMail.setPrefRowCount(7); 
		linkMail.setOnKeyPressed(event-> {
			int num = 590 - linkMail.getText().length();
			linkLabel.setText("������. �������� ��������: " + num);
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
		Label dateStartLabel = new Label("���� ������ ������");
		DatePicker datePickerStart = new DatePicker();
		datePickerStart.setEditable(false);
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); // will only show the current time
		
		dateStart.getChildren().addAll(dateStartLabel, datePickerStart);
		dateStart.setAlignment(Pos.CENTER);
		dateStart.setSpacing(5);
		dateStart.getStyleClass().add("EnterTask");
		
		VBox timeStart = new VBox(50);
		Label timeStartLabel = new Label("����� ������ �������");
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
		Label timeEndLabel = new Label("����� ��������� �������");
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
		Label dateEndLabel = new Label("���� ����� ������");
		DatePicker datePickerEnd = new DatePicker();
		datePickerEnd.setEditable(false);
		dateEnd.getChildren().addAll(dateEndLabel, datePickerEnd);
		dateEnd.setAlignment(Pos.CENTER);
		dateEnd.setSpacing(5);
		dateEnd.getStyleClass().add("EnterTask");
		
		VBox BoxDone = new VBox(50);
		Label isDoneLabel = new Label("������� � ���������� ������");
		CheckBox isDone = new CheckBox();
		BoxDone.getChildren().addAll(isDoneLabel,  isDone);
		BoxDone.setAlignment(Pos.CENTER);
		BoxDone.setSpacing(5);
		BoxDone.getStyleClass().add("EnterTask");
		
	    VBox supervisor = new VBox(50);
		Label supervisorLabel = new Label("������� �.�.�. ������������ ������");
		supervisorLabel.setWrapText(true);
		TextField textSupervisor = new TextField();
		textSupervisor.setPromptText("�.�.�.");
		supervisor.getChildren().addAll(supervisorLabel, textSupervisor);
		supervisor.setAlignment(Pos.CENTER);
		supervisor.setSpacing(5);
		supervisor.getStyleClass().add("EnterTask");
		textSupervisor.setOnKeyPressed(event-> {
			int num = 195 - textSupervisor.getText().length();
			supervisorLabel.setText("������� �.�.�. ������������ ������. �������� ��������: " + num);
			if(textSupervisor.getText().length() > 195) {
				textSupervisor.setEditable(false);
			}
			if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
				textSupervisor.setEditable(true);
			}
		});
		
		VBox urgencyBox = new VBox(50);
		Label urgencyLabel = new Label("��������� ������");
		ChoiceBox urgency = new ChoiceBox();
		urgency.getItems().addAll("�������", "�����", "������");
		urgency.getSelectionModel().selectFirst();
		urgencyBox.getChildren().addAll(urgencyLabel,urgency);
		urgencyBox.setAlignment(Pos.CENTER);
		urgencyBox.setSpacing(5);
		urgencyBox.getStyleClass().add("EnterTask");
		
		Button pushTaskOun = new Button("������ ���� ������");
		pushTaskOun.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		pushTaskOun.setId("button");
		pushTaskOun.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				// ��������� ������� - ���� ������ ������.
				validtion.setText(startHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ��� ������ ���������� ������ <"+ startHour.getText() + ">\n");
					startHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				// ��������� ������� ����� ������ ������ 
				validtion.setText(startMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ������ ������ ���������� ������ <" + startMinute.getText() + ">\n");
					startMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				validtion.setText(endHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ��� ��������� ���������� ������ <" + endHour.getText() + ">\n");
					endHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				validtion.setText(endMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ������ ��������� ���������� ������ <" + endMinute.getText() + ">\n");
					endMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				int countFalse = 0;
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				str.append(" ���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				
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
				String taskCol = null;// �������������
				String itsDone = "�� ���������";
				body = bodyMail.getText();
				description = descriptionMail.getText();
				link = linkMail.getText();
				supervisor = textSupervisor.getText();
				taskCol = textTaskCol.getText();
				
				String linkForDb = null;
				
				// ��� ��������� ������. -- /
				int idurgency;
				idurgency = urgency.getSelectionModel().getSelectedIndex();
				idurgency +=1;
				System.out.println("��� ������ - " + idurgency);
				// ����� ���� ��������� ������ -- /
				// ���� �������� ������ -- /
				Date dt = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				// ����� ���� �������� ������ -- /
				// ���� ������ ������ -- /
				String StartTime = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() + ":" + 0;
				// ���� ����� ������ ������ -- /
				// ���� ����� ������ -- /
				String EndTime = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() + ":" + 0;
				// ���� ����� ����� ������ -- /
				
				// ���������� ������ -- /
				int itsDoneToDB = 0;
				// ����� ���������� ������ -- /
				
				// ������ �� ���������� ������ � ������� ������
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + textTheme.getText() +"', "
				+ "'" + body + "', '" + idurgency + "', '" + currentTime + "',"
				+ " '" + StartTime + "', '" + EndTime + "', '" + supervisor + "', ' " + itsDoneToDB + "', '" + taskCol + "', '" + description + "');";
				// ����� ������� �� ���������� ������ � �������
				// ���������� ������ � �� -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// ����� ���������� ������ � �� -- /
				
				// ������ ����� ���� � ����� ����������� ������ -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + textTheme.getText()+ "' AND `task`.`body` = '" + body +"';";
				int idNewTask = add.WhoAdd(idNew);
				// ����� ���������� ���� ����� ������ -- /
				
				//�������� ������ � ������� ������������ ������ -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + id + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// ����� ���������� ������ � ������� ������ ������������ -- /
				
				// �������� � ���������� ������ -- /
				if (!linkMail.getText().isEmpty() ) {
					linkForDb = linkMail.getText(); 
					// ���������� ������ � ������� ������ � ������ -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + linkForDb + "');";
					add.addLink(addLink);
					// ����� ���������� ������ � ������� ������ � ������ -- /
				}
				// ����� ��������� ������ -- /
				}
			}
		});
		
		Button pushTask = new Button("���������");
		pushTask.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		pushTask.setId("button");
		pushTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				
				validtion.setText(startHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ��� ������ ���������� ������ <"+ startHour.getText() + ">\n");
					startHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				validtion.setText(startMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ������ ������ ���������� ������ <" + startMinute.getText() + ">\n");
					startMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				validtion.setText(endHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ��� ��������� ���������� ������ <" + endHour.getText() + ">\n");
					endHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				validtion.setText(endMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ������ ��������� ���������� ������ <" + endMinute.getText() + ">\n");
					endMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				int countFalse = 0;
				/*if (datePickerStart.getValue().toString().equals("")) {
					datePickerStart.setStyle("-fx-background-color: #FF7F50;");
					countFalse ++;
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ���� ������ ���������� ������.\n");
				} else {
					System.out.println(" ���������� - |" + datePickerStart.getValue().toString() + "|");
					datePickerStart.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				if (datePickerEnd.getValue().toString().equals("")) {
					datePickerEnd.setStyle("-fx-background-color: #FF7F50;");
					countFalse ++;
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ���� ������ ���������� ������.\n");
				} else {
					System.out.println(" ���������� - |" + datePickerEnd.getValue().toString() + "|");
					datePickerEnd.setStyle("-fx-background-color: #FFFFFF;");
				}*/
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				str.append(" ���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				
				LinkedList<Integer> all = getAllIds();
				all.clear();
				change.setId(id_user);
				all.addAll(change.getAllIds());
				//System.out.println (" ���������� ���� - " + change.getAllIds());
				//System.out.println (" ���������� ��������- " + all.size());
				setAllIds(all);
				//System.out.println (" ���������� � ������- " + getAllIds());
				
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
				String taskCol = null;// �������������
				String itsDone = "�� ���������";
				body = bodyMail.getText();
				description = descriptionMail.getText();
				link = linkMail.getText();
				supervisor = textSupervisor.getText();
				taskCol = textTaskCol.getText();
				
				String linkForDb = null;

				// ��� ��������� ������. -- /
				int idurgency;
				idurgency = urgency.getSelectionModel().getSelectedIndex();
				idurgency +=1;
				System.out.println("��� ������ ��������� - " + idurgency);
				// ����� ���� ��������� ������ -- /
				// ���� �������� ������ -- /
				Date dt = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				// ����� ���� �������� ������ -- /
				// ���� ������ ������ -- /
				String StartTime = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() + ":" + 0;
				// ���� ����� ������ ������ -- /
				// ���� ����� ������ -- /
				String EndTime = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() + ":" + 0;
				// ���� ����� ����� ������ -- /
				
				// ���������� ������ -- /
				int itsDoneToDB = 0;
				// ����� ���������� ������ -- /
				
				// ������ �� ���������� ������ � ������� ������
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + textTheme.getText() +"', "
				+ "'" + body + "', '" + idurgency + "', '" + currentTime + "',"
				+ " '" + StartTime + "', '" + EndTime + "', '" + supervisor + "', ' " + itsDoneToDB + "', '" + taskCol + "', '" + description + "');";
				// ����� ������� �� ���������� ������ � �������
				
				// ���������� ������ � �� -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// ����� ���������� ������ � �� -- /
				
				// ������ ����� ���� � ����� ����������� ������ -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + textTheme.getText()+ "' AND `task`.`body` = '" + body +"';";
				//String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`id_task` = LAST_INSERT_ID();";
				
				int idNewTask = add.WhoAdd(idNew);
				// ����� ���������� ���� ����� ������ -- /
				
				//�������� ������ � ������� ������������ ������ -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + id + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// ����� ���������� ������ � ������� ������ ������������ -- /
		
				// �������� � ���������� ������ -- /
				if (!linkMail.getText().isEmpty() ) {
					linkForDb = linkMail.getText(); 
					// ���������� ������ � ������� ������ � ������ -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + linkForDb + "');";
					add.addLink(addLink);
					// ����� ���������� ������ � ������� ������ � ������ -- /
				}
				// ����� ��������� ������ -- /
				
				urgencyMail = urgency.getSelectionModel().getSelectedItem().toString();
				dateStart = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() ;
				dataEnd = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() ;
				GregorianCalendar thisData = new GregorianCalendar();
				data = thisData.getTime();
				//dataCreate = data.toString();
				dataCreate =  currentTime;
				
				Theme = "����� ����������: �������: " + textTheme.getText();
				Text  = "�������: " + body  + ";\n" +
				"������������� ��������: " +description+";\n" + 
				"������ �� �������������� ���������: " + link + ";\n" +
				"������������: " +supervisor+";\n" +
				"������������� ���������: " + taskCol + ";\n"+
				"��������� ������: " + urgencyMail +";\n" +
				"���� � ����� ������ ����������: " + dateStart +";\n" +
				"���� � ����� ��������� �����������: " + dataEnd +";\n"+
				"���� � ����� �������� ������:" + dataCreate + ";\n" +
				"����������: " + itsDone ; 
				
				Scanner scanner = null;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
					scanner = new Scanner(reader);
					int numberOfRows = 0;
					while (scanner.hasNext()) {
						idch = scanner.nextInt();
						scanner.nextLine();
						numberOfRows++;
					}
				} catch (FileNotFoundException e1) {
					System.out.println("���� �� ������.");
				} catch (Exception e1) {
					System.out.println("������ ��� ���������� �� �����.");
					scanner.close();
				} finally {
					scanner.close();
				}
				
				TaskLetterSend sender = new TaskLetterSend();
				try {
					//System.out.println (" ���������� � ������ - " + getAllIds().size());
					sender.senMail(Theme, Text,idch,id,body,supervisor,link,description,dateStart,dataEnd, dataCreate,urgencyMail,taskCol,itsDone,idNewTask,getAllIds(),getPaths());
					
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		
		Button writeTask = new Button("�������� � ����");
		writeTask.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		writeTask.setId("button");
		writeTask.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				popupL.setText("");
				boolean isValid = true;
				StringBuilder str = new StringBuilder();
				Validation validtion = new Validation();
				LinkedList <Boolean> boolVali = new LinkedList<Boolean>();
				
				validtion.setText(startHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ��� ������ ���������� ������ <"+ startHour.getText() + ">\n");
					startHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				validtion.setText(startMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ������ ���������� �������\n - ������ ������ ���������� ������ <" + startMinute.getText() + ">\n");
					startMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					startMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				validtion.setText(endHour.getText());
				isValid = validtion.validHour();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ��� ��������� ���������� ������ <" + endHour.getText() + ">\n");
					endHour.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endHour.setStyle("-fx-background-color: #FFFFFF;");
				}
				validtion.setText(endMinute.getText());
				isValid = validtion.validMinutes();
				boolVali.add(isValid);
				if (!isValid) {
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ������ ��������� ���������� ������ <" + endMinute.getText() + ">\n");
					endMinute.setStyle("-fx-background-color: #FF7F50;");
				} else {
					endMinute.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				int countFalse = 0;
				/*if (datePickerStart.getValue().toString().equals("")) {
					datePickerStart.setStyle("-fx-background-color: #FF7F50;");
					countFalse ++;
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ���� ������ ���������� ������.\n");
				} else {
					
					datePickerStart.setStyle("-fx-background-color: #FFFFFF;");
				}
				
				if (datePickerEnd.getValue().toString().equals("")) {
					datePickerEnd.setStyle("-fx-background-color: #FF7F50;");
					countFalse ++;
					str.append(" ������ � ���������� ���� ��������� ���������� �������\n - ���� ������ ���������� ������.\n");
				} else {
					datePickerEnd.setStyle("-fx-background-color: #FFFFFF;");
					System.out.println(" ���������� - |" + datePickerEnd.getValue().toString() + "|");
				}*/
				for(boolean noValid: boolVali) {
					if (!noValid) {
						countFalse ++;
						isValid = false;
					}
				}
				str.append(" ���������� ���� ������: "+countFalse);
				if (isValid == false ) {
					popupL.setText(str.toString());
					popup.show(primaryStage);
					
				} else {
				
				//int idch = 0;
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
				String taskCol = null;// �������������
				String itsDone = "�� ���������";
				body = bodyMail.getText();
				description = descriptionMail.getText();
				link = linkMail.getText();
				supervisor = textSupervisor.getText();
				taskCol = textTaskCol.getText();
				
				String linkForDb = null;

				// ��� ��������� ������. -- /
				int idurgency;
				idurgency = urgency.getSelectionModel().getSelectedIndex();
				idurgency +=1;
				//System.out.println("��� ������ ��������� - " + idurgency);
				// ����� ���� ��������� ������ -- /
				// ���� �������� ������ -- /
				Date dt = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				// ����� ���� �������� ������ -- /
				// ���� ������ ������ -- /
				String StartTime = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() + ":" + 0;
				// ���� ����� ������ ������ -- /
				// ���� ����� ������ -- /
				String EndTime = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() + ":" + 0;
				// ���� ����� ����� ������ -- /
				
				// ���������� ������ -- /
				int itsDoneToDB = 0;
				// ����� ���������� ������ -- /
				
				// ������ �� ���������� ������ � ������� ������
				String queryAddTask = "INSERT INTO `taskmail`.`task` (`title`, `body`, `id_urgency`, `create_date_time`, `start_date_time`,"
				+ " `end_date_time`, `supervisor`, `is_done`, `taskcol`, `description`) VALUES ('" + textTheme.getText() +"', "
				+ "'" + body + "', '" + idurgency + "', '" + currentTime + "',"
				+ " '" + StartTime + "', '" + EndTime + "', '" + supervisor + "', ' " + itsDoneToDB + "', '" + taskCol + "', '" + description + "');";
				// ����� ������� �� ���������� ������ � �������
				
				// ���������� ������ � �� -- /
				WriteTaskInDBCreated add = new WriteTaskInDBCreated(queryAddTask);
				add.execeteQuery();
				// ����� ���������� ������ � �� -- /
				
				// ������ ����� ���� � ����� ����������� ������ -- /
				String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`title` = '" + textTheme.getText()+ "' AND `task`.`body` = '" + body +"';";
				//String idNew = "SELECT `task`.`id_task` FROM `taskmail`.`task` WHERE `task`.`id_task` = LAST_INSERT_ID();";
				
				int idNewTask = add.WhoAdd(idNew);
				// ����� ���������� ���� ����� ������ -- /
				
				//�������� ������ � ������� ������������ ������ -- /
				String addUserTask = "INSERT INTO `taskmail`.`user_task` (`id_user`, `id_task`) VALUES ('" + id + "', '" + idNewTask + "');";
				add.addUserTask(addUserTask);
				// ����� ���������� ������ � ������� ������ ������������ -- /
		
				// �������� � ���������� ������ -- /
				if (!linkMail.getText().isEmpty() ) {
					linkForDb = linkMail.getText(); 
					// ���������� ������ � ������� ������ � ������ -- /
					String addLink = "INSERT INTO `taskmail`.`task_link` (`id_task`, `link`) VALUES ('" + idNewTask + "', '" + linkForDb + "');";
					add.addLink(addLink);
					// ����� ���������� ������ � ������� ������ � ������ -- /
				}
				// ����� ��������� ������ -- /
				
				urgencyMail = urgency.getSelectionModel().getSelectedItem().toString();
				dateStart = datePickerStart.getValue().toString() + " " + startHour.getText() + ":"+startMinute.getText() ;
				dataEnd = datePickerEnd.getValue().toString() + " " + endHour.getText() + ":"+ endMinute.getText() ;
				GregorianCalendar thisData = new GregorianCalendar();
				data = thisData.getTime();
				//dataCreate = data.toString();
				dataCreate =  currentTime;
				
				Theme = "����� ����������: " + textTheme.getText();
				Text  = "�������: " + body  + ";\n" +
				"������������� ��������: " +description+";\n" + 
				"������ �� �������������� ���������: " + link + ";\n" +
				"������������: " +supervisor+";\n" +
				"������������� ���������: " + taskCol + ";\n"+
				"��������� ������: " + urgencyMail +";\n" +
				"���� � ����� ������ ����������: " + dateStart +";\n" +
				"���� � ����� ��������� �����������: " + dataEnd +";\n"+
				"���� � ����� �������� ������:" + dataCreate + ";\n" +
				"����������: " + itsDone ; 
				
				WriteTask writer = new WriteTask();
				
					///System.out.println (" ���������� � ������ - " + getAllIds().size());
					try {
						writer.writeTask(Theme, Text,id,body,supervisor,link,description,dateStart,dataEnd, dataCreate,urgencyMail,taskCol,itsDone,primaryStage,idNewTask,getPaths());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		
			}
			}
		});
		
		HBox buttons = new HBox(50);
		buttons.getChildren().addAll(adressTask,pushTask,pushTaskOun,writeTask);
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
		
		HBox TASK = new HBox(50);
		TASK .getChildren().addAll(mainPart,SecondPart,ScFiles);
		TASK .setId("Task");
		TASK .setSpacing(10);
		TASK .setAlignment(Pos.CENTER);
		//task
		tabWork.setContent(TASK);
	    // --- ����� ����� ��� ����� ������ ---/
	    // ����� ����� ������
		// ������ �������� ������ -- /
		
		VBox givenTasks = new VBox(50);
		givenTasks.setId("Task");
		givenTasks.setSpacing(10);
		givenTasks.setAlignment(Pos.CENTER);
		
		VBox givenTaskdate = new VBox(50);
		Label givenTaskdateLabel = new Label(" �������� ������ �� ���� ����: ");
		DatePicker givenTaskdatePickerStart = new DatePicker();
		givenTaskdatePickerStart.setEditable(false);
		givenTaskdate.getChildren().addAll(givenTaskdateLabel, givenTaskdatePickerStart);
		givenTaskdate.setAlignment(Pos.CENTER);
		givenTaskdate.setSpacing(5);
		givenTaskdate.getStyleClass().add("EnterTask");
		
		ScrollPane sp=new ScrollPane();
		sp.setLayoutX(10);
		sp.setLayoutY(10);
		sp.setHmin(400);
		sp.setCursor(Cursor.CLOSED_HAND);
		sp.setContent(givenTasks);
		
		Button buttonWhatTaskIGive = new Button(" �������� ������ ");
		buttonWhatTaskIGive .getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonWhatTaskIGive.setId("button");
		buttonWhatTaskIGive.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					givenTasks.getChildren().clear();
					LocalDate starts = givenTaskdatePickerStart.getValue();
					System.out.println("���� ������- " + java.sql.Date.valueOf(starts));
					sp.setContent(getGivaenTasks(givenTasks,info.getName(), info.getSecondName(), info.getLastName() , java.sql.Date.valueOf(starts)));
				}
			});

		
		VBox AllgTaskIn = new VBox(50);
		AllgTaskIn.setId("C");
		AllgTaskIn.setSpacing(10);
		AllgTaskIn.setAlignment(Pos.CENTER);
		Label AllgTaskInLabel = new Label(" ����������:  ");
		AllgTaskIn .setAlignment(Pos.CENTER);
		AllgTaskInLabel.setId("PULL");
		AllgTaskIn.getChildren().addAll(AllgTaskInLabel, givenTaskdate, buttonWhatTaskIGive,sp);
		AllgTaskIn.setMinWidth(250);
		
		Label _Label = new Label(" �������� ������: ");
		givenTasks.getChildren().add(_Label);
		givenTasks.setMinWidth(scene.getWidth());
		
		tabTask.setContent(AllgTaskIn);
		// ����� �������� ������ -- /
		// ������� ������ -- /
		// ����� ������� � ������� ... --- /
		//01
		VBox _tasksAllDay = new VBox(50);
		_tasksAllDay.setSpacing(10);
		_tasksAllDay.setAlignment(Pos.CENTER);
		Label _allLabel = new Label(" ���������� ");
		_tasksAllDay.getChildren().addAll(_allLabel);
		_tasksAllDay.setMinWidth(250);
		
		VBox _44 = new VBox(50);
		_44.setSpacing(10);
		_44.setAlignment(Pos.CENTER);
		_44.getChildren().addAll(_allLabel);
		_44.setMinWidth(250);

		ScrollPane all =new ScrollPane();
		all.setLayoutX(10);
		all.setLayoutY(10);
		all.setCursor(Cursor.CLOSED_HAND);
		all.setMinWidth(250);
		
		 Button whatTasks = new Button(" �������� ������ ");
		 whatTasks.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		 whatTasks.setId("button");
		 whatTasks.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					_tasksAllDay.getChildren().clear();
					_tasksAllDay.getChildren().addAll(_allLabel);
					all.setContent(getTasksOnThisDays (_tasksAllDay));
				}
			});
		
		 _44.getChildren().addAll(whatTasks,all); 
		 
		// ������� ������� �����... --- /
		// ����  -- //
			VBox month = new VBox(50);
		
			Label month_Label = new Label("��������� �� �����: ");
			month_Label.setWrapText(true);
			
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
			
		 tabSh.setContent(_44);
		// ����� ������� ������ -- /
	    tabpane.getTabs().addAll(tabSh,tabWork,tabTask/*,tabMonth*/);
	    
	    Label alarm = new Label(" �����������  ");
	    HBox alarmBox= new HBox();
	    alarmBox.setId("BoxForAlarm");
	    alarmBox.getChildren().setAll(alarm,uiTimer);
	    
	    double widthWindow = primaryStage.getScene().getWidth()*1.7;
		alarm.setId("alarm");
		
		border.setTop(top);
		border.setBottom(alarmBox);
	    border.setCenter(tabpane);
	    border.setLeft(accordion);
	    border.setId("win");
		
		scene.getStylesheets().add(getClass().getResource("/application/styles/mainWindow.css").toExternalForm());
		return scene;
	}

	@Override
	public void set() {
		this.primaryStage.setScene(this.ounScene);
		this.primaryStage.setMaximized(true);
		this.primaryStage.show();
	}
	public void setNewScene(Stage primaryStage, Scene newScene) {
		primaryStage.close();
		primaryStage.setScene(newScene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
	
	public void startNewScene() {
		//this.
	}
						
	public VBox getTasksOnThisDays (VBox _tasksAllDay) {
		LinkedList <EntityTask> data = new LinkedList <EntityTask>();
		LinkedList <EntityTask> dataEnd = new LinkedList <EntityTask>();
		Date thisDate = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(thisDate);
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
		ReadOunTasks thisDayTasks = new ReadOunTasks();
		thisDayTasks.setSearchQuery(queryForThisDay);
		thisDayTasks.whatIs();
		data = thisDayTasks.getData();
		
		//String queryForThisDayAnd ="SELECT * FROM `taskmail`.`task` WHERE `task`.`end_date_time` LIKE '" + DateSAllT +
		//		"%' AND `start_date_time` NOT LIKE '" + DateSAllT + "'; ";
		
		String queryForThisDayAnd = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
				+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
				+ "`user_task`.`id_user` = `users`.`id_user` "
				+ "WHERE `task`.`end_date_time` LIKE '" + sqlDate + 
				"%' AND `start_date_time` NOT LIKE '" + sqlDate + "' AND `users`.`id_user` ='" + this.id + "'; ";
		//System.out.println("���� ��� ������ - " + queryForThisDayAnd);
		
		ReadOneTask2 thisDayTasksAnd = new ReadOneTask2();
		thisDayTasksAnd.setSearchQuery(queryForThisDayAnd);
		thisDayTasksAnd.whatIs();
		dataEnd = thisDayTasksAnd.getData();
		
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
		//System.out.println("���� ��� ������ ����� ����� ������� ����� - " + queryForBetweenDate);
		ReadOunTasks3 thru = new ReadOunTasks3();
		thru.setSearchQuery(queryForBetweenDate);
		thru.whatIs();
		LinkedList <EntityTask> dataThru = new LinkedList <EntityTask>();
		dataThru = thru.getData();
		//System.out.println("���������� ����� ���������� ����� ���� ���� - " + dataThru.size());
		//System.out.println("�������� ���������� ������� - ");
		/*for (int i =0; i < data.size(); i ++ ) {
			System.out.println("i - ������ - " + i + " ����� - " + data.get(i).getDateStrart() + " ���� " + data.get(i).getTitle());
		}
		
		for (int i =0; i < dataEnd.size(); i ++ ) {
			System.out.println("i - " + i + " ����� - " + dataEnd.get(i).getDateStrart() + " ���� " + dataEnd.get(i).getTitle());
		}*/
		HBox TaskBetween = new HBox(50);
		TaskBetween.setId("C");
		TaskBetween.setSpacing(10);
		TaskBetween.setAlignment(Pos.CENTER);
		Label BetweenLabel = new Label(" ������ ������� �����\n ��������� � ������� ���: ");
		BetweenLabel.setId("PULL");
		BetweenLabel.setWrapText(true);
		TaskBetween.getChildren().add(BetweenLabel);
		TaskBetween.setMinWidth(250);
		//
		ScrollPane betweenSc =new ScrollPane();
		betweenSc.setLayoutX(10);
		betweenSc.setLayoutY(10);
		betweenSc.setCursor(Cursor.CLOSED_HAND);
		betweenSc.setContent(TaskBetween);
		betweenSc.setMinWidth(250);
		
		// ����� ����� ���������� ����� ���� ���� -- /
		for(int i =0; i <24; i++) {
			int numberTask = i+1;
			HBox allTasksforOneHour = new HBox(50);
			allTasksforOneHour.setId("C");
			allTasksforOneHour.setSpacing(10);
			allTasksforOneHour.setAlignment(Pos.CENTER);
			Label _01_TasksLabels = new Label(" ������ �� ���� ���: " + numberTask+":00");
			_01_TasksLabels.setId("PULL");
			allTasksforOneHour.getChildren().add(_01_TasksLabels);
			allTasksforOneHour.setMinWidth(250);
			
			ScrollPane allScroolForOneHour =new ScrollPane();
			allScroolForOneHour.setLayoutX(10);
			allScroolForOneHour.setLayoutY(10);
			allScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			allScroolForOneHour.setContent(allTasksforOneHour);
			allScroolForOneHour.setMinWidth(250);
			
			HBox DoneallTasksforOneHour = new HBox(50);
			DoneallTasksforOneHour.setId("C");
			DoneallTasksforOneHour.setSpacing(10);
			DoneallTasksforOneHour.setAlignment(Pos.CENTER);
			Label DoneTasks = new Label(" ����������� ������: " + numberTask+":00");
			DoneTasks.setId("PULL");
			DoneallTasksforOneHour.getChildren().add(DoneTasks);
			DoneallTasksforOneHour.setMinWidth(250);
			
			VBox TasksforOneHour = new VBox(50);
			TasksforOneHour.setId("C");
			TasksforOneHour.setSpacing(10);
			TasksforOneHour.setAlignment(Pos.CENTER);
			Label _01Label1 = new Label(" ��� " + numberTask +": ");
			_01Label1.setId("PULL");
			TasksforOneHour.setMinWidth(250);
			
			HBox Done = new HBox(50);
			Done.setId("C");
			Done.setSpacing(10);
			Done.setAlignment(Pos.CENTER);
			Label DoneT = new Label(" ����������� ������: ");
			DoneT.setId("PULL");
			Done.getChildren().add(DoneT);
			Done.setMinWidth(250);
			
			//��������� ����� �������
			HBox TaskEndforOneHour = new HBox(50);
			TaskEndforOneHour.setId("C");
			TaskEndforOneHour.setSpacing(10);
			TaskEndforOneHour.setAlignment(Pos.CENTER);
			Label eng_time = new Label(" ��������� ������ ");
			TaskEndforOneHour.getChildren().add(eng_time);
			Label _endLabel1 = new Label(" ������ ���� ����������\n � ����� �������: ");
			_endLabel1.setId("PULL");
			_endLabel1.setWrapText(true);
			TaskEndforOneHour.setMinWidth(250);
			//
			ScrollPane alland =new ScrollPane();
			alland.setLayoutX(10);
			alland.setLayoutY(10);
			alland.setCursor(Cursor.CLOSED_HAND);
			alland.setContent(TaskEndforOneHour);
			alland.setMinWidth(250);
			// - ������ ���������� ����� ���� ���� -- /
			//��������� ����� �������
			
			HBox day = new HBox(50);
			day.setId("S");
			day.setSpacing(10);
			day.setAlignment(Pos.CENTER);
			day.getChildren().add(TasksforOneHour);
			day.setMinWidth(250);
			
			ScrollPane ScroolForOneHour =new ScrollPane();
			ScroolForOneHour.setLayoutX(10);
			ScroolForOneHour.setLayoutY(10);
			ScroolForOneHour.setCursor(Cursor.CLOSED_HAND);
			ScroolForOneHour.setContent(day);
			ScroolForOneHour.setMinWidth(250);
			
			_tasksAllDay.getChildren().addAll(ScroolForOneHour);
			
			for (int j =0; j < data.size(); j++) {
				int iterator = j+1;
				Label numbersTask = new Label(" ������� �" +iterator+" : ");
				Locale.setDefault(new Locale("ru","RU"));

				Date dateS = data.get(j).getDateStrart();
				GregorianCalendar dateStartss = new GregorianCalendar();
				dateStartss.setTime(dateS);
				int Months = dateStartss.get(Calendar.MONTH) + 1;
				String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
						dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
				
				if ((i+1 == dateStartss.get(Calendar.HOUR_OF_DAY)) || ( (i==0) && (0 == dateStartss.get(Calendar.HOUR_OF_DAY)))) {
				EntityTask thisTask = data.get(j);
				VBox body_ = new VBox(50);
				Label body_Label = new Label();
				
				Label a_Label = new Label("��������: ");
				body_Label.setText(data.get(j).getBode());
				body_Label.setWrapText(true);
				body_.getChildren().addAll(a_Label, body_Label);
				body_.setAlignment(Pos.CENTER);
				body_.setSpacing(5);
				body_.getStyleClass().add("OneStringBaze");
				
				VBox title_ = new VBox(50);
				Label t_Label = new Label("���������: ");
				Label title_Label = new Label();
				title_Label.setText(data.get(j).getTitle());
				title_Label.setWrapText(true);
				title_.getChildren().addAll(t_Label,title_Label);
				title_.setAlignment(Pos.CENTER);
				title_.setSpacing(5);
				title_.getStyleClass().add("OneStringBaze");
				
				ColorAdjust eddd = new ColorAdjust();
				eddd.setHue(0.5);
				eddd.setSaturation(1);
				
				HBox mean = new HBox(50);
				mean.getChildren().addAll(title_,body_);
				mean.setAlignment(Pos.CENTER);
				mean.setSpacing(5);
				mean.getStyleClass().add("OneString");
			
				
				ScrollPane ScMain =new ScrollPane();
				ScMain.setLayoutX(10);
				ScMain.setLayoutY(10);
				ScMain .setCursor(Cursor.CLOSED_HAND);
				ScMain.setContent(mean);
				ScMain.setMaxWidth(300);
				ScMain.setMinWidth(300);
				ScMain.setMinHeight(160);
				ScMain.setMaxHeight(300);
				ScMain.setStyle("-fx-alignment: center; -fx-background-color: #FFA500; -fx-padding: 2px;");
				
				// �����
				Date dateE = data.get(j).getDateEnd();
				GregorianCalendar dateEnds = new GregorianCalendar();
				dateEnds.setTime(dateE);
				Months = dateEnds.get(Calendar.MONTH) + 1;
				String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
						dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
				
				VBox dateST = new VBox(50);
				Label dateST_Label = new Label();
				Label datest_Label = new Label("���� � �����\n ������ ����������: ");
				datest_Label.setWrapText(true);
				dateST_Label.setText(DateSAllTs);
				dateST.getChildren().addAll(datest_Label, dateST_Label);
				dateST.setAlignment(Pos.CENTER);
				dateST.setSpacing(5);
				dateST.getStyleClass().add("OneStringBaze");
				
				VBox dateEn = new VBox(50);
				Label dateEn_Label = new Label();
				Label dateen_Label = new Label("���� � �����\n ��������� ����������: ");
				dateen_Label.setWrapText(true);
				dateEn_Label.setText(DateEAllT);
				dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
				dateEn.setAlignment(Pos.CENTER);
				dateEn.setSpacing(5);
				dateEn.getStyleClass().add("OneStringBaze");
				
				VBox _urgency = new VBox(50);
				_urgency.setEffect(eddd);
				Label _urgencyLabel = new Label("��������� ������: ");
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
				
				 Button inf = new Button("������ ���������");
				 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 inf.setId("button");
				 inf.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
						}
					});
				 
				VBox name = new VBox(50);
				name.getChildren().addAll(numbersTask, mean,/*ScMain,*/dates,inf);
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
					}
				}
				}
			}
			for (int f = 0; f < dataEnd.size(); f++ ) {
				int iterator = f+1;
				Label numbersTask = new Label(" ������� �" +iterator+" : ");
				//System.out.println(" ������ ������� ������ ���� ��������� � ����� �����. " + dataEnd.get(f).getDateEnd() + " create " + dataEnd.get(f).getDateCreate() );
				//System.out.println("����� - " + f + " ������ - " + dataEnd.get(f).getTitle() + " ����� ������" + dataEnd.get(f).getDateStrart());
				Locale.setDefault(new Locale("ru","RU"));
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
						// ����� ����� ������� ����� ������������� �������
						EntityTask thisTask = dataEnd.get(f);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						Label a_Label = new Label("��������: ");
						body_Label.setText(dataEnd.get(f).getBode());
						body_Label.setWrapText(true);
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("���������: ");
						Label title_Label = new Label();
						title_Label.setText(dataEnd.get(f).getTitle());
						title_Label.setWrapText(true);
						title_.getChildren().addAll(t_Label,title_Label);
						title_.setAlignment(Pos.CENTER);
						title_.setSpacing(5);
						title_.getStyleClass().add("OneStringBaze");
						
						HBox mean = new HBox(50);
						mean.getChildren().addAll(title_,body_);
						mean.setAlignment(Pos.CENTER);
						mean.setSpacing(5);
						mean.getStyleClass().add("OneString");
						// �����
						Date dateE = dataEnd.get(f).getDateEnd();
						GregorianCalendar dateEnds = new GregorianCalendar();
						dateEnds.setTime(dateE);
						Months = dateEnds.get(Calendar.MONTH) + 1;
						String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
								dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
						
						VBox dateST = new VBox(50);
						Label dateST_Label = new Label();
						Label datest_Label = new Label("���� � �����\n ������ ����������: ");
						datest_Label.setWrapText(true);
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("���� � �����\n ��������� ����������: ");
						dateen_Label.setWrapText(true);
						dateEn_Label.setText(DateEAllT);
						dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
						dateEn.setAlignment(Pos.CENTER);
						dateEn.setSpacing(5);
						dateEn.getStyleClass().add("OneStringBaze");
						VBox _urgency = new VBox(50);
						Label _urgencyLabel = new Label("��������� ������: ");
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
						 Button inf = new Button("������ ���������");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
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
						if(dataEnd.get(f).getIsDone() > 0) {
							Done.getChildren().add(name);
						} else {
							if(dataEnd.get(f).getIdUrgency() == 3) {
								TaskEndforOneHour.getChildren().add(1,name);
							} else {
								TaskEndforOneHour.getChildren().add(name);
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
			if (TaskEndforOneHour.getChildren().size() > 0) {
				day.getChildren().addAll(new Label("������, ������� �����\n ��������� � ����� ����."),alland);
			}
			
		}
		// ������ ���������� ����� ���� ���� -- /
		for (int h =0; h < dataThru.size(); h++) {
			int iterator = h+1;
			Label numbersTask = new Label(" ������� �" +iterator+" : ");
			Locale.setDefault(new Locale("ru","RU"));

			Date dateS = dataThru.get(h).getDateStrart();
			GregorianCalendar dateStartss = new GregorianCalendar();
			dateStartss.setTime(dateS);
			int Months = dateStartss.get(Calendar.MONTH) + 1;
			String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
					dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
			
			EntityTask thisTask = dataThru.get(h);
			VBox body_ = new VBox(50);
			Label body_Label = new Label();
			
			Label a_Label = new Label("��������: ");
			body_Label.setText(dataThru.get(h).getBode());
			body_Label.setWrapText(true);
			body_.getChildren().addAll(a_Label, body_Label);
			body_.setAlignment(Pos.CENTER);
			body_.setSpacing(5);
			body_.getStyleClass().add("OneStringBaze");
			
			VBox title_ = new VBox(50);
			Label t_Label = new Label("���������: ");
			Label title_Label = new Label();
			title_Label.setText(dataThru.get(h).getTitle());
			title_Label.setWrapText(true);
			title_.getChildren().addAll(t_Label,title_Label);
			title_.setAlignment(Pos.CENTER);
			title_.setSpacing(5);
			title_.getStyleClass().add("OneStringBaze");
			
			HBox mean = new HBox(50);
			mean.getChildren().addAll(title_,body_);
			mean.setAlignment(Pos.CENTER);
			mean.setSpacing(5);
			mean.getStyleClass().add("OneString");
			
			// �����
			Date dateE = dataThru.get(h).getDateEnd();
			GregorianCalendar dateEnds = new GregorianCalendar();
			dateEnds.setTime(dateE);
			Months = dateEnds.get(Calendar.MONTH) + 1;
			String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
					dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
			
			VBox dateST = new VBox(50);
			Label dateST_Label = new Label();
			Label datest_Label = new Label("���� � �����\n ������ ����������: ");
			datest_Label.setWrapText(true);
			dateST_Label.setText(DateSAllTs);
			dateST.getChildren().addAll(datest_Label, dateST_Label);
			dateST.setAlignment(Pos.CENTER);
			dateST.setSpacing(5);
			dateST.getStyleClass().add("OneStringBaze");
			
			VBox dateEn = new VBox(50);
			Label dateEn_Label = new Label();
			Label dateen_Label = new Label("���� � �����\n ��������� ����������: ");
			dateen_Label.setWrapText(true);
			dateEn_Label.setText(DateEAllT);
			dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
			dateEn.setAlignment(Pos.CENTER);
			dateEn.setSpacing(5);
			dateEn.getStyleClass().add("OneStringBaze");
			
			VBox _urgency = new VBox(50);
			Label _urgencyLabel = new Label("��������� ������: ");
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
			
			 Button inf = new Button("������ ���������");
			 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
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
				}
		}
		// ����� ����� ���������� ����� ���� ���� -- /
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
		fileChooser.setTitle("����� ������");
		List <File> files = fileChooser.showOpenMultipleDialog(primaryStage);
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
	public VBox getGivaenTasks(VBox givenTasks, String _name, String _secondName, String _lastName, Date Start) {
		LinkedList <EntityTask> data = new LinkedList <EntityTask>();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String StartDate = sdf.format(Start);
		// ������
		String query = "SELECT * FROM `taskmail`.`task` WHERE `task`.`supervisor` = '" + _secondName+" "+ _name+" "+ _lastName+ "' AND `task`.`create_date_time` LIKE '" + StartDate + "%';";
		ReadOunTasks ounTasks = new ReadOunTasks(_secondName+" "+ _name+" "+ _lastName);
		ounTasks.setSearchQuery(query);
		ounTasks.whatIs();
		data = ounTasks.getData();
		Label numberTasks = new Label(" ������ �����: " + data.size() );
		givenTasks.getChildren().add(numberTasks);
		
		for (int i =0; i < data.size(); i++) {
			System.out.println("������� ����� ����� ");
			EntityTask thisTask = data.get(i);
			VBox body_ = new VBox(50);
			Label body_Label = new Label();
			
			Label a_Label = new Label("��������: ");
			body_Label.setText(data.get(i).getBode());
			body_Label.setWrapText(true);
			body_.getChildren().addAll(a_Label, body_Label);
			body_.setAlignment(Pos.CENTER);
			body_.setSpacing(5);
			body_.getStyleClass().add("OneStringBaze");
			
			VBox title_ = new VBox(50);
			Label t_Label = new Label("���������: ");
			Label title_Label = new Label();
			title_Label.setWrapText(true);
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
			
			// �����
			Locale.setDefault(new Locale("ru","RU"));
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
			Label datest_Label = new Label("���� � ����� ������ ����������: ");
			datest_Label.setWrapText(true);
			dateST_Label.setText(DateSAllT);
			dateST.getChildren().addAll(datest_Label, dateST_Label);
			dateST.setAlignment(Pos.CENTER);
			dateST.setSpacing(5);
			dateST.getStyleClass().add("OneStringBaze");
			
			VBox dateEn = new VBox(50);
			Label dateEn_Label = new Label();
			Label dateen_Label = new Label("���� � ����� ��������� ����������: ");
			dateen_Label.setWrapText(true);
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
			
			 Button inf = new Button("������ ���������");
			 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
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
			name.setMaxWidth(500);
			
			givenTasks.getChildren().add(name);
			
		}
		return givenTasks;
	}

}
