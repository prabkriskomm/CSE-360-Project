package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChangeContactInfo {

    public static Tab createEditProfileTab() {
        Tab editProfileTab = new Tab("Edit Profile");
        VBox editProfileContent = new VBox();
        editProfileContent.setSpacing(10);
        editProfileContent.setPadding(new Insets(10));

        HBox firstNameBox = new HBox();
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setEditable(false);
        firstNameBox.getChildren().addAll(firstNameField);

        HBox lastNameBox = new HBox();
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setEditable(false);
        lastNameBox.getChildren().addAll(lastNameField);

        HBox phoneBox = new HBox();
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");
        phoneNumberField.setEditable(false);
        phoneBox.getChildren().addAll(phoneNumberField);

        HBox emailBox = new HBox();
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setEditable(false);
        emailBox.getChildren().addAll(emailField);

        HBox buttonBox = new HBox();
        Button editButton = new Button("Edit");
        Button saveChangesButton = new Button("Save Changes");
        buttonBox.getChildren().addAll(editButton, saveChangesButton);

        editButton.setOnAction(e -> {
            firstNameField.setEditable(true);
            lastNameField.setEditable(true);
            phoneNumberField.setEditable(true);
            emailField.setEditable(true);
        });

        saveChangesButton.setOnAction(e -> {
            // Save changes here
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            phoneNumberField.setEditable(false);
            emailField.setEditable(false);
        });

        editProfileContent.getChildren().addAll(firstNameBox, lastNameBox, phoneBox, emailBox, buttonBox);
        editProfileTab.setContent(editProfileContent);
        editProfileTab.setClosable(true);

        return editProfileTab;
    }
}
