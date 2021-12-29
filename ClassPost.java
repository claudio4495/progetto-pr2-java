package Progetto;


import java.util.Vector;
import java.util.Date;
import java.util.List;

public class ClassPost implements Post{
	//AF(c) = <c.id,c.author,c.text,c.timestamp,c.likes>
	//IR(c) = c.author!=null && c.text!=null && 0<c.text.lenght<=140 && c.timestamp!=null && c.likes != null && c.id>0 && per ogni i<= c.likes.size()=> c.likes.gei(i)!=c.author &&
	//        per ogni i!=j . 0<= i,j <= c.likes.size()  => c.likes.get(i).equals(c.likes.get(j))=false 
      private final int id;
      private final String author;
      private char[] text;
      private final Date timestamp;
      private List<String> likes;
      
      //costruttore
    
      public ClassPost(int id,String author,String text) throws NegativeIdException, InvalidTextException, NullPointerException  {
    	  if (id<0)throw new NegativeIdException("Id del post negativo");
    	  this.id=id;
    	  if (author == null) throw new NullPointerException();
    	  this.author=author;
    	  if (text==null) throw new NullPointerException();
    	  if (text.length()==0) throw new InvalidTextException("testo non valido");
    	  this.text=new char[140];
    	  this.text=text.toCharArray();//copia della stringa di input nell'array di caratteri di dimensione 140
    	  this.timestamp=new Date();
    	  this.likes= new Vector<String>();
      }
      //REQUIRES: author!=null && text!=null && text.length()>0
      //THROWS: se author=null solleva NullPointerException (eccezione disponibile in java, unchecked); se text=null solleva NullPointerException (eccezione disponibile in java,unchecked);
      //        se text.lenght()=0 solleva InvalidTextException (eccezione non disponibile in java, checked); se id<0 solleva NegativeIdException (eccezione non disponibile in java, checked)
      //EFFECTS: crea un nuovo oggetto di tipo post inizializzando i suoi campi
      
    public int getId() {
   	  return id;
    }
  	//EFFECTS: ritorna l'id del post
  	
  	public String getAuthor() {
  		return author;
  	};
  	//EFFECTS: ritorna l'autore del post
  	
  	public char[] getText() {
  		return text;
  	}
  	//EFFECTS: ritorna il testo del posto
  	
  	public Date getData() {
  		return timestamp;
  	};
  	//EFFECTS: ritrona la data di creazione del post
  	
  	public List<String> getLikes() {
  		return likes;
  	}
  	//EFFECTS: ritrona i numeri del like del post
  	
  	public void addLike(String s) throws NullPointerException,OwnLikeException, SameLikeException  {
  		
  		if (s == this.author) throw new OwnLikeException("l'utente è l'autore del post");
  		if(this.likes.contains(s)) throw new SameLikeException("utente ha già messo like");
  		try{
  			this.likes.add(s);
  		}
  		catch(NullPointerException e) {}
  	}
    /*REQUIRES: s != null && s!=this.getAuthor()
 	   THROWS: se s = null solleva NullPointerException (eccezione disponibile in java, unchecked); se s = this.getAuthor() solleva OwnFollowException (eccezione non disponibile in java, checked)
  	   MODIFIES: this
  	   EFFECTS: incrementa di 1 la componente likes del post
  	*/
  	public void removeLike(String s) throws NullPointerException{
  		try {
  			this.likes.remove(s);
  			}
  		catch(NullPointerException e) {}
  	}
    
  	/*
	   REQUIRES: s!=null
	   THROWS: se s = null solleva NullPointerException(eccezione disponibile in java, unchecked)
	   MODIFIES: this
	   EFFECTS: rimuove dalla lista delle persone che hanno messo like, l'utente corrispondente ad s
	 */

  	class OwnLikeException extends Exception{
  		public OwnLikeException(String s) {super(s);}
  	}

  class InvalidTextException extends Exception{
	 public InvalidTextException(String s) {super(s);}
  }

  class NegativeIdException extends Exception{
	  public NegativeIdException(String s) {super(s);}
  }
  
  class SameLikeException extends Exception{
	  public SameLikeException(String s) {super(s);}
  }
}
