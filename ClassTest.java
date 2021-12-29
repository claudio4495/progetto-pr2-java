package Progetto;

import java.lang.*;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import Progetto.ClassPost;
import Progetto.ClassPost.InvalidTextException;
import Progetto.ClassPost.NegativeIdException;
import Progetto.ClassPost.OwnLikeException;
import Progetto.ClassPost.SameLikeException;
import Progetto.ClassSocialNetwork.NoElementException;
import Progetto.ClassSocialNetwork.NoKeyException;

public class ClassTest {
       public static void main (String[] args) throws NegativeIdException, NullPointerException, InvalidTextException,OwnLikeException,SameLikeException,NoElementException,NoKeyException {
    	   String utente1 = "stefano" ;
    	   String utente2 = "luca";
    	   String utente3 = "gabriele";
    	   String utente4 = "chiara";
    	   String utente5 = "maria";
    	   List<Post> post = new Vector<Post>();
    	   
    	   int id = 0;
    	   Post p1 = new ClassPost(id,utente2,"ciao sono luca");
           post.add(p1);
           id++;
           Post p2 = new ClassPost(id,utente3, "ciao sono gabriele");
           post.add(p2);
           id++;
           /*
           for(Post s : post) {
          	 System.out.println(s.getAuthor());
           }
           */
           List<String> words = new Vector<String>();
           words.add("ciao");
           try {
              p2.addLike(utente1);
           }
           catch(OwnLikeException e) {System.out.println("autore mette like a se stesso");}
           catch(SameLikeException e) {System.out.println("utente mette più");}
           try {
               p2.addLike(utente4);
           
            }
           catch(OwnLikeException e) {System.out.println("autore mette like a se stesso");}
           catch(SameLikeException e) {System.out.println("utente mette più");}
           try {
               p1.addLike(utente5);
              
            }
           catch(OwnLikeException e) {System.out.println("autore mette like a se stesso");}
           catch(SameLikeException e) {System.out.println("utente mette più");}
           SocialNetwork rete = new ClassSocialNetwork(post);
           Set<String> menz = rete.getMentionedUsers(post);
           /*
            for(String m : menz) {
            
            	 System.out.println(m);
             }
             */
           List<String> influencers = rete.influencers();
           /*
           for(String s : influencers) {
          	 System.out.println(s);
           }
           */
           Set<String> s = rete.getMap().keySet();
          /*
           for(String string : s) {
            	 System.out.println(string);
             }
           */
           Set<String> menzione = rete.getMentionedUsers();
           /*
           for(String me : menzione) {
            	 System.out.println(me);
             }
             */
            try {
                List<Post> lst = rete.writtenBy(utente2);
                /*for(Post p : lst) {
                 	 System.out.println(p.getText());
                  }
                  */
            }
            catch(Exception e) {
            	
            }
            List<Post> parole = rete.containing(words);
            /*
            for(Post pos : parole) {
             	 System.out.println(pos.getText());
              }
              */
            try {
            	p1.removeLike(utente5);
            	rete.removeRelation(utente5, p1.getAuthor());
            }
            catch(Exception e) {}
             try {
        	   List<Post> scritto = rete.writtenBy(post,utente3);
        	   /*
        	    for(Post scritti : scritto) {
        	  
                	 System.out.println(scritti.getText());
                 }
                 */
             }
           catch(NullPointerException e) {}
        }
}
