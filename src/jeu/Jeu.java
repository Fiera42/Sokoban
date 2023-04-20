package jeu;
import java.util.ArrayList;

/**
 * Classe jeu regroupant toute les methodes et objets utiles pour faire fonctionner le jeu
 */

public class Jeu {
    //attributs
    private Perso perso;
    private ListeElements caisses;
    private ListeElements depots;
    private Labyrinthe laby;
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * Methodes pour creer un jeu
     * @param l Tableau de mur representant le labyrinthe
     * @param d Liste contenant tout les depots du jeu
     * @param c Liste contenant toute les caisses du jeu
     * @param p Personnage du jeu
     */
    
    public Jeu(Labyrinthe l, ListeElements d, ListeElements c, Perso p){
        this.laby=l;
        this.depots=d;
        this.caisses=c;
        this.perso=p;
    }

    /**
     * Methodes pour afficher le jeu
     * @return une string correspondant au jeu
     */
    public String jeuToString(){
        // on recupère la taille du labyrinthe
        int width = this.laby.getMurs().length;
        int height = this.laby.getMurs()[0].length;
        String res = "";

        //pour chaque ligne on ajoute le caractère de chaque colonne
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                //on get le caractère
                res+=getChar(x,y);
            }
            res+="\n";
        }
        return res;
    }

    /**
     * Methode qui renvoie le caractère en (x,y)
     * @param x indice x du caractère demandé
     * @param y indice y du caractère demandé
     * @return le caractère en (x,y)
     */
    private char getChar(int x, int y){
        // de base on part sur le fait que c'est vide
        char res= Labyrinthe.VIDE;
        //si c'est un mur alors on rajoute un mur
        if(this.laby.getMurs()[x][y]){
            res = Labyrinthe.MUR;
        }else
            //si c'est un personnage alors on rajoute un personnage
            if(this.perso.getX() == x && this.perso.getY() == y){
                res = Labyrinthe.PJ;
        }else
            //si il fait parti de l'Array caisses alors on rajoute une caisse
            if(parcourirListe(x,y,this.caisses.getListe())){
                res = Labyrinthe.CAISSE;
                //si il fait parti de l'Array depots alors on rajoute un depot
            } else if (parcourirListe(x,y,this.depots.getListe())) {
                res = Labyrinthe.DEPOT;
            }
        return res;
    }

    /**
     * Methode pour voir si le caratère en (x,y) appartient à la liste l
     * @param x indice x du caractère demandé
     * @param y indice y du caractère demandé
     * @param l la liste a parcourir
     * @return true si le caractère en (x,y) appartient a la liste
     */
    public boolean parcourirListe(int x, int y, ArrayList<Element> l){
        boolean res = false;
        int i= 0;
        while(!res && i<l.size()){
            if(l.get(i).getX() == x && l.get(i).getY() == y){
                res = true;
            }
            i++;
        }
        return res;
    }
    /**
     * Methode pour deplacer le personnage
     * @param action action que le joueur veut effectuer
     */
    public void deplacerPerso(String action) throws ActionInconnueException, IndexOutOfBoundsException{
        Element caisse = null;
        boolean etremur;

        int[] suivant = getSuivant(perso.getX(), perso.getY(), action); //recupere la case suivante
        
        try{
            etremur = laby.etreMur(suivant[0], suivant[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ActionInconnueException("il y a un mur");
        }

        if(!etremur) { //si il n'y a pas de mur sur cette case
            caisse = caisses.getElement(suivant[0], suivant[1]); //alors regarde si il y a une caisse
            if(caisse != null) { //si il y a une caisse
                int[] apres_Suivant = getSuivant(suivant[0], suivant[1], action); //regarde la case derriere la caisse

                try{
                    etremur = laby.etreMur(apres_Suivant[0],apres_Suivant[1]);
                }catch (ArrayIndexOutOfBoundsException e){
                    throw new ActionInconnueException("il y a un mur");
                }

                if(!etremur) { //si la case derriere la caisse n'a pas de mur
                    if(caisses.getElement(apres_Suivant[0], apres_Suivant[1]) == null) { //et qu'il n'y a pas une autre caisse derriere egalement
                        caisse.setX(apres_Suivant[0]); //alors pousse la caisse
                        caisse.setY(apres_Suivant[1]);
                        perso.setX(suivant[0]); //et deplace le personnage
                        perso.setY(suivant[1]);
                    }
                }
            }else { //si il n'y pas de caisse ni de mur devant le personnage
                perso.setX(suivant[0]); //deplacement le simplement
                perso.setY(suivant[1]);
            }
        } //ne se deplace donc pas quand il y a un mur, deux caisse ou quand ont sort de l'ecran (out of bound exeception)
    }
    /**
     * Methode pour recuperer la case suivante en fonction d'une direction donnee
     * @param x indice x de la case d'origine
     * @param y indice y de la case d'origine
     * @param action la direction dans la quelle le personnage veut se deplacer
     * @return int[x,y] la position de la case suivante
     */
    public int[] getSuivant(int x, int y, String action) throws ActionInconnueException, IndexOutOfBoundsException{
        switch (action) {
            case HAUT : return new int[]{x, y - 1};
            case BAS : return new int[]{x, y + 1};
            case GAUCHE : return new int[]{x - 1, y};
            case DROITE : return new int[]{x + 1, y};
            default : throw new ActionInconnueException("L'action '" + action + "' n'est pas reconnue");
        }
    }

    /**
     * Methode pour detecter si le jeu est terminer
     * @return true si tout les depots sont recouvert par une caisse
     */
    public boolean etreFini() {
        for(int i = 0; i < depots.getListe().size(); i++) { //pour chaque depot
            if(!parcourirListe(depots.getListe().get(i).getX(), depots.getListe().get(i).getY(), caisses.getListe())) return false; //regarde si il est recouvert par une caisse
        } //des qu'un depot non-recouvert est detecter, la methode s'arrete et retourne false
        return true;
    }

    /**
     * Methode pour obtenir le personnage
     * @return le personnage du jeu
     */
    public Perso getPerso() {
        return this.perso;
    }

    /**
     * Methode pour changer le personnage
     * @param perso personnage du jeu
     */
    public void setPerso(Perso perso) {
        this.perso = perso;
    }

    /**
     * Methode pour recuperer les caisses
     * @return retourne la liste de toute les caisses
     */
    public ListeElements getCaisses() {
        return this.caisses;
    }

    /**
     * Methode pour modifier la liste de caisse
     * @param caisses la nouvelle liste de caisse qui va remplacer l'ancienne
     */
    public void setCaisses(ListeElements caisses) {
        this.caisses = caisses;
    }

    /**
     * Methode pour recuperer la liste des depots
     * @return tableau des depots
     */
    public ListeElements getDepots() {
        return depots;
    }

    /**
     * Methode pour modifier la liste des depots
     * @param depots la nouvelle liste de depots qui va remplacer l'ancienne
     */
    public void setDepots(ListeElements depots) {
        this.depots = depots;
    }

    /**
     * Methode pour recuperer le labyrinthe
     * @return Le labyrinthe utiliser par le jeu
     */
    public Labyrinthe getLaby() {
        return this.laby;
    }

    /**
     * Methode pour modifier le labyrinthe
     * @param laby le nouveau labyrinthe que l'on veut utiliser pour le jeu
     */
    public void setLaby(Labyrinthe laby) {
        this.laby = laby;
    }
}
