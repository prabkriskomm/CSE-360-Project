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
	messageDisplayArea.setStyle("-fx-background-coler: #D3D3D3;");

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
                File file = new File("chat_history.txt");
                //writing to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                	// adding the message to the chat log file
                    Label messageLabel = new Label(message);
                    messageLabel.setMaxWidth(Double.MAX_VALUE);
                    messageLabel.setAlignment(Pos.TOP_RIGHT); 
                    messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                    //show message on screen
                    messageDisplayArea.getChildren().add(messageLabel); 
                    textArea.clear();
                    //formatting the message for the file 
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
        
        //search bar implementation
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        searchBar.textProperty().addListener((observable, oldValue, searchBarName) -> {
            System.out.println("Searching for: " + searchBarName);
        });
       
       Button searchButton = new Button("");
       searchButton.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: .25px;");
       searchButton.setOnAction(event -> {
    	   //getting the patient name that was searched
    	   searchedName = searchBar.getText();
    	   //clearing before displaying the messages of the specific searched patient
    	   messageDisplayArea.getChildren().clear();
    	   loadMessages(searchedName);
   
       });


        // Create End Chat button
        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            // Clear text area after saving the message
            textArea.clear();
        });
        
        //display formatting
        //left pannel 
        HBox searchBarButton = new HBox();
        searchBarButton.getChildren().addAll(searchBar, searchButton);
        
        VBox search = new VBox(10);
        search.getChildren().addAll(searchBarButton);

        //Bottom Pannel
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox textBox = new HBox(10);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        textBox.getChildren().addAll(textArea, buttonBox);
        textBox.setPadding(new Insets(10));

        //whole scene
        BorderPane messageLayout = new BorderPane();
        messageLayout.setStyle("-fx-background-color: #D3D3D3;");
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
                    // looks for messages on both side to or from the specific Patient, 
                    if (line.startsWith("Doctor to " + currentPatientName + ":") || line.startsWith(currentPatientName + ":")) {
                        foundHistory = true;
                        // gets the messages and puts them in label to display on the screen
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

