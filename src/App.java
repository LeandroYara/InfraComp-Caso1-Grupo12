public class App{
    private static boolean bol=false;
    public static void main(String[] args) {
        int numMensajes=15;
        int tamanoBuzon=2;
        Buzon buzonInicial=new Buzon(tamanoBuzon, 0);
       

        Proceso procesoInicial = new Proceso(0, buzonInicial, numMensajes);  
        procesoInicial.start();
        
        Buzon[] buzonesIntermedios=new Buzon[2];
        Proceso[] procesosIntermedios= new Proceso[2];      
        int tamanoBuzonFinal=10;

        for(int i=0; i<2;i++){
        buzonesIntermedios[i]=new Buzon(tamanoBuzonFinal, 1, i+1);  
         procesosIntermedios[i]= new Proceso(i,1
         ,buzonInicial,buzonesIntermedios[i]);
         procesosIntermedios[i].start();
        }
        bol=true;
    }
}