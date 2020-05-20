package addManyItems;

import java.util.LinkedList;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.Stage;
import java.util.List;

import application.Scenes.SceneOneTaskForWorker;

public class addFilesToLeterTask {
	private LinkedList <String> paths;
	private VBox allFiles;
	private List <File> files;
	final FileChooser fileChooser;
	
	public addFilesToLeterTask() {
		this.allFiles = null;
		this.paths = null;
		this.setFiles(null);
		fileChooser = new FileChooser();
	}
	
	public addFilesToLeterTask(VBox vBox, LinkedList <String> somePaths, List <File> _files) {
		this.allFiles = vBox;
		this.paths = somePaths;
		this.setFiles(_files);
		fileChooser = new FileChooser();
	}
	
	public VBox getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(VBox allFiles) {
		this.allFiles = allFiles;
	}
	public LinkedList <String> getPaths() {
		return paths;
	}
	public void setPaths(LinkedList <String> paths) {
		this.paths = paths;
	}
	
	public VBox setVisibleMenuFiles(VBox vBox,Stage primaryStage) {
		LinkedList <String> pa = new LinkedList();
		fileChooser.setTitle("Âûבמנ פאיכמג");
		List <File> files = null;
		files = fileChooser.showOpenMultipleDialog(primaryStage);
		
		if(!files.isEmpty()) {
		for (int i=0; i <files.size(); i++) {
			System.out.println(" Ôאיכû - " + files.get(i).getPath());
			pa.add(files.get(i).getPath());
		}
		
		for (int i=0; i < pa.size(); i++ ) {
			String nAme = pa.get(i);
			Bloom effect = new Bloom();
			Bloom effect_ = new Bloom(0.9);
			Glow ef = new Glow(0.7);
			VBox file = new VBox(50);
			Label fileLabel = new Label("Ôאיכ");
			fileLabel.setEffect(ef);
			Label fileName = new Label();
			fileName.setText(nAme);
			fileName.setWrapText(true);
			fileName.setEffect(effect_);
			file.getChildren().addAll(fileLabel,fileName);
			file.setAlignment(Pos.CENTER);
			file.setSpacing(5);
			file.getStyleClass().add("EnterTask");
			fileLabel.setStyle("-fx-text-fill: white;");
			fileName.setStyle("-fx-text-fill: white;");
			file.setStyle("-fx-border-style: dashed centered; -fx-border-width: 1.5px;-fx-background-color: #7B68EE;");
			vBox.getChildren().add(file);
		
		}

		}
		this.setPaths(pa);
		return vBox;
	}

	public List <File> getFiles() {
		return files;
	}

	public void setFiles(List <File> files) {
		this.files = files;
	}
}
