package Pedidos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import Main.BaseDatos;
import Main.ventanaPrincipal;

public class Pedidos extends JInternalFrame{
	private ventanaPrincipal ven;
	private JLabel txt1,txt2,txt3,txt4;
	private JTextField id;
	private JCalendar calendario;
	private JPanel datos,botones, izq, izqArr, izqAba, centro, der;
	private JList listaCliente, listaCompras;
	private JScrollPane barra1,barra2;
	private DefaultListModel<String> listaObjetosCompras; //Muestra el nombre por la lista
	private ArrayList<ObjeCompra> listaObjetosComprasTotal; //Almacena los objetos con el id, nombre y cantidad seleccionada por el usuario
	private DefaultListModel<String> listaClientes; //Muestra nombres
	//private ArrayList<Cliente> listaClientesTotal;
	private JButton[] boton;
	private String[] botonesNom= {"Añadir", "mostrar", "modificar", "eliminar"};
	private Compras compra;
	

	//ARRIBA
	public Pedidos(ventanaPrincipal ven) {
		super();
		this.ven=ven;
		this.setLayout(new FlowLayout());
		//INICIALIZA
		listaObjetosCompras=new DefaultListModel<String>();
		listaObjetosComprasTotal=new ArrayList<ObjeCompra>();
		listaClientes=new DefaultListModel<String>();

		datosMe();//Dibuja
		botonesMe();//Dibuja
		idMax();
		clientesNombre(); //Muestra los nombres en la lista
		compra=new Compras(this);
		compra.setPreferredSize(new Dimension(700,150));
		compra.setVisible(true);
		this.add(compra);
		
	}
	
	public void datosMe() {
		datos=new JPanel();
		
		datos.setLayout(new GridLayout(1,3,10,10));
			izq=new JPanel();
			izq.setLayout(new BorderLayout());
				izqArr=new JPanel();
				izqArr.setLayout(new BorderLayout());
					txt1=new JLabel("IdPedido");
					izqArr.add(txt1, BorderLayout.NORTH);
					id=new JTextField();
					id.setEditable(false);
				izqArr.add(id, BorderLayout.CENTER);
			izq.add(izqArr, BorderLayout.NORTH);
			
			izqAba=new JPanel();
			izqAba.setLayout(new BorderLayout());
				txt2=new JLabel("Cliente");
				izqAba.add(txt2,BorderLayout.NORTH);
					listaCliente=new JList<String>(listaClientes);
					barra1=new JScrollPane(listaCliente,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				izqAba.add(barra1, BorderLayout.CENTER);
			izq.add(izqAba, BorderLayout.CENTER);
		datos.add(izq);
			
			centro=new JPanel();
			centro.setLayout(new BorderLayout());
				txt3=new JLabel("Fecha");
				centro.add(txt3, BorderLayout.NORTH);
				calendario=new JCalendar();
				centro.add(calendario, BorderLayout.CENTER);
		datos.add(centro);
			
			der=new JPanel();
			der.setLayout(new BorderLayout());
				txt4=new JLabel("Id Compras");
				der.add(txt4, BorderLayout.NORTH);
				listaCompras=new JList<String>(listaObjetosCompras);
				barra2=new JScrollPane(listaCompras,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				der.add(barra2, BorderLayout.CENTER);
		datos.add(der);
		datos.setPreferredSize(new Dimension(750,250));
		this.add(datos);

	}
	
	public void botonesMe() {
		botones=new JPanel();
		botones.setLayout(new GridLayout(1,4));
		boton=new JButton[botonesNom.length];
		for(int i=0; i<botonesNom.length; i++) {
			boton[i]=new JButton(botonesNom[i]);
			boton[i].addActionListener(new PedidosAccion(this));
			botones.add(boton[i]);
		}
		boton[0].setEnabled(false);
		boton[2].setEnabled(false);
		boton[3].setEnabled(false);
		this.add(botones);
	}
	

	//BASEDATOS
	public void idMax() {
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
			System.out.println("Error con el idMa 2x");
			e.printStackTrace();
		}
		id.setText(String.valueOf(idMax + 1));
	}
	
	public void clientesNombre() {
		String sql="SELECT nombre FROM cliente";
		BaseDatos bs=null;
		ResultSet result=null;
		try {
			bs=new BaseDatos();
			result=bs.ejecutarSQL1(sql);
			while(result.next()) {
				listaClientes.addElement(result.getString("nombre"));
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
			e.printStackTrace();
		}
	}
	
	public void anadirMetPedido() {
		java.util.Date fecha = calendario.getDate();
		// Luego conviértelo a java.sql.Date
		java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
		String sql="INSERT INTO pedido (id, fecha) VALUES ('" + Integer.parseInt(id.getText()) + "','" + fechaSql+ "')";
		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			bs.ejecutarSQL2(sql);
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
			e.printStackTrace();
		}
		anadirMetCompra();
	}
	
	public void anadirMetCompra() {

		BaseDatos bs=null;
		try {
			bs=new BaseDatos();
			for(ObjeCompra a:listaObjetosComprasTotal) {
				System.out.println(a.getId());
				int idproducto=a.getId();
				int cantidad=a.getCantidad();
				String sql="INSERT INTO compra (idpedido,idproducto,cantidad) VALUES ('" + Integer.parseInt(id.getText()) + "','" + idproducto + "','" + cantidad + "')";
				bs.ejecutarSQL2(sql);
			}
			bs.cerrarConex();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con el idMax");
			e.printStackTrace();
		}
	}
	
	//FUNCIONALIDAD
	public void listaComprasMet() { //Actualiza y si hay algo activa el botón añadir
		listaObjetosCompras.removeAllElements();
		for(ObjeCompra a: listaObjetosComprasTotal) {
			listaObjetosCompras.addElement(a.getNombre());
		}
		boton[0].setEnabled(true);
	}
	
	//Reseteo
	public void reseteoMet() {
		listaCliente.clearSelection();
		calendario.setDate(null);
		id.setText(String.valueOf(Integer.parseInt(id.getText())+1));
	}
	
	
	
	
	
	

	public JList getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(JList listaCompras) {
		this.listaCompras = listaCompras;
	}

	public ArrayList<ObjeCompra> getListaObjetosComprasTotal() {
		return listaObjetosComprasTotal;
	}

	public void setListaObjetosComprasTotal(ArrayList<ObjeCompra> listaObjetosComprasTotal) {
		this.listaObjetosComprasTotal = listaObjetosComprasTotal;
	}
	
	
	

}
