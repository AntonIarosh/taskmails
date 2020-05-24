package application.Scenes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import application.DB.GetInfoLogin;
import application.DB.ReadOneTask2;
import application.DB.ReadOunTasks;
import application.DB.ReadOunTasks3;
import application.Entities.EntityTask;
import application.StyleClasses.ButonStyle;
import application.StyleClasses.VboxStyle;
import application.interfaces.mainWindowUser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneOneDayTask implements mainWindowUser{
		
			private Stage primaryStage;
			private Scene ounScene;
			private Scene oldScene;
			private int id;
			private Date oneDay;
			
			public SceneOneDayTask(Stage primaryStage, int UserId, Date _oneDay) {
				this.oneDay = _oneDay;
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
				Button exitFromProgram = new Button("Назад");
				Button exitFromLogin = new Button("Выйти из учётной записи");
				ButonStyle styles = new ButonStyle(exitFromProgram);
				exitFromProgram = styles.getStyleButton();
				styles.setButon(exitFromLogin);
				exitFromLogin = styles.getStyleButton();
				exitFromLogin.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						primaryStage.setScene(oldScene);
						primaryStage.centerOnScreen();
					}
				});
				exitFromProgram.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						primaryStage.setScene(oldScene);
						primaryStage.centerOnScreen();
					}
				});
				
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
				 
				 personalAccounts.getChildren().setAll(Namelabel,SecondNamelabel);
				 VboxStyle intr = new VboxStyle ();
				 VBox instr = intr.getStyleVbox();
				 personalAccounts.setId("pp");
				 personalAccounts.setMinWidth(250);
				 
				 
				 VBox bottom = new VBox();
				 instr.getChildren().setAll(popupContent,bottom);
				 VboxStyle ex = new VboxStyle ();
				 VBox exits = ex.getStyleVbox();
				
				 exits.getChildren().addAll(exitFromProgram,exitFromLogin);
				 exits.setId("exit");
				 TitledPane  instrument = new TitledPane("Инструменты",instr);
				 TitledPane  exit = new TitledPane("Выход или назад",exits);

			     accordion.getPanes().addAll(/*personalAccount,*/
			    		 instrument,exit);
			        accordion.setMinSize(250, 200);
			        accordion.setPrefSize(250, 200);
			    // Аккордион меню ----/
			    
			        // Бокс для инфо ---/
			        
			        // Конец бокса для иноф ---/
			        // Бокс для верха ---/
			    HBox top = new HBox();
			    top.setSpacing(10);
			    top.setAlignment(Pos.CENTER_LEFT);
			    top.setId("H");
			    personalAccounts.setSpacing(10);
			    personalAccounts.setAlignment(Pos.CENTER);
			    top.getChildren().addAll(personalAccounts);
			    // конец бокса для верха ---/
			    // Бокс для низа --/
			    
			    bottom .setSpacing(10);
			    bottom .setAlignment(Pos.CENTER_LEFT);
			    bottom .setId("bottom");
			    bottom.setMaxWidth(200);
			  
			    Label quick = new Label("Быстрое электронное письмо ");
			    quick.setWrapText(true);
				TextField emailThem = new TextField();
				emailThem.setPromptText("Введите тему сообщения");
				TextArea email = new TextArea();
				email.setPrefRowCount(6);            
				
				email.setPromptText("Введите сообщение");
				Button adress = new Button("Выберите адресата");
				adress.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							SceneChoseUser change = new SceneChoseUser(primaryStage,  id_user, idChosenUser);
							change.setId(id_user);
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

			    // Конец бокса для низа 
			    TabPane tabpane=new TabPane();
			    
			    Tab tabSh = new Tab("Расписание выбранный день");
			    tabSh.setClosable(false);
			    Group rootSh = new Group();
			    tabSh.setContent(rootSh);
			    
			    // Конец бокса для низа 
			    // --- Бокс для задачи ---/
			    HBox task = new HBox();
			    task.setSpacing(10);
			    task.setAlignment(Pos.CENTER_LEFT);
			    task.setId("Task");
			    
			    // --- Конец бокса для новой задачи ---/
				
				 // Начало панели для расписания на текущую неделю -- /
				 VBox _week = new VBox(50);
					_week.setSpacing(10);
					_week.setAlignment(Pos.CENTER);
					Label _weekLabel = new Label(" Расписание на неделю");
					_week.getChildren().addAll(_weekLabel);
					_week.setMinWidth(250);
					
					VBox week = new VBox(50);
					week.setSpacing(10);
					week.setAlignment(Pos.CENTER);
					week.getChildren().addAll(_weekLabel);
					week.setMinWidth(250);

					ScrollPane weekSc = new ScrollPane();
					weekSc.setLayoutX(10);
					weekSc.setLayoutY(10);
					weekSc.setCursor(Cursor.CLOSED_HAND);
					weekSc.setContent(_week);
					weekSc.setMinWidth(250);
					
					 Button buttonWeek = new Button(" Обновить задачи выбранный день ");
					 buttonWeek.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
					 buttonWeek.setId("button");
					 buttonWeek.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								Locale.setDefault(new Locale("ru","RU"));
								Date start = getWeekStartDate();
								System.out.println("Дата начала- " + start);
								Date end = getWeekEndDate();
								System.out.println("Дата конца - " + end);
								 getTasksOnThisDays (_week, oneDay);
							}
						});
					
					 week.getChildren().addAll(buttonWeek,weekSc); 
				 // конец панели для расписания на текущую неделю -- /
				// Конец текущей задачи -- /
				tabSh.setContent(week);
			    tabpane.getTabs().addAll(tabSh);
			    
			    Label alarm = new Label(" Приветствую  ");
			    HBox alarmBox= new HBox();
			    alarmBox.setId("BoxForAlarm");
			    alarmBox.getChildren().setAll(alarm,uiTimer);
		
				alarm.setId("alarm");
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
				
			public VBox getTasksOnThisDays (VBox _tasksAllDay, Date _oneDay) {
				LinkedList <EntityTask> data = new LinkedList <EntityTask>();
				LinkedList <EntityTask> dataEnd = new LinkedList <EntityTask>();
				Date thisDate = new Date();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				java.sql.Date sqlDate = new java.sql.Date(_oneDay.getTime());
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
					Label _end_label = new Label(" Окончание работ: ");
					TaskEndforOneHour.getChildren().add(_end_label);
					Label _endLabel1 = new Label(" Работы надо законачить\n к этому времени: ");
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
					// - Задачи проходящие через этот день -- /
					//Окончание работ сегодня
					
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
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("Дата и время\n окончания выполнения: ");
						dateen_Label.setWrapText(true);
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
							}
						}
						}
					}
					for (int f = 0; f < dataEnd.size(); f++ ) {
						int iterator = f+1;
						Label numbersTask = new Label(" Задание №" +iterator+" : ");
						System.out.println("Время - " + i + " Задача - " + dataEnd.get(f).getTitle() + " Время начала" + dataEnd.get(f).getDateStrart());
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
								// Вывод задач которые будут заканчиваться сегодня
								//System.out.println("Вывод задач которые будут заканчиваться сегодня " + dataEnd.size());
								EntityTask thisTask = dataEnd.get(f);
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
								dateST_Label.setText(DateSAllTs);
								dateST.getChildren().addAll(datest_Label, dateST_Label);
								dateST.setAlignment(Pos.CENTER);
								dateST.setSpacing(5);
								dateST.getStyleClass().add("OneStringBaze");
								
								VBox dateEn = new VBox(50);
								Label dateEn_Label = new Label();
								Label dateen_Label = new Label("Дата и время\n окончания выполнения: ");
								dateen_Label.setWrapText(true);
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
									}else {
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
				Locale.setDefault(new Locale("ru","RU"));
			    Calendar calendar = Calendar.getInstance();
			    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			        calendar.add(Calendar.DATE, -1);
			    }
			    return calendar.getTime();
			}

			public static Date getWeekEndDate() {
				Locale.setDefault(new Locale("ru","RU"));
			    Calendar calendar = Calendar.getInstance();
			    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			        calendar.add(Calendar.DATE, 1);
			    }
			    calendar.add(Calendar.DATE, -1);
			    return calendar.getTime();
			}		
}
