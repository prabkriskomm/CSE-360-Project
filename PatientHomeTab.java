package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Node;



public class PatientHomeTab extends BorderPane {

    private VBox messageDisplayArea; 

    public PatientHomeTab() {
        createTabs();
        
    }

    private void createTabs() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab homeTab = new Tab("Home");
        Tab healthRecordTab = new Tab("Health Record");
        Tab messagesTab = new Tab("Messages");
        Tab visitsTab = new Tab("Visits");

        
        messagesTab.setContent(createMessagingInterface());

        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);

       
        setCenter(tabs);
    }

    private BorderPane createMessagingInterface() {
        
        messageDisplayArea = new VBox(5);
        messageDisplayArea.setPadding(new Insets(10));
        messageDisplayArea.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(messageDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(1.0); 

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Type your message here");

        Button sendButton = new Button("Send");
        sendButton.setMaxWidth(Double.MAX_VALUE);
        sendButton.setOnAction(event -> {
            String message = messageArea.getText();
            if (!message.isEmpty()) {
                Label messageLabel = new Label(message);
                messageLabel.setMaxWidth(Double.MAX_VALUE);
                messageLabel.setAlignment(Pos.TOP_RIGHT); 
                messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                messageDisplayArea.getChildren().add(messageLabel); 
                messageArea.clear();

                
                scrollPane.setVvalue(scrollPane.getVmax()); 
            }
        });


        // Create End Chat button
        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            File file = new File("chat_history.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Node messageNode : messageDisplayArea.getChildren()) {
                    if (messageNode instanceof Label) {
                        Label messageLabel = (Label) messageNode;
                        
                        String formattedMessage = "Patient: " + messageLabel.getText();
                        writer.write(formattedMessage);
                        writer.newLine();
                    }
                }
                System.out.println("Chat saved to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            messageArea.clear();
            messageDisplayArea.getChildren().clear(); 
        });



        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(sendButton, endChatButton);
        VBox.setVgrow(sendButton, Priority.ALWAYS);
        VBox.setVgrow(endChatButton, Priority.ALWAYS);

        HBox messageBox = new HBox(10);
        HBox.setHgrow(messageArea, Priority.ALWAYS);
        messageBox.getChildren().addAll(messageArea, buttonBox);
        messageBox.setPadding(new Insets(10));

        BorderPane messageLayout = new BorderPane();
        messageLayout.setCenter(scrollPane);
        messageLayout.setBottom(messageBox);

        return messageLayout;
        
        
    }
    
   
}
