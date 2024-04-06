package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Root layout
		BorderPane root = new BorderPane();

		// MenuBar and Menus
		MenuBar menuBar = new MenuBar();
		Menu homeMenu = new Menu("Home");
		Menu recordMenu = new Menu("Health Record");
		Menu messageMenu = new Menu("Messages");
		Menu visitMenu = new Menu("Visits");
		menuBar.getMenus().addAll(homeMenu, recordMenu, messageMenu, visitMenu);

		// HBox for aligning menu to the right
		HBox menuBox = new HBox(menuBar);
		menuBox.setAlignment(Pos.TOP_RIGHT); // Align to the right

		// Welcome Message
		Label welcomeLabel = new Label("Welcome, John!");
		welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		// Slogan
		Label sloganLabel = new Label("MEDIATE\nEmpowering Health, One Click at a Time.");
		sloganLabel.setStyle("-fx-font-size: 16px;");

		// VBox for center alignment
		VBox centerBox = new VBox(welcomeLabel, sloganLabel);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setSpacing(10);

		// Set components to BorderPane
		root.setTop(menuBar);
		root.setCenter(centerBox);

		// Scene and Stage setup
		Scene scene = new Scene(root, 936, 530);
		primaryStage.setTitle("MEDIATE - Patient Home");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
