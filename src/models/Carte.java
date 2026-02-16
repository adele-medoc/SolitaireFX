package models;

public class Carte {
	// ----------------------- ATTRIBUTS -------------------------
	private int valeur_carte;
	private String couleur_carte;
	private String img_carte;
	private String img_carte_verso = "verso.jpg";
	private ImageCarte imageCarteAafficher;

	// ----------------------- CONSTRUCTEUR ----------------------
	public Carte(int valeur_carte,String couleur_carte,String img_carte) {
		this.valeur_carte = valeur_carte;
		this.couleur_carte = couleur_carte;
		this.img_carte = img_carte;
		this.imageCarteAafficher = ImageCarte.VERSO;
	};

	// ----------------------- GETTER / SETTER -------------------
	public int getValeur_carte() {
		return valeur_carte;
	}

	public void setValeur_carte(int valeur_carte) {
		this.valeur_carte = valeur_carte;
	}

	public String getCouleur_carte() {
		return couleur_carte;
	}

	public void setCouleur_carte(String couleur_carte) {
		this.couleur_carte = couleur_carte;
	}

	public String getImg_carte() {
		return img_carte;
	}

	public void setImg_carte(String img_carte) {
		this.img_carte = img_carte;
	};

	public String getImg_carte_verso() {
		return img_carte_verso;
	}

	public void setImg_carte_verso(String img_carte_verso) {
		this.img_carte_verso = img_carte_verso;
	}

	public ImageCarte getImageCarteAafficher() {
		return imageCarteAafficher;
	}

	public void setImageCarteAafficher(ImageCarte imageCarte) {
		this.imageCarteAafficher = imageCarte;
	}

	// ----------------------- METHODES --------------------------
		public boolean peutDeplacerCarte() {
			if(imageCarteAafficher == ImageCarte.RECTO){
				return true;
			}else {
				return false;
			}
		}
		public String estRougeOuNoir() {
			if ((couleur_carte == "coeur" ) || (couleur_carte == "carreau" )) {
				return "rouge" ;
			}else{return "noir";}
		}
	// -----toString()-----
		@Override
		public String toString() {
		return valeur_carte + " de " + couleur_carte + ", la face de la carte à afficher est " + imageCarteAafficher;
	}
}
