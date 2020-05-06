package application.Scenes;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import application.DB.AddTaskCommentLinkDone;
import application.DB.AddUser;
import application.DB.SearchCommemtsAndLinksTask;
import application.Entities.EntityComment;
import application.Entities.EntityLink;
import application.Entities.EntityTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneOneTaskForWorker {
			
			private EntityTask oneTask;
			private Stage primaryStage;
			private Scene ounScene;
			private Scene oldScene;
			private AddUser add;
			private int Id;
			private int idposts;
			private LinkedList<EntityLink> _dataLinks;
			private LinkedList<EntityComment> _dataComments;
			public SceneOneTaskForWorker (Stage primaryStage, int _id, EntityTask _oneTask) {
				this.setOneTask(new EntityTask());
				this.setOneTask(_oneTask);
				add = null;
				this.Id = _id;
				System.out.println("Конструктор айдиc - " + this.Id);
				System.out.println("передача  - " + _id);
				this.primaryStage = primaryStage;

				this.ounScene = createNewScene(this.oneTask);
				this.oldScene = primaryStage.getScene();
				setNewScene(this.primaryStage,this.ounScene); 
				

			}
			
			public void setNewScene(Stage primaryStage, Scene newScene) {
				primaryStage.close();
				primaryStage.setScene(newScene);
				primaryStage.show();
			}
			public int getIdUser() {
				System.out.println("геттер - " + this.Id);
				return this.Id;
			}
			public void setId(int _id) {
				this.Id = _id;
			}

			
			public Scene createNewScene(EntityTask _oneTask) {
				//FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
				VBox flowPane = new VBox(50);
				BorderPane roots = new BorderPane();
				roots.setId("flowPane");
				Screen screen = Screen.getPrimary();
				Rectangle2D bounds = screen.getBounds();
				Scene scene = new Scene(roots, bounds.getWidth(),  bounds.getHeight());
				//FlowPane root = new FlowPane(Orientation.HORIZONTAL);
				// Панель для данных пользователя ---/
				VBox TaskInfo = new VBox();
				
				ScrollPane ScUsers =new ScrollPane();
				ScUsers.setLayoutX(10);
				ScUsers.setLayoutY(10);
				//spComment.setHmin(400);
				ScUsers .setCursor(Cursor.CLOSED_HAND);
				ScUsers.setMaxHeight(400);
				ScUsers.setMinWidth(300);
				ScUsers.setMinHeight(160);
				ScUsers.setStyle("-fx-alignment: center; -fx-background-color: #FFA500; -fx-padding: 2px;");
			
				
				VBox title = new VBox(50);
				Label titleLabel = new Label("Заголовок задачи: ");
				titleLabel.setId("PULL");
				Label title_Label = new Label();
				title_Label.setText(_oneTask.getTitle());
				title_Label.setWrapText(true);
				title.getChildren().addAll(titleLabel, title_Label);
				//enternName.setAlignment(Pos.CENTER);
				title.setSpacing(5);
				title.getStyleClass().add("EnterUserData");
				
				VBox body_ = new VBox(50);
				Label body_Label = new Label();
				Label a_Label = new Label("Описание задачи: ");
				a_Label.setWrapText(true);
				a_Label.setId("PULL");
				body_Label.setText(_oneTask.getBode());
				body_Label.setWrapText(true);
				body_Label.setWrapText(true);
				body_.getChildren().addAll(a_Label, body_Label);
				body_.setAlignment(Pos.CENTER);
				body_.setSpacing(5);
				body_.getStyleClass().add("EnterUserData");
				
				VBox description = new VBox(50);
				Label descriptionLabel = new Label("Дополнительное описание: ");
				descriptionLabel.setWrapText(true);
				descriptionLabel.setId("PULL");
				Label description_Label = new Label();
				description_Label.setText(_oneTask.getDescription());
				description_Label.setWrapText(true);
				description.getChildren().addAll(descriptionLabel, description_Label);
				//enternFatherName.setAlignment(Pos.CENTER);
				description.setSpacing(5);
				description.getStyleClass().add("EnterUserData");
				
				VBox urgency = new VBox(50);
				Label urgencyLabel = new Label("Срочность выполнения: ");
				urgencyLabel.setWrapText(true);
				urgencyLabel.setId("PULL");
				Label urgency_Label = new Label();
				urgency_Label.setText(_oneTask.getUrgency());
				urgency_Label.setWrapText(true);
				urgency.getChildren().addAll(urgencyLabel, urgency_Label);
				//enternFatherName.setAlignment(Pos.CENTER);
				urgency.setSpacing(5);
				urgency.getStyleClass().add("EnterUserData");
				
				HBox mean = new HBox(50);
				mean.getChildren().addAll(title,body_,description,urgency);
				//mean.setAlignment(Pos.CENTER);
				mean.setId("Data");
				mean.setSpacing(10);
				//enterName.setAlignment(Pos.CENTER);
				mean.getStyleClass().add("Data");
				ScUsers.setContent(mean);
				
				VBox supervisor = new VBox(50);
				Label supervisorLabel = new Label("Руководитель: ");
				supervisorLabel.setId("PULL");
				Label supervisor_Label = new Label();
				supervisor_Label.setText(_oneTask.getSupervisor());
				supervisor.getChildren().addAll(supervisorLabel, supervisor_Label);
				//enternFatherName.setAlignment(Pos.CENTER);
				supervisor.setSpacing(5);
				supervisor.getStyleClass().add("EnterUserData"); 
				
				VBox taskcol = new VBox(50); // периодичность
				Label taskcolLabel = new Label("Периодичность: ");
				taskcolLabel.setId("PULL");
				Label taskcol_Label = new Label();
				taskcol_Label.setText(_oneTask.getTaskCol());
				taskcol.getChildren().addAll(taskcolLabel, taskcol_Label);
				//enternFatherName.setAlignment(Pos.CENTER);
				taskcol.setSpacing(5);
				taskcol.getStyleClass().add("EnterUserData");
				
				VBox BoxDone = new VBox(50);
				Label isDoneLabel = new Label("Отметка о выполнении задачи: ");
				isDoneLabel.setId("PULL");
				CheckBox isDone = new CheckBox();
				int isSelected = _oneTask.getIsDone();
				if (isSelected != 0) {
					isDone.setSelected(true);
				}
				
				BoxDone.getChildren().addAll(isDoneLabel,  isDone);
				BoxDone.setAlignment(Pos.CENTER);
				BoxDone.setSpacing(5);
				BoxDone.getStyleClass().add("EnterUserData");
				
				VBox idPrent = new VBox(50); 
				Label idPrentLabel = new Label("Код родительской задачи: ");
				Label idPrent_Label = new Label();
				idPrentLabel.setId("PULL");
				int idTaskParen = _oneTask.getIdParent();
				if (idTaskParen !=0) {
					idPrent_Label.setText(Integer.toString(_oneTask.getIdParent()));
				}
				idPrent.getChildren().addAll(idPrentLabel, idPrent_Label);
				//enternFatherName.setAlignment(Pos.CENTER);
				idPrent.setSpacing(5);
				idPrent.getStyleClass().add("EnterUserData");
				
				HBox about = new HBox(50);
				about .getChildren().addAll(supervisor,taskcol,BoxDone,idPrent);
				//mean.setAlignment(Pos.CENTER);
				about .setId("Data");
				about .setSpacing(10);
				//enterName.setAlignment(Pos.CENTER);
				about .getStyleClass().add("Data");
				
				
				// --- 

				
				// Время
				Locale.setDefault(new Locale("ru","RU"));
				Date dateC = _oneTask.getDateCreate();
				GregorianCalendar dateCreate = new GregorianCalendar();
				dateCreate.setTime(dateC);
				int Month = dateCreate.get(Calendar.MONTH) + 1;
				String DateSAllC = " " + dateCreate.get(Calendar.DAY_OF_MONTH) + "." + Month +"."+ dateCreate.get(Calendar.YEAR) + " " +
						dateCreate.get(Calendar.HOUR_OF_DAY) + ":" + dateCreate.get(Calendar.MINUTE);
				
				
				Date dateS = _oneTask.getDateStrart();
				GregorianCalendar dateStarts = new GregorianCalendar();
				dateStarts.setTime(dateS);
				Month = dateStarts.get(Calendar.MONTH) + 1 ;
				String DateSAllT = " " + dateStarts.get(Calendar.DAY_OF_MONTH) + "." + Month +"."+ dateStarts.get(Calendar.YEAR) + " " +
						dateStarts.get(Calendar.HOUR_OF_DAY) + ":" + dateStarts.get(Calendar.MINUTE);
				
				
				Date dateE = _oneTask.getDateEnd();
				GregorianCalendar dateEnds = new GregorianCalendar();
				dateEnds.setTime(dateE);
				Month =  dateEnds.get(Calendar.MONTH) + 1;
				String DateEAllT = dateEnds.get(Calendar.DAY_OF_MONTH) +"."+ Month +"."+ dateEnds.get(Calendar.YEAR) + " " +
						dateEnds.get(Calendar.HOUR_OF_DAY) + ":" + dateEnds.get(Calendar.MINUTE);
				
				
				
				
				
				VBox dateST = new VBox(50);
				Label dateST_Label = new Label();
				Label datest_Label = new Label("Дата и время начала выполнения: ");
				datest_Label.setId("PULL");
				datest_Label.setWrapText(true);
				//dateST_Label.setText(data.get(i).getDateStrart().toString());
				dateST_Label.setText(DateSAllT);
				dateST.getChildren().addAll(datest_Label, dateST_Label);
				dateST.setAlignment(Pos.CENTER);
				dateST.setSpacing(5);
				dateST.getStyleClass().add("EnterUserData");
				
				VBox dateEn = new VBox(50);
				Label dateEn_Label = new Label();
				Label dateen_Label = new Label("Дата и время окончания выполнения: ");
				 dateen_Label.setId("PULL");
				dateen_Label.setWrapText(true);
				//dateEn_Label.setText(data.get(i).getDateEnd().toString());
				dateEn_Label.setText(DateEAllT);
				dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
				dateEn.setAlignment(Pos.CENTER);
				dateEn.setSpacing(5);
				dateEn.getStyleClass().add("EnterUserData");
				
				VBox dateCr = new VBox(50);
				Label dateC_Label = new Label();
				Label dateeC_Label = new Label("Дата и время создания задачи: ");
				dateeC_Label.setId("PULL");
				dateen_Label.setWrapText(true);
				//dateEn_Label.setText(data.get(i).getDateEnd().toString());
				dateC_Label.setText(DateSAllC);
				dateCr .getChildren().addAll(dateeC_Label,dateC_Label);
				dateCr .setAlignment(Pos.CENTER);
				dateCr .setSpacing(5);
				dateCr .getStyleClass().add("EnterUserData");
				
				HBox dates = new HBox(50);
				dates.getChildren().addAll(dateST,dateEn,dateCr);
				dates.setAlignment(Pos.CENTER);
				dates.setSpacing(5);
				dates.getStyleClass().add("Data");
				dates.setId("Data");
				
				///-
				// кооменты и ссылки -- /
				LinkedList<EntityLink> dataLinks = new LinkedList<EntityLink>();
				LinkedList<EntityComment> dataComments = new LinkedList<EntityComment>();
				
				SearchCommemtsAndLinksTask search = new SearchCommemtsAndLinksTask();
				search.setIdTask(this.oneTask.getIdTask());
				search.whatIs();
				dataLinks = search.getDataLinks();
				dataComments = search.getDataComments();
				this.set_dataComments(dataComments);
				this.set_dataLinks(dataLinks);
				
				VBox givenComments = new VBox(50);
				givenComments.setId("C");
				givenComments.setSpacing(10);
				givenComments.setAlignment(Pos.CENTER);
				Label _Label = new Label(" Комментарии: ");
				_Label.setId("PULL");
				givenComments.getChildren().add(_Label);
				givenComments.setMinWidth(250);
				//givenComments.getStyleClass().add("Data");
				
				ScrollPane spComment =new ScrollPane();
				spComment.setLayoutX(10);
				spComment.setLayoutY(10);
				//spComment.setHmin(400);
				spComment.setCursor(Cursor.CLOSED_HAND);
				spComment.setContent(givenComments);
				spComment.setMinWidth(250);
				
				VBox givenLinks = new VBox(50);
				givenLinks.setId("C");
				givenLinks.setSpacing(10);
				givenLinks.setAlignment(Pos.CENTER);
				Label _LabelL = new Label(" Ссылки: ");
				_LabelL.setId("PULL");
				givenLinks.getChildren().add(_LabelL);
				givenLinks.setMinWidth(250);
				//givenLinks.getStyleClass().add("Data");
				
				ScrollPane spLink =new ScrollPane();
				spLink.setLayoutX(10);
				spLink.setLayoutY(10);
				spLink.setHmin(400);
				spLink.setCursor(Cursor.CLOSED_HAND);
				spLink.setContent(givenLinks);
				spLink.setMinWidth(250);
				//spLink.setMinHeight(200);
				
				for (int i =0; i < dataComments.size(); i++) {
					System.out.println("Начался вывод комментариев  - " + dataComments.size());
					//EntityComment thisComment = dataComments.get(i);
					//String name = Integer.toString(i);
					
					VBox comment = new VBox(50);
					comment.setMaxWidth(400);
					Label comment_Label = new Label();
					comment_Label.setWrapText(true);
					Label commentLabel = new Label("Комментарий: ");
					comment_Label.setText(dataComments.get(i).getComment());
					System.out.println (" Комментарий -  " + dataComments.get(i).getComment() + " | " + comment_Label.getText());
					comment.getChildren().addAll(commentLabel, comment_Label);
					comment.setAlignment(Pos.CENTER);
					comment.setSpacing(5);
					comment.getStyleClass().add("String");
					comment.setMinHeight(100);
					comment.setMaxWidth(200);
					
					givenComments.getChildren().add(comment);
				}
				
				for (int i =0; i < dataLinks.size(); i++) {
					System.out.println("Начался вывод ссылок - " + dataLinks.size());
					//EntityComment thisComment = dataComments.get(i);
					//String name = Integer.toString(i);
					
					VBox link = new VBox(50);
					link.setMaxWidth(400);
					Label link_Label = new Label();
					Label linkLabel = new Label("Ссылка: ");
					link_Label.setText(dataLinks.get(i).getLink());
					link_Label.setWrapText(true);
					System.out.println (" ссылка -  " + dataLinks.get(i).getLink() );
					link.getChildren().addAll(linkLabel, link_Label);
					link.setAlignment(Pos.CENTER);
					link.setSpacing(5);
					link.getStyleClass().add("String");
					link.setMinHeight(100);
					link.setMaxWidth(200);
					givenLinks.getChildren().add(link);
				}
				// конец комментов и ссылок -- /
				
				VBox addOnenComment = new VBox(50);
				Label addOnenCommentLabel = new Label("Комментарий");
				addOnenCommentLabel.setId("PULL");
				TextArea bodyaddOnenComment = new TextArea();
				bodyaddOnenComment.setOnKeyPressed(event-> {
					int num = 357 - bodyaddOnenComment.getText().length();
					addOnenCommentLabel.setText("Комментарий. Осталось символов: " + num);
					if(bodyaddOnenComment.getText().length() > 357) {
						
						bodyaddOnenComment.setEditable(false);
					}
					if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
						
						bodyaddOnenComment.setEditable(true);
					}
				});

				bodyaddOnenComment.setPromptText("Введите новый комментарий");
				bodyaddOnenComment.setPrefRowCount(10); 
				bodyaddOnenComment.setWrapText(true);
				addOnenComment.getChildren().addAll(addOnenCommentLabel, bodyaddOnenComment);
				addOnenComment.setAlignment(Pos.CENTER);
				addOnenComment.setSpacing(5);
				addOnenComment.getStyleClass().add("EnterUserData");

				
				
				VBox addOneLink = new VBox(50);
				Label addOneLinkLabel = new Label("Ссылка");
				addOneLinkLabel.setId("PULL");
				TextArea bodyMailaddOneLink = new TextArea();
				bodyMailaddOneLink.setPromptText("Введите новую ссылку");
				bodyMailaddOneLink.setPrefRowCount(10); 
				bodyMailaddOneLink.setWrapText(true);
				bodyMailaddOneLink.setOnKeyPressed(event-> {
					int num = 552 - bodyMailaddOneLink.getText().length();
					addOneLinkLabel.setText("Ссылка. Осталось символов: " + num);
					if(bodyMailaddOneLink.getText().length() > 552) {
						
						bodyMailaddOneLink.setEditable(false);
					}
					if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
						
						bodyMailaddOneLink.setEditable(true);
					}
				});
				addOneLink.getChildren().addAll(addOneLinkLabel, bodyMailaddOneLink);
				addOneLink.setAlignment(Pos.CENTER);
				addOneLink.setSpacing(5);
				addOneLink.getStyleClass().add("EnterUserData");

	
				
				HBox adding = new HBox(50);
				adding.getChildren().addAll(addOnenComment,addOneLink);
				//mean.setAlignment(Pos.CENTER);
				adding.setId("Data");
				adding.setSpacing(10);
				//enterName.setAlignment(Pos.CENTER);
				adding.getStyleClass().add("Data");

				
				// --- конец панели для данных пользователя ---/
				// Панель для эмэйл пользователя ---/

				
				// --- конец панели для эмэйл пользователя---/

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
				
				// -- Кнопка обновить данные пользователя --- /
				Button addComment = new Button("Добавить комментарий");
				addComment.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				addComment.setId("button");
				addComment.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						// дата создания задачи -- /
						Date dt = new Date();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						String currentTime = sdf.format(dt);
						// конец даты создания задачи -- /
						String commentText = "Дата добавления: " + currentTime +", " +bodyaddOnenComment.getText();
						AddTaskCommentLinkDone madeAddChange = new AddTaskCommentLinkDone();
						String queryAddComment = "INSERT INTO `taskmail`.`task_comment` (`task_comment`.`id_task`, `task_comment`.`comment`) VALUES ('" + 
								_oneTask.getIdTask() + "','" + /*bodyaddOnenComment.getText()*/commentText  +"')";
						System.out.print(queryAddComment);
						madeAddChange.setQuery(queryAddComment);
						madeAddChange.execeteQuery();
						// Сообщение об успехе -- /
						Alert alert = new Alert(AlertType.INFORMATION,"Давление комментария к этой задаче, а также сохранение её в Вашей базе данных");
						alert.setTitle("Добавление комментария");
						alert.setHeaderText("Комментарий добавлен!");
						alert.show();
						// Коенец сообщение об успехе -- /
						
						updateCommit (givenComments,_dataComments);
					}
				});
				Button addLink = new Button("Добавить ссылку");
				addLink.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				addLink.setId("button");
				addLink.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						// дата создания задачи -- /
						Date dt = new Date();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						String currentTime = sdf.format(dt);
						// конец даты создания задачи -- /
						String linkText = "Дата добавления: " + currentTime +", " +bodyMailaddOneLink.getText();
						AddTaskCommentLinkDone madeAddChange = new AddTaskCommentLinkDone();
						String queryAddLink = "INSERT INTO `taskmail`.`task_link` (`task_link`.`link`, `task_link`.`id_task`) VALUES ('" +
								/*bodyMailaddOneLink.getText()*/linkText  + "','" + _oneTask.getIdTask() +"')";
						System.out.print(queryAddLink);
						madeAddChange.setQuery(queryAddLink);
						madeAddChange.execeteQuery();
						// Сообщение об успехе -- /
						Alert alert = new Alert(AlertType.INFORMATION,"Давление ссылки к этой задаче, а также сохранение её в Вашей базе данных");
						alert.setTitle("Добавление ссылки");
						alert.setHeaderText("Ссылка добавлена!");
						alert.show();
						// Коенец сообщение об успехе -- /
						
						 updateLink (givenLinks, _dataLinks);
					}
				});
				Button done = new Button("Отметить выполнение");
				done.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				done.setId("button");
				done.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						// дата  задачи -- /
						Date dt = new Date();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String currentTime = sdf.format(dt);
						// конец задачи -- /
						AddTaskCommentLinkDone madeAddChange = new AddTaskCommentLinkDone();
						String queryUpdateTask = "UPDATE `taskmail`.`task` SET `task`.`is_done` = '1' WHERE `task`.`id_task` = '" +_oneTask.getIdTask() + "'";
						madeAddChange.setQuery(queryUpdateTask);
						madeAddChange.execeteQuery();
						bodyaddOnenComment.setText("Задание выполнено в: " + currentTime);
						_oneTask.setIsDone(1);
						isDone.setSelected(true);
						
						// Сообщение об успехе -- /
						Alert alert = new Alert(AlertType.INFORMATION,"Задание было обновлено, а также сохранено в Вашей базе данных");
						alert.setTitle("Задание выполнено");
						alert.setHeaderText("Отметка о выполнении!");
						alert.show();
						addComment.fire();
						// Коенец сообщение об успехе -- /
					}
				});
				Button report = new Button("Отпаравить отчёт");
				report.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
				report.setId("button");
				report.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						SceneReport send = new SceneReport (primaryStage, Id , _oneTask, _dataLinks, _dataComments);
		
					}
				});
				
				HBox buttons = new HBox(50);
				buttons.getChildren().addAll(addComment,done,report,addLink );
				//mean.setAlignment(Pos.CENTER);
				//buttons.setId("Data");
				buttons.setSpacing(10);
				buttons.setAlignment(Pos.CENTER);
				buttons.getStyleClass().add("Data");
				// -- Конец кнопки обновить данные пользователя --- /
			
				VBox userInfo = new VBox();
				userInfo.getChildren().setAll(ScUsers,about,dates/*,spComment,spLink*/,buttons,adding  );
				userInfo.setId("Info");
				userInfo.setSpacing(8);
				
				HBox root = new HBox(50);
				root.getChildren().setAll(/*userInfo,emails*/buttonExit);
				root.setAlignment(Pos.CENTER);
				root.setSpacing(20);
				root.setId("root");
				flowPane.getChildren().setAll(root);
				
				roots.setCenter(userInfo);
				roots.setLeft(spComment);
				roots.setRight(spLink);
				roots.setBottom(root);
				
				flowPane.setAlignment(Pos.CENTER);
				flowPane.setId("flowPane");

				scene.getStylesheets().add(getClass().getResource("/application/styles/changeDataemails.css").toExternalForm());
				return scene;
			}

			public EntityTask getOneTask() {
				return oneTask;
			}

			public void setOneTask(EntityTask oneTask) {
				this.oneTask = oneTask;
			}

			public LinkedList<EntityLink> get_dataLinks() {
				return _dataLinks;
			}

			public void set_dataLinks(LinkedList<EntityLink> _dataLinks) {
				this._dataLinks = _dataLinks;
			}

			public LinkedList<EntityComment> get_dataComments() {
				return _dataComments;
			}

			public void set_dataComments(LinkedList<EntityComment> _dataComments) {
				this._dataComments = _dataComments;
			}
			public VBox updateCommit (VBox _commit, LinkedList<EntityComment> dataComments) {
				
				_commit.getChildren().clear();
				Label _Label = new Label(" Комментарии: ");
				_commit.getChildren().add(_Label);
				
				SearchCommemtsAndLinksTask search = new SearchCommemtsAndLinksTask();
				search.setIdTask(this.oneTask.getIdTask());
				search.whatIs();

				
				dataComments = search.getDataComments();
				this.set_dataComments(dataComments);

				
				for (int i =0; i < dataComments.size(); i++) {
					System.out.println("Начался вывод комментариев  - " + dataComments.size());
					//EntityComment thisComment = dataComments.get(i);
					//String name = Integer.toString(i);
					
					VBox comment = new VBox(50);
					comment.setMaxWidth(400);
					Label comment_Label = new Label();
					comment_Label.setWrapText(true);
					Label commentLabel = new Label("Комментарий: ");
					comment_Label.setText(dataComments.get(i).getComment());
					System.out.println (" Комментарий -  " + dataComments.get(i).getComment() + " | " + comment_Label.getText());
					comment.getChildren().addAll(commentLabel, comment_Label);
					comment.setAlignment(Pos.CENTER);
					comment.setSpacing(5);
					comment.getStyleClass().add("String");
					comment.setMinHeight(100);
					comment.setMaxWidth(200);
					
					_commit.getChildren().add(comment);
				}
				
				return _commit;
			}

			public VBox updateLink (VBox _link, LinkedList<EntityLink> dataLinks) {
				
				_link.getChildren().clear();
				Label _LabelL = new Label(" Ссылки: ");
				_link.getChildren().add(_LabelL);
				
				SearchCommemtsAndLinksTask search = new SearchCommemtsAndLinksTask();
				search.setIdTask(this.oneTask.getIdTask());
				search.whatIs();
				dataLinks = search.getDataLinks();

				this.set_dataLinks(dataLinks);
				
				for (int i =0; i < dataLinks.size(); i++) {
					System.out.println("Начался вывод ссылок - " + dataLinks.size());
					//EntityComment thisComment = dataComments.get(i);
					//String name = Integer.toString(i);
					
					VBox link = new VBox(50);
					link.setMaxWidth(400);
					Label link_Label = new Label();
					Label linkLabel = new Label("Ссылка: ");
					link_Label.setText(dataLinks.get(i).getLink());
					link_Label.setWrapText(true);
					System.out.println (" ссылка -  " + dataLinks.get(i).getLink() );
					link.getChildren().addAll(linkLabel, link_Label);
					link.setAlignment(Pos.CENTER);
					link.setSpacing(5);
					link.getStyleClass().add("String");
					link.setMinHeight(100);
					link.setMaxWidth(200);
					_link.getChildren().add(link);
				}
				
				return _link;
			}
	
}
