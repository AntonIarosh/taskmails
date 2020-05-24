package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.sql.*;
import application.Scenes.Login;

public class Main extends Application {
	static Connection con;
	static Statement answer;
	@Override
	public void start(Stage primaryStage) {
		try {
			Login firstScene = new Login(primaryStage);
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("View.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(firstScene.getScene());
			primaryStage.setTitle("Живое Расписание");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
