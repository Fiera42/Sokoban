package jeu;
import graphisme.Application;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe servant a lancer une partie de sokoban
 */
public class MainJeu {

    /**
     * Methode Main lancant l'application et creant un nouveau jeu
     * @param args
     */
    public static void main(String[] args) {
        try{
            Jeu jeu = Chargement.chargerJeu("laby/laby.txt");
            Application a = new Application(jeu);
        }
        catch(FichierIncorrectException fichierExept) {
            System.out.println(fichierExept.getMessage());
        }
        catch(FileNotFoundException noFichier) {
            System.out.println("Fichier introuvable");
        }
        catch(IOException erreur) {
            System.out.println(erreur.getMessage());
        }
    }
    /*
    public static void main(String[] args) throws Exception{
        int score = 0;
        Jeu jeu = Chargement.chargerJeu("laby/laby.txt");
        while(!jeu.etreFini()){
            System.out.println("\n"+jeu.jeuToString());
            System.out.println("*Score : "+score+"*");
            String mouv = MainJeu.mouvement();

            try {
                jeu.deplacerPerso(mouv);
                score +=1;
            }catch (ActionInconnueException e){
                System.out.println("Action inconnue : "+e.getMessage());
            }
        }

        if(jeu.etreFini()){
            System.out.println("bravo, vous avez terminé\n Votre score : "+score);
        }
    }*/

    public static String mouvement(){
        boolean correct = false;
        String res="";
        while (!correct) {
            System.out.println("mouvements : haut = z | gauche = q | droite = d | bas = s |");
            Scanner sc = new Scanner(System.in);
            String move = sc.nextLine();
            switch (move){
                case "z":
                    res = Jeu.HAUT;
                    correct=true;
                    break;
                case "s":
                    res= Jeu.BAS;
                    correct=true;
                break;
                case "q":
                    res= Jeu.GAUCHE;
                    correct=true;
                break;
                case "d":
                    res= Jeu.DROITE;
                    correct=true;
                break;
            }
        }
        return res;
    }


}
