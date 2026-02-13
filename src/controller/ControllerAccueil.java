package controller;
import java.util.ArrayList;
import java.util.List;

import models.Carte;
import models.Jeu_Solitaire;
import view.VueTableau;

public class ControllerAccueil {
	int choix;
	ControllerTableau controllerTableau;

	public ControllerAccueil(ControllerTableau c) {
		this.controllerTableau = c;
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	public void choisirJeu(){
		switch(choix) {
			case 1:
				List<Carte> paquet = new ArrayList<Carte>();
				Jeu_Solitaire jeu = new Jeu_Solitaire();
				paquet = jeu.creerPaquet();
				new VueTableau(paquet, controllerTableau);
				break;
			case 2:
				System.out.println("choix Score");
		}
	}
	
}
	
