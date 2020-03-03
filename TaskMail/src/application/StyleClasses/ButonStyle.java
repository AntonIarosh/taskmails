package application.StyleClasses;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class ButonStyle {
	private Button button;
	
	
	public ButonStyle(Button oldButton) {
		this.button = oldButton;
	}
	public void setButon(Button oldButton) {
		this.button = oldButton;
	}
	public Button getStyleButton () {

		this.button.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
		
		this.button.setMaxWidth(160);
		this.button.setId("button");

		return this.button;
	}
}
