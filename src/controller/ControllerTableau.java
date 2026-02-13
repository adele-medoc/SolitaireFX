package controller;

import javafx.event.EventType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;
import utilitaire.Utility;

import java.util.ArrayList;
import java.util.List;

import static utilitaire.Utility.creerImage;
import static utilitaire.Utility.creerImageView;

public class ControllerTableau {

    // ----------------------- CONSTRUCTEUR ----------------------
    public ControllerTableau() {}

    // ----------------------- METHODES --------------------------

    /**
     *
     *
     * @param carteSource
     * @param ivSource
     * @param source
     * @param carteTarget
     * @param target
     */
    public void carteEstDeposable(Carte carteSource, ImageView ivSource, VBox source, Carte carteTarget, VBox target, int compteurPioche, List<Carte> cartesPioche,List<Carte> cartesPlateau){
        int valSource = carteSource.getValeur_carte();
        String couleurSource = carteSource.getCouleur_carte();


        if(target.getId().equals("colonne1")||target.getId().equals("colonne2")||target.getId().equals("colonne3")||
                target.getId().equals("colonne4")||target.getId().equals("colonne5")||target.getId().equals("colonne6")||target.getId().equals("colonne7")){
        }
        /*todo soucis d'état de l'image de la carte à afficher
        *  est-ce qu'il ne faut pas enlement et plutôt faire le contrôle de l'ima afficher par carte.getImg_carte()*/
        if(couleurSource.equals("coeur")&&target.getId().equals("pileCoeur")){
            if(source.getId().equals("pioche")){
                if(target.getChildren().size() ==1 && valSource==1){
                    target.getChildren().removeFirst();
                    target.getChildren().add(ivSource);
                    compteurPioche--;
                    //cartesPioche.get(compteurPioche).setImageCarteAafficher(ImageCarte.RECTO);
                    ImageView nouvelleImgPioche = creerImageView(creerImage(cartesPioche.get(compteurPioche).getImg_carte()));
                    nouvelleImgPioche.setUserData(cartesPioche.get(compteurPioche));
                    System.out.println("nouvelleImgPioche.getUserData "+nouvelleImgPioche.getUserData());
                    source.getChildren().add(nouvelleImgPioche);

                } else if (carteTarget.getValeur_carte() == carteSource.getValeur_carte()+1) {
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                }

            }else{
                if(target.getChildren().size() ==1 && valSource==1){
                    target.getChildren().removeFirst();
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                    System.out.println("Controleur tableau imageview de la carte source " + ivSource.getUserData());
                }
                if(carteTarget.getValeur_carte() == carteSource.getValeur_carte()+1){
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                }
            }

             // if(){} si la carte source = à target+1 on peut ajouter la carte
        }
        if(couleurSource.equals("pique")&&target.getId().equals("pilePique")){

        }
        if(couleurSource.equals("trefle")&&target.getId().equals("pileTrefle")){

        }
        if(couleurSource.equals("carreau")&&target.getId().equals("pileCarreau")){

        }

/*if(source.estRougeOuNoir().equals(carteTarget.estRougeOuNoir())){

            }else {
                if((source.getValeur_carte()-1 == carteTarget.getValeur_carte()) || (source.getValeur_carte()+1 == carteTarget.getValeur_carte())){
                }else {
                }
            }*/
    }

    //public carteDeposablePileFondation(){}


}


