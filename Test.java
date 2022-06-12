import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] historial = new int[5];
        double habilidad = 0;
        for (int i = 0; i < 5; i++) {
            historial[i] = 6;
        }
        for (int i = 0; i < 5; i++) {
            habilidad += historial[i];
            
        }
        Random aleatorio = new Random();
        double num = aleatorio.nextDouble();
        int nuum= aleatorio.nextInt(6);

        System.out.println(habilidad);
        System.out.println(num);
        System.out.println(nuum);

        System.out.println((35-habilidad)/(105));
    }
}
