package application;

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

public class PatientHomeTab extends BorderPane {
	private Label welcomeLabel;
	private String patientName;
    private VBox messageDisplayArea;
	private TabPane tabs;
	

    public PatientHomeTab() {
        welcomeLabel = new Label();
    	createTabs();
        currentPatient();
    }
    


    private void createTabs() {
    	tabs = new TabPane();
    	

        Tab homeTab = new Tab("Home", createHomeInterface());
        Tab healthRecordTab = new Tab("Health Record", new PatientHealthRecord());
        Tab messagesTab = new Tab("Messages", createMessagingInterface());
        Tab visitsTab = new Tab("Visits", new PatientVisitsTab());

        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        setCenter(tabs);
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

        //welcomeLabel.setText("Welcome!"); //John needs to be changed to Patient.getFirstName()
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
        //patientName = ("Kaitlyn");
        //loadChatHistory(patientName);

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

    private void currentPatient() {
        File file = new File("Current_Patient.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Trim the line to remove leading and trailing whitespace
                    line = line.trim();
                    welcomeLabel.setText("Welcome, " + line);
                    loadChatHistory(line);
                    patientName = line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If the file doesn't exist, display a message indicating that
            welcomeLabel.setText("Patient file not found.");
        }
    }
}
