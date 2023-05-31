package gameLaby.laby;


public class Monstre extends Combattant{

    public Monstre(int vie, int frc, int x, int y, boolean col){
        super(vie, frc, x, y, col);
    }
    @Override
    public void deplacer(int[] suiv) {
        setX(suiv[0]);
        setY(suiv[1]);
    }

    public void attaquer(Combattant c){
        c.etreAttaque(Labyrinthe.ATTAQUE_MONSTRE);
    }
    public void etreAttaque(int force){
        int pvRestant = this.getPv() - force;
        this.setPv(pvRestant);
    }
}
