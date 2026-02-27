package controller;
import java.util.List;

import models.Carte;
import models.Jeu_Solitaire;
import view.VueTableau;

public class ControllerAccueil extends Controller {
	int choix;
	//ControllerTableau controllerTableau;
	DragDropHandler controllerDragDrop;


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
				ControllerTableau controllerTableau = new ControllerTableau(jeu, controllerDragDrop,this);
				new VueTableau(controllerTableau, controllerDragDrop,this);
				break;
			case 2:
				System.out.println("choix Score");
				break;
		}
	}
	
}
	
