package memory.model;

/**
 *
 */
public class Player {
    private int id;
    private String name;
    private Boolean tour;
    private int score;

    public Player() {
        this.id = 0;
        this.tour = false;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * À chaque tour on récupère son état
     * @return Boolean
     */
    public Boolean getTour() {
        return tour;
    }

    /**
     * À chaque tour on modifie son état
     * @param tour
     */
    public void setTour(Boolean tour) {
        this.tour = tour;
    }

    /**
     * On récupère le score de chaque joueur
     * @return Int
     */
    public int getScore() {
        return score;
    }

    /**
     * On modifie le score de chaque joueur
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Incrémente le score à chaque appel de la fonction
     */
    public void addScore()
    {
        this.score++;
    }
}
