import java.util.Random;

public class Proyecto3 {
    static Caballo[] caballos;
    public static void main(String[] args) {
    }

    public void setCaballos(){
        caballos=new Caballo[6];
        for (int i = 0; i < 6; i++) {
            caballos[i]= new Caballo("Caballo "+ (i+1));
        }

        setHistoriales();
        
        for (int i = 0; i < 6; i++) {
            caballos[i].setHabilidad();
            caballos[i].setProbabilidad();
            caballos[i].setCuota();
        }
    }

    public void setHistoriales(){
        Random al = new Random();
        int[] ar = { 1, 2, 3, 4, 5, 6 };

        for (int i = 0; i < 6; i++) {
            int IndAl = al.nextInt(6);
            int aux = ar[IndAl];
            ar[IndAl] = ar[i];
            ar[i] = aux;
        }

        for (int i = 0; i < 6; i++) {
            caballos[i].setHistorial(ar[i]);
        }
    }
}
