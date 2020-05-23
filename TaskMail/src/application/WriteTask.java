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
		fileChooser.setTitle("Выбор текстового файла, чтобы записать задачу");
		List <File> files = null;
		files = fileChooser.showOpenMultipleDialog(primaryStage);
		
		if(!files.isEmpty()) {
			PrintWriter output = null;
			output = new PrintWriter(new BufferedWriter(new FileWriter(files.get(0).getAbsolutePath())));
			
			try {
				output.println(theme);
				//output.println(text);
				output.println("Суть задачи: " + body  + ";");
				output.println("Руководитель: " +supervisor+";");
				output.println("Дополнительное описание: " +description+";");
				output.println("Дата и время начала выполнения: " + dateStart +";");
				output.println("Дата и время окончания выполнения: " + dataEnd +";");
				output.println("Дата и время создания задачи:" + dataCreate + ";");
				output.println("Срочность задачи: " + urgencyMail +";");
				output.println("Ссылка на дополнительные материалы: " + link + ";");
				output.println("Периодичность выполнения: " + taskCol + ";");
				output.println("Выполнение: " + itsDone +";");
				
				output.close();
				// Сообщение об успехе -- /
				Alert alert = new Alert(AlertType.INFORMATION,"Новая задача создана и записана в файл: " + files.get(0).getAbsolutePath());
				alert.setTitle("Запись в файл");
				alert.setHeaderText("Создание задачи");
				alert.show();
				// Коенец сообщение об успехе -- /
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			if (!paths.isEmpty()) {
			FileChooser fileChooserZ = new FileChooser();
			fileChooserZ.setTitle("Выбор файла для ZIP архива");
			File file = null;
			file = fileChooserZ.showOpenDialog(primaryStage);
			
			 try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(file.getAbsoluteFile())))
		        {
				 
				 FileInputStream fis= new FileInputStream(files.get(0).getAbsolutePath());
		         ZipEntry entry1=new ZipEntry("Задача.txt");
		         zout.putNextEntry(entry1);
		         // считываем содержимое файла в массив byte
		         byte[] buffer = new byte[fis.available()];
		         fis.read(buffer);
		         // добавляем содержимое к архиву
		         zout.write(buffer);
		         
		         for (int i=0; i < paths.size(); i++) {
		        	 FileInputStream fiss= new FileInputStream(paths.get(i));
		        	 ZipEntry entryFile = new ZipEntry(paths.get(i));
		        	 zout.putNextEntry(entryFile);
		        	 byte[] bufferFile = new byte[fiss.available()];
		        	 fiss.read(bufferFile);
		        	 zout.write(bufferFile);
		         }
		         
		         // закрываем текущую запись для новой записи
		         zout.closeEntry();
		        }
		        catch(Exception ex){
		            System.out.println(ex.getMessage());
		        } 
			}
		}
	

	
	}
}
