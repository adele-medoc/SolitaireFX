package controller;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;
import models.Jeu_Solitaire;

import java.util.ArrayList;
import java.util.List;

import static utilitaire.Utility.creerImage;
import static utilitaire.Utility.creerImageView;

public class ControllerTableau extends Controller{
    DragDropHandler controllerDragDrop;
    int cptPioche = -1;

    Carte carteSource;
    ImageView ivSource;
    VBox source;

    Carte carteTarget;
    VBox target;

    Jeu_Solitaire jeu;
    List<Carte> paquet = new ArrayList<Carte>();
    List<Carte> pioche = new ArrayList<Carte>();
    List<Carte> carteSurTableau = new ArrayList<Carte>();




    // ----------------------- CONSTRUCTEUR ----------------------
    public ControllerTableau(Jeu_Solitaire jeu, DragDropHandler controllerDragDrop) {
        this.jeu = jeu;
        this.controllerDragDrop = controllerDragDrop;
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

    // ----------------------- METHODES --------------------------

    private void initialiserPartie() {
        paquet = jeu.creerPaquet();
        System.out.println("**** taille du jeu avant de distribuer les carte : "+ paquet.size());
        for(int i =0;i<28;i++){
            carteSurTableau.add(paquet.get(i));

        }
        for(int i =28; i< paquet.size();i++){
            pioche.add(paquet.get(i));
        }
        System.out.println("******** taille carteSurTableau : "+ carteSurTableau.size()+ "  carte ajouté : "+ carteSurTableau.toString()+"\n ");
        System.out.println("************ taille pioche : "+ pioche.size()+ "  carte ajouté : "+ pioche.toString()+"\n ");
    }
    /**
     *
     */
    public void carteEstDeposable(){
        //int valSource = carteSource.getValeur_carte();
        String couleurSource = carteSource.getCouleur_carte();
        try{
            System.out.println("carteTarget.getValeur_carte() " + carteTarget.getValeur_carte());
            System.out.println("carteSource.getValeur_carte() " + carteSource.getValeur_carte());

        }catch (Exception e){e.getMessage();}

        if(target.getId().equals("colonne1")||target.getId().equals("colonne2")||target.getId().equals("colonne3")||
                target.getId().equals("colonne4")||target.getId().equals("colonne5")||target.getId().equals("colonne6")||target.getId().equals("colonne7")){
        }
        Node dernierNoeud = target.getChildren().getLast();
        if ((dernierNoeud instanceof ImageView)){

            if(!(dernierNoeud.getId()==null)){

                if("carteVide_colonne1".equals(dernierNoeud.getId())|| "carteVide_colonne2".equals(dernierNoeud.getId())|| "carteVide_colonne3".equals(dernierNoeud.getId())||
                        "carteVide_colonne4".equals(dernierNoeud.getId())|| "carteVide_colonne5".equals(dernierNoeud.getId())||
                        "carteVide_colonne6".equals(dernierNoeud.getId())|| dernierNoeud.getId().equals("carteVide_colonne7")){
                    if(carteSource.getValeur_carte()==13){
                        if(source.getId().equals("pioche")){
                            transfertCartePioche();
                        }else {
                            transfertCartePlateau();
                        }

                    }
                }
            }
        }


        if(couleurSource.equals("coeur")&&target.getId().equals("pileCoeur")){
            carteDeposablePileFondation();
        }
        if(couleurSource.equals("pique")&&target.getId().equals("pilePique")){
            carteDeposablePileFondation();
        }
        if(couleurSource.equals("trefle")&&target.getId().equals("pileTrefle")){
            carteDeposablePileFondation();
        }
        if(couleurSource.equals("carreau")&&target.getId().equals("pileCarreau")){
            carteDeposablePileFondation();
        }

/*if(source.estRougeOuNoir().equals(carteTarget.estRougeOuNoir())){

            }else {
                if((source.getValeur_carte()-1 == carteTarget.getValeur_carte()) || (source.getValeur_carte()+1 == carteTarget.getValeur_carte())){
                }else {
                }
            }*/
    }

    public void carteDeposablePileFondation(){
        int valSource = carteSource.getValeur_carte();

        //SI LA CARTE PROVIENT DE LA PIOCHE
        if(source.getId().equals("pioche")){
            System.out.println("----CONTROLLER TABLEAU avant if cpt pioche : " + cptPioche + " Taille de la pioche : "+pioche.size());
            if((target.getChildren().size() ==1 && valSource==1)) {
                transfertCartePioche();
//                target.getChildren().removeFirst();
//                target.getChildren().add(ivSource);
//                pioche.remove(carteSource);
//
//                if (cptPioche == 0) {
//                    ImageView nouvelleImgPioche = creerImageView(creerImage("pioche.png"));
//                    source.getChildren().add(nouvelleImgPioche);
//                } else {
//                    cptPioche--;
//                    pioche.get(cptPioche).setImageCarteAafficher(ImageCarte.RECTO);
//                    ImageView nouvelleImgPioche = creerImageView(creerImage(pioche.get(cptPioche).getImg_carte()));
//                    nouvelleImgPioche.setUserData(pioche.get(cptPioche));
//                    controllerDragDrop.eventDrag(nouvelleImgPioche);
//                    System.out.println("carte provient pioche --------nouvelleImgPioche.getUserData " + nouvelleImgPioche.getUserData());
//                    source.getChildren().add(nouvelleImgPioche);
//                    System.out.println("carte provient pioche --------------CONTROLLER TABLEAU fin if | cpt pioche : " + cptPioche + " Taille de la pioche : " +pioche.size());
//
//                }
            } else if ((carteSource.getValeur_carte() == carteTarget.getValeur_carte()+1)) {
                transfertCartePioche();
//                target.getChildren().removeFirst();
//                target.getChildren().add(ivSource);
//                pioche.remove(carteSource);
//
//                if (cptPioche == 0) {
//                    ImageView nouvelleImgPioche = creerImageView(creerImage("pioche.png"));
//                    source.getChildren().add(nouvelleImgPioche);
//                } else {
//                    cptPioche--;
//                    pioche.get(cptPioche).setImageCarteAafficher(ImageCarte.RECTO);
//                    ImageView nouvelleImgPioche = creerImageView(creerImage(pioche.get(cptPioche).getImg_carte()));
//                    nouvelleImgPioche.setUserData(pioche.get(cptPioche));
//                    controllerDragDrop.eventDrag(nouvelleImgPioche);
//                    System.out.println("carte provient pioche --------nouvelleImgPioche.getUserData " + nouvelleImgPioche.getUserData());
//                    source.getChildren().add(nouvelleImgPioche);
//                    System.out.println("carte provient pioche --------------CONTROLLER TABLEAU fin if | cpt pioche : " + cptPioche + " Taille de la pioche : " + pioche.size());
//
//                }
            }else {
                System.out.println("impossible de placer la carte "+carteSource+" sur la pile fondation");
            }

            //SI LA CARTE PROVIENT DU PLATEAU
        }else{

            if(target.getChildren().size() ==1 && valSource==1){
                transfertCartePlateau();
//                target.getChildren().removeFirst();
//                source.getChildren().remove(ivSource);
//                target.getChildren().add(ivSource);


            } else if (carteSource.getValeur_carte() == carteTarget.getValeur_carte()+1) {
                transfertCartePlateau();
//                target.getChildren().removeFirst();
//                source.getChildren().remove(ivSource);
//                target.getChildren().add(ivSource);
            }else {
                System.out.println("impossible de placer la carte "+carteSource+" sur la pile fondation");
            }
        }
    }

    public void transfertCartePioche(){
        target.getChildren().removeFirst();
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
            controllerDragDrop.eventDrag(nouvelleImgPioche);
            System.out.println("carte provient pioche --------nouvelleImgPioche.getUserData " + nouvelleImgPioche.getUserData());
            source.getChildren().add(nouvelleImgPioche);
            System.out.println("carte provient pioche --------------CONTROLLER TABLEAU fin if | cpt pioche : " + cptPioche + " Taille de la pioche : " +pioche.size());

        }
    }

    public void transfertCartePlateau(){
        target.getChildren().removeFirst();
        source.getChildren().remove(ivSource);
        target.getChildren().add(ivSource);
    }

}


