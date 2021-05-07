package main;
import pieces.*;
import java.util.*;
/**
 * <b>Classe Historique</b>
 * <p>
 * Création de 2 listes qui seront synchronisés:
 * <ul>
 * <li>l'une contenant les pieces qu'on veut déplacer</li>
 * <li> l'autre contenant les pièces qui se sont faites écraser par la méthode ccPiece</li>
 * </ul>
 * </p>
 */
public class Historique {
	
	private List<Piece> histoListA;
	private List<Piece> histoListB;
		/**
		 * <b>Constructeur de la classe Historique</b>
		 * Initialisation des listes
		 */
		public Historique() {
		this.histoListA = new ArrayList<>();
		this.histoListB = new ArrayList<>();
	}
	
		/**
		 * Méthode permettant d'ajouter une pièce dans l'historique (histoListA)
		 * @param piece
		 * 		Pièce à ajouter
		 */
	public void ajoutA(Piece piece) {
		switch(piece.getType()) {
		case Main.CAVALIER: this.histoListA.add(new Cavalier(piece)); break;
		case Main.DAME: this.histoListA.add(new Dame(piece)); break;
		case Main.FOU: this.histoListA.add(new Fou(piece)); break;
		case Main.PION: this.histoListA.add(new Pion(piece)); break;
		case Main.ROI: this.histoListA.add(new Roi(piece)); break;
		case Main.TOUR: this.histoListA.add(new Tour(piece)); break;
		case Main.VIDE: this.histoListA.add(new Tour(piece)); break;
		}
	}
	/**
	 * Méthode permettant d'ajouter une pièce dans l'historique (histoListB)
	 * @param piece
	 * 		Pièce à ajouter
	 */
	public void ajoutB(Piece piece) {
		switch(piece.getType()) {
		case Main.CAVALIER: this.histoListB.add(new Cavalier(piece)); break;
		case Main.DAME: this.histoListB.add(new Dame(piece)); break;
		case Main.FOU: this.histoListB.add(new Fou(piece)); break;
		case Main.PION: this.histoListB.add(new Pion(piece)); break;
		case Main.ROI: this.histoListB.add(new Roi(piece)); break;
		case Main.TOUR: this.histoListB.add(new Tour(piece)); break;
		case Main.VIDE: this.histoListB.add(new Tour(piece)); break;
		}
	}
	
	/**
	 * Méthode permettant de désempiler la liste en renvoyant le dernier élément ajouté
	 * Cet élément est notamment supprimé de la liste
	 * @return Le dernier élément ajouté à la liste
	 */
	public Piece recupererA() {
		Piece tmp = new Piece(this.histoListA.get(this.histoListA.size() - 1));
		this.histoListA.remove(this.histoListA.size() - 1);
		return tmp;
	}
	
	/**
	 * Idem méthode précédente
	 * @see Historique#recupererA()
	 */
	public Piece recupererB() {
		Piece tmp = new Piece(this.histoListB.get(this.histoListB.size() - 1));
		this.histoListB.remove(this.histoListB.size() - 1);
		return tmp;
	}
	
	/**
	 * <b>Getter de la taille des listes histoListA et histoListB</b>
	 * @see Historique#histoListA
	 * @see Historique#histoListB
	 * À noter qu'elles ont toutes les deux la même taille.
	 * @return
	 */
	public int getTaille() {
		return this.histoListB.size();
	}
	
	/**
	 * Méthode supprimant l'élément le plus ancien des deux listes de l'historique 
	 */
	public void libere() {
		this.histoListA.remove(0);
		this.histoListB.remove(0);
	}
}
