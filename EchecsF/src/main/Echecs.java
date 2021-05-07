package main;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import pieces.*;
/**
 * <b>Classe Echecs de gestion de partie</b>
 * <ul>
 * <li>plateau: Plateau de jeu</li>
 * <li>sc: Scanner de lecture </li>
 *  </ul>
 */
public class Echecs {

	Plateau plateau;
	Scanner sc;
	int nbTour;
	/**
	 * <b>Constructeur de la classe Echecs</b>
	 * Intialise le plateau et le scanner
	 */
	public Echecs(){
		this.plateau = new Plateau();
		this.sc = new Scanner(System.in);
		this.nbTour = 0;
	}

	// lecture de l'Entrée standard
	/**
	 * Méthode de lecture sur l'entrée standard pour le déplacement des pièces
	 * @return un tableau de 4 entiers (coordonnées initiales et finales)
	 */
	public int[] lire() {
		int[] tabval = new int[4];
		String move;
		char car1;
		char car2;
		do {
			
			move = this.sc.nextLine();
			
			if(move.equals("undo")) {
				tabval[0] = 10; tabval[2] = 10;
				tabval[1] = 10; tabval[3] = 10;
				return tabval;
			}

			if(move.length() != 4) continue;
			
			car1 = move.charAt(0);
			tabval[1] = (int)move.charAt(1) - 49;
			car2 = move.charAt(2);
			tabval[3] = (int)move.charAt(3) - 49;

			switch (car1) {
			case 'a': tabval[0]=0;
			break;
			case 'b': tabval[0]=1;
			break;
			case 'c': tabval[0]=2;
			break;
			case 'd': tabval[0]=3;
			break;
			case 'e': tabval[0]=4;
			break;
			case 'f': tabval[0]=5;
			break;
			case 'g': tabval[0]=6;
			break;
			case 'h': tabval[0]=7;
			break;	                          
			default: ;
			break;		
			}

			switch (car2) {
			case 'a': tabval[2]=0;
			break;
			case 'b': tabval[2]=1;
			break;
			case 'c': tabval[2]=2;
			break;
			case 'd': tabval[2]=3;
			break;
			case 'e': tabval[2]=4;
			break;
			case 'f': tabval[2]=5;
			break;
			case 'g': tabval[2]=6;
			break;
			case 'h': tabval[2]=7;
			break;	                           
			default: ;
			break;		
			}
		}while(tabval[0] < 0 || tabval[0] > 7 || tabval[1] < 0 || tabval[1] > 7 || tabval[2] < 0 || tabval[2] > 7 || tabval[3] < 0 || tabval[3] > 7);
		return tabval;
	}
	/**
	 * Méthode de fermeture du scanner
	 */
	public void fScanner() {
		this.sc.close();
	}
	/**
	 * Méthode du mode joueur contre joueur
	 */
	public void demarrerPVP() {
		this.plateau.affiche();
		while(true) {
			System.out.println("Tour des Blancs: ");
			if(this.plateau.situationEchec(Main.BLANC) == 0)
				while(!this.plateau.deplacer(this.lire(), Main.BLANC, 0)) System.out.println("Ressaisir une commande: ");
			else if(this.plateau.situationEchec(Main.BLANC) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES NOIRS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
			System.out.println("Tour des Noirs: ");
			if(this.plateau.situationEchec(Main.NOIR) == 0)
				while(!this.plateau.deplacer(this.lire(), Main.NOIR, 0)) System.out.print("Ressaisir une commande: ");
			else if(this.plateau.situationEchec(Main.NOIR) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES BLANCS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
		}
		this.fScanner();
	}

	/**
	 * Méthode du mode joueur contre ordi
	 * @throws InterruptedException 
	 */
	public void demarrerPVE() throws InterruptedException {
		IA ordi = new IA(Main.NOIR);
		this.plateau.affiche();

		while(true) {
			System.out.println("Tour des Blancs (Joueur): ");
			if(this.plateau.situationEchec(Main.BLANC) == 0)
				while(!this.plateau.deplacer(this.lire(), Main.BLANC, 1)) System.out.print("Ressaisir une commande: ");
			else if(this.plateau.situationEchec(Main.BLANC) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES NOIRS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
			System.out.println("Tour des Noirs (Ordinateur): ");
			this.plateau.affiche();
			TimeUnit.SECONDS.sleep(1);
			if(this.plateau.situationEchec(Main.NOIR) == 0)
				ordi.DeplaceIA(plateau);
			else if(this.plateau.situationEchec(Main.NOIR) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES BLANCS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
			TimeUnit.SECONDS.sleep(1);
			
		}
		this.fScanner();
	}
	
	/**
	 * Méthode du mode ordi contre ordi
	 * @throws InterruptedException
	 * 		Exception levée 
	 */
	public void demarrerEVE() throws InterruptedException {
		IA ordi1 = new IA(Main.BLANC);
		IA ordi2 = new IA(Main.NOIR);
	
		while(true) {
			System.out.println("Tour des Blancs (Ordinateur): ");
			if(this.plateau.situationEchec(Main.BLANC) == 0)
				ordi1.DeplaceIA(plateau);
			else if(this.plateau.situationEchec(Main.BLANC) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES NOIRS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Tour des Noirs (Ordinateur): ");
			if(this.plateau.situationEchec(Main.NOIR) == 0)
				ordi2.DeplaceIA(plateau);
			else if(this.plateau.situationEchec(Main.NOIR) == 2) {
				System.out.println("****ECHEC ET MAT****");
				System.out.println("VICTOIRE DES BLANCS");
				break;
			}
			else {
				System.out.println("****PAT****");
				System.out.println("*MATCH NUL*");
				break;
			}
			this.plateau.affiche();
			TimeUnit.SECONDS.sleep(1);
			this.nbTour++;
			if(nbTour > 1000) {
				System.out.println("****TEMPS ECOULE****");
				System.out.println("*****MATCH NUL*****");
				break;
			}
		}
		this.fScanner();
	}	

}
