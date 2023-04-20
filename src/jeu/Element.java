package jeu;

/**
 * Classe representant un element de jeu
 */

public class Element {
    private int x, y;

    /**
     * Constructeur d'element avec paramatre
     * @param x position en x de l'element
     * @param y position en y de l'element
     */
    public Element(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * getter de position en x
     * @return int
     */
    public int getX(){
        return this.x;
    }

    /**
     * getter de position en y
     * @return int
     */
    public int getY(){
        return this.y;
    }

    /**
     * setter de position en x
     * @param i nouvelle position en x
     */
    public void setX(int i){
        this.x = i;
    }

    /**
     * setter de position en y
     * @param i nouvelle position en y
     */
    public void setY(int i){
        this.y = i;
    }


}
