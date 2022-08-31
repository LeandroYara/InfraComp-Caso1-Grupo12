public class Proceso extends Thread {
    private int id;
    private int nivel;
    private Buzon recogida;
    private Buzon entrega;
    private static int numSubConjuntos;

    public Proceso(int id, int nivel, Buzon recogida, Buzon entrega) {// Buzones intermedios
        this.id = id;
        this.nivel = nivel;
        this.recogida = recogida;
        this.entrega = entrega;
    }

    public Proceso(int nivel, Buzon buzonRecogida) {// Es el proceso final
        this.nivel = nivel;
        this.recogida = buzonRecogida;
    }

    public Proceso(int nivel, Buzon entrega, int numSubConjuntos) { // Si es el proceso inicial
        this.nivel = nivel;
        this.entrega = entrega;
        this.numSubConjuntos = numSubConjuntos;
    }

    @Override
    public void run() {
        if (nivel == 0) {// Thread inicial escribe los n mensajes
            for (int i = 0; i < numSubConjuntos; i++) {
                if (i < numSubConjuntos - 1) {
                    entrega.escribirMensajePasivo("Se envía mensaje"+"'" + (i) + "'", nivel, 0);
                } else {
                    for (int j = 0; j < 3; j++)
                    {
                        entrega.escribirMensajePasivo("FIN", 0, 0);
                    }
                }
            }
        } else if(nivel==1) {
            String mensajeFin = "";
            while (!mensajeFin.equals("FIN")) {
                mensajeFin = this.recogida.retirarMensajeActivo();
                // this.entrega.escribirMensajePasivo(mensajeFin,nivel,id);
               
            }
        }
        // else if(nivel==2){
        //     String mensajeFin = "";
        //     while (!mensajeFin.equals("FIN")) {
        //         mensajeFin = this.recogida.retirarMensajePasivo(nivel,id);
               
        //     }
        // }
    }

}