public class Competidor {
  private int habilidad;
  private String clave;
  private int puntos;

  public Competidor(int habilidad, String clave) {
    this.habilidad = habilidad;
    this.clave = clave;
    this.puntos = 0;
  }

  public int getHabilidad() {
    return habilidad;
  }

  public void setHabilidad(int habilidad) {
    this.habilidad = habilidad;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public  Competidor[] revolver(Competidor [] ps){
    int j;
    Competidor temporal;
    for(int i = 0; i<ps.length; i++){
    j=(int) Math.round(Math.random() * (ps.length-1) + 0.5);
    temporal = ps[j];
    ps[j] = ps[i];
    ps[i]= temporal;
    }
    return ps;
  }
}
