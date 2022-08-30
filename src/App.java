import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    	
    	int numeroSubconjuntos;
    	int tamanoBuzonesIntermedios;
    	int tamanoBuzonesExtremos;

    	Scanner lector = new Scanner(System.in);
    	System.out.println("Ingrese el numero de subconjuntos que quiere generar: ");
    	numeroSubconjuntos = lector.nextInt();
    	System.out.println("Ingrese el tamaño de los buzones extremos: ");
    	tamanoBuzonesExtremos = lector.nextInt();
    	System.out.println("Ingrese el tamaño de los buzones intermedios: ");
    	tamanoBuzonesIntermedios = lector.nextInt();
    	
    	Buzon buzonInicial = new Buzon(tamanoBuzonesExtremos);
    	Buzon buzonFinal = new Buzon(tamanoBuzonesExtremos);
    	Buzon buzon11 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon21 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon31 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon12 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon22 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon32 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon13 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon23 = new Buzon(tamanoBuzonesIntermedios);
    	Buzon buzon33 = new Buzon(tamanoBuzonesIntermedios);
    	Proceso procesoInicial = new Proceso(0, 0, null, buzonInicial, numeroSubconjuntos);
    	Proceso procesoFinal = new Proceso(4, 0, buzonFinal, null);
    	Proceso proceso11 = new Proceso(1, 1, buzonInicial, buzon11);
    	Proceso proceso21 = new Proceso(2, 1, buzon11, buzon21);
    	
    	
    	
    }
}
