package application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.Stage;

public class PatientHomeTab extends BorderPane {
	private String patientName;
    private VBox messageDisplayArea;
	private TabPane tabs;
	private Stage primaryStage;
	private Patient patient;

	 public PatientHomeTab(Stage primaryStage, Patient patient) {
	        this.primaryStage = primaryStage;
	        this.patient = patient;
	        createTabs();
	        createHomeInterface();
	    }
    


    private void createTabs() {
    	tabs = new TabPane();

        Tab homeTab = new Tab("Home", createHomeInterface());
        homeTab.setClosable(false);
        Tab healthRecordTab = new Tab("Health Record", new PatientHealthRecord(patient));
        healthRecordTab.setClosable(false);
        Tab messagesTab = new Tab("Messages", createMessagingInterface());
        messagesTab.setClosable(false);
        Tab visitsTab = new Tab("Visits", new PatientVisitsTab(patient));
        visitsTab.setClosable(false);

        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            Platform.runLater(() -> {
                WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage(patient);
                welcomePage.start(primaryStage);
            });
        });

        Label mediateLabel = new Label("MEDIATE");
        mediateLabel.setFont(new Font("Arial", 25));
        mediateLabel.setStyle("-fx-font-weight: bold;");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabs, logoutButton, mediateLabel);
        StackPane.setAlignment(logoutButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(mediateLabel, Pos.TOP_RIGHT);
        StackPane.setMargin(logoutButton, new Insets(3, 130, 0, 0));
        StackPane.setMargin(mediateLabel, new Insets(1, 10, 0, 0));

        this.setCenter(stackPane);
    }

    private BorderPane createHomeInterface() {
        Button initialsButton = new Button("Edit Profile");
        initialsButton.setStyle("-fx-font-weight: bold;");
        
        initialsButton.setOnAction(e -> {
            Tab editProfileTab = ChangeContactInfo.createEditProfileTab();
            tabs.getTabs().add(editProfileTab);
            tabs.getSelectionModel().select(editProfileTab);
        });

        BorderPane topLayout = new BorderPane();
        topLayout.setRight(initialsButton);

        Label welcomeLabel = new Label("Welcome, John!"); //John needs to be changed to Patient.getFirstName()
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label mediateLabel = new Label("MEDIATE");
        mediateLabel.setStyle("-fx-font-size: 20px;");

        Label oneClickLabel = new Label("Empowering Health, One Click at a Time.");
        oneClickLabel.setStyle("-fx-font-size: 16px;");

        VBox sloganBox = new VBox(mediateLabel, oneClickLabel);
        sloganBox.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(welcomeLabel, sloganBox);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);

        BorderPane homeLayout = new BorderPane();
        homeLayout.setTop(topLayout);
        homeLayout.setCenter(centerBox);

        return homeLayout;
    }

    private BorderPane createMessagingInterface() {
        messageDisplayArea = new VBox(5);
        messageDisplayArea.setPadding(new Insets(10));
        messageDisplayArea.setFillWidth(true);
        messageDisplayArea.setStyle("-fx-background-coler: #D3D3D3;");

        ScrollPane scrollPane = new ScrollPane(messageDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);
        //get the patient name from the log in page
        patientName = "Kaitlyn";
        loadChatHistory(patientName);

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Type your message here");

        
        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(event -> {
            String message = messageArea.getText();
            if (!message.isEmpty()) {
                // Add message to chat log file
                File file = new File("chat_history.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    Label messageLabel = new Label(message);
                    messageLabel.setMaxWidth(Double.MAX_VALUE);
                    messageLabel.setAlignment(Pos.TOP_RIGHT); 
                    messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                    messageDisplayArea.getChildren().add(messageLabel); 
                    messageArea.clear();
                    //formatting messages in the log from the patient 
                    String formattedMessage = patientName + ": " + message;
                    writer.write(formattedMessage);
                    writer.newLine();
                    System.out.println("Message sent and saved to chat history.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Clear the text area after sending the message
                messageArea.clear();
            }
        });

        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            // Clear text area after saving the message
            messageArea.clear();
        });

        VBox buttonBox = new VBox(10, sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox messageBox = new HBox(10);
        HBox.setHgrow(messageArea, Priority.ALWAYS);
        messageBox.getChildren().addAll(messageArea, buttonBox);
        messageBox.setPadding(new Insets(10));

        BorderPane messageLayout = new BorderPane();
        messageLayout.setCenter(scrollPane);
        messageLayout.setBottom(messageBox);
        messageLayout.setStyle("-fx-background-color: #D3D3D3;");

        return messageLayout;
    }
    
    private void loadChatHistory(String name) {
        File file = new File("chat_history.txt");
        if (file.exists()) {
        	//read the file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                //read the file till you get to an empty line
                while ((line = reader.readLine()) != null) {
                	//if theirs the patient name, either to or from pull the line and display it 
                    if (line.startsWith("Doctor to " + name + ":") || line.startsWith(name + ":")) {
                        Label messageLabel = new Label(line);
                        messageLabel.setMaxWidth(Double.MAX_VALUE);
                        messageLabel.setAlignment(Pos.TOP_LEFT);
                        messageDisplayArea.getChildren().add(messageLabel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}