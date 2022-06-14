
public class ClasePrincipal{
    public static void main(String[] args) {
        Torneo t = new Torneo();
        t.actual = new Jugador("nan", "io");
        t.actual.setDinero(100);
        Carrera c = new Carrera(t.actual);
        //t.iniciar();
        //t.start();
        //c.start();
        c.test();
    }
}