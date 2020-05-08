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
		System.out.println(" äàòà- " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" ãîä ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	// Âàëèäàöèÿ ÷àñîâ âğåìåíè
	public boolean validHour() {
		boolean valid = true;
		int hour = Integer.parseInt(this.text);
		System.out.println(" ×àñ - " + hour);
		if((hour >= 0) &&(hour < 25)) {
			valid = true;
			System.out.println(" ×àñ ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		
		return valid;
	}
	// Âàëèäàöèÿ ìèíóò âğåìåíè
	public boolean validMinutes() {
		boolean valid = true;
		int minutes = Integer.parseInt(this.text);
		System.out.println(" ìèíóòà - " + minutes);
		if((minutes  >= 0) &&(minutes  < 61)) {
			valid = true;
			System.out.println(" ìèíóòû ïğàâèëüíûå ");
		}
		else {
			valid = false;
		}
		
		return valid;
	}
	
	public boolean validEmail() {
		boolean valid = true;
		Pattern p = Pattern.compile("^(?!.*@.*@.*$)(?!.*@.*\\-\\-.*\\..*$)(?!.*@.*\\-\\..*$)(?!.*@.*\\-$)(.*@.+(\\..{1,11})?)$");
		Matcher m = p.matcher(this.text);
		System.out.println("  Email- " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" Email ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validPort() {
		boolean valid = true;
		Pattern p = Pattern.compile("^[0-9]{1,}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  Ïîğò - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" Ïîğò ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validSMTP() {
		boolean valid = true;
		Pattern p = Pattern.compile("^smtp[.][a-zA-Z]{1,}[.][a-zA-Z]{1,}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  SMTP - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" SMTP  ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validIMAP() {
		boolean valid = true;
		Pattern p = Pattern.compile("^imap[.][a-zA-Z]+[.][a-zA-Z]+");
		Matcher m = p.matcher(this.text);
		System.out.println("  IMAP - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" IMAP ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validName() {
		boolean valid = true;
		Pattern p = Pattern.compile("^[0-9à-ÿÀ-ß¸¨a-zA-Z//-]{2,59}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  Èìÿ - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" ÈÌÿ ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validElseName() {
		boolean valid = true;
		
		Pattern p = Pattern.compile("^[0-9à-ÿÀ-ß¸¨a-zA-Z//-]{2,59}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  ÔÎ - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" ÔÎ ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	public boolean validLogin() {
		boolean valid = true;
		
		Pattern p = Pattern.compile("^[0-9à-ÿÀ-ß¸¨a-zA-Z//-//_]{6,250}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  Ëîãèí - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" Ëîãèí ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}
	
	public boolean validPassword() {
		boolean valid = true;
		
		Pattern p = Pattern.compile("^[0-9à-ÿÀ-ß¸¨a-zA-Z//-//_]{6,48}$");
		Matcher m = p.matcher(this.text);
		System.out.println("  Ëîãèí - " + this.text);
		if(m.matches()) {
			valid = true;
			System.out.println(" Ëîãèí ïğàâèëüíûé ");
		}
		else {
			valid = false;
		}
		//this.text
		return valid;
	}

}
