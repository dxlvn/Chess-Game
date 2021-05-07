package main;
import pieces.*;
import java.util.*;
/**
 *Classe Plateau faisant office de serveur de jeu 
 */
/**
 * <b>Classe Plateau</b>
 * <p>
 * Un tableau possède trois attributs:
 * <ul>
 * <li>histo : Gère l'historique de jeu grâce à "undo"
 * <li>piece : Tableau de pièces
 * <li>posRoi</li>
 * </ul>
 * </p>
 */
public class Plateau {
	private Historique histo;
	private Piece [][] piece;
	private int [][] posRoi;
	/**
	 * <b>Constructeur de la classe Plateau</b>
	 */
	public Plateau() {

		this.histo = new Historique();
		this.piece = new Piece[8][8];
		this.posRoi = new int[2][2];

		for(int i = 0; i < 8; i+=7){
			char couleur;
			if(i == 0) couleur = Main.BLANC;
			else couleur = Main.NOIR;

			this.piece[i][0] = new Tour(0, i, couleur);
			this.piece[i][1] = new Cavalier( 1, i, couleur);
			this.piece[i][2] = new Fou( 2, i, couleur);
			this.piece[i][3] = new Dame( 3, i, couleur);
			this.piece[i][4] = new Roi( 4, i, couleur);
			this.piece[i][5] = new Fou( 5, i, couleur);
			this.piece[i][6] = new Cavalier( 6, i, couleur);
			this.piece[i][7] = new Tour( 7, i, couleur);

		}
		this.posRoi[0][0] = 4; this.posRoi[0][1] = 0; //Position du Roi Blanc
		this.posRoi[1][0] = 4; this.posRoi[1][1] = 7; //Position du Roi Noir

		for(int j = 0; j < 8; j++) {
			this.piece[1][j] = new Pion( j, 1, Main.BLANC);
		}
		for(int j = 0; j < 8; j++) {
			this.piece[6][j] = new Pion( j, 6, Main.NOIR);
		}

		for(int j = 2; j < 6; j++)
			for(int i = 0; i < 8; i++) {
				this.piece[j][i] = new Vide( i, j, Main.VIDE);
			}

		this.piece[6][0] = new Pion(0, 6, Main.BLANC);
	}	

	/**
	 * <b>Constructeur de copie de la classe plateau</b>
	 * @param aCopier
	 * 		Plateau à copier 
	 */
	public Plateau(Plateau aCopier) {
		this.piece = new Piece[8][8];
		this.posRoi = new int[2][2];

		for(int j = 0; j < 8; j++)
			for(int i = 0; i < 8; i++) {
				switch(aCopier.piece[j][i].getType()) {
				case Main.CAVALIER: this.piece[j][i] = new Cavalier(aCopier.piece[j][i]); break;
				case Main.DAME: this.piece[j][i] = new Dame(aCopier.piece[j][i]); break;
				case Main.FOU: this.piece[j][i] = new Fou(aCopier.piece[j][i]); break;
				case Main.PION: this.piece[j][i] = new Pion(aCopier.piece[j][i]); break;
				case Main.ROI: this.piece[j][i] = new Roi(aCopier.piece[j][i]); break;
				case Main.TOUR: this.piece[j][i] = new Tour(aCopier.piece[j][i]); break;
				case Main.VIDE: this.piece[j][i] = new Vide(aCopier.piece[j][i]); break;	
				}

			}

		this.posRoi = aCopier.posRoi;
	}

	/**
	 * <b>Getter de type d'une case</b>
	 * @param x
	 * 		Abscisse de la case
	 * @param y
	 * 		Ordonnée de la case
	 * @return Le type de la case à laquelle il est appliqué 
	 */
	public char getType(int x, int y){
		return this.piece[y][x].getType();
	}
	/**
	 * <b>Getter de couleur de case</b>
	 * @param x
	 * 		Abscisse de la case
	 * @param y
	 * 		Ordonnée de la case
	 * @return La couleur de la case à laquelle il est appliqué
	 */
	public char getCouleur(int x, int y){
		return this.piece[y][x].getCouleur();
	}
	/**
	 * <b>Setter de type et de couleur de case</b>
	 * Fixe le type et la couleur de la case à laquelle il est appliqué au type et à la couleur donnés en paramètres
	 * @param x
	 * 		Abscisse de la case
	 * @param y
	 * 		Ordonnée de la case
	 * @param type
	 * 		Nouveau type de la case
	 * @param couleur
	 * 		Nouvelle couleur de la case
	 */

	/**
	 * Méthode qui "coupe/colle" une pièce (déplacement)
	 * @param ax
	 * 		Abscisse initiale de la pièce
	 * @param ay
	 * 		Ordonnée initiale de la pièce
	 * @param bx
	 * 		Abscisse finale de la pièce
	 * @param by
	 * 		Ordonnée finale de la pièce
	 */
	public void ccPiece(int ax, int ay, int bx, int by) {
		switch(this.piece[ay][ax].getType()) {
		case Main.CAVALIER: this.piece[by][bx] = new Cavalier(this.piece[ay][ax]); break;
		case Main.DAME: this.piece[by][bx] = new Dame(this.piece[ay][ax]); break;
		case Main.FOU: this.piece[by][bx] = new Fou(this.piece[ay][ax]); break;
		case Main.PION: this.piece[by][bx] = new Pion(this.piece[ay][ax]); break;
		case Main.ROI: this.piece[by][bx] = new Roi(this.piece[ay][ax]); break;
		case Main.TOUR: this.piece[by][bx] = new Tour(this.piece[ay][ax]); break;
		case Main.VIDE: this.piece[by][bx] = new Vide(this.piece[ay][ax]); break;	
		}
		this.piece[by][bx].setCoord(bx, by);
		this.vider(ax, ay);
	}
	
	/**
	 * Méthode qui change la pièce ciblée en pièce vide (case vierge)
	 * @param x
	 * 		Abscisse de la pièce
	 * @param y
	 * 		Ordonnée de la pièce 
	 */
	public void vider(int x, int y) {
		this.piece[y][x] = new Vide( x, y, Main.VIDE);
	}	
	/**
	 * Méthode qui vérifie si une pièce peut se déplacer de (ax;ay) en (bx;by) 
	 * @param ax
	 * 		Abscisse initiale
	 * @param ay
	 * 		Ordonnée initiale
	 * @param bx
	 * 		Abscisse finale
	 * @param by
	 * 		Ordonnée finale
	 * @return true si oui, false sinon
	 */
	public boolean peutBouger(int ax, int ay, int bx, int by) {
		return this.piece[ay][ax].seDeplace(bx, by, this);
	}
	/**
	 * Méthode principale de déplacement,cette méthode test dans un premier temps si l'entrée est un undo
	 * si oui elle fait un tour en arriere, si non elle déplace la pièce
	 * @param tabvar
	 * 		Tableau retourné lors du scanner 
	 * @param couleur
	 * @return true si le déplacement est possible, false sinon
	 */
	public boolean deplacer(int[] tabvar, char couleur, int mode){
		int ax = tabvar[1];
		int ay = tabvar[0];
		int bx = tabvar[3];
		int by = tabvar[2];
		boolean estIA = mode == 2 ? true: false;

		if(ax + ay + bx + by == 40) {
			this.undo();
			if(mode == 1) {
				this.undo();
				this.affiche();
				return false;
			}
			return true;
		}	
		//if(!this.pasPat(couleur)) return false;
		int coul = (couleur == Main.BLANC) ? 0 : 1;
		
		if(this.piece[ay][ax].getCouleur() == couleur && this.piece[ay][ax].seDeplace(bx,by,this) && this.piece[by][bx].getType() != Main.ROI) {
			
			Plateau fakePlateau = new Plateau(this);
			if(fakePlateau.getType(ax, ay) == Main.ROI) {
				fakePlateau.posRoi[coul][0] = bx; 
				fakePlateau.posRoi[coul][1] = by;
			}
			fakePlateau.ccPiece(ax, ay, bx, by);
			if(fakePlateau.piece[fakePlateau.posRoi[coul][1]][fakePlateau.posRoi[coul][0]].enEchec(fakePlateau))
				return false;

			
			if(this.getType(ax, ay) == Main.ROI) {
				this.posRoi[coul][0] = bx; 
				this.posRoi[coul][1] = by;
			}
				
			if(this.histo.getTaille() >= 10) this.histo.libere();			
			this.histo.ajoutA(this.piece[ay][ax]);
			this.histo.ajoutB(this.piece[by][bx]);
			this.ccPiece(ax, ay, bx, by);
			this.promotionPion(bx, by, estIA);
			return true;	
		}

		else return false;
	
	}
		
	/**
	 * Méthode qui renvoit un tour en arrière
	 */
	public void undo() {
		if(this.histo.getTaille() > 0) {
			Piece tmpA = new Piece(this.histo.recupererA());
			Piece tmpB = new Piece(this.histo.recupererB());

			switch(tmpA.getType()) {
			case Main.CAVALIER: this.piece[tmpA.getY()][tmpA.getX()] = new Cavalier(tmpA); break;
			case Main.DAME: this.piece[tmpA.getY()][tmpA.getX()] = new Dame(tmpA); break;
			case Main.FOU: this.piece[tmpA.getY()][tmpA.getX()] = new Fou(tmpA); break;
			case Main.PION: this.piece[tmpA.getY()][tmpA.getX()] = new Pion(tmpA); break;
			case Main.ROI: this.piece[tmpA.getY()][tmpA.getX()] = new Roi(tmpA); break;
			case Main.TOUR: this.piece[tmpA.getY()][tmpA.getX()] = new Tour(tmpA); break;
			case Main.VIDE: this.piece[tmpA.getY()][tmpA.getX()] = new Vide(tmpA); break;	
			}
			switch(tmpB.getType()) {
			case Main.CAVALIER: this.piece[tmpB.getY()][tmpB.getX()] = new Cavalier(tmpB); break;
			case Main.DAME: this.piece[tmpB.getY()][tmpB.getX()] = new Dame(tmpB); break;
			case Main.FOU: this.piece[tmpB.getY()][tmpB.getX()] = new Fou(tmpB); break;
			case Main.PION: this.piece[tmpB.getY()][tmpB.getX()] = new Pion(tmpB); break;
			case Main.ROI: this.piece[tmpB.getY()][tmpB.getX()] = new Roi(tmpB); break;
			case Main.TOUR: this.piece[tmpB.getY()][tmpB.getX()] = new Tour(tmpB); break;
			case Main.VIDE: this.piece[tmpB.getY()][tmpB.getX()] = new Vide(tmpB); break;	
			}
		
		}
	}

	/**
	 * Méthode qui Vérifie le statut du roi de la couleur ciblée. Elle renvoit:
	 * <ul> 
	 * <li> 0 si tout va bien </li> 
	 * <li> 1 s'il est en échec </li>
	 * <li> 2 s'il est en échec et mat </li>
	 * @param couleur
	 * 		Couleur du roi 
	 * </ul>
	 */
	
	
	public int situationEchec(char couleur) {
		
		int coul = (couleur == Main.BLANC) ? 0 : 1;
		if(piece[posRoi[coul][1]][posRoi[coul][0]].enEchec(this)) {
			if(this.entourageRoi(couleur)) {System.out.println("Echec au Roi"); return 0;}
			else if(this.peutDefendre(couleur)) {System.out.println("Echec au Roi");return 0;}
			else return 2;
		}
		if(!pasPat(couleur)) return 1;	
		return 0;
	}

	 
	/**
	 * Méthode vérifiant s'il existe au moins une case à portée du roi sur laquelle il ne sera pas en echec s'il s'y déplace
	 * @param couleur
	 * @return true si oui, false si non
	 */
	public boolean entourageRoi(char couleur) {
		int coul = (couleur == Main.BLANC) ? 0 : 1;

		for(int y = posRoi[coul][1] - 1; y <= posRoi[coul][1]+1; y++) {
			for(int x = posRoi[coul][0] - 1; x <= posRoi[coul][0] + 1; x++) {
				if(x < 0 || y < 0 || x > 7 || y > 7) continue;
				if(this.peutBouger(posRoi[coul][0], posRoi[coul][1], x, y))
					if(!seraEchec(posRoi[coul][0], posRoi[coul][1], x, y))
						return true;
			}
		}
		return false;
	}	
	
	
	public boolean pasPat(char couleur) {
		int coul = (couleur == Main.BLANC) ? 0 : 1;
		
		Plateau fakePlateau = new Plateau(this);
		fakePlateau.histo = new Historique();
		int[] tabvar = new int[4];
		
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(fakePlateau.piece[y][x].getCouleur() == couleur) {
					for(int j = 0; j < 8; j++) {
						for(int i = 0; i < 8; i++) {
						tabvar[0] = y;
						tabvar[1] = x;
						tabvar[2] = j;
						tabvar[3] = i;
						if(fakePlateau.deplacer(tabvar, couleur, 2))
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * Méthode vérifiant s'il existe au moins une pièce pouvant annuler l'echec de son roi
	 * @param couleur
	 * @return true si oui, false sinon
	 */
	public boolean peutDefendre(char couleur) {
		int coul = (couleur == Main.BLANC) ? 0 : 1;
		
		Plateau fakePlateau = new Plateau(this);
		fakePlateau.histo = new Historique();
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(fakePlateau.piece[y][x].getCouleur() == couleur) {
					for(int j = 0; j < 8; j++) {
						for(int i = 0; i < 8; i++) {
							if(fakePlateau.piece[y][x].seDeplace(i, j, fakePlateau)) {
								fakePlateau.histo.ajoutA(fakePlateau.piece[y][x]);
								fakePlateau.histo.ajoutB(fakePlateau.piece[j][i]);
								fakePlateau.ccPiece(x, y, i, j);
								if(fakePlateau.piece[fakePlateau.posRoi[coul][1]][fakePlateau.posRoi[coul][0]].enEchec(fakePlateau)) {
									fakePlateau.undo();
								}
								else return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	

	public void promotionPion(int x, int y, boolean estIA) {
		if(this.piece[y][x].getType() == Main.PION) {
			if((this.piece[y][x].getCouleur() == Main.BLANC && y == 7) || (this.piece[y][x].getCouleur() == Main.NOIR && y == 0)) {
				if(estIA) {
					System.out.println("Promotion d'un Pion !");
					Random rand = new Random();
					int promo = rand.nextInt(4);
					switch (promo) {
					case 0: this.piece[y][x] = new Dame( x, y, this.piece[y][x].getCouleur());
					break;
					case 1: this.piece[y][x] = new Tour( x, y, this.piece[y][x].getCouleur());
					break;
					case 2: this.piece[y][x] = new Fou( x, y, this.piece[y][x].getCouleur());
					break;
					case 3: this.piece[y][x] = new Cavalier( x, y, this.piece[y][x].getCouleur());
					break;
					default: ;
					break;
					}
				
				}
				else {
					Scanner sc = new Scanner(System.in);
					System.out.println("Promotion d'un Pion !");
					System.out.println("Veuillez saisir le nouveau type; ");
					System.out.print("Dame: D, Tour: T, Fou: F, Cavalier: C):  ");
					String chaine = "X";
					char newType  = chaine.charAt(0);
					while(newType != 'D' && newType != 'T' && newType != 'F' && newType != 'C') {
						chaine = sc.nextLine();
						if(chaine.length() > 0)
							newType = chaine.charAt(0);
					}
					switch (newType) {
					case 'D': this.piece[y][x] = new Dame( x, y, this.piece[y][x].getCouleur());
					break;
					case 'T': this.piece[y][x] = new Tour( x, y, this.piece[y][x].getCouleur());
					break;
					case 'F': this.piece[y][x] = new Fou( x, y, this.piece[y][x].getCouleur());
					break;
					case 'C': this.piece[y][x] = new Cavalier( x, y, this.piece[y][x].getCouleur());
					break;
					default: ;
					break;
					}
				}
			}
		}
	}

	

    //
	/**
	 *  Méthode vérifiant si le roi sera en échec s'il se déplace à la position ciblée
	 * @param ax
	 * 		Abscisse initiale
	 * @param ay
	 * 		Ordonnée initiale
	 * @param bx
	 * 		Abscisse finale
	 * @param by
	 * 		Ordonnée finale
	 * @return true si oui, false si non
	 */
	public boolean seraEchec(int ax, int ay, int bx, int by){
		Plateau fakePlateau = new Plateau(this);
		fakePlateau.ccPiece(ax, ay, bx, by);
		return fakePlateau.piece[by][bx].enEchec(fakePlateau);	
	}



	/**
	 * Méthode permettant d'afficher le plateau de jeu
	 */
	public void affiche() {
		int i, j, k;

		System.out.printf("%n");
		for(j = 0; j < 8; j++) {
			for(k = 0; k < 8; k++) {
				if(k==0) System.out.print(" +---+---");
				else System.out.print("-+---");
			}
			System.out.println("-+");
			System.out.printf(" | %c", 'H'-j);
			for(i = 0; i < 8; i++) {
				System.out.print(" | " + this.piece[7-j][i].getType() + this.piece[7-j][i].getCouleur());
			}
			System.out.println(" |");

		}
		for(j = 0; j < 3; j++) {
			if(j == 1) { 
				System.out.print(" |  ");
				for(k = 0; k < 8; k++) {
					System.out.print(" | " + (k+1) + " ");

				}
				System.out.println(" |");
			}
			else {
				for(k = 0; k < 8; k++) {
					if(k==0) System.out.print(" +---+---");
					else System.out.print("-+---");		
				}
				System.out.println("-+");

			}

		}
	}

}
