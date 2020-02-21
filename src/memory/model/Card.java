package memory.model;

public class Card {
    private int id;
    private String path;
    private boolean pair;
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

    public boolean isTrouver() {
        return trouver;
    }

    public void setTrouver(boolean trouver) {
        this.trouver = trouver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
