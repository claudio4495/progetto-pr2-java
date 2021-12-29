package Progetto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.print.DocFlavor.STRING;

import Progetto.ClassSocialNetwork.NoKeyException;


public class ClassSocialNetwork  implements SocialNetwork {
      
	   // AF(c) = {<c.rete.keySet(),c.rete.values()> | rete.keySet() != null && rete.values()!=null}  
	   // IR(c) = c.rete != null && posts!=null && for all i. 0<=i<c.rete.size(); key = string && value = Set<String> 
	   //         && per ogni i<=c.posts.size() => per ogni j<=c.posts.getLikes().size=> c.posts.get(i).contains(c.posts.getLikes().get(j))==true
	   //         => c.rete.put(c.posts.getLikes().get(j),c.posts.getAuthor()) 
	
	private Map<String,	Set<String>> rete;
	private List<Post> posts;
	
	//costruttore
	public ClassSocialNetwork(List<Post> posts) throws NullPointerException {
		this.rete = new Hashtable<String,Set<String>>();
		if (posts == null) throw new NullPointerException();
		this.posts=posts;
		this.buildNet();
		
	}
	// REQUIRES: posts != null
	// THROWS: se posts = null solleva NullPointerException(eccezione disponibile in java, unchecked)
	// EFFECTS: crea la rete sociale vuota ed associa ad essa l'insieme di post da cui verrà costruita 
	
	public Map<String, Set<String>> guessFollowers(List<Post> ps) throws NullPointerException{
		if (ps == null) throw new NullPointerException();
		ClassSocialNetwork nuovarete = new ClassSocialNetwork(ps);
		Map<String, Set<String>> mp = nuovarete.getMap();
		return mp;
		
	}
	
	public List<String> influencers() {
		List<String> listainfluencers = new ArrayList<String>(5);
		Vector<String> v = new Vector <String>();           // vector di supporto per inserire i valori corrispondenti alle chiavi
		Set<String> chiavi = this.rete.keySet();                      // set per raccogliere le chiavi della rete 
		for(String el:chiavi) {                                  // iterazione sulle chiavi per recuperare i valori ad esse associate e inserirle in v
			Set<String> valori = this.rete.get(el);         // set per contenere il valore(set) associato alla chiave el
			for(String val:valori) {
				v.add(val);
			}
				
		}
		int []a = new int[v.size()];                       // array di interi per contare le occorrenze delle stringhe usando le posizioni corrispondenti
		for (int i = 0; i<v.size();i++) {                 // al vector v costruito precedentemente
				int cont = 0;                              // variabile contatore per il conteggio delle occorrenze
		        String autore = v.get(i);                 
		        for (int j = 0; j<v.size(); j++) {
		        	if(autore.equals(v.get(j)))
		        		cont++;
		        }
		        a[i] = cont;
		       
		}
		int [] b = new int[5];                           //array di interi per il conteggio degli indici con più occorrenze
	 	for(int x = 0; x<a.length;x++) {
			int tmp = a[x];
			for (int y = 0 ; y<a.length;y++) {
				if(tmp>=a[y])
					b[x] = y;
			}
		}
		for(int z = 0; z<a.length;z++) {
			listainfluencers.add(z, v.get(b[z]));      //inserimento delle stringhe degli utenti più influenti nell'insieme di output
		}
		return listainfluencers;
	}
	
	public Set<String> getMentionedUsers(){
		Set<String> set = new HashSet<String>();
		for(Post el:posts) {
			if(rete.containsKey(el.getAuthor()))
					set.add(el.getAuthor());
					
		}
		return set;
	}
	
	public Set<String> getMentionedUsers(List<Post> ps) throws NullPointerException{
		if (ps == null) throw new NullPointerException();
		Set<String> set = new HashSet<String>();
		for(Post el:ps) {
			if(rete.containsKey(el.getAuthor()))
					set.add(el.getAuthor());
					
		}
		return set;
	}
	
	
	public List<Post> writtenBy(String username) throws NullPointerException,NoElementException{
		if (username == null) throw new NullPointerException();
		if (!rete.containsKey(username)) throw new NoElementException("username non è nella rete sociale");
		List<Post> lst = new Vector<Post>();
		for(Post e:posts) {
			if (e.getAuthor().equals(username))
				lst.add(e);
		}
		return lst;
		
	}
	
	public List<Post> writtenBy(List<Post> ps, String username) throws NullPointerException{
		if (ps==null) throw new NullPointerException();
		if (username==null) throw new NullPointerException();
		List<Post> lst = new Vector<Post>();
		for (Post elem: ps) {
			if (elem.getAuthor().equals(username))
					lst.add(elem);
		}
		
		return lst;
		
	}
	
	public List<Post> containing(List<String> words) throws NullPointerException{
		if (words==null) throw new NullPointerException();
		List<Post> lst = new Vector<Post>();
		for(String elem:words) {
			for(Post el:posts) {
				String s=String.copyValueOf(el.getText()); //conversione dell'array di char passato dal metodo getText() in una String
				if (s.contains(elem)==true)
					lst.add(el);
			}
		}
		return lst;
	}
	
	public void buildNet() {
		for (Post ps : this.posts) {
			for (String follower:ps.getLikes()) {
				this.addRelation(follower,ps.getAuthor());
			}
		}
		
		
	}
	
	public void addRelation(String utente, String autore) throws NullPointerException {
		if(utente == null) throw new NullPointerException();
		if(autore == null) throw new NullPointerException();
		if(!this.rete.containsKey(utente)) {
			Set<String> autori = new HashSet<String>();
			autori.add(autore);
			this.rete.putIfAbsent(utente,autori);
		}
		else {
		    Set<String> s = this.rete.get(utente);
		    s.add(autore);
		    this.rete.remove(utente);
		    this.rete.put (utente,s);
		}
	}
	
	public void removeRelation(String utente, String autore) throws NullPointerException, NoKeyException{
		if(utente == null) throw new NullPointerException();
		if(autore == null) throw new NullPointerException();
		if(!this.rete.containsKey(utente)) throw new NoKeyException(" utente non ha associazioni");
		Set<String> s = this.rete.get(utente);
		s.remove(autore);
		this.rete.remove(utente);
		this.rete.put (utente,s);
	}
	
	 
	public Map<String, Set<String>> getMap(){
		return this.rete;
	}
	
	public List<Post> getPost(){
		return this.posts;
	}
	
	class NoElementException extends Exception{
		public NoElementException(String s) {super(s);}
	}
	
	class NoKeyException extends Exception{
		public NoKeyException(String s) {super(s);}
	}
	
}
