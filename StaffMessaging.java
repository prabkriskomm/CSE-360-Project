package application;
//team 9 is amazing
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StaffMessaging extends BorderPane {
	private String searchedName;
    private VBox messageDisplayArea;

    public StaffMessaging() {
        BorderPane messageLayout = messageSys();

        Scene scene = new Scene(messageLayout, 800, 600);
        setCenter(messageLayout); 
    }

    private BorderPane messageSys() {
        messageDisplayArea = new VBox(5);
        messageDisplayArea.setPadding(new Insets(10));
        messageDisplayArea.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(messageDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0);

        TextArea textArea = new TextArea();
        textArea.setPromptText("Type your message here");

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(event -> {


            String message = textArea.getText();
            if (!message.isEmpty()) {
                // Append the message to the chat history file
                File file = new File("chat_history.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    // Get the searched name from the search bar
                    //String searchedName = searchBar.getText();
                    // Append the message to the file
                    Label messageLabel = new Label(message);
                    messageLabel.setMaxWidth(Double.MAX_VALUE);
                    messageLabel.setAlignment(Pos.TOP_RIGHT); 
                    messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                    messageDisplayArea.getChildren().add(messageLabel); 
                    textArea.clear();
                    String formattedMessage = "Doctor to " + searchedName + ": " + message;
                    writer.write(formattedMessage);
                    writer.newLine();
                    System.out.println("Message sent and saved to chat history.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Clear the text area after sending the message
                textArea.clear();
            }
        });
        
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        
        searchBar.textProperty().addListener((observable, oldValue, searchBarName) -> {
            // have to get information from a file with all Patient info 
        	//Chat names = patient names find the chat
            System.out.println("Searching for: " + searchBarName);
            
            //instead of patientClass.get we could read from the the file to find a match 
        });
       
       Button searchButton = new Button("");
       searchButton.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: .25px;");
       searchButton.setOnAction(event -> {
    	   //String currentPatientName = searchedName;
    	   searchedName = searchBar.getText();
    	   messageDisplayArea.getChildren().clear();
    	   loadMessages(searchedName);
    	   //need to find a way to transfer name variable so it sends to the save file
    	   //searchBar.clear();
       });


        // Create End Chat button
        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            // Clear text area after saving the message
            textArea.clear();
        });
        
        HBox searchBarButton = new HBox();
        searchBarButton.getChildren().addAll(searchBar, searchButton);

        VBox search = new VBox(10);
        search.getChildren().addAll(searchBarButton);

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox textBox = new HBox(10);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        textBox.getChildren().addAll(textArea, buttonBox);
        textBox.setPadding(new Insets(10));

        BorderPane messageLayout = new BorderPane();
        messageLayout.setLeft(search);
        messageLayout.setCenter(scrollPane);
        messageLayout.setBottom(textBox);
        messageLayout.setPadding(new Insets(20));

        return messageLayout;
    }
	private void loadMessages(String currentPatientName) {
		messageDisplayArea.getChildren().clear();
	    File file = new File("chat_history.txt");
	    if (file.exists()) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	            String line;
	            boolean foundHistory = false;
                while ((line = reader.readLine()) != null) {
                    // Check if the message is for the current patient
                    if (line.startsWith("Doctor to " + currentPatientName + ":") || line.startsWith(currentPatientName + ":")) {
                        foundHistory = true;
                        // Extract the message part without "Doctor to {patientName}: "
                        //String message = line.substring(line.indexOf( ("Doctor to " + currentPatientName + ":"));
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


