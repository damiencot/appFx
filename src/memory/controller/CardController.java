package memory.controller;

import memory.view.poubelle;

public class CardController {

    private int identifiant ;
    static int clickCount=2;
    private poubelle poubelle;



    public boolean hasSameValue(CardController other){
        return this.getIdentifiant()== other.getIdentifiant();

    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public static int getClickCount() {
        return clickCount;
    }

    public static void setClickCount(int clickCount) {
        CardController.clickCount = clickCount;
    }

}