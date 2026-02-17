package controller;
import java.util.ArrayList;
import java.util.List;

import models.Carte;
import models.Jeu_Solitaire;
import view.VueTableau;

public class ControllerAccueil {
	int choix;
	//ControllerTableau controllerTableau;
	DragDropHandler controllerDragDrop;

//	public ControllerAccueil(ControllerTableau c) {
//
//		this.controllerTableau = c;
//	}
//	public ControllerAccueil(ControllerTableau c, DragDropHandler d) {
//		this.controllerDragDrop = d;
//		this.controllerTableau = c;
//	}

	public ControllerAccueil(DragDropHandler controllerDragDrop) {
		this.controllerDragDrop = controllerDragDrop;
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
				Jeu_Solitaire jeu = new Jeu_Solitaire();
				//List<Carte> paquet = new ArrayList<Carte>();
				//paquet = jeu.creerPaquet();
				//new VueTableau(paquet, controllerTableau);
				//new VueTableau(paquet, controllerTableau,controllerDragDrop);
				ControllerTableau controllerTableau = new ControllerTableau(jeu, controllerDragDrop);
				new VueTableau(controllerTableau, controllerDragDrop);
				break;
			case 2:
				System.out.println("choix Score");
		}
	}
	
}
	
