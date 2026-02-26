package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;
import models.Jeu_Solitaire;
import view.VueAccueil;

import java.util.ArrayList;
import java.util.List;

import application.Main;

import static utilitaire.Utility.creerImage;
import static utilitaire.Utility.creerImageView;

public class ControllerTableau extends Controller {
    DragDropHandler controllerDragDrop;
    ControllerAccueil controllerAccueil;
    
    int cptPioche = -1;
    int indexCarteColonne;

    Carte carteSource;
    ImageView ivSource;
    VBox source;

    Carte carteTarget;
    VBox target;

    Jeu_Solitaire jeu;
    List<Carte> paquet = new ArrayList<Carte>();
    List<Carte> pioche = new ArrayList<Carte>();
    List<Carte> carteSurTableau = new ArrayList<Carte>();

    List<Carte> pileFondatriceCoeur = new ArrayList<Carte>();
    List<Carte> pileFondatricePique = new ArrayList<Carte>();
    List<Carte> pileFondatriceCarreau = new ArrayList<Carte>();
    List<Carte> pileFondatriceTrefle = new ArrayList<Carte>();
    boolean pileFondatriceCompleteCoeur = false;
    boolean pileFondatriceCompletePique = false;
    boolean pileFondatriceCompleteCarreau = false;
    boolean pileFondatriceCompleteTrefle = false;
    boolean victoire = false;
    // ----------------------- CONSTRUCTEUR ----------------------
    public ControllerTableau(Jeu_Solitaire jeu, DragDropHandler controllerDragDrop, ControllerAccueil controllerAccueil) {
        this.jeu = jeu;
        this.controllerDragDrop = controllerDragDrop;
        this.controllerAccueil = controllerAccueil;
        initialiserPartie();
    }
    // ----------------------- GETTER/SETTER ----------------------

    public int getCptPioche() {
        return cptPioche;
    }

    public void setCptPioche(int cptPioche) {
        this.cptPioche = cptPioche;
    }

    public Carte getCarteSource() {
        return carteSource;
    }

    public void setCarteSource(Carte carteSource) {
        this.carteSource = carteSource;
    }

    public ImageView getIvSource() {
        return ivSource;
    }

    public void setIvSource(ImageView ivSource) {
        this.ivSource = ivSource;
    }

    public VBox getSource() {
        return source;
    }

    public void setSource(VBox source) {
        this.source = source;
    }

    public Carte getCarteTarget() {
        return carteTarget;
    }

    public void setCarteTarget(Carte carteTarget) {
        this.carteTarget = carteTarget;
    }

    public VBox getTarget() {
        return target;
    }

    public void setTarget(VBox target) {
        this.target = target;
    }

//    public Jeu_Solitaire getJeu() {
//        return jeu;
//    }
//
//    public void setJeu(Jeu_Solitaire jeu) {
//        this.jeu = jeu;
//    }
//
//    public List<Carte> getPaquet() {
//        return paquet;
//    }
//
//    public void setPaquet(List<Carte> paquet) {
//        this.paquet = paquet;
//    }

    public List<Carte> getPioche() {
        return pioche;
    }

    public void setPioche(List<Carte> pioche) {
        this.pioche = pioche;
    }

    public List<Carte> getCarteSurTableau() {
        return carteSurTableau;
    }

    public void setCarteSurTableau(List<Carte> carteSurTableau) {
        this.carteSurTableau = carteSurTableau;
    }

    public int getIndexCarteColonne() {
        return indexCarteColonne;
    }

    public void setIndexCarteColonne(int indexCarteColonne) {
        this.indexCarteColonne = indexCarteColonne;
    }
    // ----------------------- METHODES --------------------------

    private void initialiserPartie() {
        paquet = jeu.creerPaquet();
//        System.out.println("**** taille du jeu avant de distribuer les carte : " + paquet.size());
        for (int i = 0; i < 28; i++) {
            carteSurTableau.add(paquet.get(i));

        }
        for (int i = 28; i < paquet.size(); i++) {
            pioche.add(paquet.get(i));
        }
        System.out.println("******** taille carteSurTableau : " + carteSurTableau.size() + "  carte ajouté : " + carteSurTableau.toString() + "\n ");
        System.out.println("************ taille pioche : " + pioche.size() + "  carte ajouté : " + pioche.toString() + "\n ");
    }

    /**
     *
     */
    public void carteEstDeposable() {

        int tailleColonne = source.getChildren().indexOf(source.getChildren().getLast());

        //déplacement d'une ou plusieurs cartes sur une des colonnes du tableau
        if (target.getChildren().getLast() instanceof ImageView iv) {
            if (iv.getUserData() instanceof Carte) {
                if (target.getId().equals("colonne1") || target.getId().equals("colonne2") || target.getId().equals("colonne3") ||
                        target.getId().equals("colonne4") || target.getId().equals("colonne5") || target.getId().equals("colonne6") || target.getId().equals("colonne7")) {
                    if (!(carteTarget.estRougeOuNoir().equals(carteSource.estRougeOuNoir())) && carteSource.getValeur_carte() + 1 == carteTarget.getValeur_carte()) {
                        if (tailleColonne == indexCarteColonne) {
//                            System.out.println("++++++++++++++++++++++ une seule carte à déplacer");
                            transfertCarteSansSuppresionImageView();
                        } else {
                            transfertPileCartes();
                        }
                    }
                }
            }
        }

        // Ajout du roi sur une colonne vide
        Node dernierNoeud = target.getChildren().getLast();
        if ((dernierNoeud instanceof ImageView)) {
            if (!(dernierNoeud.getId() == null)) {
                if ("carteVide_colonne1".equals(dernierNoeud.getId()) || "carteVide_colonne2".equals(dernierNoeud.getId()) || "carteVide_colonne3".equals(dernierNoeud.getId()) ||
                        "carteVide_colonne4".equals(dernierNoeud.getId()) || "carteVide_colonne5".equals(dernierNoeud.getId()) ||
                        "carteVide_colonne6".equals(dernierNoeud.getId()) || dernierNoeud.getId().equals("carteVide_colonne7")) {
                    if (carteSource.getValeur_carte() == 13) {
                        if (tailleColonne == indexCarteColonne) {
//                            System.out.println("++++++++++++++++++++++ une seule carte à déplacer");
                            transfertCarteAvecSuppresionImageView();
                        } else {
                            target.getChildren().removeFirst();
                            transfertPileCartes();
                        }
                    }
                }
            }
        }

        //ajout d'une carte sur les piles fondations

        String couleurSource = carteSource.getCouleur_carte();

        if (couleurSource.equals("coeur") && target.getId().equals("pileCoeur")) {
            carteDeposablePileFondation(pileFondatriceCoeur);

        }
        if (couleurSource.equals("pique") && target.getId().equals("pilePique")) {
            carteDeposablePileFondation(pileFondatricePique);
        }
        if (couleurSource.equals("trefle") && target.getId().equals("pileTrefle")) {
            carteDeposablePileFondation(pileFondatriceTrefle);
        }
        if (couleurSource.equals("carreau") && target.getId().equals("pileCarreau")) {
            carteDeposablePileFondation(pileFondatriceCarreau);
        }

        //retirer une carte sur les piles fondations
        if(source.getId().equals("pileCoeur")){
            retirerCartePileFondation(pileFondatriceCoeur);
        }
        if(source.getId().equals("pilePique")){
            retirerCartePileFondation(pileFondatricePique);
        }

        if(source.getId().equals("pileTrefle")){
            retirerCartePileFondation(pileFondatriceTrefle);
        }

        if(source.getId().equals("pileCarreau")){
            retirerCartePileFondation(pileFondatriceCarreau);
        }
        //finDePartie();
        System.out.println("******** taille carteSurTableau = "+ carteSurTableau.size());
        System.out.println("******** taille pioche = "+ pioche.size());
        System.out.println("******** taille pile fondation coeur = "+ pileFondatriceCoeur.size());
        System.out.println("******** taille pile fondation pique = "+ pileFondatricePique.size());
        System.out.println("******** taille pile fondation carreau = "+ pileFondatriceCarreau.size());
        System.out.println("******** taille pile fondation trefle = "+ pileFondatriceTrefle.size());
        System.out.println("******** total carte = " + (carteSurTableau.size()+pioche.size()+pileFondatriceCoeur.size()+pileFondatricePique.size()+pileFondatriceTrefle.size()+pileFondatriceCarreau.size()));

    }

    public void carteDeposablePileFondation(List<Carte> pilefondation) {
        int valSource = carteSource.getValeur_carte();
        //SI LA CARTE PROVIENT DE LA PIOCHE
        if (source.getId().equals("pioche")) {
//            System.out.println("----CONTROLLER TABLEAU avant if cpt pioche : " + cptPioche + " Taille de la pioche : " + pioche.size());
            if ((target.getChildren().size() == 1 && valSource == 1)) {
                target.getChildren().removeFirst();
                transfertCartePioche();

                //Carte déjà supprimé de la pioche dans transfertCartePioche
                pilefondation.add(carteSource);

            } else if ((carteSource.getValeur_carte() == carteTarget.getValeur_carte() + 1)) {
                target.getChildren().removeFirst();
                transfertCartePioche();

                //Carte déjà supprimé de la pioche dans transfertCartePioche
                pilefondation.add(carteSource);

            } else {
                System.out.println("impossible de placer la carte " + carteSource + " sur la pile fondation");
            }
            //SI LA CARTE PROVIENT DU PLATEAU
        } else {

            if (target.getChildren().size() == 1 && valSource == 1) {
                target.getChildren().removeFirst();
                transfertCartePlateau();

                carteSurTableau.remove(carteSource);
                pilefondation.add(carteSource);

            } else if (carteSource.getValeur_carte() == carteTarget.getValeur_carte() + 1) {
                target.getChildren().removeFirst();
                transfertCartePlateau();

                carteSurTableau.remove(carteSource);
                pilefondation.add(carteSource);

            } else {
                System.out.println("impossible de placer la carte " + carteSource + " sur la pile fondation");
            }
        }
    }

    public void majListCarte(VBox target){
        if (target.getId().equals("pileCoeur")){
            pileFondatriceCoeur.add(carteSource);
        }
        if(target.getId().equals("pilePique")){
            pileFondatricePique.add(carteSource);
        }
        if(target.getId().equals("pileTrefle")){
            pileFondatriceTrefle.add(carteSource);
        }
        if(target.getId().equals("pileCarreau")){
            pileFondatriceCarreau.add(carteSource);
        }

        if (target.getId().equals("colonne1") || target.getId().equals("colonne2") || target.getId().equals("colonne3") ||
                target.getId().equals("colonne4") || target.getId().equals("colonne5") || target.getId().equals("colonne6") || target.getId().equals("colonne7")){
            if (!(source.getId().equals("colonne1") || source.getId().equals("colonne2") || source.getId().equals("colonne3") ||
                    source.getId().equals("colonne4") || source.getId().equals("colonne5") || source.getId().equals("colonne6") || source.getId().equals("colonne7"))){
                carteSurTableau.add(carteSource);
            }
        }
    }

    public void transfertCartePioche() {
        target.getChildren().add(ivSource);
        pioche.remove(carteSource);
        if (cptPioche == 0) {
            ImageView nouvelleImgPioche = creerImageView(creerImage("pioche.png"));
            source.getChildren().add(nouvelleImgPioche);
        } else {
            cptPioche--;
            pioche.get(cptPioche).setImageCarteAafficher(ImageCarte.RECTO);
            ImageView nouvelleImgPioche = creerImageView(creerImage(pioche.get(cptPioche).getImg_carte()));
            nouvelleImgPioche.setUserData(pioche.get(cptPioche));
            controllerDragDrop.eventDrag(nouvelleImgPioche, this);
            source.getChildren().add(nouvelleImgPioche);
        }
    }

    public void transfertCartePlateau() {
        source.getChildren().remove(ivSource);
        target.getChildren().add(ivSource);
    }

    public void transfertCarteAvecSuppresionImageView() {
        if (source.getId().equals("pioche")) {
            target.getChildren().removeFirst();
            transfertCartePioche();
            majListCarte(target);
        } else {
            target.getChildren().removeFirst();
            transfertCartePlateau();
            majListCarte(target);
        }
    }

    public void transfertCarteSansSuppresionImageView() {
        if (source.getId().equals("pioche")) {
            transfertCartePioche();
            majListCarte(target);
        } else {
            transfertCartePlateau();
            majListCarte(target);
        }

    }

    public void transfertPileCartes(){
//        System.out.println("++++++++++++++++++++++ pile de cartes à déplacer");
        List<Node> listeCarteColonne = new ArrayList<>();
        for (int i = indexCarteColonne; i < source.getChildren().size(); i++) {
            listeCarteColonne.add(source.getChildren().get(i));
            listeCarteColonne.getLast().setUserData(source.getChildren().get(i).getUserData());
        }
        target.getChildren().addAll(listeCarteColonne);
        source.getChildren().removeAll(listeCarteColonne);
    }

    public void retirerCartePileFondation(List<Carte> pileFondatrice){
//        System.out.println("&&&&&&&&&&&&&&&&&& Carte d'une pile fondation déplacé vers le plateau");
//            System.out.println("pile " + source.getId());

        Carte carteDessousPileFondation = null;
        ImageView nouvelleImgfondation = creerImageView(creerImage("pioche.png"));

        //Recherche la carte qui est avant celle de la pile fondation
        for(int i =0;i< paquet.size();i++){
            if(paquet.get(i).getValeur_carte()+1==carteSource.getValeur_carte() && paquet.get(i).getCouleur_carte().equals(carteSource.getCouleur_carte())){
                carteDessousPileFondation = paquet.get(i);
                System.out.println("UserData de la carte à afficher à la place de la carte fondation = "+paquet.get(i));
            }
        }
        if (carteDessousPileFondation != null) {
            nouvelleImgfondation.setImage(creerImage(carteDessousPileFondation.getImg_carte()));
            nouvelleImgfondation.setUserData(carteDessousPileFondation);
        }
        //permet d'éviter l'erreur -> Java Message : Children : duplicate children added : parent = VBox[id = colonne3]
        if (ivSource.getParent() != null) {
            ((VBox) ivSource.getParent()).getChildren().remove(ivSource);
        }

        controllerDragDrop.eventDrag(nouvelleImgfondation, this);
        source.getChildren().remove(ivSource);

        source.getChildren().add(nouvelleImgfondation);
        target.getChildren().add(ivSource);

        pileFondatrice.remove(carteSource);

    }

    public boolean finDePartie(){
    	List<Boolean> l = new ArrayList<Boolean>();

        // partie gagné si toutes les cartes du plateau sont face RECTO
        for(Carte c : carteSurTableau){
        	
            if(c.getImageCarteAafficher()==ImageCarte.VERSO){
                l.add(false);
            }else {
            	l.add(true);
            }
               
        }
        // partie gagné si toutes les cartes sont rangé dans les pile fondation
        if((pileFondatriceCarreau.size()==13&& pileFondatriceCoeur.size()==13&&pileFondatriceTrefle.size()==13&&pileFondatricePique.size()==13)||!(l.contains(false))){
            System.out.println("FIN DE PARTIE");
            return true;
        }
        
        return false;
    }
    
    public void rejouerPartie() {
    	System.out.println("appelle à rejouerPartie");
    	controllerAccueil.setChoix(1);
		controllerAccueil.choisirJeu();
    }
    public void retourMenu() {
    	new VueAccueil(controllerAccueil);
    	System.out.println("appelle à retourMenu");
    }



}


