package Pedidos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import Main.BaseDatos;

public class PedidosModificar extends JInternalFrame{
	private JPanel panelCliente, panelPedidos, panelArriba, panelAbajo, panelCompras, panelEditar, panelBotones, panelEditarCentro, panelEditarNorte;
	private JLabel txt1,txt2,txt3,txt4,txt5;
	private JList listaCliente, listaPedido, listaCompra;
	private JScrollPane barra1,barra2,barra3;
	private JSpinner cantidad;
	private JComboBox productos;
	private JButton guardar, eliminarPedido, eliminarCompra, entrega;
	private ObjCliente gestionClientes;
	private ObjPedido gestionPedidos;
	private ObjProducto gestionProductos;
	private JRadioButton entregado, espera;
	private ButtonGroup grupo;
	
	private ObjCliente clienteSeleccionado;
	private ObjPedido pedidoSeleccionado;
	private ObjCompra compraSeleccionada;
	public PedidosModificar() {
		this.setLayout(new GridLayout(2,1, 15 ,10));
		gestionClientes=new ObjCliente();
		gestionPedidos=new ObjPedido();
		gestionProductos=new ObjProducto();
		pedidoSeleccionado=new ObjPedido();
		//DIBUJA
		dibujoMe1();
		dibujoMe2();

	}
	
	
	public void dibujoMe1() {
	    // Panel superior
	    panelArriba = new JPanel();
	    panelArriba.setLayout(new GridLayout(1, 2));

	    // Panel Cliente
	    panelCliente = new JPanel();
	    panelCliente.setBorder(new EmptyBorder(10, 90, 10, 5));
	    panelCliente.setLayout(new BorderLayout());
	    txt1 = new JLabel("Lista clientes");
	    panelCliente.add(txt1, BorderLayout.NORTH);

	    listaCliente = new JList<>(gestionClientes.getListaClientes()); //Lista 1
	    listaCliente.addMouseListener(new AccionListaPedidosModificar(this));
	    barra1 = new JScrollPane(listaCliente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panelCliente.add(barra1, BorderLayout.CENTER);

	    panelArriba.add(panelCliente);

	    // Panel Pedidos
	    panelPedidos = new JPanel();
	    panelPedidos.setBorder(new EmptyBorder(10, 5, 10, 90));
	    panelPedidos.setLayout(new BorderLayout());
	    txt2 = new JLabel("Lista pedidos del cliente");
	    panelPedidos.add(txt2, BorderLayout.NORTH);

	    listaPedido = new JList<>();
	    listaPedido.addMouseListener(new AccionListaPedidosModificar(this)); //Lista2
	    barra2 = new JScrollPane(listaPedido, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panelPedidos.add(barra2, BorderLayout.CENTER);

	    panelArriba.add(panelPedidos);

	    // Agregar panel superior al contenedor principal
	    this.add(panelArriba);
	}

	public void dibujoMe2() {
	    // Panel inferior
	    panelAbajo = new JPanel();
	    panelAbajo.setLayout(new GridLayout(1, 2));

	    // Panel Compras
	    panelCompras = new JPanel();
	    panelCompras.setBorder(new EmptyBorder(10, 90, 40, 5));
	    panelCompras.setLayout(new BorderLayout());
	    txt3 = new JLabel("Compras");
	    panelCompras.add(txt3, BorderLayout.NORTH);

	    listaCompra = new JList<>();
	    listaCompra.addMouseListener(new AccionListaPedidosModificar(this)); //Lista 3
	    barra3 = new JScrollPane(listaCompra, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panelCompras.add(barra3, BorderLayout.CENTER);

	    panelAbajo.add(panelCompras);

	    // Panel Editar
	    panelEditar = new JPanel();
	    panelEditar.setLayout(new BorderLayout());
	    panelEditar.setBorder(new EmptyBorder(30, 5, 20, 70));
	    
	    panelEditarNorte=new JPanel();
	    panelEditarNorte.setLayout(new FlowLayout());
	    grupo=new ButtonGroup();
	    entregado=new JRadioButton("Entregado");
	    entregado.setEnabled(false);
	    grupo.add(entregado);
	    panelEditarNorte.add(entregado);
	    espera=new JRadioButton("No entregado");
	    espera.setEnabled(false); 
	    grupo.add(espera);
	    panelEditarNorte.add(espera);
	    panelEditar.add(panelEditarNorte, BorderLayout.NORTH);

	    // Panel Centro dentro de Editar
	    panelEditarCentro = new JPanel();
	    panelEditarCentro.setLayout(new FlowLayout());
	    txt4 = new JLabel("Producto");
	    panelEditarCentro.add(txt4);

	    productos = new JComboBox<>();
	    productos.setEnabled(false);
	    for(ObjCompra nombre:gestionProductos.getListaProductosTotal()) {
	    	productos.addItem(nombre.getNombre());
	    }
	    productos.setPreferredSize(new Dimension(190, 25));
	    panelEditarCentro.add(productos);

	    txt5 = new JLabel("Cantidad de productos");
	    panelEditarCentro.add(txt5);

	    cantidad = new JSpinner();
	    cantidad.setEnabled(false);
	    cantidad.setPreferredSize(new Dimension(40, 25));
	    panelEditarCentro.add(cantidad);

	    panelEditar.add(panelEditarCentro, BorderLayout.CENTER);

	    // Panel Botones dentro de Editar
	    panelBotones = new JPanel();
	    panelBotones.setLayout(new FlowLayout());

	    guardar = new JButton("Guardar");
	    guardar.addActionListener(new AccionPedidosModificar(this));
	    guardar.setEnabled(false);
	    panelBotones.add(guardar);

	    eliminarPedido = new JButton("Eliminar pedido");
	    eliminarPedido.setEnabled(false);
	    eliminarPedido.addActionListener(new AccionPedidosModificar(this));
	    panelBotones.add(eliminarPedido);
	    
	    eliminarCompra = new JButton("Eliminar compra");
	    eliminarCompra.setEnabled(false);
	    eliminarCompra.addActionListener(new AccionPedidosModificar(this));
	    panelBotones.add(eliminarCompra);

	    entrega = new JButton("Cambiar entrega");
	    entrega.setEnabled(false);
	    entrega.addActionListener(new AccionPedidosModificar(this));
	    panelBotones.add(entrega);

	    panelEditar.add(panelBotones, BorderLayout.SOUTH);

	    // Agregar Editar al panel inferior
	    panelAbajo.add(panelEditar);

	    // Agregar panel inferior al contenedor principal
	    this.add(panelAbajo);
	}
	
	public void buscarPedidos() { //Busca los pedidos del cliente seleccionado
		int index= listaCliente.getSelectedIndex();
		clienteSeleccionado=gestionClientes.getListaClientesTotal().get(index);
		clienteSeleccionado.getListaPedidosClienteTotal().clear();
		clienteSeleccionado.getListaPedidosCliente().clear();
		int idCliente=clienteSeleccionado.getId(); //Saca el id
		clienteSeleccionado.buscarPedidosCliente(idCliente);
		
		listaPedido.setModel(clienteSeleccionado.getListaPedidosCliente());
		
	}
	
	public void buscarCompras() { //Busca las compras y mira las fechas de entrega
		int index=listaPedido.getSelectedIndex();
		pedidoSeleccionado=clienteSeleccionado.getListaPedidosClienteTotal().get(index);
		//System.out.println(pedidoSeleccionado.toString());
		pedidoSeleccionado.buscarCompras();
		listaCompra.setModel(pedidoSeleccionado.getListaPedidos());
		
		//Para los radioButtons
		LocalDate fechaHoy=LocalDate.now();
		//Formateo fecha
		String fechaFormateada = pedidoSeleccionado.getFechaEntrega().toString();
		LocalDate fechaEntrega = LocalDate.parse(fechaFormateada);

		 if(fechaEntrega.isBefore(fechaHoy)){ //Saber si es anterior a fecha hoy
	        entregado.setSelected(true);
	        espera.setSelected(false);
	     }else{
			espera.setSelected(true);
			entregado.setSelected(false);
	     }
		 entrega.setEnabled(true);
		 
		productos.setEnabled(false);
		cantidad.setEnabled(false);
		guardar.setEnabled(false);
	}
	
	public void mostrarCompra() {
		int index=listaCompra.getSelectedIndex();
		compraSeleccionada=pedidoSeleccionado.getListaComprasTotal().get(index);
		productos.setSelectedItem(compraSeleccionada.getNombre());
		cantidad.setValue(compraSeleccionada.getCantidad());
		
		productos.setEnabled(true);
		cantidad.setEnabled(true);
		guardar.setEnabled(true);
	}
	
	public void modificarCompra() {
		if(validar()) {
			int index=productos.getSelectedIndex();
			ObjCompra productoSeleccionado=gestionProductos.getListaProductosTotal().get(index);
			compraSeleccionada.actualizaCompra(productoSeleccionado.getIdProducto(), Integer.parseInt(cantidad.getValue().toString()) ,pedidoSeleccionado.getIdPedido());
		}
		buscarCompras();
	}
	
	public boolean validar() {
		if(!productos.isEnabled() && listaCompra.getSelectedValue()!=null) {
			JOptionPane.showMessageDialog(this, "Selecciona una compra para modificar");
		}else if(Integer.parseInt(cantidad.getValue().toString())<0) {
			JOptionPane.showMessageDialog(this, "Pon una cantidad en positivo");
		}else {
			return true;
		}
		return false;
	}
	
	public void cambiarEntrega() {
		PanelCalendario panelCalendario=new PanelCalendario(this);
		panelCalendario.setVisible(true);
	}
	
	public void eliminarPedido() {
		
	}
	
	public void eliminarCompra() {
		
	}



	public JPanel getPanelCompra() {
		return panelPedidos;
	}


	public void setPanelCompra(JPanel panelCompra) {
		this.panelPedidos = panelCompra;
	}


	public JList getListaCliente() {
		return listaCliente;
	}


	public void setListaCliente(JList listaCliente) {
		this.listaCliente = listaCliente;
	}


	public JList getListaCompra() {
		return listaCompra;
	}


	public void setListaCompra(JList listaCompra) {
		this.listaCompra = listaCompra;
	}


	public JPanel getPanelArriba() {
		return panelArriba;
	}


	public void setPanelArriba(JPanel panelArriba) {
		this.panelArriba = panelArriba;
	}


	public JList getListaPedido() {
		return listaPedido;
	}


	public void setListaPedido(JList listaPedido) {
		this.listaPedido = listaPedido;
	}


	public JComboBox getProductos() {
		return productos;
	}


	public void setProductos(JComboBox productos) {
		this.productos = productos;
	}


	public JSpinner getCantidad() {
		return cantidad;
	}


	public void setCantidad(JSpinner cantidad) {
		this.cantidad = cantidad;
	}


	public JButton getGuardar() {
		return guardar;
	}


	public void setGuardar(JButton guardar) {
		this.guardar = guardar;
	}


	public ObjPedido getPedidoSeleccionado() {
		return pedidoSeleccionado;
	}


	public void setPedidoSeleccionado(ObjPedido pedidoSeleccionado) {
		this.pedidoSeleccionado = pedidoSeleccionado;
	}
	
	
	
}
