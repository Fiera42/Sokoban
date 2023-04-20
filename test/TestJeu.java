import static org.junit.jupiter.api.Assertions.assertEquals;

import jeu.*;
import jeu.Perso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestJeu {

    Jeu jeu;
    @BeforeEach
    public void initEach() {
        ListeElements depots = new ListeElements();
        depots.ajouterElement(new Depot(0,0));
        depots.ajouterElement(new Depot(1,0));

        ListeElements caisses = new ListeElements();
        caisses.ajouterElement(new Caisse(1,1));

        Perso perso = new Perso(1,3);
        Labyrinthe laby = new Labyrinthe(4,4);
        this.jeu = new Jeu(laby, depots, caisses, perso);
    }
    @Test
    void testGetSuivantGauche() throws ActionInconnueException {

        int[] solution = {0,1};
        assertEquals(solution[0] , jeu.getSuivant(1,1, "Gauche")[0], "Gauche probleme x");
        assertEquals(solution[1] , jeu.getSuivant(1,1, "Gauche")[1], "Gauche Probleme y");
    }

    @Test
    void testGetSuivantDroite() throws ActionInconnueException {
        int[] solution = new int[]{2, 1};
        assertEquals(solution[0] , jeu.getSuivant(1,1, "Droite")[0], "Droite probleme x");
        assertEquals(solution[1] , jeu.getSuivant(1,1, "Droite")[1], "Droite probleme y");
    }

    @Test
    void testGetSuivantHaut() throws ActionInconnueException {
        int[] solution = new int[]{1,0};
        assertEquals(solution[0] , jeu.getSuivant(1,1, "Haut")[0], "Haut probleme x");
        assertEquals(solution[1] , jeu.getSuivant(1,1, "Haut")[1], "Haut probleme y");
    }

    @Test
    void testGetSuivantBas() throws ActionInconnueException {
        int[] solution = new int[]{1,2};
        assertEquals(solution[0] , jeu.getSuivant(1,1, "Bas")[0], "Bas probleme x");
        assertEquals(solution[1] , jeu.getSuivant(1,1, "Bas")[1], "Bas probleme y");
    }

    @Test
    void testEtreMur() {
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{true},{false}};
        laby.setMurs(newLaby);

        assertEquals(true, laby.etreMur(0,0), "Erreur etreMur devrait etre vrai");
        assertEquals(false, laby.etreMur(1,0), "Erreur etreMur devrait etre faux");
    }

    @Test
    void testGetElementCaisse() {
        Element test = new Caisse(1,2);
        jeu.getCaisses().ajouterElement(test);
        assertEquals(test, jeu.getCaisses().getElement(1,2), "La caisse n'a pas ete trouvee");
        assertEquals(null, jeu.getCaisses().getElement(2,2), "Il n'y a pas de caisse cet emplacement");
    }

    @Test
    void testGetElementDepot() {
        Element test = new Depot(1,2);
        jeu.getDepots().ajouterElement(test);
        assertEquals(test, jeu.getDepots().getElement(1,2), "Le depot n'a pas ete trouve");
        assertEquals(null, jeu.getDepots().getElement(2,2), "Il n'y a pas de depot cet emplacement");
    }

    @Test
    void testDeplacementNormalGauche() throws ActionInconnueException {
        //start position : 1x/3y
        jeu.deplacerPerso("Gauche");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(3, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
    }

    @Test
    void testDeplacementNormalDroite() throws ActionInconnueException {
        //start position : 1x/3y
        jeu.deplacerPerso("Droite");
        assertEquals(2, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(3, jeu.getPerso().getY(), "Deplacement erreur droite Y");
    }

    @Test
    void testDeplacementNormalBas() throws ActionInconnueException {
        //start position : 1x/3y
        jeu.getPerso().setY(2);
        jeu.deplacerPerso("Bas");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur bas X");
        assertEquals(3, jeu.getPerso().getY(), "Deplacement erreur bas Y");
    }

    @Test
    void testDeplacementNormalHaut() throws ActionInconnueException {
        //start position : 1x/3y
        jeu.getPerso().setY(4);
        jeu.deplacerPerso("Haut");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur haut X");
        assertEquals(3, jeu.getPerso().getY(), "Deplacement erreur haut Y");
    }

    @Test
    void testDeplacementCaisseBas() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(0);
        jeu.getPerso().setX(1);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Bas");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur bas X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur bas Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur bas X");
        assertEquals(2, caisse.getY(), "Deplacement caisse erreur bas X");
    }

    @Test
    void testDeplacementCaisseHaut() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(2);
        jeu.getPerso().setX(1);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Haut");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur haut X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur haut Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur haut X");
        assertEquals(0, caisse.getY(), "Deplacement caisse erreur haut Y");
    }

    @Test
    void testDeplacementCaisseGauche() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(1);
        jeu.getPerso().setX(2);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Gauche");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
        assertEquals(0, caisse.getX(), "Deplacement caisse erreur gauche X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur gauche Y");
    }

    @Test
    void testDeplacementCaisseDroite() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(1);
        jeu.getPerso().setX(0);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Droite");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur droite Y");
        assertEquals(2, caisse.getX(), "Deplacement caisse erreur droite X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur droite Y");
    }

    @Test
    void testDeplacementMur() throws ActionInconnueException {
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{true, true, true}, {true, false, true}, {true, true, true}};
        laby.setMurs(newLaby);
        jeu.getPerso().setY(1);
        jeu.getPerso().setX(1);

        jeu.deplacerPerso("Droite");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement mur erreur droite X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement mur erreur droite Y");

        jeu.deplacerPerso("Gauche");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement mur erreur gauche X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement mur erreur gauche Y");

        jeu.deplacerPerso("Haut");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement mur erreur haut X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement mur erreur haut Y");

        jeu.deplacerPerso("Bas");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement mur erreur bas X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement mur erreur bas Y");
    }

    @Test
    void testDeplacementDoubleCaisseGauche() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(1);
        jeu.getPerso().setX(3);
        jeu.getCaisses().ajouterElement(new Caisse(2,1));
        Caisse caisse1 = (Caisse) jeu.getCaisses().getElement(2,1);
        Caisse caisse2 = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Gauche");
        assertEquals(3, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
        assertEquals(2, caisse1.getX(), "Deplacement caisse1 erreur gauche X");
        assertEquals(1, caisse1.getY(), "Deplacement caisse1 erreur gauche Y");
        assertEquals(1, caisse2.getX(), "Deplacement caisse2 erreur gauche X");
        assertEquals(1, caisse2.getY(), "Deplacement caisse2 erreur gauche Y");
    }

    @Test
    void testDeplacementDoubleCaisseDroite() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setY(1);
        jeu.getPerso().setX(0);
        jeu.getCaisses().ajouterElement(new Caisse(2,1));
        Caisse caisse1 = (Caisse) jeu.getCaisses().getElement(1,1);
        Caisse caisse2 = (Caisse) jeu.getCaisses().getElement(2,1);
        jeu.deplacerPerso("Droite");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur droite Y");
        assertEquals(1, caisse1.getX(), "Deplacement caisse1 erreur droite X");
        assertEquals(1, caisse1.getY(), "Deplacement caisse1 erreur droite Y");
        assertEquals(2, caisse2.getX(), "Deplacement caisse2 erreur droite X");
        assertEquals(1, caisse2.getY(), "Deplacement caisse2 erreur droite Y");
    }

    @Test
    void testDeplacementDoubleCaisseBas() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(1);
        jeu.getPerso().setY(0);
        jeu.getCaisses().ajouterElement(new Caisse(1,2));
        Caisse caisse1 = (Caisse) jeu.getCaisses().getElement(1,1);
        Caisse caisse2 = (Caisse) jeu.getCaisses().getElement(1,2);
        jeu.deplacerPerso("Bas");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur bas X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur bas Y");
        assertEquals(1, caisse1.getX(), "Deplacement caisse1 erreur bas X");
        assertEquals(1, caisse1.getY(), "Deplacement caisse1 erreur bas Y");
        assertEquals(1, caisse2.getX(), "Deplacement caisse2 erreur bas X");
        assertEquals(2, caisse2.getY(), "Deplacement caisse2 erreur bas Y");
    }

    @Test
    void testDeplacementDoubleCaisseHaut() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(1);
        jeu.getPerso().setY(3);
        jeu.getCaisses().ajouterElement(new Caisse(1,2));
        Caisse caisse1 = (Caisse) jeu.getCaisses().getElement(1,2);
        Caisse caisse2 = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Haut");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur haut X");
        assertEquals(3, jeu.getPerso().getY(), "Deplacement erreur haut Y");
        assertEquals(1, caisse1.getX(), "Deplacement caisse1 erreur haut X");
        assertEquals(2, caisse1.getY(), "Deplacement caisse1 erreur haut Y");
        assertEquals(1, caisse2.getX(), "Deplacement caisse2 erreur haut X");
        assertEquals(1, caisse2.getY(), "Deplacement caisse2 erreur haut Y");
    }

    @Test
    void testDeplacementCaisseMurGauche() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(2);
        jeu.getPerso().setY(1);
        jeu.getLaby().ajouterMur(0,1);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Gauche");
        assertEquals(2, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur gauche X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur gauche Y");
    }

    @Test
    void testDeplacementCaisseMurDroite() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(1);
        jeu.getLaby().ajouterMur(2,1);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Droite");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(1, jeu.getPerso().getY(), "Deplacement erreur droite Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur droite X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur droite Y");
    }

    @Test
    void testDeplacementCaisseMurBas() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(1);
        jeu.getPerso().setY(0);
        jeu.getLaby().ajouterMur(1,2);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Bas");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur bas X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur bas Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur bas X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur bas Y");
    }

    @Test
    void testDeplacementCaisseMurHaut() throws ActionInconnueException {
        //box start position : 1x/1y
        jeu.getPerso().setX(1);
        jeu.getPerso().setY(2);
        jeu.getLaby().ajouterMur(1,0);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,1);
        jeu.deplacerPerso("Haut");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur haut X");
        assertEquals(2, jeu.getPerso().getY(), "Deplacement erreur haut Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur haut X");
        assertEquals(1, caisse.getY(), "Deplacement caisse erreur haut Y");
    }

    @Test
    void testDeplacementNormalOutOfBoundGauche() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
    }

    @Test
    void testDeplacementNormalOutOfBoundDroite() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur droite Y");
    }

    @Test
    void testDeplacementNormalOutOfBoundBas() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur Bas X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur Bas Y");
    }

    @Test
    void testDeplacementNormalOutOfBoundHaut() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur Haut X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur Haut Y");
    }

    @Test
    void testDeplacementCaisseOutOfBoundGauche() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false, false},{false, false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getCaisses().ajouterElement(new Caisse(0,0));
        jeu.getPerso().setX(1);
        jeu.getPerso().setY(0);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(0,0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(1, jeu.getPerso().getX(), "Deplacement erreur gauche X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur gauche Y");
        assertEquals(0, caisse.getX(), "Deplacement caisse erreur gauche X");
        assertEquals(0, caisse.getY(), "Deplacement caisse erreur gauche Y");
    }

    @Test
    void testDeplacementCaisseOutOfBoundDroite() throws ActionInconnueException {
        //box start position : 1x/1y
        Labyrinthe laby = jeu.getLaby();
        boolean[][] newLaby = {{false, false},{false, false}};
        laby.setMurs(newLaby);
        jeu.getCaisses().setListe(new ListeElements().getListe());
        jeu.getCaisses().ajouterElement(new Caisse(1,0));
        jeu.getPerso().setX(0);
        jeu.getPerso().setY(0);
        Caisse caisse = (Caisse) jeu.getCaisses().getElement(1,0);
        boolean error = false;
        try {
            jeu.deplacerPerso("Droite");
        }catch (ActionInconnueException e){
            error = true;
        }
        assertEquals(true, error, "il devrait y avoir une erreur");
        assertEquals(0, jeu.getPerso().getX(), "Deplacement erreur droite X");
        assertEquals(0, jeu.getPerso().getY(), "Deplacement erreur droite Y");
        assertEquals(1, caisse.getX(), "Deplacement caisse erreur droite X");
        assertEquals(0, caisse.getY(), "Deplacement caisse erreur droite Y");
    }
}
