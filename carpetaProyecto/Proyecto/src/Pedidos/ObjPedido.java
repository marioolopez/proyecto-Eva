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
	private String nombre;
	private int idPedido,cantidad, idProducto;
	private Date fechaEntrega,fechaRealizada;
	
	private ArrayList<ObjPedido> listaProductosTotal; //Almacena el nombre, su id y stock de toda bd
	private DefaultListModel<String> listaProductos;//Solo el nombre del producto de listaProductosTotal
	private ArrayList<ObjPedido> listaComprasTotal; //Almacena los objetos con el id, nombre y cantidad seleccionada por el usuario
	private DefaultListModel<String> listaCompras; //Muestra el nombre por la lista de listaComprasTotal
	
	private ArrayList<ObjPedido> listaPedidosTotal;
	private DefaultListModel<String> listaPedidos;
	
	public ObjPedido() { //GestionCompras
		super();
		this.listaProductosTotal= new ArrayList<ObjPedido>();
		this.listaProductos= new DefaultListModel<String>();
		this.listaCompras = new DefaultListModel<String>();
		this.listaComprasTotal = new ArrayList<ObjPedido>();
		listaProductosMet(); //Busca todos los productos y los añade a la lista listaProductosTotal
		
		this.listaPedidosTotal=new ArrayList<ObjPedido>();
		this.listaPedidos= new DefaultListModel<String>();
	}

	public ObjPedido(String nombre, int id, int cantidad) { //Para crear la compra q realiza el cliente
		super();
		this.nombre = nombre;
		this.idPedido = id;
		this.cantidad = cantidad;
	}
	
	public ObjPedido(String nombre, int cantidad, Date fechaEntrega, int idPedido, int idProducto) { 
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.fechaEntrega=fechaEntrega;
		this.idPedido=idPedido;
		this.idProducto=idProducto;
	}
	
	public ObjPedido(int id, Date fechaRealizada, Date fechaEntrega) {
		super();
		this.idPedido = id;
		this.fechaRealizada = fechaRealizada;
		this.fechaEntrega=fechaEntrega;
	}
	
	
	public void listaProductosMet() { //Busca todos los productos y los añade a la lista listaProductosTotal
		String sql="SELECT nombre,id,stock FROM producto WHERE stock>0";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			
			while(result.next()) {
				ObjPedido productos=new ObjPedido(result.getString("nombre"), result.getInt("id"), result.getInt("stock"));
				listaProductosTotal.add(productos);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error en listaProductosMet");
			e.printStackTrace();
		}
		//Añade los nombres a listaProductos
		for(ObjPedido nombres:listaProductosTotal ) {
			listaProductos.addElement(nombres.getNombre());
		}
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
		
		
		
		
		public void anadirMetPedido(String cliente, int idCliente, Date fechaSql, String id, ObjPedido gestionCompras) {//Crea el pedido
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
		
		public void anadirMetCompra(ObjPedido gestionCompras,String id) { //Añade las compras despues de crear el pedido
			BaseDatos bs=null;
			try {
				bs=new BaseDatos();
				for(ObjPedido a:gestionCompras.getListaComprasTotal()) {
					System.out.println(a.getIdPedido());
					int idproducto=a.getIdPedido();
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
		
		public void buscarCompras(int id) {
			listaComprasTotal.clear();
			listaCompras.clear();
			String sql="SELECT compra.cantidad, producto.nombre, pedido.fechaEntrega, pedido.id AS idPedido, producto.id AS idProducto FROM compra JOIN producto ON compra.idProducto = producto.id "
					+ "JOIN pedido ON producto.id = compra.idpedido WHERE compra.idpedido = '" + id + "'";
			BaseDatos bs=null;
			ResultSet result=null;
			try {
				bs=new BaseDatos();
				result=bs.ejecutarSQL1(sql);
				while(result.next()) {
					int cantidad=result.getInt("cantidad");
					String nombre=result.getString("nombre");
					Date fechaEntrega=result.getDate("fechaEntrega");
					int idProducto=result.getInt("idProducto");
					int idPedido=result.getInt("idPedido");
					System.out.println("El id del producto es: " + idProducto);
					ObjPedido objCompra=new ObjPedido(nombre, cantidad, fechaEntrega, idProducto, idPedido);
					listaComprasTotal.add(objCompra);
					listaCompras.addElement(nombre);

				}
				bs.cerrarConex();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Error con buscarCompras");
				e.printStackTrace();
			}
		}
		
		public void actualizarCompras(int idProducto, int idPedido, int cantidad) {
			System.out.println("idProducto:" + idProducto + "cantidad: " + cantidad + " idPedido: " +idPedido);
			String sql="UPDATE compra SET idproducto='" + idProducto + "', cantidad = '" + cantidad + "' WHERE idPedido='" + idPedido +"'";
			BaseDatos bs=null;
			ResultSet result=null;
			try {
				bs=new BaseDatos();
				bs.ejecutarSQL2(sql);

				bs.cerrarConex();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Error con actualizarCompras");
				e.printStackTrace();
			}
			
		}
		
		public LocalDate conversionDate(Date fechaGuardada) {
			 LocalDate fechaGuardadaLocal = fechaGuardada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			 return fechaGuardadaLocal;
		}

		public ArrayList<ObjPedido> getListaProductosTotal() {
			return listaProductosTotal;
		}

		public void setListaProductosTotal(ArrayList<ObjPedido> listaProductosTotal) {
			this.listaProductosTotal = listaProductosTotal;
		}

		public DefaultListModel<String> getListaProductos() {
			return listaProductos;
		}

		public void setListaProductos(DefaultListModel<String> listaProductos) {
			this.listaProductos = listaProductos;
		}

		public ArrayList<ObjPedido> getListaComprasTotal() {
			return listaComprasTotal;
		}

		public void setListaComprasTotal(ArrayList<ObjPedido> listaComprasTotal) {
			this.listaComprasTotal = listaComprasTotal;
		}

		public DefaultListModel<String> getListaCompras() {
			return listaCompras;
		}

		public void setListaCompras(DefaultListModel<String> listaCompras) {
			this.listaCompras = listaCompras;
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

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
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

		@Override
		public String toString() {
			return "ObjPedido [nombre=" + nombre + ", id=" + idPedido + ", cantidad=" + cantidad + ", fechaEntrega="
					+ fechaEntrega + ", fechaRealizada=" + fechaRealizada + "]";
		}

		public int getIdPedido() {
			return idPedido;
		}

		public void setIdPedido(int idPedido) {
			this.idPedido = idPedido;
		}

		public int getIdProducto() {
			return idProducto;
		}

		public void setIdProducto(int idProducto) {
			this.idProducto = idProducto;
		}


		
	
	
}
