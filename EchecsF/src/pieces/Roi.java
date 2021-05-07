package pieces;
import main.*;
/**
 * Classe Roi sous classe de Piece 
 * {@link Piece}
 */
public class Roi extends Piece{
	/**
	 * Constructeur de la classe Roi
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */
	public Roi(int chiffre,int lettre,char couleur) {
		super(Main.ROI,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Roi
	 * @param piece
	 * 		Objet à copier
	 */
	public Roi (Piece piece){
		super(piece);
	}
	/**
	 * Teste les conditions de déplacement du roi vers la position :
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
		if((Math.abs(bx - this.x) <= 1 && Math.abs(by - this.y) <= 1) && plateau.getCouleur(bx, by) != this.getCouleur()) {
			return true;
		}
		return false;
	}


	@Override
	public boolean enEchec(Plateau plateau) {
		
		int i = 0, j = 0;
		for(j = 0; j < 8; j++){
			for(i = 0; i < 8; i++) {
				if(i ==  this.x && j == this.y) continue;
				if(plateau.getCouleur(i,j) != this.couleur)
					if(plateau.peutBouger(i, j, this.x, this.y)) {
			//			System.out.println("return true");
						return true; 
					}
			}
		}
		//System.out.println("return false");
		return false;
	}

}