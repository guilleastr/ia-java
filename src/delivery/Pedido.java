package delivery;

import java.util.Date;

import transport.Transport;

public class Pedido {
	private String idPedido;
	private double kg;
	private Transport transporte;
	private double precio;
	private Date date;
	private double timepoEntrega;
	
	public Pedido(String idPedido, double kg,double timepoEntrega, double precio) {
		super();
		this.idPedido = idPedido;
		this.kg = kg;
		this.timepoEntrega = timepoEntrega;
		this.precio = precio;
	}

	public double getTimepoEntrega() {
		return timepoEntrega;
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

	public void setTransporte(Transport transporte) {
		this.transporte = transporte;
	}
}
