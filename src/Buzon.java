import java.util.ArrayList;

public class Buzon {

    private ArrayList<String> buff;
    private int tamano;
    private String Bname;

    // Buzones extremos
    public Buzon(int n, int pNivel) {
        this.tamano = n;
        this.buff = new ArrayList<String>();
        this.Bname = pNivel == 0 ? "B-inicial" : "B-final";
    }

    // Buzones intermedios
    public Buzon(int n, int pId, int pNivel) {
        this.tamano = n;
        this.buff = new ArrayList<String>();
        this.Bname = "B-" + pNivel + pId;
    }

    public void escribirMensajeActivo(String mensaje, String Tname) {
        while (estaLleno()) {
            Thread.yield();
        }
        synchronized (this) {
            buff.add(mensaje);
            System.out.println("Proceso " + Tname + " escribió mensaje '" + mensaje + "' al buzón " + Bname + " (tamaño actual: " + buff.size() + ")");
            notify();
        }
    }

    public String retirarMensajeActivo(int nivel, int id, String Tname) {
        while (estaVacio()) {
            Thread.yield();            
        }
        synchronized (this) {
            String mensaje = this.buff.remove(0);
            System.out.println("Proceso " + Tname + "retiró mensaje '" + mensaje + "' del buzón " + Bname + " (tamaño actual: " + buff.size() + ")");
            notifyAll();
            return mensaje;
        }

    }

    public synchronized void escribirMensajePasivo(String mensaje, String Tname) {
        while (buff.size() == tamano) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(mensaje);
        System.out.println("Proceso " + Tname + " escribió mensaje '" + mensaje + "' al buzón " + Bname + " (tamaño actual: " + buff.size() + ")");
        notifyAll();
    }

    public synchronized String retirarMensajePasivo(String Tname) {
        while (buff.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String mensaje = buff.remove(0);
        System.out.println("Proceso " + Tname + " retiró mensaje '" + mensaje + "' del buzón " + Bname + " (tamaño actual: " + buff.size() + ")");
        notifyAll();
        return mensaje;
    }

    public synchronized boolean estaVacio(){
        return buff.size()==0;
    }
    public synchronized boolean estaLleno(){
        return buff.size()==tamano;
    }

}
