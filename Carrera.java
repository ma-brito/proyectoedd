import java.util.Random;
import java.util.Scanner;

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

    public void SetApuesta(int opcion) {
        if (opcion>caballos.length)
            throw new IllegalArgumentException();
        cApuesta=caballos[opcion-1];
    }

    public void inscribirJugador(Jugador jugador) {
        this.jugador = jugador;
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

    public void menuExtendido(int tiempo) {
        String opt2;
        Scanner entrada = new Scanner(System.in);
        System.out.println("I: ver mas información sobre los caballos");
        System.out.println("Tiempo restante: " + tiempo + " segundos");
        System.out.println("Escoge un Caballo (por número):");
        System.out.println(competidores());
        switch (opt2 = entrada.next()) {
            case "1":
                System.out.println("Cuanto quieres apostar?");
                apuesta=entrada.nextDouble();
                cApuesta=caballos[0];
                break;
            case "2":
                System.out.println("Cuanto quieres apostar?");
                apuesta = entrada.nextDouble();
                cApuesta = caballos[1];
                break;
            case "3":
                System.out.println("Cuanto quieres apostar?");
                apuesta = entrada.nextDouble();
                cApuesta = caballos[2];
                break;
            case "4":
                System.out.println("Cuanto quieres apostar?");
                apuesta = entrada.nextDouble();
                cApuesta = caballos[3];
                break;
            case "5":
                System.out.println("Cuanto quieres apostar?");
                apuesta = entrada.nextDouble();
                cApuesta = caballos[4];
                break;
            case "6":
                System.out.println("Cuanto quieres apostar?");
                apuesta = entrada.nextDouble();
                cApuesta = caballos[5];
                break;
            case "I":
                System.out.println(infoCompetidores());
                break;
        }

    }

    public void hacerCarrera(){
        cruzoLinea = new Lista<Caballo>();
        Caballo ganador;
        ganador = ganador();
        cruzoLinea.empty();
        cruzoLinea.add(ganador);
        inscritos.delete(ganador);
        Random aleatorio = new Random();
        System.out.println("el ganador es " + ganador.getID());
        int aux;
        for (int i = 4; i >0; i--) {
            aux = aleatorio.nextInt(i + 1);
            cruzoLinea.add(inscritos.getAt(aux));
            System.out.println(inscritos.getAt(aux).getID() + " terminó la carrera.");
            inscritos.delete(inscritos.getAt(aux));
        }

        cruzoLinea.add(inscritos.getAt(0));
        System.out.println(inscritos.getAt(0).getID() + " terminó la carrera al final.\n");
        inscritos.delete(inscritos.getAt(0));
        iteratorFin = cruzoLinea.iteradorLista();

        if (iteratorFin.next() == cApuesta) {
            Double ganancia = apuesta * cApuesta.getCuota();
            jugador.setDinero(jugador.getDinero()+ganancia);
            System.out.println("Ganaste "+ganancia+".");
        }

        for (int i = 0; i < 6; i++) {
            inscribir(caballos[i]);
        }

        updateHistoriales();

        for (int i = 0; i < 6; i++) {
            System.out.println(caballos[i].getID() + " Puesto: " + caballos[i].getLastPlace()+"\n");
        }
    }

    public void test(){
        setCaballos();
        System.out.println(competidores());
        System.out.println(infoCompetidores());
        hacerCarrera();
        System.out.println(infoCompetidores());
        
    }

    @Override
        public void run(){
            Caballo aux = null;
            Scanner entrada = new Scanner(System.in);
            setCaballos();
            int tiempo;
            String opt;
            for (tiempo=19; tiempo>=0; tiempo--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                if(!CarreraIniciada){
                    if (cruzoLinea!=null){
                        for (int i = 0; i < 6; i++){
                            System.out.println(caballos[i].getID() + " Puesto: "+caballos[i].getLastPlace());
                        }
                        iteratorFin = cruzoLinea.iteradorLista();
                        while (iteratorFin.hasNext()) {
                            aux = iteratorFin.next();
                            System.out.println(aux.getID()+" Puesto: ");
                        }
                    }
                    if (tiempo==0){
                        CarreraIniciada=true;
                    }
                    System.out.println("Periodo de apuesta");// Si todavía se puede apostar
                    System.out.println("Para apostar en la carrera, presiona C");
                    switch(opt=entrada.next()){
                        case "c":
                            System.out.println("I: ver mas información sobre el caballo H: ver historial");
                            System.out.println("Tiempo restante: " + tiempo + " segundos");
                            System.out.println("Escoge un Caballo (por número):");
                            System.out.println(competidores());
                            menuExtendido(tiempo); 
                            break;
                        default:
                            System.out.println("Para apostar en la carrera, presiona C");
                            break;
                    }

                } else{
                    System.out.println("Carrera en curso");// Si ya pasó el tiempo
                    System.out.println("Tiempo restante: " + tiempo + " segundos");
                    if (tiempo == 0) {
                        CarreraIniciada = false;
                        //updateHistoriales();
                    }
                }
            }
            run();
        }

}