import java.util.ArrayList;

public class Buzon{
	
	private ArrayList<String> mensajesBuzon;
	private int tamanoBuzon;
	
	public Buzon (int nuevoTamano) {
		
		this.mensajesBuzon = new ArrayList<String>();
		this.tamanoBuzon = nuevoTamano;
		
	}
	
	public synchronized void agregarElementoPasivo (String elemento) {
		
		if (this.mensajesBuzon.size() == tamanoBuzon) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.mensajesBuzon.add(elemento);
	}
	
public synchronized void agregarElementoSemiactivo (String elemento) {
		
		if (this.mensajesBuzon.size() == tamanoBuzon) {
			Thread.yield();
		}
		this.mensajesBuzon.add(elemento);
	}
	
	
	public synchronized String retirarElementoPasivo() {
		
		if (this.mensajesBuzon.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String elementoRetirado = this.mensajesBuzon.remove(0);
		return elementoRetirado;
		
	}
	
public synchronized String retirarElementoSemiactivo() {
		
		if (this.mensajesBuzon.isEmpty()) {
			Thread.yield();
		}
		String elementoRetirado = this.mensajesBuzon.remove(0);
		
		return elementoRetirado;
		
	}

}
