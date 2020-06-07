package application.Scenes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneAbout {
	Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	
	public SceneAbout(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.oldScene = primaryStage.getScene();
		newWindow(primaryStage, Modality.APPLICATION_MODAL);
	}
	
	public void newWindow(Stage parent, Modality modality) {
		Stage window = new Stage();
		window.initModality(modality);
		window.initOwner(parent);
		BorderPane pane = new BorderPane();
		
		StringBuilder str = new StringBuilder();
		Scanner scanner = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("about.txt"));
			scanner = new Scanner(reader);
			int numberOfRows = 0;
			while (scanner.hasNext()) {
				str.append(scanner.nextLine() + "\n");
				numberOfRows++;
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Файл не найден.");
			Alert alert = new Alert(AlertType.INFORMATION,"Файл - about.txt не найден в папке с запускаемым файлом.");
			alert.setTitle("Ошибка");
			alert.setHeaderText("Ошибка открытия файла!");
			alert.show();
		} catch (Exception e1) {
			System.out.println("Ошибка при считывании из файла.");
			Alert alert = new Alert(AlertType.INFORMATION,"Ошибка при считывании из файла - about.tx");
			alert.setTitle("Ошибка");
			alert.setHeaderText("Содержание файла не может быть прочтено!");
			alert.show();
			scanner.close();
		} 
		
		TextArea text = new TextArea(str.toString());
		text.setEditable(false);
		
		Button close = new Button("Закрыть");
		close.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		close.setId("button");
		pane.setCenter(text);
		VBox btn = new VBox();
		btn.setAlignment(Pos.CENTER);
		btn.getChildren().add(close);
		pane.setBottom(btn);
		close.setOnAction(event->{
			window.close();
		});
		window.setScene(new Scene(pane,500,500));
		window.setTitle("О программе");
		//parent.show();
		window.show();
	}
}
