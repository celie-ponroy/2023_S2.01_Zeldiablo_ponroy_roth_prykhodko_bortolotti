import gameLaby.laby.Labyrinthe;

import java.io.IOException;

public class TestMonstre {
    @Test
    public void testPosMonstre() throws IOException {
        Labyrinthe laby = new Labyrinthe("laby0.txt");
        laby.getEntites();
    }
}