package pieces;
import main.*;
/**
 * Classe Tour sous classe de Piece
 * {@link Piece}
 */
public class Tour extends Piece {
	/**
	 * Constructeur de la classe Tour
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */
	public Tour(int chiffre,int lettre,char couleur) {
		super(Main.TOUR,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Tour
	 * @param piece
	 * 		Objet à copier
	 */
	public Tour (Piece piece){
		super(piece);
	}
	/**
	 * Teste les conditions de déplacement de la tour vers la position :
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

		if(by==this.y && bx!=this.x)  {  //Déplacement horizontal
			if(bx-this.x == 1) {
				if( plateau.getType(this.x,this.y) != plateau.getType(bx,by)){
					if(plateau.getCouleur(this.x,this.y) != plateau.getCouleur(bx, by)) {
						return true;
					}
				}
			}

			for(int i=x+1;i<bx;i++){ //On vérifie toutes les cases avant celle choisie

				if( plateau.getType(i,y) != Main.VIDE){
					if(plateau.getCouleur(this.x,this.y) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}
				//else;
			}


			for(int i=x-1;i>bx-1;i--){ //On vérifie toutes les cases avant celle choisie

				if( plateau.getType(i,y) != Main.VIDE){
					if(plateau.getCouleur(this.x,this.y) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}
				//else;
			}



			if(plateau.getType(bx,by) != Main.VIDE && plateau.getCouleur(bx,by)== plateau.getCouleur(x,y)){
				//Même couleur
				return false;
			}
			if(this.getCouleur() == plateau.getCouleur(bx,by)) {
				return false;
			}

			else return true; 

		}

		if(bx==this.x && by!=this.y)  {  //Déplacement vertical
			if(by-this.y == 1) {
				if( plateau.getType(this.x,this.y) != plateau.getType(bx,by)){
					if(plateau.getCouleur(this.x,this.y) != plateau.getCouleur(bx, by)) {
						return true;
					}
				}

			}

			for(int j=y+1;j<by+1;j++){ //On vérifie toutes les cases avant celle choisie
				if( plateau.getType(x,j) != Main.VIDE && j!=by){
					return false;
				}
				//else;
			}

			for(int j=y-1;j>by-1;j--){ //On vérifie toutes les cases avant celle choisie
				if( plateau.getType(x,j) != Main.VIDE && j!=by){
					return false;
				}
				//else;
			}


			if(plateau.getType(bx,by) != Main.VIDE && plateau.getCouleur(bx,by)==plateau.getCouleur(x,y)){
				//Même couleur
				return false;
			}

			if(this.getCouleur() == plateau.getCouleur(bx,by)) {
				return false;
			}

			else return true; 
		}

		return false;
	}
}

