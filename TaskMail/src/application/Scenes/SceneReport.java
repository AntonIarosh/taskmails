package application.Scenes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import application.DB.AddTaskCommentLinkDone;
import application.DB.AddUser;
import application.DB.SearchCommemtsAndLinksTask;
import application.DB.WriteTaskInDBCreated;
import application.Entities.EntityComment;
import application.Entities.EntityLink;
import application.Entities.EntityTask;
import application.Mails.ReportLetterSend;
import application.Mails.TaskLetterSend;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class SceneReport {
	
				private EntityTask oneTask;
				private Stage primaryStage;
				private Scene ounScene;
				private Scene oldScene;
				private AddUser add;
				private int Id;
				private int idposts;
				private LinkedList<EntityLink> _dataLinks;
				private LinkedList<EntityComment> _dataComments;
				private LinkedList <String> paths;
				private LinkedList<Integer> allIds;
				private SceneChoseUser change;
				
				public SceneReport (Stage primaryStage, int _id, EntityTask _oneTask,LinkedList<EntityLink> _dataLinks,
				LinkedList<EntityComment> _dataComments ) {
					this.setOneTask(new EntityTask());
					this.setOneTask(_oneTask);
					this.paths = new LinkedList <String>();
					this.allIds = new LinkedList<Integer>();
					add = null;
					this.Id = _id;
					System.out.println("Конструктор айдиc - " + this.Id);
					System.out.println("передача  - " + _id);
					this.primaryStage = primaryStage;
					this._dataLinks = _dataLinks;
					this._dataComments = _dataComments;
					

					this.ounScene = createNewScene(this.oneTask, this._dataLinks,this._dataComments);
					this.oldScene = primaryStage.getScene();
					setNewScene(this.primaryStage,this.ounScene); 
					
					

				}
				
				public void setNewScene(Stage primaryStage, Scene newScene) {
					primaryStage.close();
					primaryStage.setScene(newScene);
					primaryStage.show();
				}
				public int getIdUser() {
					System.out.println("геттер - " + this.Id);
					return this.Id;
				}
				public void setId(int _id) {
					this.Id = _id;
				}

				
				public Scene createNewScene(EntityTask _oneTask,LinkedList<EntityLink> _dataLinks,LinkedList<EntityComment> _dataComments) {
					//FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
					int idChosenUser=0;
					VBox flowPane = new VBox(50);
					BorderPane roots = new BorderPane();
					roots.setId("flowPane");
					Screen screen = Screen.getPrimary();
					Rectangle2D bounds = screen.getBounds();
					Scene scene = new Scene(roots, bounds.getWidth(),  bounds.getHeight());
					//FlowPane root = new FlowPane(Orientation.HORIZONTAL);
					// Панель для данных пользователя ---/
					VBox TaskInfo = new VBox();
					
					Label supervisorLabel = new Label("Отчет по проделанной работе ");
					supervisorLabel.setTextFill(Color.web("#E0FFFF"));
					VBox addOnenComment = new VBox(50);
					Label addOnenCommentLabel = new Label("Отчет");
					addOnenCommentLabel.setId("PULL");
					TextArea bodyaddOnenComment = new TextArea();
					bodyaddOnenComment.setOnKeyPressed(event-> {
						int num = 680 - bodyaddOnenComment.getText().length();
						addOnenCommentLabel.setText("Отчет. Осталось символов: " + num);
						if(bodyaddOnenComment.getText().length() > 680) {
							
							bodyaddOnenComment.setEditable(false);
						}
						if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
							
							bodyaddOnenComment.setEditable(true);
						}
					});
					bodyaddOnenComment.setPromptText("Введите содержимое отчёта ");
					bodyaddOnenComment.setPrefRowCount(10); 
					bodyaddOnenComment.setWrapText(true);
					addOnenComment.getChildren().addAll(addOnenCommentLabel, bodyaddOnenComment);
					addOnenComment.setAlignment(Pos.CENTER);
					addOnenComment.setSpacing(5);
					addOnenComment.getStyleClass().add("EnterUserData");
					

					VBox doneWork = new VBox(50);
					Label ItogLabel = new Label("Итог проделанной работы");
					ItogLabel.setId("PULL");
					TextArea contentDoneWork = new TextArea();
					contentDoneWork.setOnKeyPressed(event-> {
						int num = 680 - contentDoneWork.getText().length();
						ItogLabel.setText("Итог проделанной работы. Осталось символов: " + num);
						if(contentDoneWork.getText().length() > 680) {
							
							contentDoneWork.setEditable(false);
						}
						if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
							
							contentDoneWork.setEditable(true);
						}
					});
					contentDoneWork.setPromptText("Введите итог выполнения");
					contentDoneWork.setPrefRowCount(5); 
					contentDoneWork.setWrapText(true);
					doneWork.getChildren().addAll(ItogLabel, contentDoneWork);
					doneWork.setAlignment(Pos.CENTER);
					doneWork.setSpacing(5);
					doneWork.getStyleClass().add("EnterUserData");
					
			
					VBox problems = new VBox(50);
					Label problemsLabel = new Label("Проблемы возникшие при выполнении работы");
					problemsLabel.setId("PULL");
					TextArea contentProblems = new TextArea();
					contentProblems.setOnKeyPressed(event-> {
						int num = 680 - contentProblems.getText().length();
						problemsLabel.setText("Проблемы возникшие при выполнении работы. Осталось символов: " + num);
						if(contentProblems.getText().length() > 680) {
							
							contentProblems.setEditable(false);
						}
						if((event.getCode() == KeyCode.BACK_SPACE) || (event.getCode() == KeyCode.DELETE)) {
							
							contentProblems.setEditable(true);
						}
					});
					contentProblems.setPromptText("Введите перечень проблем");
					contentProblems.setPrefRowCount(5); 
					contentProblems.setWrapText(true);
					problems.getChildren().addAll(problemsLabel, contentProblems);
					problems.setAlignment(Pos.CENTER);
					problems.setSpacing(5);
					problems.getStyleClass().add("EnterUserData");
				
					VBox adding = new VBox(50);
					adding.getChildren().addAll(supervisorLabel,addOnenComment,doneWork ,problems );
					adding.setId("Data");
					adding.setSpacing(10);
					adding.getStyleClass().add("Data");
					
					// --- конец панели для данных пользователя ---/
					// Панель для эмэйл пользователя ---/
					
					// --- конец панели для эмэйл пользователя---/
					 //----------------------------------------------------прикрепление файлов----------------------------/
					
					InnerShadow ih = new InnerShadow();
					InnerShadow is = new InnerShadow(30.0, Color.MEDIUMPURPLE);
					VBox Allfilles = new VBox(50);
					Allfilles.setEffect(ih);
					Allfilles.setAlignment(Pos.CENTER);
					Allfilles.setSpacing(5);
					Allfilles.setStyle("-fx-alignment: center;-fx-padding: 5px;  -fx-background-color: #FF6347; -fx-background-radius: 6;");
					VBox files = new VBox(50);
					Label fileLabel = new Label("Прикреплённые файлы");
					fileLabel.setWrapText(true);
					fileLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
					files.setEffect(is);
					files.getChildren().addAll(fileLabel,Allfilles);
					files.setAlignment(Pos.CENTER);
					files.setSpacing(5);
					files.setStyle("-fx-alignment: center;-fx-padding: 5px;  -fx-background-color: #DC143C; -fx-background-radius: 6;");
					files.setMinWidth(230);
					
					ScrollPane ScUsers =new ScrollPane();
					ScUsers.setLayoutX(10);
					ScUsers.setLayoutY(10);
					ScUsers .setCursor(Cursor.CLOSED_HAND);
					ScUsers.setContent(files);
					ScUsers.setMaxWidth(230);
					ScUsers.setMinWidth(230);

				// --------------------------------- Конец прикрепления файлов -----------------------------------------/

					// -- Кнопка назад ---/
					Button buttonExit = new Button("Назад");
					buttonExit.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
					buttonExit.setId("button");
					buttonExit.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							//Login firstScene = new Login(primaryStage);
							System.out.print("DВызов назад");
							//primaryStage.setScene(firstScene.getScene());//,oldScene);
							
							primaryStage.setScene(oldScene);
							primaryStage.centerOnScreen();
						}
					});
					// -- Конец кнопки назад --/

					Button adressTask = new Button("Выберите адресата");
					adressTask .getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
					adressTask .setId("button");
					TextField textTaskAdress = new TextField();
					adressTask .setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								LinkedList<Integer> all = getAllIds();
								all.clear();
								
								change = new SceneChoseUser(primaryStage,  Id, idChosenUser);
								change.setId(Id);
								
								all.addAll(change.getAllIds());
								System.out.println (" Количество есть - " + change.getAllIds());
								System.out.println (" Количество записано- " + all.size());
								setAllIds(all);
								System.out.println (" Количество в класса- " + getAllIds());
							}
						});

					// -- Кнопка обновить данные пользователя --- /
					Button addFile = new Button("Прикрепить");
					addFile.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
					addFile.setId("button");
					addFile.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							setVisibleMenuFiles(Allfilles);
						}
					});
					
					Button pushTask = new Button("Отправить");
					pushTask.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
					pushTask.setId("button");
					pushTask.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							
							LinkedList<Integer> all = getAllIds();
							all.clear();
							all.addAll(change.getAllIds());
							System.out.println (" Количество есть - " + change.getAllIds());
							System.out.println (" Количество записано- " + all.size());
							setAllIds(all);
							System.out.println (" Количество в класса- " + getAllIds());
							
							int idch = 0;
							String Theme = null;
							String Text = null;
							String comments = null;
							String body = null;
							String supervisor = null;
							String links = null;
							String description = null;
							String dateStart = null;
							String dataEnd = null;
							String dataCreate = null;
							String urgencyMail = null;
							String taskCol = null;// Периодичность
							String itsDone = "";
							String report = null;
							if (_oneTask.getIsDone() == 0) {
								itsDone = "Не выполнено";
							} else {
								itsDone = "Выполнено";
							}
							body = _oneTask.getBode();
							description = _oneTask.getDescription();
							supervisor = _oneTask.getSupervisor();
							taskCol = _oneTask.getTaskCol();
							
							report = bodyaddOnenComment.getText() + " Итог выполнения: " + contentDoneWork.getText() + "; Возникшие проблемы: "+
									contentProblems.getText();
							//String linkForDb = null;
							// Код срочности задачи. -- /
							int idurgency;
							idurgency = _oneTask.getIdUrgency();
							idurgency +=1;
							//System.out.println("код выбора срочности - " + idurgency);
							// конец кода срочности задачи -- /
							
							// Выполнение задачи -- /
							int itsDoneToDB = 0;
							// конец выполнения задачи -- /
							StringBuilder sbComment = new StringBuilder();
							StringBuilder sbLink = new StringBuilder();
							
							for (int i =0; i < _dataComments.size(); i++) {
								//System.out.println("Начался вывод комментариев  - " + _dataComments.size());
								//EntityComment thisComment = dataComments.get(i);
								//String name = Integer.toString(i);
								//comments = ""+_dataComments.get(i).getComment() + "\n";
								sbComment.append(" ");
								sbComment.append(_dataComments.get(i).getComment());
								sbComment.append("\n");
								//System.out.println (" Комментарий -  " + _dataComments.get(i).getComment());
								
							}
							comments = sbComment.toString();
							//System.out.println (" Комментарий -  " + comments);
							for (int i =0; i < _dataLinks.size(); i++) {
								System.out.println("Начался вывод ссылок - " + _dataLinks.size());
								//EntityComment thisComment = dataComments.get(i);
								//String name = Integer.toString(i);
								sbLink.append(" ");
								sbLink.append( _dataLinks.get(i).getLink());
								sbLink.append("\n");
								//links = ""+_dataLinks.get(i).getLink()+"\n";
								System.out.println (" ссылка -  " + _dataLinks.get(i).getLink() );
							
							}
							links = sbLink.toString();
							//System.out.println (" ссылка -  " + links);
							
							urgencyMail = _oneTask.getUrgency();
							//dateStart = _oneTask.getDateStrart().toString();
							//dataEnd = _oneTask.getDateEnd().toString();
							GregorianCalendar thisData = new GregorianCalendar();
							//data = thisData.getTime();
							//dataCreate = data.toString();
							//dataCreate =  _oneTask.getDateCreate().toString();
							
							Theme = "Живое расписание: Отчёт: " + _oneTask.getTitle();
							Text  = "Отчёт по заданию: " + body  + ";\n" +
							"Дополниельное описание: " +description+";\n" + 
							"Ссылка на дополнительные материалы: " + links + ";\n" +
							"Руководитель: " +supervisor+";\n" +
							"Периодичность выполения: " + taskCol + ";\n"+
							"Срочность задачи: " + urgencyMail +";\n" +
							"Дата и время начала выполнения: " + dateStart +";\n" +
							"Дата и время окончания выполенения: " + dataEnd +";\n"+
							"Дата и время создания задачи:" + dataCreate + ";\n" +
							"Выполнение: " + itsDone ; 
							
							int idNewTask = _oneTask.getIdTask();
							Scanner scanner = null;
							try {
								BufferedReader reader = new BufferedReader(new FileReader("idChosenUser.txt"));
								scanner = new Scanner(reader);
								int numberOfRows = 0;
								while (scanner.hasNext()) {
									idch = scanner.nextInt();
									//text.setText(Integer.toString(scanner.nextInt()));
									//System.out.println(" Вывод из главного окна - " + text.getText());
									System.out.println(" Вывод из главного окна - " + idch);
									scanner.nextLine();
									numberOfRows++;
								}
							} catch (FileNotFoundException e1) {
								//System.out.println("Файл не найден.");
								e1.printStackTrace();
							} catch (Exception e1) {
								//System.out.println("Ошибка при считывании из файла.");
								e1.printStackTrace();
								scanner.close();
							} finally {
								scanner.close();
							}
							
							java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							String dateStar = sdf.format(_oneTask.getDateStrart());
							
							String dataEn = sdf.format(_oneTask.getDateEnd());
							
							String dataCreat = sdf.format(_oneTask.getDateCreate());
							ReportLetterSend sender = new ReportLetterSend();
							try {
								sender.senMail(Theme, Text,idch,
										Id,body,supervisor,
										description,dateStar,dataEn, 
										dataCreat,urgencyMail,taskCol,
										itsDone,comments,links ,
										report,getAllIds(),getPaths() );
								/*
								(String theme, String text,int idChosenUser, int idUser,String body, String supervisor,
										String description,String dateStart,String dataEnd, String dataCreate,String urgencyMail,String taskCol, 
										String itsDone, String comments, String links, String report)
								*/
								// Сообщение об успехе -- /
								/*Alert alert = new Alert(AlertType.INFORMATION,"Задание было отправлено получателю, а также сохранено в Вашей базе данных");
								alert.setTitle("Создание задания");
								alert.setHeaderText("Задание сформировано и отправлено!");
								alert.show();*/
								// Коенец сообщение об успехе -- /
								String rep = bodyaddOnenComment.getText() + " " + comments + " " + links;
								String queryAddReport = "INSERT INTO `taskmail`.`report`(`report`.`id_task`,`report`.`report`,"
										+ "`report`.`result`,`report`.`troubles`) VALUES('" + _oneTask.getIdTask() + "','" + 
										rep + "','" + contentDoneWork.getText() + "','" +
										contentProblems.getText() + "')";
								
								AddTaskCommentLinkDone madeAddChange = new AddTaskCommentLinkDone();
								
								System.out.print(queryAddReport);
								madeAddChange.setQuery(queryAddReport);
								madeAddChange.execeteQuery();
							} catch (AddressException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (MessagingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					
					HBox buttons = new HBox(50);
					buttons.getChildren().addAll(adressTask,addFile,pushTask);
					buttons.setSpacing(10);
					buttons.setAlignment(Pos.CENTER);
					buttons.getStyleClass().add("Data");
					// -- Конец кнопки обновить данные пользователя --- /
				
					VBox userInfo = new VBox();
					userInfo.getChildren().setAll(buttons,adding);
					userInfo.setId("Info");
					userInfo.setSpacing(8);
				
					HBox root = new HBox(50);
					root.getChildren().setAll(/*userInfo,emails*/buttonExit);
					root.setAlignment(Pos.CENTER);
					root.setSpacing(20);
					root.setId("root");
					flowPane.getChildren().setAll(root);
		
					roots.setCenter(userInfo);
					roots.setRight(ScUsers);
					roots.setBottom(root);
					
					flowPane.setAlignment(Pos.CENTER);
					flowPane.setId("flowPane");

					scene.getStylesheets().add(getClass().getResource("/application/styles/report.css").toExternalForm());
					return scene;
				}

				public EntityTask getOneTask() {
					return oneTask;
				}

				public void setOneTask(EntityTask oneTask) {
					this.oneTask = oneTask;
				}
				
				public VBox setVisibleMenuFiles(VBox vBox) {
					LinkedList <String> pa = new LinkedList<String>();
					
					FileChooser fileChooser = new FileChooser();
					List <File> files = null;
					files = fileChooser.showOpenMultipleDialog(primaryStage);
					if(!files.isEmpty()) {
						for (int i=0; i <files.size(); i++) {
							System.out.println(" Файлы - " + files.get(i).getPath());
							pa.add(files.get(i).getPath());
						}
						
						for (int i=0; i < pa.size(); i++ ) {
							String nAme = pa.get(i);
							addPath(nAme);
							
							Bloom effect = new Bloom();
							Bloom effect_ = new Bloom(0.9);
							Glow ef = new Glow(0.7);
							
							VBox file = new VBox(50);
							Label fileLabel = new Label("Файл: ");
							fileLabel.setEffect(ef);
							
							Label fileName = new Label();
							fileName.setText(nAme);
							fileName.setWrapText(true);
							fileName.setEffect(effect_);
							
							file.getChildren().addAll(fileLabel,fileName);
							file.setAlignment(Pos.CENTER);
							file.setSpacing(5);
							file.setMaxWidth(200);
							file.getStyleClass().add("EnterTask");
							fileLabel.setStyle("-fx-text-fill: white;");
							fileName.setStyle("-fx-text-fill: white;");
							
							file.setStyle("-fx-border-style: dashed centered; -fx-border-width: 1.5px;-fx-background-color: #7B68EE; -fx-alignment: center;"
									+ "-fx-padding: 5px;");
							
							ScrollPane Scf =new ScrollPane();
							Scf.setLayoutX(10);
							Scf.setLayoutY(10);
							//spComment.setHmin(400);
							Scf .setCursor(Cursor.CLOSED_HAND);
							Scf.setContent(file);
							Scf.setMaxWidth(600);
							Scf.setMinWidth(300);
							Scf.setMinHeight(160);
							Scf.setStyle("-fx-alignment: center; -fx-background-color: #FFA500; -fx-padding: 2px;");
							Scf.setEffect(ef);
							
							Button delete = new Button("Удалить");
							delete.getStylesheets().add(getClass().getResource("/application/styles/button.css").toExternalForm());
							delete.setId("button");
							file.getChildren().add(delete);
							vBox.getChildren().add(file);
							delete.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent e) {
										deleteThis (vBox, file,nAme);
									}
								});
						}

					}
				
				return vBox;
			}
				public VBox deleteThis (VBox vBox, VBox c, String delPath) {
					vBox.getChildren().remove(c);
					this.getPaths().remove(delPath);
					return vBox;
				}

				public LinkedList <String> getPaths() {
					return paths;
				}
				public void addPath(String _path) {
					this.paths.add(_path);
				}
				

				public void setPaths(LinkedList <String> paths) {
					this.paths = paths;
				}

				public LinkedList<Integer> getAllIds() {
					return allIds;
				}

				public void setAllIds(LinkedList<Integer> allIds) {
					this.allIds = allIds;
				}
		
}
