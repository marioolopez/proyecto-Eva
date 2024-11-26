package Pedidos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

import main.BaseDatos;

public class Compras extends JPanel{
	private Pedidos pedidos;
	private JLabel txt1,txt2;
	private JPanel datos, izq, dere, botones;
	private JButton anadir;
	private JScrollPane barra;
	private JList listaProducto;
	private JSlider cantidad;
	
	private ObjPedido gestionPedidos;
	private ObjProducto gestionProductos;

	
	
	//ABAJO
	public Compras(Pedidos pedidos, ObjPedido gestionPedidos, ObjProducto gestionProductos) {
		this.pedidos=pedidos;
		this.gestionPedidos=gestionPedidos;
		this.gestionProductos=gestionProductos;


		//Cargar interfaz
		datosMe();
		botonesMe();

	}
	public void datosMe() {
		this.setLayout(new BorderLayout());
		datos=new JPanel();
		datos.setLayout(new GridLayout(1,2));

		
			izq=new JPanel();
			izq.setLayout(new BorderLayout());
			txt1=new JLabel("Seleccionar productos");
			izq.add(txt1, BorderLayout.NORTH);
			listaProducto=new JList<String>(gestionProductos.getListaProductos()); //Lista
			listaProducto.addMouseListener(new AccionListaCompras(this));
			barra=new JScrollPane(listaProducto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			izq.add(barra, BorderLayout.CENTER);
		datos.add(izq);
		
			dere=new JPanel();
			dere.setLayout(new BorderLayout());
			txt2=new JLabel("Cantidad");
			dere.add(txt2, BorderLayout.NORTH);
			cantidad=new JSlider();
			cantidad.setEnabled(false);
			dere.add(cantidad,BorderLayout.CENTER);                
		datos.add(dere);
		this.add(datos, BorderLayout.CENTER);
	}
	
	public void botonesMe() {
		botones=new JPanel();
		botones.setLayout(new FlowLayout());
		anadir=new JButton("Añadir");
		anadir.setEnabled(false);
		anadir.addActionListener(new AccionCompras(this));
		botones.add(anadir);

		this.add(botones, BorderLayout.SOUTH);
	}
	
	public void infoSeleccionado() { //Detecta el obje seleccionado en la lista y saca la cantidad para ponerla en el JSpinner
		int index = listaProducto.getSelectedIndex();
		ObjCompra compra=gestionProductos.getListaProductosTotal().get(index);
		System.out.println(compra.toString());
		//System.out.println(compra.toString());
		
		int stock=compra.getCantidad();
		if(stock==0) {
			JOptionPane.showMessageDialog(datos, "No hay stock");
		}else {
			cantidad.setEnabled(true);
			// Max/min
			cantidad.setMinimum(0);
			cantidad.setMaximum(stock);
			//Lineas menores
			cantidad.setMinorTickSpacing(1);
			cantidad.setMajorTickSpacing(5); 

			cantidad.setPaintTicks(true);
			cantidad.setPaintLabels(true);
			
			cantidad.setValue(stock/2);
			
			anadir.setEnabled(true);//Activamos el boton añadir
		}
		
	}
	
	public void guardarCompra() {
		int index = listaProducto.getSelectedIndex();
		ObjCompra compra=gestionProductos.getListaProductosTotal().get(index);
		int cantidadProducto=cantidad.getValue();
		ObjCompra compraTerminada=new ObjCompra(compra.getNombre(),compra.getIdProducto(),cantidadProducto);
		
		gestionPedidos.getListaComprasTotal().add(compraTerminada);
		gestionPedidos.getListaPedidos().addElement(compraTerminada.getNombre());
		System.out.println(compraTerminada.toString());
		
		//Resta para actualizar el stock
		compra.setCantidad(compra.getCantidad()-cantidadProducto);
		
		//Activa los botones de la clase Pedidos
		pedidos.activarBotones();
		
		listaProducto.clearSelection();
		cantidad.setEnabled(false);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JList getProductos() {
		return listaProducto;
	}
	public void setProductos(JList productos) {
		this.listaProducto = productos;
	}
	public JList getListaProducto() {
		return listaProducto;
	}
	public void setListaProducto(JList listaProducto) {
		this.listaProducto = listaProducto;
	}

	
}

