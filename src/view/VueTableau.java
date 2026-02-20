package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import controller.ControllerTableau;
import controller.DragDropHandler;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.WritableIntegerValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	//List<Carte> carteDepart = new ArrayList<Carte>();
	List<Carte> cartePioche;
	List<Carte> carteSurTableau;

	BorderPane root = new BorderPane();
	GridPane  hautTableauJeu = new GridPane ();
		HBox pioche = new HBox();
		VBox carteDevoilePioche= new VBox();
		VBox cartesCachePioche= new VBox();
		//int cptPioche;
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

	public VueTableau(ControllerTableau controllerTableau, DragDropHandler controllerDragDrop) {
		this.controllerTableau = controllerTableau;
		this.controllerEvent = controllerDragDrop;
		this.cartePioche = controllerTableau.getPioche();
		this.carteSurTableau = controllerTableau.getCarteSurTableau();
		//this.cptPioche = controllerTableau.getCptPioche();
		afficherTableau();
	}

	//---------------------------------------AUTRES METHODES-----------------------------------------------------------------------
	public void afficherTableau() {

		cartesCachePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("verso.jpg")));
		//cartesCachePioche.setStyle("-fx-background-color : CRIMSON; ");
		carteDevoilePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("pioche.png")));
		//carteDevoilePioche.setStyle("-fx-background-color : DARKMAGENTA;");
		//carteDevoilePioche.setPrefWidth(400);
		carteDevoilePioche.setPrefHeight(300);
			
		pileCoeur.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCoeur.png")));
		pileCoeur.setId("pileCoeur");
		//pileCoeur.setStyle("-fx-background-color : red; ");

		pilePique.getChildren().add(Utility.creerImageView(Utility.creerImage("pilePique.png")));
		pilePique.setId("pilePique");
		//pilePique.setStyle("-fx-background-color : black; ");

		pileCarreau.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCarreau.png")));
		pileCarreau.setId("pileCarreau");
		//pileCarreau.setStyle("-fx-background-color : orange; ");

		pileTrefle.getChildren().add(Utility.creerImageView(Utility.creerImage("pileTrefle.png")));
		pileTrefle.setId("pileTrefle");
		//pileTrefle.setStyle("-fx-background-color : grey; ");

		carteDevoilePioche.setId("pioche");

		listColl.add(colonne1);
		colonne1.setId("colonne1");
		//colonne1.getStyleClass().add("colonne");

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

		
		pioche.getChildren().addAll(cartesCachePioche,carteDevoilePioche);
		pioche.setPadding(new Insets(0, 10, 0, 10));
		pileFondation.getChildren().addAll(pileCoeur,pilePique,pileCarreau,pileTrefle );
		
		//System.out.println("VueTableau / taille du jeu avant de distribuer les carte : "+ carteDepart.size());
		/**
		 * Distribution des cartes sur le plateau de jeu
		 * todo franchement j'ai un doute sur l'affichage des cartes
		 */
			for(int i=0;i<7;i++) {
				for(int j=0;j< i+1;j++) {
					ImageView iv = Utility.creerImageViewCarteVerso(carteSurTableau.get(index));
//					iv.setTranslateY(j*5);
						listColl.get(i).getChildren().add(iv);
						index++;
				}
			}
	/*
	* TODO afficher la dernière carte et appliquer le drag sur les cartes versos n'est appelé qu'une fois, il faut rappeler le bloc à chaque fois qu'une carte est dévoilé
	* */

		afficherDerniereCarteColonneEtDragable();

		afficherNouvelleCartePioche();
		miseAjourAffichageDerniereCarteColonne();


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

		colonne1.setSpacing(-200);
		colonne2.setSpacing(-200);
		colonne3.setSpacing(-200);
		colonne4.setSpacing(-200);
		colonne5.setSpacing(-200);
		colonne6.setSpacing(-200);
		colonne7.setSpacing(-200);

		//hautTableauJeu.getChildren().addAll(pioche,pileFondation);
		hautTableauJeu.setPadding(new Insets(20));
		hautTableauJeu.setHgap(50); // espace horizontal entre les éléments
		hautTableauJeu.setVgap(10); // espace vertical si tu rajoutes des lignes
		
		hautTableauJeu.add(pioche, 0, 0);
		hautTableauJeu.add(pileCoeur, 1, 0);
		hautTableauJeu.add(pilePique, 2, 0);
		hautTableauJeu.add(pileCarreau, 3, 0);
		hautTableauJeu.add(pileTrefle, 4, 0);
		
		//basTableauJeu.getChildren().addAll(listColl);
		basTableauJeu.add(colonne1, 0, 0);
		basTableauJeu.add(colonne2, 1, 0);
		basTableauJeu.add(colonne3, 2, 0);
		basTableauJeu.add(colonne4, 3, 0);
		basTableauJeu.add(colonne5, 4, 0);
		basTableauJeu.add(colonne6, 5, 0);
		basTableauJeu.add(colonne7, 6, 0);
		

		root.setTop(hautTableauJeu);
		root.setCenter(basTableauJeu); 
		root.getStyleClass().add("bg");
	    root.setPadding(new Insets(0, 10, 0, 10));
	    
	    
		Scene scene = new Scene(root);
		try {
        	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());	 
        } catch (NullPointerException e) {
            System.err.println("Le fichier css est introuvable : " + e.getMessage());
        }
		Main.setMainScene(scene);
	}

	public void afficherNouvelleCartePioche() {
		cartesCachePioche.setOnMouseClicked(new EventHandler <MouseEvent>(){
			public void handle(MouseEvent event) {
				//System.out.println("VUE | cptPioche : " + cptPioche + " Taille pioche : "+cartePioche.size());
				if(cartePioche.size()== controllerTableau.getCptPioche()){
					controllerTableau.setCptPioche(controllerTableau.getCptPioche() - 1);
				}
				if(controllerTableau.getCptPioche() != -1){
					cartePioche.get(controllerTableau.getCptPioche()).setImageCarteAafficher(ImageCarte.VERSO);
				}
				controllerTableau.setCptPioche(controllerTableau.getCptPioche() + 1);
				//System.out.println("Début de la méthode afficherNouvelleCartePioche " + carteDepart.get(cptPioche).toString());
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
					//System.out.println("Fin de la méthode afficherNouvelleCartePioche " + carteDepart.get(cptPioche).toString());
				}

			}
		});

	}

	/**
	 * méthode permettant de remplacer l'image de la derniere carte de chaque pile pour la remplacer par la carte coté recto
	 */
	public void afficherDerniereCarteColonneEtDragable() {

		for(int i=0;i<listColl.size();i++) {
			VBox colonne = listColl.get(i);
			//Si la colonne est vide on ajoute l'imageView de carte vide pour pouvoir ajouter un roi dessus
			if(colonne.getChildren().isEmpty()){
				System.out.println("--------------------------Colonne vide " + colonne.getId());
				ImageView imageViewPile = creerImageView(creerImage("pioche.png"));

				imageViewPile.setId("carteVide_"+colonne.getId());
				System.out.println(imageViewPile.getId());
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
	public void miseAjourAffichageDerniereCarteColonne(){

    	for (VBox colonne : listColl) {

        	colonne.getChildren().addListener((ListChangeListener<Node>) change -> {
			System.out.println("*********************************Listener actif sur " + colonne.getId());
            while (change.next()) {
				System.out.println("changement détecté dans la colonne "+ colonne.getId());
                if (change.wasRemoved()) {
					System.out.println("Suppression détectée dans " + colonne.getId());
					Platform.runLater(this::afficherDerniereCarteColonneEtDragable);
//					if(colonne.getChildren().isEmpty()){
//						System.out.println("--------------------------Colonne vide " + colonne.getId());
//						ImageView imageViewPile = creerImageView(creerImage("pioche.png"));
//						imageViewPile.setId("carteVide_"+colonne.getId());
//						System.out.println(imageViewPile.getId());
//						colonne.getChildren().add(imageViewPile);
//
//					}
                }
            }
        });
    	}
	}



	/**
	*	méthode pour le controle des évenemments drag, grâce à la sources qui est une imageView
	*/
}


