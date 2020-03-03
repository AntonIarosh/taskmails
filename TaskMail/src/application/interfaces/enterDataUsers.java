package application.interfaces;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface enterDataUsers {
	public void setNewScene (Stage primaryStage, Scene newScene);
	public Scene createScene();
	public boolean safeData(String name,String secondName, String fatherName, String job, String pass);
}
