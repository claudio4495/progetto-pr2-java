package Progetto;

import java.util.Date;
import java.util.List;


import Progetto.ClassPost.OwnLikeException;
import Progetto.ClassPost.SameLikeException;

public interface Post {
	// Overwiev: Tipo modificabile che rappresenta un post di un social network tramite varie informazioni
	// Typical Element: <id,author,text,time,likes,...> nupla di dati != null con likes >0 e text<=140 caratteri 
	
	
	public int getId();
	// EFFECTS: ritorna l'id del post
	
	public String getAuthor();
	// EFFECTS: ritorna l'autore del post
	
	public char[] getText();
	// EFFECTS: ritorna il testo del posto
	
	public Date getData();
	// EFFECTS: ritrona la data di creazione del post
	
	public List<String> getLikes();
	// EFFECTS: ritrona i numeri del like del post
	
	public void addLike(String s) throws NullPointerException,OwnLikeException, SameLikeException;
	/* REQUIRES: s != null && s!=this.getAuthor() && this.getLikes().contains(s) = false
	   THROWS: se s = null solleva NullPointerException (eccezione disponibile in java, unchecked); se s = this.getAuthor() solleva OwnFollowException (eccezione non disponibile in java, checked)
	           se this.getLikes().contains(s) = true solleva SameFollowException (eccezione non disponibile in java, checked);
	   MODIFIES: this
	   EFFECTS: inserisce s nella lista degli utenti che hanno messo like al post
	*/
	
	public void removeLike(String s) throws NullPointerException;
	/*
	   REQUIRES: s!=null
	   THROWS: se s = null solleva NullPointerException(eccezione disponibile in java, unchecked)
	   MODIFIES: this
	   EFFECTS: rimuove dalla lista delle persone che hanno messo like, l'utente corrispondente ad s
	 */
	
}
