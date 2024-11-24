package Pedidos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Main.BaseDatos;

public class ObjPedido {
	private int id;
	private Date fechaRealizada, fechaEntrega;
	
	private ArrayList<ObjPedido> listaPedidosTotal;
	private DefaultListModel<String> listaPedidos;
	
	public ObjPedido() {
		this.listaPedidosTotal=new ArrayList<ObjPedido>();
		this.listaPedidos= new DefaultListModel<String>();
	}
	
	

	public ObjPedido(int id, Date fechaRealizada, Date fechaEntrega) {
		super();
		this.id = id;
		this.fechaRealizada = fechaRealizada;
		this.fechaEntrega=fechaEntrega;
	}

	//Busca pedidos del cliente seleccionado
	public void buscarPedidosCliente(int idCliente) { 
		//Limpia
		listaPedidosTotal.clear();
		listaPedidos.clear();
		//Busca en db
		System.out.println(idCliente);
		String sql="SELECT id, fechaEntrega, fechaRealizada FROM pedido WHERE idCliente= '" + idCliente + "'" ;
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				int idPedido=result.getInt("id");
				Date fechaEntrega=result.getDate("fechaEntrega");
				Date fechaRealizada=result.getDate("fechaRealizada");
				ObjPedido objePedido=new ObjPedido(idPedido,fechaRealizada,fechaEntrega);
				listaPedidosTotal.add(objePedido);
				//System.out.println(objePedido.getId());
				listaPedidos.addElement("{ " +idPedido + " } " + fechaRealizada + " / " + fechaEntrega);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con pedidosCliente");
			e.printStackTrace();
		}
	}
	
	//Casa la id Max de pedido
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
	
	
	public void anadirMetPedido(String cliente, int idCliente, Date fechaSql, String id, ObjCompra gestionCompras) {//Crea el pedido
		LocalDate fechaHoy = LocalDate.now(); //Fecha hoy
	    Date fechaSQL = Date.valueOf(fechaHoy);
		String sql="INSERT INTO pedido (id, fechaEntrega,idCliente, fechaRealizada) VALUES ('" + Integer.parseInt(id) + "','" + fechaSql+ "','" + idCliente + "','" + fechaSQL + "')";
		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.ejecutarSQL2(sql);
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con anadirMetPedido");
			e.printStackTrace();
		}
		anadirMetCompra(gestionCompras, id);
	}
	
	public void anadirMetCompra(ObjCompra gestionCompras,String id) { //Añade las compras despues de crear el pedido
		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			for(ObjCompra a:gestionCompras.getListaComprasTotal()) {
				System.out.println(a.getId());
				int idproducto=a.getId();
				int cantidad=a.getCantidad();
				String sql="INSERT INTO compra (idpedido,idproducto,cantidad) VALUES ('" + Integer.parseInt(id) + "','" + idproducto + "','" + cantidad + "')";
				bs.ejecutarSQL2(sql);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con anadirMetCompra");
			e.printStackTrace();
		}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public ArrayList<ObjPedido> getListaPedidosTotal() {
		return listaPedidosTotal;
	}

	public void setListaPedidosTotal(ArrayList<ObjPedido> listaPedidosTotal) {
		this.listaPedidosTotal = listaPedidosTotal;
	}

	public DefaultListModel<String> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(DefaultListModel<String> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	

}
