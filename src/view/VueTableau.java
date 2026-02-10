package view;

import java.util.ArrayList;

import application.Main;
import controller.ControllerTableau;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;

public class VueTableau {
	//-----------------------------------------ATTRIBUTS---------------------------------------------------------------------
	ArrayList<Carte> carteDepart;
	ArrayList<Carte> carteSurTableau = new ArrayList<Carte>();
	BorderPane root = new BorderPane();
	
	GridPane  hautTableauJeu = new GridPane ();
		HBox pioche = new HBox();
		VBox carteDevoile= new VBox();
		VBox cartesCache= new VBox();
	
		HBox pileFondation = new HBox();
		VBox pileCoeur = new VBox();
		VBox pilePique = new VBox();
		VBox pileCarreau = new VBox();
		VBox pileTrefle = new VBox();
	
	GridPane  basTableauJeu = new GridPane ();
		
	ArrayList<VBox> listColl = new ArrayList<VBox>();
		VBox colonne1 = new VBox();
		VBox colonne2 = new VBox();
		VBox colonne3 = new VBox();
		VBox colonne4 = new VBox();
		VBox colonne5 = new VBox();
		VBox colonne6 = new VBox();
		VBox colonne7 = new VBox();

	//---------------------------------------CONSTRUCTEUR-----------------------------------------------------------------------
	
	public VueTableau(ArrayList<Carte> paquet) {
		carteDepart = paquet;
		afficherTableau();
	};
	//---------------------------------------AUTRES METHODES-----------------------------------------------------------------------
	public void afficherTableau() {
		
		cartesCache.getChildren().add(creerImageView(creerImage("verso.jpg")));
		carteDevoile.getChildren().add(creerImageView(creerImage("pioche.png")));
			
		pileCoeur.getChildren().add(creerImageView(creerImage("pileCoeur.png")));
		pilePique.getChildren().add(creerImageView(creerImage("pilePique.png")));
		pileCarreau.getChildren().add(creerImageView(creerImage("pileCarreau.png")));
		pileTrefle.getChildren().add(creerImageView(creerImage("pileTrefle.png")));
			
		listColl.add(colonne1);
		listColl.add(colonne2);
		listColl.add(colonne3);
		listColl.add(colonne4);
		listColl.add(colonne5);
		listColl.add(colonne6);
		listColl.add(colonne7);
		
		pioche.getChildren().addAll(cartesCache,carteDevoile);
		pioche.setPadding(new Insets(0, 10, 0, 10));
		pileFondation.getChildren().addAll(pileCoeur,pilePique,pileCarreau,pileTrefle );
		
		System.out.println("VueTableau / taille du jeu avant de distribuer les carte : "+ carteDepart.size());
		/**
		 * Distribution des cartes sur le plateau de jeu : carteSurTableau venant du paquet de carte : carteDepart
		 * et suppréssion des cartes distribué de carteDepart
		 */
			for(int i=0;i<7;i++) {
				for(int j=0;j< i+1;j++) {
//					ListCarte.get(i).add(carteDepart.getLast());
						listColl.get(i).getChildren().add(creerImageViewCarteVerso(carteDepart.getLast()));
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
		afficherDerniereCarteColonne();

		for(int i=0;i<listColl.size();i++) {
            ImageView iv = (ImageView) listColl.get(i).getChildren().getLast();
			Carte c = (Carte) iv.getUserData();
			//System.out.println("****************************** iv.getUserData() = " + iv.getUserData());
           if (c.getImageCarteAafficher() == ImageCarte.RECTO) {
                eventDrag(iv);
            }
        }

		eventDrop(colonne1);
		eventDrop(colonne2);
		eventDrop(colonne3);
		eventDrop(colonne4);
		eventDrop(colonne5);
		eventDrop(colonne6);
		eventDrop(colonne7);

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

	
	public void afficherNouvelleCartePioche(Carte carte) {
		String imgCarte;
		imgCarte = carte.getImg_carte();
		
		try {
			
			Image img = new Image(getClass().getResource('"' + imgCarte +'"' ).toExternalForm());
			ImageView iv = new ImageView(img);
			carteDevoile.getChildren().clear();
			carteDevoile.getChildren().add(iv);
					
		}catch(Exception e) {
			System.out.println("VueTableau : Erreur lors du chargement de l'image de la carte devoille de la pioche " +e);
		}
	}

	public Image creerImage(String cheminImg) {
		try {
			Image imgCarte = new Image(getClass().getResource("/images/"+cheminImg).toExternalForm());
			//ImageView imgViewCarte = new ImageView(imgCarte);
			//imgCarte.setFitHeight(244);
			//imgViewCarte.setFitWidth(170);
			//imgViewCarte.setUserData(cheminImg.getClass());

			return imgCarte;
		}catch(Exception e) {System.out.println("VueTableau: fonction afficherImg(string)"+ e);
			return null;
		}
	}
	public ImageView creerImageView(Image Img) {
		try {
		//Image imgCarte = new Image(getClass().getResource("/images/"+cheminImg).toExternalForm());
		ImageView imgViewCarte = new ImageView(Img);
		imgViewCarte.setFitHeight(244);
		imgViewCarte.setFitWidth(170);
		//imgViewCarte.setUserData(cheminImg.getClass());

		return imgViewCarte;
		}catch(Exception e) {System.out.println("VueTableau: fonction afficherImg(string)"+ e);
		return null;
		}		
	}

	public ImageView creerImageViewCarteVerso(Carte carte) {
		try {
			Image imgCarte = new Image(getClass().getResource("/images/"+carte.getImg_carte_verso()).toExternalForm());
			ImageView imgViewCarte = new ImageView(imgCarte);
			imgViewCarte.setFitHeight(244);
			imgViewCarte.setFitWidth(170);
			imgViewCarte.setUserData(carte);
			//System.out.println("creerImageViewCarteVerso | imgViewCarte.setUserData(carte) = " + imgViewCarte.getUserData());
			return imgViewCarte;
			}catch(Exception e) {System.out.println("VueTableau: creerImageViewCarteVerso | fonction afficherImg(carte)"+ e);
			return null;
			}
	}

	/**
	 * méthode permettant de remplacer l'image de la derniere carte de chaque pile pour la remplacer par la carte coté recto
	 */
	public void afficherDerniereCarteColonne() {
		for(int i=0;i<listColl.size();i++) {

			Carte derniereCarte = (Carte) listColl.get(i).getChildren().getLast().getUserData();
			ImageView ivDerniereCarte = (ImageView) listColl.get(i).getChildren().getLast();
			ivDerniereCarte.setImage(creerImage(derniereCarte.getImg_carte()));
			derniereCarte.setImageCarteAafficher(ImageCarte.RECTO);
			System.out.println("derniere carte = " + derniereCarte + " pour  i = " + i + "Image à afficher : " + derniereCarte.getImageCarteAafficher());

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
				System.out.println("Event on Source: mouse pressed");
				event.setDragDetect(true);
			}
		});

		noeud.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				noeud.setMouseTransparent(false);
				System.out.println("Event on Source: mouse released");
			}
		});

		noeud.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				System.out.println("Event on Source: mouse dragged");
				event.setDragDetect(false);
			}
		});

		noeud.setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event)
			{
				noeud.startFullDrag();
				System.out.println("Event on Source: drag detected");
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
				System.out.println("Event on Target: mouse dragged");
			}
		});

		noeud.setOnMouseDragOver(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				System.out.println("Event on Target: mouse drag over");
			}
		});

		noeud.setOnMouseDragReleased(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				System.out.println("Event on Target: mouse drag released");
				/*Appelle au controlleur pour savoir quoi faire lors du relachement de la souris */
				new ControllerTableau();
			}
		});

		noeud.setOnMouseDragExited(new EventHandler <MouseDragEvent>() {
			public void handle(MouseDragEvent event)
			{
				System.out.println("Event on Target: mouse drag exited");
			}
		});

	}
}


