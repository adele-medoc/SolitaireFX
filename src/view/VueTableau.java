package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import controller.ControllerTableau;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
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

public class VueTableau {
	//-----------------------------------------ATTRIBUTS---------------------------------------------------------------------
	ControllerTableau controller;
	//ArrayList<Carte> carteDepart;
	List<Carte> carteDepart = new ArrayList<Carte>();
	List<Carte> carteSurTableau = new ArrayList<Carte>();
	BorderPane root = new BorderPane();
	
	GridPane  hautTableauJeu = new GridPane ();
		HBox pioche = new HBox();
		VBox carteDevoilePioche= new VBox();
		VBox cartesCachePioche= new VBox();
		int cptPioche = -1;

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
	
	public VueTableau(List<Carte> paquet, ControllerTableau controllerTableau) {
		this.carteDepart = paquet;
		this.controller = controllerTableau;
		afficherTableau();
	}

	//---------------------------------------AUTRES METHODES-----------------------------------------------------------------------
	public void afficherTableau() {

		cartesCachePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("verso.jpg")));
		cartesCachePioche.setStyle("-fx-background-color : CRIMSON; ");
		carteDevoilePioche.getChildren().add(Utility.creerImageView(Utility.creerImage("pioche.png")));
		carteDevoilePioche.setStyle("-fx-background-color : DARKMAGENTA;");
		carteDevoilePioche.setPrefWidth(400);
		carteDevoilePioche.setPrefHeight(400);
			
		pileCoeur.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCoeur.png")));
		pileCoeur.setId("pileCoeur");
		pileCoeur.setStyle("-fx-background-color : red; ");

		pilePique.getChildren().add(Utility.creerImageView(Utility.creerImage("pilePique.png")));
		pilePique.setId("pilePique");
		pilePique.setStyle("-fx-background-color : black; ");

		pileCarreau.getChildren().add(Utility.creerImageView(Utility.creerImage("pileCarreau.png")));
		pileCarreau.setId("pileCarreau");
		pileCarreau.setStyle("-fx-background-color : orange; ");

		pileTrefle.getChildren().add(Utility.creerImageView(Utility.creerImage("pileTrefle.png")));
		pileTrefle.setId("pileTrefle");
		pileTrefle.setStyle("-fx-background-color : grey; ");

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
		
		System.out.println("VueTableau / taille du jeu avant de distribuer les carte : "+ carteDepart.size());
		/**
		 * Distribution des cartes sur le plateau de jeu : carteSurTableau venant du paquet de carte : carteDepart
		 * et suppréssion des cartes distribué de carteDepart. carteDepart devient donc par la suite la pioche
		 */
			for(int i=0;i<7;i++) {
				for(int j=0;j< i+1;j++) {
//					ListCarte.get(i).add(carteDepart.getLast());
						listColl.get(i).getChildren().add(Utility.creerImageViewCarteVerso(carteDepart.getLast()));
//						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! colonne " +i+" listColl " + listColl.get(i));
						carteSurTableau.add(carteDepart.getLast());				
						carteDepart.remove(carteDepart.getLast());
//						System.out.println("\n VueTableau: i = "+i+" j =" +j + "  taille du jeu : "+ carteDepart.size()); 
//						System.out.println("VueTableau: taille carteSurTableau : "+ carteSurTableau.size()+ "  carte ajouté : "+ carteSurTableau.toString()+"\n ");
				}
			}
	/*
	* TODO afficher la dernière carte et appliquer le drag sur les cartes versos n'est appelé qu'une fois, il faut rappeler le bloc à chaque fois qu'une carte est dévoilé
	* */


		/*for(int i=0;i<listColl.size();i++) {
            ImageView iv = (ImageView) listColl.get(i).getChildren().getLast();
			Carte c = (Carte) iv.getUserData();
			//System.out.println("****************************** iv.getUserData() = " + iv.getUserData());
           if (c.getImageCarteAafficher() == ImageCarte.RECTO) {
                eventDrag(iv);
            } --> maintenant dans la méthode afficherDerniereCarteColonneEtDragable()
        }*/
		afficherDerniereCarteColonneEtDragable();
		afficherNouvelleCartePioche();

		eventDrop(colonne1);
		eventDrop(colonne2);
		eventDrop(colonne3);
		eventDrop(colonne4);
		eventDrop(colonne5);
		eventDrop(colonne6);
		eventDrop(colonne7);
		eventDrop(pileCarreau);
		eventDrop(pileCoeur);
		eventDrop(pileTrefle);
		eventDrop(pilePique);


		//carteDepart.subList(0, cpt).clear();
		System.out.println("VueTableau / carte restant dans carteDépart : "+ carteDepart.size() +" carteSurTableau : "+carteSurTableau.size()+"    carte de départ : "+ carteDepart.toString());
			
		
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
				System.out.println("cpt pioche : " + cptPioche);
				if(cptPioche != -1){
					carteDepart.get(cptPioche).setImageCarteAafficher(ImageCarte.VERSO);
				}
				cptPioche++;
				//System.out.println("Début de la méthode afficherNouvelleCartePioche " + carteDepart.get(cptPioche).toString());
				ImageView imageViewCarteDePioche = (ImageView) carteDevoilePioche.getChildren().getFirst();

				if(carteDepart.size()==cptPioche){

					imageViewCarteDePioche.setImage(Utility.creerImage("piocheRetour.png"));
					cptPioche =-1;

				}else {
					imageViewCarteDePioche.setImage(Utility.creerImage(carteDepart.get(cptPioche).getImg_carte()));
					if(cptPioche != -1){

						carteDepart.get(cptPioche).setImageCarteAafficher(ImageCarte.RECTO);
						imageViewCarteDePioche.setUserData(carteDepart.get(cptPioche));
						eventDrag(imageViewCarteDePioche);
					}
					//System.out.println("Fin de la méthode afficherNouvelleCartePioche " + carteDepart.get(cptPioche).toString());
				}

			}
		});
	}

	/**
	 * méthode permettant de remplacer l'image de la derniere carte de chaque pile pour la remplacer par la carte coté recto

	 for(int i=0;i<listColl.size();i++) {
	 ImageView iv = (ImageView) listColl.get(i).getChildren().getLast();
	 Carte c = (Carte) iv.getUserData();
	 //System.out.println("****************************** iv.getUserData() = " + iv.getUserData());
	 if (c.getImageCarteAafficher() == ImageCarte.RECTO) {
	 eventDrag(iv);
	 }
	 }
	 */
	public void afficherDerniereCarteColonneEtDragable() {
		for(int i=0;i<listColl.size();i++) {

			Carte derniereCarte = (Carte) listColl.get(i).getChildren().getLast().getUserData();
			listColl.get(i).getChildren().remove((ImageView) listColl.get(i).getChildren().getLast());
			derniereCarte.setImageCarteAafficher(ImageCarte.RECTO);

			ImageView ivDerniereCarte = Utility.creerImageView(Utility.creerImage(derniereCarte.getImg_carte()));
			ivDerniereCarte.setUserData(derniereCarte);
			ivDerniereCarte.setId("ImageView de " + derniereCarte.toString());
			listColl.get(i).getChildren().add(ivDerniereCarte);
			if (derniereCarte.getImageCarteAafficher() == ImageCarte.RECTO) {
				eventDrag(ivDerniereCarte);
			}
			//ivDerniereCarte.setImage(creerImage(derniereCarte.getImg_carte()));
			//System.out.println("derniere carte = " + derniereCarte + " pour  i = " + i + "Image à afficher : " + derniereCarte.getImageCarteAafficher());
		}	
	}
	/**
	*	méthode pour le controle des évenemments drag, grâce à la sources qui est une imageView
	*/
	public void eventDrag(ImageView noeud){
		// Add mouse event handlers for the source
		noeud.setOnMousePressed(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				noeud.setMouseTransparent(true);
				//System.out.println("Event on Source: mouse pressed");
				event.setDragDetect(true);
			}
		});

		noeud.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				noeud.setMouseTransparent(false);
				//System.out.println("Event on Source: mouse released");
			}
		});

		noeud.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				//System.out.println("Event on Source: mouse dragged");
				event.setDragDetect(false);
			}
		});

		noeud.setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				noeud.startFullDrag();
				//System.out.println("Event on Source: drag detected");
			}
		});

	}
	/*   méthode pour les le controle des évenemments drop, le target est situé sur un VBox
	 * */
	public void eventDrop(VBox noeud){
		//System.out.println("************** passé par eventdragndrop VBox ***************");

		noeud.setOnMouseDragEntered(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				//System.out.println("Event on Target: mouse dragged");
			}
		});

		noeud.setOnMouseDragOver(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				//System.out.println("Event on Target: mouse drag over");
			}
		});

		noeud.setOnMouseDragReleased(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				//System.out.println("Event on Target: mouse drag released");
				/*Appelle au controlleur pour savoir quoi faire lors du relachement de la souris */
				EventType<MouseDragEvent> eventType = event.getEventType();
				ImageView ivSource = (ImageView) event.getGestureSource();
				VBox vBoxSource = (VBox) ivSource.getParent();
				Carte carteSource = (Carte) ivSource.getUserData();
				System.out.println("source.getUserData() : " + ivSource.getUserData().toString());
				System.out.println("event.getGestureSource() : " + (event.getGestureSource()));
				System.out.println("event.getGestureSource().getParents() : " + (((ImageView) event.getGestureSource()).getParent()));
				VBox vBoxTarget = noeud;
				Carte carteTarget = (Carte)vBoxTarget.getChildren().getLast().getUserData();;
				System.out.println("target = " + vBoxTarget.toString() +" carte target : "+ carteTarget);

				controller.carteEstDeposable(carteSource,ivSource,vBoxSource,carteTarget,vBoxTarget,cptPioche,carteDepart,carteSurTableau);




			}
		});

		noeud.setOnMouseDragExited(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				//System.out.println("Event on Target: mouse drag exited");
			}
		});

	}
}


