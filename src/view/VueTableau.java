package view;

import java.util.ArrayList;
import java.util.List;
import application.Main;
import controller.ControllerAccueil;
import controller.ControllerTableau;
import controller.DragDropHandler;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;
import utilitaire.Utility;

import static utilitaire.Utility.creerImage;
import static utilitaire.Utility.creerImageView;

public class VueTableau {
	//-----------------------------------------ATTRIBUTS---------------------------------------------------------------------
	ControllerTableau controllerTableau;
	DragDropHandler controllerEvent;
	ControllerAccueil controllerAccueil;

	List<Carte> cartePioche;
	List<Carte> carteSurTableau;

	BorderPane root = new BorderPane();
	VBox top = new VBox();
	HBox navBar = new HBox();
	HBox hautTableauJeu = new HBox();
		HBox pioche = new HBox();
		VBox carteDevoilePioche= new VBox();
		VBox cartesCachePioche= new VBox();
		int index;

	HBox pileFondation = new HBox();
		VBox pileCoeur = new VBox();
		VBox pilePique = new VBox();
		VBox pileCarreau = new VBox();
		VBox pileTrefle = new VBox();
	
	GridPane  basTableauJeu = new GridPane ();
		
	List<VBox> listColl = new ArrayList<VBox>();
		VBox colonne1 = new VBox();
		VBox colonne2 = new VBox();
		VBox colonne3 = new VBox();
		VBox colonne4 = new VBox();
		VBox colonne5 = new VBox();
		VBox colonne6 = new VBox();
		VBox colonne7 = new VBox();

	//---------------------------------------CONSTRUCTEUR-----------------------------------------------------------------------

	public VueTableau(ControllerTableau controllerTableau, DragDropHandler controllerDragDrop,ControllerAccueil controllerAccueil) {
		this.controllerTableau = controllerTableau;
		this.controllerEvent = controllerDragDrop;
		this.cartePioche = controllerTableau.getPioche();
		this.carteSurTableau = controllerTableau.getCarteSurTableau();
		this.controllerAccueil = controllerAccueil;
		afficherTableau();
	}

	//---------------------------------------AUTRES METHODES-----------------------------------------------------------------------
	public void afficherTableau() {

	    Region spacer = new Region();
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    
		hautTableauJeu.setId("hautTableauJeu");
		basTableauJeu.setId("basTableauJeu");
		
		cartesCachePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("verso.jpg")));
		carteDevoilePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("pioche.png")));
		carteDevoilePioche.setPrefHeight(250);
			
		pileCoeur.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCoeur.png")));
		pileCoeur.setId("pileCoeur");
		

		pilePique.getChildren().add(Utility.creerImageView(Utility.creerImage("pilePique.png")));
		pilePique.setId("pilePique");

		pileCarreau.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCarreau.png")));
		pileCarreau.setId("pileCarreau");

		pileTrefle.getChildren().add(Utility.creerImageView(Utility.creerImage("pileTrefle.png")));
		pileTrefle.setId("pileTrefle");

		carteDevoilePioche.setId("pioche");

		listColl.add(colonne1);
		colonne1.setId("colonne1");

		listColl.add(colonne2);
		colonne2.setId("colonne2");

		listColl.add(colonne3);
		colonne3.setId("colonne3");

		listColl.add(colonne4);
		colonne4.setId("colonne4");

		listColl.add(colonne5);
		colonne5.setId("colonne5");

		listColl.add(colonne6);
		colonne6.setId("colonne6");

		listColl.add(colonne7);
		colonne7.setId("colonne7");

		Button retourMenu = new Button("Retour Menu");
		retourMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	new VueAccueil(controllerAccueil);
            }
        });
		retourMenu.getStyleClass().add("boutonTableau");
		navBar.getChildren().add(retourMenu);
		
		pioche.getChildren().addAll(cartesCachePioche,carteDevoilePioche);
		pioche.setSpacing(10);
		pioche.setId("VboxPioche");
		pileFondation.getChildren().addAll(pileCoeur,pilePique,pileCarreau,pileTrefle);
		pileFondation.setId("fondation");
		pileFondation.setSpacing(10);
		pileFondation.setPadding(new Insets(0, 40, 0, 0));

			for(int i=0;i<7;i++) {
				for(int j=0;j< i+1;j++) {
					ImageView iv = Utility.creerImageViewCarteVerso(carteSurTableau.get(index));
//					iv.setTranslateY(j*5);
						listColl.get(i).getChildren().add(iv);
						index++;
				}
			}

		controllerEvent.eventDrop(colonne1,controllerTableau);
		controllerEvent.eventDrop(colonne2,controllerTableau);
		controllerEvent.eventDrop(colonne3,controllerTableau);
		controllerEvent.eventDrop(colonne4,controllerTableau);
		controllerEvent.eventDrop(colonne5,controllerTableau);
		controllerEvent.eventDrop(colonne6,controllerTableau);
		controllerEvent.eventDrop(colonne7,controllerTableau);
		controllerEvent.eventDrop(pileCarreau,controllerTableau);
		controllerEvent.eventDrop(pileCoeur,controllerTableau);
		controllerEvent.eventDrop(pileTrefle,controllerTableau);
		controllerEvent.eventDrop(pilePique,controllerTableau);

		
		afficherDerniereCarteColonneEtDragable();

		afficherNouvelleCartePioche();
		miseAjourAffichageDerniereCarteColonne();
		
		affichageFinPartie();

/* ****************** Style de la page ****************** */
		colonne1.setSpacing(-200);
		colonne2.setSpacing(-200);
		colonne3.setSpacing(-200);
		colonne4.setSpacing(-200);
		colonne5.setSpacing(-200);
		colonne6.setSpacing(-200);
		colonne7.setSpacing(-200);

		hautTableauJeu.setPadding(new Insets(5));
		hautTableauJeu.getChildren().addAll(pioche,spacer,pileFondation);
		top.getChildren().addAll(navBar,hautTableauJeu);
		basTableauJeu.add(colonne1, 0, 0);
		basTableauJeu.add(colonne2, 1, 0);
		basTableauJeu.add(colonne3, 2, 0);
		basTableauJeu.add(colonne4, 3, 0);
		basTableauJeu.add(colonne5, 4, 0);
		basTableauJeu.add(colonne6, 5, 0);
		basTableauJeu.add(colonne7, 6, 0);
		basTableauJeu.setAlignment(Pos.TOP_CENTER);

		root.setTop(top);
		root.setCenter(basTableauJeu);
		root.getStyleClass().add("bg");
	    root.setPadding(new Insets(0, 20, 0, 20));

		/* ****************** set de la scene sur la stage ****************** */
		Scene scene = new Scene(root);
		try {
        	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());	 
        } catch (NullPointerException e) {
            System.err.println("Le fichier css est introuvable : " + e.getMessage());
        }
		Main.setMainScene(scene);
	}
/**
 * Méthode permettant d'afficher l' imageView de la pioche
 * */
	public void afficherNouvelleCartePioche() {

		cartesCachePioche.setOnMouseClicked(new EventHandler <MouseEvent>(){
			public void handle(MouseEvent event) {

				System.out.println("cptPioche : " + controllerTableau.getCptPioche() + " Taille pioche : "+cartePioche.size());
				if(cartePioche.size()== controllerTableau.getCptPioche()){
					controllerTableau.setCptPioche(controllerTableau.getCptPioche() - 1);
				}
				if(controllerTableau.getCptPioche() != -1){
					cartePioche.get(controllerTableau.getCptPioche()).setImageCarteAafficher(ImageCarte.VERSO);
				}
				controllerTableau.setCptPioche(controllerTableau.getCptPioche() + 1);
				System.out.println("cptPioche : " + controllerTableau.getCptPioche() + " Taille pioche : "+cartePioche.size());
				if(cartePioche.size()==1 && controllerTableau.getCptPioche()==0){
					System.out.println("cartePioche.size()==1 && controllerTableau.getCptPioche()==0 = " + (cartePioche.size()==1 && controllerTableau.getCptPioche()==0));
					cartesCachePioche.getChildren().clear();
				}
				ImageView imageViewCarteDePioche = (ImageView) carteDevoilePioche.getChildren().getFirst();

				if(cartePioche.size()==controllerTableau.getCptPioche()){

					imageViewCarteDePioche.setImage(Utility.creerImage("piocheRetour.png"));
					controllerTableau.setCptPioche(-1);

				}else {
					imageViewCarteDePioche.setImage(Utility.creerImage(cartePioche.get(controllerTableau.getCptPioche()).getImg_carte()));
					if(controllerTableau.getCptPioche() != -1){

						cartePioche.get(controllerTableau.getCptPioche()).setImageCarteAafficher(ImageCarte.RECTO);
						imageViewCarteDePioche.setUserData(cartePioche.get(controllerTableau.getCptPioche()));
						controllerEvent.eventDrag(imageViewCarteDePioche,controllerTableau);
					}
				}

			}
		});

	}

	/**
	 * méthode permettant de remplacer l'image verso de la derniere carte de chaque pile pour la remplacer par la carte coté recto
	 */
	public void afficherDerniereCarteColonneEtDragable() {

		for(int i=0;i<listColl.size();i++) {
			VBox colonne = listColl.get(i);
			//Si la colonne est vide on ajoute l'imageView de carte vide pour pouvoir ajouter un roi dessus
			if(colonne.getChildren().isEmpty()){
				ImageView imageViewPile = creerImageView(creerImage("pioche.png"));
				imageViewPile.setId("carteVide_"+colonne.getId());
				colonne.getChildren().add(imageViewPile);
				continue;
			}
			// vérification de l'imageView
			// (si c'est la carte vide, sans ça, ça renvoie une NullPointerException à derniereCarte.getImageCarteAafficher())
			Node dernierNoeud = colonne.getChildren().getLast();
			//Si le dernier noeud n'est pas une imageview on reboucle
			if (!(dernierNoeud instanceof ImageView iv)) continue;
			//si la dernière imageview n'est pas une carte on reboucle
			if(!(iv.getUserData() instanceof Carte))continue;

			Carte derniereCarte = (Carte) dernierNoeud.getUserData();

			if( !(derniereCarte.getImageCarteAafficher()==null)){
				if(derniereCarte.getImageCarteAafficher() == ImageCarte.VERSO){
					listColl.get(i).getChildren().remove((ImageView) listColl.get(i).getChildren().getLast());
					derniereCarte.setImageCarteAafficher(ImageCarte.RECTO);

					ImageView ivDerniereCarte = Utility.creerImageView(Utility.creerImage(derniereCarte.getImg_carte()));
					ivDerniereCarte.setUserData(derniereCarte);
					ivDerniereCarte.setId("ImageView de " + derniereCarte.toString());
					listColl.get(i).getChildren().add(ivDerniereCarte);
					if (derniereCarte.getImageCarteAafficher() == ImageCarte.RECTO) {
					controllerEvent.eventDrag(ivDerniereCarte,controllerTableau);
					}
				}
			}
		}
	}
	/**
	 * méthode permettant d'appeler AffichageDerniereCarteColonne() si une carte est retiré d'une des piles
	 */
	public void miseAjourAffichageDerniereCarteColonne(){
    	for (VBox colonne : listColl) {
        	colonne.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
					Platform.runLater(this::afficherDerniereCarteColonneEtDragable);
                }
            }
        });
    	}
	}

	/**
	 * Méthode permettant d'appeler la méthode du controlleur pour vérifier la fin de partie quand une carte est changé de pile
	 */
	public void affichageFinPartie() {
		for (VBox colonne : listColl) {
			colonne.getChildren().addListener((ListChangeListener<Node>) change -> {
				while (change.next()) {
					if(change.wasAdded()||change.wasRemoved()) {
						boolean victoire = controllerTableau.finDePartie();
						
						if(victoire) {
							alertFinPartie();
						}
					}
										
				}
			});
		}
	}

	/**
	 * Méthode permettant afficher une dialog pour rejouer ou retourner au menu
	 */
	public void alertFinPartie() {

	      Dialog<ButtonType> dialog = new Dialog<>();
	      dialog.setTitle("Victoire !");
	      ButtonType boutonRejouer = new ButtonType("Rejouer", ButtonData.OK_DONE);
	      ButtonType boutonMenu = new ButtonType("Menu Principale", ButtonData.BACK_PREVIOUS);

	      dialog.setContentText("Que voulez-vous faire ?");

	      dialog.getDialogPane().getButtonTypes().addAll(boutonRejouer,boutonMenu);

	      dialog.showAndWait().ifPresent(response -> {
	    	  if (response == boutonRejouer) {
	    		  controllerTableau.rejouerPartie();
	    	  }else if (response == boutonMenu) {
	    		  controllerTableau.retourMenu();
	    	  }
	    	  
	      });
	      
	}     
}
	



