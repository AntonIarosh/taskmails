package application.Scenes;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneOneTask {
		
		private EntityTask oneTask;
		private Stage primaryStage;
		private Scene ounScene;
		private Scene oldScene;
		private AddUser add;
		private int Id;
		private int idposts;
		public SceneOneTask(Stage primaryStage, int _id, EntityTask _oneTask) {
			this.setOneTask(new EntityTask());
			this.setOneTask(_oneTask);
			add = null;
			this.Id = _id;
			System.out.println("����������� ����c - " + this.Id);
			System.out.println("��������  - " + _id);
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
			System.out.println("������ - " + this.Id);
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
			// ������ ��� ������ ������������ ---/
			VBox TaskInfo = new VBox();
			
			ScrollPane ScUsers =new ScrollPane();
			ScUsers.setLayoutX(10);
			ScUsers.setLayoutY(10);
			ScUsers .setCursor(Cursor.CLOSED_HAND);
			ScUsers.setMaxHeight(400);
			ScUsers.setMinWidth(300);
			ScUsers.setMinHeight(160);
			ScUsers.setStyle("-fx-alignment: center; -fx-background-color: #FFA500; -fx-padding: 2px;");
			
			VBox title = new VBox(50);
			Label titleLabel = new Label("��������� ������: ");
			titleLabel.setWrapText(true);
			titleLabel.setId("PULL");
			Label title_Label = new Label();
			title_Label.setText(_oneTask.getTitle());
			title_Label.setWrapText(true);
			title.getChildren().addAll(titleLabel, title_Label);
			title.setSpacing(5);
			title.getStyleClass().add("EnterUserData");
			
			VBox body_ = new VBox(50);
			Label body_Label = new Label();
			Label a_Label = new Label("�������� ������: ");
			a_Label.setWrapText(true);
			a_Label.setId("PULL");
			body_Label.setText(_oneTask.getBode());
			body_Label.setWrapText(true);
			body_.getChildren().addAll(a_Label, body_Label);
			body_.setAlignment(Pos.CENTER);
			body_.setSpacing(5);
			body_.getStyleClass().add("EnterUserData");
			
			VBox description = new VBox(50);
			Label descriptionLabel = new Label("�������������� ��������: ");
			descriptionLabel.setWrapText(true);
			descriptionLabel.setId("PULL");
			Label description_Label = new Label();
			description_Label.setText(_oneTask.getDescription());
			description_Label.setWrapText(true);
			description.getChildren().addAll(descriptionLabel, description_Label);
			description.setSpacing(5);
			description.getStyleClass().add("EnterUserData");
			
			VBox urgency = new VBox(50);
			Label urgencyLabel = new Label("��������� ����������: ");
			urgencyLabel.setWrapText(true);
			urgencyLabel.setId("PULL");
			Label urgency_Label = new Label();
			urgency_Label.setText(_oneTask.getUrgency());
			urgency_Label .setWrapText(true);
			urgency.getChildren().addAll(urgencyLabel, urgency_Label);
			urgency.setSpacing(5);
			urgency.getStyleClass().add("EnterUserData");
			
			HBox mean = new HBox(50);
			mean.getChildren().addAll(title,body_,description,urgency);
			mean.setId("Data");
			mean.setSpacing(10);
			mean.getStyleClass().add("Data");
			ScUsers.setContent(mean);
			
			VBox supervisor = new VBox(50);
			Label supervisorLabel = new Label("������������: ");
			supervisorLabel.setId("PULL");
			Label supervisor_Label = new Label();
			supervisor_Label.setText(_oneTask.getSupervisor());
			supervisor.getChildren().addAll(supervisorLabel, supervisor_Label);
			supervisor.setSpacing(5);
			supervisor.getStyleClass().add("EnterUserData"); 
			
			VBox taskcol = new VBox(50); // �������������
			Label taskcolLabel = new Label("������������: ");
			taskcolLabel.setId("PULL");
			Label taskcol_Label = new Label();
			taskcol_Label.setText(_oneTask.getTaskCol());
			taskcol.getChildren().addAll(taskcolLabel, taskcol_Label);
			taskcol.setSpacing(5);
			taskcol.getStyleClass().add("EnterUserData");
			
			VBox BoxDone = new VBox(50);
			Label isDoneLabel = new Label("������� � ���������� ������: ");
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
			Label idPrentLabel = new Label("��� ������������ ������: ");
			Label idPrent_Label = new Label();
			idPrentLabel.setId("PULL");
			int idTaskParen = _oneTask.getIdParent();
			if (idTaskParen !=0) {
				idPrent_Label.setText(Integer.toString(_oneTask.getIdParent()));
			}
			idPrent.getChildren().addAll(idPrentLabel, idPrent_Label);
			idPrent.setSpacing(5);
			idPrent.getStyleClass().add("EnterUserData");
			
			HBox about = new HBox(50);
			about .getChildren().addAll(supervisor,taskcol,BoxDone,idPrent);
			about .setId("Data");
			about .setSpacing(10);
			about .getStyleClass().add("Data");
			
			// --- 

			// �����
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
			Label datest_Label = new Label("���� � ����� ������ ����������: ");
			datest_Label.setId("PULL");
			datest_Label.setWrapText(true);
			dateST_Label.setText(DateSAllT);
			dateST.getChildren().addAll(datest_Label, dateST_Label);
			dateST.setAlignment(Pos.CENTER);
			dateST.setSpacing(5);
			dateST.getStyleClass().add("EnterUserData");
			
			VBox dateEn = new VBox(50);
			Label dateEn_Label = new Label();
			Label dateen_Label = new Label("���� � ����� ��������� ����������: ");
			 dateen_Label.setId("PULL");
			dateen_Label.setWrapText(true);
			dateEn_Label.setText(DateEAllT);
			dateEn.getChildren().addAll(dateen_Label,dateEn_Label);
			dateEn.setAlignment(Pos.CENTER);
			dateEn.setSpacing(5);
			dateEn.getStyleClass().add("EnterUserData");
			
			VBox dateCr = new VBox(50);
			Label dateC_Label = new Label();
			Label dateeC_Label = new Label("���� � ����� �������� ������: ");
			dateeC_Label.setId("PULL");
			dateen_Label.setWrapText(true);
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
			
			///-
			// �������� � ������ -- /
			LinkedList<EntityLink> dataLinks = new LinkedList<EntityLink>();
			LinkedList<EntityComment> dataComments = new LinkedList<EntityComment>();
			
			SearchCommemtsAndLinksTask search = new SearchCommemtsAndLinksTask();
			search.setIdTask(this.oneTask.getIdTask());
			search.whatIs();
			dataLinks = search.getDataLinks();
			dataComments = search.getDataComments();
			
			VBox givenComments = new VBox(50);
			givenComments.setId("C");
			givenComments.setSpacing(10);
			givenComments.setAlignment(Pos.CENTER);
			Label _Label = new Label(" �����������: ");
			_Label.setId("PULL");
			givenComments.getChildren().add(_Label);
			givenComments.setMinWidth(250);
			
			ScrollPane spComment =new ScrollPane();
			spComment.setLayoutX(10);
			spComment.setLayoutY(10);
			spComment.setCursor(Cursor.CLOSED_HAND);
			spComment.setContent(givenComments);
			spComment.setMinWidth(250);
			
			VBox givenLinks = new VBox(50);
			givenLinks.setId("C");
			givenLinks.setSpacing(10);
			givenLinks.setAlignment(Pos.CENTER);
			Label _LabelL = new Label(" ������: ");
			_LabelL.setId("PULL");
			givenLinks.getChildren().add(_LabelL);
			givenLinks.setMinWidth(250);
			
			ScrollPane spLink =new ScrollPane();
			spLink.setLayoutX(10);
			spLink.setLayoutY(10);
			spLink.setHmin(400);
			spLink.setCursor(Cursor.CLOSED_HAND);
			spLink.setContent(givenLinks);
			spLink.setMinWidth(250);
			
			for (int i =0; i < dataComments.size(); i++) {
				VBox comment = new VBox(50);
				comment.setMaxWidth(400);
				Label comment_Label = new Label();
				Label commentLabel = new Label("�����������: ");
				comment_Label.setText(dataComments.get(i).getComment());
				System.out.println (" ����������� -  " + dataComments.get(i).getComment() + " | " + comment_Label.getText());
				comment.getChildren().addAll(commentLabel, comment_Label);
				comment.setAlignment(Pos.CENTER);
				comment.setSpacing(5);
				comment.getStyleClass().add("String");
				comment.setMinHeight(100);
				comment.setMaxWidth(200);
				givenComments.getChildren().add(comment);
			}
			
			for (int i =0; i < dataLinks.size(); i++) {
				VBox link = new VBox(50);
				link.setMaxWidth(400);
				Label link_Label = new Label();
				Label linkLabel = new Label("������: ");
				link_Label.setText(dataLinks.get(i).getLink());
				System.out.println (" ������ -  " + dataLinks.get(i).getLink() );
				link.getChildren().addAll(linkLabel, link_Label);
				link.setAlignment(Pos.CENTER);
				link.setSpacing(5);
				link.getStyleClass().add("String");
				link.setMinHeight(100);
				link.setMaxWidth(200);
				givenLinks.getChildren().add(link);
			}
			// ����� ��������� � ������ -- /
			

			VBox userInfo = new VBox();
			userInfo.getChildren().setAll(ScUsers,about,dates/*,spComment,spLink*/);
			userInfo.setId("Info");
			userInfo.setSpacing(8);
			
			// --- ����� ������ ��� ������ ������������ ---/
			// ������ ��� ����� ������������ ---/


			// --- ����� ������ ��� ����� ������������---/

			// -- ������ ����� ---/
			Button buttonExit = new Button("�����");
			buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
			buttonExit.setId("button");
			buttonExit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.print("D����� �����");
					primaryStage.setScene(oldScene);
					primaryStage.centerOnScreen();
				}
			});
			// -- ����� ������ ����� --/
			
			// -- ������ �������� ������ ������������ --- /
	
			// -- ����� ������ �������� ������ ������������ --- /
		
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

}
