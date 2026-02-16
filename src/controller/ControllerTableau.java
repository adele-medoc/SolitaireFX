package controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Carte;
import models.ImageCarte;

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
        try{
            System.out.println("carteTarget.getValeur_carte() " + carteTarget.getValeur_carte());
            System.out.println("carteSource.getValeur_carte() " + carteSource.getValeur_carte());

        }catch (Exception e){e.getMessage();}

        if(target.getId().equals("colonne1")||target.getId().equals("colonne2")||target.getId().equals("colonne3")||
                target.getId().equals("colonne4")||target.getId().equals("colonne5")||target.getId().equals("colonne6")||target.getId().equals("colonne7")){
        }
        /*todo soucis d'état de l'image de la carte à afficher
        *  est-ce qu'il ne faut pas enlement et plutôt faire le contrôle de l'ima afficher par carte.getImg_carte()*/
        if(couleurSource.equals("coeur")&&target.getId().equals("pileCoeur")){
            carteDeposablePileFondation(source,target,carteSource,carteTarget,compteurPioche,ivSource,cartesPioche);
            /*if(source.getId().equals("pioche")){
                if(target.getChildren().size() ==1 && valSource==1){
                    target.getChildren().removeFirst();
                    target.getChildren().add(ivSource);
                    compteurPioche--;
                    cartesPioche.get(compteurPioche).setImageCarteAafficher(ImageCarte.RECTO);
                    ImageView nouvelleImgPioche = creerImageView(creerImage(cartesPioche.get(compteurPioche).getImg_carte()));
                    nouvelleImgPioche.setUserData(cartesPioche.get(compteurPioche));
                    System.out.println("nouvelleImgPioche.getUserData "+nouvelleImgPioche.getUserData());
                    source.getChildren().add(nouvelleImgPioche);

                } else if (carteSource.getValeur_carte() == carteTarget.getValeur_carte()+1) {
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                }

            }else{
                if(target.getChildren().size() ==1 && valSource==1){
                    target.getChildren().removeFirst();
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                    //System.out.println("Controleur tableau imageview de la carte source " + ivSource.getUserData());
                }

                if(carteTarget.getValeur_carte() == carteSource.getValeur_carte()+1){
                    source.getChildren().remove(ivSource);
                    target.getChildren().add(ivSource);
                }
            }

             // if(){} si la carte source = à target+1 on peut ajouter la carte*/
        }
        if(couleurSource.equals("pique")&&target.getId().equals("pilePique")){
            carteDeposablePileFondation(source,target,carteSource,carteTarget,compteurPioche,ivSource,cartesPioche);
        }
        if(couleurSource.equals("trefle")&&target.getId().equals("pileTrefle")){
            carteDeposablePileFondation(source,target,carteSource,carteTarget,compteurPioche,ivSource,cartesPioche);
        }
        if(couleurSource.equals("carreau")&&target.getId().equals("pileCarreau")){
            carteDeposablePileFondation(source,target,carteSource,carteTarget,compteurPioche,ivSource,cartesPioche);
        }

/*if(source.estRougeOuNoir().equals(carteTarget.estRougeOuNoir())){

            }else {
                if((source.getValeur_carte()-1 == carteTarget.getValeur_carte()) || (source.getValeur_carte()+1 == carteTarget.getValeur_carte())){
                }else {
                }
            }*/
    }

    public void carteDeposablePileFondation(VBox source, VBox target, Carte carteSource, Carte carteTarget, int compteurPioche, ImageView ivSource, List<Carte>cartesPioche){
        int valSource = carteSource.getValeur_carte();
        String couleurSource = carteSource.getCouleur_carte();
        //SI LA CARTE PROVIENT DE LA PIOCHE
        if(source.getId().equals("pioche")){
            System.out.println("********VUE TABLEAU avant if cpt pioche : " + compteurPioche + " Taille de la pioche : "+cartesPioche.size());
                    if(target.getChildren().size() ==1 && valSource==1){
                        target.getChildren().removeFirst();
                        target.getChildren().add(ivSource);
                        cartesPioche.remove(carteSource);
                        compteurPioche--;
                        cartesPioche.get(compteurPioche).setImageCarteAafficher(ImageCarte.RECTO);
                        ImageView nouvelleImgPioche = creerImageView(creerImage(cartesPioche.get(compteurPioche).getImg_carte()));
                        nouvelleImgPioche.setUserData(cartesPioche.get(compteurPioche));
                        System.out.println("nouvelleImgPioche.getUserData "+nouvelleImgPioche.getUserData());
                        source.getChildren().add(nouvelleImgPioche);
                        System.out.println("********CONTROLLER TABLEAU fin if | cpt pioche : " + compteurPioche + " Taille de la pioche : "+cartesPioche.size());

                    } else if (carteSource.getValeur_carte() == carteTarget.getValeur_carte()+1) {
                        source.getChildren().remove(ivSource);
                        target.getChildren().add(ivSource);
                        compteurPioche--;
                        cartesPioche.get(compteurPioche).setImageCarteAafficher(ImageCarte.RECTO);
                        ImageView nouvelleImgPioche = creerImageView(creerImage(cartesPioche.get(compteurPioche).getImg_carte()));
                        nouvelleImgPioche.setUserData(cartesPioche.get(compteurPioche));
                        cartesPioche.remove(carteSource);
                        System.out.println("nouvelleImgPioche.getUserData "+nouvelleImgPioche.getUserData());
                        source.getChildren().add(nouvelleImgPioche);
                        System.out.println("********CONTROLLER TABLEAU fin if | cpt pioche : " + compteurPioche + " Taille de la pioche : "+cartesPioche.size());
                        System.out.println("nouvelleImgPioche.getUserData "+nouvelleImgPioche.getUserData());
                        /*
                        source.getChildren().remove(ivSource);
                        target.getChildren().add(ivSource);
                        cartesPioche.remove(carteSource);
                        compteurPioche--;
                        cartesPioche.get(compteurPioche).setImageCarteAafficher(ImageCarte.RECTO);
                        ImageView nouvelleImgPioche = creerImageView(creerImage(cartesPioche.get(compteurPioche).getImg_carte()));
                        nouvelleImgPioche.setUserData(cartesPioche.get(compteurPioche));
                        source.getChildren().add(nouvelleImgPioche);
                    */

                    }

            //SI LA CARTE PROVIENT DU PLATEAU
        }else{
            if(target.getChildren().size() ==1 && valSource==1){
                target.getChildren().removeFirst();
                source.getChildren().remove(ivSource);
                target.getChildren().add(ivSource);
                        //System.out.println("Controleur tableau imageview de la carte source " + ivSource.getUserData());
            } else if (carteSource.getValeur_carte() == carteTarget.getValeur_carte()+1) {
                source.getChildren().remove(ivSource);
                target.getChildren().add(ivSource);
            }
        }
    }


}


