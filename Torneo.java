
import java.util.concurrent.ThreadLocalRandom;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;
import java.util.Scanner;
public class Torneo extends Thread {
     Competidor[] ps;
     double fA;
     double fB;
     Competidor ganador;
     Competidor apostado;
     Jugador actual;
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
        double divisor =(primero.getHabilidad() + segundo.getHabilidad());
          double pA = (double)primero.getHabilidad()/(divisor);
          double pB = 1 - pA;
          fA= 1/pA;
          fB= 1/pB;
          Random rd = new Random();
          int value = rd.nextInt(100);;
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
             Scanner as = new Scanner(System.in);
             Scanner in = new Scanner(System.in);
            Competidor[] ganadores = new Competidor[ps.length];
            // for(int i=0; i<ps.length; i++){
            //     ganadores[i] = ps[i];
            // }	
            int contador=1;
            while(ganadores.length!=1){
                System.out.println("Ronda " + contador);
                contador++;
                ganadores = new Competidor[ps.length/2];
            for(int i=0; i<ps.length/2; i++){
                Competidor ai=  ps[ps.length-(i+1)];
               
                boolean valido=true;
                String  apuesta;
                do{
                    valido=true;
                    System.out.println(ps[i].getClave() +": con habilidad "+  ps[i].getHabilidad() + " vs " + ai.getClave() +": con habilidad "+ ai.getHabilidad());
                    System.out.print("Tu saldo actual es: "+ actual.getDinero()  +" pesos.\nPor quien apuestas? (ingresa el numero del jugador): ");
                    apuesta = "jugador"+in.nextLine();
                    System.out.println("Apostatse por el "+apuesta);
                if(!(apuesta.equals(ps[i].getClave()) || apuesta.equals(ai.getClave()))){
                    valido=false;
                    System.out.println("\n**Error, ingrese una clave valida**");
                }
                }while(!valido);
                boolean validob=false;
                double monto=0;
                do{
                    try{
                        System.out.println("Ingrese el monto que desea apostar");
                        monto = as.nextDouble();
                        if(monto<=0){
                            System.out.println("\n**Ingrese un monto valido**");
                            validob=false;
                        }
                        else{
                            boolean mont = false;
                            do{
                                mont = actual.apostar(monto);
                             }while(!mont);
                            validob=true;
                            // as.next();
                        }
                    }catch(Exception a){
                        System.out.println("\n**Error, ingrese un monto valido**");
                        as.next();
                        validob=false;
                        
                    }
                }while(!validob);
               
         
                boolean ganador = competir(ps[i], ai);

                if(ganador){
                    if(apuesta.equals(ps[i].getClave())){
                        
                        actual.Gano(fA*monto);
                    }
                    ganadores[i] = ps[i];
                }
                else{
                    if(apuesta.equals(ai.getClave())){
                        actual.Gano(fA*monto);
                    }
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
            // this.ganador = ps[0];
            // if(apostado.equals(ganador)){
            //     System.out.println("El ganador es " + ps[0].getClave());
            //     System.out.println("Felicidades, has ganado");
            //     System.out.println("Tus ganancias son: " + fA);
            // }
            // else{
            //     System.out.println("El ganador es " + ps[0].getClave());
            //     System.out.println("Lo siento, has perdido");
            // }

        }
      
    }

    
