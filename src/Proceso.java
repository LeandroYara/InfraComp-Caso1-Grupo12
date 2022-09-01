import java.util.concurrent.ThreadLocalRandom;

public class Proceso extends Thread {
	
	private int fila;
	private int nivel;
	private Buzon buzonEntrada;
	private Buzon buzonSalida;
	private String mensajeActual;
	private int numeroSubconjuntos;
	
	public Proceso (int nuevaFila, int nuevoNivel, Buzon nuevaEntrada, Buzon nuevaSalida) {
		
		this.fila = nuevaFila;
		this.nivel = nuevoNivel;
		this.buzonEntrada = nuevaEntrada;
		this.buzonSalida = nuevaSalida;
		this.mensajeActual = "";
	}
	
public Proceso (int nuevaFila, int nuevoNivel, Buzon nuevaEntrada, Buzon nuevaSalida, int nuevoSubconjunto) {
		
		this.fila = nuevaFila;
		this.nivel = nuevoNivel;
		this.buzonEntrada = nuevaEntrada;
		this.buzonSalida = nuevaSalida;
		this.mensajeActual = "";
		this.numeroSubconjuntos = nuevoSubconjunto;
	}
	
	public void run() {
		
		if (this.nivel == 0) {
			int contadorSubconjuntos = 1;
			while (contadorSubconjuntos <= this.numeroSubconjuntos) {
				String mensajeNuevo = "M" + Integer.toString(contadorSubconjuntos);
				this.buzonSalida.agregarElementoSemiactivo(mensajeNuevo);
				System.out.println("El proceso inicial envio el mensaje " + mensajeNuevo + " al buzon inicial");
				contadorSubconjuntos += 1;
			}
			
			for (int i = 0; i < 3; i++) {
				this.buzonSalida.agregarElementoSemiactivo("FIN");
				System.out.println("El proceso inicial envio un mensaje de fin al buzon inicial");
			}
			
		}
		
		if (this.nivel >= 1 && this.nivel <= 3) {
			while (!this.mensajeActual.equals("FIN")) {
				this.mensajeActual = this.buzonEntrada.retirarElementoPasivo();
				System.out.println("El proceso de nivel " + this.nivel + " y fila " + this.fila + " retiro el mensaje: " + this.mensajeActual);
				if (!this.mensajeActual.equals("FIN")) {
					this.mensajeActual = this.mensajeActual + "T" + Integer.toString(nivel) + Integer.toString(fila);
					int minimo = 50;
					int maximo = 500;
					ThreadLocalRandom variableAleatoria = ThreadLocalRandom.current();
					int numeroAleatorio = variableAleatoria.nextInt(minimo, maximo + 1);
					try {
						Thread.sleep(numeroAleatorio);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.buzonSalida.agregarElementoPasivo(this.mensajeActual);
				System.out.println("El proceso de nivel " + this.nivel + " y fila " + this.fila + " envio el mensaje: " + this.mensajeActual);
			}
			
		}
		
		if (this.nivel == 4) {
			int contadorFinales = 0;
			while (contadorFinales < 3) {
				String mensajeTemporal = this.buzonEntrada.retirarElementoSemiactivo();
				System.out.println("El proceso final retiro el mensaje " + mensajeTemporal);
				if (mensajeTemporal.equals("FIN")) {
					contadorFinales++;
					System.out.println("Numero de mensajes finales recibidos: " + Integer.toString(contadorFinales));
				}
				
				this.mensajeActual = this.mensajeActual + " - " + mensajeTemporal;
			}
			
			System.out.println("El resultado del procesamiento es: " + this.mensajeActual);
		}
		
	}

}
