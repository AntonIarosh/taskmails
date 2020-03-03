package application.interfaces;

import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public interface loginScene {
	public Scene getScene ();
	public Scene createScene(Stage primaryStage);
	public boolean check(String pass,String text);
}
