package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class SignUpPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up - MEDIATE");

        // Header
        Text headerText = new Text("Create Account");
        headerText.setFont(Font.font(20));

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");
        
        TextField insuranceInfoField = new TextField();
        insuranceInfoField.setPromptText("Insurance Info");
        
        

        // Error message text
        Text errorMessage = new Text();

        // Sign Up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String phoneNumber = phoneNumberField.getText();
            String insuranceInfo = insuranceInfoField.getText();
            
            if(password.equals(confirmPassword)) {
                // Assuming Patient class extends User or has similar attributes
                Patient patient1 = new Patient(insuranceInfo, insuranceInfo); // If Patient constructor takes parameters, pass them here
                patient1.setFirstName(firstName);
                patient1.setLastName(lastName);
                patient1.setEmail(email);
                patient1.setPassword(password);
                patient1.setphoneNumber(phoneNumber); // Assuming there is a setPhoneNumber method
                patient1.setinsuranceInfo(insuranceInfo); // Assuming there is a setInsuranceInfo method

                //UserStorage.addUser(patient1); // Assumes UserStorage can handle Patient objects

                // Switch to login page, show success message, etc.
            } else {
                errorMessage.setText("Error: Passwords do not match.");
                errorMessage.setFill(Color.RED); // Set the text color to red for errors
            }
        });


        // Back to login page button
        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
            welcomePage.start(new Stage());
        });

        // Layout for the sign up form
        VBox formLayout = new VBox(10);
        formLayout.getChildren().addAll(firstNameField, lastNameField, emailField, passwordField, confirmPasswordField, errorMessage, signUpButton, backButton);
        formLayout.setAlignment(Pos.CENTER);

        // Overall layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(headerText, formLayout);

        // Scene
        Scene scene = new Scene(mainLayout, 400, 450);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
