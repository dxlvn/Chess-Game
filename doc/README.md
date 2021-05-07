###### Date: Avril 2019

###### Membres: 

-- DADOU Émile
-- DOUBI Dylan
-- FONTAINE Benjamin
-- MARIE-ANNE Andy 

###### Contenu du projet

Ce projet réalisé en java contient le code d'un jeu d'échecs et une javadoc générée à partir de celui-ci.

###### Contenu de l'application

L'application propose un logiciel permettant de jouer aux échecs dans 3 modes différents:
L'ensemble du code est écrit sous Éclipse.

###### Usage de l'application (manuel utilisateur)

Tout d'abord il est nécéssaire de "run" le programme principal (inclus dans le fichier Main.java) pour lancer le jeu.
Trois modes de jeu sont proposés :
--1) Joueur VS Joueur
--2) Joueur VS IA
--3) IA VS IA 
Choisissez le mode de jeu puis appuyez sur la touche "Entrée"
Le plateau s'affiche.
Les pièces sont repérées par leur position. Pour en déplacer une, écrivez dans la console la position iniatiale et la position finale de la pièce que vous souhaitez déplacer puis appuyez sur "Entréé". Par exemple : a2c3 pour un cavalier qui se déplace de la position a2 vers la position c3.
Pour revenir sur un coup, entrez "undo".
Vous pouvez revoir les règles du jeu d'échecs si vous le souhaitez : https://fr.wikipedia.org/wiki/%C3%89checs
La partie se termine lorsqu'un des rois est mis en échec.


###### Conception du jeu (manuel technique)

La lecture des actions de jeu (mouvement en occurence) se fait à l'aider d'un Scanner, la conversion de l'entrée est gérée par un switch dans la méthode lire définie dans Echecs.java. 

Le plateau de jeu contient principalement une matrice 2D d'objets de type Piece. Des getters et setters de type et couleur sont utilisés pour déplacer les pièces. Les déplacements se font par "coupe/colle", c'est à dire par éffacement et réécriture sur les cases du plateau(méthode ccPiece de Plateau.java).

Les déplacements de chaque type de pièce sont testés dans leurs classes respectives, la méthode seDeplace renvoie donc true si les conditions sont respectées et false sinon. 
Le déplacement est ensuite effectué par le plateau si la méthode seDeplace de la case testée renvoie vrai 

Le 'undo' en entrée en lieu et place d'un déplacement permet de revenir sur les précédents coups. Cet historique est implémenté grâce à deux ArraysList entièrements synchronisés, l'un permettant d'effectuer à chaque fois un déplacément inverse au dernier (sur la tête de liste), et l'autre qui restaure la case qu'on vient de quitter permettant ainsi de ramener en jeu une pièce si le précédent déplacement avait entraîné son élimination. Ces deux ArrayList sont actualisés à chaque déplacement.

Les pions sont promus si ils atteignent la dernière de l'échiquier grâce à la méthode promotionPion.
Un scanner permet de fixer le nouveau type du pion (Dame,Fou,Tour,Cavalier) choisi par l'utilisateur en mode joueur et aléatoirement en mode IA.

À chaque début de tour et déplacement de pièce le programme teste la mise en échec du roi qui se déroule de la façon suivante :
- Si le roi est en échec,si il ne peut bouger du tout ou si aucune des pièces ne peut le défendre alors il est en échec et mat
- Sinon si il est échec et que ces deux dernières conditions sont fausses alors il est juste en échec au roi
- Enfin si le roi n'est pas échec mais que le joueur ne dispose d'aucun mouvement disponible alors on a un 'pat'





