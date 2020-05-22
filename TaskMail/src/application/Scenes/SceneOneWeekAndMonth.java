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
import application.Entities.EntityTask;
import application.StyleClasses.ButonStyle;
import application.StyleClasses.VboxStyle;
import application.interfaces.mainWindowUser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneOneWeekAndMonth implements mainWindowUser {
		private Stage primaryStage;
		private Scene ounScene;
		private Scene oldScene;
		private int id;
		
		public SceneOneWeekAndMonth(Stage primaryStage, int UserId) {
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
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getBounds();
			Scene scene = new Scene(border, bounds.getWidth(),  bounds.getHeight());
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
					//Login firstScene = new Login(primaryStage);
					//System.out.print("DВызов назад");
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
					//System.out.print("DВызов назад");
					//primaryStage.setScene(firstScene.getScene());//,oldScene);
					
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
			 
			 int id_user = this.getIdUser();
			 System.out.println(" айди юзера - " + id_user);
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
			 //TitledPane personalAccount= new TitledPane("Личный кабинет",personalAccounts);
		
			 TitledPane  exit = new TitledPane("Выход или назад",exits);
		     accordion.getPanes().addAll(exit);
		        accordion.setMinSize(250, 200);
		        accordion.setPrefSize(250, 200);
		    // Аккордион меню ----
		    // конец бокса для верха ---/
		    // Бокс для низа --/
		    
		    bottom .setSpacing(10);
		    bottom .setAlignment(Pos.CENTER_LEFT);
		    bottom .setId("bottom");
		    bottom.setMaxWidth(200);
		  
		    // Конец бокса для низа 
		    TabPane tabpane=new TabPane();
		    
		    Tab tabSh = new Tab("Расписание на текущую неделю");
		    tabSh.setClosable(false);
		    Group rootSh = new Group();
		    tabSh.setContent(rootSh);
		    
		    // Конец бокса для низа 
		
		    Tab tabMany = new Tab("Расписание на заданный интервал времени");
		    tabMany.setClosable(false);
		    Group rootMany = new Group();
		    tabMany.setContent(rootMany);
		    
		    Tab tabDay = new Tab("На день");
		    tabDay.setClosable(false);
		    Group rootDay = new Group();
		    tabDay.setContent(rootDay);
		    // --- Бокс для задачи ---/
		    HBox task = new HBox();
		    task.setSpacing(10);
		    task.setAlignment(Pos.CENTER_LEFT);
		    task.setId("Task");
		
			HBox _tasksAllDay = new HBox(50);
			_tasksAllDay.setSpacing(10);
			_tasksAllDay.setAlignment(Pos.CENTER);
			Label _allLabel = new Label(" Расписание ");
			_tasksAllDay.getChildren().addAll(_allLabel);
			_tasksAllDay.setMinWidth(250);
			
			VBox _44 = new VBox(50);
			_44.setId("C");
			_44.setSpacing(10);
			_44.setAlignment(Pos.CENTER);
			Label _44Label = new Label(" Расписание ");
			_44 .setAlignment(Pos.CENTER);
			_44Label.setId("PULL");
			_44.getChildren().addAll(_44Label);
			_44.setMinWidth(250);

			ScrollPane all =new ScrollPane();
			all.setLayoutX(10);
			all.setLayoutY(10);
			all.setContent(_tasksAllDay);
			all.setCursor(Cursor.CLOSED_HAND);
			
			all.setMinWidth(250);
			
			VBox dateEnd = new VBox(50);
			Label dateEndLabel = new Label("Задачи заканичая датой: ");
			DatePicker datePickerEnd = new DatePicker();
			datePickerEnd.setEditable(false);
			dateEnd.getChildren().addAll(dateEndLabel, datePickerEnd);
			dateEnd.setAlignment(Pos.CENTER);
			dateEnd.setSpacing(5);
			dateEnd.getStyleClass().add("EnterTask");
			
			VBox dateStart = new VBox(50);
			Label dateSartLabel = new Label("Задачи начинающиеся от даты: ");
			DatePicker datePickerStart = new DatePicker();
			datePickerStart.setEditable(false);
			dateStart.getChildren().addAll(dateSartLabel, datePickerStart);
			dateStart.setAlignment(Pos.CENTER);
			dateStart.setSpacing(5);
			dateStart.getStyleClass().add("EnterTask");
			
			 Button whatTasks = new Button(" Обновить задачи ");
			 whatTasks.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			 whatTasks.setId("button");
			 whatTasks.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						_tasksAllDay.getChildren().clear();
						LocalDate starts = datePickerStart.getValue();
						LocalDate ends = datePickerEnd.getValue();
						//System.out.println("Дата начала- " + java.sql.Date.valueOf(starts));
						//System.out.println("Дата конца - " + java.sql.Date.valueOf(ends));
						if (starts.isBefore(ends)) {
							all.setContent(getWeek(_tasksAllDay, java.sql.Date.valueOf(starts), java.sql.Date.valueOf(ends)));
						} else {
							all.setContent(getWeek(_tasksAllDay, java.sql.Date.valueOf(ends), java.sql.Date.valueOf(starts)));
						}
						
					}
				});
				
			 _44.getChildren().addAll(dateStart, dateEnd,whatTasks,all); 
			 
			 
			// Попытка создать циклы... --- /
			// дата  -- //
		
			 // Начало панели для расписания на текущую неделю -- /
			 HBox _week = new HBox(50);
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
				
				 Button buttonWeek = new Button(" Обновить задачи на текущую неделю ");
				 buttonWeek.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				 buttonWeek.setId("button");
				 buttonWeek.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							Locale.setDefault(new Locale("ru","RU"));
							//week.getChildren().clear();
							Date start = getWeekStartDate();
							//System.out.println("Дата начала- " + start);
							Date end = getWeekEndDate();
							//System.out.println("Дата конца - " + end);
							weekSc.setContent(getWeek(_week, start, end));
						}
					});
				
				 week.getChildren().addAll(buttonWeek,weekSc); 
			 // конец панели для расписания на текущую неделю -- /
			// Конец текущей задачи -- /
			tabSh.setContent(week);
			tabMany.setContent(_44);
		    tabpane.getTabs().addAll(tabSh,tabMany);
		    
		    Label alarm = new Label(" Приветствую  ");
		    HBox alarmBox= new HBox();
		    alarmBox.setId("BoxForAlarm");
		    alarmBox.getChildren().setAll(alarm,uiTimer);
		    double widthWindow = primaryStage.getScene().getWidth()*1.7;
	
			alarm.setId("alarm");
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
		
		public HBox getWeek(HBox week, Date Start, Date End) {
			Locale.setDefault(new Locale("ru","RU"));
			week.getChildren().clear();
			
			HBox aLlTaskWeek = new HBox(50);
			aLlTaskWeek.setId("C");
			aLlTaskWeek.setSpacing(10);
			aLlTaskWeek.setAlignment(Pos.CENTER);
			Label aLlTaskWeekLabels = new Label("Все задачи на неделю");
			aLlTaskWeekLabels.setId("PULL");
			aLlTaskWeek.getChildren().add(aLlTaskWeekLabels);
			aLlTaskWeek.setMinWidth(250);
			
			ScrollPane scAll =new ScrollPane();
			scAll.setLayoutX(10);
			scAll.setLayoutY(10);
			scAll.setCursor(Cursor.CLOSED_HAND);
			scAll.setContent(aLlTaskWeek);
			scAll.setMinWidth(250);
			
			week.getChildren().add(scAll);
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			String StartDate = sdf.format(Start);
			String EndDate =  sdf.format(End);
			
			LinkedList <EntityTask> dataWeek = new LinkedList <EntityTask>();
			/*String queryForWeek ="SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
					+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
					+ "`user_task`.`id_user` = `users`.`id_user` "
					+ "WHERE `task`.`start_date_time` LIKE '" + sqlDate + "%' AND `users`.`id_user` ='" + this.id + "'; ";*/
			Date start = getWeekStartDate();
			//System.out.println("Дата начала- " + StartDate);
			Date end = getWeekEndDate();
			//System.out.println("Дата конца - " + EndDate );
			String queryForBetweenDate = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
					+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
					+ "`user_task`.`id_user` = `users`.`id_user` "
					+ "WHERE `task`.`start_date_time` >= '" + StartDate + " 00:00:00" + 
					"' AND `start_date_time`  <= '" + EndDate + " 23:59:59" + "' AND `users`.`id_user` ='" + this.id + "'; ";
			//System.out.println("Дата для выбора - " + queryForBetweenDate);
			ReadOunTasks thisDayTasks = new ReadOunTasks();
			thisDayTasks.setSearchQuery(queryForBetweenDate);
			thisDayTasks.whatIs();
			dataWeek = thisDayTasks.getData();
			//System.out.println("количество задач на сегодня - " + dataWeek.size());
			
			LinkedList <EntityTask> dataEndWeek = new LinkedList <EntityTask>();
			String queryForBetweenDateEnd = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
					+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
					+ "`user_task`.`id_user` = `users`.`id_user` "
					+ "WHERE `task`.`end_date_time` >= '" + StartDate + " 00:00:00" + 
					"' AND `task`.`end_date_time`  <= '" + EndDate+" 23:59:59" + "' AND `users`.`id_user` ='" + this.id + "'; ";
			//System.out.println("Дата для выбора - " + queryForBetweenDateEnd);
			
			ReadOneTask2 thisDayTasksAnd = new ReadOneTask2();
			thisDayTasksAnd.setSearchQuery(queryForBetweenDateEnd);
			thisDayTasksAnd.whatIs();
			dataEndWeek = thisDayTasksAnd.getData();
			//System.out.println("количество задач на сегодня - " + dataEndWeek.size());
			
			for (int i =1; i <8; i++) {
				
				int numberTask = i+1;
				VBox allTasksforOneDay = new VBox(50);
				allTasksforOneDay.setId("C");
				allTasksforOneDay.setSpacing(10);
				allTasksforOneDay.setAlignment(Pos.CENTER);
				Label _01_TasksLabels = new Label("Задачи на день - " + i);
				_01_TasksLabels.setId("PULL");
				allTasksforOneDay.getChildren().add(_01_TasksLabels);
				allTasksforOneDay.setMinWidth(250);
				
				ScrollPane ScroolForOneDay =new ScrollPane();
				ScroolForOneDay.setLayoutX(10);
				ScroolForOneDay.setLayoutY(10);
				ScroolForOneDay.setCursor(Cursor.CLOSED_HAND);
				ScroolForOneDay.setContent(allTasksforOneDay);
				ScroolForOneDay.setMinWidth(250);
				ScroolForOneDay.setId("sc");
				
				VBox DoneallTasksforOneDay = new VBox(50);
				DoneallTasksforOneDay.setId("C");
				DoneallTasksforOneDay.setSpacing(10);
				DoneallTasksforOneDay.setAlignment(Pos.CENTER);
				Label DoneTasks = new Label(" Уже выполненные задачи на этот день: ");
				DoneTasks.setId("PULL");
				DoneallTasksforOneDay.getChildren().add(DoneTasks);
				DoneallTasksforOneDay.setMinWidth(250);
				
				VBox Done = new VBox(50);
				Done.setId("C");
				Done.setSpacing(10);
				Done.setAlignment(Pos.CENTER);
				Label DoneT = new Label(" Выполненные задачи: ");
				DoneT.setId("PULL");
				Done.getChildren().add(DoneT);
				Done.setMinWidth(250);
				
				//Окончание работ сегодня
				VBox TaskEndforOneHour = new VBox(50);
				TaskEndforOneHour.setId("C");
				TaskEndforOneHour.setSpacing(10);
				TaskEndforOneHour.setAlignment(Pos.CENTER);
				Label _end_label = new Label(" Окончание работ:");
				TaskEndforOneHour.getChildren().add(_end_label);
				Label _endLabel1 = new Label(" Работы надо законачить\n к этому времени: ");
				_endLabel1.setTextFill(Color.web("#E0FFFF"));
				_endLabel1.setWrapText(true);
				TaskEndforOneHour.setMinWidth(250);
				//
				ScrollPane alland =new ScrollPane();
				alland.setLayoutX(10);
				alland.setLayoutY(10);
				alland.setCursor(Cursor.CLOSED_HAND);
				alland.setContent(TaskEndforOneHour);
				alland.setMinWidth(250);
				
				for(int j=0; j < dataWeek.size(); j++) {
					
					Date dateS = dataWeek.get(j).getDateStrart();
					GregorianCalendar dateStartss = new GregorianCalendar();
					dateStartss.setTime(dateS);
					int Months = dateStartss.get(Calendar.MONTH) + 1;
					String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
							dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
					
					GregorianCalendar oneTaskDate = new GregorianCalendar();
					oneTaskDate.setTime(dataWeek.get(j).getDateStrart());
					
					if ((i == oneTaskDate.get(Calendar.DAY_OF_WEEK))) {
						
						int dayOfWeek = oneTaskDate.get(Calendar.DAY_OF_WEEK);
						switch(dayOfWeek-1) {
						case 0: {
							_01_TasksLabels.setText(" Задачи на воскреенье: ");
							break;
						}
						case 1: {
							_01_TasksLabels.setText(" Задачи на понедельник: ");
							break;
						}
						case 2: {
							_01_TasksLabels.setText(" Задачи на вторник: ");
							break;
						}
						case 3: {
							_01_TasksLabels.setText(" Задачи на среду: ");
							break;
						}
						case 4: {
							_01_TasksLabels.setText(" Задачи на четверг:");
							break;
						}
						case 5: {
							_01_TasksLabels.setText(" Задачи на пятницу: ");
							break;
						}
						case 6: {
							_01_TasksLabels.setText(" Задачи на субботу: ");
							break;
						}
						case 7: {
							_01_TasksLabels.setText(" Задачи на воскреенье: ");
							break;
						}
						}
						
						System.out.println("День недели - " + (i) + " и на день- " + oneTaskDate.get(Calendar.DAY_OF_WEEK));
						EntityTask thisTask = dataWeek.get(j);
						//String name = Integer.toString(i);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("Описание: ");
						body_Label.setText(dataWeek.get(j).getBode());
						body_Label.setWrapText(true);
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("Заголовок: ");
						Label title_Label = new Label();
						title_Label.setText(dataWeek.get(j).getTitle());
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
						
						// Время
						Date dateE = dataWeek.get(j).getDateEnd();
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
						_urgency_Label.setText(dataWeek.get(j).getUrgency());
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
						
						Date dayDate = dataWeek.get(j).getDateStrart();
						 Button inf = new Button("Узнать подробнее");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
							Button dayTaskTo  = new Button("Детальнее о задачах дня");
							dayTaskTo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
							dayTaskTo.setId("button");
							dayTaskTo.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent e) {
										SceneOneDayTask AllTasksday = new SceneOneDayTask(primaryStage,id,dayDate);
										//SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
									}
								});
						 
						VBox name = new VBox(50);
						name.getChildren().addAll( mean,dates,inf,dayTaskTo);
						name.setId("OneTask");
						name.setSpacing(10);
						name.setAlignment(Pos.CENTER);
						if(dataWeek .get(j).getIsDone() > 0) {
							DoneallTasksforOneDay.getChildren().add(name);
						} else {
							if(dataWeek.get(j).getIdUrgency() == 3) {
								allTasksforOneDay.getChildren().add(1,name);
							
							}else {
								allTasksforOneDay.getChildren().add(name);
							}
						}
					}
				}
				
				/// конец выполнения задачи на каждый день недели -- /
				
				for(int k=0; k < dataEndWeek .size(); k++) {
					
					Date dateS = dataEndWeek.get(k).getDateStrart();
					GregorianCalendar dateStartss = new GregorianCalendar();
					dateStartss.setTime(dateS);
					int Months = dateStartss.get(Calendar.MONTH) + 1;
					String DateSAllTs = " " + dateStartss.get(Calendar.DAY_OF_MONTH) + "." + Months +"."+ dateStartss.get(Calendar.YEAR) + " " +
							dateStartss.get(Calendar.HOUR_OF_DAY) + ":" + dateStartss.get(Calendar.MINUTE);
					
					GregorianCalendar oneTaskDate = new GregorianCalendar();
					oneTaskDate.setTime(dataEndWeek .get(k).getDateStrart());
					
					if ((i == oneTaskDate.get(Calendar.DAY_OF_WEEK))) {
						//System.out.println("День недели - " + i + " и на день- " + oneTaskDate.get(Calendar.DAY_OF_WEEK));
						EntityTask thisTask = dataEndWeek .get(k);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("Описание: ");
						body_Label.setText(dataEndWeek .get(k).getBode());
						body_Label.setWrapText(true);
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("Заголовок: ");
						Label title_Label = new Label();
						title_Label.setText(dataEndWeek .get(k).getTitle());
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
						
						// Время
						Date dateE = dataEndWeek .get(k).getDateEnd();
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
						_urgency_Label.setText(dataEndWeek.get(k).getUrgency());
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
						Date dayDate = dataEndWeek.get(k).getDateStrart();
						 Button inf = new Button("Узнать подробнее");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
						 Button dayTaskTo  = new Button("Детальнее о задачах дня");
						dayTaskTo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						dayTaskTo.setId("button");
						dayTaskTo.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent e) {
										SceneOneDayTask AllTasksday = new SceneOneDayTask(primaryStage,id,dayDate);
										//SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
									}
								});
						VBox name = new VBox(50);
						name.getChildren().addAll( mean,dates,inf,dayTaskTo);
						name.setId("OneTask");
						name.setSpacing(10);
						name.setAlignment(Pos.CENTER);
						
						if(dataEndWeek .get(k).getIsDone() > 0) {
							Done.getChildren().add(name);
						} else {
							if(dataEndWeek.get(k).getIdUrgency() == 3) {
								TaskEndforOneHour.getChildren().add(1,name);
							}else {
								TaskEndforOneHour.getChildren().add(name);
							}
						}
					}
				}
				
				/// Конец конца выполнения задачи на казждый день недели -- /
				
				aLlTaskWeek.getChildren().addAll(ScroolForOneDay);
				if (DoneallTasksforOneDay.getChildren().size() > 1) {
					allTasksforOneDay.getChildren().add(DoneallTasksforOneDay);
				}
				if (Done.getChildren().size() > 1) {
					TaskEndforOneHour.getChildren().add(Done);
				}
				Label endLabel = new Label("Задачи, которые нужно\n выполнить к этому дню.");
				endLabel.setId("PULL");
				allTasksforOneDay.getChildren().addAll(endLabel,alland);
			}
			return week;
		}
}
