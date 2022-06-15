import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Carrera extends Thread {
    static Caballo[] caballos;
    Caballo cApuesta;
    Lista<Caballo> inscritos,cruzoLinea;
    Jugador jugador;
    IteradorLista<Caballo> iteratorInscritos,iteratorFin;
    boolean CarreraIniciada=false;
    double apuesta;
    
    public Carrera(Jugador jugador) {
        this.jugador=jugador;
        inscritos = new Lista<Caballo>();
        //iteratorInscritos = inscritos.iteradorLista();
    }
    public void inscribir(Caballo participante){
        inscritos.add(participante);
    }

    public Caballo ganador(){
        Caballo aux=null;
        Random aleatorio = new Random();
        double num = aleatorio.nextDouble();
        double prob=0;
        for(int i=0; i < 6; i++){
            aux = inscritos.getAt(i);
            prob+=aux.getProbabilidad();
            if(num < aux.getProbabilidad()){
                return aux;
            }
        }
        return aux;
    }

    public void setCaballos() {
        if(caballos==null){
            caballos = new Caballo[6];
            for (int i = 0; i < 6; i++) {
                caballos[i] = new Caballo("Caballo " + (i + 1));
            }

            setHistoriales();
        }

        for (int i = 0; i < 6; i++) {
            caballos[i].setHabilidad(0);
            caballos[i].setProbabilidad(0);
            caballos[i].setCuota(0);
            caballos[i].setHabilidad();
            caballos[i].setProbabilidad();
            caballos[i].setCuota();
            inscribir(caballos[i]);
        }
    }

    public void setHistorial() {
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

    public void setHistoriales() {
        for (int i = 0; i < 6; i++) {
            setHistorial();
        }
    }

    public void updateHistoriales(){
        Caballo aux = null;
        for (int i = 0; i < 6; i++) {
            iteratorFin = cruzoLinea.iteradorLista();
            while (iteratorFin.hasNext()) {
                aux = iteratorFin.next();
                if (caballos[i].getID() == aux.getID())
                    caballos[i].setHistorial(cruzoLinea.indexOf(aux)+1);
            }
        }
    }

    public String competidores(){
        String str="";
        Caballo aux = null;
        iteratorInscritos = inscritos.iteradorLista();
        while (iteratorInscritos.hasNext()) {
            aux = iteratorInscritos.next();
            str += aux.getID()+"\n";
        }
        return str;
    }

    public String infoCompetidores() {
        Caballo aux = null;
        String str = "";
        iteratorInscritos = inscritos.iteradorLista();
        while (iteratorInscritos.hasNext()) {
            aux = iteratorInscritos.next();
            str += aux.getID() + ". Historial:" + aux.getHistorialStr()+ ". Probabilidad de ganar:"+ aux.getProbabilidad() + ". Cuota:" +aux.getCuota()+ "\n";
        }
        return str;
    }

    public void menuCarreras() {
        boolean validoC=false;
        String str;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Para apostar en la carrera, presiona C");
        do {
            switch (str = entrada.next()) {
                case "c":
                    System.out.println("i: ver mas información sobre el caballo");
                    // System.out.println("Tiempo restante: " + tiempo + " segundos");
                    System.out.println("Escoge un Caballo (por número):");
                    System.out.println(competidores());
                    break;
                case "i":
                    System.out.println(infoCompetidores());
                    break;
                case "1":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    jugador.apostar(apuesta);
                    cApuesta = caballos[0];
                    validoC = true;
                    break;
                case "2":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    cApuesta = caballos[1];
                    validoC = true;
                    break;
                case "3":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    cApuesta = caballos[2];
                    validoC = true;
                    break;
                case "4":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    cApuesta = caballos[3];
                    validoC = true;
                    break;
                case "5":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    cApuesta = caballos[4];
                    validoC = true;
                    break;
                case "6":
                    System.out.println("Cuanto quieres apostar?");
                    apuesta = entrada.nextDouble();
                    cApuesta = caballos[5];
                    validoC = true;
                    break;
                default:
                    System.out.println("Para apostar en la carrera, presiona C");
                    break;
            }
        } while (!validoC);
    }

    /**
     * Método que se encarga de obtener el ganador y las posiciones finales de los caballos.
     */
    public void hacerCarrera(){
        System.out.println("Empieza la carrera...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        cruzoLinea = new Lista<Caballo>();
        Caballo ganador;
        ganador = ganador();
        cruzoLinea.empty();
        cruzoLinea.add(ganador);
        inscritos.delete(ganador);
        Random aleatorio = new Random();
        System.out.println(ganador.getID() + " es el primero en cruzar la linea ");
        int aux;
        for (int i = 4; i >0; i--) {
            aux = aleatorio.nextInt(i + 1);
            cruzoLinea.add(inscritos.getAt(aux));
            System.out.println(inscritos.getAt(aux).getID() + " terminó la carrera.");
            inscritos.delete(inscritos.getAt(aux));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }

        cruzoLinea.add(inscritos.getAt(0));
        System.out.println(inscritos.getAt(0).getID() + " terminó la carrera al final.\n");
        inscritos.delete(inscritos.getAt(0));
        iteratorFin = cruzoLinea.iteradorLista();

        if (iteratorFin.next() == cApuesta) {
            Double ganancia = apuesta * cApuesta.getCuota();
            jugador.Gano(ganancia);
            // System.out.println("Ganaste "+ganancia+".");

        }else{
            System.out.println("Perdiste.");
        }
        
        updateHistoriales();
        setCaballos();

        for (int i = 0; i < 6; i++) {
            System.out.println(caballos[i].getID() + " Puesto: " + caballos[i].getLastPlace()+"\n");
        }
    }

    public void test(){
        setCaballos();//crea un historial desde cero
        menuCarreras();//únicamente sirve para que el usuario apueste pero la carrera puede correr indepentientemente de si apuesta o no.
        hacerCarrera();//hace toda la carrera, 5 sleeps de un segundo cada uno.
        System.out.println(infoCompetidores());//solo para comprobar que se agregó lo de la última carrera
        
    }

    @Override
        public void run(){
        }

}