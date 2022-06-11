import java.io.Serializable;
public class Jugador implements Serializable{
  private String nombre;
  private int puntos;
  private String clave;
  private String password;

  public Jugador(String n, String cont){
    nombre = n;
    puntos = 0;
    password = cont;

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


  public String toString(){
    return clave + "\nNombre: "+ nombre;
  }

  public void Gano(){
  	this.puntos += 10;
  }
  public void Empato(){
    this.puntos += 5;
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
