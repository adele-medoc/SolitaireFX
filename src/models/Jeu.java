package models;

import java.util.ArrayList;

public abstract class Jeu {
	
	private ArrayList<Carte> liste_cartes;
	
	
	Jeu(ArrayList<Carte> liste_cartes){
		this.setListe_cartes(liste_cartes);
	}


	public ArrayList<Carte> getListe_cartes() {
		return liste_cartes;
	}


	public void setListe_cartes(ArrayList<Carte> liste_cartes) {
		this.liste_cartes = liste_cartes;
	}
	

}
