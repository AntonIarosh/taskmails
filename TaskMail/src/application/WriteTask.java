package application;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.zip.*;

public class WriteTask {
	
	public void writeTask(String theme, String text, int idUser,String body, String supervisor, String link,
			String description,String dateStart,String dataEnd, String dataCreate,String urgencyMail,String taskCol, 
			String itsDone, Stage primaryStage,
			int idTask, LinkedList <String> paths) throws IOException {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("����� ���������� �����, ����� �������� ������");
		List <File> files = null;
		files = fileChooser.showOpenMultipleDialog(primaryStage);
		
		if(!files.isEmpty()) {
			PrintWriter output = null;
			output = new PrintWriter(new BufferedWriter(new FileWriter(files.get(0).getAbsolutePath())));
			
			try {
				output.println(theme);
				//output.println(text);
				output.println("���� ������: " + body  + ";");
				output.println("������������: " +supervisor+";");
				output.println("�������������� ��������: " +description+";");
				output.println("���� � ����� ������ ����������: " + dateStart +";");
				output.println("���� � ����� ��������� ����������: " + dataEnd +";");
				output.println("���� � ����� �������� ������:" + dataCreate + ";");
				output.println("��������� ������: " + urgencyMail +";");
				output.println("������ �� �������������� ���������: " + link + ";");
				output.println("������������� ����������: " + taskCol + ";");
				output.println("����������: " + itsDone +";");
				
				output.close();
				// ��������� �� ������ -- /
				Alert alert = new Alert(AlertType.INFORMATION,"����� ������ ������� � �������� � ����: " + files.get(0).getAbsolutePath());
				alert.setTitle("������ � ����");
				alert.setHeaderText("�������� ������");
				alert.show();
				// ������ ��������� �� ������ -- /
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			if (!paths.isEmpty()) {
			FileChooser fileChooserZ = new FileChooser();
			fileChooserZ.setTitle("����� ����� ��� ZIP ������");
			File file = null;
			file = fileChooserZ.showOpenDialog(primaryStage);
			
			 try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(file.getAbsoluteFile())))
		        {
				 
				 FileInputStream fis= new FileInputStream(files.get(0).getAbsolutePath());
		         ZipEntry entry1=new ZipEntry("������.txt");
		         zout.putNextEntry(entry1);
		         // ��������� ���������� ����� � ������ byte
		         byte[] buffer = new byte[fis.available()];
		         fis.read(buffer);
		         // ��������� ���������� � ������
		         zout.write(buffer);
		         
		         for (int i=0; i < paths.size(); i++) {
		        	 FileInputStream fiss= new FileInputStream(paths.get(i));
		        	 ZipEntry entryFile = new ZipEntry(paths.get(i));
		        	 zout.putNextEntry(entryFile);
		        	 byte[] bufferFile = new byte[fiss.available()];
		        	 fiss.read(bufferFile);
		        	 zout.write(bufferFile);
		         }
		         
		         // ��������� ������� ������ ��� ����� ������
		         zout.closeEntry();
		        }
		        catch(Exception ex){
		            System.out.println(ex.getMessage());
		        } 
			}
		}
	

	
	}
}
