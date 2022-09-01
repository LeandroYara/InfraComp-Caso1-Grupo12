import java.util.ArrayList;

public class Buzon {
	
	private ArrayList<String> mensajesBuzon;
	private int tamanoBuzon;
	
	public Buzon (int nuevoTamano) {
		
		this.mensajesBuzon = new ArrayList<String>();
		this.tamanoBuzon = nuevoTamano;
		
	}
	
	public synchronized void agregarElementoPasivo (String elemento) {
		
		while (this.mensajesBuzon.size() == tamanoBuzon) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		this.mensajesBuzon.add(elemento);
		
		this.notify();
		
	}
	
public void agregarElementoSemiactivo (String elemento) {
		
		while (this.mensajesBuzon.size() == tamanoBuzon) {
			Thread.yield();
		}

		synchronized(this){
		
		this.mensajesBuzon.add(elemento);
		
		this.notify();

		}
		
	}
	
	public synchronized String retirarElementoPasivo() {
		
		while (this.mensajesBuzon.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		String elementoRetirado = this.mensajesBuzon.remove(0);
		
		this.notifyAll();
		
		return elementoRetirado;
		
	}
	
public String retirarElementoSemiactivo() {

		while (this.mensajesBuzon.isEmpty()) {
			Thread.yield();
		}

		synchronized(this){
		
		String elementoRetirado = this.mensajesBuzon.remove(0);
		
		this.notifyAll();
		
		return elementoRetirado;

		}
		
	}

}
