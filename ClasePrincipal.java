
public class ClasePrincipal{
    public static void main(String[] args) {
        Torneo t = new Torneo();
        t.actual = new Jugador("nan", "io");
        t.actual.setDinero(100);
        t.iniciar();
        t.start();
    }
}