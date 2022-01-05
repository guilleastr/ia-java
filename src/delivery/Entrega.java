package delivery;

import java.util.Date;

public class Entrega {
	private String idEntrega;
	private String direccion;
	private Pedido idPedido;
	private Date fechaEntrega;
	
	public Entrega(String idEntrega, String direccion,  Pedido idPedido) {
		super();
		this.idEntrega = idEntrega;
		this.direccion = direccion;
		this.idPedido = idPedido;
	}

	public String getIdEntrega() {
		return idEntrega;
	}

	public String getDireccion() {
		return direccion;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
}
