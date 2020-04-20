package application.Scenes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import application.DB.GetInfoLogin;
import application.DB.ReadOneTask2;
import application.DB.ReadOunTasks;
import application.Entities.EntityTask;
import application.Mails.QuickLetterSend;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneOneWeekAndMonth implements mainWindowUser {
	
		//protected static final int SceneMainWindow getIdUser() = 0;
		private Stage primaryStage;
		private Scene ounScene;
		private Scene oldScene;
		private int id;
		
		
		public SceneOneWeekAndMonth(Stage primaryStage, int UserId) {
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
			// ����� ---- 
			Button exitFromProgram = new Button("�����");
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
					System.out.print("D����� �����");
					//primaryStage.setScene(firstScene.getScene());//,oldScene);
					
					primaryStage.setScene(oldScene);
					primaryStage.centerOnScreen();
				}
			});
			
			
			// ����� ----/
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
			 
			 int id_user = this.getIdUser();
			 System.out.println(" ���� ����� - " + id_user);
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
			 //TitledPane personalAccount= new TitledPane("������ �������",personalAccounts);
			 TitledPane  instrument = new TitledPane("�����������",instr);
			 TitledPane  exit = new TitledPane("����� ��� �����",exits);

			
		     accordion.getPanes().addAll(/*personalAccount,*/
		    		 instrument,exit);
		        accordion.setMinSize(250, 200);
		        accordion.setPrefSize(250, 200);
		    // ��������� ���� ----
		    // ����� ����� ��� ����� ---/
		    // ���� ��� ���� --/
		    
		    bottom .setSpacing(10);
		    bottom .setAlignment(Pos.CENTER_LEFT);
		    bottom .setId("bottom");
		    bottom.setMaxWidth(200);
		  
		    Label quick = new Label("������� ����������� ������ ");
		    quick.setWrapText(true);
			TextField emailThem = new TextField();
			//emailThem.setMinSize(250, 80);
			emailThem.setPromptText("������� ���� ���������");
			//TextField email = new TextField();
			TextArea email = new TextArea();
			email.setPrefRowCount(6);            
			
			email.setPromptText("������� ���������");
			//email.setMinSize(250, 100);
			Button adress = new Button("�������� ��������");
			//int idChoseUser = 0;
			TextField text = new TextField();
			adress.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						SceneChoseUser change = new SceneChoseUser(primaryStage,  id_user, idChosenUser);
						change.setId(id_user);
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
			//bottom.getChildren().
			/*push.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					int idch = 0;
					Scanner scanner = null;
					try {
						BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
						scanner = new Scanner(reader);
						int numberOfRows = 0;
						while (scanner.hasNext()) {
							idch = scanner.nextInt();
							//text.setText(Integer.toString(scanner.nextInt()));
							//System.out.println(" ����� �� �������� ���� - " + text.getText());
							System.out.println(" ����� �� �������� ���� - " + idch);
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
					
					QuickLetterSend sender = new QuickLetterSend();
					try {
						sender.senMail(emailThem.getText(), email.getText(),idch,id );
					} catch (AddressException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});*/
		    // ����� ����� ��� ���� 
		    TabPane tabpane=new TabPane();
		    
		    Tab tabSh = new Tab("���������� �� ������� ������");
		    tabSh.setClosable(false);
		    Group rootSh = new Group();
		    tabSh.setContent(rootSh);
		    
		    // ����� ����� ��� ���� 
		
		    Tab tabMany = new Tab("���������� �� �������� �������� �������");
		    tabMany.setClosable(false);
		    Group rootMany = new Group();
		    tabMany.setContent(rootMany);
		    
		    Tab tabDay = new Tab("�� ����");
		    tabDay.setClosable(false);
		    Group rootDay = new Group();
		    tabDay.setContent(rootDay);
		    // --- ���� ��� ������ ---/
		    HBox task = new HBox();
		    task.setSpacing(10);
		    task.setAlignment(Pos.CENTER_LEFT);
		    task.setId("Task");
		
			HBox _tasksAllDay = new HBox(50);
			//_all.setId("C");
			_tasksAllDay.setSpacing(10);
			_tasksAllDay.setAlignment(Pos.CENTER);
			Label _allLabel = new Label(" ���������� ");
			//_allLabel.setId("PULL");
			_tasksAllDay.getChildren().addAll(_allLabel);
			_tasksAllDay.setMinWidth(250);
			
			VBox _44 = new VBox(50);
			_44.setId("C");
			_44.setSpacing(10);
			_44.setAlignment(Pos.CENTER);
			Label _44Label = new Label(" ���������� ");
			_44 .setAlignment(Pos.CENTER);
			_44Label.setId("PULL");
			_44.getChildren().addAll(_44Label);
			_44.setMinWidth(250);

			ScrollPane all =new ScrollPane();
			all.setLayoutX(10);
			all.setLayoutY(10);
			all.setContent(_tasksAllDay);
			//spComment.setHmin(400);
			all.setCursor(Cursor.CLOSED_HAND);
			
			all.setMinWidth(250);
			
			VBox dateEnd = new VBox(50);
			Label dateEndLabel = new Label("������ ��������� �����: ");
			DatePicker datePickerEnd = new DatePicker();
			dateEnd.getChildren().addAll(dateEndLabel, datePickerEnd);
			dateEnd.setAlignment(Pos.CENTER);
			dateEnd.setSpacing(5);
			dateEnd.getStyleClass().add("EnterTask");
			
			VBox dateStart = new VBox(50);
			Label dateSartLabel = new Label("������ ������������ �� ����: ");
			DatePicker datePickerStart = new DatePicker();
			dateStart.getChildren().addAll(dateSartLabel, datePickerStart);
			dateStart.setAlignment(Pos.CENTER);
			dateStart.setSpacing(5);
			dateStart.getStyleClass().add("EnterTask");
			
			 Button whatTasks = new Button(" �������� ������ ");
			 whatTasks.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			 //inf.setMaxWidth(160);
			 whatTasks.setId("button");
			 whatTasks.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						_tasksAllDay.getChildren().clear();
						LocalDate starts = datePickerStart.getValue();
						LocalDate ends = datePickerEnd.getValue();
						System.out.println("���� ������- " + java.sql.Date.valueOf(starts));
						System.out.println("���� ����� - " + java.sql.Date.valueOf(ends));
						all.setContent(getWeek(_tasksAllDay, java.sql.Date.valueOf(starts), java.sql.Date.valueOf(ends)));
					}
				});
				
			 _44.getChildren().addAll(dateStart, dateEnd,whatTasks,all); 
			 
			 
			// ������� ������� �����... --- /
			// ����  -- //
		
			 // ������ ������ ��� ���������� �� ������� ������ -- /
			 HBox _week = new HBox(50);
				_week.setSpacing(10);
				_week.setAlignment(Pos.CENTER);
				Label _weekLabel = new Label(" ���������� �� ������");
				_week.getChildren().addAll(_weekLabel);
				_week.setMinWidth(250);
				
				VBox week = new VBox(50);
				//_all.setId("C");
				week.setSpacing(10);
				week.setAlignment(Pos.CENTER);
				//Label _allLabel = new Label(" ���������� ");
				//_allLabel.setId("PULL");
				week.getChildren().addAll(_weekLabel);
				week.setMinWidth(250);

				
				ScrollPane weekSc = new ScrollPane();
				weekSc.setLayoutX(10);
				weekSc.setLayoutY(10);
				//spComment.setHmin(400);
				weekSc.setCursor(Cursor.CLOSED_HAND);
				weekSc.setContent(_week);
				weekSc.setMinWidth(250);
				
				 Button buttonWeek = new Button(" �������� ������ �� ������� ������ ");
				 buttonWeek.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				 //inf.setMaxWidth(160);
				 buttonWeek.setId("button");
				 buttonWeek.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							Locale.setDefault(new Locale("ru","RU"));
							//week.getChildren().clear();
							Date start = getWeekStartDate();
							System.out.println("���� ������- " + start);
							Date end = getWeekEndDate();
							System.out.println("���� ����� - " + end);
							weekSc.setContent(getWeek(_week, start, end));
						
							//getTaskOnThisDay(_tasksAllDay);
							
						}
					});
				
				 week.getChildren().addAll(buttonWeek,weekSc); 
			 // ����� ������ ��� ���������� �� ������� ������ -- /
			// ����� ������� ������ -- /
				 //tabSh.setContent(_44);
			//rootMany.getChildren().add(_44);
			tabSh.setContent(week);
			tabMany.setContent(_44);
		    tabpane.getTabs().addAll(tabSh,tabMany);
		    
		    Label alarm = new Label(" �����������  ");
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
			Label aLlTaskWeekLabels = new Label("��� ������ �� ������");
			aLlTaskWeekLabels.setId("PULL");
			aLlTaskWeek.getChildren().add(aLlTaskWeekLabels);
			aLlTaskWeek.setMinWidth(250);
			
			ScrollPane scAll =new ScrollPane();
			scAll.setLayoutX(10);
			scAll.setLayoutY(10);
			//spComment.setHmin(400);
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
			System.out.println("���� ������- " + StartDate);
			Date end = getWeekEndDate();
			System.out.println("���� ����� - " + EndDate );
			String queryForBetweenDate = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
					+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
					+ "`user_task`.`id_user` = `users`.`id_user` "
					+ "WHERE `task`.`start_date_time` >= '" + StartDate + " 00:00:00" + 
					"' AND `start_date_time`  <= '" + EndDate + " 23:59:59" + "' AND `users`.`id_user` ='" + this.id + "'; ";
			System.out.println("���� ��� ������ - " + queryForBetweenDate);
			ReadOunTasks thisDayTasks = new ReadOunTasks();
			thisDayTasks.setSearchQuery(queryForBetweenDate);
			thisDayTasks.whatIs();
			dataWeek = thisDayTasks.getData();
			System.out.println("���������� ����� �� ������� - " + dataWeek.size());
			
			LinkedList <EntityTask> dataEndWeek = new LinkedList <EntityTask>();
			String queryForBetweenDateEnd = "SELECT * FROM (`taskmail`.`task` JOIN `taskmail`.`user_task` ON"
					+ " `task`.`id_task` = `user_task`.`id_task`) JOIN `taskmail`.`users` ON "
					+ "`user_task`.`id_user` = `users`.`id_user` "
					+ "WHERE `task`.`end_date_time` >= '" + StartDate + " 00:00:00" + 
					"' AND `task`.`end_date_time`  <= '" + EndDate+" 23:59:59" + "' AND `users`.`id_user` ='" + this.id + "'; ";
			System.out.println("���� ��� ������ - " + queryForBetweenDateEnd);
			
			ReadOneTask2 thisDayTasksAnd = new ReadOneTask2();
			thisDayTasksAnd.setSearchQuery(queryForBetweenDateEnd);
			thisDayTasksAnd.whatIs();
			dataEndWeek = thisDayTasksAnd.getData();
			System.out.println("���������� ����� �� ������� - " + dataEndWeek.size());
			
			for (int i =1; i <8; i++) {
				
				int numberTask = i+1;
				VBox allTasksforOneDay = new VBox(50);
				allTasksforOneDay.setId("C");
				allTasksforOneDay.setSpacing(10);
				allTasksforOneDay.setAlignment(Pos.CENTER);
				Label _01_TasksLabels = new Label("������ �� ���� - " + i);
				_01_TasksLabels.setId("PULL");
				allTasksforOneDay.getChildren().add(_01_TasksLabels);
				allTasksforOneDay.setMinWidth(250);
				
				ScrollPane ScroolForOneDay =new ScrollPane();
				ScroolForOneDay.setLayoutX(10);
				ScroolForOneDay.setLayoutY(10);
				//spComment.setHmin(400);
				ScroolForOneDay.setCursor(Cursor.CLOSED_HAND);
				ScroolForOneDay.setContent(allTasksforOneDay);
				ScroolForOneDay.setMinWidth(250);
				ScroolForOneDay.setId("sc");
				
				VBox DoneallTasksforOneDay = new VBox(50);
				DoneallTasksforOneDay.setId("C");
				DoneallTasksforOneDay.setSpacing(10);
				DoneallTasksforOneDay.setAlignment(Pos.CENTER);
				Label DoneTasks = new Label(" ��� ����������� ������ �� ���� ����: ");
				DoneTasks.setId("PULL");
				DoneallTasksforOneDay.getChildren().add(DoneTasks);
				DoneallTasksforOneDay.setMinWidth(250);
				
				VBox Done = new VBox(50);
				Done.setId("C");
				Done.setSpacing(10);
				Done.setAlignment(Pos.CENTER);
				Label DoneT = new Label(" ����������� ������: ");
				DoneT.setId("PULL");
				Done.getChildren().add(DoneT);
				Done.setMinWidth(250);
				
				//��������� ����� �������
				VBox TaskEndforOneHour = new VBox(50);
				TaskEndforOneHour.setId("C");
				TaskEndforOneHour.setSpacing(10);
				TaskEndforOneHour.setAlignment(Pos.CENTER);
				Label _endLabel1 = new Label(" ������ ���� ����������\n � ����� �������: ");
				_endLabel1.setTextFill(Color.web("#E0FFFF"));
				//_endLabel1.setId("PULL");
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
							_01_TasksLabels.setText(" ������ �� ����������: ");
							break;
						}
						case 1: {
							_01_TasksLabels.setText(" ������ �� �����������: ");
							break;
						}
						case 2: {
							_01_TasksLabels.setText(" ������ �� �������: ");
							break;
						}
						case 3: {
							_01_TasksLabels.setText(" ������ �� �����: ");
							break;
						}
						case 4: {
							_01_TasksLabels.setText(" ������ �� �������:");
							break;
						}
						case 5: {
							_01_TasksLabels.setText(" ������ �� �������: ");
							break;
						}
						case 6: {
							_01_TasksLabels.setText(" ������ �� �������: ");
							break;
						}
						case 7: {
							_01_TasksLabels.setText(" ������ �� ����������: ");
							break;
						}
						}
						
						System.out.println("���� ������ - " + (i) + " � �� ����- " + oneTaskDate.get(Calendar.DAY_OF_WEEK));
						EntityTask thisTask = dataWeek.get(j);
						//String name = Integer.toString(i);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("��������: ");
						body_Label.setText(dataWeek.get(j).getBode());
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("���������: ");
						Label title_Label = new Label();
						title_Label.setText(dataWeek.get(j).getTitle());
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
						Date dateE = dataWeek.get(j).getDateEnd();
						GregorianCalendar dateEnds = new GregorianCalendar();
						dateEnds.setTime(dateE);
						Months = dateEnds.get(Calendar.MONTH) + 1;
						String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
								dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
						
						
						VBox dateST = new VBox(50);
						Label dateST_Label = new Label();
						Label datest_Label = new Label("���� � �����\n ������ ����������: ");
						datest_Label.setWrapText(true);
						//dateST_Label.setText(data.get(i).getDateStrart().toString());
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("���� � �����\n ��������� ����������: ");
						dateen_Label.setWrapText(true);
						//dateEn_Label.setText(data.get(i).getDateEnd().toString());
						dateEn_Label.setText(DateEAllT);
						dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
						dateEn.setAlignment(Pos.CENTER);
						dateEn.setSpacing(5);
						dateEn.getStyleClass().add("OneStringBaze");
						
						VBox _urgency = new VBox(50);
						Label _urgencyLabel = new Label("��������� ������: ");
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
						 Button inf = new Button("������ ���������");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 //inf.setMaxWidth(160);
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
							Button dayTaskTo  = new Button("��������� � ������� ���");
							dayTaskTo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
							 //inf.setMaxWidth(160);
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
					//week.getChildren().add(ScroolForOneDay);
				}
				
				/// ����� ���������� ������ �� ������ ���� ������ -- /
				
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
						
						System.out.println("���� ������ - " + i + " � �� ����- " + oneTaskDate.get(Calendar.DAY_OF_WEEK));
						EntityTask thisTask = dataEndWeek .get(k);
						//String name = Integer.toString(i);
						VBox body_ = new VBox(50);
						Label body_Label = new Label();
						
						Label a_Label = new Label("��������: ");
						body_Label.setText(dataEndWeek .get(k).getBode());
						body_.getChildren().addAll(a_Label, body_Label);
						body_.setAlignment(Pos.CENTER);
						body_.setSpacing(5);
						body_.getStyleClass().add("OneStringBaze");
						
						VBox title_ = new VBox(50);
						Label t_Label = new Label("���������: ");
						Label title_Label = new Label();
						title_Label.setText(dataEndWeek .get(k).getTitle());
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
						Date dateE = dataEndWeek .get(k).getDateEnd();
						GregorianCalendar dateEnds = new GregorianCalendar();
						dateEnds.setTime(dateE);
						Months = dateEnds.get(Calendar.MONTH) + 1;
						String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Months + "."+ dateEnds.get(Calendar.YEAR) + " " +
								dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
						
						
						VBox dateST = new VBox(50);
						Label dateST_Label = new Label();
						Label datest_Label = new Label("���� � �����\n ������ ����������: ");
						datest_Label.setWrapText(true);
						//dateST_Label.setText(data.get(i).getDateStrart().toString());
						dateST_Label.setText(DateSAllTs);
						dateST.getChildren().addAll(datest_Label, dateST_Label);
						dateST.setAlignment(Pos.CENTER);
						dateST.setSpacing(5);
						dateST.getStyleClass().add("OneStringBaze");
						
						VBox dateEn = new VBox(50);
						Label dateEn_Label = new Label();
						Label dateen_Label = new Label("���� � �����\n ��������� ����������: ");
						dateen_Label.setWrapText(true);
						//dateEn_Label.setText(data.get(i).getDateEnd().toString());
						dateEn_Label.setText(DateEAllT);
						dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
						dateEn.setAlignment(Pos.CENTER);
						dateEn.setSpacing(5);
						dateEn.getStyleClass().add("OneStringBaze");
						
						VBox _urgency = new VBox(50);
						Label _urgencyLabel = new Label("��������� ������: ");
						Label _urgency_Label = new Label();
						_urgency_Label.setText(dataEndWeek .get(k).getUrgency());
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
						Date dayDate = dataWeek.get(k).getDateStrart();
						 Button inf = new Button("������ ���������");
						 inf.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
						 //inf.setMaxWidth(160);
						 inf.setId("button");
						 inf.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									
									SceneOneTaskForWorker t = new SceneOneTaskForWorker(primaryStage,id,thisTask);
								}
							});
						 Button dayTaskTo  = new Button("��������� � ������� ���");
						dayTaskTo.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
							 //inf.setMaxWidth(160);
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

					//week.getChildren().add(ScroolForOneDay);
				}
				
				/// ����� ����� ���������� ������ �� ������� ���� ������ -- /
				
				aLlTaskWeek.getChildren().addAll(ScroolForOneDay);
				
				/*if (allTasksforOneDay.getChildren().size() == 1) {
					aLlTaskWeek.getChildren().addAll(_01_TasksLabels);
				} else {
					aLlTaskWeek.getChildren().addAll(_01_TasksLabels,allTasksforOneDay);
				}*/
				if (DoneallTasksforOneDay.getChildren().size() > 1) {
					allTasksforOneDay.getChildren().add(DoneallTasksforOneDay);
				}
				if (Done.getChildren().size() > 1) {
					TaskEndforOneHour.getChildren().add(Done);
				}
				/*if (TaskEndforOneHour.getChildren().size() > 1) {
					allTasksforOneDay.getChildren().addAll(new Label("������, ������� ������\n ��������� � ����� ���."),alland);
				}*/
				Label endLabel = new Label("������, ������� �����\n ��������� � ����� ���.");
				endLabel.setId("PULL");
				allTasksforOneDay.getChildren().addAll(endLabel,alland);
				//allTasksforOneDay.getChildren().addAll(dayTaskTo);
			}
			//week.getChildren().add(ScroolForOneDay);
			return week;
		}
}
