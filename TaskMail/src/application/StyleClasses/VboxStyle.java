package application.StyleClasses;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VboxStyle {
	private VBox ounVBox;
	public VboxStyle() {
		this.ounVBox = new VBox();
	}
	public VBox getStyleVbox() {
		//this.ounVBox
		this.ounVBox.setAlignment(Pos.CENTER);
		this.ounVBox.setSpacing(6);
		return this.ounVBox;
	}
}
