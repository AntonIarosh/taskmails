package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	private String text;

	public Validation() {
		this.text = null;
	}
	public Validation(String _text) {
		this.text = _text;
	}
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public boolean validData() {
		boolean valid = true;
		Pattern p = Pattern.compile("^[12][09][0-9][0-9]-[01][0-9]-[0-3][0-9]$");
		Matcher m = p.matcher(this.text);
		System.out.println(" дата- " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" год правильный ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	// Валидация часов времени
	public boolean validHour() {
		boolean valid = true;
		int hour = Integer.parseInt(this.text);
		System.out.println(" Час - " + hour);
		if((hour > 0) &&(hour < 25)) {
			valid = true;
			System.out.println(" Час правильный ");
		}
		else {
			valid = false;
		}
		
		return valid;
	}
	// Валидация минут времени
	public boolean validMinutes() {
		boolean valid = true;
		int minutes = Integer.parseInt(this.text);
		System.out.println(" минута - " + minutes);
		if((minutes  > 0) &&(minutes  < 61)) {
			valid = true;
			System.out.println(" минуты правильные ");
		}
		else {
			valid = false;
		}
		
		return valid;
	}

}
