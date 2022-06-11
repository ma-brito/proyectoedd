

public class Caballo {
    private int[] historial = new int[5];
    private double probabilidad, habilidad=0;
    private String ID;
    
    public Caballo(String ID){
        this.ID=ID;
        for(int i=0; i<5; i++){
            historial[i]=0;
        }
        
    }
    

    public void setHistorial(int a){
        for (int i=0; i<=4; i++){
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

    public void setHabilidad(){
        if(historial[0]==0){
            return;
        }
        for (int i = 0; i < 5; i++) {
            habilidad=+historial[i];
        }
    }

    public void setProbabilidad() {
        //Las carreras serán de 6 caballos
        probabilidad=(5*7-habilidad)/(5*(6*7/2));
    }

    public double getHabilidad(){
        return habilidad;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public double getCuota() {
        return 1/probabilidad;
    }
}
