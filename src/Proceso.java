import java.util.ArrayList;

public class Proceso extends Thread {
    
    private static ArrayList<String> finalMsgs = new ArrayList<String>();
    private Buzon buzonLiberar = null;
    private Buzon buzonAgregar = null;
    private int cantidadMensajes = 0;
    private String Tnombre;

    public Proceso(int pNivel, int pId, Buzon pBuzonLiberar, Buzon pBuzonAgregar) { // Proceso intermedio
        this.buzonLiberar = pBuzonLiberar;
        this.buzonAgregar = pBuzonAgregar;
        this.Tnombre = "T" + pNivel + "" + pId;
    }

    public Proceso(Buzon pBuzonAgregar, int pCantidadMensajes) { // Proceso inicial
        this.buzonAgregar = pBuzonAgregar;
        this.cantidadMensajes = pCantidadMensajes;
        this.Tnombre = "T-inicial";
    }

    public Proceso(Buzon pBuzonLiberar) { // Proceso final
        this.buzonLiberar = pBuzonLiberar;
        this.Tnombre = "T-final";
    }

    private String transformar(String mensage) throws InterruptedException {
        mensage += Tnombre;
        Thread.sleep(50 + App.r.nextInt(450));
        return mensage;
    }

    private void mostrarFinales() {
        // mostrar los mensajes finales
        System.out.println("Mensajes finales:");
        for (String msg : finalMsgs) {
            System.out.println("- " + msg);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Proceso " + Tnombre + " inicializando...");
            if (cantidadMensajes != 0) { // Proceso inicial
                String msg;
                for (int i = 1; i <= cantidadMensajes; i++) {
                    msg = "M" + i;
                    synchronized (buzonAgregar) {
                        buzonAgregar.agregarInicial(msg);
                        System.out.println("Proceso " + Tnombre + " agregó mensaje inicial '" + msg + "' al buzón " + buzonAgregar.darNombre());
                    }
                }
                for (int i = 1; i <= App.niveles; i++) {
                    msg = "FIN";
                    synchronized (buzonAgregar) {
                        buzonAgregar.agregarInicial(msg);
                        System.out.println("Proceso " + Tnombre + " agregó mensaje inicial '" + msg + "' al buzón " + buzonAgregar.darNombre());
                        if (i == App.niveles) System.out.println("Proceso " + Tnombre + " finalizando...");
                    }
                }
            } else if (buzonAgregar != null) { // Proceso intermedio
                String msg;
                do {
                    synchronized (buzonLiberar) {
                        msg = buzonLiberar.liberar();
                        System.out.println("Proceso " + Tnombre + " liberó mensaje '" + msg + "' del buzón " + buzonLiberar.darNombre());
                    }
                    if (msg.equals("FIN")) break;
                    msg = transformar(msg);
                    synchronized (buzonAgregar) {
                        buzonAgregar.agregar(msg);
                        System.out.println("Proceso " + Tnombre + " agregó mensaje '" + msg + "' al buzón " + buzonAgregar.darNombre());
                    }
                } while (true);
                synchronized (buzonAgregar) {
                    buzonAgregar.agregar(msg);
                    System.out.println("Proceso " + Tnombre + " agregó mensaje '" + msg + "' al buzón " + buzonAgregar.darNombre());
                    System.out.println("Proceso " + Tnombre + " finalizando...");
                }
            } else { // Proceso final
                String msg;
                int countFin = 0;
                do {
                    synchronized (buzonLiberar) {
                        msg = buzonLiberar.liberarFinal();
                        finalMsgs.add(msg);
                        System.out.println("Proceso " + Tnombre + " liberó mensaje final '" + msg + "' del buzón " + buzonLiberar.darNombre());
                    }
                    if (msg.equals("FIN")) {
                        countFin++;
                        if (countFin == App.niveles) break;
                    }
                } while (true);
                System.out.println("Proceso " + Tnombre + " liberó mensaje final '" + msg + "' del buzón " + buzonLiberar.darNombre());
                System.out.println("Proceso " + Tnombre + " finalizando...");
                mostrarFinales();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
