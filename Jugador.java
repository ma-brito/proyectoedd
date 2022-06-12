import java.io.Serializable;
import java.text.DecimalFormat;
public class Jugador implements Serializable{
  private String nombre;
  private int puntos;
  private String clave;
  private String password;
  private double dinero;
  

  public Jugador(String n, String cont){
    nombre = n;
    puntos = 0;
    password = cont;
    dinero = 0;

  }

  public Jugador [] revolver(Jugador [] ps){
    int j;
    Jugador temporal;
    for(int i = 0; i<ps.length; i++){
    j=(int) Math.round(Math.random() * (ps.length-1) + 0.5);
    temporal = ps[j];
    ps[j] = ps[i];
    ps[i]= temporal;
    }
    return ps;
  }

  public double getDinero(){
    return dinero;
  }

  public void setDinero(double d){
    dinero = d;
  }
  public boolean apostar(double m){
    if (dinero<m){
      System.out.println("No tiene suficiente dinero, ingresa una cantidad válida");
      return false;
    }
    else{
      dinero-=m;
      return true;
    }
  }
  


  public String toString(){
    return clave + "\nNombre: "+ nombre;
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}

  public void Gano(double n){
    
    double no = round(n, 2);
    System.out.println("Felicidades obtienes" + no + " de vuelta");
    dinero+=  no;
    System.out.println("Tu saldo es: " + dinero);
  }



public String getNombre(){
	return nombre;
}

public void setNombre(String nombre){
	this.nombre = nombre;
}

public int getPuntos(){
	return  puntos;
}

public void setPuntos(int  puntos){
	this.puntos =  puntos;
}

public String getClave(){
	return clave;
}

public void setClave(String clave){
	this.clave = clave;
}

public String getPassword(){
  return password;

}

public void setPassword(String password){
  this.password = password;
}

}
