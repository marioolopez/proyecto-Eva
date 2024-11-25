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

import Main.BaseDatos;

public class Compras extends JPanel{
	private Pedidos pedidos;
	private JLabel txt1,txt2;

	private JPanel datos, izq, dere, botones;
	private JButton boton[];
	private String[] botonesNom= {"Añadir", "modificar"};
	

	private JScrollPane barra;
	private JList listaProducto;
	private JSlider cantidad;
	
	private ObjPedido gestionPedidos;
	
	
	//ABAJO
	public Compras(Pedidos pedidos, ObjPedido gestionPedidos) {
		this.pedidos=pedidos;
		this.gestionPedidos=gestionPedidos;

		
		
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
			listaProducto=new JList(gestionPedidos.getListaProductos());
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
		botones.setLayout(new GridLayout(1,5));
		botones.add(new JLabel());
		boton=new JButton[botonesNom.length];
		for(int i=0; i<botonesNom.length; i++) {
			boton[i]=new JButton(botonesNom[i]);
			boton[i].addActionListener(new AccionCompras(this));
			botones.add(boton[i]);
		}
		botones.add(new JLabel());
	
		boton[1].setEnabled(false);

		this.add(botones, BorderLayout.SOUTH);
	}
	
	
	
	//FUNCIONALIDAD
	//Busca el producto seleccionado y mira en el array la cantidad para sl JSlider
	public void cantidadMet(String producto) { //Se activa desde AccionLista
		
		for(ObjPedido a:gestionPedidos.getListaProductosTotal()) {
			if(a.getNombre().equals(producto)) { //Encontrado
				int stock=a.getCantidad();
				if(stock==0) {
					JOptionPane.showMessageDialog(datos, "No hay stock");
				}else {
					cantidad.setEnabled(true);
					// Max/min
					cantidad.setMinimum(0);
					cantidad.setMaximum(stock);
					//Lineas menores
					cantidad.setMinorTickSpacing(1);
					cantidad.setMajorTickSpacing(stock); //Max

					cantidad.setPaintTicks(true);
					cantidad.setPaintLabels(true);
				}
			}
		}
	}
	
	 //Cuando se de al boton añadir este objeto se crea y se pasa a la lista listaComprasTotal
	public void compraRealizadaMet() {
		ObjPedido compraProducto=null;
		if(validar()) {
			String nombreProducto = (String) listaProducto.getSelectedValue(); 
	        int cantidadSeleccionada = cantidad.getValue();
	        //Busco id
	        int idProducto=0;
	        for (ObjPedido producto : gestionPedidos.getListaProductosTotal()) {
	        	System.out.println(producto);
	            if (producto.getNombre().equals(nombreProducto)) {
	            	idProducto=producto.getId(); //Saco la id del producto
	            	
	            	int cantidadMax=producto.getCantidad(); //RESTO LA CANTIDAD Q COMPRA
	            	producto.setCantidad(cantidadMax-cantidadSeleccionada);
	                break;
	            }
	        }
	        compraProducto =new ObjPedido(nombreProducto, idProducto, cantidadSeleccionada); //Compra realizada
	        gestionPedidos.getListaComprasTotal().add(compraProducto);
	        pedidos.listaComprasMet(); //Actualiza la lista
		}
	}
	
	public boolean validar() {
		if(listaProducto.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(botones, "Selecciona un producto");
		}else if(cantidad.getValue()==0){
			JOptionPane.showMessageDialog(botones, "Selecciona una cantidad");
		}else {
			return true;
		}
		return false;
	}
	
	
	//Reseteo
	public void reseteoMet() {
		listaProducto.clearSelection();
		cantidad.setEnabled(false); //Oculto el JSpinner
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

