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
	private Patient patient;
	
	public SignUpPage() {
		Patient patient = new Patient();
		this.patient = patient;
	}
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
        insuranceInfoField.setPromptText("Insurance Information");
        
        

        // Error message text
        Text messageText = new Text();

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
                patient.setFirstName(firstName);
                patient.setLastName(lastName);
                patient.setEmail(email);
                patient.setPhoneNumber(phoneNumber);
                patient.setInsurance(insuranceInfo);
                
                // Display success message
                messageText.setText("Sign-up Complete!");
                messageText.setFill(Color.GREEN); 
                
                // Clear the fields after successful sign-up
                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                phoneNumberField.setText("");
                insuranceInfoField.setText("");
                
            } else {
                // Display error message
                messageText.setText("Error: Passwords do not match.");
                messageText.setFill(Color.RED); // Set the text color to red for errors
            }
        });


        // Back to login page button
        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage(patient);
            welcomePage.start(new Stage());
            
            //close current sign up page
            primaryStage.close();
        });

        // Layout for the sign up form
        VBox formLayout = new VBox(10);
        formLayout.getChildren().addAll(firstNameField, lastNameField, emailField, phoneNumberField, insuranceInfoField, passwordField, confirmPasswordField, messageText, signUpButton, backButton);
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