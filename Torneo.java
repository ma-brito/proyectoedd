import java.util.concurrent.ThreadLocalRandom;
public class Torneo {
    public static void main(String[] args) {
    UtilidadesS uts = new UtilidadesS();
    Competidor[] ps= new Competidor[16];
    for (int i = 0; i < ps.length; i++) {
        int habilidad = ThreadLocalRandom.current().nextInt(50, 400 + 1);
        ps[i] = new Competidor(habilidad, "jugador" + i);
    }
    double d = ps.length;
    int npar = (int) Math.round(d/2);


    ps[0].revolver(ps);
    String [] parejas1 = new String[npar];
      if(ps.length%2==0){
          for(int i = 0; i<ps.length; i+=2){
        parejas1 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
      }
      }else{
            for(int i = 0; i<(ps.length-1); i+=2){
        parejas1 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
      }
      parejas1[npar-1] = ps[ps.length-1].getClave();
      }

      ps[0].revolver(ps);
      String [] parejas3 = new String[npar];
        if(ps.length%2==0){
            for(int i = 0; i<ps.length; i+=2){
          parejas3 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
        }
        }else{
              for(int i = 0; i<(ps.length-1); i+=2){
          parejas3 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
        }
        parejas3[npar-1] = ps[ps.length-1].getClave();
        }

        ps[0].revolver(ps);
        String [] parejas2 = new String[npar];
          if(ps.length%2==0){
              for(int i = 0; i<ps.length; i+=2){
            parejas2 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
          }
          }else{
                for(int i = 0; i<(ps.length-1); i+=2){
            parejas2 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
          }
          parejas2[npar-1] = ps[ps.length-1].getClave();
          }

        uts.EscribirObjetosArchivo("Parejas1.txt",parejas1);
        uts.EscribirObjetosArchivo("Parejas2.txt",parejas2);
        uts.EscribirObjetosArchivo("Parejas3.txt",parejas3);
        }
    }
    
