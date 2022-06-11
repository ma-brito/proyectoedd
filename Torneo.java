import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
public class Torneo {
     Competidor[] ps;
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
          boolean gano = false;
          double pA = primero.getHabilidad()/(primero.getHabilidad() + segundo.getHabilidad());
          double pB = 1 - pA;
          double fA= 1/pA;
          double fB= 1/pB;
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

    
      }

    
