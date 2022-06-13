import java.util.Random;

public class Carrera extends Thread {
    static Caballo[] caballos;
    Caballo apuesta, aux;
    Lista<Caballo> inscritos,cruzoLinea;
    Jugador jugador;
    IteradorLista<Caballo> iteratorInscritos,iteratorFin;
    boolean CarreraIniciada=false;
    
    public Carrera() {
        cruzoLinea = new Lista<Caballo>();
        inscritos = new Lista<Caballo>();
        iteratorInscritos = inscritos.iteradorLista();
        iteratorFin = cruzoLinea.iteradorLista();
    }
    public void inscribir(Caballo participante){
        inscritos.add(participante);
    }

    public void SetApuesta(int opcion) {
        if (opcion>caballos.length)
            throw new IllegalArgumentException();
        apuesta=caballos[opcion-1];
    }

    public void inscribirJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Caballo ganador(){
        Random aleatorio = new Random();
        double num = aleatorio.nextDouble();
        double prob=0;
        for(int i=0; i < 7; i++){
            aux = iteratorInscritos.next();
            prob+=aux.getProbabilidad();
            if(num < aux.getProbabilidad()){
                return aux;
            }
        }
        return null;
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

    public void setHistoriales() {
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

    public void updateHistoriales(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++){
                while (iteratorFin.hasNext()) {
                    aux = iteratorFin.next();
                    if(caballos[i].getID()==aux.getID())
                    caballos[i].setHistorial(cruzoLinea.indexOf(aux));
                }
            }
        }
    }

    @Override
        public void run(){
            setCaballos();
            int tiempo;
            for (tiempo=19; tiempo>=0; tiempo--){
                if(!CarreraIniciada){
                    if (cruzoLinea!=null){
                        for (int i = 0; i < 6; i++){
                            System.out.println(caballos[i].getID() + "Puesto: "+caballos[i].getLastPlace());
                        }
                        while (iteratorFin.hasNext()) {
                            aux = iteratorFin.next();
                            System.out.println(aux.getID()+"Puesto: ");
                        }
                    }
                    if (tiempo==0){
                        CarreraIniciada=true;
                    }

                } else{
                    System.out.println("Carrera en curso");// Si ya pasó el tiempo
                    System.out.println("Tiempo restante: " + tiempo + " segundos");
                    while (iteratorInscritos.hasNext()){
                        aux=iteratorInscritos.next();
                        System.out.println(aux.getID());
                    }
                    if (tiempo == 0) {
                        CarreraIniciada = false;
                        updateHistoriales();
                    }
                }
            }

            //Si el usuario entra al meno de carreras
            System.out.println("Periodo de apuesta");// Si todavía se puede apostar
            System.out.println("Para apostar en una carrera, presiona C");
            //Menú expandido
            System.out.println("I: ver mas información sobre el caballo H: ver historial");
            System.out.println("Tiempo restante: "+tiempo+" segundos");
            System.out.println("Escoge un Caballo:");
            //mostrar lista de caballos
            Caballo ganador;
            ganador = ganador();
            cruzoLinea.empty();
            cruzoLinea.add(ganador);
            inscritos.delete(ganador);
            Random aleatorio = new Random();
            int aux;
            for (int i = 0; i >= 5; i++) {
                aux = aleatorio.nextInt(inscritos.size() + 1);
                cruzoLinea.add(inscritos.getAt(aux));
                inscritos.delete(aux);
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e){

            }

            cruzoLinea.reverse();
            if (cruzoLinea.pop()== apuesta){
                System.out.println("ganaste we wuuuuuuuuu");
            }

            System.out.println("Aquí van los puestos");
        }

}