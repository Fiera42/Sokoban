import static org . junit . jupiter . api . Assertions .*;

import graphisme.Application;
import jeu.Chargement;
import jeu.FichierIncorrectException;
import jeu.Jeu;
import org . junit.jupiter.api.Test ;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestChargement {

    @Test
    void test1_ChargementOk() {
        //variables nécéssaires
        boolean erreur = false;
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_simple.txt");
        }catch (Exception e){
            erreur = true;
        }
        //tests
        assertEquals(false, erreur, "il y a une erreur dans la création du jeu");
    }
    @Test
    void test2_ToStringOk(){
        //variables nécéssaires
        String res = " ";
        String laby ="#######\n#. $  #\n#  @  #\n#  $ .#\n#######\n";
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_simple.txt");
            res  = j.jeuToString();
        }catch (Exception e){
            //
        }
        //tests
        assertEquals(laby, res, "le labyrinthe n'est pas le bon");
    }
    @Test
    void test3_Chargement_NoPerso(){
        //variables nécéssaires
        boolean erreur = false;
        String msg =" ";
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_testChargement1.txt");
        }catch (Exception e){
            erreur = true;
            msg = e.getMessage();
        }
        //tests
        assertEquals(true, erreur, "il devrait y avoir une erreur");
        assertEquals("personnage inconnu\n", msg, "le message n'est pas le bon");
    }
    @Test
    void test4_Chargement_NoCaisses(){
        //variables nécéssaires
        boolean erreur = false;
        String msg =" ";
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_testChargement2.txt");
        }catch (Exception e){
            erreur = true;
            msg = e.getMessage();
        }
        //tests
        assertEquals(true, erreur, "il devrait y avoir une erreur");
        assertEquals("caisses inconnues\n", msg, "le message n'est pas le bon");
    }
    @Test
    void test5_Chargement_NbDepotsCaissesError(){
        //variables nécéssaires
        boolean erreur = false;
        String msg =" ";
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_testChargement3.txt");
        }catch (Exception e){
            erreur = true;
            msg = e.getMessage();
        }
        //tests
        assertEquals(true, erreur, "il devrait y avoir une erreur");
        assertEquals("Caisses(2) Depots (1)\n", msg, "le message n'est pas le bon");
    }
    @Test
    void test6_Chargement_CharInconnu(){
        //variables nécéssaires
        boolean erreur = false;
        String msg =" ";
        //methodes
        try {
            Jeu j= Chargement.chargerJeu("./laby/laby_testChargement4.txt");
        }catch (Exception e){
            erreur = true;
            msg = e.getMessage();
        }
        //tests
        assertEquals(true, erreur, "il devrait y avoir une erreur");
        assertEquals("caractere inconnu *\n", msg, "le message n'est pas le bon");
    }

}
