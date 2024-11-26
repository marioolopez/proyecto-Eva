package Pedidos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Main.BaseDatos;

public class ObjPedido {
	private int idPedido, idCliente;
	private Date fechaEntrega,fechaRealizada;
	private ArrayList<ObjCompra> listaComprasTotal; //Almacena los pedidos del cliete
	private DefaultListModel<String> listaCompras; //Almacena los nompres del pedido
	

	public ObjPedido(int idPedido, Date fechaEntrega, Date fechaRealizada,ArrayList<ObjCompra> listaComprasTotal) {
		super();
		this.idPedido = idPedido;
		this.fechaEntrega = fechaEntrega;
		this.fechaRealizada = fechaRealizada;
		this.listaComprasTotal=listaComprasTotal;
	}
	
	public ObjPedido(int idPedido, Date fechaEntrega, Date fechaRealizada) {
		super();
		this.idPedido = idPedido;
		this.fechaEntrega = fechaEntrega;
		this.fechaRealizada = fechaRealizada;
		this.listaComprasTotal=new ArrayList<ObjCompra>();
		this.listaCompras = new DefaultListModel<String>();
	}
	
	
	public ObjPedido() {
		super();
		this.listaComprasTotal = new ArrayList<ObjCompra>();
		this.listaCompras = new DefaultListModel<String>();
		this.idPedido = -1;
		this.idCliente = -1;
		this.fechaEntrega = null;
		this.fechaRealizada = null;
	}
	

	
	//Busca la id Max de pedido
	public int idMax() {
		int idMax=0;
		String sql="SELECT MAX(id) FROM pedido";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			
			if(result.next()) {
				idMax=result.getInt("MAX(id)");
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
			e.printStackTrace();
		}
		idMax++;
		return idMax;
	}

	//Inserta en BD el pedido
	public void crearPedido(ObjPedido pedido) {
		String sql="INSERT INTO pedido (id, fechaEntrega,idCliente, fechaRealizada) VALUES ('" + pedido.getIdPedido() + "','" + pedido.getFechaEntrega()+ "','" + pedido.getIdCliente() + "','" + pedido.getFechaRealizada() + "')";

		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.ejecutarSQL2(sql);
			for(ObjCompra compra:pedido.getListaComprasTotal()) {
				String sql2="INSERT INTO compra(idpedido, idproducto, cantidad) VALUES ('" +pedido.getIdPedido() + "','" + compra.getIdProducto() + "','" + compra.getCantidad() +"')";
				bs.ejecutarSQL2(sql2);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al crear el pedido en BD");
			e.printStackTrace();
		}
	}
	
	//Busca las compras del pedido seleccionado
	public void buscarCompras() {
		listaComprasTotal.clear();
		listaCompras.clear();
		String sql="SELECT producto.nombre, compra.idproducto, compra.cantidad FROM producto JOIN compra ON producto.id= compra.idproducto WHERE compra.idpedido='" + idPedido +"'";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				String nombre=result.getString("nombre");
				int idProducto=result.getInt("idProducto");
				int cantidad=result.getInt("cantidad");
				
				ObjCompra compra=new ObjCompra(nombre,idProducto,cantidad);
				listaComprasTotal.add(compra);
				listaCompras.addElement(nombre);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al buscar las compras dentro del pedido");
			e.printStackTrace();
		}
	}

	public void actualizaFechaEntrega() {
		String sql = "UPDATE pedido SET fechaEntrega='" + fechaEntrega + "' WHERE id='" + idPedido + "'";

		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.ejecutarSQL2(sql);
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al actualiza la fecha de entrega del pedido en BD");
			e.printStackTrace();
		}
	}
	
	public void eliminarPedido() {
		
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public DefaultListModel<String> getListaPedidos() {
		return listaCompras;
	}
	public void setListaPedidos(DefaultListModel<String> listaPedidos) {
		this.listaCompras = listaPedidos;
	}

	public ArrayList<ObjCompra> getListaComprasTotal() {
		return listaComprasTotal;
	}

	public void setListaComprasTotal(ArrayList<ObjCompra> listaComprasTotal) {
		this.listaComprasTotal = listaComprasTotal;
	}

	@Override
	public String toString() {
		return "ObjPedido [idPedido=" + idPedido + ", idCliente=" + idCliente + ", fechaEntrega=" + fechaEntrega
				+ ", fechaRealizada=" + fechaRealizada + ", listaComprasTotal=" + listaComprasTotal + ", listaCompras="
				+ listaCompras + "]";
	}

	
	

		
	
	
	
}
