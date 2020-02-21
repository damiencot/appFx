package memory.model;

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

    public Boolean getTour() {
        return tour;
    }

    public void setTour(Boolean tour) {
        this.tour = tour;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore()
    {
        this.score++;
    }
}
