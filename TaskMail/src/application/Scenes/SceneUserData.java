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
		Label enterLNameLabel = new Label("������� ���");
		TextField textName = new TextField();
		textName.setPromptText("���");
		enternName.getChildren().addAll(enterLNameLabel, textName);
		enternName.setAlignment(Pos.CENTER);
		enternName.setSpacing(5);
		enternName.getStyleClass().add("EnterUserData");
		
		VBox enternSecondName = new VBox(50);
		Label enterSecondNameLabel = new Label("������� �������");
		TextField textSeconName = new TextField();
		textSeconName.setPromptText("�������");
		enternSecondName.getChildren().addAll(enterSecondNameLabel, textSeconName);
		enternSecondName.setAlignment(Pos.CENTER);
		enternSecondName.setSpacing(5);
		enternSecondName.getStyleClass().add("EnterUserData");
		
		VBox enternFatherName = new VBox(50);
		Label enterFatherNameLabel = new Label("������� ��������");
		TextField textFatherName = new TextField();
		textFatherName.setPromptText("��������");
		enternFatherName.getChildren().addAll(enterFatherNameLabel, textFatherName);
		enternFatherName.setAlignment(Pos.CENTER);
		enternFatherName.setSpacing(5);
		enternFatherName.getStyleClass().add("EnterUserData");
		
		VBox enterLogin = new VBox(50);
		Label enterLoginLabel = new Label("������� �����");
		TextField textLogin = new TextField();
		textLogin.setPromptText("�����");
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
		Label enterLoginPass = new Label("������� ������");
		Label enterRule = new Label("������ ������ ��������� ����� ���������� �������� � �� ������ ����� �����");
		enterRule.setMaxWidth(250);
		enterRule.setWrapText(true);
		enterRule.setMinHeight(85);
		TextField pass = new TextField();
		pass.setPromptText("������� ������");
		enterPass.getChildren().addAll(enterLoginPass, pass,enterRule);
		enterPass.setAlignment(Pos.CENTER);
		enterPass.setSpacing(5);
		enterPass.getStyleClass().add("EnterUserData");
		
		HBox enterJob = new HBox(50);
		Label enterJobLabel = new Label("���� � ���, �� �������");
		Label lookJobLabel = new Label("������ ���� ���������");
		TextField job= new TextField();
		job.setPromptText("���������");
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
		Button readData = new Button("������������");
		readData.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		readData.setId("button");
		readData.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Dialog <ButtonType> dialog = new Dialog <ButtonType>();
				dialog.setTitle("��������� ������������ ������");
				dialog.setHeaderText("�� ���� �������� �� ���!");
				//dialog.setContentText
				ScrollPane sp=new ScrollPane();
				//dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		 
		        final DialogPane dialogPane = dialog.getDialogPane();
		        dialogPane.setContentText("��������� �������� ��������� ������������ ������ ����� 1, 5 ������ 1 ������ 6 ������������ ������ �� �� ������������ �������." + 
						"\r\n" + 
						"2. �������� ������������ �� ��������� ������������ ������\r\n" + 
						"��������� ��������� ����� ����� �������� �����, ����������� �� �����, ������������ ����� �������� ���� �������� �� ��������� ������������ ������ � ������������ ��������� ��������� ����� � ������." + 
						"" + 
						"������������ ����� ����������� �� ��������� �������� �� ����������� �����, ���� ����� ����������� ���������������. �������� ����� ��������� �������� � ��������, ������������� ����������, ������������, ���������� ���������, � ���� ��������. �������� ���� �e-mail�, ������������ ���� ���� �������� �� ��������� ����� ��������. ������������ � ����� ������ ����� �������� ���� �������� �� ��������� ��������. ����������� ���������� �� �������� ��������������� ������������ � ������ ������." + 
						"\r\n" + 
						"3. ���� ��������� ������������ ������\r\n" + 
						"����� ��������� ������������ ������ �������� �������������� ������������ ���������� � ��������, � ��� ����� ������� ���������� ���������, �������� ��������� � �. �. ���� ������������ ����� ������ ������������� ����, �� ������������ ������ �� ��������������." + 
						"\r\n" + 
						"4. ����� �������������� ������������ ������\r\n" + 
						"� ��������� ������������ ����� ������� ��������� ������������ ������: �������, ���, ��������, ����� ����������� �����." + 
						"" + 
						"\r\n" + 
						"5. ������������������ ������������ ������\r\n" + 
						"�������� �� ���������� ������� ����� � �� �������������� ������������ ������ ������������� ��� �� ��������, ����� �������, ��������������� ����������� �������.\r\n" + 
						"\r\n" + 
						"6. ���� ��������� ������������ ������\r\n" + 
						"��������������� ������������� ������ �������������� ���������. ��������� ������������ ������ ������������ � ������ ������ ������������� �������� �� ��������� ������������ ������ ���� �������� ��� ������� ������." + 
						"\r\n" + 
						"7. ����� �������� ������������ ������" + 
						"������� ������������ ������ ������ ��������� ������ �������������� ����� �� ��������� ����������, ���������� ��������� ��� ������������ ������ � ������������ � ������������ ������ 14 ������������ ������ �� �� ������������ �������." + 
						"\r\n" + 
						"������ ������ ����� ���� ��������� � ������� ���������.\r\n" + 
						"\r\n" + 
						"8. ������ ������������ ������\r\n" + 
						"�������� ��������� ����, ����������� � ����������� ��� ����������� ���������� ������������, ��������������� ����������� ������� �� ������������ ������� � ��������� � ������������ � ��� ������������ ��������� ������. �������� �������������� ���������� ������ � �������� ���, ����������� � ����������� ��� ����������� ���������� ����� ������������." + 
						"\r\n" + 
						"������ � ������������ ������ ����� ������ �������������� ���������� ��������. ��� ���������� ��������, ������� ������ � ������������ ������, ������ �������������� �������� �� ����������� ������������������ � ������ ������������ ������. � ����� ����������� ������������������ ���������� � ������ ������������ ������ �������� ������������ ��������������� ��-����� � ��������� ��� ����, ����������� ��� �������������� �������������������� �������");;
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
		Button Enter = new Button("�����������");
		Enter.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		Enter.setId("button");
		Enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				alarm.setText("");
				if(box.selectedProperty().getValue()==true) {
					if (textName.getText().isEmpty() && textSeconName.getText().isEmpty() && textFatherName.getText().isEmpty() && pass.getText().isEmpty()) {
						alarm.setText("�� �� ����� ���� �.�.�. � ������!");
					}
					else {
						String currentJob;
						int idpost;
						if (job.getText().isEmpty()) {
							idpost = allJobs.getSelectionModel().getSelectedIndex();
							idpost +=1;
							System.out.println("��� ������ - " + idpost);
						} else {
							posts.setNamePost(job.getText());
							idpost = posts.addPost("INSERT INTO `post` (`name_post`) VALUES ('" + job.getText() +"'); ");
							System.out.println("��� ������ - " + idpost);
						}
						
						String login = textLogin.getText();
						//alarm.setText("��������! �� �� ����� ���� �.�.�. � ������!");
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
							Alert alert = new Alert(AlertType.INFORMATION,"����������� ������ �������! ������ �� ������ ����� � �������");
							alert.setTitle("�����������");
							alert.setHeaderText("�� ����������������");
							alert.show();
							alarm.setText("�� ����������������! ");
						}
					}
				}
				else {
					alarm.setText("�� �� �������� � �����������, ���������� ��������� ����� ������������ ������!");
				}
			}
		});
		HBox reg = new HBox(50);
		reg.setAlignment(Pos.CENTER);
		reg.setSpacing(15);
		reg.setPadding(new Insets(10.0,10.0,10.0,10.0));
		Label personalData = new Label("� �������� �� ��������� ������������ ������");
		personalData.setWrapText(true);
		
		reg.getChildren().addAll(box,personalData, readData, Enter  );
		reg.setId("Info");
		
		Button buttonExit = new Button("�����");
		buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		buttonExit.setId("button");
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//Login firstScene = new Login(primaryStage);
				System.out.print("D����� �����");
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
