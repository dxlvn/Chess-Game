package main;
import pieces.*;
import java.util.*;

public class Undo {
	 public ArrayList<Quadruple> undolist;
	 public ArrayList<Piece> cimetiere;
	 
	 public Undo(){
		  this.undolist = new ArrayList<Quadruple>();
		  this.cimetiere = new ArrayList<Piece>();
	 }

	 public void undoAdd(Quadruple el,Plateau p) {
		 this.undolist.add(el);
		 //this.cimetiere.add(p.piece[el.c][el.d]); à corriger
	 }
	 
	 public int getSize() {
		 return this.undolist.size();
	 }
	 
	 public void undoRemove() {
		 this.undolist.remove(this.getSize()-1);
		 this.cimetiere.remove(this.getSize()-1);
		 
	 }
	 
	 public Quadruple undoTete() {
		 Quadruple q = this.undolist.get(this.getSize()-1);		 
		 return q;
	 }
	
}
