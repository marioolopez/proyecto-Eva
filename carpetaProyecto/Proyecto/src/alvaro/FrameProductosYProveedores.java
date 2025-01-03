package alvaro;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FrameProductosYProveedores extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private DefaultTableModel contenidoTabla;
	private FrameAñadir añadir;
	private JScrollPane barraDeslizar;
	
	public FrameProductosYProveedores() {
		super();
		super.setTitle("PRODUCTOS");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		super.getContentPane().setLayout(new FlowLayout());
		super.setSize(550,400);
		super.setClosable(true);
	}
	
	public void completarFrame() {
		generarTablaProductos();
		añadirBotones();
		super.setVisible(true);
	}
	
	protected void completarInternalFrameProductos(Connection conexion) throws SQLException {
		añadir=new FrameAñadir();
		añadir.completarFrameProductos(conexion);
		añadir.setVisible(true);		
	}
	protected void completarInternalFrameProveedores() {
		añadir=new FrameAñadir();
		añadir.completarFrameProveedores();
		añadir.setVisible(true);
	}
	
	protected void generarTablaProductos() {
		final String [] titulosTablas= {"ID","NOMBRE","STOCK ACTUAL","ID DEL PROVEEDOR"};
		contenidoTabla=new DefaultTableModel(titulosTablas,0);
		tabla=new JTable(contenidoTabla);
		tabla.setPreferredSize(new Dimension(700,240));
		barraDeslizar=new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barraDeslizar.setPreferredSize(new Dimension(700,245));
		super.getContentPane().add(barraDeslizar);
	}
	private void añadirBotones() {
		final String [] titulosBotones= {"AÑADIR PRODUCTO","MOSTRAR PRODUCTOS","MOSTRAR AGOTADOS","BUSCAR POR NOMBRE", "ELIMINAR PRODUCTOS","ACTUALIZAR STOCK","AÑADIR PROVEEDOR"};
		JButton [] botones= new JButton [titulosBotones.length];
		for (int boton = 0; boton < botones.length; boton++) {
			botones[boton]=new JButton(titulosBotones[boton]);
			botones[boton].addActionListener(new OnClickProductosYProveedores(this));
			botones[boton].setActionCommand(titulosBotones[boton]);
			botones[boton].setPreferredSize(getPreferredSize());
			botones[boton].setPreferredSize(new Dimension(170,30));
			botones[boton].setBorder(new EmptyBorder(0, 15, 0, 15));
			super.getContentPane().add(botones[boton]);
		}
	}
	
	protected void rellenarTabla(String filtro, Connection conexion) {
		contenidoTabla.setRowCount(0); // LIMPIO LA TABLA
		switch (filtro) {
		case "MOSTRAR PRODUCTOS":
			rellenarTablaConTodosLosProductos(conexion,"SELECT * from producto");
			break;
		case "MOSTRAR AGOTADOS":
			rellenarTablaConTodosLosProductos(conexion,"SELECT * from producto where stock=0");
			break;
		case "BUSCAR POR NOMBRE":
			String busqueda= JOptionPane.showInputDialog(this, "Introduzca el nombre del producto que busca.");
			if(busqueda!=null && !busqueda.trim().isEmpty()) {
				rellenarTablaConTodosLosProductos(conexion, "SELECT * FROM producto where nombre like '"+busqueda+"'");
			}
			break;
		}
	}
	
	private void rellenarTablaConTodosLosProductos(Connection conexion, String consultaSQL) {
		if(conexion!=null) {
			try {
				boolean existen=false;
				Statement consulta=conexion.createStatement();
				ResultSet resultado=consulta.executeQuery(consultaSQL);
				while (resultado.next()) {
					existen=true;
					Object [] objeto= {resultado.getInt(1),resultado.getString(2),resultado.getInt(3),resultado.getInt(4)};
					contenidoTabla.addRow(objeto);
				}
				conexion.close();
				if(!existen) {
					contenidoTabla.addRow(new Object[] {"No existen productos."});
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "No fue posible realizar la acción solicitada.");
			}
		}	
	}
	
	protected void eliminarProducto(Connection conexion) throws SQLException {
		
		//LA SEGUNDA CONDICIÓN ES PARA QUE NO TE PERMITA ELIMINAR CUANDO ELIGES "NO EXISTEN PRODUCTOS"
		
		if(tabla.getSelectedRowCount()>0 && !tabla.getValueAt(0, 0).toString().contains("No")) {			
			Producto producto=new Producto();
			for(int fila: tabla.getSelectedRows()) {
				if(JOptionPane.showConfirmDialog(this, "¿Desea eliminar el producto: "+tabla.getValueAt(fila, 1).toString()+"?")==JOptionPane.YES_OPTION) {
					producto.eliminarStock(conexion, tabla.getValueAt(fila, 0).toString());
					JOptionPane.showMessageDialog(this, "Producto eliminado.");
				}
			}
			rellenarTabla("MOSTRAR PRODUCTOS",conexion);
			conexion.close();
		}
		else {
			JOptionPane.showMessageDialog(this, "No hay productos seleccionados.");
		}
	}
	
	protected void actualizarStock(Connection conexion) throws SQLException {		
		
		//LA SEGUNDA CONDICIÓN ES PARA QUE NO TE PERMITA ACTUALIZARCUANDO ELIGES "NO EXISTEN PRODUCTOS"
		
		if(tabla.getSelectedRowCount()>0 && !tabla.getValueAt(0, 0).toString().contains("No")) {			
			Producto producto=new Producto();
			for(int fila: tabla.getSelectedRows()) {
				String nuevoStock= JOptionPane.showInputDialog(this, "Introduzca el nuevo stock para el producto: "+tabla.getValueAt(fila, 1));
				try {
					int stock= Integer.parseInt(nuevoStock);
					if(stock>-1) {
						
						producto.actualizarStock(conexion, tabla.getValueAt(fila, 0).toString(), stock);
						JOptionPane.showMessageDialog(this, "Stock actualizado.");
						
					}
					else JOptionPane.showMessageDialog(this, "No se aceptan valores negativos.");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Introduzca un valor válido.");
				}
			}
			rellenarTabla("MOSTRAR PRODUCTOS",conexion);
			conexion.close();
		}
		else {
			JOptionPane.showMessageDialog(this, "No hay productos seleccionados.");
		}		
	}

	protected JTable getTabla() {
		return tabla;
	}

	protected void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	protected DefaultTableModel getContenidoTabla() {
		return contenidoTabla;
	}

	protected void setContenidoTabla(DefaultTableModel contenidoTabla) {
		this.contenidoTabla = contenidoTabla;
	}

	protected FrameAñadir getAñadir() {
		return añadir;
	}

	protected void setAñadir(FrameAñadir añadir) {
		this.añadir = añadir;
	}

	protected JScrollPane getBarraDeslizar() {
		return barraDeslizar;
	}

	protected void setBarraDeslizar(JScrollPane barraDeslizar) {
		this.barraDeslizar = barraDeslizar;
	}
}

