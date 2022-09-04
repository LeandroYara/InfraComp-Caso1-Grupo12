import java.util.Scanner;

public class App{

    // Parámetros generales (generalizado a X niveles y Y transformaciones)
    public static int niveles = 3;
    public static int transformaciones = 3;

    public static void main(String[] args) {

        /* 1. Pedir los parámetros al usuario */

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

        /* 2. Crear los buzones */
        Buzon bInicial = new Buzon(tamanoBuzonFinal, 0);
        Buzon bFinal= new Buzon(tamanoBuzonFinal, niveles+1);

        Buzon[][] buzones = new Buzon[niveles][transformaciones-1];
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones-1; j++) {
                buzones[i][j] = new Buzon(tamanoBuzonIntermedio, j+1, i+1);
            }
        }

        /* 3. Crear los procesos */
        Proceso procesoInicial = new Proceso(0, bInicial, numMensajes);  
        Proceso procesoFinal= new Proceso(niveles+1, bFinal);

        Proceso[][] procesos= new Proceso[niveles][transformaciones];

        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones; j++) {
                procesos[i][j] = new Proceso(   
                                                j+1, // id
                                                i+1, // nivel
                                                j == 0 ? bInicial : buzones[i][j-1], // buzón liberar (anterior)
                                                j == transformaciones-1 ? bFinal : buzones[i][j] // buzón agregar (siguiente)
                                            );
            }
        }

        /* 4. Iniciar los procesos */

        System.out.println("--------------------- Iniciando el programa ---------------------");

        procesoInicial.start();
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones; j++) {
                procesos[i][j].start();
            }
        }
        procesoFinal.start();
    }

}