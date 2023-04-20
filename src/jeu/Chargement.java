package jeu;
import java.io.*;
/**
 * Classe chargee de generer un nouveau jeu a partir d'un fichier
 */

public class Chargement {
    /**
     * Methode pour créer un jeu
     * @param nomFichier nom du fichier contenant le layout du niveau
     * @return un Objet jeu correspondant au contenu du fichier
     * @throws Exception Exceptions causé par des erreurs dans la contruction du niveau (erreurs d'écritures dnas le fichier)
     */
    public static Jeu chargerJeu(String nomFichier) throws FichierIncorrectException, FileNotFoundException, IOException{
        //ints pour la taille du labyrinthe
        int height = 0;
        int width = 0;

        //tableau contenant tout les lignes du fichier
        String[] tab = new String[100];

        //de quoi lire le fichier
        BufferedReader r = new BufferedReader(new FileReader (nomFichier));
        String res = r.readLine();

        //on recupère le contenu du fichier et on calcul la taille du labyrinthe
        while(res != null){
            tab[height] = res;
            if(res.length() > width) width = res.length();
            res = r.readLine();
            height++;
        }

        //on crée les différents éléments de Jeu
        Labyrinthe laby = new Labyrinthe(width, height);
        ListeElements caisses = new ListeElements();
        ListeElements depots = new ListeElements();
        Perso perso = null;
        //on commence a assembler les élément necessaire pour le jeu
        for(int i = 0; i < height; i++){
            width = tab[i].length();
            for(int j = 0; j < width; j++){
                //pour chaque charactère dans le tableau de string on fait une action :
                char ch = tab[i].charAt(j);
                switch(ch){
                    // ajouter un mur
                    case '#':
                        laby.ajouterMur(j,i);
                        break;
                    // ajouter une caisse
                    case '$':
                        caisses.ajouterElement(new Caisse(j,i));
                        break;
                    // ajouter un dépot
                    case '.':
                        depots.ajouterElement(new Depot(j,i));
                        break;
                    // ajouter un personnage
                    case '@':
                        perso = new Perso(j,i);
                        break;
                    // ajouter un vide
                    case ' ':
                        laby.ajouterVide(j,i);
                        break;
                    // le caractère n'est pas reconnu, il y a une erreur
                    default:
                        throw new FichierIncorrectException("caractere inconnu "+ch+"\n");

                }
            }
        }

        //on regarde pour les exceptions
        if(perso==null)throw new FichierIncorrectException("personnage inconnu\n");
        if(caisses.getListe().size()==0)throw new FichierIncorrectException("caisses inconnues\n");
        if(caisses.getListe().size()!=depots.getListe().size())throw new FichierIncorrectException("Caisses("+caisses.getListe().size()+") Depots ("+depots.getListe().size()+")\n");

        //on crée l'objet jeu
        Jeu jeu = new Jeu(laby,depots,caisses,perso);
        return jeu;
    }

}
