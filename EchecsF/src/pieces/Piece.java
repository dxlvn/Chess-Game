package pieces;
import main.*;


/**
 * Classe Piece.
 * Une Piece est caractérisée par les informations suivantes:
 * @param type
 * 		Type de la pièce
 * @param x
 * 		Coordonnée en abscisse 
 * @parma y
 * 		Coordonnée en ordonnée
 * @param couleur
 * 		Couleur de la pièce
 * 
 */

public class Piece {
	protected char type;
	protected int x;
	protected int y;
	protected char couleur;
	
	/** 
	 * Un constructeur de la classe Piece.
	 * @param type
	 * 		Type de la pièce
	 * @param x
	 * 		Coordonnée en abscisse 
	 * @param y
	 * 		Coordonnée en ordonnée
	 * @param couleur
	 * 		Couleur de la pièce
	 */
	Piece(char type, int x, int y, char couleur){
		this.type = type;
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	
	/**
	 * Constructeur de copie de la classe Piece.
	 * Celui-ci permet de copier la pièce donnée en paramètre.
	 * @param aCopier
	 * 		Pièce à copier
	 * @return Une nouvelle pièce nouvellement créée
	 */
	public Piece(Piece aCopier){
		this.type = aCopier.type;
		this.x = aCopier.x;
		this.y = aCopier.y;
		this.couleur = aCopier.couleur;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	/**
	 * Getter du type de pièce 
	 * @return Le type de pièce à laquelle elle est appliquée.
	 */
	public char getType(){
		return this.type;
	}
	/**
	 * Getter de la couleur
	 * @return La couleur de la pièce à laquelle il est appliqué
	 */
	public char getCouleur(){
		return this.couleur;
	}

	/**
	 * Setter de coordonnées
	 * @param x
	 * 		Fixe l'abscisse à x
	 * @param y
	 * 		Fixe l'ordonnée à y
	 */
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Méthode vérifiant les conditions de déplacement d'une pièce
	 * @param bx
	 * 		Abscisse de la pièce
	 * @param by
	 * 		Ordonnée de la pièce
	 * @return true si le déplacement est possible, false sinon
	 */
	public boolean seDeplace(int bx, int by, Plateau plateau) {	
		return false;
	}
	/**
	 * Méthode testant l'échec
	 * @param plateau
	 * 		Le plateau de jeu
	 * @return true s'il y a échec, false sinon
	 */
	public boolean enEchec(Plateau plateau) {
		return false;
	}
	
}
