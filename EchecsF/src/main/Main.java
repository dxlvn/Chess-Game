package main;

import java.io.IOException;
import java.util.Scanner;
/**
 * <b>Classe principale</b>
 *	Appel à toutes les méthodes nécessaires pour le déroulement de la partie
 */
public class Main {

	public static final char VIDE = ' ';
	public static final char PION = 'P';
	public static final char TOUR = 'T';
	public static final char CAVALIER = 'C';
	public static final char FOU = 'F';
	public static final char DAME = 'D';
	public static final char ROI = 'R';
	public static final char BLANC = 'a';
	public static final char NOIR = 'b';

	/**
	 * Méthode principale 
	 * @param args
	 * 		Lecture grâce au scanner
	 * @throws InterruptedException
	 * 		Exception levée
	 */
	public static void main(String[] args) throws InterruptedException{
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Debut du jeu, choisissez le mode;");
		System.out.print("  1=PVP , 2=PVE , 3=EVE:  ");
		int choix = 0;
		while(choix != 1 && choix != 2 && choix != 3)
			choix = sc.nextInt();
		
		if(choix == 1) {
		Echecs partie = new Echecs();
		partie.demarrerPVP();
		}
		
		else if(choix == 2) {
			Echecs partie = new Echecs();
			partie.demarrerPVE();
		}
		
		else if(choix == 3) {
			Echecs partie = new Echecs();
			partie.demarrerEVE();
		}
	}
}
