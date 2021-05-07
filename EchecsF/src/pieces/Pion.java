package pieces;
import main.*;
import java.util.*;
/**
* Classe Pion sous classe de Piece 
* {@link Piece}
*/
public class Pion extends Piece{
	/**
	 * Constructeur de la classe Pion
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */

	public Pion(int chiffre,int lettre,char couleur) {
		super(Main.PION,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Pion
	 * @param piece
	 * 		Objet à copier
	 */
	public Pion (Piece piece){
		super(piece);
	}
	/**
	 * Teste les conditions de déplacement du pion vers la position :
	 * @param bx
	 * 		Coordonnée en abscisse
	 * @param by
	 * 		Coordonné en ordonnée
	 * @param
	 * 		Plateau de jeu
	 * @return true si les conditions sont vérifiées, false sinon	
	 */
	@Override
	public boolean seDeplace( int bx, int by, Plateau plateau) {
	if((this.y == 1 && by == 3 && plateau.getType(this.x, 2) == Main.VIDE && this.getCouleur() == Main.BLANC) || (this.y == 6 && by == 4 && plateau.getType(this.x, 5) == Main.VIDE &&this.getCouleur() == Main.NOIR)) {
		if( ( (by - this.y) == 2 && this.getCouleur() == Main.BLANC ) ||  ((by - this.y) == -2 && this.getCouleur() == Main.NOIR ))  {
			if((bx - this.x) == 0) { // Si le pion avance tout droit
				if(plateau.getType(bx, by) == Main.VIDE){ // Si la case cible est vide
					return true;
				}
			}
		}
	}
	else {
		if( ( (by - this.y) == 1 && this.getCouleur() == Main.BLANC ) ||  ((by - this.y) == -1 && this.getCouleur() == Main.NOIR ))  {
			if((bx - this.x) == 0) { // Si le pion avance tout droit
				if(plateau.getType(bx, by) == Main.VIDE){ // Si la case cible est vide
					return true;
				}
			}
			else if (Math.abs(bx - this.x) == 1) { // Si le pion avance en diagonale
				
				if(plateau.getType(bx, by) != Main.VIDE && plateau.getCouleur(bx, by) != this.getCouleur()) {//Mange un pion
					return true;					
				}
			}
		}
	}
		return false;
	}
	
}