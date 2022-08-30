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
        synchronized (this) {
            System.out.println(mensaje);
            buff.add(mensaje);
            System.out.println("tamaño buffer escritura : " + buff.size() + " --------");
            notify();
        }
    }

    public String retirarMensajeActivo() {
        while (buff.isEmpty()) {
            Thread.yield();
        }
        synchronized (this) {
            System.out.println("tamaño buffer recepción antes de remover: " + buff.size() + " --------");
            String mensaje = buff.remove(0);
            notify();
            System.out.println("Se retira mensaje " + mensaje);
          
            return mensaje;
        }

    }

    public synchronized void escribirMensajePasivo(String mensaje) {
        System.out.println("tamaño al escribir"+buff.size());
        int tamañooo=buff.size();
        while (buff.size() == tamano) {
            try {
                System.out.println("Productor en espera");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        buff.add(mensaje);
        System.out.println(mensaje);
        notifyAll();
        System.out.println("ProductorNotifica");
    }

    public synchronized String retirarMensajePasivo() {
        while (buff.isEmpty()) {
            try {
                System.out.println("Consumidor en espera");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String mensaje = buff.remove(0);
        System.out.println("Se retira mensaje " + mensaje);
        notify();
        System.out.println("Consumidor notifica");
        System.out.println("tamaño buffer al notificar: "+buff.size());
        return mensaje;
    }

    public int darNivel() {
        return nivel;
    }

    public int darId() {
        return id;
    }

}
