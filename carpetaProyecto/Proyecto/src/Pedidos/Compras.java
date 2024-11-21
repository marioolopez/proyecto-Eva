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
	private String[] botonesNom= {"Añadir", "modificar", "eliminar"};
	
	private ArrayList<ObjeCompra> listaProductosTotal; //Almacena el nombre, su id y stock
	private DefaultListModel<String> listaProductos;//Solo el nombre del producto q se muesta en la losta
	private JScrollPane barra;
	private JList productos;
	private JSlider cantidad;
	

	
	
	//ABAJO
	public Compras(Pedidos pedidos) {
		this.pedidos=pedidos;
		//cargar lista productos
		listaProductosTotal=new ArrayList<ObjeCompra>();
		listaProductosMet();
		
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
			productos=new JList(listaProductos);
			productos.addMouseListener(new AccionLista(this));
			barra=new JScrollPane(productos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
			boton[i].addActionListener(new ComprasAccion(this));
			botones.add(boton[i]);
		}
		botones.add(new JLabel());
	
		boton[1].setEnabled(false);
		boton[2].setEnabled(false);

		this.add(botones, BorderLayout.SOUTH);
	}
	
	
	

	//BASEDATOS
	
	public void listaProductosMet() {
		String sql="SELECT nombre,id,stock FROM producto WHERE stock>0";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			
			while(result.next()) {
				ObjeCompra objeCompra=new ObjeCompra(result.getString("nombre"), result.getInt("id"), result.getInt("stock"));
				listaProductosTotal.add(objeCompra);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error en listaProductosMet");
			e.printStackTrace();
		}
		listaProductos=new DefaultListModel<String>();
		for(int i=0; i<listaProductosTotal.size(); i ++) {
			
		}
		
		for(ObjeCompra nombres:listaProductosTotal ) {
			listaProductos.addElement(nombres.getNombre());
		}
	}
	
	
	
	//FUNCIONALIDAD
	//Busca el producto seleccionado y mira en el array la cantidad para sl JSlider
	public void cantidadMet(String producto) { //Se activa desde AccionLista
		
		for(ObjeCompra a:listaProductosTotal) {
			if(a.getNombre().equals(producto)) { //Encontrado
				int stock=a.getCantidad();
				
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
	
	 //Cuando se de al boton añadir este objeto se crea y se pasa a la lista listaCompras en Pedidos
	public void compraRealizadaMet() {
		ObjeCompra objeCompra=null;
		if(validar()) {
			String nombreProducto = (String) productos.getSelectedValue();
	        int cantidadSeleccionada = cantidad.getValue();
	        //Busco id
	        ObjeCompra compraRealizada = null;
	        int idProducto=0;
	        for (ObjeCompra producto : listaProductosTotal) {
	            if (producto.getNombre().equals(nombreProducto)) {
	            	idProducto=producto.getId();
	            	
	            	int cantidadMax=producto.getCantidad(); //RESTO LA CANTIDAD Q COMPRA
	            	producto.setCantidad(cantidadMax-cantidadSeleccionada);
	                break;
	            }
	        }
	        objeCompra =new ObjeCompra(nombreProducto, idProducto, cantidadSeleccionada);
	        pedidos.getListaObjetosComprasTotal().add(objeCompra);
	        pedidos.listaComprasMet();
	        
	        //Quuito del total
	        
		}
	}
	
	public boolean validar() {
		if(productos.isSelectionEmpty()) {
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
		productos.clearSelection();
		cantidad.setEnabled(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JList getProductos() {
		return productos;
	}
	public void setProductos(JList productos) {
		this.productos = productos;
	}
	public ArrayList<ObjeCompra> getListaProductosTotal() {
		return listaProductosTotal;
	}
	public void setListaProductosTotal(ArrayList<ObjeCompra> listaProductosTotal) {
		this.listaProductosTotal = listaProductosTotal;
	}
	
	
}

