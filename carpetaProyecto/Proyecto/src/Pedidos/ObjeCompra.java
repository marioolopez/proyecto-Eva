package Pedidos;

public class ObjeCompra {
	private String nombre;
	private int id,cantidad;

	
	public ObjeCompra(String nombre, int id, int cantidad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.cantidad = cantidad;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
