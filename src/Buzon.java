import java.util.ArrayList;

public class Buzon {
    
    private static boolean isInicio = true;
    private int tamano;
    private ArrayList<String> buffer;
    private String Bnombre;

    public Buzon(int pNivel, int id, int tamano) { // Buzón intermedio
        this.tamano = tamano;
        this.buffer = new ArrayList<String>();
        this.Bnombre = "B" + pNivel + "" + id;
    }

    public Buzon(int tamano) { // Buzón extremo
        this.Bnombre = isInicio ? "B-inicial" : "B-final";
        isInicio = false;
        this.tamano = tamano;
        this.buffer = new ArrayList<String>();
    }

    public String darNombre() {
        return Bnombre;
    }

    public synchronized String liberar() throws InterruptedException {
        while (this.buffer.size() == 0) { // Si el buzon esta vacio, espera a agregar algun mensaje
            wait();
        }
        notifyAll();
        return this.buffer.remove(0);
    }
    
    public synchronized String liberarFinal() throws InterruptedException {
        // TODO: Implementar espera semiactiva
        while (this.buffer.size() == 0) { // Si el buzon esta vacio, espera a agregar algun mensaje
            wait();
        }
        notifyAll();
        return this.buffer.remove(0);
    }

    public synchronized void agregar(String mensaje) throws InterruptedException {
        while (this.buffer.size() == this.tamano) { // Si el buzon esta lleno, espera a liberar algun mensaje
            wait();
        }
        notifyAll();
        this.buffer.add(mensaje);
    }

    public synchronized void agregarInicial(String mensaje) throws InterruptedException {
        // TODO: Implementar espera semiactiva
        while (this.buffer.size() == this.tamano) { // Si el buzon esta lleno, espera a liberar algun mensaje
            wait();
        }
        notifyAll();
        this.buffer.add(mensaje);
    }
    
}
