package utilitaire;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Carte;

public final class Utility {

    private Utility() {
        throw new java.lang.UnsupportedOperationException("Utility class and cannot be instantiated");
    }

    public static Image creerImage(String cheminImg) {
        try {
            return new Image(Utility.class.getResource("/images/"+cheminImg).toExternalForm());
        }catch(Exception e) {System.out.println("VueTableau: fonction afficherImg(string)"+ e);
            return null;
        }
    }

    public static ImageView creerImageView(Image Img) {
        try {
            ImageView imgViewCarte = new ImageView(Img);
            imgViewCarte.setFitHeight(244);
            imgViewCarte.setFitWidth(170);
            return imgViewCarte;
        }catch(Exception e) {System.out.println("VueTableau: fonction creerImageView(string)"+ e);
            return null;
        }
    }

    public static ImageView creerImageViewCarteVerso(Carte carte) {
        try {
            ImageView imgViewCarte = creerImageView(creerImage(carte.getImg_carte_verso()));
            imgViewCarte.setUserData(carte);
            return imgViewCarte;
        }catch(Exception e) {System.out.println("VueTableau: creerImageViewCarteVerso | fonction afficherImg(carte)"+ e);
            return null;
        }
    }

}
