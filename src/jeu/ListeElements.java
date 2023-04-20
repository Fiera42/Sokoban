package jeu;
import java.util.ArrayList;

/**
 * Classe representant une liste d'element
 */
public class ListeElements {
    private ArrayList<Element> liste;

    /**
     * Constructeur de liste vide
     */
    public ListeElements(){
        this.liste = new ArrayList<>();
    }

    /**
     * Methode pour ajouter un element a la liste
     * @param e element a ajouter
     */
    public void ajouterElement(Element e){
        this.liste.add(e);
    }

    /**
     * Getter de liste
     * @return ArrayList<Element> la liste d'element
     */
    public ArrayList<Element> getListe(){
        return this.liste;
    }

    /**
     * Getter d'element specifique
     * @param x position en x de l'element
     * @param y position en y de l'element
     * @return Retourne l'element a la position x,y, si il n'y a pas d'element renvoie nul
     */
    public Element getElement(int x, int y){
        for(int i = 0; i < liste.size(); i++) {
            if(liste.get(i).getX() == x && liste.get(i).getY() == y) return liste.get(i);
        }
        return null;
    }

    /**
     * Setter de liste d'element
     * @param liste la nouvelle liste a utiliser
     */
    public void setListe(ArrayList<Element> liste) {
        this.liste = liste;
    }
}
