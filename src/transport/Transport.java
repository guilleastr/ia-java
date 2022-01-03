package transport;

public class Transport {
	private String nameTransport;
	private double kg;
	private double velocidad;
	
	public Transport(String nameTransport, double kg, double velocidad) {
		super();
		this.nameTransport = nameTransport;
		this.kg = kg;
		this.velocidad = velocidad;
	}
	
	public String getNameTransport() {
		return nameTransport;
	}

	public double getKg() {
		return kg;
	}

	public double getVelocidad() {
		return velocidad;
	}

	
}
