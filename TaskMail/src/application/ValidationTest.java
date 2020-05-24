package application;

/*import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import application.Validation;

class ValidationTest {
	/**
	 * @throws java.lang.Exception
	 */
/*	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
/*	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}


	/**
	 * Test method for {@link application.Validation#validHour()}.
	 */
/*	@Test
	final void testValidHour() {
		int [] intHour = new int [5];
		intHour[0] = 0;
		intHour[1] = -5;
		intHour[2] = 22;
		intHour[3] = 3;
		intHour[4] = 256;
		boolean[] tests = new boolean[5];
		tests [0] = true;
		tests [1] = false;
		tests [2] = true;
		tests [3] = true;
		tests [4] = false;
		String [] hour = new String [5];
		hour[0] = "2";
		hour[1] = "5";
		hour[2] = "-6";
		hour[3] = "sadf";
		hour[4] = "qwerty";
		boolean[] testS= new boolean[5];
		testS [0] = true;;
		testS [1] = true;
		testS [2] = false;
		testS [3] = false;
		testS [4] = false; 
		Validation valid = new Validation();
		for (int i =0; i< intHour.length;i++) {
			valid.setText(Integer.toString(intHour[i]));
			Assert.assertEquals(tests[i],valid.validHour());
			valid.setText(hour[i]);
			Assert.assertEquals(testS[i],valid.validHour());
		}
	}

	/**
	 * Test method for {@link application.Validation#validMinutes()}.
	 */
/*	@Test
	final void testValidMinutes() {
		int [] intMinutes = new int [6];
		intMinutes[0] = 0;
		intMinutes[1] = -5;
		intMinutes[2] = 59;
		intMinutes[3] = 3;
		intMinutes[4] = 256;
		intMinutes[5] = 60;
		boolean[] tests = new boolean[6];
		tests [0] = true;
		tests [1] = false;
		tests [2] = true;
		tests [3] = true;
		tests [4] = false;
		tests [5] = false;
		String [] hourStrin = new String [6];
		hourStrin[0] = "0";
		hourStrin[1] = "59";
		hourStrin[2] = "60";
		hourStrin[3] = "-6";
		hourStrin[4] = "sadf";
		hourStrin[5] = "qwerty";
		boolean[] testS= new boolean[6];
		testS [0] = true;
		testS [1] = true;
		testS [2] = false;
		testS [3] = false;
		testS [4] = false;
		testS [5] = false;
		Validation valid = new Validation();
		for (int i =0; i< intMinutes.length;i++) {
			valid.setText(Integer.toString(intMinutes[i]));
			Assert.assertEquals(tests[i],valid.validMinutes());
			valid.setText(hourStrin[i]);
			Assert.assertEquals(testS[i],valid.validMinutes());
		}
	}*/

	/**
	 * Test method for {@link application.Validation#validEmail()}.
	 */
/*	@Test
	final void testValidEmail() {
		String [] emails = new String [5];
		emails[0] = "asdf";
		emails[1] = "sdf@asdf.com";
		emails[2] = "sadf.asdf@asdf.ru";
		emails[3] = "sdf@@asdf.com";
		emails[4] = "asdf@asdf@asdf.asdf";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = true;
		testS [3] = false;
		testS [4] = false;
		Validation valid = new Validation();
		for (int i =0; i< emails .length;i++) {
			valid.setText(emails[i]);
			Assert.assertEquals(testS[i],valid.validEmail());
		}
	}*/

	/**
	 * Test method for {@link application.Validation#validPort()}.
	 */
/*	@Test
	final void testValidPort() {
		int [] ports = new int [5];
		ports[0] = 0;
		ports[1] = -5;
		ports[2] = 22;
		ports[3] = 3;
		ports[4] = 256;
		boolean[] tests = new boolean[5];
		tests [0] = true;
		tests [1] = false;
		tests [2] = true;
		tests [3] = true;
		tests [4] = true;
		String [] portS = new String [5];
		portS[0] = "asdf";
		portS[1] = "5";
		portS[2] = "-6";
		portS[3] = "sadf";
		portS[4] = "qwerty";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = false;
		testS [3] = false;
		testS [4] = false;
		Validation valid = new Validation();
		for (int i =0; i< ports.length;i++) {
			valid.setText(Integer.toString(ports[i]));
			Assert.assertEquals(tests[i],valid.validPort());
			valid.setText(portS[i]);
			Assert.assertEquals(testS[i],valid.validPort());
		}
		
	}*/

	/**
	 * Test method for {@link application.Validation#validSMTP()}.
	 */
/*	@Test
	final void testValidSMTP() {
		String [] server = new String [5];
		server[0] = "SMTP.fasdf.sdf";
		server[1] = "smtp.asdf.com";
		server[2] = "smtp.as666666666df.asdf6666";
		server[3] = "smtp.com";
		server[4] = "com.com.com";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = false;
		testS [3] = false;
		testS [4] = false;
		Validation valid = new Validation();
		for (int i =0; i< server .length;i++) {
			valid.setText(server[i]);
			Assert.assertEquals(testS[i],valid.validSMTP());
		}
	}

	/**
	 * Test method for {@link application.Validation#validIMAP()}.
	 */
/*	@Test
	final void testValidIMAP() {
		String [] server = new String [5];
		server[0] = "IMAP.fasdf.sdf";
		server[1] = "imap.asdf.com";
		server[2] = "imap.as666666666df.asdf6666";
		server[3] = "imap.com";
		server[4] = "com.com.com";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = false;
		testS [3] = false;
		testS [4] = false;
		Validation valid = new Validation();
		for (int i =0; i< server .length;i++) {
			valid.setText(server[i]);
			Assert.assertEquals(testS[i],valid.validIMAP());
		}
	}

	/**
	 * Test method for {@link application.Validation#validName()}.
	 */
/*	@Test
	final void testValidName() {
		String [] names = new String [5];
		names[0] = "";
		names[1] = "Анна-Мария";
		names[2] = "Анастасия";
		names[3] = "Вероника-89";
		names[4] = "12345555";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = true;
		testS [3] = true;
		testS [4] = true;
		Validation valid = new Validation();
		for (int i =0; i< names .length;i++) {
			valid.setText(names[i]);
			Assert.assertEquals(testS[i],valid.validName());
		}
	}

	/**
	 * Test method for {@link application.Validation#validElseName()}.
	 */
/*	@Test
	final void testValidElseName() {
		String [] namesAnother = new String [5];
		namesAnother[0] = "";
		namesAnother[1] = "Иванов-Петров";
		namesAnother[2] = "Петров";
		namesAnother[3] = "Иванов";
		namesAnother[4] = "12345555";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = true;
		testS [2] = true;
		testS [3] = true;
		testS [4] = true;
		Validation valid = new Validation();
		for (int i =0; i< namesAnother .length;i++) {
			valid.setText(namesAnother[i]);
			Assert.assertEquals(testS[i],valid.validElseName());
		}
	}

	/**
	 * Test method for {@link application.Validation#validLogin()}.
	 */
/*	@Test
	final void testValidLogin() {
		String [] logins = new String [5];
		logins[0] = "";
		logins[1] = "login";
		logins[2] = "loginn";
		logins[3] = "asdfghjkl";
		logins[4] = "12345555";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = false;
		testS [2] = true;
		testS [3] = true;
		testS [4] = true;
		Validation valid = new Validation();
		for (int i =0; i< logins .length;i++) {
			valid.setText(logins[i]);
			Assert.assertEquals(testS[i],valid.validLogin());
		}
	}

	/**
	 * Test method for {@link application.Validation#validPassword()}.
	 */
/*	@Test
	final void testValidPassword() {
		String [] pass = new String [5];
		pass[0] = "";
		pass[1] = "login";
		pass[2] = "loginn";
		pass[3] = "asdfghjkl";
		pass[4] = "12345555";
		boolean[] testS= new boolean[5];
		testS [0] = false;
		testS [1] = false;
		testS [2] = true;
		testS [3] = true;
		testS [4] = true;
		Validation valid = new Validation();
		for (int i =0; i< pass .length;i++) {
			valid.setText(pass[i]);
			Assert.assertEquals(testS[i],valid.validPassword());
		}
	}

}*/
