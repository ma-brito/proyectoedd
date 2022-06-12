import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Carrera extends Thread {
    Caballo[] caballos;
    Lista<Caballo> inscritos,cruzoLinea;
    
    public Carrera() {
        cruzoLinea = new Lista<Caballo>();
        inscritos = new Lista<Caballo>();
    }
    public void inscribir(Caballo participante){
        inscritos.add(participante);
    }
    public Caballo ganador(){
        Random aleatorio = new Random();
        double num = aleatorio.nextDouble();
        double prob=0;
        IteradorLista<Caballo> iterator = inscritos.iteradorLista();
        Caballo aux;
        for(int i=0; i < 7; i++){
            aux = iterator.next();
            prob+=aux.getProbabilidad();
            if(num < aux.getProbabilidad()){
                return aux;
            }
        }
        return null;
    }

    @Override
        public void run(){
            Caballo ganador;
            if (inscritos.size() != 6) {// caso base
                // no se puede iniciar carrera
            }
            ganador = ganador();
            cruzoLinea.add(ganador);
            inscritos.delete(ganador);
            Random aleatorio = new Random();
            int aux;
            for (int i = 0; i >= 5; i++) {
                aux = aleatorio.nextInt(inscritos.size() + 1);
                cruzoLinea.add(inscritos.getAt(aux));
                inscritos.delete(aux);
            }
        }

}