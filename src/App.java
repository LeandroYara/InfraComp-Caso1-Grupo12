import java.util.Random;

public class App {

    public static Random r = new Random();

    // Parámetros generales
    public static int niveles = 3;
    public static int transformaciones = 3;

    // Parámetros dados por el usuario
    private static int numeroSubdivisiones = 200;
    private static int tamanoBuzonesExtremos = 2;
    private static int tamanoBuzonesIntermedios = 1;

    public static void main(String[] args) throws Exception {
        Buzon bI = new Buzon(tamanoBuzonesExtremos); // buzón inicial
        Buzon bF = new Buzon(tamanoBuzonesExtremos); // buzón final

        Buzon[][] buzones = new Buzon[niveles][transformaciones-1];
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones-1; j++) {
                buzones[i][j] = new Buzon(i+1, j+1, tamanoBuzonesIntermedios);
            }
        }

        Proceso pI = new Proceso(bI, numeroSubdivisiones); // Proceso inicial
        Proceso pF = new Proceso(bF); // Proceso final

        Proceso[][] procesos = new Proceso[niveles][transformaciones];

        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones; j++) {
                procesos[i][j] = new Proceso(
                                                i+1, // nivel
                                                j+1, // id
                                                j == 0 ? bI : buzones[i][j-1], // buzón liberar (anterior)
                                                j == transformaciones-1 ? bF : buzones[i][j] // buzón agregar (siguiente)
                                            );
            }
        }

        pI.start();
        for (int i = 0; i < niveles; i++) {
            for (int j = 0; j < transformaciones; j++) {
                procesos[i][j].start();
            }
        }
        pF.start();
    }
}
