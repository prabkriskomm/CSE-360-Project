package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeAndLoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to MEDIATE");

        // Header
        Text headerText = new Text("MEDIATE");
        headerText.setFont(Font.font(30));

        // Subheader
        Text subHeaderText = new Text("Empowering health, One click at a time");

        // Buttons
        Button employeeLoginButton = new Button("Employee Login");
        Button patientLoginButton = new Button("Patient Login");

        // Employee login button action
        employeeLoginButton.setOnAction(e -> {
            // Navigate to Employee login page
            LoginPage employeeLoginPage = new LoginPage("Employee");
            employeeLoginPage.start(primaryStage);
        });

        // Patient login button action
        patientLoginButton.setOnAction(e -> {
            // Navigate to Patient login page
            LoginPage patientLoginPage = new LoginPage("Patient");
            patientLoginPage.start(primaryStage);
        });

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

class LoginPage extends Application {

    private String userType;

    public LoginPage(String userType) {
        this.userType = userType;
    }
    
    

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(userType + " Login");
        
        
        Button backButton = new Button("<-");
        backButton.setOnAction(event -> {
            WelcomeAndLoginPage welcomePage = new WelcomeAndLoginPage();
            welcomePage.start(primaryStage);
        });

        // Header
        Text headerText = new Text(userType + " Login");
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

        // Horizontal layout for birthday field and login button
        HBox bottomLayout = new HBox(10);
        bottomLayout.getChildren().addAll(birthdayField, loginButton);

        //Welcome Back text
        Text welcomeBackText = new Text("Welcome back");
        welcomeBackText.setFont(Font.font(25));
        
        // Sign up text
        Text signUpText = new Text("New to Mediate? Sign up here");
        
     // Layout for login page
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));

        // Create an HBox for the back button
        HBox backButtonLayout = new HBox(backButton);
        backButtonLayout.setAlignment(Pos.TOP_LEFT);

        // Add the back button HBox and other elements to the login layout VBox
        loginLayout.getChildren().addAll(backButtonLayout, welcomeBackText, signUpText, headerText, nameLayout, passwordField, bottomLayout);


        // Scene for login page
        Scene loginScene = new Scene(loginLayout, 400, 300);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
