public class App{
    private static boolean bol=false;
    public static void main(String[] args) {
        int numMensajes=10000;
        int tamanoBuzon=100;
        Buzon buzonInicial=new Buzon(tamanoBuzon, 0);
       

        Proceso procesoInicial = new Proceso(0, buzonInicial, numMensajes);  
        procesoInicial.start();
        
        Buzon[] buzonesIntermedios=new Buzon[3];
        Proceso[] procesosIntermedios= new Proceso[6];      
        int tamanoBuzonFinal=1;
        Buzon buzonFinal= new Buzon(tamanoBuzon,3);

        for(int i=0; i<3;i++){
        buzonesIntermedios[i]=new Buzon(tamanoBuzonFinal, 1, i+1);  
         procesosIntermedios[i]= new Proceso(i,1
         ,buzonInicial,buzonesIntermedios[i]);
         procesosIntermedios[i+3]= new Proceso(i,2
         ,buzonesIntermedios[i],buzonFinal);
         procesosIntermedios[i].start();
         procesosIntermedios[i+3].start();
        }
        Proceso procesoFinal= new Proceso( 3, buzonFinal);
        procesoFinal.start();
       
        bol=true;
    }
}