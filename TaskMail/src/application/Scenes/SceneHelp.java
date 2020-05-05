package application.Scenes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneHelp {
	Stage primaryStage;
	private Scene ounScene;
	private Scene oldScene;
	
	public SceneHelp(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.oldScene = primaryStage.getScene();
		newWindow(this.primaryStage, Modality.WINDOW_MODAL);
	}
	
	public void newWindow(Stage parent, Modality modality) {
		Stage window = new Stage();
		window.initModality(modality);
		window.initOwner(parent);
		BorderPane pane = new BorderPane();
		
		StringBuilder str = new StringBuilder();
		Scanner scanner = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("help.txt"));
			scanner = new Scanner(reader);
			int numberOfRows = 0;
			while (scanner.hasNext()) {
				str.append(scanner.nextLine() + "\n");
				//text.setText(Integer.toString(scanner.nextInt()));
				//System.out.println(" ����� �� �������� ���� - " + text.getText());
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
		
		TextArea text = new TextArea(str.toString());
		text.setEditable(false);
		text.setWrapText(true);
		
		Button close = new Button("�������");
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
		
		Dialog<ButtonType> dia = new Dialog <ButtonType>();
		dia.setTitle("�����");
		dia.setHeaderText("�������");
		dia.setContentText("����������� ����� �����?");
		dia.getDialogPane().getButtonTypes().addAll(new ButtonType("OK", ButtonData.OK_DONE) , new ButtonType("������",ButtonData.CANCEL_CLOSE));

		
		Label about = new Label(" ���������� ����� �� �������");
		about.setWrapText(true);
		Label what = new Label(" ������� ��� ������ �����");
		what.setWrapText(true);
		TextField textSearch = new TextField();
		textSearch.setPromptText("� ���");
		Button searsch = new Button("�����");
		searsch.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		searsch.setId("button");
		searsch.setOnAction(event->{
			char[] all = text.getText().toCharArray();
			char[] target = textSearch.getText().toCharArray();
			int start =0;
			int end = 0;
			
			for (int i=0; i < all.length; i++) {

				if (all[i] == target[0]) {
					start = i;
					System.out.println(" ����� ������ " + target[0]);
					
					int h = i;
					int counter = 0;
					for (int j =0; j<target.length; j++) {
						if(all[h] == target[j]) {
							counter++;
							System.out.println(" co����� -" +all[h] + ", � - "+ target[j] +"���������� - "+counter );
						}
						
						if ((all[h] == target[target.length-1])&&(counter == target.length))  {
							end = h;
							text.selectRange(start, end+1);
							System.out.println(" ������ -" +start + ", ����� - "+ end +", ������� - "+all[start] + " " + all[end]);
							Optional <ButtonType> result = dia.showAndWait();
							if (result.isPresent()) {
								if(result.orElseThrow().getButtonData() == ButtonData.CANCEL_CLOSE) {
									break;
								}
							}
							//start =0;
							//end = 0;
							
						}
						h++;
					}
				}
				
				/*if (all[i] == target[target.length-1])  {
					end = i;
					text.selectRange(start, end+1);
					System.out.println(" ������ -" +start + ", ����� - "+ end +", ������� - "+all[start] + " " + all[end]);
					Optional <ButtonType> result = dia.showAndWait();
					if (result.isPresent()) {
						if(result.orElseThrow().getButtonData() == ButtonData.CANCEL_CLOSE) {
							break;
						}
					}
					
					//start =0;
					//end = 0;
					
				}*/
			}
		});
		
		
		
		VBox rirht = new VBox();
		rirht.setMinWidth(200);
		rirht.setMaxWidth(300);
		rirht.getChildren().addAll(about,what,textSearch,searsch );
		pane.setRight(rirht);
		
		window.setScene(new Scene(pane,800,600));
		window.setTitle("� ���������");
		window.show();
	}
}
