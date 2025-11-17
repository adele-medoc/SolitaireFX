package controller;
import java.util.ArrayList;

import models.Carte;
import models.Jeu_Solitaire;
import view.VueTableau;

public class ControllerAccueil {
	
	public ControllerAccueil(int choix) {
		switch(choix) {
			case 1:
				ArrayList<Carte> paquet = new ArrayList<Carte>();
				Jeu_Solitaire jeu = new Jeu_Solitaire();
				paquet = jeu.creerPaquet();
				new VueTableau(paquet);
				break;
			case 2:
				System.out.println("choix Score");
		}
	}
	
}
	
