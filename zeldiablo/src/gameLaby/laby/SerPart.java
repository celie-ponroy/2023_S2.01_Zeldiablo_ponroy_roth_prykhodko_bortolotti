package gameLaby.laby;

public class SerPart extends Entite {
    public SerPart(int xPos, int yPos) {
        super(xPos, yPos, true);
    }


    public boolean etrePresent(int dx, int dy) {
        boolean res = false;

        if ( dx ==getX() && dy == getY() )
        {
            res = true;
        }

        return res;
    }
    

    public String getImage() {
        return "/corps_serp.png";
    }

}
