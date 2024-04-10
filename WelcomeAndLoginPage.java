package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeAndLoginPage extends Application {

    private Stage primaryStage;
    private LoginController loginController;
    private Patient patient;
    
    public WelcomeAndLoginPage() {}
    public WelcomeAndLoginPage(Patient patient) {
    	this.patient = patient;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Welcome to MEDIATE");

        // Initialize the LoginController
        loginController = new LoginController(primaryStage, patient);

        // Header
        Text headerText = new Text("MEDIATE");
        headerText.setFont(Font.font(30));

        // Subheader
        Text subHeaderText = new Text("Empowering health, One click at a time");

        // Buttons
        Button employeeLoginButton = new Button("Employee Login");
        Button patientLoginButton = new Button("Patient Login");

        // Employee login button action
        employeeLoginButton.setOnAction(e -> loginController.showEmployeeLoginPage());

        // Patient login button action
        patientLoginButton.setOnAction(e -> loginController.showPatientLoginPage());

        // Layout for welcome page
        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(employeeLoginButton, patientLoginButton);

        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(50));
        welcomeLayout.getChildren().addAll(headerText, subHeaderText, buttonLayout);

        // Scene for welcome page
        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class LoginController {

    private Stage primaryStage;
    private Patient patient;

    public LoginController(Stage primaryStage, Patient patient) {
        this.primaryStage = primaryStage;
        this.patient = patient;
    }

    public void showEmployeeLoginPage() {
        primaryStage.setTitle("Employee Login");

        // Header
        Text headerText = new Text("Employee Login");
        headerText.setFont(Font.font(15));

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        // Horizontal layout for first name and last name
        HBox nameLayout = new HBox(10);
        nameLayout.getChildren().addAll(firstNameField, lastNameField);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Birthday field
        TextField birthdayField = new TextField();
        birthdayField.setPromptText("Birthday");

        // Login button
        Button loginButton = new Button("Login");

        // Back button
        Button backButton = new Button("<- Back");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage(patient);
            welcomePage.start(primaryStage);
        });

        // Horizontal layout for birthday field, login button, and back button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(birthdayField, loginButton, backButton);

        // Welcome Back text
        Text welcomeBackText = new Text("Welcome back");
        welcomeBackText.setFont(Font.font(25));

        // Layout for login page
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));

        loginLayout.getChildren().addAll(welcomeBackText, headerText, nameLayout, passwordField, bottomLayout);

        // Scene for login page
        Scene loginScene = new Scene(loginLayout, 400, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public void showPatientLoginPage() {
        primaryStage.setTitle("Patient Login");

        // Header
        Text headerText = new Text("Patient Login");
        headerText.setFont(Font.font(15));

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        // Horizontal layout for first name and last name
        HBox nameLayout = new HBox(10);
        nameLayout.getChildren().addAll(firstNameField, lastNameField);

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Birthday field
        TextField birthdayField = new TextField();
        birthdayField.setPromptText("Birthday");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            primaryStage.setTitle("Patient Home");
            PatientHomeTab patientHomeTab = new PatientHomeTab(primaryStage, patient); // Create an instance of PatientHomeTab
            primaryStage.setScene(new Scene(patientHomeTab, 800, 600)); // Set the scene to the patient home tab
            primaryStage.show();
        });

        // Back button
        Button backButton = new Button("<- Back");
        backButton.setOnAction(e -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage(patient);
            welcomePage.start(primaryStage);
        });

        // Horizontal layout for birthday field, login button, and back button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(birthdayField, loginButton, backButton);

        // Welcome Back text
        Text welcomeBackText = new Text("Welcome back");
        welcomeBackText.setFont(Font.font(25));

        // Sign up hyperlink
        Hyperlink signUpLink = new Hyperlink("Sign up here");
        signUpLink.setOnAction(e -> {
        // Assuming you have a method showSignUpPage() that displays the sign-up page
        showSignUpPage();
    });

        // New to Mediate? text with hyperlink
        Text newToText = new Text("New to Mediate? ");
        HBox signUpBox = new HBox(newToText, signUpLink);
        signUpBox.setAlignment(Pos.CENTER);


        // Layout for login page
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));

        loginLayout.getChildren().addAll(welcomeBackText, headerText, nameLayout, passwordField, bottomLayout, signUpBox);

//    // Method to display the sign-up page
//    public void showSignUpPage() {
//    SignUpPage signUpPage = new SignUpPage();
//    signUpPage.start(primaryStage);
//}

        // Scene for login page
        Scene loginScene = new Scene(loginLayout, 400, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

	private void showSignUpPage() {
		// TODO Auto-generated method stub
		SignUpPage signUpPage = new SignUpPage();
	    signUpPage.start(primaryStage);
	}
}