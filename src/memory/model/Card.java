package memory.model;

/**
 * Classe Cartes
 */
public class Card {

    private int id;
    //Chemin Dossier
    private String path;
    //Valeur utilise pour vérifier la paire
    private boolean pair;
    //Valeur utilise indiquant si la pair à étais trouver
    private boolean trouver;

    public Card() {
        this.trouver = false;
    }

    public boolean isPair() {
        return pair;
    }

    public void setPair(boolean pair) {
        this.pair = pair;
    }

    /**
     * Carte trouvée alors
     * @return Boolean
     */
    public boolean isTrouver() {
        return trouver;
    }

    /**
     * Changement d'état de la carte
     * @param trouver
     */
    public void setTrouver(boolean trouver) {
        this.trouver = trouver;
    }

    /**
     * Id initialisé à chaque carte
     * @return
     */
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    /**
     * Chemin du fichier
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Chemin du fichier
     * @param path
     */

    public void setPath(String path) {
        this.path = path;
    }
}
