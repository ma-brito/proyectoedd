import java.util.Scanner;
public class Registro{
  public static void main(String[] args){
        boolean continuar = true;

          int contador = 1;
    Scanner as = new Scanner(System.in);
    Scanner in = new Scanner(System.in);
    Jugador [] ps = new Jugador[0];
    Utilidadesj ut = new Utilidadesj();
    UtilidadesS uts = new UtilidadesS();
    Utilidadesj utr = new Utilidadesj();

    Torneo t = new Torneo();
  System.out.println("Bienvenido jugador");
  boolean valido = false;
  do{
    System.out.println("Si deseas registrarte, ingresa la palabra \"registrar\", de lo contrario presiona enter");
    String arg = as.nextLine();
    if(arg.equals("registrar")){
    System.out.println("Hola, este es el registro de jugadores.Por favor, introduzca los siguientes datos para el jugador");
    while(continuar){
    boolean validoo = false;
    System.out.println("Nombre:");
    String nombre= as.nextLine();
    System.out.println("Contraseña:");
    String contrasena= as.nextLine();
    String clave = "jugador" + contador;
    Jugador nuevo = new Jugador(nombre,contrasena);
    nuevo.setClave(clave);
    System.out.println(nuevo);
    while(!validoo){
    // ps[contador] = new Jugador(nombre,nacimiento,sexo,carrera,clave);
    ut.leerObjetosArchivo("Jugadores.txt");
      ps = ut.agregarAArregloJugador(nuevo);
      utr.EscribirObjetosArchivo("Jugadores.txt",ps);
      System.out.print("Desea agregar a otro jugador? (s/n):");
      String opcion =  as.nextLine().trim();
      if(opcion.equals("n")){
        continuar= false;
        validoo= true;
      }else if(opcion.equals("s")){
        validoo= true;
      }else
        System.out.println(" Por favor introduce una opción valida ");
      }
      contador++;
    }
  }
  System.out.println("Para ingresar, ingresa tu nombre y contraseña");
    System.out.print("Nombre: ");
    String nombre = as.nextLine();
    System.out.print("Contraseña: ");
    String contrasena = as.nextLine();
    Jugador [] jugadores = ut.leerObjetosArchivo("Jugadores.txt");
    for(int i=0; i<jugadores.length; i++){
      if(jugadores[i].getNombre().equals(nombre) && jugadores[i].getPassword().equals(contrasena)){
        valido = true;
        t.actual= jugadores[i];
      
        System.out.println("Bienvenido jugador, cuanto dinero depositaras para esta sesion?");
        double dinero = as.nextDouble();
        t.actual.setDinero(dinero);
        Carrera c = new Carrera(jugadores[i]);
        c.jugador.setDinero(dinero);
        System.out.println("Que deseas jugar? \n1Torneo \n2Carrera \n3Salir");
        String opcion = in.nextLine();
        boolean validacion = true;
        do{
        switch (opcion) {
          case "1" :
          validacion = true;
          while(true){
         try{
          t.iniciar();
          t.start();
         }catch(Exception e){
           System.out.println("");
         }
     
          }
          case "2":
     
          validacion = true;
          while(true){
            c.test();
          }

          case "3":
          System.out.println("Gracias por jugar");
          validacion = true;
          System.exit(0);
          break;

          default:
          System.out.println("Por favor ingresa una opción valida");
          validacion = false;
            break;
        }
      }while(!validacion);
        // t.iniciar();
        // t.start();
        // c.start();
        break;
      }
    }
      if(!valido){
        System.out.println("Usuario o contraseña incorrectos");   
      }
    
  }while(!valido);
  // t.iniciar();
  // t.start();

//  for(int i=0; i<ps.length; i++){
//     System.out.println(ps[i]);
//  }

    
    //     double d = ps.length;
    // int npar = (int) Math.round(d/2);


    // ps[0].revolver(ps);
    // String [] parejas1 = new String[npar];
    //   if(ps.length%2==0){
    //       for(int i = 0; i<ps.length; i+=2){
    //     parejas1 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //   }
    //   }else{
    //         for(int i = 0; i<(ps.length-1); i+=2){
    //     parejas1 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //   }
    //   parejas1[npar-1] = ps[ps.length-1].getClave();
    //   }

    //   ps[0].revolver(ps);
    //   String [] parejas3 = new String[npar];
    //     if(ps.length%2==0){
    //         for(int i = 0; i<ps.length; i+=2){
    //       parejas3 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //     }
    //     }else{
    //           for(int i = 0; i<(ps.length-1); i+=2){
    //       parejas3 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //     }
    //     parejas3[npar-1] = ps[ps.length-1].getClave();
    //     }

    //     ps[0].revolver(ps);
    //     String [] parejas2 = new String[npar];
    //       if(ps.length%2==0){
    //           for(int i = 0; i<ps.length; i+=2){
    //         parejas2 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //       }
    //       }else{
    //             for(int i = 0; i<(ps.length-1); i+=2){
    //         parejas2 [i/2] = ps[i].getClave() +","+ ps[i+1].getClave();
    //       }
    //       parejas2[npar-1] = ps[ps.length-1].getClave();
    //       }

    //     uts.EscribirObjetosArchivo("Parejas1.txt",parejas1);
    //     uts.EscribirObjetosArchivo("Parejas2.txt",parejas2);
    //     uts.EscribirObjetosArchivo("Parejas3.txt",parejas3);

    }
  }
