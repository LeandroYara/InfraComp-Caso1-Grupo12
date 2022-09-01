import java.util.Scanner;

public class App{
    private static boolean bol=false;
    public static void main(String[] args) {


        int numMensajes;
        int tamanoBuzonFinal;
        int tamanoBuzonIntermedio;

        Scanner lector = new Scanner(System.in);

        System.out.println("Ingrese el numero de subconjuntos que desea generar: ");
        numMensajes = lector.nextInt();
        System.out.println("Ingrese el tamano de los buzones extremos: ");
        tamanoBuzonFinal = lector.nextInt();
        System.out.println("Ingrese el tamano de los buzones intermedios: ");
        tamanoBuzonIntermedio = lector.nextInt();

        lector.close();

        Buzon buzonInicial=new Buzon(tamanoBuzonFinal, 0);

        Proceso procesoInicial = new Proceso(0, buzonInicial, numMensajes);  
        procesoInicial.start();
        
        Buzon[] buzonesIntermedios=new Buzon[6];
        Proceso[] procesosIntermedios= new Proceso[9];      
        Buzon buzonFinal= new Buzon(tamanoBuzonFinal, 4);

        for(int i=0; i<3;i++){
        buzonesIntermedios[i]= new Buzon(tamanoBuzonIntermedio, 1, i+1);
        buzonesIntermedios[i+3] = new Buzon(tamanoBuzonIntermedio, 2, i+4);  
         procesosIntermedios[i]= new Proceso(i+1,1
         ,buzonInicial,buzonesIntermedios[i]);
         procesosIntermedios[i+3]= new Proceso(i+1,2
         ,buzonesIntermedios[i],buzonesIntermedios[i+3]);
         procesosIntermedios[i+6]= new Proceso(i+1,3
         ,buzonesIntermedios[i+3], buzonFinal);
         procesosIntermedios[i].start();
         procesosIntermedios[i+3].start();
         procesosIntermedios[i+6].start();
        }
        Proceso procesoFinal= new Proceso(4, buzonFinal);
        procesoFinal.start();
       
        bol=true;
    }
}