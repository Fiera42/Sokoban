package jeu;
/**
 * Classe d'exception utilisee pour representer une action inconnue
 */
public class ActionInconnueException extends Exception{

    /**
     * Constructeur vide pour generer l'exception
     */
    public ActionInconnueException(){
        super();
    }

    /**
     * Constructeur avec message
     * @param message message d'erreur
     */
    public ActionInconnueException(String message){
        super(message);
    }
}
