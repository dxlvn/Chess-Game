package pieces;
import main.*;
/**
 * Classe Dame sous classe de Piece
 * {@link Piece}
 */
public class Dame extends Piece {
	/**
	 * Constructeur de la classe Dame
	 * @param type
	 * @param chiffre
	 * @param lettre
	 * @param couleur
	 */
	public Dame(int chiffre,int lettre,char couleur) {
		super(Main.DAME,chiffre,lettre,couleur);
	}
	/**
	 * Un autre constructeur
	 * Constructeur de copie de l'objet Dame
	 * @param piece
	 * 		Objet à copier
	 */
	public Dame (Piece piece){
		super(piece);
	}
	/**
	 * Teste les conditions de déplacement de la dame vers la position :
	 * @param bx
	 * 		Coordonnée en abscisse
	 * @param by
	 * 		Coordonné en ordonnée
	 * @param
	 * 		Plateau de jeu
	 * @return true si les conditions sont vérifiées, false sinon
	 */
	@Override
	public boolean seDeplace(int bx, int by, Plateau plateau) {


		//Utilisation du code des méthodes seDeplace des classes Fou et Tour


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

			else return true;   // ADDDDDDD

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

			else return true; // ADDDDDDDD
		}


		// ZZZZZZZZZZZZZZZZZZZZZZZZ


		if( Math.abs(bx-this.x) != Math.abs(by-this.y) ) return false;


		if( (Math.abs(bx-this.x) == 1)&&(Math.abs(by-this.y) == 1) ) {
			if( plateau.getType(this.x,this.y) != plateau.getType(bx,by)){
				if(plateau.getCouleur(this.x,this.y) == plateau.getCouleur(bx, by)) return false;
				return true;
			}
		}


		if(Math.abs(bx-this.x)==Math.abs(by-this.y) && (bx-this.x)>0 && (by-this.y)>0){ //Cas1 haut droit
			for(int i=x+1,j=y+1;i<bx && j<by;i++,j++) {
				if( plateau.getType(i,j) != Main.VIDE){
					if(plateau.getCouleur(i,j) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}

				//else;
			}
		}

		else if(Math.abs(bx-this.x)==Math.abs(by-this.y) && (bx-this.x)<0 && (by-this.y)<0 ) { //Cas2 bas gauche
			for(int i=x-1,j=y-1;i>bx && j>by;i--,j--) {
				if( plateau.getType(i,j) != Main.VIDE){
					if(plateau.getCouleur(i,j) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}

			}
			//else;
		}


		else if(Math.abs(bx-this.x)==Math.abs(by-this.y) && (bx-this.x)<0 && (by-this.y)>0) {  //Cas3 haut gauche
			for(int i=x-1,j=y+1;i>bx-1 && j<by+1;i--,j++) {
				if( plateau.getType(i,j) != Main.VIDE){
					if(plateau.getCouleur(i,j) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}
			}
		}

		else if(Math.abs(bx-this.x)==Math.abs(by-this.y) && (bx-this.x)>0 && (by-this.y)<0) {  //Cas4 bas droit
			for(int i=x+1,j=y-1;i<bx+1 && j>by-1;i++,j--) {
				if( plateau.getType(i,j) != Main.VIDE){
					if(plateau.getCouleur(i,j) == plateau.getCouleur(bx, by)) {
						return false;
					}
					return false;
				}

			}
			//else;
		}


		//Modifications sur le plateau

		if(plateau.getType(bx,by) != Main.VIDE && plateau.getCouleur(bx,by)==plateau.getCouleur(x,y)){
			//Même couleur
			return false;
		}
		else if(plateau.getType(bx,by) != Main.VIDE && plateau.getCouleur(bx,by)!=plateau.getCouleur(x,y)){
			//Atteindre la position ou manger si une pièce adverse y est
			return true;
		}

		else if(plateau.getType(bx,by) == Main.VIDE){
			//Atteindre la position
			return true;
		}

		else return false; //


		//return false;
	}

}
