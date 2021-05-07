package pieces;
import main.*;
/**
 * Classe Vide sous classe de Piece 
 * Type des classes vides de l'échiquier 
 * {@link Piece}
 */
public class Vide extends Piece {
	/**
	 * Constructeur de la classe Vide
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */
	public Vide(int chiffre,int lettre,char couleur) {
		super(Main.VIDE,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Vide
	 * @param piece
	 * 		Objet à copier
	 */
	public Vide (Piece aCopier){
		super(aCopier);
	}

}
