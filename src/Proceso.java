public class Proceso extends Thread {
    private int id;
    private int nivel;
    private Buzon recogida;
    private Buzon entrega;
    private static int numSubConjuntos;

    // Proceso inicial
    public Proceso(int pNivel, Buzon pEntrega, int pNumSubConjuntos) {
        this.nivel = pNivel;
        this.entrega = pEntrega;
        numSubConjuntos = pNumSubConjuntos;
    }

    // Proceso final
    public Proceso(int pNivel, Buzon pBuzonRecogida) {
            this.nivel = pNivel;
            this.recogida = pBuzonRecogida;
    }
    
    // Proceso intermedio
    public Proceso(int pId, int pNivel, Buzon pRecogida, Buzon pEntrega) {
        this.id = pId;
        this.nivel = pNivel;
        this.recogida = pRecogida;
        this.entrega = pEntrega;
    }

    @Override
    public void run() {
        String Tname = nivel == 0 ? "Tini" : (
           nivel == App.niveles+1 ? "Tfin" : "T" + nivel + id);
        System.out.println("Proceso " + Tname + " iniciando...");
        if (nivel == 0) {
            // Proceso inicial
            for (int i = 0; i < numSubConjuntos; i++) {
                if (i < numSubConjuntos - 1) {
                    entrega.escribirMensajeActivo("M"+i, Tname);
                } else {
                    for (int j = 0; j < App.niveles; j++)
                    {
                        entrega.escribirMensajeActivo("FIN", Tname);
                    }
                }
            }
        } else if (nivel > 0 && nivel <= App.niveles) {
            // Proceso intermedio
            String mensajeFin = "";
            while (!mensajeFin.contains("FIN")) {
                mensajeFin = this.recogida.retirarMensajePasivo(Tname);
                if (!mensajeFin.contains("FIN")){
                    mensajeFin = mensajeFin + Tname;
                }
                this.entrega.escribirMensajePasivo(mensajeFin,Tname);
            }
        } else {
            // Proceso final
            int cont=0;
            String mensajeFin = "";
            while (!mensajeFin.contains("FIN") || cont < App.niveles) {
                mensajeFin = this.recogida.retirarMensajePasivo(Tname);
                if(mensajeFin.contains("FIN")){
                    cont++;
                    System.out.println("------"+cont+"--------");
                }
            }
            System.out.println("Ejecución terminada. Los mensajes fueron procesados y enviados.");
        }
    }

}
