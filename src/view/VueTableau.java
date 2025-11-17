package view;

import java.util.ArrayList;

import application.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Carte;

public class VueTableau {
	//-----------------------------------------ATTRIBUTS---------------------------------------------------------------------
	ArrayList<Carte> carteDepart;
	ArrayList<Carte> carteSurTableau = new ArrayList<Carte>();
	BorderPane root = new BorderPane();
	
	HBox pioche = new HBox();
	VBox carteDevoile= new VBox();
	VBox cartesCache= new VBox();
	
	HBox pileFondation = new HBox();
	VBox pileCoeur = new VBox();
	VBox pilePique = new VBox();
	VBox pileCarreau = new VBox();
	VBox pileTrefle = new VBox();
	
	GridPane  hautTableauJeu = new GridPane ();
	GridPane  basTableauJeu = new GridPane ();
	
	VBox colonne1 = new VBox();
	VBox colonne2 = new VBox();
	VBox colonne3 = new VBox();
	VBox colonne4 = new VBox();
	VBox colonne5 = new VBox();
	VBox colonne6 = new VBox();
	VBox colonne7 = new VBox();
	
	ArrayList<ArrayList<Carte>> ListCat= new ArrayList<ArrayList<Carte>>();
	ArrayList<Carte> carteCol1 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol2 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol3 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol4 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol5 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol6 = new ArrayList<Carte>();
	ArrayList<Carte> carteCol7 = new ArrayList<Carte>();
	
	ArrayList<VBox> listColl = new ArrayList<VBox>();
	
	//---------------------------------------CONSTRUCTEUR-----------------------------------------------------------------------
	
	public VueTableau(ArrayList<Carte> paquet) {
		carteDepart = paquet;
		afficherTableau();
	};
	//----------------------------------------GETTER / SETTER ----------------------------------------------------------------------

	//---------------------------------------AUTRES METHODES-----------------------------------------------------------------------
	public void afficherTableau() {
		
		cartesCache.getChildren().add(afficherImage("verso.jpg"));
		carteDevoile.getChildren().add(afficherImage("pioche.png"));
			
		pileCoeur.getChildren().add(afficherImage("pileCoeur.png"));
		pilePique.getChildren().add(afficherImage("pilePique.png"));
		pileCarreau.getChildren().add(afficherImage("pileCarreau.png"));
		pileTrefle.getChildren().add(afficherImage("pileTrefle.png"));
			
		listColl.add(colonne1);
		listColl.add(colonne2);
		listColl.add(colonne3);
		listColl.add(colonne4);
		listColl.add(colonne5);
		listColl.add(colonne6);
		listColl.add(colonne7);
		
		ListCat.add(carteCol1);
		ListCat.add(carteCol2);
		ListCat.add(carteCol3);
		ListCat.add(carteCol4);
		ListCat.add(carteCol5);
		ListCat.add(carteCol6);
		ListCat.add(carteCol7);
		
		pioche.getChildren().addAll(cartesCache,carteDevoile);
		pioche.setPadding(new Insets(0, 10, 0, 10));
		pileFondation.getChildren().addAll(pileCoeur,pilePique,pileCarreau,pileTrefle );
		
		System.out.println("VueTableau: taille du jeu avant de distribuer les carte :"+ carteDepart.size());
		
			
			for(int i=0;i<7;i++) {
				
				for(int j=0;j< i+1;j++) {
					
						ListCat.get(i).add(carteDepart.getLast());
						listColl.get(i).getChildren().add(afficherImage(ListCat.get(i).getLast().getImg_carte_verso()));
//						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! colonne " +i+"      "+ ListCat.get(i) +"listColl " + listColl.get(i));
						carteSurTableau.add(carteDepart.getLast());
						carteDepart.remove(carteDepart.getLast());
//						System.out.println("\n VueTableau: i = "+i+" j =" +j + "  taille du jeu : "+ carteDepart.size()); 
//						System.out.println("VueTableau: taille carteSurTableau : "+ carteSurTableau.size()+ "  carte ajouté : "+ carteSurTableau.toString()+"\n "); 
				}		 
			}
			
			afficherDerniereCarteColonne();
			
			
//			for(VBox col : listColl) {
//				for(int i =0; i< listColl.get(i).getChildren().size()-1;i++){}
//				col.getChildren().getLast().setOnDragOver(new EventHandler<>() {
//				    public void handle(DragEvent event) {
//				        Dragboard db = event.getDragboard();
//				        if (db.hasString()) {
//				            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//				        }
//				        event.consume();
//				    }
//
//						
//					});
//
//				col.setOnDragDropped(new EventHandler<>() {
//				    public void handle(DragEvent event) {
//				        Dragboard db = event.getDragboard();
//				        boolean success = false;
//				        if (db.hasString()) {
//				            System.out.println("Dropped: " + db.getString());
//				            success = true;
//				        }
//				        event.setDropCompleted(success);
//				        event.consume();
//				    }
//
//				});
//			}
			
			
		

		//carteDepart.subList(0, cpt).clear();
		System.out.println("VueTableau : carte restant dans carteDépart : "+ carteDepart.size() +"carteSurTableau : "+carteSurTableau.size()+" carte de départ : "+ carteDepart.toString());
			
		
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
	
	
	public ImageView afficherImage(String cheminImg) {
		try {
		Image imgCarte = new Image(getClass().getResource("/images/"+cheminImg).toExternalForm());
		ImageView imgViewCarte = new ImageView(imgCarte);
		imgViewCarte.setFitHeight(244);
		imgViewCarte.setFitWidth(170);
		return imgViewCarte;
		}catch(Exception e) {System.out.println("VueTableau: fonction afficherImg()"+ e);
		return null;
		}		
	}

	public void afficherDerniereCarteColonne() {
		
		for(int i=0;i<listColl.size();i++) {
			var children = listColl.get(i).getChildren();
			if (!children.isEmpty()) {
			    children.remove(children.size() - 1); 
			    children.add(afficherImage(ListCat.get(i).getLast().getImg_carte()));
			}
		}
		
	}
	// fonction pour déplacer une carte vers une autre colonne
	public void creerDragDropEvent(ImageView source,ImageView target){
		source.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString(source.getId());
		        System.out.println(source.getId());
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
		
	}
	
}


