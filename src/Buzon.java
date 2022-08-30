import java.util.ArrayList;

public class Buzon {
    private ArrayList<String> buff;
    private int tamano;
    private int nivel;
    private int id;

    public Buzon(int n, int nivel, int id) {
        this.tamano = n;
        buff = new ArrayList<String>();
        this.nivel = nivel;
        this.id = id;
    }

    public Buzon(int n, int nivel) { // Buzones extremos que no necesitan id
        this.tamano = n;
        buff = new ArrayList<String>();
        this.nivel = nivel;
    }

    public void escribirMensajeActivo(String mensaje) {
        while (buff.size() == tamano) {
            Thread.yield();
        }
        synchronized(this){
        System.out.println(mensaje);
        buff.add(mensaje);
        System.out.println("tamaño buffer escritura : " + buff.size() + " --------");
        notify();}
    }

    public synchronized String retirarMensajeActivo(int id) {
        while (buff.isEmpty() ) {
            Thread.yield();
        }
        String mensaje = null;
            mensaje = buff.get(0);
            buff.remove(0);
            notify(); 
        return mensaje;
    }

    public synchronized void escribirMensajePasivo(String mensaje) {
        while (buff.size() == tamano) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(mensaje);
        buff.add(mensaje);
        notify();
    }

    public synchronized String retirarMensajePasivo() {
        while (buff.isEmpty() ) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String mensaje =  buff.remove(0);
            System.out.println("Se retira mensaje " + mensaje);
            System.out.println("tamaño buffer recepción : " + buff.size() + " --------");
           
            notify();
        return mensaje;

    }

    public int darNivel() {
        return nivel;
    }

    public int darId() {
        return id;
    }

}
