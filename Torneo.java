
import java.util.concurrent.ThreadLocalRandom;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.*;
public class Torneo extends Thread {
     Competidor[] ps;
    //  double fA;
    //  double fB;
     Competidor ganador;
     Competidor apostado;
     double fB;
     double monto;
     boolean jugando;
        String apuesta;
        double fA;
        double pA;
        double pB;
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


    public boolean competir (Competidor primero, Competidor segundo, double pA){
        System.out.println("Empieza la partida...");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println("Error");
        }
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
        // public class Mytask extends TimerTask{
        //     public void run(){
        //         System.out.println("Hola");
        //     }
        // }
        private String str = "";
       
        public class Input extends Thread{
            boolean valido;
            int i;
            Scanner as = new Scanner(System.in);
            Scanner in = new Scanner(System.in);
            Competidor ai;
            Competidor psi;
            Jugador actual;
            String apuesta;
            double monto;
            boolean jugando;
            Input (Competidor ps, Competidor ai, Jugador actual, double monto){
                valido = false;
                i = 0;
                this.psi = ps;
                this.ai = ai;
                this.actual = actual;
                

            }

            public void run(){
                jugando=false;
                synchronized(this){
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask()
                    {
                        public void run()
                        {
                            if( str.equals("") )
                            {
                                System.out.println( "Se ha acabado el tiempo para apostar. Ya comenzo la carrera" );
                                System.exit( 0 );
                                // System.exit( 0 );
                            }
                        }    
                    };
        // timer.schedule( task, 15*1000 );
                    do{
                        valido=true;
                 
                        System.out.println("Presion 1 para apostar, presiona 2 para ver el historial, presiona 3 para ver el dinero, presiona 4 para agregar fondos, 5 para salir");
                        str = in.nextLine();
                        System.out.println(str);
                        switch(str){
                            case "1":
                            break;
                            case "2":
                            System.out.println("Este es tu historial");
                            System.out.println(actual.getHistorial());
                            case "3":
                            System.out.println("Dinero: " + actual.getDinero());
                            break;
                            case "4":
                            System.out.println("Cuanto dinero quieres agregar?");
                            monto = in.nextDouble();
                            actual.setDinero(actual.getDinero()+monto);
                            break;
                            case "5":
                            System.exit(0);
                            break;
                            default:
                            System.out.println("Nooooo");
                            valido=false;
                        }
                        System.out.println("***"+ this.psi.getClave() +": con habilidad de "+ this.psi.getHabilidad() + " y cuota decimal de " + fA +  "\nvs\n***" + ai.getClave() +": con habilidad "+ ai.getHabilidad()+ " y cuota decimal de " + fB);
                        System.out.print("Tu saldo actual es: "+ actual.getDinero()  +" pesos.\nPor quien apuestas? (ingresa el numero del jugador): ");
                        apuesta = "jugador"+in.nextLine();
                        System.out.println("Apostatse por el "+apuesta);
                    if(!(apuesta.equals(this.psi.getClave()) || apuesta.equals(ai.getClave()))){
                        valido=false;
                        System.out.println("\n**Error, ingrese una clave valida**");
                    }
                    }while(!valido);
                    boolean validob=false;
                    monto=0;
                    do{
                        try{
                            
                            System.out.println("Ingrese el monto que desea apostar");
                            monto = as.nextDouble();
                            if(monto<=0){
                                System.out.println("\n**Ingrese un monto valido**");
                                validob=false;
                            }
                            else{
    
                            
                                 boolean mont = actual.apostar(monto);
                                
                                validob=mont;
                                // as.next();
                            }
                        }catch(Exception a){
                            System.out.println("\n**Error, ingrese un monto valido**");
                            as.next();
                            validob=false;
                            
                        }
                    }while(!validob);
                    str= "i";
                    System.out.println("\nApuesta realizada");
                    jugando=true;
                    notifyAll();
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        System.out.println("Error");
                    }
                    in.next();
                }
                
            }
        }
        @Override
        public void run(){
            synchronized (this){
            Competidor[] ganadores = new Competidor[ps.length];
            // for(int i=0; i<ps.length; i++){
            //     ganadores[i] = psi;
            // }	
            int contador=1;
            while(ganadores.length!=1){
                while(true ){
                System.out.println("Ronda " + contador);
                contador++;
                ganadores = new Competidor[ps.length/2];
                while(true){
                for(int i=0; i<ps.length/2; i++){
                    
                    Competidor ai=  ps[ps.length-(i+1)];
                    Competidor psi= ps[i];
                    double divisor =(psi.getHabilidad() + ai.getHabilidad());
                    pA = (double)psi.getHabilidad()/(divisor);
               
                    pB =(double)ai.getHabilidad()/(divisor);
             
                    fA= 1/pA;
    
                    fB= 1/pB;
                    boolean valido=true;
                    Input input = new Input(psi, ai, actual, monto);

                    input.start();
                    try{
                        Thread.sleep(15000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                // while (!Thread.currentThread().isInterrupted()) {
                //     try {
                 
              
                    // try{
                    //     input.join();
                    // }catch(InterruptedException e){
                    //     System.out.println("Error");
                    // }
                            
                    //     try{
                    //     wait(5000);
                    // } catch (InterruptedException ex) {
                    //     Thread.currentThread().interrupt();
                    // }
                
            
               
                   
                this.apuesta = input.apuesta;
                this.monto = input.monto;
                this.jugando = input.jugando;
                // this.actual = input.actual;
                // try{
                //     Thread.sleep(1000);
                // }catch(InterruptedException e){
                //     System.out.println("Error");
                // }
                
               
                
                boolean ganador = competir(psi, ai, pA);
                        System.out.println("\n");
                        if(jugando){
                            if(ganador){
                                if(apuesta.equals(psi.getClave())){
                                    System.out.println(fA*monto);
                                    actual.Gano(fA*monto);
                                }else{
                                    System.out.println("Lo siento, has perdido la apuesta");
                                }
                                ganadores[i] = psi;
                            }
                            else{
                                if(apuesta.equals(ai.getClave())){
                                    System.out.println(fB*monto);
                                    actual.Gano(fB*monto);
                                }else{
                                    System.out.println("Lo siento, has perdido la apuesta");
                                }
                                ganadores[i] = ps[ps.length-(i+1)];
                            }
                        }
                        // notify();
                    
                    // try{
                    //     Thread.sleep(3000);
                    // }catch(InterruptedException e){
                    //     System.out.println("Error");
                    // }
                
            
               
                }
            
                ps = new Competidor[ganadores.length];


                for(int j=0; j<ganadores.length; j++){
                    ps[j] = ganadores[j];
                    // System.out.println(ganadores[j]);
                    // System.out.println(ps[j].getClave());
                // try{
                //     Thread.sleep(1000);
                // }catch(InterruptedException e){
                //     System.out.println("Error");
                // }
            }
               
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
        }
      
    }

    
