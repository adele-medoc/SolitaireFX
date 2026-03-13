package view;

import application.Main;
import controller.ControllerAccueil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VueAccueil {
	ControllerAccueil controllerAccueil;
	
	public VueAccueil(ControllerAccueil controllerAccueil) {
		this.controllerAccueil = controllerAccueil;
		afficherAccueil();
	}
	public void afficherAccueil() {
		// ----------------------- ATTRIBUTS DE LA VUE -------------------------
		StackPane root = new StackPane();
		VBox accueil = new VBox();
		VBox buttonVb = new VBox();
		Button buttonJouer = new Button("Jouer");
		Button buttonScore = new Button("Meilleur Score");
		buttonJouer.getStyleClass().add("bouton");
		buttonJouer.setId("boutonAccueil");
		buttonScore.getStyleClass().add("bouton");

		try {

				Image img = new Image(getClass().getResource("/images/fondMenu.png").toExternalForm());
				ImageView iv = new ImageView(img);
				accueil.getChildren().add(iv);

				Image imgTitre = new Image(getClass().getResource("/images/titre1.png").toExternalForm());
				ImageView ivTitre = new ImageView(imgTitre);
				ivTitre.setId("imgAccueil");
				buttonVb.getChildren().add(ivTitre);

		}catch(Exception e) {
            System.out.println("Main : Erreur lors du chargement du background " +e);
        }


		// ----------------------- BOUTTON QUI APPELLE LE CONSTRUCTEUR POUR DIRIGER VERS LA BONNE VUE -------------------------
		buttonJouer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				controllerAccueil.setChoix(1);
				controllerAccueil.choisirJeu();
            }
        });

		buttonScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				controllerAccueil.setChoix(2);
				controllerAccueil.choisirJeu();
            }
        });

		// ----------------------- STYLE DE LA VUE -------------------------
//		buttonVb.getChildren().addAll(buttonJouer,buttonScore);
		buttonVb.getChildren().add(buttonJouer);
		buttonVb.setAlignment(Pos.CENTER);
		buttonVb.setSpacing(15);


		root.getChildren().add(accueil);
		root.getChildren().add(buttonVb);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root);
		try {
        	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());	 
        } catch (NullPointerException e) {
            System.err.println("Le fichier css est introuvable : " + e.getMessage());
        }
		Main.setMainScene(scene);
	}
	}


