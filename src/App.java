import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    	
    	int numeroSubconjuntos;
    	int tamanoBuzonesIntermedios;
    	int tamanoBuzonesExtremos;

    	Scanner lector = new Scanner(System.in);
    	System.out.println("Ingrese el numero de subconjuntos que quiere generar: ");
    	numeroSubconjuntos = lector.nextInt();
    	System.out.println("Ingrese el tamano de los buzones extremos: ");
    	tamanoBuzonesExtremos = lector.nextInt();
    	System.out.println("Ingrese el tamano de los buzones intermedios: ");
    	tamanoBuzonesIntermedios = lector.nextInt();
    	
    	lector.close();
    	
    	Buzon buzonInicial = new Buzon(tamanoBuzonesExtremos);
    	Buzon buzonFinal = new Buzon(tamanoBuzonesExtremos);
    	Buzon buzon11 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon21 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon12 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon22 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon13 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon23 = new Buzon(tamanoBuzonesIntermedios);
    	
    	Proceso procesoInicial = new Proceso(0, 0, null, buzonInicial, numeroSubconjuntos);
    	Proceso procesoFinal = new Proceso(0, 4, buzonFinal, null);
    	Proceso procesoi1 = new Proceso(1, 1, buzonInicial, buzon11);
    	Proceso proceso21 = new Proceso(1, 2, buzon11, buzon21);
    	Proceso procesof1 = new Proceso(1, 3, buzon21, buzonFinal);
    	Proceso procesoi2 = new Proceso(2, 1, buzonInicial, buzon12);
    	Proceso proceso22 = new Proceso(2, 2, buzon12, buzon22);
    	Proceso procesof2 = new Proceso(2, 3, buzon22, buzonFinal);
    	Proceso procesoi3 = new Proceso(3, 1, buzonInicial, buzon13);
    	Proceso proceso23 = new Proceso(3, 2, buzon13, buzon23);
    	Proceso procesof3 = new Proceso(3, 3, buzon23, buzonFinal);
    	
    	procesoInicial.start();
    	procesoFinal.start();
    	procesoi1.start();
    	proceso21.start();
    	procesof1.start();
    	procesoi2.start();
    	proceso22.start();
    	procesof2.start();
    	procesoi3.start();
    	proceso23.start();
    	procesof3.start();
    }
    
}
