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
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientHomeTab extends BorderPane {

    private VBox messageDisplayArea;
	private TabPane tabs;

    public PatientHomeTab() {
        createTabs();
    }

    private void createTabs() {
    	tabs = new TabPane();
    	
        //TabPane tabs = new TabPane();

        Tab homeTab = new Tab("Home", createHomeInterface());
        Tab healthRecordTab = new Tab("Health Record", new PatientHealthRecord());
        Tab messagesTab = new Tab("Messages", createMessagingInterface());
        Tab visitsTab = new Tab("Visits", new PatientVisitsTab());

        tabs.getTabs().addAll(homeTab, healthRecordTab, messagesTab, visitsTab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        setCenter(tabs);
    }

    private BorderPane createHomeInterface() {
    	
    	
      
    	
        Button initialsButton = new Button("JS");
        initialsButton.setStyle("-fx-font-weight: bold;");
        
        initialsButton.setOnAction(e -> {
            Tab editProfileTab = ChangeContactInfo.createEditProfileTab();
            tabs.getTabs().add(editProfileTab);
            tabs.getSelectionModel().select(editProfileTab);
        });
       
        


        BorderPane topLayout = new BorderPane();
        topLayout.setRight(initialsButton);

        Label welcomeLabel = new Label("Welcome, John!");
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

        return messageLayout;
    }
}
