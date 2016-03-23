package viewPlatform;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	BorderPane root = new BorderPane();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			showPersonOverview();
		       
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 public void showPersonOverview() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("PersonOverview.fxml"));
	            
	            System.out.println("prova 1");
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            System.out.println("prova 2");
	            
	            // Set person overview into the center of root layout.
	            root.setCenter(personOverview);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
