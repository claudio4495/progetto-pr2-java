package Progetto;

import java.util.*;
import java.util.Map;
import java.util.Set;

import Progetto.ClassSocialNetwork.NoElementException;
import Progetto.ClassSocialNetwork.NoKeyException;

public interface SocialNetwork   {
    // OVERVIEW: Tipo modificabile che rappresenta una rete sociale, intesa come l'insieme delle relazioni, attraverso i post, tra un utente di un social network 
	// e l'insieme degli utenti che segue (proprietà che dipende dai like ai post);inoltre implementa alcuni metodi per operare ed ottenere informazioni su di essa
	// Typical Element: {<follower->{autori post}> 
	
	public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException;
	       /*
	        REQUIRES: ps != null
	        THROWS: se ps = null solleva NullPointerException (eccezione disponibile in java, unchecked)
	        EFFECTS: Restituisce una nuova relazione derivata della lista di post passata come parametro
	      */
	
	public List<String> influencers();
	       /*
	         
	         EFFECTS: ritorna l'insieme degli utenti con il maggior numero di likes ai post della rete sociale passata come parametro
	       */
	      
	public Set<String> getMentionedUsers();
	       /*
	         EFFECTS: ritorna l'insieme degli utenti che hanno scritto un post e sono in almeno una relazione
	       */
	
	public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException;
	      /*
	         REQUIRES: ps != null
	         THROWS: se ps = null solleva NullPointerException
	         EFFECTS: data una lista di post ritorna l'insieme degli utenti che hanno scritto un post e sono in almeno una relazione
	      */
	
	public List<Post> writtenBy(String username) throws NullPointerException,NoElementException;
	      /*
	         REQUIRES: username != null && rete.containsKey(username)==true
	         THROWS: se username = null solleva NullPointerException(eccezione disponibile in java,unchecked); se username non è nella rete sociale solleva NoElementException(eccezione non disponibile in java, checked)
	         EFFECTS: ritorna una lista di post che ha come autore il parametro username e l'autore è nell'insieme delle relazioni
	      */
	
	public List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException;
	      /*
             REQUIRES: username != null && ps != null
             THROWS: se username = null solleva NullPointerException
             EFFECTS: data una lista di post ritorna una sottolista di post che ha come autore il parametro username
          */
	
	
	public List<Post> containing(List<String> words) throws NullPointerException;
	      /*
	         REQUIRES: words != null
	         THROWS: se words = null solleva NullPointerException
	         EFFECTS: restituisce una lista di post che nell'informazione text contengono almeno una dell'insieme delle parole passato come parametro
	      */
    
	public void buildNet();
	     /* 
	        MODIFIES: this
	        EFFECTS: Aggiunge le relazioni tra gli utenti dedotte dall'insieme di post
	     */
	
	public void addRelation(String utente, String autore) throws NullPointerException;
	     /*
	        REQUIRES: utente != null && autore != null 
	        THROWS: se utente = null solleva NullPointerException(eccezione disponibil in java,unchecked); se autore = null solleva NullPointerException(eccezione disponibile in java,unchecked); 
	        MODIFIES: this
	        EFFECTS: modifica l'associazione corrispondente alla chiave utente, aggiungendo un elemento all'insieme dei suoi valori corrispondenti
	      */
	
	public void removeRelation(String utente, String autore) throws NullPointerException, NoKeyException;
    /*
       REQUIRES: utente != null && autore != null && rete.containsKey(utente) = true
       THROWS: se utente = null solleva NullPointerException(eccezione disponibil in java,unchecked); se autore = null solleva NullPointerException(eccezione disponibile in java,unchecked); 
               se !this.rete.containsKey(utente) solleva NoKeyException;
       MODIFIES: this
       EFFECTS: modifica l'associazione corrispondente alla chiave utente, rimuovendo un elemento dall'insieme dei suoi valori corrispondenti
     */
    
    public Map<String,Set<String>> getMap();
	     // EFFECTS: ritorna la rete sociale
   
    public List<Post> getPost();
        // EFFECTS: ritorna la lista di post della rete 
	
}
