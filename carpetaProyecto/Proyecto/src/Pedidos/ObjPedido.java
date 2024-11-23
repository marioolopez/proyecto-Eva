package Pedidos;

import java.sql.Date;

public class ObjPedido {
	private int id;
	private Date fecha;
	
	public ObjPedido(int id, Date fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

}
