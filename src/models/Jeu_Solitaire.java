package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jeu_Solitaire {
	List<Carte> paquet= new ArrayList<Carte>();
	
	public Jeu_Solitaire() {
	
	}
	
	public List<Carte> creerPaquet() {
		
		for(int i=1;i<53;i++) {
			int valeurCarte;
			String couleurCarte = "";
			String imgCarte;
			
			switch(i) {
				case 1,2,3,4,5,6,7,8,9,10,11,12,13:
					couleurCarte = "coeur";
					break;
				case 14,15,16,17,18,19,20,21,22,23,24,25,26:
					couleurCarte = "pique";
					break;
				case 27,28,29,30,31,32,33,34,35,36,37,38,39:
					couleurCarte = "carreau";
					break;
				case 40,41,42,43,44,45,46,47,48,49,50,51,52:
					couleurCarte = "trefle";
					break;
				default:
					couleurCarte = "pas de couleur";
			}
			
			if(i % 13 ==0) {
				valeurCarte = 13;
			}else {
			valeurCarte =  i % 13;
			}
			imgCarte = i +".png";
			paquet.add(new Carte(valeurCarte,couleurCarte,imgCarte));	
		}
		System.out.println("Jeu_Solitaire: Avant mélange : " + paquet);
		Collections.shuffle(paquet);
		System.out.println("Après mélange : " + paquet);
		
		return paquet ;
	}
	public List<Carte> getPaquet() {
		return paquet;
	}

	public void setPaquet(List<Carte> paquet) {
		this.paquet = paquet;
	}

	public void tirerCartePioche() {}
	
	
}
