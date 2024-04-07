package application;
//team 9 is amazing
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StaffMessaging extends BorderPane {
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
                Label messageLabel = new Label(message);
                messageLabel.setMaxWidth(Double.MAX_VALUE);
                messageLabel.setAlignment(Pos.TOP_RIGHT);
                messageLabel.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10;");
                messageDisplayArea.getChildren().add(messageLabel);
                textArea.clear();
                scrollPane.setVvalue(scrollPane.getVmax());
            }
        });

        Button endChatButton = new Button("End Chat");
        endChatButton.setOnAction(event -> {
            File file = new File("chat_history.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Node message : messageDisplayArea.getChildren()) {
                    if (message instanceof Label) {
                        Label messageLabel = (Label) message;
                        String formattedMessage = "Doctor: " + messageLabel.getText();
                        writer.write(formattedMessage);
                        writer.newLine();
                    }
                }
                System.out.println("Chat saved to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            textArea.clear();
            messageDisplayArea.getChildren().clear();
        });

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search...");
        searchBar.textProperty().addListener((observable, oldValue, patientName) -> {
            // Logic to search for patient chats
            System.out.println("Searching for: " + patientName);
        });

        VBox search = new VBox(10);
        search.getChildren().addAll(searchBar);

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

}
