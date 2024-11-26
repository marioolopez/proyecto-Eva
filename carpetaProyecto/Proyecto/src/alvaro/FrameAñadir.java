package alvaro;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrameAñadir extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nombre, stock;
	private JComboBox<String> combobox;
	
	public FrameAñadir() {
		super();
		super.setSize(210, 240);
		super.setResizable(false);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		
	}
	
	protected void completarFrameProveedores() {
		super.setSize(210, 200);
		final String [] titulos= {"Nombre: ","CIF: ","AÑADIR PROVEEDOR "};
		JLabel [] tags=new JLabel[titulos.length];
		for (int posicion = 0; posicion < titulos.length; posicion++) {
			if(posicion==titulos.length-1) {
				JButton boton=new JButton(titulos[posicion]);
				boton.addActionListener(new OnClickProductosYProveedores(this));
				boton.setActionCommand(titulos[posicion]);
				super.getContentPane().add(boton);
			}
			else {
				tags[posicion]=new JLabel(titulos[posicion]);
				tags[posicion].setPreferredSize(new Dimension(150,20));
				super.getContentPane().add(tags[posicion]);
				
			}
			switch (posicion) {
			case 0:
				nombre=new JTextField();
				nombre.setPreferredSize(new Dimension(140,20));
				super.getContentPane().add(nombre);
				break;
			case 1:
				stock=new JTextField();
				stock.setPreferredSize(new Dimension(140,20));
				super.getContentPane().add(stock);
				break;
			}
		}
	}
	
	protected void completarFrameProductos(Connection conexion) throws SQLException {
		final String [] titulos= {"Nombre: ","Stock: ","Código del proveedor: ","AÑADIR PRODUCTO "};
		JLabel [] tags=new JLabel[titulos.length];
		Proveedor proveedor=new Proveedor();
		combobox=new JComboBox<String>(proveedor.devolverProveedores(conexion));
		conexion.close();
		for (int posicion = 0; posicion < titulos.length; posicion++) {
			if(posicion==titulos.length-1) {
				JButton boton=new JButton(titulos[posicion]);
				boton.addActionListener(new OnClickProductosYProveedores(this));
				boton.setActionCommand(titulos[posicion]);
				super.getContentPane().add(boton);
			}
			else {
				tags[posicion]=new JLabel(titulos[posicion]);
				tags[posicion].setPreferredSize(new Dimension(150,20));
				super.getContentPane().add(tags[posicion]);
				
			}
			switch (posicion) {
			case 0:
				nombre=new JTextField();
				nombre.setPreferredSize(new Dimension(140,20));
				super.getContentPane().add(nombre);
				break;
			case 1:
				stock=new JTextField();
				stock.setPreferredSize(new Dimension(140,20));
				super.getContentPane().add(stock);
				break;
			case 2:
				combobox.setPreferredSize(new Dimension(140,20));
				super.getContentPane().add(combobox);
				break;
			}
		}
	}
	
	private boolean comprobarCamposProducto() {
		boolean comprobar=true;
		if(nombre.getText().trim().isEmpty()) {
			comprobar=false;
		}
		if(comprobar && !stock.getText().trim().isEmpty()) {
			try {
				Integer.parseInt(stock.getText().trim());
			} catch (Exception e) {
				comprobar=false;
			}
		}
		else comprobar=false;
		if(comprobar && combobox.getSelectedIndex()<0) {
			comprobar=false;
		}
		return comprobar;
	}
	
	protected void insertarProducto(Connection conexion) throws SQLException {
		if(comprobarCamposProducto()) {
			Producto producto=new Producto();
			producto.insertar(conexion, nombre.getText().trim(), stock.getText().trim(), combobox.getSelectedItem().toString().split(". ")[0]);
			JOptionPane.showMessageDialog(this, "Producto añadido a la Base de Datos.");
			super.dispose();
			
		}
		else JOptionPane.showMessageDialog(this, "Rellene los campos correctamente.");
	}
	
	protected boolean comprobarCamposProveedor() {
		if(nombre.getText().trim().isEmpty() || stock.getText().trim().isEmpty()) {
			try {
				Integer.parseInt(stock.getText().trim());
				return true;
			} catch (Exception e) {
				return false;
			}			
		}
		else return true;
	}
	
	protected void insertarProveedor(Connection conexion) throws SQLException {
		if(comprobarCamposProveedor()) {
			Proveedor proveedor=new Proveedor();
			proveedor.darDeAltaProveedorEnLaBaseDeDatos(conexion, nombre.getText().trim(), Integer.parseInt(stock.getText().trim()));
			JOptionPane.showMessageDialog(this, "Proveedor añadido a la Base de Datos.");
			super.dispose();
		}
		else JOptionPane.showMessageDialog(this, "Rellene los campos correctamente.");
	}

	protected JTextField getNombre() {
		return nombre;
	}

	protected void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	protected JTextField getStock() {
		return stock;
	}

	protected void setStock(JTextField stock) {
		this.stock = stock;
	}

	protected JComboBox<String> getCombobox() {
		return combobox;
	}

	protected void setCombobox(JComboBox<String> combobox) {
		this.combobox = combobox;
	}
	
}
