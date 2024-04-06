package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
 @Override
 public void start(Stage primaryStage) {
     try {
         StaffHomeTab staffHomeTab = new StaffHomeTab();
         Scene scene = new Scene(staffHomeTab, 800, 600);
         scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         primaryStage.setScene(scene);
         primaryStage.setTitle("Mediate - Staff Home");
         primaryStage.show();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

 public static void main(String[] args) {
     launch(args);
 }
}