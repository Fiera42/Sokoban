package jeu;

/**
 * classe representant les murs du labyrinthe
 */
public class Labyrinthe {
    private boolean[][] murs;

    public static final char MUR = '#';
    public static final char CAISSE = '$';
    public static final char PJ = '@';
    public static final char DEPOT = '.';
    public static final char VIDE = ' ';

    /**
     * Constructeur de labyrinthe avec taille definisable
     * @param x taille en x du labyrinthe
     * @param y taille en y du labyrinthe
     */
    public Labyrinthe(int x, int y){
        this.murs = new boolean[x][y];
    }

    /**
     * Methode permettant d'ajouter un mur au labyrinthe
     * @param x position en x du mur
     * @param y position en y du mur
     */
    public void ajouterMur(int x, int y){
        this.murs[x][y]=true;
    }

    /**
     * Methode pour retirer un mur au labyrinthe
     * @param x position du vide en x
     * @param y position du vide en y
     */
    public void ajouterVide(int x, int y){
        this.murs[x][y]=false;
    }

    /**
     * Getter de labyrinthe
     * @return boolean[][] tout les murs du labyrinthe
     */
    public boolean[][] getMurs(){
        return this.murs;
    }

    /**
     * Methode utilisee pour savoir si une case [x,y] dispose d'un mur
     * @param x la position en x de la case
     * @param y la position en y de la case
     */
    public boolean etreMur(int x, int y) throws IndexOutOfBoundsException{
        return murs[x][y];
    }

    /**
     * Setter de tableau de mur
     * @param murs le nouveau tableau de mur a utiliser
     */
    public void setMurs(boolean[][] murs) {
        this.murs = murs;
    }
}
