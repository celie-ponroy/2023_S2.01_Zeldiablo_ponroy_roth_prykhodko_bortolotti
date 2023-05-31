package gameLaby.laby;

public class Escalier extends Entite{
        public boolean montant;//si l'escalier monte(true) ou descends(false)
    Escalier(int x, int y, boolean estmontant){
        super(x,y,false);
        this.montant = estmontant;
    }

    public void changerEtage(){
        //appel de changerLabyCourant de LabyJeu

    }
}
