package main;
import pieces.*;

import static java.lang.Math.abs;
import static java.lang.Math.random;
/**
 * <b>Classe IA permettant le mode Joueur VS Ordinateur.</b>
 * <p>
 * Une IA possède trois attributs:
 * <ul>
 * <li>couleur: correspondant à la couleur de la pièce chosie</li>
 * <li>x: abscisse de la pièce</li>
 * <li>y: ordonnée de la pièce</li>
 * </ul>
 * </p>
 * <p>
 * <b>NB: </b>L'IA est par défaut de couleur "NOIR"
 * </p>
 */
public class IA {
    private char couleur;
    private int x = (int) (random() * (8));
    private int y = (int) (random() * (8));

    public IA(char couleur) {
        this.couleur = couleur;
    }
    /**
     * Méthode qui récupère aléatoirement sur le plateau une pièce "NOIR" pour le tour de l'IA
     * @param plateau
     * 		Plateau de jeu
     */
    public void getPiece(Plateau plateau) {

        if (this.couleur != plateau.getCouleur(x, y)) {

            for (int i = 0; i < 8; ++i) {


                if (this.couleur != plateau.getCouleur(x, y)) {
                    for (int j = 0; j < 8; ++j) {


                        if (this.couleur != plateau.getCouleur(x, y)) {
                            this.y = this.y + 1;
                        }
                        if (this.y == 8) {
                            this.y = 0;
                        }
                    }

                    this.x = this.x + 1;
                    if (this.x == 8) {
                        this.x = 0;
                    }
                }
            }
        }
    }

/**
 * Méthode vérifiant si le mouvement de la pièce recupérée par la méthode précedente est possible
 * Voir ci-dessous:   
 * @see IA#getPiece(Plateau)
 * Si oui, on l'éffectue, sinon on teste tous les autres mouvements
 * Elle refait l'opération tant qu'on ne trouve pas de pièce ayant un mouvement valide
 * @param plateau
 * 		Plateau de jeu
 */
    void DeplaceIA (Plateau plateau) {
    	this.getPiece(plateau);

    	int[] tabval = new int[4];  
        tabval[1] = this.x;
        tabval[0] = this.y;
        tabval[3] = (int) (random() * (8));  
        tabval[2] = (int) (random() * (8)); 

           while (!plateau.deplacer(tabval, this.couleur, 2)) {

               for (int i = 0; i < 8; ++i) {

                       for (int j = 0; j < 8; ++j) {
                               if (tabval[2] + 1 == 8) {
                                   tabval[2] = 0;
                               } else {
                                   tabval[2] = tabval[2] + 1;
                               }
                           if (plateau.deplacer(tabval, this.couleur, 2)) {
                               return;
                           }

                       }
               if (tabval[3] + 1 == 8) {
                   tabval[3] = 0;
               } else {
                   tabval[3] = tabval[3] + 1;
               }
               if (plateau.deplacer(tabval, this.couleur, 2)) { return; }
               }
               this.x = (int) (random()*(8));
               this.y = (int) (random()*(8));
               this.getPiece(plateau);
               tabval[1] = this.x;
               tabval[0] = this.y;
               tabval[3] = (int) (random() * (8));
               tabval[2] = (int) (random() * (8));
           }
       }
    }