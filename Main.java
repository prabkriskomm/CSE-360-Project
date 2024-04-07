package application;
//team 9 is amazing
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
 @Override
 public void start(Stage primaryStage) {
     try {
    	 
         StaffHomeTab staffHomeTab = new StaffHomeTab();
         Scene scene = new Scene(staffHomeTab, 800, 600);
         
         PatientHomeTab PatientHomeTab = new PatientHomeTab();
         Scene scene1 = new Scene(PatientHomeTab, 800, 600);
         
         scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         primaryStage.setScene(scene1);
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