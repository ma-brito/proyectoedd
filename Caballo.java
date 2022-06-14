

public class Caballo {
    private int[] historial = {0,0,0,0,0};
    private double probabilidad, cuota, habilidad=0;
    private String ID;
    
    public Caballo(String ID){
        setID(ID);
    }
    

    public void setHistorial(int a){
        for (int i=0; i<5; i++){
            if (historial[i] == 0) {
                historial[i] = a;
                return;
            }
            if (i==4){
                for (int j=0; j<=3; j++){
                    historial[j]=historial[j+1];
                }
                historial[i]=a;
            }
        }
    }

    public int getLastPlace(){
        return historial[4];
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setHabilidad(){
        if(historial[0]==0){
            return;
        }
        for (int i = 0; i < 5; i++) {
            habilidad+=historial[i];
        }
    }

    public String getHistorialStr() {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += historial[i] + ", ";
        }
        return str + historial[4] ;
    }

    public void setProbabilidad() {
        //Las carreras serán de 6 caballos
        probabilidad=(5*7-habilidad)/(5*(6*7/2));
    }

    public void setCuota() {
        cuota=1/probabilidad;
    }

    public double getHabilidad(){
        return habilidad;
    }
    
    public String getID() {
        return ID;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public double getCuota() {
        return cuota;
    }
}
