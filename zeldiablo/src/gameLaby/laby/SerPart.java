package gameLaby.laby;

public class SerPart {
    int x, y;

    public SerPart(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }



    public boolean etrePresent(int dx, int dy) {
        boolean res = false;

        if ( dx ==x && dy == y )
        {
            res = true;
        }

        return res;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImage() {
        return "/corps_serp.png";
    }

}
