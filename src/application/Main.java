package application;

import controller.ControllerAccueil;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private static Stage primaryStage;
    private static Scene mainScene;
    
    
	@Override
	public void start(Stage stage) {
		
		
		try {
			primaryStage = stage;
			primaryStage.setWidth(1920);
	        primaryStage.setHeight(1080);
	        primaryStage.setTitle("Solitaire");
	        StackPane root = new StackPane();
			VBox accueil = new VBox();
			VBox buttonVb = new VBox();			
			
			try {
				
					Image img = new Image(getClass().getResource("/images/fondSolitaire.png").toExternalForm());
					ImageView iv = new ImageView(img);
					accueil.getChildren().add(iv);
					
					Image imgTitre = new Image(getClass().getResource("/images/titre1.png").toExternalForm());
					ImageView ivTitre = new ImageView(imgTitre);
					buttonVb.getChildren().add(ivTitre);
							
			}catch(Exception e) {
	            System.out.println("Main : Erreur lors du chargement du background " +e);
	        }
					
			
			Button buttonJouer = new Button("Jouer");
			Button buttonScore = new Button("Meilleur Score");
			buttonJouer.getStyleClass().add("boutonAccueil");
			buttonScore.getStyleClass().add("boutonAccueil");
			
			buttonJouer.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	new ControllerAccueil(1);
	            }
	        });
			
			buttonScore.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	new ControllerAccueil(2);
	            }
	        });
			buttonVb.getChildren().addAll(buttonJouer,buttonScore);
			buttonVb.setAlignment(Pos.CENTER);
			buttonVb.setSpacing(15);
			
			
			root.getChildren().add(accueil);
			root.getChildren().add(buttonVb);
			root.setAlignment(Pos.CENTER);
			
			
			mainScene = new Scene(root);
	        mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(mainScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
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
