package delivery;

import java.util.Date;

import transport.Transport;

public class Pedido {
	private String idPedido;
	private double kg;
	private Transport transporte;
	private double precio;
	private Date date;
	
	public Pedido(String idPedido, double kg, Transport transporte, double precio) {
		super();
		this.idPedido = idPedido;
		this.kg = kg;
		this.transporte = transporte;
		this.precio = precio;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public double getKg() {
		return kg;
	}

	public Transport getTransporte() {
		return transporte;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getDate() {
		return date;
	}	
	
	
}
