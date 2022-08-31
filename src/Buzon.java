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
        while (estaLleno()) {
            Thread.yield();
        }
        synchronized (this) {
            buff.add(mensaje);
            System.out.println("Buzon Inicial Escribió: "+mensaje);
            notify();
        }
    }

    public String retirarMensajeActivo() {
        while (estaVacio()) {
            Thread.yield();            
        }
        synchronized (this) {
            String mensaje ="";
            try{
            mensaje= this.buff.remove(0);
            System.out.println("Se retiró el mensaje: "+ mensaje);
            System.out.println("-----El tamaño del buffer al momento de retirar fue: "+ buff.size());
            notify();}
            catch(Exception e){
                e.printStackTrace();
            }
            return mensaje;
        }

    }

    public synchronized void escribirMensajePasivo(String mensaje, int nivel,int id) {
        while (buff.size() == tamano) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(mensaje);
        System.out.println("En el nivel " +nivel+ " el thread "+id+ " escribió: "+mensaje);
        System.out.println("El tamaño del buffer "+"N"+nivel+"ID"+id+ " al escribir es: "+buff.size());
        notifyAll();
    }

    public synchronized String retirarMensajePasivo(int nivel,int id) {
        while (buff.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String mensaje = buff.remove(0);
        System.out.println("N"+nivel+"ID"+id+": retiró "+mensaje);
        System.out.println("El tamaño del buffer "+"N"+nivel+"ID"+id+ " al retirar es: "+buff.size());
        notify();
        return mensaje;
    }

    public int darNivel() {
        return nivel;
    }

    public int darId() {
        return id;
    }
    public synchronized boolean estaVacio(){
        return buff.size()==0;
    }
    public synchronized boolean estaLleno(){
        return buff.size()==tamano;
    }

}
