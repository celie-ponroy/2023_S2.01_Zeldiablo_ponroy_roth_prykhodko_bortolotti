package gameLaby.laby;

public class Monstre extends Entite{

    public Monstre(int x, int y){
        super(x, y);
    }
    @Override
    public void deplacer() {

        int action = (int)(Math.random()*3);
        switch (action){
            case 0:
                setX(getX()+1);
                break;
            case 1:
                setX(getX()-1);
                break;
            case 2:
                setY(getY()+1);
                break;
            case 3:
                setY(getY()-1);
                break;
        }
    }
}
