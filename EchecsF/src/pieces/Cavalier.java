package pieces;
import main.*;
/**
 * Classe Cavalier sous classe de Piece
 * {@link Piece}
 */
public class Cavalier extends Piece{
	/**
	 * Constructeur de la classe Cavalier
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */
	public Cavalier(int chiffre,int lettre,char couleur) {
		super(Main.CAVALIER,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Cavalier
	 * @param piece
	 * 		Objet à copier
	 */
	public Cavalier (Piece piece){
		super(piece);
	}
	/**
	 * Teste les conditions de déplacement du cavalier vers la position :
	 * @param bx
	 * 		Coordonnée en abscisse
	 * @param by
	 * 		Coordonné en ordonnée
	 * @param plateau
	 * 		Plateau de jeu
	 * @return true si les conditions sont vérifiées, false sinon
	 */
	@Override
	public boolean seDeplace(int bx, int by, Plateau plateau) {
		if((Math.abs(bx - this.x) == 1 && Math.abs(by - this.y) == 2) || (Math.abs(bx - this.x) == 2 && Math.abs(by - this.y) == 1)) {
			if(plateau.getCouleur(bx,by) != this.getCouleur()) {
				return true;
			}
		}
		return false;

	}
}
