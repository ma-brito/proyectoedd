
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
public class Torneo extends Thread {
     Competidor[] ps;
     double fA;
     double fB;
    public void iniciar() {
    
    UtilidadesS uts = new UtilidadesS();
    ps= new Competidor[16];
    for (int i = 0; i < ps.length; i++) {
        int ability = ThreadLocalRandom.current().nextInt(50, 400 + 1);
        ps[i] = new Competidor(ability, "jugador" + i);
    }
    double d = ps.length;
    int npar = (int) Math.round(d/2);
    }


    public boolean competir (Competidor primero, Competidor segundo){
        // System.out.println(primero.getHabilidad() + " vs " + segundo.getHabilidad());
        double divisor =(primero.getHabilidad() + segundo.getHabilidad());
          double pA = (double)primero.getHabilidad()/(divisor);
          double pB = 1 - pA;
          fA= 1/pA;
          fB= 1/pB;
          Random rd = new Random();
          int value = rd.nextInt(100);
          if(pA*100>=value){
            System.out.println(primero.getClave() + " gana a " + segundo.getClave());
            return true;
          }
          else{
              System.out.println(primero.getClave() + " pierde a " + segundo.getClave());
              return false;
          }
        }

        @Override
        public void run(){
            

            Competidor[] ganadores = new Competidor[ps.length];
            // for(int i=0; i<ps.length; i++){
            //     ganadores[i] = ps[i];
            // }	
            while(ganadores.length!=1){
                ganadores = new Competidor[ps.length/2];
            for(int i=0; i<ps.length/2; i++){
                Competidor ai=  ps[ps.length-(i+1)];
                boolean ganador = competir(ps[i], ai);
                if(ganador){
                    ganadores[i] = ps[i];
                }
                else{
                    ganadores[i] = ps[ps.length-(i+1)];
                }
             
                }
                ps = new Competidor[ganadores.length];


                for(int j=0; j<ganadores.length; j++){
                    ps[j] = ganadores[j];
                    // System.out.println(ganadores[j]);
                    // System.out.println(ps[j].getClave());
            }
            }
            System.out.println("El ganador es " + ps[0].getClave());
            

        }
      
    }

    
