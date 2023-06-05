package gameLaby.entites;

public class Escalier extends EntiteInteractives{
        public boolean montant;//si l'escalier monte(true) ou descends(false)
    public Escalier(int x, int y, boolean estmontant){
        super(x,y,false);
        this.montant = estmontant;
    }

    public void changerEtage(){
        //appel de changerLabyCourant de LabyJeu

    }

    @Override
    public String getImage() {
        String res;
        if(this.montant){
            res="/esc_monte.png";
        }else {
            res = "/esc_desc.png";
        }
        return res;
    }
}
