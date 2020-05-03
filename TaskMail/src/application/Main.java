package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.sql.*;

import application.DB.DBConnection;
import application.Scenes.Login;



public class Main extends Application {
	static Connection con;
	static Statement answer;
	@Override
	public void start(Stage primaryStage) {
		try {
			/*DBConnection conect = new DBConnection("taskmail");
			con = conect.connect();
			System.out.println("| ура подключение");
			String query = "select * from users";
			try {
				answer = con.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ResultSet gg = null;
			try {
				gg = answer.executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				while (gg.next()) {
					System.out.println(gg.getString(3) + gg.getString(2));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}*/
			
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
