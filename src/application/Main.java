package application;

import controller.Controller;
import controller.ControllerAccueil;
import controller.ControllerTableau;
import controller.DragDropHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.VueAccueil;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	// ----------------------- ATTRIBUTS DE L'APPLICATION -------------------------
	private static Stage primaryStage;
    private static Scene mainScene;

	// ----------------------- INSTANCIATION DES CONTROLLEUR DE L'APPLICATION -------------------------
	private DragDropHandler controllerDragDrop = new DragDropHandler();
	private ControllerAccueil controllerAccueil = new ControllerAccueil(controllerDragDrop);

	@Override
	public void start(Stage stage) {

		try {
			primaryStage = stage;
			double largeur = Screen.getPrimary().getBounds().getWidth();
	        double hauteur = Screen.getPrimary().getBounds().getHeight();
			primaryStage.setWidth(largeur-70);
	        primaryStage.setHeight(hauteur-70);
	        primaryStage.setTitle("Solitaire");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		new VueAccueil(controllerAccueil);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// ----------------------- GETTER / SETTER -------------------
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage mainStage) {
		Main.primaryStage = mainStage;
	}

	public static Scene getMainScene() {
		return primaryStage.getScene();
	}

	public static void setMainScene(Scene scene) {
		Main.mainScene = scene;
		primaryStage.setScene(mainScene);
	}
	
}
