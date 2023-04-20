package jeu;

/**
 * Classe d'exception utilisee pour les erreurs de chargement de fichier
 */
public class FichierIncorrectException extends Exception{

    /**
     * Constructeur vide d'exception
     */
    public FichierIncorrectException(){
        super();
    }

    /**
     * Constructeur d'exception avec message
     * @param message message d'erreur
     */
    public FichierIncorrectException(String message){
        super(message);
    }
}
